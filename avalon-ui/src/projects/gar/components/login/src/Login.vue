<template>
    <div class="login-wrap">
        <div class="login-header">
            <img class="login-header-logo" src="../../../../../../public/gar/images/login/logo.png"/>
            <div class="login-header-title">权限管理系统</div>
        </div>
        <div class="login-main">
            <div class="login-main-inner">
                <div class="login-main-bg-img"></div>
                <!--登录框-->
                <div class="login-main-form-position">
                    <div class="login-main-img"></div>
                    <div class="login-main-form">
                        <h3 class="login-main-form-title">用户登录</h3>
                        <el-form ref="form"
                                 :model="formData"
                                 :rules="formRules"
                                 class="login-main-form-inner">
                            <el-form-item label="" prop="username">
                                <div class="form-inner-one">
                                    <em><img src="../../../../../../public/gar/images/login/username.png"/></em>
                                    <el-input v-model="formData.username" placeholder="请输入用户名"></el-input>
                                </div>
                            </el-form-item>
                            <el-form-item label="" prop="password">
                                <div class="form-inner-one">
                                    <em><img src="../../../../../../public/gar/images/login/password.png"/></em>
                                    <el-input type="password" v-model="formData.password"
                                              placeholder="请输入密码"></el-input>
                                </div>
                            </el-form-item>
                            <el-form-item>
                                <el-button :loading="submitLoading" type="primary" @click="submit" style="width: 100%">
                                    登录
                                </el-button>
                            </el-form-item>
                        </el-form>
                    </div>
                </div>
                <!--/登录框-->
            </div>
        </div>
    </div>
</template>

<script>

    import merge from "../../../../../utils/merge.js";
    import ApiGarUrls from "../../../urls/api-gar-urls.js";

    export default {
        name: "login",

        props: {
            value: Boolean
        },

        data() {
            return {
                submitLoading: false,
                formData: {
                    username: '',
                    password: ''
                },
                formRules: {
                    username: [
                        {required: true, message: '请输入用户名', trigger: 'blur'},
                        {min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '请输入密码', trigger: 'blur'},
                        {min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur'}
                    ]
                }
            };
        },

        watch: {
            formData: {
                handler(val) {
                    window.addEventListener('keyup', this.onKeyUpEvent);
                },
                deep: true
            }
        },

        methods: {
            onKeyUpEvent(e) {
                if (e.keyCode === 13 || e.which === 13 || e.key === 'Enter') {
                    this.submit(e);
                }
            },
            submit(e) {
                this.$refs.form.validate((valid) => {
                    if (valid) {
                        this.submitLoading = true;
                        this.$Ajax.post(ApiGarUrls.post.login, merge({}, this.formData))
                            .success(true, () => {
                                window.removeEventListener('keyup', this.onKeyUpEvent);
                                this.$emit('login-success');
                            })
                            .finally(() => this.submitLoading = false);

                    } else {
                        return false;
                    }
                });
            }
        }

    }
</script>
<style lang="less" type="text/less">
    @login-css-prefix: login;
    .@{login-css-prefix} {
        &-wrap {
            position: absolute;
            top: 0;
            right: 0;
            bottom: 0;
            left: 0;
        }

        &-header {
            height: 60px;
            padding: 15px 20px;

            &-logo {
                float: left;
                display: block;
                height: 40px;
                margin: 10px 20px 0 0;
            }

            &-title {
                float: left;
                font-size: 30px;
                line-height: 60px;
                color: #333333;
            }
        }

        &-main {
            position: relative;
            height: calc(100% - 90px - 90px);
            background-image: linear-gradient(45deg, #5141F4 20%, #7B94FF 80%);

            &-inner {

            }

            &-bg-img {
                position: absolute;
                top: 0;
                right: 0;
                bottom: 0;
                left: 0;
                z-index: 0;
                background: url("../../../../../../public/gar/images/login/bg.png");
                background-size: 100% 100%;
            }

            &-img {
                float: left;
                height: 476px;
                width: 524px;
                margin-top: 30px;
                background: url("../../../../../../public/gar/images/login/login_img.png");
                background-size: 100% 100%;
            }

            &-form {
                position: relative;
                z-index: 10;
                float: right;
                height: 426px;
                width: 322px;
                padding: 70px 70px 0;
                background: url("../../../../../../public/gar/images/login/login_bg.png");

                &-position {
                    position: absolute;
                    top: 50%;
                    left: 50%;
                    width: 1260px;
                    height: 496px;
                    margin-left: -630px;
                    margin-top: -248px;
                }

                &-title {
                    font-size: 26px;
                    text-align: center;
                    padding-bottom: 15px;
                    color: #333333;
                }

                &-inner {
                    width: 100%;
                    margin: 0 auto;

                    & .el-input__inner {
                        padding-left: 40px;
                    }

                    & .form-inner-one {
                        position: relative;
                        margin-top: 10px;

                        & em {
                            position: absolute;
                            top: 4px;
                            left: 4px;
                            height: 32px;
                            display: block;
                            z-index: 11;
                        }
                    }
                }
            }
        }

        &-footer {
            line-height: 90px;
            text-align: center;
            color: #999999;
        }
    }
</style>