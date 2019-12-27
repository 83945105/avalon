<template>
    <div>
        <div class="gar-layout-custom common-border-box" style="border-right: 1px solid #e5e5e5;right: 50%">
            <div class="gar-title is-border">
                <h3 class="gar-title-left">设置关联路由及跳转路由</h3>
                <div class="gar-title-right is-margin">
                    <we-button :loading="treeAsyncLoading" size="mini" icon-name="refresh-o"
                               @click="getTreeAsyncRows"></we-button>
                </div>
                <div class="common-clear"></div>
            </div>
            <div class="gar-layout-custom common-padding-all-small gar-tree" style="top: 41px;">
                <div>
                    <el-input size="small"
                              placeholder="请输入关键字"
                              v-model="params.likeText"
                              clearable
                              class="common-wp-80 common-float-left">
                    </el-input>
                    <we-button :loading="treeAsyncLoading" size="small" @click="getTreeAsyncRows"
                               class="common-float-left"
                               style="width: 19%;margin-left:1%;">查询
                    </we-button>
                    <div class="common-clear"></div>
                </div>
                <we-skeleton v-model="treeAsyncSkeleton"
                             animation
                             :paragraph-rows="8"
                             @reload="getTreeAsyncRows">
                    <div v-loading="!treeAsyncSkeleton && treeAsyncLoading" class="common-full common-scroll-v"
                         style="top: 42px;">
                        <el-tree :ref="treeAsyncRef"
                                 :data="treeAsyncRows"
                                 :props="treeAsyncProps"
                                 :node-key="treeAsyncNodeKey"
                                 :lazy="true"
                                 :load="getTreeAsyncNodes"
                                 :expand-on-click-node="false"
                                 :check-strictly="true"
                                 empty-text="暂无资源"
                                 class="common-margin-t-small gar-tree-custom"
                        >
                            <span class="custom-tree-node" slot-scope="{node, data}">
                                <span class="custom-tree-node-left"
                                      @dblclick="handleDblClickTreeAsyncNode({node, data})">

                                    <!--路由树复选框开始-->

                                    <template v-if="disabledRouteIds.includes(data.id)">
                                        <we-tooltip content="该路由已被其它菜单关联" placement="top">
                                            <el-checkbox :disabled="true" :value="false"></el-checkbox>
                                        </we-tooltip>
                                    </template>
                                    <template v-else-if="checkedRouteIds.includes(data.id)">
                                        <we-tooltip content="取消关联该路由后,关联菜单在该路由下将无法显示为选中效果" placement="top">
                                            <el-checkbox :value="true"
                                                         @change="handleUnCheckedRoute({node, data})"></el-checkbox>
                                        </we-tooltip>
                                    </template>
                                    <template v-else>
                                        <we-tooltip content="关联该路由后,关联菜单会在该路由下显示为选中效果" placement="top">
                                            <el-checkbox :value="false"
                                                         @change="handleCheckedRoute({node, data})"></el-checkbox>
                                        </we-tooltip>
                                    </template>


                                    <!--路由树复选框结束-->
                                    <we-icon name="gar-route"></we-icon>
                                    {{node.label}}
                                </span>
                                <span class="custom-tree-node-right">

                                    <template v-if="clickToRouteId === data.id">
                                        <we-popover title="操作" type="confirm"
                                                    placement="top-start"
                                                    :manual="clickToRouteLoading"
                                                    confirm-button-text="取消吧"
                                                    :cancel-button-text="clickToRouteLoading ? '请稍等' : '我再想想'"
                                                    :cancel-button-options="{disabled: clickToRouteLoading}"
                                                    :confirm-button-options="{loading: clickToRouteLoading}"
                                                    @click-cancel-button="(e, vm) => vm.close()"
                                                    @click-confirm-button="(e, vm) => handleDeleteClickMenuToRoute({node, data})">
                                            <template slot="content">
                                                <template v-if="clickToRouteLoading">
                                                    <span style="color: red">删除中...</span>删除完成后将自动关闭气泡
                                                </template>
                                                <template v-else>
                                                    您确定要<span style="color: red">取消</span>该路由为<span style="color: red">
                                                    跳转路由</span>吗？
                                                </template>
                                            </template>
                                            <div class="common-fc-success common-padding-tb-mini">已设置该路由为跳转路由</div>
                                        </we-popover>
                                    </template>
                                    <template v-else>
                                        <we-button :disabled="clickToRouteLoading" type="text" size="small"
                                                   @click="handleAddClickMenuToRoute({node, data})">设置为跳转路由
                                        </we-button>
                                    </template>

                                </span>
                                <div class="common-clear"></div>
                            </span>
                        </el-tree>
                    </div>
                </we-skeleton>
            </div>
        </div>
        <div class="gar-layout-custom" style="left:50%">
            <div class="gar-title is-border">
                <h3 class="gar-title-left">授予角色</h3>
                <div class="gar-title-right is-margin">
                    <we-button size="mini" icon-name="refresh-o" @click="getListRows"></we-button>
                </div>
                <div class="common-clear"></div>
            </div>
            <we-skeleton v-model="listSkeleton" animation :paragraph-rows="10"
                         :title-width="'50%'" :paragraph-width="'100%'"
                         @reload="getListRows"
            >
                <div v-loading="!listSkeleton && listLoading"
                     class="gar-layout-custom common-padding-all-small common-scroll-v"
                     style="top: 41px;">
                    <!--          <div style="margin: 0 1%">
                                <el-checkbox :indeterminate="isIndeterminate" v-model="checkAll" @change="handleCheckAllChange">全选
                                </el-checkbox>
                              </div>-->
                    <ul class="gar-list-average is-col-4">
                        <li v-for="(row, index) in listRows">

                            <template v-if="row.status !== dict.statusValue.NORMAL">
                                <we-tooltip content="该角色已被禁用,无法操作" placement="top">
                                    <el-checkbox disabled border size="small"
                                                 :value="checkedRoleIds.includes(row.id)"
                                                 class="common-w-all">{{row.name}}
                                    </el-checkbox>
                                </we-tooltip>
                            </template>
                            <template v-else-if="checkedRoleIds.includes(row.id)">
                                <we-tooltip content="取消授予该角色后,该角色将无法访问该菜单" placement="top">
                                    <el-checkbox :value="true"
                                                 :label="row"
                                                 @change="handleUnCheckedRole({row, $index: index})"
                                                 border size="small"
                                                 class="common-w-all">
                                        {{row.name}}
                                    </el-checkbox>
                                </we-tooltip>
                            </template>
                            <template v-else>
                                <we-tooltip content="授予该角色后,该角色可以访问该菜单" placement="top">
                                    <el-checkbox :value="false"
                                                 :label="row"
                                                 @change="handleCheckedRole({row, $index: index})"
                                                 border size="small"
                                                 class="common-w-all"
                                    >{{row.name}}
                                    </el-checkbox>
                                </we-tooltip>
                            </template>

                        </li>
                    </ul>
                </div>
            </we-skeleton>
        </div>
    </div>
