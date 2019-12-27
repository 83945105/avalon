<template>
    <div>
        <!--待分配角色-->
        <div class="gar-layout-tree-top gar-module-panel gar-module-type-default" style="height: 160px">
            <div class="gar-module-panel" style="height: 100%">
                <div class="module-panel-title">
                    <h3 class="module-panel-title-left">待分配角色</h3>
                    <div class="module-panel-title-right is-margin">
                        <we-button size="mini" icon-name="refresh-o" @click="reloadRoles"></we-button>
                    </div>
                </div>
                <we-skeleton v-model="roleSkeleton"
                             animation
                             :paragraph-rows="2"
                             @reload="reloadRoles">
                    <div v-loading="!roleSkeleton && roleLoading"
                         class="module-panel-content common-padding-tb-small common-padding-lr-mini">
                        <ul class="gar-list-module-ul">
                            <li v-for="(row, index) in allocatedRoles">
                                <we-button :disabled="row.status !== dict.statusValue.NORMAL" type="primary" size="mini"
                                           class="common-w-all common-ellipsis">{{row.name}}
                                </we-button>
                            </li>
                        </ul>
                    </div>
                </we-skeleton>
            </div>
        </div>
        <!--待分配角色-->
        <!--分割线-->
        <div class="gar-layout-tree-resize" style="top: 160px;">
            <div class="resize-button"></div>
        </div>
        <!--/分割线-->
        <!--关联模板-->
        <div class="gar-layout-tree-bottom gar-module-panel gar-module-type-default" style="top: 164px">
            <div class="module-panel-title">
                <h3 class="module-panel-title-left">关联模板</h3>
                <div class="module-panel-title-right is-margin">
                    <we-button size="mini" type="primary"
                               @click="routerToRouteTreeTemplate({moduleId: moduleId, routeViewId: routeViewId})">新增关联模版
                    </we-button>
                    <we-button size="mini" icon-name="refresh-o" @click="reload"></we-button>
                </div>
            </div>
            <we-skeleton v-model="listSkeleton" animation :paragraph-rows="10"
                         :title-width="'50%'" :paragraph-width="'100%'"
                         @reload="reload"
            >
                <div v-loading="!listSkeleton && listLoading"
                     class="module-panel-content common-padding-all is-loading">
                    <error-page v-if="listRows.length === 0" title="未找到关联模板"></error-page>
                    <div v-else class="gar-list-module-one" v-for="(row, index) in listRows">
                        <div class="module-one-top">
                            <h3 class="module-one-top-left">{{row.templateName}}</h3>
                            <div class="module-one-top-right">
                                <we-button type="text" size="small"
                                           @click="handleShoAddRoles({row, $index: index})">
                                    分配角色
                                </we-button>
                                <we-popover title="操作" type="confirm"
                                            placement="top"
                                            :manual="row[listRowDeleteLoading$]"
                                            confirm-button-text="删了吧"
                                            :cancel-button-text="row[listRowDeleteLoading$] ? '请稍等' : '我再想想'"
                                            :cancel-button-options="{disabled: row[listRowDeleteLoading$]}"
                                            :confirm-button-options="{loading: row[listRowDeleteLoading$]}"
                                            @click-cancel-button="(e, vm) => vm.close()"
                                            @click-confirm-button="(e, vm) => handleClickRemoveRow({row, $index:index, event:e, vm})">
                                    <template slot="content">
                                        <template v-if="row[listRowDeleteLoading$]">
                                            <span style="color: red">移除中...</span>移除完成后将自动关闭气泡
                                        </template>
                                        <template v-else>
                                            您确定要<span style="color: red">移除</span>该<span style="color: red">关联模板</span>吗？
                                        </template>
                                    </template>
                                    <we-button type="text" size="small">移除关联</we-button>
                                </we-popover>
                            </div>
                            <div class="common-clear"></div>
                        </div>
                        <div class="module-one-left">
                            <ul class="gar-form-view label-width-7 size-mini">
                                <li>
                                    <em class="view-left">模板唯一标识符：</em>
                                    <div class="view-right">{{row.templateValue}}</div>
                                </li>
                                <li>
                                    <em class="view-left">模板类型：</em>
                                    <div class="view-right">{{dict.templateTypeLabel[row.templateType]}}</div>
                                </li>
                                <li>
                                    <em class="view-left">模板描述：</em>
                                    <div class="view-right">
                                        {{row.templateDescription}}
                                    </div>
                                </li>
                            </ul>
                            <div class="common-clear"></div>
                        </div>
                        <div class="module-one-right">
                            <h3 class="module-one-right-title common-float-left">
                                <div>授予的角色</div>
                            </h3>
                            <div class="module-one-right-content">
                                <template v-if="!row.roles || row.roles.length === 0">
                                    <error-page :image-width="0" :image-height="0" title="暂无角色，请添加角色。"></error-page>
                                </template>
                                <ul v-else class="gar-list-button-ul">
                                    <li v-for="(role, roleIndex) in row.roles">
                                        <div class="button-ul-left">{{role.name}}</div>
                                        <template v-if="role.id === 'gar_developer'">
                                            <div class="button-ul-right" style="background-color: #a1bdae">
                                                <we-icon name="trash"></we-icon>
                                            </div>
                                        </template>
                                        <template v-else>
                                            <we-popover title="操作" type="confirm"
                                                        placement="right"
                                                        :manual="role[listRowDeleteLoading$]"
                                                        confirm-button-text="删了吧"
                                                        :cancel-button-text="role[listRowDeleteLoading$] ? '请稍等' : '我再想想'"
                                                        :cancel-button-options="{disabled: role[listRowDeleteLoading$]}"
                                                        :confirm-button-options="{loading: role[listRowDeleteLoading$]}"
                                                        @click-cancel-button="(e, vm) => vm.close()"
                                                        @click-confirm-button="(e, vm) => handleClickRemoveRole({row, $index:index, event:e, vm, role, roleIndex})">
                                                <template slot="content">
                                                    <template v-if="role[listRowDeleteLoading$]">
                                                        <span style="color: red">移除中...</span>移除完成后将自动关闭气泡
                                                    </template>
                                                    <template v-else>
                                                        您确定要<span style="color: red">取消分配</span>该<span
                                                            style="color: red">角色</span>吗？
                                                    </template>
                                                </template>
                                                <div class="button-ul-right">
                                                    <we-icon name="trash"></we-icon>
                                                </div>
                                            </we-popover>
                                        </template>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </we-skeleton>
        </div>
        <div class="gar-paging-infinite is-absolute common-tac">
            <div class="gar-paging-infinite-inner size-height-small size-small">
                <we-loading :value="moreLoading" size="mini"></we-loading>
                <span v-if="limit.currentPage * limit.pageSize > limit.total" class="common-fc-text-3">没有更多了</span>
                <we-button v-else size="small" type="text" @click="limit.currentPage++;loadMore()">加载更多...
                </we-button>
            </div>
        </div>
        <!--/关联模板-->

        <!--分配角色-->
        <we-layer v-model="showAddRoleLayer"
                  title="分配角色"
                  width="700"
                  height="450"
                  drag
                  :maskClosable="false"
                  :cancel-button-options="{disabled: addRoleLoading}"
                  :confirm-button-options="{loading: addRoleLoading, disabled: checkedRoles.length === 0}"
                  @click-confirm-button="handleAddCheckedRoles"
        >
            <div class="common-padding-all" style="padding-bottom: 0;">
                <template v-if="allocatedCanUseRoles.length === 0">
                    <error-page title="暂无角色可以授予"></error-page>
                </template>
                <div v-else class="gar-layer-content is-scroll-v" style="bottom: 52px;">
                    <div class="common-padding-all">
                        <div v-show="allocatedCanUseRoles.length > 1" style="margin: 0 1%">
                            <el-checkbox v-model="checkAll" :indeterminate="isIndeterminate"
                                         @change="handleCheckAllChange">全选
                            </el-checkbox>
                        </div>
                        <el-checkbox-group v-model="checkedRoles" @change="handleCheckedRoles">
                            <ul class="gar-list-average is-col-5">
                                <li v-for="(row, index) in allocatedCanUseRoles">
                                    <el-checkbox :label="row" :key="row.id" border size="small"
                                                 class="common-w-all">{{row.name}}
                                    </el-checkbox>
                                </li>
                            </ul>
                        </el-checkbox-group>
                    </div>
                </div>
            </div>
        </we-layer>
        <!--/分配角色-->
    </div>
