<template>
  <div>
    <!--查询-->
    <div class="gar-search-bar">
      <!--查询条件-->
      <ul class="gar-search-bar-list">
        <li>
          <em class="list-left list-label-4">关键字：</em>
          <div class="list-right common-w-large">
            <el-input v-model="params.likeText" clearable size="small" class="common-w-all"
                      placeholder="请输入关键字"></el-input>
          </div>
        </li>
        <li class="spacing">
          <we-button :loading="listLoading" size="small"
                     @click="getRows">查询
          </we-button>
        </li>
      </ul>
      <div class="common-float-right">
        <we-button :disabled="listLoading" size="small" type="success" @click="listShowAdd = true">新增</we-button>
      </div>
      <div class="common-clear"></div>
      <!--/查询条件-->
    </div>
    <!--/查询-->

    <!--列表-->
    <div class="gar-list-main">
      <we-skeleton v-model="listSkeleton" animation :paragraph-rows="10"
                   :title-width="'50%'" :paragraph-width="'100%'"
                   @reload="getRows"
      >
        <div v-loading="!listSkeleton && listLoading" class="gar-list-main-inner">
          <template v-if="listRows.length === 0">
            <error-page :image-width="180" :image-height="180"></error-page>
          </template>
          <ul v-else class="gar-list-module">
            <li v-for="(row, index) in listRows">
              <div class="list-module-title">
                <h3 class="title-left">{{row.name}}（{{row.value}}）</h3>
                <div class="title-right">
                  <we-button v-show="index > 0" shape="circle" size="mini" icon-name="arrow-up"
                             :loading="orderLoading"
                             @click="handleSwitchOrder({source: row, sourceIndex: index, target: listRows[index - 1], targetIndex: index - 1})"
                  ></we-button>
                  <we-button v-show="index < listRows.length - 1" shape="circle" size="mini"
                             icon-name="arrow-down"
                             :loading="orderLoading"
                             @click="handleSwitchOrder({source: row, sourceIndex: index, target: listRows[index + 1], targetIndex: index + 1})"
                  ></we-button>
                  <we-button shape="circle" type="primary" size="mini" icon-name="edit"
                             @click="handleListShowEdit({row, $index:index})"></we-button>
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
                        您确定要<span style="color: red">删除</span>该子模块及其<span style="color: red">相关数据</span>吗？
                        <div style="color: red">删除后不可恢复！！！</div>
                      </template>
                    </template>
                    <we-button shape="circle" type="danger" size="mini" icon-name="trash"></we-button>
                  </we-popover>
                </div>
              </div>
              <div class="common-padding-all-small">
                <div class="list-module-row">
                  <em class="list-module-label">模块描述：</em>
                  <div class="list-module-content">{{row.description}}</div>
                </div>
                <div class="list-module-row">
                  <em class="list-module-label">模块状态：</em>
                  <div class="list-module-content">
                    <el-switch v-model="row.status"
                               active-color="#13ce66"
                               inactive-color="#cccccc"
                               :active-value="dict.statusValue.NORMAL"
                               :inactive-value="dict.statusValue.DISABLED"
                               @change="handleChangeStatus({row, $index:index})"
                    >
                    </el-switch>
                  </div>
                </div>
              </div>
            </li>
          </ul>
        </div>
      </we-skeleton>
    </div>
    <!--/列表-->

    <!--新增子模块-->
    <we-layer v-model="listShowAdd"
              title="新增子模块"
              width="700"
              height="530"
              drag
              :maskClosable="false"
              :cancel-button-options="{disabled: listAddLoading}"
              :confirm-button-options="{loading: listAddLoading}"
              @click-confirm-button="handleClickListAdd">
      <div class="common-padding-all" style="padding-bottom: 0;">
        <el-form ref="listAddForm"
                 :model="listAddForm"
                 :rules="listAddFormRules"
                 label-width="150px"
                 size="small">
          <el-form-item label="子模块名称：" prop="name">
            <el-input v-model="listAddForm.name" clearable></el-input>
          </el-form-item>
          <el-form-item label="子模块唯一标识符：" prop="value">
            <el-input v-model="listAddForm.value" clearable></el-input>
          </el-form-item>
          <el-form-item label="子模块描述：" prop="description">
            <el-input type="textarea"
                      :rows="10"
                      resize="none"
                      v-model="listAddForm.description"></el-input>
          </el-form-item>
          <el-form-item label="状态：" prop="status">
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
    <!--/新增子模块-->

    <!--修改子模块-->
    <we-layer v-model="listShowEdit"
              title="修改子模块"
              width="700"
              height="530"
              drag
              :maskClosable="false"
              :cancel-button-options="{disabled: listEditLoading}"
              :confirm-button-options="{loading: listEditLoading}"
              @click-confirm-button="handleClickListEdit">
      <div class="common-padding-all" style="padding-bottom: 0;">
        <el-form ref="listEditForm"
                 :model="listEditForm"
                 :rules="listEditFormRules"
                 label-width="150px"
                 size="small">
          <el-form-item label="子模块名称：" prop="name">
            <el-input v-model="listEditForm.name" disabled clearable></el-input>
          </el-form-item>
          <el-form-item label="子模块唯一标识符：" prop="value">
            <el-input v-model="listEditForm.value" disabled clearable></el-input>
          </el-form-item>
          <el-form-item label="子模块描述：" prop="description">
            <el-input type="textarea"
                      :rows="10"
                      resize="none"
                      v-model="listEditForm.description"></el-input>
          </el-form-item>
          <el-form-item label="子模块状态：" prop="status">
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
    <!--/修改子模块-->

  </div>
