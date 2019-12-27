<template>
    <div>
        <!--查询-->
        <div class="tour-search-bar" style="padding: 0 0 15px;">
            <!--查询条件-->
            <ul class="tour-search-bar-list">
                <li>
                    <em class="list-left list-label-4">上交情况：</em>
                    <div class="list-right">
                        <hand-in-select v-model="params.attachmentStatus" show-all-option
                                        style="width: 100px;"></hand-in-select>
                    </div>
                </li>
                <li>
                    <em class="list-left list-label-4">签到时间：</em>
                    <div class="list-right">
                        <el-date-picker v-model="signTimeRange"
                                        type="datetimerange"
                                        value-format="yyyy-MM-dd HH:mm:ss"
                                        size="small"
                                        clearable
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
                    <we-button :disabled="downloadExcelLoading" :loading="tableLoading" size="small" type="primary"
                               @click="reGetRows">查询
                    </we-button>
                    <we-button :disabled="tableLoading" :loading="downloadExcelLoading" size="small" type="success"
                               @click="handleExportExcel">导出
                    </we-button>
                </li>
            </ul>
            <div class="common-clear"></div>
            <!--/查询条件-->
        </div>
        <!--/查询-->

        <!--列表-->
        <div style="height: 600px;">
            <we-skeleton v-model="tableSkeleton" animation :paragraph-rows="10"
                         :title-width="'50%'" :paragraph-width="'100%'"
                         @reload="getRows">
                <div v-loading="!tableSkeleton && tableLoading">
                    <el-table :data="tableRows"
                              style="width: 100%"
                              border
                              height="600px">
                        <template slot="empty">
                            <error-page :image-width="180" :image-height="180"></error-page>
                        </template>
                        <el-table-column prop="realname"
                                         label="教师姓名"
                                         show-overflow-tooltip
                                         min-width="120">
                        </el-table-column>
                        <el-table-column prop="card"
                                         label="身份证号"
                                         show-overflow-tooltip
                                         min-width="150">
                        </el-table-column>
                        <el-table-column prop="phone"
                                         label="联系电话"
                                         show-overflow-tooltip
                                         width="120">
                        </el-table-column>
                        <el-table-column prop="orgname"
                                         label="所在学校"
                                         show-overflow-tooltip
                                         min-width="150">
                        </el-table-column>
                        <el-table-column prop="attachmentCount"
                                         label="上交情况"
                                         show-overflow-tooltip
                                         width="100">
                            <template slot-scope="scope">
                                <el-tag v-if="parseInt(scope.row.attachmentCount) > 0 || parseInt(scope.row.homeworkCount) > 0"
                                        type="success"
                                        size="small">已上交
                                </el-tag>
                                <el-tag v-else
                                        type="info"
                                        size="small">未上交
                                </el-tag>
                            </template>
                        </el-table-column>
                        <el-table-column prop="attachmentTime"
                                         label="上交时间"
                                         show-overflow-tooltip
                                         width="180">
                            <template slot-scope="scope">{{formatterTime(scope.row.attachmentTime)}}</template>
                        </el-table-column>
                        <el-table-column prop="operation"
                                         fixed="right"
                                         label="查看作业"
                                         width="120">
                            <template slot-scope="scope">
                                <we-button
                                        :disabled="parseInt(scope.row.attachmentCount) === 0 && parseInt(scope.row.homeworkCount) === 0"
                                        type="text"
                                        size="small"
                                        @click="handleViewHomework(scope)">查看
                                </we-button>
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
                           :page-sizes="[10, 20, 50]"
                           @current-change="v => {limit.currentPage = v;getRows();}"
                           @size-change="v => {limit.pageSize = v;getRows();}"
            >
            </el-pagination>
        </div>
        <!--/分页-->

        <!--查看作业-->
        <we-layer v-model="showHomework"
                  title="查看作业"
                  width="50%"
                  height="480px"
                  drag
                  resize
                  :showFooter="false">
            <div class="common-padding-all" :style="{'height': isEmpty(attachmentList) ? '320px' : 'auto'}">
                <div v-loading="homeworkLoading">
                    <div v-if="parseInt(tableViewRow.homeworkCount) > 0" v-html="homework.content"
                         style="border: 1px #e5e5e5 solid; height: 400px;"></div>
                    <template v-if="false">
                        <el-table :data="attachmentList"
                                  style="width: 100%; margin-top: 5px"
                                  border
                                  size="mini"
                                  height="320px">
                            <template slot="empty">
                                <error-page :image-width="180" :image-height="180"></error-page>
                            </template>
                            <el-table-column prop="fileRealName"
                                             label="文件名称"
                                             show-overflow-tooltip
                                             min-width="150">
                            </el-table-column>
                            <el-table-column prop="operation"
                                             fixed="right"
                                             label="操作"
                                             width="120">
                                <template slot-scope="scope">
                                    <we-button type="text" size="small" @click="handleDownloadAttachment(scope)">下载
                                    </we-button>
                                </template>
                            </el-table-column>
                        </el-table>
                    </template>
                </div>
            </div>
        </we-layer>
        <!--/查看作业-->
    </div>
