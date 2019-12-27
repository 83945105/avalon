<template>
    <div>
        <div class="gar-title is-border">
            <h3 class="gar-title-left">关联模板【控制台】</h3>
            <div class="gar-title-right is-margin">
                <we-button :disabled="addTemplateIds.length === 0"
                           :loading="addLoading" type="success" size="mini"
                           @click="handleSave">保存
                </we-button>
                <we-button :disabled="addTemplateIds.length === 0"
                           :loading="addLoading" type="primary" size="mini"
                           @click="handleSaveAndBack">保存并返回
                </we-button>
                <we-button :disabled="addLoading" size="mini"
                           @click="routerToRouteTreeRoleTemplate({moduleId: moduleId, routeViewId: routeViewId})">
                    返回
                </we-button>
            </div>
            <div class="common-clear"></div>
        </div>
        <div class="gar-layout-custom" style="top: 41px;">
            <!--查询-->
            <div class="gar-search-bar gar-search-bar-size">
                <!--查询条件-->
                <ul class="gar-search-bar-list">
                    <li>
                        <em class="list-left">关键字：</em>
                        <div class="list-right common-w-large">
                            <el-input v-model="params.likeText" size="small"
                                      placeholder="请输入关键字" clearable></el-input>
                        </div>
                    </li>
                    <li>
                        <em class="list-left list-label-4">所属子模块：</em>
                        <div class="list-right">
                            <sub-module-multiple-select v-model="subModuleIdSet"
                                                        :module-id="moduleId"></sub-module-multiple-select>
                        </div>
                    </li>
                    <li>
                        <em class="list-left list-label-4">模板类型：</em>
                        <div class="list-right common-w">
                            <view-type-select v-model="viewType" show-all-option></view-type-select>
                        </div>
                    </li>
                    <li class="spacing">
                        <we-button :loading="loading" size="small" @click="$refs.table.reloadTableCellEditRows()">查询
                        </we-button>
                    </li>
                </ul>
                <div class="common-float-right">
                    <we-button size="small" type="success"
                               @click="$refs.table.showAdd()">新增模板
                    </we-button>
                </div>
                <div class="common-clear"></div>
                <!--/查询条件-->
            </div>
            <!--/查询-->

            <!--列表-->
            <div class="gar-list-main is-border is-paging-size-mini">
                <template-cell-edit-table ref="table"
                                          :module-id="moduleId"
                                          :reload-table-cell-edit="reload"
                                          @table-cell-edit-add-success="handleTableCellEditAddSuccess"
                                          @table-selection-change="handleTableSelectionChange"
                >
                    <template slot="table-column-before">
                        <el-table-column type="selection" width="30"></el-table-column>
                    </template>
                </template-cell-edit-table>
                <div class="gar-paging-infinite is-absolute common-tac">
                    <div class="gar-paging-infinite-inner gar-paging-infinite-inner-size-mini">
                        <we-loading :value="moreLoading" size="mini"></we-loading>
                        <span v-if="limit.currentPage * limit.pageSize > limit.total"
                              class="common-fc-text-3">没有更多了</span>
                        <div v-else style="margin-top: 5px;">
                            <we-button size="small" type="text" @click="limit.currentPage++;loadMore()">加载更多...
                            </we-button>
                        </div>
                    </div>
                </div>
            </div>
            <!--/列表-->
        </div>
    </div>
</template>

