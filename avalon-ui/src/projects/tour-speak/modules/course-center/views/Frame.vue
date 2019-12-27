<script>

    /*模块全局配置,用于参考*/
    const EventEmitter = require('events');//全局事件对象
    window.events = new EventEmitter();
    window.events.setMaxListeners(100);//设置最大监听数

    //全局注册一些第三方组件,这里以element-ui和weview举例

    import Vue from 'vue';

    import Element from 'element-ui';
    import 'element-ui/lib/theme-chalk/index.css';

    Vue.use(Element);

    import weview from 'weview';
    import 'weview/lib/theme-chalk/index.css';

    Vue.use(weview);

    //使用Packages包下工具
    import Packages from '../../../../../../packages/index.js';
    import {DataParser, $Message} from '../../../../../../packages/index.js';

    Vue.use(Packages, {
        Ajax: {
            options: {
                mock: false,//开启模拟数据
                mockTimeout: 1000,
                baseURL: require('../../../../../../config/axios.js').getBaseUrl(),
                withCredentials: true,//允许跨域携带cookie
                dataParserOptions: {
                    use: DataParser.DataView,
                    options: {
                        needLoginOptions: {//需要登录
                            callback: (data, res) => {
                                if (data && data.data && data.data.logoutUrl) {
                                    window.location.href = data.data.logoutUrl;
                                } else {
                                    // window.events.emit('show-login-window');
                                }
                            }
                        },
                        noAuthorityOptions: {//无权限
                            callback(data, res) {
                                $Message(data.resultInfo.message);
                            }
                        },
                        notFoundOptions: {//404
                            callback(data, res) {
                                $Message(data.resultInfo.message);
                            }
                        }
                    }
                }
            }
        }
    });

    import Directives from '../../../../../../packages/directives/index.js';

    Vue.use(Directives);

    //导入一些自定义样式
    //使用混合
    import Gar from '../../../../gar/mixins/gar.js';
    import Link from '../../../mixins/link.js';

    import {mapState} from 'vuex';
    import {
        Role,
        getLoginCacheKey,
        getPrimaryRoleCacheKey,
        getHeaderMenuRowsCacheKey,
        getLeftMenuRowsCacheKey,
        getRouteRowsCacheKey
    } from "../../../vuex-modules/frame-store.js";

    import ApiGarUrls from "../../../../gar/urls/api-gar-urls.js";
    import {getCache, setCache, removeCache} from "../../../../../utils/cache.js";
    import {
        getCurrentHeaderMenuRowsCacheKey,
        getCurrentLeftMenuRowsCacheKey,
        allCurrentCacheExist
    } from "../utils/module.js";
    import ProjectConf from "../config/module.conf.js";

    export default {

        name: "Frame",

        mixins: [Gar, Link],

        data() {
            return {
                user: {},
                primaryRole: undefined,
                roles: []
            };
        },

        computed: {
            ...mapState(['frameModule']),
            userId() {
                return this.user.id;
            }
        },

        methods: {
            initModule() {
                //初始化判断用户是否在线
                new Promise((resolve, reject) => {
                    this.$Ajax.get(ApiGarUrls.get.online)
                        .success(true, data => {
                            this.user = data.records.user;
                            resolve(data.records);
                        })
                        .needLogin(true, reject);
                }).then(({user: {id: userId, username}, roles}) => {
                    return new Promise((resolve, reject) => {
                        //判断是否有相关缓存
                        let exist = allCurrentCacheExist({key: userId});
                        if (!exist) {
                            // 不存在相关缓存, 转到登录模块
                            reject();
                            return;
                        }
                        //校验登录信息与缓存是否一致
                        const loginCacheKey = getLoginCacheKey();
                        const loginCacheJson = getCache({key: loginCacheKey});
                        if (!loginCacheJson) {
                            // 不存在登录缓存, 转到登录模块
                            reject();
                            return;
                        }
                        if (userId !== JSON.parse(loginCacheJson).userId) {
                            // 登录信息不一致, 转到登录模块
                            reject();
                            return;
                        }
                        //解析角色
                        const primaryRoleCacheKey = getPrimaryRoleCacheKey({key: this.userId});
                        if (!roles || roles.length === 0) {
                            // 没有角色
                            this.primaryRole = undefined;
                            this.roles = [];
                            // 当前用户没有角色, 清除当前用户主角色缓存
                            removeCache({key: primaryRoleCacheKey});
                            resolve({
                                user: {id: userId, username},
                                primaryRole: undefined,
                                roles: []
                            });
                            return;
                        }
                        if (roles.length === 1) {
                            // 只有一个角色
                            this.primaryRole = new Role(roles[0]);
                            this.roles = roles.map(role => new Role(role));
                            // 与缓存中的主角色进行对比, 如果不一致则跳转到登录重新载入配置
                            const primaryRoleJsonCache = getCache({key: primaryRoleCacheKey});
                            if (JSON.stringify(this.primaryRole) !== primaryRoleJsonCache) {
                                reject();
                                return;
                            }
                            resolve({
                                user: {id: userId, username},
                                primaryRole: this.primaryRole,
                                roles: this.roles
                            });
                            return;
                        }
                        if (roles.length > 1) {
                            // 有多个角色
                            this.roles = roles.map(role => new Role(role));
                            // 从缓存中尝试拿出主角色
                            const primaryRoleJson = getCache({key: primaryRoleCacheKey});
                            if (!primaryRoleJson) {
                                //没有缓存, 跳回登录页重新获取
                                reject();
                                return;
                            }
                            // 判断缓存的主角色是否为当前用户拥有的角色,判断方式,字符串比对
                            for (let i in this.roles) {
                                if (JSON.stringify(this.roles[i]) === primaryRoleJson) {
                                    // 缓存主角色是当前用户已有角色
                                    this.primaryRole = new Role(this.roles[i]);
                                    resolve({
                                        user: {id: userId, username},
                                        primaryRole: this.primaryRole,
                                        roles: this.roles
                                    });
                                    return;
                                }
                            }
                            // 走到这一步,说明用户缓存的主角色不是当前已有角色列表中的,退回登录页,重新选择
                            reject();
                        }
                    });
                }).then(({user: {id: userId, username}, primaryRole, roles}) => {
                    //登录成功
                    //处理数据
                    Promise.all([new Promise((resolve, reject) => {
                        // 设置登录信息
                        this.$store.dispatch('setLogin', {userId, username}).then(resolve);
                    }), new Promise((resolve, reject) => {
                        // 设置角色信息
                        this.$store.dispatch('setPrimaryRoleAndRoles', {
                            primaryRole: primaryRole,
                            roles: roles
                        }).then(resolve);
                    }), new Promise((resolve, reject) => {
                        //设置头部菜单信息
                        let headerMenuRows = [];
                        const headerMenuRowsCacheKey = getCurrentHeaderMenuRowsCacheKey({key: userId});
                        const headerMenuRowsJson = getCache({key: headerMenuRowsCacheKey});
                        if (headerMenuRowsJson) {
                            headerMenuRows = JSON.parse(headerMenuRowsJson);
                        }
                        this.$store.dispatch('setHeaderMenuRows', {rows: headerMenuRows}).then(resolve);
                    }), new Promise((resolve, reject) => {
                        //设置左侧菜单信息
                        let leftMenuRows = [];
                        /*            const leftMenuRowsCacheKey = getCurrentLeftMenuRowsCacheKey({key: userId});
                                    const leftMenuRowsJson = getCache({key: leftMenuRowsCacheKey});
                                    if (leftMenuRowsJson) {
                                      leftMenuRows = JSON.parse(leftMenuRowsJson);
                                    }*/
                        this.$store.dispatch('setLeftMenuRows', {rows: leftMenuRows}).then(resolve);
                    })]);
                }).then(() => {
                    //数据处理完毕
                }).catch(data => {
                    if (data && data.data && data.data.logoutUrl) {
                        window.location.href = data.data.logoutUrl;
                    } else {
                        this.pageToModule("Login");
                    }
                });
            }
        },

        created() {
            this.initModule();
            window.events.on('login-success', () => this.initModule());
            window.events.on('logout-success', () => {
                // 登出成功
                // 清除相关配置
                const loginCacheKey = getLoginCacheKey();
                const primaryRoleCacheKey = getPrimaryRoleCacheKey({key: this.userId});
                const routeRowsCacheKey = getRouteRowsCacheKey({
                    projectName: ProjectConf.projectName,
                    moduleName: ProjectConf.moduleName,
                    key: this.userId
                });
                const headerMenuRowsCacheKey = getHeaderMenuRowsCacheKey({
                    projectName: ProjectConf.projectName,
                    moduleName: ProjectConf.moduleName,
                    key: this.userId
                });
                const leftMenuRowsCacheKey = getLeftMenuRowsCacheKey({
                    projectName: ProjectConf.projectName,
                    moduleName: ProjectConf.moduleName,
                    key: this.userId
                });
                removeCache({key: loginCacheKey});
                removeCache({key: primaryRoleCacheKey});
                removeCache({key: routeRowsCacheKey});
                removeCache({key: headerMenuRowsCacheKey});
                removeCache({key: leftMenuRowsCacheKey});
                this.pageToModule("Login");
            });
            window.events.on('selected-primary-role', ({primaryRole, index, roles}) => {
                // 用户选择完主角色, 将主角色缓存起来
                if (JSON.stringify(this.primaryRole) === JSON.stringify(primaryRole)) return;
                this.primaryRole = primaryRole;
                const primaryRoleCacheKey = getPrimaryRoleCacheKey({key: this.userId});
                setCache({key: primaryRoleCacheKey, data: JSON.stringify(primaryRole)});
                setTimeout(() => {
                    this.pageToModule("Login");
                }, 300);
            });
        }

    }
</script>
<!--这里是一种示例,根据自己实际情况自己设计页面布局,这里只做参考-->
<template>
    <router-view></router-view>
</template>

<style lang="less" type="text/less">
    @import "../../../theme-chalk/index.less";
</style>