</template>

<script>

    import Global from '../../../../mixins/global.js';
    import Link from '../../../../mixins/link.js';
    import List from '../../../../../../assets/template/mixins/list.js';
    import ErrorPage from "../../../../../../components/error-page/src/ErrorPage.vue";
    import merge from "../../../../../../utils/merge.js";
    import ApiGarRouteViewTemplateUrls from "../../urls/api-gar-route-view-template-urls.js";
    import ApiGarRoleUrls from "../../urls/api-gar-role-urls.js";
    import ApiGarRoleRouteViewTemplateUrls from "../../urls/api-gar-role-route-view-template-urls.js";

    const Limit = {
        currentPage: 1,
        pageSizes: [10, 20, 50, 100],
        total: 0,
        pageCount: 1
    };

    export default {

        name: "route-role-template",

        componentName: "RouteRoleTemplate",

        components: {ErrorPage},

        mixins: [Global, Link, List],

        props: {
            moduleId: {
                type: String,
                default: ''
            },
            routeViewId: {
                type: String,
                default: ''
            }
        },

        data() {
            return {
                roleSkeleton: true,
                roleLoading: false,
                roleList: [],
                moreLoading: false,

                limit: merge({pageSize: 20}, Limit),

                addRoleLoading: false,
                showAddRoleLayer: false,

                checkAll: false,
                isIndeterminate: false,
                checkedRoles: []
            }
        },

        computed: {
            // 待分配角色
            allocatedRoles() {
                return this.roleList;
            },
            // 待分配可用角色
            allocatedCanUseRoles() {
                let roles = [];
                this.roleList.forEach(role => {
                    if (role.status !== this.dict.statusValue.NORMAL) return true;
                    roles.push(role);
                });
                return roles;
            }
        },

        watch: {
            routeViewId(val) {
                this.reload();
                this.reloadRoles();
            }
        },

        methods: {
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            reloadRoles() {
                this.roleList = [];
                if (!this.moduleId) {
                    return;
                }
                this.roleLoading = true;
                Promise.all([new Promise((resolve, reject) => {
                    this.$Ajax.get(ApiGarRoleUrls.get.listRole, {
                        moduleId: this.moduleId
                    })
                        .success(true, data => resolve(data.rows))
                        .notSuccess(reject)
                        .catch(reject);
                }), new Promise((resolve, reject) => {
                    this.$Ajax.get(ApiGarRoleRouteViewTemplateUrls.get.roleByRouteViewId, [this.routeViewId], {
                        params: {moduleId: this.moduleId}
                    })
                        .success(true, data => resolve(data.rows))
                        .notSuccess(reject)
                        .catch(reject);
                })]).then(([roles, usedRoles]) => {
                    this.roleSkeleton = false;
                    this.roleLoading = false;

                    let roleList = [];
                    roles.forEach(role => {
                        for (let i in usedRoles) {
                            if (role.id === usedRoles[i].id) return true;
                        }
                        roleList.push(role);
                    });
                    this.roleList = roleList;
                }).catch(err => {
                    this.roleSkeleton = 500;
                    this.roleLoading = false;
                });
            },
            reload() {
                merge(this.limit, Limit);
                this.listRows = [];
                if (!this.routeViewId) {
                    return;
                }
                this.listLoading = true;
                this.$Ajax.get(ApiGarRouteViewTemplateUrls.get.pageListRouteViewTemplateByRouteViewId, [this.routeViewId], {
                    params: {
                        moduleId: this.moduleId,
                        currentPage: this.limit.currentPage,
                        pageSize: this.limit.pageSize
                    }
                })
                    .success(true, data => {
                        this.listSkeleton = false;
                        merge(this.limit, data.limit);
                        this.listRows = this.formatterListRows(data.rows, {
                            formatterRow: row => {
                                if (row.roles) {
                                    // 给每个角色初始化一个删除特效属性
                                    row.roles.forEach(role => {
                                        role[this.listRowDeleteLoading$] = false;
                                        role.status = this.dict.statusValue.NORMAL;
                                    });
                                }
                            }
                        });
                    })
                    .notSuccess(() => this.listSkeleton = 500)
                    .catch(() => this.listSkeleton = 500)
                    .finally(() => this.listLoading = false);
            },
            loadMore() {
                if (!this.moduleId) {
                    return;
                }
                this.moreLoading = true;
                this.$Ajax.get(ApiGarRouteViewTemplateUrls.get.pageListRouteViewTemplateByRouteViewId, [this.routeViewId], {
                    params: {
                        moduleId: this.moduleId,
                        currentPage: this.limit.currentPage,
                        pageSize: this.limit.pageSize
                    }
                })
                    .success(true, data => {
                        merge(this.limit, data.limit);
                        this.appendListRows(data.rows);
                    })
                    .finally(() => this.moreLoading = false);
            },
            handleClickRemoveRole({row, $index, event, vm, role, roleIndex}) {
                if (!this.moduleId || !this.routeViewId) {
                    return;
                }
                role[this.listRowDeleteLoading$] = true;
                this.$Ajax.post(ApiGarRoleRouteViewTemplateUrls.delete.roleRouteViewTemplateByRoleIdAndRouteViewIdAndTemplateId, {
                    moduleId: this.moduleId,
                    roleId: role.id,
                    routeViewId: this.routeViewId,
                    templateId: row.templateId
                })
                    .success(true, () => {
                        row.roles.splice(roleIndex, 1);
                        this.roleList.push(role);
                        vm.close();
                    })
                    .finally(() => role[this.listRowDeleteLoading$] = false);
            },
            handleClickRemoveRow({row, $index, event, vm}) {
                if (!this.moduleId) {
                    return;
                }
                row[this.listRowDeleteLoading$] = true;
                this.$Ajax.delete(ApiGarRouteViewTemplateUrls.delete.routeViewTemplateByRouteViewTemplateId, {
                    moduleId: this.moduleId,
                    routeViewTemplateId: row.id
                })
                    .success(true, () => {
                        if (row.roles) {
                            this.roleList.push(...row.roles);
                        }
                        this.listRows.splice($index, 1);
                        vm.close();
                    })
                    .finally(() => row[this.listRowDeleteLoading$] = false);
            },
            handleCheckAllChange(val) {
                this.checkedRoles = val ? this.allocatedCanUseRoles : [];
                this.isIndeterminate = false;
            },
            handleCheckedRoles(value) {
                let checkedCount = value.length;
                this.checkAll = checkedCount === this.allocatedCanUseRoles.length;
                this.isIndeterminate = checkedCount > 0 && checkedCount < this.allocatedCanUseRoles.length;
            },
            handleShoAddRoles({row, $index}) {
                this.checkAll = false;
                this.isIndeterminate = false;
                this.checkedRoles = [];
                this.listSelectedRow = row;
                this.listSelectedRowIndex = $index;
                this.showAddRoleLayer = true;
            },
            handleAddCheckedRoles(e, vm) {
                if (!this.moduleId || !this.routeViewId) {
                    return;
                }
                this.addRoleLoading = true;
                const roleIds = this.checkedRoles.map(val => val.id);
                this.$Ajax.post(ApiGarRoleRouteViewTemplateUrls.post.roleRouteViewTemplateByRoleIdsAndRouteViewIdAndTemplateId, {
                    moduleId: this.moduleId,
                    roleIds: roleIds.toString(),
                    routeViewId: this.routeViewId,
                    templateId: this.listSelectedRow.templateId
                })
                    .success('授予角色成功', () => {
                        if (!this.listSelectedRow.roles) {
                            this.$set(this.listSelectedRow, 'roles', []);
                        }
                        const roles = [];
                        this.checkedRoles.forEach(role => {
                            roles.push(merge({}, role));
                        });
                        this.listSelectedRow.roles.push(...roles);
                        for (let i = 0; i < this.roleList.length; i++) {
                            for (let j in roleIds) {
                                if (this.roleList[i].id === roleIds[j]) {
                                    this.roleList.splice(i, 1);
                                    i--;
                                    break;
                                }
                            }
                        }
                        this.showAddRoleLayer = false;
                    })
                    .finally(() => this.addRoleLoading = false);
            }
        },

        created() {
            this.reload();
            this.reloadRoles();
        }
    }
</script>
