<template>
    <div class="common-bg-default">
        <h1 class="tour-layout-right-title">课程中心</h1>
        <!--查询-->
        <div class="tour-search-bar">
            <!--查询条件-->
            <ul class="tour-search-bar-list">
                <li>
                    <em class="list-left list-label-4">课程状态：</em>
                    <div class="list-right common-w-small">
                        <course-status-select v-model="params.classstatus" showAllOption></course-status-select>
                    </div>
                </li>
                <li>
                    <em class="list-left list-label-4">报名时间：</em>
                    <div class="list-right">
                        <el-date-picker v-model="signTimeRange"
                                        type="datetimerange"
                                        value-format="yyyy-MM-dd HH:mm:ss"
                                        size="small"
                                        range-separator="至"
                                        start-placeholder="开始日期"
                                        end-placeholder="结束日期"
                                        style="width: 220px">
                        </el-date-picker>
                    </div>
                </li>
                <li>
                    <em class="list-left list-label-4">上课时间：</em>
                    <div class="list-right">
                        <el-date-picker v-model="classTimeRange"
                                        type="datetimerange"
                                        value-format="yyyy-MM-dd HH:mm:ss"
                                        size="small"
                                        range-separator="至"
                                        start-placeholder="开始日期"
                                        end-placeholder="结束日期"
                                        style="width: 220px">
                        </el-date-picker>
                    </div>
                </li>
                <li>
                    <em class="list-left list-label-4">关键字：</em>
                    <div class="list-right">
                        <el-input v-model="params.likeText" size="small" clearable
                                  style="width: 200px"
                                  placeholder="请输入教师姓名/课程信息"></el-input>
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
        <div :style="{'height': isEmpty(tableRows) ? '600px' : 'auto'}">
            <we-skeleton v-model="tableSkeleton" animation :paragraph-rows="10"
                         :title-width="'50%'" :paragraph-width="'100%'"
                         @reload="getRows">
                <div v-loading="!tableSkeleton && tableLoading" class="tour-img-main" style="min-height: 600px;">
                    <error-page v-if="isEmpty(tableRows) && !tableLoading" :image-width="180"
                                :image-height="180"></error-page>
                    <ul v-else class="tour-img-list">
                        <li v-for="(row, index) in tableRows"
                            @click="handleRouterToCourseDetails({row, $index: index})">
                            <div class="tour-img-inner">
                                <img v-if="isEmpty(row.filePath)"
                                     src="../../../../../../public/tour-speak/images/course_default_img.png"/>
                                <img v-else :src="row.filePath"/>
                                <div class="tour-img-inner-status">
                                <span v-if="row.realTimeClassStatus === dict.classStatusValue.NOT_START"
                                      class="tour-img-inner-status-fc-1">未开始</span>
                                    <span v-else-if="row.realTimeClassStatus === dict.classStatusValue.SIGNING"
                                          class="tour-img-inner-status-fc-2">报名中</span>
                                    <span v-else-if="row.realTimeClassStatus === dict.classStatusValue.SIGN_OVER"
                                          class="tour-img-inner-status-fc-3">报名结束</span>
                                    <span v-else-if="row.realTimeClassStatus === dict.classStatusValue.CLASSING"
                                          class="tour-img-inner-status-fc-4">上课中</span>
                                    <span v-else-if="row.realTimeClassStatus === dict.classStatusValue.CLASS_OVER"
                                          class="tour-img-inner-status-fc-5">已结束</span>
                                </div>
                            </div>
                            <div class="tour-img-inner-info">
                                <h3 class="tour-img-inner-info-title">{{row.title}}</h3>
                                <div class="tour-img-inner-info-one common-ellipsis">
                                    <span class="info-one-title">授课教师：</span>
                                    <em class="info-one-content">{{row.teachername}}</em>
                                </div>
                                <div class="tour-img-inner-info-one common-ellipsis">
                                    <span class="info-one-title">报名：</span>
                                    <span class="info-one-content">
                                    <em class="common-fc-success">{{row.signbegintime}}</em>&nbsp;-
                                    <em class="common-fc-danger">{{row.signendtime}}</em>
                                </span>
                                </div>
                                <div class="tour-img-inner-info-one common-ellipsis">
                                    <span class="info-one-title">上课：</span>
                                    <span class="info-one-content">
                                    <em class="common-fc-success">{{row.begintime}}</em>&nbsp;-
                                    <em class="common-fc-danger">{{row.endtime}}</em>
                                </span>
                                </div>
                            </div>
                        </li>
                    </ul>
                    <div class="common-clear"></div>
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
                           :page-sizes="[12, 20, 50, 100]"
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
    import Link from '../../../mixins/link.js';
    import ErrorPage from "../../../../../components/error-page/src/ErrorPage.vue";
    import CourseStatusSelect from "../components/select/src/CourseStatusSelect.vue";
    import merge from "../../../../../utils/merge.js";
    import FamClassUrls from "../../../urls/fam-class-urls.js";

    const Limit = {
        currentPage: 1,
        pageSize: 12,
        total: 0,
        pageCount: 1
    };

    export default {

        name: "course-center",

        components: {ErrorPage, CourseStatusSelect},

        mixins: [Global, Link, Table],

        data() {
            return {
                signTimeRange: [],
                classTimeRange: [],
                limit: merge({}, Limit),
                params: {
                    classstatus: '',
                    minClassTime: '',
                    maxClassTime: '',
                    minSignTime: '',
                    maxSignTime: '',
                    likeText: ''
                }
            }
        },

        watch: {
            signTimeRange(val) {
                if (this.isEmpty(val)) {
                    merge(this.params, {
                        minSignTime: '',
                        maxSignTime: ''
                    });
                    return;
                }
                merge(this.params, {
                    minSignTime: val[0],
                    maxSignTime: val[1]
                });
            },
            classTimeRange(val) {
                if (this.isEmpty(val)) {
                    merge(this.params, {
                        minClassTime: '',
                        maxClassTime: ''
                    });
                    return;
                }
                merge(this.params, {
                    minClassTime: val[0],
                    maxClassTime: val[1]
                });
            }
        },

        methods: {
            reGetRows() {
                merge(this.limit, Limit);
                this.getRows();
            },
            formatterRows(rows, {filePathPrefix, currentCreator}) {
                rows.forEach(row => {
                    row.filePath = this.isEmpty(row.location) ? void 0 : `${filePathPrefix}/${row.location}`;
                    row.signbegintime = row.signbegintime.substring(5, 16);
                    row.signendtime = row.signendtime.substring(5, 16);
                    row.begintime = row.begintime.substring(5, 16);
                    row.endtime = row.endtime.substring(5, 16);
                    row.isMy = row.teacherid === currentCreator;
                });
                return rows;
            },
            getRows() {
                this.tableLoading = true;
                this.$Ajax.get(FamClassUrls.get.pageListPublishFamClass, merge({}, this.params, this.limit))
                    .success(true, data => {
                        this.tableSkeleton = false;
                        this.formatterRows(data.rows, {
                            filePathPrefix: `${data.records.staticServerPath}${data.records.staticPathPrefix}`,
                            currentCreator: data.records.currentCreator
                        });
                        this.tableRows = data.rows;
                        merge(this.limit, data.limit);
                    })
                    .notSuccess(() => this.tableSkeleton = 500)
                    .catch(() => this.tableSkeleton = 500)
                    .finally(() => this.tableLoading = false);
            },
            handleRouterToCourseDetails({row, $index}) {
                if (row.isMy) {
                    this.routerToCourseManageDetails({courseId: row.id});
                } else {
                    this.routerToCourseCenterDetails({courseId: row.id});
                }
            }
        },

        created() {
            this.getRows();
        }
    }
</script>