</template>

<script>

    import Global from '../../../../mixins/global.js';

    import TreeAsync from '../../../../../../assets/template/mixins/treeAsync.js';
    import List from '../../../../../../assets/template/mixins/list.js';
    import ErrorPage from "../../../../../../components/error-page/src/ErrorPage.vue";
    import merge from "../../../../../../utils/merge.js";
    import ApiGarRouteUrls from "../../urls/api-gar-route-urls.js";
    import ApiGarMenuRouteUrls from "../../urls/api-gar-menu-route-urls.js";
    import ApiGarRoleUrls from "../../urls/api-gar-role-urls.js";
    import ApiGarRoleMenuUrls from "../../urls/api-gar-role-menu-urls.js";

    const Params = {
        likeText: ''
    };

    export default {

        name: "menu-route-role",

        componentName: "MenuRouteRole",

        components: {ErrorPage},

        mixins: [Global, TreeAsync, List],

        props: {
            moduleId: {
                type: String,
                default: ''
            },
            subModuleId: {
                type: String,
                default: ''
            },
            menuId: {
                type: String,
                default: ''
            }
        },

        data() {
            return {
                params: merge({}, Params),

                //TODO 编写你的异步树节点属性
                treeAsyncProps: {
                    label: 'name',
                    isLeaf: 'leaf',
                    children: 'children',
                    disabled: 'disabled'
                },
                //TODO 设置树节点唯一标识属性
                treeAsyncNodeKey: 'id',

                menuRouteList: [],
                clickToRouteLoading: false,

                roleMenuList: [],
                checkedRoleLoading: false,

            }
        },

        computed: {
            // 禁用的路由ID
            disabledRouteIds() {
                let ids = [];
                this.menuRouteList.forEach(menuRoute => {
                    if (menuRoute.menuId === this.menuId || menuRoute.relation !== this.dict.menuRouteRelationValue.MENU_SELECTED) return true;
                    ids.push(menuRoute.routeId);
                });
                return ids;
            },
            // 选中的路由ID
            checkedRouteIds() {
                let ids = [];
                this.menuRouteList.forEach(menuRoute => {
                    if (menuRoute.menuId !== this.menuId || menuRoute.relation !== this.dict.menuRouteRelationValue.MENU_SELECTED) return true;
                    ids.push(menuRoute.routeId);
                });
                return ids;
            },
            // 跳转的路由ID
            clickToRouteId() {
                for (let i in this.menuRouteList) {
                    if (this.menuRouteList[i].menuId === this.menuId && this.menuRouteList[i].relation === this.dict.menuRouteRelationValue.MENU_CLICK_TO_ROUTE) {
                        return this.menuRouteList[i].routeId;
                    }
                }
                return undefined;
            },
            // 授予的角色
            checkedRoleIds() {
                let ids = [];
                this.roleMenuList.forEach(menuRole => {
                    if (menuRole.menuId !== this.menuId) return true;
                    ids.push(menuRole.roleId);
                });
                return ids;
            }
        },

        methods: {
            handleDblClickTreeAsyncNode({node, data}) {
                if (node.expanded) {
                    node.collapse();
                } else {
                    node.expand();
                }
            },

            // 路由

            getTreeAsyncRows() {
                this.menuRouteList = [];
                const Tree = this.$refs[this.treeAsyncRef];
                if (!Tree || !this.moduleId || !this.subModuleId) {
                    return;
                }
                //清空
                this.treeAsyncLoading = true;
                new Promise((resolve, reject) => {
                    this.$Ajax.get(ApiGarMenuRouteUrls.get.listMenuRoute, [], {
                        params: {moduleId: this.moduleId}
                    })
                        .success(true, data => {
                            this.menuRouteList = data.rows;
                            resolve();
                        })
                        .notSuccess(reject)
                        .catch(reject);
                }).then(() => {
                    this.$Ajax
                        .get(ApiGarRouteUrls.get.listRootRouteBySubModuleId, [this.subModuleId], {
                            params: merge({moduleId: this.moduleId}, this.params)
                        })
                        .success(true, data => {
                            this.treeAsyncSkeleton = false;
                            //由于骨架屏原因会导致tree俩次初始化,这里如果不设置异步,那么tree第二次初始化的时候找不到data属性
                            //所以要先关闭骨架屏,异步给data设置值
                            this.$nextTick(() => {
                                this.treeAsyncRows = this.formatterTreeAsyncRows(data.rows, {
                                    formatterRow: row => {
                                        row.expanded = false;
                                        row.dataType = 'Route';
                                    },
                                    //TODO 写你的格式化叶子节点逻辑
                                    formatterNodeIsLeaf: row => row.childCount === 0
                                });
                            });
                        })
                        .notSuccess(() => this.treeAsyncSkeleton = 500)
                        .catch(() => this.treeAsyncSkeleton = 500)
                        .finally(() => this.treeAsyncLoading = false);
                }).catch(() => {
                    this.treeAsyncSkeleton = 500;
                    this.treeAsyncLoading = false;
                });
            },
            getTreeAsyncNodes(node, resolve) {
                if (!this.moduleId) {
                    resolve([]);
                    return;
                }
                let params = {
                    moduleId: this.moduleId
                };
                if (this.queryChildParams) {
                    merge(params, this.queryChildParams);
                }
                //加载子节点,level代表树层级数
                if (node.level >= 1) {
                    this.$Ajax.get(ApiGarRouteUrls.get.listRouteByParentId, [node.data.id], {
                        params: params
                    })
                        .success(true, data => {
                            //记录为展开过状态
                            node.data.expanded = true;
                            const rows = this.formatterTreeAsyncRows(data.rows, {
                                formatterRow: row => {
                                    row.expanded = false;
                                    row.dataType = 'Route';
                                },
                                //TODO 写你的格式化叶子节点逻辑
                                formatterNodeIsLeaf: row => row.childCount === 0
                            });
                            resolve(rows);
                        });
                }
            },
            handleCheckedRoute({node, data}) {
                if (!this.moduleId || !this.menuId) {
                    return;
                }
                this.treeAsyncLoading = true;
                this.$Ajax.post(ApiGarMenuRouteUrls.post.menuRouteByMenuIdAndRouteId, {
                    moduleId: this.moduleId,
                    menuId: this.menuId,
                    routeId: data.id,
                    relation: this.dict.menuRouteRelationValue.MENU_SELECTED
                })
                    .success(true, data => {
                        this.menuRouteList.push(data.records.menuRoute);
                    })
                    .finally(() => this.treeAsyncLoading = false)
            },
            handleUnCheckedRoute({node, data}) {
                if (!this.moduleId || !this.menuId) {
                    return;
                }
                this.treeAsyncLoading = true;
                this.$Ajax.delete(ApiGarMenuRouteUrls.delete.menuRouteByMenuIdAndRouteIdAndMenuRouteRelation, {
                    moduleId: this.moduleId,
                    menuId: this.menuId,
                    routeId: data.id,
                    menuRouteRelation: this.dict.menuRouteRelationValue.MENU_SELECTED
                })
                    .success(true, () => {
                        for (let i = 0; i < this.menuRouteList.length; i++) {
                            if (this.menuRouteList[i].menuId === this.menuId
                                && this.menuRouteList[i].routeId === data.id
                                && this.menuRouteList[i].relation === this.dict.menuRouteRelationValue.MENU_SELECTED) {
                                this.menuRouteList.splice(i, 1);
                                break;
                            }
                        }
                    })
                    .finally(() => this.treeAsyncLoading = false)
            },
            handleAddClickMenuToRoute({node, data}) {
                if (!this.moduleId || !this.menuId) {
                    return;
                }
                this.clickToRouteLoading = true;
                this.$Ajax.post(ApiGarMenuRouteUrls.post.menuRouteByMenuIdAndRouteId, {
                    moduleId: this.moduleId,
                    menuId: this.menuId,
                    routeId: data.id,
                    relation: this.dict.menuRouteRelationValue.MENU_CLICK_TO_ROUTE
                })
                    .success(true, res => {
                        for (let i = 0; i < this.menuRouteList.length; i++) {
                            if (this.menuRouteList[i].menuId === this.menuId
                                && this.menuRouteList[i].relation === this.dict.menuRouteRelationValue.MENU_CLICK_TO_ROUTE) {
                                this.menuRouteList.splice(i, 1);
                                break;
                            }
                        }
                        this.menuRouteList.unshift(res.records.menuRoute);
                    })
                    .finally(() => this.clickToRouteLoading = false)
            },
            handleDeleteClickMenuToRoute({node, data}) {
                if (!this.moduleId || !this.menuId) {
                    return;
                }
                this.clickToRouteLoading = true;
                this.$Ajax.post(ApiGarMenuRouteUrls.delete.menuRouteByMenuIdAndRouteIdAndMenuRouteRelation, {
                    moduleId: this.moduleId,
                    menuId: this.menuId,
                    routeId: data.id,
                    menuRouteRelation: this.dict.menuRouteRelationValue.MENU_CLICK_TO_ROUTE
                })
                    .success(true, () => {
                        for (let i = 0; i < this.menuRouteList.length; i++) {
                            if (this.menuRouteList[i].menuId === this.menuId
                                && this.menuRouteList[i].routeId === data.id
                                && this.menuRouteList[i].relation === this.dict.menuRouteRelationValue.MENU_CLICK_TO_ROUTE) {
                                this.menuRouteList.splice(i, 1);
                                break;
                            }
                        }
                    })
                    .finally(() => this.clickToRouteLoading = false);
            },

            // 角色

            getListRows() {
                this.roleMenuList = [];
                if (!this.moduleId) {
                    return;
                }
                this.listLoading = true;
                new Promise((resolve, reject) => {
                    this.$Ajax.get(ApiGarRoleMenuUrls.get.listRoleMenu, [], {
                        params: {moduleId: this.moduleId}
                    })
                        .success(true, data => {
                            this.roleMenuList = data.rows;
                            resolve();
                        })
                        .notSuccess(reject)
                        .catch(reject);
                }).then(() => {
                    this.$Ajax.get(ApiGarRoleUrls.get.listRole, merge({moduleId: this.moduleId}))
                        .success(true, data => {
                            this.listRows = this.formatterListRows(data.rows);
                            this.listSkeleton = false;
                        })
                        .notSuccess(() => this.listSkeleton = 500)
                        .catch(() => this.listSkeleton = 500)
                        .finally(() => this.listLoading = false);
                }).catch(() => {
                    this.listLoading = false;
                    this.listSkeleton = 500;
                });
            },
            handleCheckedRole({row, $index}) {
                if (!this.moduleId || !this.menuId) {
                    return;
                }
                this.listLoading = true;
                this.$Ajax.post(ApiGarRoleMenuUrls.post.roleMenuByRoleIdAndMenuId, {
                    moduleId: this.moduleId,
                    roleId: row.id,
                    menuId: this.menuId
                })
                    .success(true, data => {
                        this.roleMenuList.push(data.records.roleMenu);
                    })
                    .finally(() => this.listLoading = false);
            },
            handleUnCheckedRole({row, $index}) {
                if (!this.moduleId || !this.menuId) {
                    return;
                }
                this.listLoading = true;
                this.$Ajax.delete(ApiGarRoleMenuUrls.delete.roleMenuByRoleIdAndMenuId, {
                    moduleId: this.moduleId,
                    roleId: row.id,
                    menuId: this.menuId
                })
                    .success(true, data => {
                        for (let i = 0; i < this.roleMenuList.length; i++) {
                            if (this.roleMenuList[i].menuId === this.menuId && this.roleMenuList[i].roleId === row.id) {
                                this.roleMenuList.splice(i, 1);
                                break;
                            }
                        }
                    })
                    .finally(() => this.listLoading = false);
            }
        },
        mounted() {
            this.getTreeAsyncRows();
            this.getListRows();
        }
    }
</script>