<script>

    import Link from '../../../../mixins/link.js';
    import ErrorPage from "../../../../../../components/error-page/src/ErrorPage.vue";
    import SubModuleMultipleSelect from "../../components/sub-module-select/src/SubModuleMultipleSelect.vue";
    import TemplateCellEditTable from "../template/TemplateCellEditTableA.vue";
    import ViewTypeSelect from "../../components/sub-module-select/src/ViewTypeSelect.vue";
    import ApiGarRouteViewTemplateUrls from "../../urls/api-gar-route-view-template-urls.js";
    import merge from "../../../../../../utils/merge.js";
    import ApiGarTemplateUrls from "../../urls/api-gar-template-urls.js";
    import {isFunction} from "../../../../../../utils/util.js";

    const Limit = {
        currentPage: 1,
        pageSizes: [10, 20, 50, 100],
        total: 0,
        pageCount: 1
    };

    const Params = {
        likeText: ''
    };

    export default {

        name: "route-tree-template",

        components: {ViewTypeSelect, SubModuleMultipleSelect, TemplateCellEditTable, ErrorPage},

        mixins: [Link],

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
                subModuleIdSet: [],
                viewType: '',
                limit: merge({pageSize: 20}, Limit),
                params: merge({}, Params),

                loading: false,
                moreLoading: false,

                routeViewTemplates: [],

                addLoading: false,

                tableSelectionData: []
            };
        },

        computed: {
            ownedTemplateIds() {
                return this.routeViewTemplates.map(val => val.templateId);
            },
            addTemplateIds() {
                return this.tableSelectionData.map(val => val.id);
            }
        },

        watch: {
            routeViewId(val) {
                this.getRows();
            }
        },

        methods: {
            handleTableSelectionChange(selection, vm) {
                this.tableSelectionData = selection;
            },
            getRows() {
                this.routeViewTemplates = [];
                const Table = this.$refs.table;
                if (!Table || !this.moduleId || !this.routeViewId) {
                    return;
                }
                this.$Ajax.get(ApiGarRouteViewTemplateUrls.get.listRouteViewTemplateByRouteViewId, [this.routeViewId], {
                    params: {moduleId: this.moduleId}
                })
                    .success(true, data => {
                        this.routeViewTemplates = data.rows;
                        Table.reloadTableCellEditRows();
                    });
            },
            reload(resolve, reject) {
                merge(this.limit, Limit);
                if (!this.moduleId) {
                    resolve([]);
                    return;
                }
                let params = merge({moduleId: this.moduleId}, this.limit, this.params);
                if (this.subModuleIdSet.length > 0) {
                    params.subModuleIdSet = this.subModuleIdSet.toString();
                }
                if (this.viewType) {
                    params.viewType = this.viewType;
                }
                if (this.ownedTemplateIds.length > 0) {
                    params.notInIdSet = this.ownedTemplateIds.toString();
                }
                this.loading = true;
                this.$Ajax
                    .get(ApiGarTemplateUrls.get.pageListTemplate, params)
                    .success(true, data => {
                        merge(this.limit, data.limit);
                        resolve(data.rows);
                    })
                    .notSuccess(reject)
                    .catch(reject)
                    .finally(() => this.loading = false);
            },
            loadMore() {
                if (!this.moduleId) {
                    return;
                }
                let params = merge({moduleId: this.moduleId}, this.limit, this.params);
                if (this.subModuleIdSet.length > 0) {
                    params.subModuleIdSet = this.subModuleIdSet.toString();
                }
                if (this.viewType) {
                    params.viewType = this.viewType;
                }
                if (this.ownedTemplateIds.length > 0) {
                    params.notInIdSet = this.ownedTemplateIds.toString();
                }
                this.moreLoading = true;
                this.$Ajax
                    .get(ApiGarTemplateUrls.get.pageListTemplate, params)
                    .success(true, data => {
                        merge(this.limit, data.limit);
                        this.$refs.table.appendRows(data.rows);
                    })
                    .finally(() => this.moreLoading = false);
            },
            handleSave(callback) {
                if (!this.moduleId || !this.routeViewId) {
                    return;
                }
                //新增
                this.addLoading = true;
                this.$Ajax.post(ApiGarRouteViewTemplateUrls.post.listRouteViewTemplateByRouteViewIdsAndTemplateIds, {
                    moduleId: this.moduleId,
                    routeViewIds: this.routeViewId,
                    templateIds: this.addTemplateIds.toString()
                })
                    .success(true, () => {
                        if (isFunction(callback)) {
                            callback();
                        } else {
                            // 重新查询拥有的模板
                            this.getRows();
                        }
                    })
                    .finally(() => this.addLoading = false);
            },
            handleSaveAndBack() {
                this.handleSave(() => this.routerToRouteTreeRoleTemplate({
                    moduleId: this.moduleId,
                    routeViewId: this.routeViewId
                }));
            },
            handleTableCellEditAddSuccess({row, vm}) {
                if (this.limit.currentPage * this.limit.pageSize > this.limit.total) {
                    this.$refs.table.appendTableCellEditRow(row);
                }
            }
        },

        mounted() {
            const Table = this.$refs.table;
            if (!Table) return;
            this.getRows();
        }

    }
</script>
