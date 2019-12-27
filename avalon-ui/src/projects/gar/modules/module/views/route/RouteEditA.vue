<template>
    <div class="common-padding-all">
        <el-form :ref="formRefValue$"
                 v-loading="formLoading"
                 :rules="formRules"
                 :model="formData"
                 label-width="160px"
                 size="small">
            <el-form-item label="所属子模块：">
                <el-input v-model="formData.subModuleName" disabled></el-input>
            </el-form-item>
            <el-form-item v-if="formData.parentId" label="父级路由名称：">
                <el-input v-model="formData.parentRouteName" disabled clearable></el-input>
            </el-form-item>
            <el-form-item label="路由名称：" prop="name">
                <el-input v-model="formData.name" clearable></el-input>
            </el-form-item>
            <el-form-item label="路由唯一标识符：" prop="value">
                <el-input v-model="formData.value" clearable></el-input>
            </el-form-item>
            <el-form-item>
                <el-tag v-if="propsCount > 0" type="warning">当启用props时,路由地址应包含动态字段,如：/:xx</el-tag>
            </el-form-item>
            <el-form-item label="路由地址：" prop="path">
                <el-input v-model="formData.path" clearable></el-input>
            </el-form-item>
            <el-form-item label="重定向别名：" prop="alias">
                <el-input v-model="formData.alias" clearable></el-input>
            </el-form-item>
            <el-form-item label="是否缓存：" prop="cache">
                <el-switch v-model="formData.cache"
                           :active-value="dict.routeCacheValue.Y"
                           :inactive-value="dict.routeCacheValue.N"
                           active-color="#13ce66"
                           inactive-color="#cccccc">
                </el-switch>
            </el-form-item>
            <el-form-item label="命名视图：">
                <we-button size="small" type="success" @click="routeViews.push({value: '', props: false})">新增视图
                </we-button>
                <template v-for="(routeView, index) in routeViews">
                    <div class="gar-form-edit-one">
                        <div class="edit-one-left common-wp-70">
                            <div class="common-wp-55 common-float-left">
                                <el-input v-model="routeView.value" size="small"></el-input>
                            </div>
                            <div class="common-float-left common-margin-lr-mini">
                                <el-checkbox v-model="routeView.props"
                                             :true-label="dict.routeViewValue.true"
                                             :false-label="dict.routeViewValue.false"
                                             border>
                                    启用props
                                </el-checkbox>
                            </div>
                            <we-button v-show="routeViews.length > 1" size="small" icon-name="trash" type="danger"
                                       class="common-float-left" @click="routeViews.splice(index, 1)"></we-button>
                        </div>
                        <div class="common-clear"></div>
                    </div>
                </template>
            </el-form-item>
            <el-form-item label="路由描述：" prop="description">
                <el-input type="textarea"
                          :rows="10"
                          resize="none"
                          v-model="formData.description"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button :loading="formLoading" type="primary" @click="submit">提交修改</el-button>
                <el-button :disabled="formLoading" @click="resetEditForm">重置</el-button>
                <slot name="button"></slot>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>

    import Global from '../../../../mixins/global.js';

    import Form from '../../../../../../assets/template/mixins/form.js';
    import merge from "../../../../../../utils/merge.js";
    import ApiGarRouteUrls from "../../urls/api-gar-route-urls.js";

    export default {
        name: "route-edit",

        componentName: "RouteEdit",

        mixins: [Global, Form],

        props: {
            moduleId: {
                type: String,
                required: true
            },
            subModuleId: {
                type: String,
                required: true
            },
            data: {
                type: Object,
                default() {
                    return {};
                }
            }
        },

        data() {
            return {
                routeViews: [],
                formData: {},
                formRules: {
                    name: [
                        {required: true, message: '请输入路由名称', trigger: 'blur'},
                        {min: 1, max: 64, message: '长度在 1 到 64 个字符', trigger: 'blur'}
                    ],
                    value: [
                        {required: true, message: '请输入路由唯一标识符', trigger: 'blur'},
                        {min: 1, max: 64, message: '长度在 1 到 64 个字符', trigger: 'blur'},
                        {
                            validator: (rule, value, callback) => {
                                if (!/^[a-zA-Z0-9_]+$/.test(value)) {
                                    callback(new Error('路由唯一标识符格式不正确,只能由字母、数字、下划线组成'));
                                    return;
                                }
                                this.$Ajax.get(ApiGarRouteUrls.get.validateValueCanUseBySubModuleId, [value, this.subModuleId], {
                                    params: {moduleId: this.moduleId, excludeValues: this.data.value}
                                })
                                    .success(true, data => {
                                        if (data.records.canUse) {
                                            callback();
                                        } else {
                                            callback(new Error('路由唯一标识符不可用'));
                                        }
                                    })
                                    .fail(true, data => {
                                        callback(new Error(data.resultInfo.message));
                                    })
                                    .catch(() => {
                                        callback(new Error('发生错误...请稍后再试'));
                                    });
                            }, trigger: 'blur'
                        }
                    ],
                    path: [
                        {required: true, message: '请输入路由地址', trigger: 'blur'},
                        {min: 1, max: 64, message: '长度在 1 到 64 个字符', trigger: 'blur'},
                        {
                            validator: (rule, value, callback) => {
                                if (!/^(([a-zA-Z0-9_\-]*|\/|\/:)([a-zA-Z0-9_\-])+)+$/.test(value)) {
                                    callback(new Error('路由地址格式不正确,应当由数字、字母、下划线、-(横线)、/(斜线)、:(冒号,必须跟在/后面)组成,且/(斜线)与:(冒号)不能连续出现'));
                                    return;
                                }
                                this.$Ajax.get(ApiGarRouteUrls.get.validatePathCanUseBySubModuleId, [this.subModuleId], {
                                    params: {moduleId: this.moduleId, path: value, excludePaths: this.data.path}
                                })
                                    .success(true, data => {
                                        if (data.records.canUse) {
                                            callback();
                                        } else {
                                            callback(new Error('路由地址不可用'));
                                        }
                                    })
                                    .fail(true, data => {
                                        callback(new Error(data.resultInfo.message));
                                    })
                                    .catch(() => {
                                        callback(new Error('发生错误...请稍后再试'));
                                    });
                            }, trigger: 'blur'
                        }
                    ],
                    alias: [
                        {min: 1, max: 64, message: '长度在 1 到 64 个字符', trigger: 'blur'},
                        {
                            validator: (rule, value, callback) => {
                                if (!value) {
                                    callback();
                                    return;
                                }
                                this.$Ajax.get(ApiGarRouteUrls.get.validateAliasCanUseBySubModuleId, [value, this.subModuleId], {
                                    params: {moduleId: this.moduleId, excludeAliases: this.data.alias}
                                })
                                    .success(true, data => {
                                        if (data.records.canUse) {
                                            callback();
                                        } else {
                                            callback(new Error('路由重定向别名不可用'));
                                        }
                                    })
                                    .fail(true, data => {
                                        callback(new Error(data.resultInfo.message));
                                    })
                                    .catch(() => {
                                        callback(new Error('发生错误...请稍后再试'));
                                    });
                            }, trigger: 'blur'
                        }
                    ]
                }
            }
        },

        computed: {
            // 启用props的routeView数量
            propsCount() {
                return this.routeViews.filter(row => row.props === this.dict.routeViewValue.true).length;
            }
        },

        watch: {
            data(val) {
                this.resetEditForm();
            }
        },

        methods: {
            resetEditForm() {
                let routeViews = [];
                if (this.data.routeViews) {
                    this.data.routeViews.forEach(routeView => {
                        routeViews.push(merge({}, routeView));
                    });
                }
                this.routeViews = routeViews;
                this.formData = merge({}, this.data);
                this.formData.routeViews = routeViews;
            },
            submit() {
                this.$refs[this.formRefValue$].validate((valid) => {
                    if (valid) {
                        //校验路由视图
                        for (let i in this.routeViews) {
                            if (!this.routeViews[i].value) {
                                this.$message('命名视图不能为空');
                                return;
                            }
                            for (let j in this.routeViews) {
                                if (this.routeViews[i] !== this.routeViews[j] && this.routeViews[i].value === this.routeViews[j].value) {
                                    this.$message('命名视图不能重复');
                                    return;
                                }
                            }
                        }
                        this.formLoading = true;
                        this.$Ajax.put(ApiGarRouteUrls.put.routeByRouteId, merge({
                            moduleId: this.moduleId,
                            subModuleId: this.subModuleId,
                            routeId: this.formData.id,
                            routeViewJson: JSON.stringify(this.routeViews)
                        }, this.formData))
                            .success('修改路由成功', () => {
                                this.$emit('submit-success', this.formData, this);
                            })
                            .finally(() => this.formLoading = false);
                    } else {
                        return false;
                    }
                });
            }
        },
        created() {
            this.resetEditForm();
        }
    }
</script>
