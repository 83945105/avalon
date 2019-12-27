<template>
    <div>
        <div class="tour-details-chat-text">
            <el-input type="textarea"
                      :rows="3"
                      resize="none"
                      maxlength="256"
                      v-model="params.content"
                      style="width: 89.5%"
                      class="common-float-left">
            </el-input>
            <we-button :disabled="isEmpty(params.content)"
                       :loading="replyLoading"
                       class="common-float-right" type="success" size="large"
                       style="height: 75px; width: 10%"
                       @click="handleReply"
            >发布
            </we-button>
            <div class="common-clear"></div>
        </div>


        <div style="min-height: 500px">
            <error-page v-if="isEmpty(listRows)" :image-width="180" :image-height="180"></error-page>
            <template v-else>
                <template v-for="(row, index) in listRows">
                    <div class="tour-details-chat-one">
                        <div class="chat-one-left">
                            <img src="../../../../../../public/tour-speak/images/face/user_face.png"/>
                        </div>
                        <div class="chat-one-right">
                            <div class="chat-one-info">
                                <h3 class="chat-one-info-left">
                                    <span>{{row.username}}</span>&nbsp;<em v-if="`${row.teacherid}` === `${row.userid}`"
                                                                           class="chat-one-info-type">授课教师</em>
                                </h3>
                                <div class="chat-one-info-right">{{formatterTime(row.createtime)}}</div>
                                <div class="common-clear"></div>
                            </div>
                            <div class="chat-one-content">
                                <div class="chat-one-content-left">
                                    <template v-if="!isEmpty(row.targetUserName)">
                                        <em class="common-fc-primary common-padding-r-mini">回复</em>
                                        <span class="common-fc-primary">{{row.targetUserName}}：</span>
                                    </template>
                                    <div class="common-wordwrap">{{row.content}}</div>
                                </div>
                                <div class="chat-one-content-right">
                                    <we-button type="text" size="small" @click="handleShowReply({row, $index:index})">
                                        回复
                                    </we-button>
                                </div>
                                <div class="common-clear"></div>
                            </div>
                            <div class="common-clear"></div>
                        </div>
                    </div>
                </template>
            </template>
        </div>
        <!--分页-->
        <div class="tour-paging-main tour-paging-size-small common-tac common-margin-t">
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

        <!--回复-->
        <we-layer v-model="showReplyLayer"
                  title="回复内容"
                  width="600px"
                  height="250px"
                  drag
                  :showFooter="false">
            <div class="common-padding-all">
                <el-input v-model="replyContent"
                          type="textarea"
                          :rows="5"
                          maxlength="256"
                          resize="none"
                >
                </el-input>
                <div class="common-tar common-margin-t">
                    <we-button :disabled="isEmpty(replyContent)"
                               :loading="replyLoading"
                               type="primary"
                               @click="handleReplyUser"
                    >发布
                    </we-button>
                </div>
            </div>
        </we-layer>
        <!--/回复-->
    </div>
</template>

<script>

    import Global from '../../../mixins/global.js';
    import List from '../../../../../assets/template/mixins/list.js';
    import Link from '../../../mixins/link.js';
    import ErrorPage from "../../../../../components/error-page/src/ErrorPage.vue";
    import merge from "../../../../../utils/merge.js";
    import FamQuestionReplyUrls from "../../../urls/fam-question-reply-urls.js";

    const Limit = {
        currentPage: 1,
        pageSize: 20,
        total: 0,
        pageCount: 1
    };

    export default {
        name: "learning-interact",

        components: {ErrorPage},

        mixins: [Global, Link, List],

        props: {
            courseId: String
        },

        data() {
            return {
                showReplyLayer: false,
                replyUserId: '',
                replyUserName: '',
                replyContent: '',
                replyLoading: false,
                limit: merge({}, Limit),
                params: {
                    content: '',
                    targetUserId: '',
                    targetUserName: ''
                },
            }
        },

        watch: {
            courseId: {
                immediate: true,
                handler(val) {
                    this.getRows();
                }
            }
        },

        methods: {
            getRows() {
                if (this.isEmpty(this.courseId)) {
                    this.listRows = [];
                    return;
                }
                this.listLoading = true;
                this.$Ajax.get(FamQuestionReplyUrls.get.pageListFamQuestionReplyByClassId, [this.courseId], {
                    params: merge({}, this.limit)
                })
                    .success(true, data => {
                        this.listRows = data.rows;
                        merge(this.limit, data.limit);
                    })
                    .finally(() => this.listLoading = false);
            },
            handleReply() {
                this.replyLoading = true;
                let params = merge({
                    classId: this.courseId
                }, this.params);
                this.$Ajax.post(FamQuestionReplyUrls.post.famQuestionReplyByClassId, params)
                    .success(true, data => {
                        merge(this.params, {
                            content: '',
                            targetUserId: '',
                            targetUserName: ''
                        });
                        this.listRows.push(data.records.famQuestionReply);
                    })
                    .finally(() => this.replyLoading = false);
            },
            handleShowReply({row, $index}) {
                this.replyUserId = row.userid;
                this.replyUserName = row.username;
                this.showReplyLayer = true;
            },
            handleReplyUser() {
                this.replyLoading = true;
                let params = {
                    classId: this.courseId,
                    targetUserId: this.replyUserId,
                    targetUserName: this.replyUserName,
                    content: this.replyContent
                };
                this.$Ajax.post(FamQuestionReplyUrls.post.famQuestionReplyByClassId, params)
                    .success(true, data => {
                        this.showReplyLayer = false;
                        this.replyUserId = '';
                        this.replyUserName = '';
                        this.replyContent = '';
                        this.listRows.push(data.records.famQuestionReply);
                    })
                    .finally(() => this.replyLoading = false);
            }
        }
    }
</script>
