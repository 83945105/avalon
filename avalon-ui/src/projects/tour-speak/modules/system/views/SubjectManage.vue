<template>
    <div class="common-bg-default">
        <h1 class="tour-layout-right-title">学科管理</h1>
        <!--查询-->
        <div class="tour-search-bar">
            <!--查询条件-->
            <ul class="tour-search-bar-list">
                <li>
                    <em class="list-left list-label-4">学科：</em>
                    <div class="list-right">
                        <el-input v-model="params.subject" size="small" clearable
                                  style="width: 200px"
                                  placeholder="请输入学科"></el-input>
                    </div>
                </li>
                <li class="spacing">
                    <we-button :loading="loading" size="small" type="primary"
                               @click="reGetRows">查询
                    </we-button>
                </li>
            </ul>
            <div class="common-float-right">
                <we-button type="success">同步</we-button>
            </div>
            <div class="common-clear"></div>
            <!--/查询条件-->
        </div>
        <!--/查询-->
        <!--列表-->
        <div style="height: 600px">
            <we-skeleton v-model="skeleton" animation :paragraph-rows="10"
                         :title-width="'50%'" :paragraph-width="'100%'"
                         @reload="getRows">
                <div v-loading="!skeleton && loading" class="common-padding-lr">
                    <el-table :data="rows"
                              style="width: 100%"
                              border
                              height="600px">
                        <template slot="empty">
                            <error-page :image-width="180" :image-height="180"></error-page>
                        </template>
                        <el-table-column prop="subject"
                                         label="学科"
                                         show-overflow-tooltip>
                        </el-table-column>
                        <el-table-column prop="period"
                                         label="学段"
                                         show-overflow-tooltip>
                        </el-table-column>
                    </el-table>
                </div>
            </we-skeleton>
        </div>
        <!--/列表-->
        <!--分页-->
        <div class="tour-paging-main tour-paging-size-small common-tac">
            <el-pagination layout="total, sizes, prev, pager, next, jumper"
                           :current-page="limit.currentPage"
                           :page-size="limit.pageSize"
                           :total="limit.total"
                           :page-count="limit.pageCount"
                           :page-sizes="[10, 20, 50]"
                           @current-change="v => {limit.currentPage = v;getRows();}"
                           @size-change="v => {limit.pageSize = v;getRows();}"
            >
            </el-pagination>
        </div>
        <!--/分页-->
    </div>
</template>

<script>
    import Global from '../../../mixins/global.js';
    import ErrorPage from "../../../../../components/error-page/src/ErrorPage.vue";
    import Link from '../../../mixins/link.js';
    import merge from "../../../../../utils/merge";
    import SchoolTypeSelect from "../components/select/src/SchoolTypeSelect";

    const Limit = {
        currentPage: 1,
        pageSizes: [10, 20, 50, 100],
        total: 0,
        pageCount: 1
    };

    const Params = {
        subject: ""
    };

    export default {
        name: "subject-manage",

        components: {SchoolTypeSelect, ErrorPage},

        mixins: [Global, Link],

        data() {
            return {
                skeleton: true,
                loading: false,
                limit: merge({pageSize: 20}, Limit),
                params: merge({}, Params),
                rows: []
            }
        },

        methods: {
            reGetRows() {
                // merge(this.limit, Limit);
                this.getRows();
            },
            formatterRows(rows) {
                rows.forEach(row => {
                    row.deleteLoading = false;
                });
                return rows;
            },
            getRows() {
                this.loading = true;
                setTimeout(() => {
                    this.rows = this.formatterRows([{
                        subject: '语文',
                        period: '初中'
                    }]);
                    this.skeleton = false;
                    this.loading = false;
                }, 500);
            },
            handleCancelRow(e, vm, {row}) {
                row.deleteLoading = true;
                setTimeout(() => {
                    vm.close();
                    row.deleteLoading = false;
                }, 500);
            }
        },

        created() {
            this.getRows();
        }
    }
</script>