<template>
    <div class="common-full common-scroll-v">
        <div class="module-list-main">
            <we-skeleton v-model="listSkeleton" animation :paragraph-rows="10"
                         :title-width="'50%'" :paragraph-width="'100%'"
                         @reload="getRows"
            >
                <ul class="module-list">
                    <li v-for="(row, index) in listRows">
                        <!--工具条-->
                        <div class="module-tools">
                            <div class="tools-left">
                                <i v-if="row.serviceId !== 'gar-service'" class="el-icon-edit"
                                   @click="handleListShowEdit({row, $index:index})"></i>
                                <i v-if="false" class="el-icon-setting"></i>
                            </div>
                            <div v-if="row.serviceId !== 'gar-service'" class="tools-right">
                                <we-popover title="操作" type="confirm"
                                            placement="top-start"
                                            :manual="row[listRowDeleteLoading$]"
                                            confirm-button-text="删了吧"
                                            :cancel-button-text="row[listRowDeleteLoading$] ? '请稍等' : '我再想想'"
                                            :cancel-button-options="{disabled: row[listRowDeleteLoading$]}"
                                            :confirm-button-options="{loading: row[listRowDeleteLoading$]}"
                                            @click-cancel-button="(e, vm) => vm.close()"
                                            @click-confirm-button="(e, vm) => handleClickDeleteRow({row, $index:index, event:e, vm})">
                                    <template slot="content">
                                        <template v-if="row[listRowDeleteLoading$]">
                                            <span style="color: red">删除中...</span>删除完成后将自动关闭气泡
                                        </template>
                                        <template v-else>
                                            您确定要<span style="color: red">删除</span>该模块及其<span
                                                style="color: red">相关数据</span>吗？
                                            <div style="color: red">删除后不可恢复！！！</div>
                                        </template>
                                    </template>
                                    <i class="el-icon-delete"></i>
                                </we-popover>
                            </div>
                        </div>
                        <!--/工具条-->
                        <!--列表内容-->
                        <div class="module-one" @click="openResourceManage(row.id)">
                            <ul class="module-info-form">
                                <li>
                                    <em class="info-title tar">模块ID：</em>
                                    <div class="info-content ellipsis">{{row.id}}</div>
                                </li>
                                <li>
                                    <em class="info-title tar">服务ID：</em>
                                    <div class="info-content ellipsis">{{row.serviceId}}</div>
                                </li>
                                <li>
                                    <em class="info-title tar">服务名：</em>
                                    <div class="info-content ellipsis">{{row.serviceName}}</div>
                                </li>
                                <li>
                                    <em class="info-title tar">路由地址：</em>
                                    <div class="info-content ellipsis">{{row.path}}</div>
                                </li>
                                <li>
                                    <em class="info-title tar">状态：</em>
                                    <div class="info-content ellipsis">
                                        <em v-show="row.status === dict.statusValue.NORMAL" class="fc-normal">正常</em>
                                        <em v-show="row.status === dict.statusValue.DISABLED" class="fc-disable">禁用</em>
                                    </div>
                                </li>
                            </ul>
                        </div>
                        <!--/列表内容-->
                    </li>
                    <li @click="listShowAdd = true">
                        <div class="module-one">
                            <i class="module-add-btn el-icon-plus"></i>
                        </div>
                    </li>
                </ul>
            </we-skeleton>
            <!--新建-->
            <we-layer v-model="listShowAdd"
                      title="新建模块"
                      :width="500"
                      :height="460"
                      position="center"
                      drag
                      :confirm-button-options="{loading: listAddLoading}"
                      @click-confirm-button="handleClickListAdd"
            >
                <div class="form-padding">
                    <el-form label-width="90px"
                             ref="listAddForm"
                             :model="listAddForm"
                             :rules="listAddFormRules">
                        <el-form-item label="模块ID:" prop="id">
                            <el-input label="right" v-model="listAddForm.id" style="width: 70%"></el-input>
                        </el-form-item>
                        <el-form-item label="服务ID:" prop="serviceId">
                            <el-input label="right" v-model="listAddForm.id" disabled style="width: 70%"></el-input>
                            <el-input label="right" v-model="serviceIdSuffix" disabled style="width: 24%"></el-input>
                        </el-form-item>
                        <el-form-item label="服务名称:" prop="serviceName">
                            <el-input label="right" v-model="listAddForm.serviceName" style="width: 95%"></el-input>
                        </el-form-item>
                        <el-form-item label="根路径:" prop="path">
                            <el-input label="right" v-model="pathPrefix" disabled style="width: 24%"></el-input>
                            <el-input label="right" v-model="listAddForm.id" disabled style="width: 45%"></el-input>
                            <el-input label="right" v-model="pathSuffix" disabled style="width: 24%"></el-input>
                        </el-form-item>
                        <el-form-item label="状态:">
                            <el-switch v-model="listAddForm.status"
                                       active-color="#13ce66"
                                       inactive-color="#cccccc"
                                       :active-value="dict.statusValue.NORMAL"
                                       :inactive-value="dict.statusValue.DISABLED">
                            </el-switch>
                        </el-form-item>
                    </el-form>
                </div>
            </we-layer>
            <!--/新建-->

            <!--编辑-->
            <we-layer v-model="listShowEdit"
                      title="编辑模块"
                      :width="500"
                      :height="460"
                      position="center"
                      drag
                      :confirm-button-options="{loading: listEditLoading}"
                      @click-confirm-button="handleClickListEdit"
            >
                <div class="form-padding">
                    <el-form label-width="90px"
                             ref="listEditForm"
                             :model="listEditForm"
                             :rules="listEditFormRules">
                        <el-form-item label="模块ID:" prop="id">
                            <el-input label="right" v-model="listEditForm.id" disabled style="width: 70%"></el-input>
                        </el-form-item>
                        <el-form-item label="服务ID:" prop="serviceId">
                            <el-input label="right" v-model="listEditForm.id" disabled style="width: 70%"></el-input>
                            <el-input label="right" v-model="serviceIdSuffix" disabled style="width: 24%"></el-input>
                        </el-form-item>
                        <el-form-item label="服务名称:" prop="serviceName">
                            <el-input label="right" v-model="listEditForm.serviceName" style="width: 95%"></el-input>
                        </el-form-item>
                        <el-form-item label="根路径:" prop="path">
                            <el-input label="right" v-model="pathPrefix" disabled style="width: 24%"></el-input>
                            <el-input label="right" v-model="listEditForm.id" disabled style="width: 45%"></el-input>
                            <el-input label="right" v-model="pathSuffix" disabled style="width: 24%"></el-input>
                        </el-form-item>
                        <el-form-item label="状态:">
                            <el-switch v-model="listEditForm.status"
                                       active-color="#13ce66"
                                       inactive-color="#cccccc"
                                       :active-value="dict.statusValue.NORMAL"
                                       :inactive-value="dict.statusValue.DISABLED">
                            </el-switch>
                        </el-form-item>
                    </el-form>
                </div>
            </we-layer>
            <!--/编辑-->
        </div>
    </div>
