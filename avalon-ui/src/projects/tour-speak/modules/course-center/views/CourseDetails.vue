<template>
    <div>
        <div class="tour-details-crumbs">
            <div class="tour-details-crumbs-left">课程详情</div>
            <div class="tour-details-crumbs-right ">
                <we-button size="small" type="text" @click="$router.go(-1)">返回</we-button>
            </div>
            <div class="common-clear"></div>
        </div>

        <div class="tour-details-info">
            <div class="tour-details-info-left">
                <img v-if="isEmpty(famClass.filePath)"
                     src="../../../../../../public/tour-speak/images/course_default_img.png"/>
                <img v-else :src="famClass.filePath"/>
                <div class="tour-details-info-status">
                    <span v-if="famClass.realTimeClassStatus === dict.classStatusValue.NOT_START"
                          class="tour-details-info-status-fc-1">未开始</span>
                    <span v-else-if="famClass.realTimeClassStatus === dict.classStatusValue.SIGNING"
                          class="tour-details-info-status-fc-2">报名中</span>
                    <span v-else-if="famClass.realTimeClassStatus === dict.classStatusValue.SIGN_OVER"
                          class="tour-details-info-status-fc-3">报名结束</span>
                    <span v-else-if="famClass.realTimeClassStatus === dict.classStatusValue.CLASSING"
                          class="tour-details-info-status-fc-4">上课中</span>
                    <span v-else-if="famClass.realTimeClassStatus === dict.classStatusValue.CLASS_OVER"
                          class="tour-details-info-status-fc-5">已结束</span>
                </div>
            </div>
            <div v-loading="classLoading" class="tour-details-info-right">
                <h3 class="info-title">{{famClass.title}}</h3>
                <div class="info-content-one">授课教师：<em class="common-fc-text-1">{{famClass.teachername}}</em></div>
                <div class="info-content-one">报名人数：<em class="common-fc-success">{{famClass.enterednum}}</em>&nbsp;/&nbsp;<em
                        class="common-fc-text-1">{{famClass.allownum}}人</em></div>
                <div class="common-clear"></div>
                <div class="info-content-one">课程时间：<em class="common-fc-text-1">{{famClass.begintime}}&nbsp;至&nbsp;{{famClass.endtime}}</em>
                </div>
                <div class="info-content-one">报名时间：<em class="common-fc-text-1">{{famClass.signbegintime}}&nbsp;至&nbsp;{{famClass.signendtime}}</em>
                </div>
                <div class="common-clear"></div>
                <div class="info-content-one" style="width: 98%">上课地点：<em
                        class="common-fc-text-1">{{famClass.address}}</em></div>
                <div class="common-clear"></div>
                <div class="info-content-button">
                    <template v-if="famClass.realTimeClassStatus === dict.classStatusValue.NOT_START">
                        <we-button disabled>报名未开始</we-button>
                    </template>
                    <template v-else-if="famClass.realTimeClassStatus === dict.classStatusValue.SIGNING">
                        <we-button v-if="isEmpty(famSignId) || signStatus === dict.signStatusValue.UNSIGN"
                                   :loading="signLoading"
                                   type="success" size="small"
                                   @click="handleSign">我要报名
                        </we-button>
                        <we-button v-else-if="!isEmpty(famSignId) && signStatus === dict.signStatusValue.SIGNED"
                                   :loading="signLoading"
                                   type="primary" size="small"
                                   @click="handleUnSign">
                            取消报名
                        </we-button>
                        <we-button v-else
                                   disabled
                                   type="warning" size="small">
                            您无法报名该课程
                        </we-button>
                    </template>
                    <template v-else-if="famClass.realTimeClassStatus === dict.classStatusValue.SIGN_OVER">
                        <we-button disabled>报名已结束</we-button>
                    </template>
                    <template v-else-if="famClass.realTimeClassStatus === dict.classStatusValue.CLASSING">
                        <we-button disabled>上课中</we-button>
                    </template>
                    <template v-else-if="famClass.realTimeClassStatus === dict.classStatusValue.CLASS_OVER">
                        <we-button disabled>课程已结束</we-button>
                    </template>
                </div>
            </div>
            <div class="common-clear"></div>
            <div class="common-margin-t common-wordwrap common-fc-text-1">{{famClass.content}}</div>
            <div class="common-clear"></div>
        </div>

        <!--选项卡-->
        <div class="tour-details-tab">
            <el-tabs type="border-card">
                <el-tab-pane key="A" label="教师信息">
                    <teacher-info key="A" :teacher-id="famClass.teacherid"></teacher-info>
                </el-tab-pane>
                <el-tab-pane key="B" lazy label="课堂互动" v-if="signStatus === dict.signStatusValue.SIGNED">
                    <learning-interact key="B" :course-id="courseId"></learning-interact>
                </el-tab-pane>
                <el-tab-pane key="C" label="我的作业" v-if="signStatus === dict.signStatusValue.SIGNED && famClass.realTimeClassStatus === dict.classStatusValue.CLASS_OVER">
                    <my-task key="C" :course-id="courseId"></my-task>
                </el-tab-pane>
            </el-tabs>
        </div>
        <!--/选项卡-->
    </div>