</template>

<script>

  import Global from '../../../../mixins/global.js';
  import Link from '../../../../mixins/link.js';
  import List from '../../../../../../assets/template/mixins/list.js';
  import merge from "../../../../../../utils/merge.js";

  import ErrorPage from "../../../../../../components/error-page/src/ErrorPage.vue";
  import ApiGarSubModuleUrls from "../../urls/api-gar-sub-module-urls.js";

  const GlobalData = Global.data();

  const Params = {
    likeText: ''
  };

  const SubModule = {
    name: '',
    value: '',
    description: '',
    status: ''
  };

  export default {

    name: "sub-module-manage",

    mixins: [Global, Link, List],

    components: {ErrorPage},

    props: {
      moduleId: {
        type: String,
        required: true
      }
    },

    data() {
      return {
        params: Object.assign({}, Params),
        orderLoading: false,

        listAddForm: merge({}, SubModule, {status: GlobalData.dict.statusValue.NORMAL}),
        listAddFormRules: {
          name: [{required: true, message: '请输入子模块名称', trigger: 'blur'}],
          value: [
            {required: true, message: '请输入子模块唯一标识符', trigger: 'blur'},
            {min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur'},
            {
              validator: (rule, value, callback) => {
                this.$Ajax.get(ApiGarSubModuleUrls.get.validateValueCanUse, [value], {
                  params: {moduleId: this.moduleId}
                })
                  .success(true, data => {
                    if (data.records.canUse) {
                      callback();
                    } else {
                      callback(new Error('唯一标识符不可用'));
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
        },

        listEditForm: merge({}, SubModule),
        listEditFormRules: {
          name: [{required: true, message: '请输入子模块名称', trigger: 'blur'}],
          value: [
            {required: true, message: '请输入子模块唯一标识符', trigger: 'blur'},
            {min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur'},
            {
              validator: (rule, value, callback) => {
                this.$Ajax.get(ApiGarSubModuleUrls.get.validateValueCanUse, [value], {
                  params: {moduleId: this.moduleId, excludeValues: [this.listEditForm.value].toString()}
                })
                  .success(true, data => {
                    if (data.records.canUse) {
                      callback();
                    } else {
                      callback(new Error('唯一标识符不可用'));
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

    methods: {
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      getRows() {
        this.listLoading = true;
        this.$Ajax.get(ApiGarSubModuleUrls.get.listSubModule, merge({moduleId: this.moduleId}, this.params))
          .success(true, data => {
            this.listRows = this.formatterListRows(data.rows);

            this.listSkeleton = false;
          })
          .notSuccess(() => this.listSkeleton = 500)
          .catch(() => this.listSkeleton = 500)
          .finally(() => this.listLoading = false);
      },
      handleChangeStatus({row, $index}) {
        this.$Ajax.post(ApiGarSubModuleUrls.put.subModuleBySubModuleId, {
          moduleId: this.moduleId,
          subModuleId: row.id,
          status: row.status
        })
          .success(true)
          .notSuccess(() => row.status = row.status === 'ENABLED' ? 'DISABLED' : 'ENABLED');
      },
      handleClickListAdd(e, vm) {
        this.$refs['listAddForm'].validate((valid) => {
          if (valid) {
            this.listAddLoading = true;
            this.$Ajax.post(ApiGarSubModuleUrls.post.subModule, merge({moduleId: this.moduleId}, this.listAddForm))
              .success('创建子模块成功', () => {
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
      handleClickListEdit(e, vm) {
        this.$refs['listEditForm'].validate((valid) => {
          if (valid) {
            this.listEditLoading = true;
            this.$Ajax.put(ApiGarSubModuleUrls.put.subModuleBySubModuleId, merge({
              moduleId: this.moduleId,
              subModuleId: this.listSelectedRow.id
            }, this.listEditForm))
              .success('修改模块成功', () => {
                vm.close();
                merge(this.listSelectedRow, this.listEditForm);
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
        this.$Ajax.delete(ApiGarSubModuleUrls.delete.subModuleBySubModuleId, {
          moduleId: this.moduleId,
          subModuleId: row.id
        })
          .success('删除成功', () => {
            this.listRows.splice($index, 1);
            vm.close();
          })
          .finally(() => row[this.listRowDeleteLoading$] = false);
      },
      handleSwitchOrder({source, sourceIndex, target, targetIndex}) {
        this.orderLoading = true;
        this.$Ajax.put(ApiGarSubModuleUrls.put.switchSubModuleIndexBySubModuleId, {
          moduleId: this.moduleId,
          sourceSubModuleId: source.id,
          targetSubModuleId: target.id
        })
          .success(true, () => {
            this.$set(this.listRows, sourceIndex, target);
            this.$set(this.listRows, targetIndex, source);
          })
          .finally(() => this.orderLoading = false);
      }
    },
    created() {
      this.getRows();
    }
  }
</script>