</template>

<script>
    import Global from '../../../../mixins/global.js';
    import Link from '../../../../mixins/link.js';
    import List from '../../../../../../assets/template/mixins/list.js';
    import ApiGarModuleUrls from "../../urls/api-gar-module-urls.js";
    import merge from "../../../../../../utils/merge.js";

    const GlobalData = Global.data();

    const SERVICE_ID_SUFFIX = '-service';
    const PATH_PREFIX = '/route-';
    const PATH_SUFFIX = '/**';
    const Module = {
        id: '',
        serviceName: '',
        status: ''
    };

    export default {

        name: "module-manage",

        mixins: [Global, Link, List],

        data() {
            return {
                serviceIdSuffix: SERVICE_ID_SUFFIX,
                pathPrefix: PATH_PREFIX,
                pathSuffix: PATH_SUFFIX,
                listAddForm: merge({}, Module, {status: GlobalData.dict.statusValue.NORMAL}),
                listAddFormRules: {
                    id: [
                        {required: true, message: '请输入模块ID', trigger: 'blur'},
                        {min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur'},
                        {
                            validator: (rule, value, callback) => {
                                if (!/^[a-zA-Z]+(-[a-zA-Z]+|[a-zA-Z]*)$/.test(value)) {
                                    callback(new Error('模块ID格式不正确'));
                                    return;
                                }
                                this.$Ajax.get(ApiGarModuleUrls.get.validateModuleIdCanUse, [value])
                                    .success(true, data => {
                                        if (data.records.canUse) {
                                            callback();
                                        } else {
                                            callback(new Error('模块ID不可用'));
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
                    serviceName: [{required: true, message: '请输入模块名称', trigger: 'blur'}]
                },

                listEditForm: merge({}, Module, {moduleId: ''}),
                listEditFormRules: {
                    id: [
                        {required: true, message: '请输入模块ID', trigger: 'blur'},
                        {min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur'},
                        {
                            validator: (rule, value, callback) => {
                                if (!/^[a-zA-Z]+(-[a-zA-Z]+|[a-zA-Z]*)$/.test(value)) {
                                    callback(new Error('模块ID格式不正确'));
                                    return;
                                }
                                this.$Ajax.get(ApiGarModuleUrls.get.validateModuleIdCanUse, [value], {
                                    params: {
                                        excludeModuleIds: [this.listEditForm.moduleId].toString()
                                    }
                                })
                                    .success(true, data => {
                                        if (data.records.canUse) {
                                            callback();
                                        } else {
                                            callback(new Error('模块ID不可用'));
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
                    serviceName: [{required: true, message: '请输入模块名称', trigger: 'blur'}]
                }
            }
        },

        methods: {
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            getRows() {
                this.listLoading = true;
                this.$Ajax.get(ApiGarModuleUrls.get.listModule)
                    .success(true, data => {
                        this.listRows = this.formatterListRows(data.rows);

                        this.listSkeleton = false;
                    })
                    .notSuccess(() => this.listSkeleton = 500)
                    .catch(() => this.listSkeleton = 500)
                    .finally(() => this.listLoading = false);
            },
            handleClickListAdd(e, vm) {
                this.$refs['listAddForm'].validate((valid) => {
                    if (valid) {
                        let params = merge({
                            path: `${this.pathPrefix}${this.listAddForm.id}${this.pathSuffix}`,
                            serviceId: `${this.listAddForm.id}${this.serviceIdSuffix}`
                        }, this.listAddForm);
                        this.listAddLoading = true;
                        this.$Ajax.post(ApiGarModuleUrls.post.module, params)
                            .success('创建模块成功', () => {
                                vm.close();
                                this.resetForm('listAddForm');
                                this.getRows();
                            })
                            .finally(() => this.listAddLoading = false);
                    } else {
                        return false;
                    }
                });
            },
            handleListShowEdit({row, $index}) {
                this.listSelectedRow = row;
                this.listSelectedRowIndex = $index;
                merge(this.listEditForm, row, {moduleId: row.id});
                this.listShowEdit = true;
            },
            handleClickListEdit(e, vm) {
                this.$refs['listEditForm'].validate((valid) => {
                    if (valid) {
                        let params = merge({
                            moduleId: this.listSelectedRow.id,
                            path: `${this.pathPrefix}${this.listEditForm.id}${this.pathSuffix}`,
                            serviceId: `${this.listEditForm.id}${this.serviceIdSuffix}`
                        }, this.listEditForm);
                        this.listEditLoading = true;
                        this.$Ajax.put(ApiGarModuleUrls.put.moduleByModuleId, params)
                            .success('修改模块成功', () => {
                                vm.close();
                                merge(this.listSelectedRow, params);
                                this.resetForm('listEditForm');
                            })
                            .finally(() => this.listEditLoading = false);
                    } else {
                        return false;
                    }
                });
            },
            handleClickDeleteRow({row, $index, event, vm}) {
                row[this.listRowDeleteLoading$] = true;
                this.$Ajax.delete(ApiGarModuleUrls.delete.moduleByModuleId, {
                    moduleId: row.id
                })
                    .success('删除成功', () => {
                        this.listRows.splice($index, 1);
                        vm.close();
                    })
                    .finally(() => row[this.listRowDeleteLoading$] = false);
            }
        },
        created() {
            this.getRows();
        }
    }
</script>

<style scoped>
    .fc-normal {
        color: #009900;
    }

    .fc-disable {
        color: #ff0000;
    }

    .ellipsis {
        white-space: nowrap;
        text-overflow: ellipsis;
        overflow: hidden;
    }

    .form-padding {
        padding: 25px 15px 15px;
    }

    .module-list-main {
        width: 1280px;
        margin: 0 auto;
    }

    .module-list > li {
        margin: 30px 1% 0;
        width: 18%;
        float: left;
        padding-top: 17%;
        position: relative;
        box-sizing: border-box;
        -moz-box-sizing: border-box;
        -webkit-box-sizing: border-box;
        transition: box-shadow .2s;
        -moz-transition: box-shadow .2s;
        -webkit-transition: box-shadow .2s;
        -o-transition: box-shadow .2s;
        cursor: pointer;
        border: 1px solid #dddddd;
        background-color: #FCFCFC;
    }

    .module-list > li:hover {
        box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
    }

    .module-list > li:hover > .module-tools {
        display: block;
    }

    .module-one {
        position: absolute;
        top: 26px;
        bottom: 26px;
        left: 28px;
        right: 28px;
    }

    .module-tools {
        display: none;
        position: absolute;
        top: 3px;
        left: 3px;
        right: 3px;
        z-index: 2;
        color: #FFFFFF;
    }

    .module-tools i {
        cursor: pointer;
        font-size: 16px;
        width: 26px;
        height: 26px;
        line-height: 26px;
        text-align: center;
        display: inline-block;
        background-color: rgba(0, 0, 0, 0.45);
    }

    .module-tools i:hover {
        background-color: rgba(0, 0, 0, 0.6);
        color: #ffff00;
    }

    .module-tools .tools-left {
        float: left;
    }

    .module-tools .tools-right {
        float: right;
    }

    .module-one .module-add-btn {
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translateY(-50%) translateX(-50%);
        font-size: 60px;
        margin: 0 auto;
        color: #999999;
    }

    .module-info-form li {
        height: 32px;
        line-height: 32px;
    }

    .module-info-form .info-title {
        float: left;
        width: 70px;
        color: #999999;
    }

    .module-info-form .info-title.tar {
        text-align: right;
    }

    .module-info-form .info-content {
        overflow: hidden;
        color: #333333;
    }

</style>
