<template>
    <div>
        <div class="layout-header">课程签到</div>
        <div class="layout-content">
            <div class="course-info">
                <img v-if="isEmpty(famClass.filePath)"
                     class="course-img"
                     src="../../../../../../public/tour-speak/images/course_default_img.png"/>
                <img v-else class="course-img" :src="famClass.filePath"/>
                <h3 class="course-title common-wordwrap">{{famClass.title}}</h3>
            </div>
            <!--课程已结束-->
            <div v-if="famClass.realTimeClassStatus === dict.classStatusValue.CLASS_OVER"
                 class="course-prompt complete">
                <h3>已结束</h3>
                <div>课程已经结束，请参加其他课程吧！</div>
            </div>
            <!--/课程已结束-->

            <div v-else class="course-login">
                <div class="common-mt">
                    <input v-model="realname" type="text" class="common-text common-w-all"
                           placeholder="请填写个人姓名"/>
                </div>
                <div class="common-mt">
                    <input v-model="card" type="text" class="common-text common-w-all" placeholder="请填写身份证号"/>
                </div>
                <div class="common-mt">
                    <button class="common-btn common-btn-primary common-w-all" @click="handleSign">签到</button>
                </div>
                <div class="common-validate-info">{{errorMessage}}</div>
            </div>

            <!--课程未开始-->
<!--            <div v-else class="course-prompt not-started">
                <h3 @click="getRow">未开始</h3>
                <div>课程还未开始，请在开课当天进行签到！</div>
            </div>-->
            <!--/课程未开始-->
        </div>
    </div>
</template>

<script>
    import Global from '../../../mixins/global.js';
    import Link from '../../../mixins/link.js';
    import H5Urls from "../../../urls/h5-urls.js";
    import ApiGarUrls from "../../../../gar/urls/api-gar-urls.js";

    export default {

        name: "sign-in",

        mixins: [Global, Link],

        props: {
            courseId: {//课程ID
                type: String,
                required: true
            }
        },

        data() {
            return {
                loading: false,
                realname: '',
                card: '',
                errorMessage: '',
                famClass: {}
            };
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
                this.$Ajax.get(H5Urls.get.famClassByClassId, [this.courseId])
                    .success(true, data => {
                        this.famClass = data.records.famClass;
                        if (!this.isEmpty(this.famClass.location)) {
                            this.famClass.filePath = `${data.records.staticServerPath}/${data.records.staticPathPrefix}/${this.famClass.location}`;
                        } else {
                            this.famClass.filePath = void 0;
                        }
                    });
            },
            handleSign() {
                if (this.isEmpty(this.realname)) {
                    this.errorMessage = '请填写个人姓名';
                    return;
                }
                if (this.isEmpty(this.card)) {
                    this.errorMessage = '请填写身份证号';
                    return;
                }
                this.$Ajax.post(H5Urls.post.signClassByClassId, {
                    classId: this.courseId,
                    realname: this.realname,
                    card: this.card
                })
                    .success(true, data => {
                        this.realname = '';
                        this.card = '';
                        this.errorMessage = '';
                        this.$Ajax.post(ApiGarUrls.post.login, {
                            username: data.records.loginName,
                            password: data.records.loginName
                        })
                            .success(true, () => {
                                this.routerToCourseInteract({courseId: this.courseId});
                            })
                            .notSuccess(true, () => {
                                this.errorMessage = '发生错误...';
                            });
                    })
                    .fail(true, data => {
                        this.errorMessage = data.resultInfo.message;
                    })
                    .error(true, () => {
                        this.errorMessage = '发生错误...';
                    });
            }
        }
    }
</script>
