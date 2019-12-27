<template>
    <el-tabs v-model="name" type="card"
             @tab-click="handleClickTabPane"
             @tab-remove="handleRemoveTabPane">
        <template v-if="tabRows !== void 0">
            <el-tab-pane v-for="(row, index) in tabRows"
                         :key="row.key"
                         :label="row.label"
                         :name="row.name"
                         :disabled="row.disabled"
                         :lazy="row.lazy"
                         :closable="(row.closable === true) && tabRows.length > 1"
            >
            </el-tab-pane>
        </template>
    </el-tabs>
</template>

<script>

    import {mapState} from 'vuex';
    import {isArray, humpToString} from "../../../utils/util.js";
    import {Tab} from "../../../vuex-modules/frame-store.js";
    import {getRoutePathCache, setRoutePathCache} from "../../frame/router/src/route.js";

    export default {

        name: "tab-mode",

        props: {
            data: {
                type: Array,
                default() {
                    return [];
                }
            }
        },

        data() {
            return {
                // 当前选项卡名称(理论上是当前路由名称)
                name: '',
                // 当前已经打开的选项卡
                tabRows: undefined,
                // 所有选项卡
                tabs: []
            };
        },

        watch: {
            data: {
                immediate: true,
                handler(val) {
                    this.setTabs(val);
                }
            },
            // 观测当前路由
            $route: {
                immediate: true,
                handler(val) {
                    if (!val) return;
                    setRoutePathCache(val.name, val.path);
                    if (val.meta && val.meta.cache === true) {
                        // 添加缓存
                        this.$store.dispatch('addTabKeepAliveName', humpToString(val.name).substring(1));
                    }
                    this.name = val.name;
                }
            },
            // 观测当前路由名称(值)
            name: {
                immediate: true,
                handler(val) {
                    this.resetCurrentTab();
                }
            },
            tabRows(val, oldValue) {
                // tabRows一旦初始化进数据,不再观测
                if (isArray(oldValue) && oldValue.length > 0) return;
                this.resetCurrentTab();
            }
        },

        methods: {
            resetCurrentTab() {
                if (this.tabRows === void 0) return;
                // 能执行到这里说明已经初始化过tabs了,哪怕是个[]
                // 判断当前name(理论上说是当前路由name)是否已经存在于打开的选项卡中
                let tabRow;
                for (let i in this.tabRows) {
                    tabRow = this.tabRows[i];
                    // 使用选项卡关联的路由来判断当前路由属于哪个选项卡
                    if (tabRow.routerValues.indexOf(this.name) !== -1) {
                        // 当前路由存在于打开的选项卡中
                        // 关闭已经激活的路由并激活选项卡中对应路由
                        this.enableRouter(tabRow.routerEnabled, this.name, tabRow);
                        return
                    }
                }
                // 走到这里说明name对应的选项卡还未打开,那么将其打开
                // 找到对应选项卡
                for (let i in this.tabs) {
                    tabRow = this.tabs[i];
                    // 使用选项卡关联的路由来判断当前路由属于哪个选项卡
                    if (tabRow.routerValues.indexOf(this.name) !== -1) {
                        // 找到了
                        // 关闭已经激活的路由并激活选项卡中对应路由
                        this.enableRouter(tabRow.routerEnabled, this.name, tabRow);
                        // 打开
                        this.tabRows.push(tabRow);
                        return;
                    }
                }
                //throw new Error(`未找到路由:[${this.name}]对应的选项卡`);
            },
            // 激活选项卡路由
            enableRouter(routerEnabled, name, tab) {
                for (let propName in routerEnabled) {
                    if (routerEnabled[propName] === true) {
                        routerEnabled[propName] = false;
                    }
                }
                // 激活选项卡中对应路由
                routerEnabled[name] = true;
                // 改变当前选项卡name,以便选项卡出现选中效果
                tab.name = name;
                //TODO 可以扩展tab属性,实现选项卡名称变为子路由
            },
            routerToEnabled(routerEnabled) {
                for (let name in routerEnabled) {
                    if (routerEnabled[name] === true) {
                        const path = getRoutePathCache(name);
                        if (path === void 0) {
                            this.$router.push({name: name});
                        } else {
                            this.$router.push({path: path});
                        }
                        return;
                    }
                }
                throw new Error(`未找到可以跳转的路由`);
            },
            setTabs(tabs = []) {
                if (!isArray(tabs) || tabs.length === 0) return;
                let tabRows = [];
                // 找到需要默认打开的选项卡
                // 匹配规则, 以包含当前路由的第一个
                tabs.forEach(tab => {
                    if (!(tab instanceof Tab)) {
                        throw new Error('setTabs tab 必须是 Tab类型...');
                    }
                    // 初始化需要在选项卡中打开 且 菜单指向的路由不包含当前路由
                    if (tab.initOpen === true) {
                        tabRows.push(tab);
                    }
                });
                this.tabs = tabs;
                this.tabRows = tabRows;
            },
            handleClickTabPane(pane) {
                let name = pane.name;
                let len = this.tabRows.length;
                for (let i = 0; i < len; i++) {
                    if (name === this.tabRows[i].name) {
                        this.routerToEnabled(this.tabRows[i].routerEnabled);
                        return;
                    }
                }
            },
            handleRemoveTabPane(name) {
                let len = this.tabRows.length;
                let i = 0;
                for (; i < len; i++) {
                    if (name === this.tabRows[i].name) {
                        // 移除缓存
                        this.$store.dispatch('removeTabKeepAliveName', humpToString(name).substring(1));
                        this.tabRows.splice(i, 1);
                        break;
                    }
                }
                if (this.name === name) {
                    // 如果删除的是当前选中的选项卡
                    if (i === 0) {
                        // 删除的是第一个选项卡, 从tabRows中按顺序找到第一个可用的选项卡选中
                        for (let j = 0; j < this.tabRows.length; j++) {
                            if (this.tabRows[j].disabled !== true) {
                                this.name = this.tabRows[j].name;
                                this.routerToEnabled(this.tabRows[j].routerEnabled);
                                // 终止
                                return;
                            }
                        }
                    } else if (i > 0) {
                        // 删除的不是第一个之后的选项卡, 先以当前位置倒序找到第一个可用的选项卡选中, 找不到再正序找到第一个可用的选项卡选中
                        //倒序
                        for (let j = i - 1; j >= 0; j--) {
                            if (this.tabRows[j].disabled !== true) {
                                this.name = this.tabRows[j].name;
                                this.routerToEnabled(this.tabRows[j].routerEnabled);
                                // 终止
                                return;
                            }
                        }
                        //正序
                        for (let j = i; j < this.tabRows.length; j++) {
                            if (this.tabRows[j].disabled !== true) {
                                this.name = this.tabRows[j].name;
                                this.routerToEnabled(this.tabRows[j].routerEnabled);
                                // 终止
                                return;
                            }
                        }
                    }
                }
            },
            initAddTabKeepAliveCache(routes) {
                isArray(routes) && routes.forEach(route => {
                    isArray(route.children) && this.initAddTabKeepAliveCache(route.children);
                    if (route.meta && route.meta.cache === true) {
                        this.$store.dispatch('addTabKeepAliveName', humpToString(route.name).substring(1));
                    }
                });
            }
        },

        created() {
            // 初始化将所有需要缓存的路由加进去
            this.initAddTabKeepAliveCache(this.$router.options.routes);
        }

    }
</script>
