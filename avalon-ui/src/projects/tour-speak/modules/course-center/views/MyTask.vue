<template>
    <div class="common-h-min-main">
        <div class="common-margin-auto" style="width: 70%">
            <div style="margin-bottom: 20px">
                <div ref="editor" v-html="html" contenteditable="true" class="editor"
                     style="border: 1px #e5e5e5 solid; height: 400px;"
                     @input="handleChangeContent"
                ></div>
                <div style="margin-top: 10px; text-align: center">
                    <we-button :disabled="loading || !homework.content"
                               type="primary"
                               style="width: 100px"
                               @click="handleSubmitHomework">提交
                    </we-button>
                    <we-button :disabled="loading || !homework.content"
                               @click="homework.content = '';$refs.editor.innerHTML = ''">重置
                    </we-button>
                </div>
            </div>
            <!--                //TODO 临时注释掉, 用户暂时放弃使用附件   -->
            <!--            <el-upload class="upload-demo"
                                   ref="upload"
                                   :before-upload="beforeUpload"
                                   action=""
                                   accept="application/msword,application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet">
                            <we-button :loading="uploadLoading" size="small" type="primary">上传文件</we-button>
                            <span slot="tip" style="line-height: 32px;padding-left: 10px;">只能上传xls/xlsx/doc/docx文件，且不超过500kb</span>
                        </el-upload>-->

            <!--列表-->
            <!--            <div style="height: 600px">
                            <we-skeleton v-model="listSkeleton" animation :paragraph-rows="10"
                                         :title-width="'50%'" :paragraph-width="'100%'"
                                         @reload="getRows">
                                <div v-loading="!listSkeleton && listLoading" class="common-margin-t">
                                    <el-table :data="listRows"
                                              style="width: 100%"
                                              border
                                              height="500px">
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
                                                <we-popover title="操作" type="confirm"
                                                            placement="top-start"
                                                            :manual="scope.row.deleteLoading"
                                                            confirm-button-text="删了吧"
                                                            :cancel-button-text="scope.row.deleteLoading ? '请稍等' : '我再想想'"
                                                            :cancel-button-options="{disabled: scope.row.deleteLoading}"
                                                            :confirm-button-options="{loading: scope.row.deleteLoading}"
                                                            @click-cancel-button="(e, vm) => vm.close()"
                                                            @click-confirm-button="(e, vm) => handleDeleteRow(e, vm, scope)">
                                                    <template slot="content">
                                                        <template v-if="scope.row.deleteLoading">
                                                            <span style="color: red">删除中...</span>删除完成后将自动关闭气泡
                                                        </template>
                                                        <template v-else>
                                                            您确定要<span style="color: red">删除</span>该行数据吗？
                                                            <div style="color: red">删除后不可恢复！！！</div>
                                                        </template>
                                                    </template>
                                                    <we-button type="text" size="small">删除</we-button>
                                                </we-popover>
                                            </template>
                                        </el-table-column>
                                    </el-table>
                                </div>
                            </we-skeleton>
                        </div>-->
            <!--/列表-->
            <!--分页-->
            <!--            <div class="tour-paging-main tour-paging-size-small common-tac">
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
                        </div>-->
            <!--/分页-->
        </div>
    </div>
</template>

<script>
    import Global from '../../../mixins/global.js';
    import List from '../../../../../assets/template/mixins/list.js';
    import Link from '../../../mixins/link.js';
    import ErrorPage from "../../../../../components/error-page/src/ErrorPage.vue";
    import merge from "../../../../../utils/merge.js";
    import FamClassAttachmentUrls from "../../../urls/fam-class-attachment-urls.js";
    import FamClassHomeworkUrls from "../../../urls/fam-class-homework-urls";

    const Limit = {
        currentPage: 1,
        pageSize: 20,
        total: 0,
        pageCount: 1
    };

    export default {

        name: "my-task",

        components: {ErrorPage},

        mixins: [Global, Link, List],

        props: {
            courseId: String
        },

        data() {
            return {
                uploadLoading: false,
                limit: merge({}, Limit),

                /**
                 * 作业内容
                 */
                html: '',
                homework: {
                    id: '',
                    content: ''
                },
                loading: false
            }
        },

        methods: {
            reGetRows() {
                merge(this.limit, Limit);
                this.getRows();
            },
            formatterRows(rows) {
                rows.forEach(row => {
                    row.deleteLoading = false;
                });
                return rows;
            },
            getRows() {
                this.listLoading = true;
                this.$Ajax.get(FamClassAttachmentUrls.get.pageListMyFamClassAttachmentByClassId, [this.courseId], {
                    params: merge({}, this.limit)
                })
                    .success(true, data => {
                        this.listSkeleton = false;
                        this.formatterRows(data.rows);
                        this.listRows = data.rows;
                        merge(this.limit, data.limit)
                    })
                    .finally(() => this.listLoading = false);
            },
            beforeUpload(file) {
                if (file.size > (500 * 1024 * 1024)) {
                    this.$message.error({
                        content: '上传文件大小不能超过500KB！'
                    });
                    return false;
                }
                let formData = new FormData();
                formData.append("file", file);
                this.uploadLoading = true;
                this.$Ajax.post(FamClassAttachmentUrls.post.famClassAttachmentByClassId + this.courseId, formData)
                    .success(true, data => {
                        this.listRows.push(data.records.famClassAttachment)
                    })
                    .finally(() => this.uploadLoading = false);
                return false;
            },
            handleDeleteRow(e, vm, {row, $index}) {
                row.deleteLoading = true;
                this.$Ajax.delete(FamClassAttachmentUrls.delete.famClassAttachmentByFamClassAttachmentId, {
                    famClassAttachmentId: row.id
                })
                    .success('删除成功', () => {
                        vm.close();
                        this.reGetRows();
                    })
                    .finally(() => row.deleteLoading = false);
            },
            handleChangeContent(e) {
                this.homework.content = e.target.innerHTML;
            },
            // 文本作业
            getHomework() {
                this.loading = true;
                this.$Ajax.get(FamClassHomeworkUrls.get.famClassHomeworkByFamClassId, [this.courseId])
                    .success(true, data => {
                        let homework = data.records.famClassHomework;
                        if (homework) {
                            // 存在作业
                            this.homework = homework;
                            this.html = homework.content;
                        } else {
                            // 不存在作业
                        }
                    })
                    .finally(() => this.loading = false);
            },
            handleSubmitHomework() {
                this.loading = true;
                if (this.isEmpty(this.homework.id)) {
                    this.$Ajax.post(FamClassHomeworkUrls.post.famClassHomeworkByFamClassId, {
                        famClassId: this.courseId,
                        content: this.homework.content
                    })
                        .success('提交成功', data => {
                            this.homework.id = data.records.famClassHomeworkId;
                        })
                        .finally(() => this.loading = false);
                    return;
                }
                this.$Ajax.put(FamClassHomeworkUrls.put.famClassHomeworkByFamClassHomeworkId, {
                    famClassHomeworkId: this.homework.id,
                    content: this.homework.content
                })
                    .success('提交成功')
                    .finally(() => this.loading = false);
            }
        },

        created() {
            // this.getRows();
            this.getHomework();
        }
    }
</script>