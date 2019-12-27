<template>
    <div class="common-bg-default">
        <h1 class="tour-layout-right-title">课程管理</h1>
        <!--查询-->
        <div class="tour-search-bar">
            <!--查询条件-->
            <ul class="tour-search-bar-list">
                <li>
                    <em class="list-left list-label-4">上课时间：</em>
                    <div class="list-right">
                        <el-date-picker v-model="timeRange"
                                        type="datetimerange"
                                        value-format="yyyy-MM-dd HH:mm:ss"
                                        size="small"
                                        range-separator="至"
                                        start-placeholder="开始日期"
                                        end-placeholder="结束日期"
                                        style="width: 300px">
                        </el-date-picker>
                    </div>
                </li>
                <li>
                    <em class="list-left list-label-4">关键字：</em>
                    <div class="list-right">
                        <el-input v-model="params.likeText" size="small" clearable
                                  style="width: 200px"
                                  placeholder="请输入关键字"></el-input>
                    </div>
                </li>
                <li class="spacing">
                    <we-button :loading="listLoading" size="small" type="primary"
                               @click="reGetRows">查询
                    </we-button>
                </li>
            </ul>
            <div class="common-float-right">
                <we-button type="success" @click="routerToCourseAdd">新建课程</we-button>
            </div>
            <div class="common-clear"></div>
            <!--/查询条件-->
        </div>
        <!--/查询-->
        <!--列表-->
        <div :style="{'height': isEmpty(listRows) ? '600px' : 'auto'}">
            <we-skeleton v-model="listSkeleton" animation :paragraph-rows="10"
                         :title-width="'50%'" :paragraph-width="'100%'"
                         @reload="getRows">
                <div v-loading="!listSkeleton && listLoading" class="tour-img-full" style="min-height: 600px;">
                    <error-page v-if="isEmpty(listRows)" :image-width="180" :image-height="180"></error-page>
                    <ul v-else class="tour-img-full-list">
                        <li v-for="(row, index) in listRows">
                            <div class="tour-img-full-list-left">
                                <img v-if="isEmpty(row.filePath)"
                                     src="../../../../../../public/tour-speak/images/course_default_img.png"/>
                                <img v-else :src="row.filePath"/>
                            </div>
                            <div class="tour-img-full-list-right">
                                <h3 class="tour-img-full-list-info-title">
                                    <div class="title-left" @click="routerToCourseManageDetails({courseId: row.id})">
                                        {{row.title}}
                                    </div>
                                    <div class="title-right">
                                        <we-button v-if="row.status === dict.famClassStatusValue.SAVE
                                                        && row.realTimeClassStatus === dict.classStatusValue.NOT_START"
                                                   :disabled="row.publishLoading || row.deleteLoading"
                                                   type="primary"
                                                   size="mini"
                                                   @click="routerToCourseEdit({courseId: row.id})">
                                            编辑
                                        </we-button>
                                        <we-button v-if="row.status !== dict.famClassStatusValue.SAVE"
                                                   disabled
                                                   type="primary"
                                                   size="mini"
                                                   @click="routerToCourseEdit({courseId: row.id})">
                                            已发布
                                        </we-button>
                                        <we-popover title="操作" type="confirm"
                                                    placement="left-start"
                                                    :manual="row.publishLoading"
                                                    confirm-button-text="我要发布"
                                                    :cancel-button-text="row.publishLoading ? '请稍等' : '我再想想'"
                                                    :cancel-button-options="{disabled: row.publishLoading}"
                                                    :confirm-button-options="{loading: row.publishLoading}"
                                                    @click-cancel-button="(e, vm) => vm.close()"
                                                    @click-confirm-button="(e, vm) => handlePublish(e, vm, {row, $index: index})">
                                            <template slot="content">
                                                <template v-if="row.publishLoading">
                                                    <span style="color: red">发布中...</span>发布完成后将自动关闭气泡
                                                </template>
                                                <template v-else>
                                                    您确定要<span style="color: red">发布</span>该课程吗？
                                                    <div style="color: red">发布后不可恢复！！！</div>
                                                </template>
                                            </template>
                                            <we-button v-if="row.status === dict.famClassStatusValue.SAVE"
                                                       :disabled="row.deleteLoading"
                                                       type="success" size="mini">发布
                                            </we-button>
                                        </we-popover>
                                        <we-popover title="操作" type="confirm"
                                                    placement="top-start"
                                                    :manual="row.deleteLoading"
                                                    confirm-button-text="删了吧"
                                                    :cancel-button-text="row.deleteLoading ? '请稍等' : '我再想想'"
                                                    :cancel-button-options="{disabled: row.deleteLoading}"
                                                    :confirm-button-options="{loading: row.deleteLoading}"
                                                    @click-cancel-button="(e, vm) => vm.close()"
                                                    @click-confirm-button="(e, vm) => handleDeleteRow(e, vm, {row, $index: index})">
                                            <template slot="content">
                                                <template v-if="row.deleteLoading">
                                                    <span style="color: red">删除中...</span>删除完成后将自动关闭气泡
                                                </template>
                                                <template v-else>
                                                    您确定要<span style="color: red">删除</span>该课程吗？
                                                    <div style="color: red">删除后不可恢复！！！</div>
                                                </template>
                                            </template>
                                            <we-button v-if="[dict.classStatusValue.NOT_START,
                                                            dict.classStatusValue.CLASS_OVER].indexOf(row.realTimeClassStatus) !== -1"
                                                       :disabled="row.publishLoading" type="danger" size="mini">删除
                                            </we-button>
                                        </we-popover>
                                    </div>
                                </h3>
                                <div class="common-clear"></div>
                                <div class="tour-img-full-list-info-one common-ellipsis">
                                    报名时间：<em class="common-fc-success">{{row.signbegintime}}</em>&nbsp;-
                                    <em class="common-fc-danger">{{row.signendtime}}</em>
                                </div>
                                <div class="tour-img-full-list-info-one common-ellipsis">
                                    上课时间：<em class="common-fc-success">{{row.begintime}}</em>&nbsp;-
                                    <em class="common-fc-danger">{{row.endtime}}</em>
                                </div>
                                <div class="common-clear"></div>
                                <div class="common-margin-t-small common-ellipsis">{{row.content}}</div>
                            </div>
                            <div class="common-clear"></div>
                        </li>
                    </ul>
                    <div class="common-clear"></div>
                </div>
            </we-skeleton>
        </div>
        <!--/列表-->
        <!--分页-->
        <div class="tour-paging-main tour-paging-size common-tac">
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
    import List from '../../../../../assets/template/mixins/list.js';
    import Link from '../../../mixins/link.js';
    import ErrorPage from "../../../../../components/error-page/src/ErrorPage.vue";
    import merge from "../../../../../utils/merge.js";
    import FamClassUrls from "../../../urls/fam-class-urls.js";

    const Limit = {
        currentPage: 1,
        pageSize: 20,
        total: 0,
        pageCount: 1
    };

    export default {

        name: "course-manage",

        components: {ErrorPage},

        mixins: [Global, Link, List],

        data() {
            return {
                timeRange: [],
                listSkeleton: true,
                limit: merge({}, Limit),
                params: {
                    minClassTime: '',
                    maxClassTime: '',
                    likeText: ''
                },
                radio: "未结束"
            }
        },

        watch: {
            timeRange(val) {
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
            formatterRows(rows, {filePathPrefix}) {
                rows.forEach(row => {
                    row.deleteLoading = false;
                    row.publishLoading = false;
                    row.filePath = this.isEmpty(row.location) ? void 0 : `${filePathPrefix}/${row.location}`;
                });
                return rows;
            },
            getRows() {
                this.listLoading = true;
                this.$Ajax.get(FamClassUrls.get.pageListMyFamClass, merge({}, this.params, this.limit))
                    .success(true, data => {
                        this.listSkeleton = false;
                        this.formatterRows(data.rows, {filePathPrefix: `${data.records.staticServerPath}${data.records.staticPathPrefix}`});
                        this.listRows = data.rows;
                        merge(this.limit, data.limit);
                    })
                    .notSuccess(() => this.listSkeleton = 500)
                    .catch(() => this.listSkeleton = 500)
                    .finally(() => this.listLoading = false);
            },
            handlePublish(e, vm, {row, $index}) {
                row.publishLoading = true;
                this.$Ajax.delete(FamClassUrls.put.publishFamClassByFamClassId, {
                    famClassId: row.id
                })
                    .success('发布成功', () => {
                        vm.close();
                        row.status = this.dict.famClassStatusValue.PUBLISH;
                    })
                    .finally(() => row.publishLoading = false);
            },
            handleDeleteRow(e, vm, {row, $index}) {
                row.deleteLoading = true;
                this.$Ajax.delete(FamClassUrls.delete.famClassByFamClassId, {
                    famClassId: row.id
                })
                    .success('删除成功', () => {
                        vm.close();
                        this.reGetRows();
                    })
                    .finally(() => row.deleteLoading = false);
            }
        },

        created() {
            this.getRows();
        }
    }
</script>