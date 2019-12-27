<template>
    <el-form ref="form"
             :model="formData"
             :rules="formRules"
             label-width="70px"
             style="margin: 50px 50px 50px 50px"
    >
        <el-form-item label="用户名" prop="username">
            <el-input v-model="formData.username" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
            <el-input type="password" v-model="formData.password" placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button :loading="submitLoading" type="primary"
                       @click="submit"
                       @keyup.13.native="submit">登录
            </el-button>
        </el-form-item>
    </el-form>
</template>

<script>

    import merge from "../../../../../utils/merge.js";
    import ApiGarUrls from "../../../../gar/urls/api-gar-urls.js";

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
                        {min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '请输入密码', trigger: 'blur'},
                        {min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur'}
                    ]
                }
            };
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