</template>

<script>

    import Global from '../../../mixins/global.js';
    import Link from '../../../mixins/link.js';
    import TeacherInfo from "./TeacherInfo.vue";
    import TaskStatisticsList from "./TaskStatisticsList.vue";
    import LearningInteract from "./LearningInteract.vue";
    import MyTask from "./MyTask";
    import FamClassUrls from "../../../urls/fam-class-urls";
    import FamSignUrls from "../../../urls/fam-sign-urls";

    export default {

        name: "course-details",

        components: {
            MyTask,
            LearningInteract,
            TaskStatisticsList,
            TeacherInfo
        },

        mixins: [Global, Link],

        props: {
            courseId: String
        },

        data() {
            return {
                classLoading: false,
                famClass: {},
                signLoading: false,
                famSignId: '',
                signStatus: '',


                isSignUp: false,
                showQrCodeLayer: false,
                rowFormView: {
                    status: "ENROLMENT"
                },
            }
        },

        watch: {
            courseId: {
                immediate: true,
                handler(val) {
                    this.getRow();
                }
            }
        },

        methods: {
            getRow() {
                if (this.isEmpty(this.courseId)) {
                    return;
                }
                this.classLoading = true;
                this.$Ajax.get(FamClassUrls.get.famClassByFamClassId, [this.courseId])
                    .success(true, data => {
                        this.famClass = data.records.famClass;
                        this.famClass.filePath = this.isEmpty(this.famClass.location) ? void 0 : `${data.records.staticServerPath}/${data.records.staticPathPrefix}/${this.famClass.location}`;
                        let famSign = data.records.famSign;
                        if (famSign) {
                            this.famSignId = famSign.id;
                            this.signStatus = famSign.status;
                        }
                    })
                    .finally(() => this.classLoading = false);
            },
            handleSign() {
                this.signLoading = true;
                this.$Ajax.post(FamSignUrls.post.famSignByClassId, {
                    classId: this.courseId
                })
                    .success('报名成功', data => {
                        this.famClass.enterednum = parseInt(this.famClass.enterednum) + 1;
                        this.famSignId = data.records.famSignId;
                        this.signStatus = this.dict.signStatusValue.SIGNED;
                    })
                    .finally(() => this.signLoading = false);
            },
            handleUnSign() {
                this.signLoading = true;
                this.$Ajax.post(FamSignUrls.put.unFamSignByClassId, {
                    classId: this.courseId
                })
                    .success('取消报名成功', data => {
                        this.famClass.enterednum = parseInt(this.famClass.enterednum) - 1;
                        this.famSignId = '';
                        this.signStatus = '';
                    })
                    .finally(() => this.signLoading = false);
            }
        }
    }
</script>