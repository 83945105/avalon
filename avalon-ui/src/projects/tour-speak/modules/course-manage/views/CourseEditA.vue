<template>
    <div class="common-bg-default">
        <h1 class="tour-layout-right-title">
            <div class="common-float-left" style="line-height: 30px">编辑课程</div>
            <div class="common-float-right">
                <we-button size="small" type="text" @click="routerToCourseManage">返回</we-button>
            </div>
            <div class="common-clear"></div>
        </h1>

        <div class="avatar-form">
            <el-form :ref="formRefValue$"
                     v-loading="formLoading"
                     :rules="formRules"
                     :model="formData"
                     label-width="120px"
            >
                <el-form-item label="课程名称：" prop="title">
                    <el-input v-model="formData.title" placeholder="请输入课程名称" :max="64"></el-input>
                </el-form-item>
                <el-form-item label="封面图片：" prop="imgUrl">
                    <el-upload class="avatar-uploader"
                               action="https://jsonplaceholder.typicode.com/posts/"
                               :show-file-list="false"
                               accept="image/jpeg,image/png"
                               :before-upload="beforeAvatarUpload">
                        <i v-if="isEmpty(imageUrl)" class="el-icon-plus avatar-uploader-icon"></i>
                        <img v-else :src="imageUrl" class="avatar"/>
                    </el-upload>
                </el-form-item>
                <el-form-item label="报名时间：" prop="signTimeRange">
                    <el-date-picker v-model="signTimeRange"
                                    type="datetimerange"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    range-separator="至"
                                    start-placeholder="开始日期"
                                    end-placeholder="结束日期"
                                    style="width: 420px">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="上课时间：" prop="classTimeRange">
                    <el-date-picker v-model="classTimeRange"
                                    type="datetimerange"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    range-separator="至"
                                    start-placeholder="开始日期"
                                    end-placeholder="结束日期"
                                    style="width: 420px">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="允许报名人数：" prop="allownum">
                    <el-input-number v-model="formData.allownum" :min="1" :max="300" :precision="0"></el-input-number>
                    <span class="common-padding-l-mini">人</span>
                </el-form-item>
                <el-form-item label="上课地点：" prop="address">
                    <el-input v-model="formData.address" placeholder="请输入上课地点"></el-input>
                </el-form-item>
                <el-form-item label="课程介绍：" prop="content">
                    <el-input v-model="formData.content" placeholder="请输入课程介绍" type="textarea" rows="5"
                              resize="none"></el-input>
                </el-form-item>
                <el-form-item>
                    <we-button :loading="formLoading" type="primary" style="width: 90px" @click="submit">保存</we-button>
                    <we-button :loading="formLoading" type="success" style="width: 120px" @click="publish">保存并发布
                    </we-button>
                    <we-button :disabled="formLoading" style="width: 90px" @click="handleResetForm">重置</we-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    import Global from '../../../mixins/global.js';
    import Link from '../../../mixins/link.js';
    import Form from '../../../../../assets/template/mixins/form.js';
    import FamClassUrls from "../../../urls/fam-class-urls.js";
    import merge from "../../../../../utils/merge.js";

    export default {

        name: "course-edit",

        mixins: [Global, Link, Form],

        props: {
            courseId: String
        },

        data() {
            return {
                signTimeRange: [],
                classTimeRange: [],
                formData: {
                    title: '',
                    signbegintime: '',
                    signendtime: '',
                    begintime: '',
                    endtime: '',
                    allownum: 1,
                    address: '',
                    content: '',
                    famClassFileId: ''
                },
                formRules: {
                    title: [
                        {required: true, message: '请输入课程名称', trigger: 'blur'},
                        {min: 1, max: 64, message: '长度在 1 到 64 个字符', trigger: 'blur'}
                    ],
                    signTimeRange: [
                        {
                            validator: (rule, value, callback) => {
                                if (this.isEmpty(this.formData.signbegintime)) {
                                    callback(new Error('请选择报名开始时间'));
                                    return;
                                }
                                if (this.isEmpty(this.formData.signendtime)) {
                                    callback(new Error('请选择报名结束时间'));
                                    return;
                                }
                                callback();
                            }, trigger: 'blur'
                        }
                    ],
                    classTimeRange: [
                        {
                            validator: (rule, value, callback) => {
                                if (this.isEmpty(this.formData.begintime)) {
                                    callback(new Error('请选择上课开始时间'));
                                    return;
                                }
                                if (this.isEmpty(this.formData.endtime)) {
                                    callback(new Error('请选择上课结束时间'));
                                    return;
                                }
                                if (this.formData.begintime.substring(0, 10) !== this.formData.endtime.substring(0, 10)) {
                                    callback(new Error('上课起止时间必须为同一天'));
                                    return;
                                }
                                callback();
                            }, trigger: 'blur'
                        }
                    ],
                    allownum: [
                        {required: true, message: '请输入允许报名人数', trigger: 'blur'}
                    ],
                    address: [
                        {required: true, message: '请输入上课地点', trigger: 'blur'},
                        {min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur'}
                    ],
                    content: [
                        {required: true, message: '请输入课程介绍', trigger: 'blur'},
                        {min: 1, max: 256, message: '长度在 1 到 256 个字符', trigger: 'blur'}
                    ]
                },
                imageUrl: ''
            }
        },

        watch: {
            courseId: {
                immediate: true,
                handler(val) {
                    this.getRow();
                }
            },
            signTimeRange(val) {
                if (this.isEmpty(val)) {
                    merge(this.formData, {
                        signbegintime: '',
                        signendtime: ''
                    });
                    return;
                }
                merge(this.formData, {
                    signbegintime: val[0],
                    signendtime: val[1]
                });
            },
            classTimeRange(val) {
                if (this.isEmpty(val)) {
                    merge(this.formData, {
                        begintime: '',
                        endtime: ''
                    });
                    return;
                }
                merge(this.formData, {
                    begintime: val[0],
                    endtime: val[1]
                });
            }
        },

        methods: {
            getRow() {
                if (this.isEmpty(this.courseId)) {
                    return;
                }
                this.formLoading = true;
                this.$Ajax.get(FamClassUrls.get.myFamClassByFamClassId, [this.courseId])
                    .success(true, data => {
                        this.formData = data.records.famClass;
                        if (!this.isEmpty(this.formData.location)) {
                            this.imageUrl = `${data.records.staticServerPath}/${data.records.staticPathPrefix}/${this.formData.location}`;
                        }
                        this.signTimeRange = [this.formData.signbegintime, this.formData.signendtime];
                        this.classTimeRange = [this.formData.begintime, this.formData.endtime];
                    })
                    .finally(() => this.formLoading = false);
            },
            beforeAvatarUpload(file) {
                const isJPG = file.type === 'image/jpeg' || file.type === 'image/png';
                const isLt2M = file.size / 1024 / 1024 < 2;
                if (!isJPG) {
                    this.$message.error('上传头像图片只能是 jpg、jpeg、png 格式！');
                    return false;
                }
                if (!isLt2M) {
                    this.$message.error('上传头像图片大小不能超过 2MB ！');
                    return false;
                }

                let formData = new FormData();
                formData.append("file", file);
                this.$Ajax.post(FamClassUrls.post.famClassImage, formData)
                    .success('上传图片成功', data => {
                        let {famClassFile, filePath} = data.records;
                        this.imageUrl = filePath;
                        this.formData.famClassFileId = famClassFile.id;
                    });
                return false;
            },
            submit() {
                this.$refs[this.formRefValue$].validate((valid) => {
                    if (valid) {
                        this.formLoading = true;
                        this.$Ajax.put(FamClassUrls.put.famClassByFamClassId, merge({
                            famClassId: this.courseId
                        }, this.formData))
                            .success('修改课程成功', () => {
                                this.handleResetForm();
                                this.routerToCourseManage();
                            })
                            .finally(() => this.formLoading = false);
                    } else {
                        return false;
                    }
                });
            },
            publish() {
                this.$refs[this.formRefValue$].validate((valid) => {
                    if (valid) {
                        new Promise((resolve, reject) => {
                            this.formLoading = true;
                            this.$Ajax.put(FamClassUrls.put.famClassByFamClassId, merge({
                                famClassId: this.courseId
                            }, this.formData))
                                .success(true, data => {
                                    this.handleResetForm();
                                    resolve();
                                })
                                .finally(() => this.formLoading = false);
                        }).then(() => {
                            this.formLoading = true;
                            this.$Ajax.put(FamClassUrls.put.publishFamClassByFamClassId, {
                                famClassId: this.courseId
                            })
                                .success('发布成功', () => {
                                    this.routerToCourseManage();
                                })
                                .finally(() => this.formLoading = false);
                        });
                    } else {
                        return false;
                    }
                });
            },
            handleResetForm() {
                this.formData.famClassFileId = '';
                this.imageUrl = '';
                this.resetForm();
            }
        }
    }
</script>

<style>
    .avatar-form {
        width: 800px;
        margin: 0 auto;
        padding: 30px 0;
    }

    .avatar-form .el-form-item__content {
        line-height: 1.6 !important;
    }

    .avatar-uploader .el-upload {
        border: 1px dashed #d9d9d9;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
    }

    .avatar-uploader .el-upload:hover {
        border-color: #409EFF;
    }

    .avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 300px;
        height: 200px;
        line-height: 200px;
        text-align: center;
    }

    .avatar {
        width: 300px;
        height: 200px;
        display: block;
    }
</style>