<template>
    <div class="common-bg-default">
        <h1 class="tour-layout-right-title">教师管理</h1>
        <!--查询-->
        <div class="tour-search-bar">
            <!--查询条件-->
            <ul class="tour-search-bar-list">
                <li>
                    <em class="list-left list-label-4">用户姓名：</em>
                    <div class="list-right">
                        <el-input v-model="params.likeUsername" size="small" clearable
                                  style="width: 200px"
                                  placeholder="请输入用户姓名"></el-input>
                    </div>
                </li>
                <li>
                    <em class="list-left list-label-4">所在学校：</em>
                    <div class="list-right">
                        <el-input v-model="params.likeSchoolName" size="small" clearable
                                  style="width: 200px"
                                  placeholder="请输入所在学校"></el-input>
                    </div>
                </li>
                <li class="spacing">
                    <we-button :loading="tableLoading" size="small" type="primary"
                               @click="reGetRows">查询
                    </we-button>
                </li>
            </ul>
            <!--<div class="common-float-right">
                <we-button type="success">同步</we-button>
            </div>-->
            <div class="common-clear"></div>
            <!--/查询条件-->
        </div>
        <!--/查询-->
        <!--列表-->
        <div style="height: 600px">
            <we-skeleton v-model="tableSkeleton" animation :paragraph-rows="10"
                         :title-width="'50%'" :paragraph-width="'100%'"
                         @reload="getRows">
                <div v-loading="!tableSkeleton && tableLoading" class="common-padding-lr">
                    <el-table :data="tableRows"
                              style="width: 100%"
                              border
                              height="600px">
                        <template slot="empty">
                            <error-page :image-width="180" :image-height="180"></error-page>
                        </template>
                        <el-table-column prop="loginname"
                                         label="登录名称"
                                         show-overflow-tooltip>
                        </el-table-column>
                        <el-table-column prop="realname"
                                         label="用户姓名"
                                         show-overflow-tooltip
                                         width="200">
                        </el-table-column>
                        <el-table-column prop="phone"
                                         label="手机号"
                                         show-overflow-tooltip
                                         width="130">
                        </el-table-column>
                        <el-table-column prop="userRole"
                                         label="用户角色"
                                         show-overflow-tooltip
                                         width="130">
                        </el-table-column>
                        <el-table-column prop="schoolName"
                                         label="所在学校"
                                         show-overflow-tooltip
                                         width="240">
                        </el-table-column>
                        <el-table-column prop=""
                                         label="课程权限"
                                         show-overflow-tooltip
                                         width="90">
                            <template slot-scope="scope">
                                <el-checkbox v-model="scope.row.courseTeacher"
                                             @change="val => handleChangeCourseTeacher(val, scope)"></el-checkbox>
                            </template>
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
    import PubUserUrls from "../../../urls/pub-user-urls.js";
    import FamTeacherUrls from "../../../urls/fam-teacher-urls.js";

    const Limit = {
        currentPage: 1,
        pageSize: 20,
        total: 0,
        pageCount: 1
    };

    export default {

        name: "teacher-manage",

        components: {ErrorPage},

        mixins: [Global, Link, Table],

        data() {
            return {
                limit: merge({}, Limit),
                params: {
                    likeUsername: '',
                    likeSchoolName: ''
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
                this.$Ajax.get(PubUserUrls.get.pageListPubUser, merge({}, this.params, this.limit))
                    .success(true, data => {
                        this.tableSkeleton = false;
                        this.tableRows = data.rows;
                        merge(this.limit, data.limit);
                    })
                    .notSuccess(() => this.tableSkeleton = 500)
                    .catch(() => this.tableSkeleton = 500)
                    .finally(() => this.tableLoading = false);
            },
            handleChangeCourseTeacher(val, {row, $index}) {
                if (val) {
                    this.$Ajax.post(FamTeacherUrls.post.famTeacherByPubUserId, {
                        pubUserId: row.id
                    })
                        .success(true)
                        .catch(() => row.courseTeacher = false)
                        .notSuccess(() => row.courseTeacher = false);
                } else {
                    this.$Ajax.delete(FamTeacherUrls.delete.famTeacherByFamTeacherId, {
                        famTeacherId: row.id
                    }).success(true)
                        .catch(() => row.courseTeacher = true)
                        .notSuccess(() => row.courseTeacher = true);
                }
            }
        },

        created() {
            this.getRows();
        }
    }
</script>