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
                    <!--<we-button type="success" size="small">编辑课程</we-button>-->
                    <we-button type="primary" size="small" @click="handleGenQrCode">二维码</we-button>
                    <we-button type="warning" size="small" @click="showRollCallLayer = true">开始点名</we-button>
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
                <el-tab-pane key="C" label="学员管理">
                    <student-manage-list key="C" :course-id="courseId"></student-manage-list>
                </el-tab-pane>
                <el-tab-pane key="B" lazy label="课堂互动">
                    <learning-interact key="B" :course-id="courseId"></learning-interact>
                </el-tab-pane>
                <el-tab-pane key="D" label="作业统计">
                    <task-statistics-list key="D" :course-id="courseId"></task-statistics-list>
                </el-tab-pane>
            </el-tabs>
        </div>
        <!--/选项卡-->

        <!--二维码下载-->
        <we-layer v-model="showQrCode"
                  title="二维码下载"
                  width="400px"
                  height="600px"
                  drag
                  :showFooter="false">
            <div class="common-padding-all common-tac common-margin-auto">
                <canvas id="canvas"></canvas>
                <div class="common-margin-t common-wordwrap">{{famClass.title}}</div>
                <div class="common-margin-t">
                    <we-button type="primary" size="small" @click="handleDownloadQrCode">下载二维码</we-button>
                </div>
            </div>
        </we-layer>
        <!--/二维码下载-->

        <we-layer v-model="showRollCallLayer"
                  title="点名"
                  width="480px"
                  height="430px"
                  drag
                  :escCloseable="false"
                  :maskClosable="false"
                  :showFooter="false"
                  @input="isStart = false">
            <div class="common-padding-all common-tac common-margin-auto">
                <div class="tour-details-carousel">
                    <el-carousel trigger="click" height="220px" :autoplay="isStart" :interval="250"
                                 style="width: 220px;margin: 0 auto;">
                        <el-carousel-item v-for="(item, index) in signUser" :key="index">
                            <h3>{{item}}</h3>
                        </el-carousel-item>
                    </el-carousel>
                </div>
                <div style="margin-top: 20px;">
                    <we-button v-if="isStart === false"
                               size="large"
                               type="success"
                               @click="isStart = true">开始
                    </we-button>
                    <we-button v-else
                               size="large"
                               type="danger"
                               @click="isStart = false">停止
                    </we-button>
                </div>
            </div>
        </we-layer>
    </div>
</template>

<script>
    import Global from '../../../mixins/global.js';
    import Link from '../../../mixins/link.js';
    import TeacherInfo from "../../course-center/views/TeacherInfo.vue";
    import StudentManageList from "../../course-center/views/StudentManageList.vue";
    import TaskStatisticsList from "../../course-center/views/TaskStatisticsList.vue";
    import LearningInteract from "../../course-center/views/LearningInteract.vue";
    import FamClassUrls from "../../../urls/fam-class-urls.js";
    import QrCode from 'qrcode';
    import H5ModuleConf from '../../h5/config/module.conf.js';
    import ProjectConf from '../../../../../../config/project.js';
    import FamSignUrls from "../../../urls/fam-sign-urls";

    export default {

        name: "course-details",

        components: {
            LearningInteract,
            TaskStatisticsList,
            StudentManageList,
            TeacherInfo
        },

        mixins: [Global, Link],

        props: {
            courseId: String
        },

        data() {
            return {
                showQrCode: false,
                classLoading: false,
                famClass: {},
                signLoading: false,
                famSignId: '',
                signStatus: '',
                clientPath: '',
                showRollCallLayer: false,
                signRows: [],
                isStart: false
            }
        },

        computed: {
            signUser() {
                return this.signRows.map(row => row.realname);
            }
        },

        watch: {
            courseId: {
                immediate: true,
                handler(val) {
                    this.getRow();
                    this.getRows();
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
                        this.clientPath = `${data.records.staticClientPath}`;
                        this.famClass.filePath = this.isEmpty(this.famClass.location) ? void 0 : `${data.records.staticServerPath}/${data.records.staticPathPrefix}/${this.famClass.location}`;
                        let famSign = data.records.famSign;
                        if (famSign) {
                            this.famSignId = famSign.id;
                            this.signStatus = famSign.status;
                        }
                    })
                    .finally(() => this.classLoading = false);

            },
            getRows() {
                if (this.isEmpty(this.courseId)) {
                    return;
                }
                this.$Ajax.get(FamSignUrls.get.listSignAndSignUserByClassId, [this.courseId])
                    .success(true, data => {
                        this.signRows = data.rows;
                    });
            },
            handleGenQrCode() {
                QrCode.toCanvas(this.$el.querySelector("#canvas"), `${this.clientPath}/${H5ModuleConf.projectName}/${ProjectConf.htmlSubDirectory}/${H5ModuleConf.moduleName}#/sign-in/${this.courseId}`, {
                    width: 350,
                    margin: 1
                }, err => {
                    if (err) {
                        return;
                    }
                    this.showQrCode = true;
                });
            },
            handleDownloadQrCode() {
                let imgData = this.$el.querySelector("#canvas").toDataURL("png");
                let save_link = document.createElementNS('http://www.w3.org/1999/xhtml', 'a');
                save_link.href = imgData;
                save_link.download = this.famClass.title;
                let event = document.createEvent('MouseEvents');
                event.initMouseEvent('click', true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);
                save_link.dispatchEvent(event);
            }
        }
    }
</script>