</template>

<script>

    import Global from '../../../mixins/global.js';
    import Table from '../../../../../assets/template/mixins/table.js';
    import ErrorPage from "../../../../../components/error-page/src/ErrorPage.vue";
    import Link from '../../../mixins/link.js';
    import merge from "../../../../../utils/merge";
    import HandInSelect from "../components/select/src/HandInSelect.vue";
    import FamSignUrls from "../../../urls/fam-sign-urls";
    import FamClassAttachmentUrls from "../../../urls/fam-class-attachment-urls";
    import FamClassHomeworkUrls from "../../../urls/fam-class-homework-urls";

    const Limit = {
        currentPage: 1,
        pageSize: 20,
        total: 0,
        pageCount: 10
    };

    export default {

        name: "task-statistics-list",

        components: {ErrorPage, HandInSelect},

        mixins: [Global, Link, Table],

        props: {
            courseId: String
        },

        data() {
            return {
                signTimeRange: [],
                showHomework: false,
                homeworkLoading: false,
                attachmentList: [],
                limit: merge({}, Limit),
                params: {
                    attachmentStatus: '',
                    minSignTime: '',
                    maxSignTime: '',
                    likeText: ''
                },
                downloadExcelLoading: false,

                homework: {
                    content: ''
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
            }
        },

        methods: {
            reGetRows() {
                merge(this.limit, Limit);
                this.getRows();
            },
            getRows() {
                if (this.isEmpty(this.courseId)) {
                    this.tableRows = [];
                    return;
                }
                this.tableLoading = true;
                this.$Ajax.get(FamSignUrls.get.pageListSignUserAttachmentByClassId, [this.courseId], {
                    params: merge({}, this.params, this.limit)
                })
                    .success(true, data => {
                        this.tableSkeleton = false;
                        this.tableRows = data.rows;
                        merge(this.limit, data.limit);
                    })
                    .notSuccess(() => this.tableSkeleton = 500)
                    .catch(() => this.tableSkeleton = 500)
                    .finally(() => this.tableLoading = false);
            },
            handleViewHomework({row, $index}) {
                this.tableViewRow = row;
                //TODO 临时注释掉, 用户暂时放弃使用附件
                /*                this.attachmentList = [];
                                this.homeworkLoading = true;
                                this.$Ajax.get(FamClassAttachmentUrls.get.listFamClassAttachmentByClassAndUserId, [this.courseId, row.id])
                                    .success(true, data => {
                                        data.records.rows.forEach(row => {
                                            row.filePath = this.isEmpty(row.fileRelativeSaveFullPath) ? void 0 : `${data.records.staticServerPath}/${data.records.staticPathPrefix}/${row.fileRelativeSaveFullPath}`;
                                        });
                                        this.attachmentList = data.records.rows;
                                        this.showHomework = true;
                                    })
                                    .finally(() => this.homeworkLoading = false);*/
                this.getHomework();
                this.showHomework = true;
            },
            handleDownloadAttachment({row, $index}) {
                window.location.href = row.filePath;
            },
            handleExportExcel() {
                this.downloadExcelLoading = true;
                this.$Ajax.get(FamSignUrls.get.downloadExcelSignUserByClassId, [this.courseId], {
                    params: merge({}, this.params)
                })
                    .success(true, data => {
                        const {staticServerPath, downloadPath, filePath} = data.records;
                        // 使用请求地址下载,如果服务端静态服务端地址是内网,则无法下载
                        window.location.href = staticServerPath + "/" + FamSignUrls.get.downloadExcel.replace("{excelName}", "学员管理").replace("{filePath}", filePath);
                        // 使用流下载,该方式下载的Excel会报内存或磁盘不足,无法打开,需要解除用户电脑Excel安全信任,自行百度
                        /*this.$Ajax.download('学员管理.xlsx', FamSignUrls.get.downloadExcel, {
                            excelName: '学员管理',
                            filePath: filePath
                        });*/

                    })
                    .finally(() => this.downloadExcelLoading = false);
            },
            // 文本作业
            getHomework() {
                this.homeworkLoading = true;
                this.$Ajax.get(FamClassHomeworkUrls.get.famClassHomeworkByFamClassIdAndUserId, [this.courseId, this.tableViewRow.id])
                    .success(true, data => {
                        let homework = data.records.famClassHomework;
                        if (homework) {
                            // 存在作业
                            this.homework = homework;
                        } else {
                            // 不存在作业
                        }
                    })
                    .finally(() => this.homeworkLoading = false);
            },
        },

        created() {
            this.getRows();
        }
    }
</script>
