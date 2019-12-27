<template>
    <div>
        <div class="layout-header" style="padding-right: 0;">
            <div class="common-lfloat">课堂互动</div>
            <div class="common-rfloat layout-header-btn" @click="isComment = true">发布评论</div>
            <div class="common-clra"></div>
        </div>
        <div class="layout-content">
            <van-pull-refresh v-model="loading" @refresh="onRefresh" style="text-align: center" :head-height="30">
                <van-list ref="list"
                          v-model="listLoading"
                          :finished="finished"
                          finished-text="没有更多了"
                          @load="onLoad">
                    <van-cell v-for="(row, index) in rows" :key="row.id">
                        <div class="course-interact-one">
                            <img class="user-face" src="../../../../../../public/tour-speak/images/face/user_face.png"/>
                            <div class="user-info">
                                <h3 class="user-name">
                                    <div>{{row.username}}</div>
                                    <span v-if="`${row.teacherid}` === `${row.userid}`">授课教师</span>
                                </h3>
                                <div class="user-reply-date">{{formatterTime(row.createtime)}}</div>
                            </div>
                            <div class="common-clra"></div>
                            <div class="reply-content common-wordwrap">
                                <span v-if="!isEmpty(row.targetUserName)">回复{{row.targetUserName}}：</span>
                                {{row.content}}
                            </div>
                            <div class="reply-btn" @click="handleShowReply({row, $index:index})">回复</div>
                        </div>
                    </van-cell>
                </van-list>
            </van-pull-refresh>
        </div>

        <!--发布评论-->
        <div class="reply-layout-main" :class="{'is-show':isComment}">
            <div class="reply-layout-header">
                <h3 class="header-left">发布评论</h3>
                <div class="header-right" @click="isComment = false">
                    <i class="iconfont we-icon-tour-close header-close"></i>
                </div>
                <div class="common-clra"></div>
            </div>
            <div class="reply-layout-content">
                <textarea v-model="params.content" class="content-text"></textarea>
                <button class="content-btn" @click="handleReply">发布评论</button>
            </div>
        </div>
        <!--/发布评论-->
        <!--回复评论-->
        <div class="reply-layout-main" :class="{'is-show':showReplyLayer}">
            <div class="reply-layout-header">
                <h3 class="header-left common-ellipsis">回复{{replyUserName}}</h3>
                <div class="header-right" @click="showReplyLayer = false">
                    <i class="iconfont we-icon-tour-close header-close"></i>
                </div>
                <div class="common-clra"></div>
            </div>
            <div class="reply-layout-content">
                <textarea v-model="replyContent" class="content-text"></textarea>
                <button class="content-btn" @click="handleReplyUser">回复</button>
            </div>
        </div>
        <!--/回复评论-->
    </div>
</template>

<script>

    import FamQuestionReplyUrls from "../../../urls/fam-question-reply-urls";
    import Global from '../../../mixins/global.js';
    import Link from '../../../mixins/link.js';
    import VanList from 'vant/lib/list/index.js';
    import VanCell from 'vant/lib/cell/index.js';
    import VanPullRefresh from 'vant/lib/pull-refresh/index.js';
    import 'vant/lib/list/style/index.js';
    import merge from "../../../../../utils/merge";

    const Limit = {
        currentPage: 0,
        pageSize: 5,
        total: 0,
        pageCount: 1
    };

    export default {

        components: {VanList, VanCell, VanPullRefresh},

        name: "course-interact",

        mixins: [Global, Link],

        props: {
            courseId: {//课程ID
                type: String,
                required: true
            }
        },

        data() {
            return {
                listLoading: false,
                loading: false,
                finished: false,
                replyLoading: false,
                params: {
                    content: '',
                    targetUserId: '',
                    targetUserName: ''
                },
                limit: merge({}, Limit),
                isComment: false,
                showReplyLayer: false,
                replyUserId: '',
                replyUserName: '',
                replyContent: '',
                rows: []
            };
        },

        methods: {
            onRefresh() {
                // 这个是上拉刷新,应该是重置加载
                setTimeout(() => {
                    this.finished = false;
                    this.loading = false;
                    this.rows = [];
                    merge(this.limit, Limit);
                    this.onLoad();
                }, 500);
            },
            onLoad() {
                // 这个是下拉加载更多
                this.limit.currentPage++;
                this.$Ajax.get(FamQuestionReplyUrls.get.pageListFamQuestionReplyByClassId, [this.courseId], {
                    params: merge({}, this.limit)
                })
                    .success(true, data => {
                        this.rows.push(...data.rows);
                        if (data.rows.length === 0) {
                            this.finished = true;
                        }
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
                        if (this.finished === true) {
                            this.rows.push(data.records.famQuestionReply);
                        }
                        merge(this.params, {
                            content: '',
                            targetUserId: '',
                            targetUserName: ''
                        });
                        this.isComment = false;
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
                        if (this.finished === true) {
                            this.rows.push(data.records.famQuestionReply);
                        }
                        this.replyUserId = '';
                        this.replyUserName = '';
                        this.replyContent = '';
                        this.showReplyLayer = false;
                    })
                    .finally(() => this.replyLoading = false);
            }
        },

        created() {
            window.events.on('show-login-window', () => {
                this.routerToSignIn({courseId: this.courseId});
            });
        }
    }
</script>
<style type="text/css">
    .van-pull-refresh__loading {
        text-align: center;
    }
    .van-pull-refresh__loading .van-loading{
        margin: 0 auto;
    }
</style>
