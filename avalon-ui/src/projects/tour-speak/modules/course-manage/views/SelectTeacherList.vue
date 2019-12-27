<template>
    <div>
        <!--查询-->
        <div class="tour-search-bar">
            <!--查询条件-->
            <ul class="tour-search-bar-list">
                <li>
                    <em class="list-left list-label-4">关键字：</em>
                    <div class="list-right">
                        <el-input v-model="params.likeText" size="small" clearable
                                  style="width: 200px"
                                  placeholder="请输入关键字"></el-input>
                    </div>
                </li>
                <li class="spacing">
                    <we-button :loading="tableLoading" size="small" type="primary"
                               @click="reGetRows">查询
                    </we-button>
                </li>
            </ul>
            <div class="common-clear"></div>
            <!--/查询条件-->
        </div>
        <!--/查询-->
        <!--列表-->
        <div style="height: 400px;">
            <we-skeleton v-model="tableSkeleton" animation :paragraph-rows="10"
                         :title-width="'50%'" :paragraph-width="'100%'"
                         @reload="getRows">
                <div v-loading="!tableSkeleton && tableLoading" class="common-padding-lr">
                    <el-table :data="tableRows"
                              style="width: 100%"
                              border
                              height="400px">
                        <template slot="empty">
                            <error-page v-show="!tableLoading" :image-width="180" :image-height="180"></error-page>
                        </template>
                        <el-table-column prop="name"
                                         label="教师姓名"
                                         show-overflow-tooltip>
                        </el-table-column>
                        <el-table-column prop="schooname"
                                         label="所在学校"
                                         show-overflow-tooltip>
                        </el-table-column>
                        <el-table-column prop="operation"
                                         fixed="right"
                                         label="操作"
                                         width="100">
                            <template slot-scope="scope">
                                <we-button type="text" size="small" @click="handleSelectTeacher(scope)">选择</we-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </div>
            </we-skeleton>
        </div>
        <!--/列表-->
        <!--分页-->
        <div class="tour-paging-main tour-paging-size-mini common-tac">
            <el-pagination layout="total, sizes, prev, pager, next, jumper"
                           :current-page="limit.currentPage"
                           :page-size="limit.pageSize"
                           :total="limit.total"
                           :page-count="limit.pageCount"
                           :page-sizes="[10, 20, 50, 100]"
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
    import Table from '../../../../../assets/template/mixins/table.js';
    import ErrorPage from "../../../../../components/error-page/src/ErrorPage.vue";
    import Link from '../../../mixins/link.js';
    import merge from "../../../../../utils/merge.js";
    import FamTeacherUrls from "../../../urls/fam-teacher-urls.js";

    const Limit = {
        currentPage: 1,
        pageSize: 20,
        total: 0,
        pageCount: 1
    };

    export default {

        name: "select-teacher-list",

        components: {ErrorPage},

        mixins: [Global, Link, Table],

        data() {
            return {
                limit: merge({}, Limit),
                params: {
                    likeText: ''
                }
            }
        },

        methods: {
            reGetRows() {
                merge(this.limit, Limit);
                this.getRows();
            },
            getRows() {
                this.tableLoading = true;
                this.$Ajax.get(FamTeacherUrls.get.pageListFamTeacher, merge({}, this.params, this.limit))
                    .success(true, data => {
                        this.tableSkeleton = false;
                        this.tableRows = data.rows;
                        merge(this.limit, data.limit);
                    })
                    .notSuccess(() => this.tableSkeleton = 500)
                    .catch(() => this.tableSkeleton = 500)
                    .finally(() => this.tableLoading = false);
            },
            handleSelectTeacher({row, $index}) {
                this.$emit('select', {row, $index});
            }
        },

        created() {
            this.getRows();
        }
    }
</script>