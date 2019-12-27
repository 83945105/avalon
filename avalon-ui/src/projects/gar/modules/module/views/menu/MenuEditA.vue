<template>
  <div class="common-padding-all">
    <div class="gar-prompt-text">
      <span class="prompt-title">提示内容</span>
    </div>
    <div class="common-margin-t">
      <el-form :ref="formRefValue$"
               v-loading="formLoading"
               :rules="formRules"
               :model="formData"
               label-width="180px"
               size="small">
        <el-form-item label="所属子模块：">
          <el-input v-model="formData.subModuleName" disabled></el-input>
        </el-form-item>
        <el-form-item v-if="menuGroupId" label="菜单组名称：">
          <el-input v-model="menuGroup.name" disabled clearable></el-input>
        </el-form-item>
        <el-form-item v-if="formData.parentId" label="父级菜单名称：">
          <el-input v-model="formData.parentMenuName" disabled clearable></el-input>
        </el-form-item>
        <el-form-item label="菜单名称：" prop="name">
          <el-input v-model="formData.name" clearable></el-input>
        </el-form-item>
        <el-form-item label="菜单唯一标识符：" prop="value">
          <el-input v-model="formData.value" clearable></el-input>
        </el-form-item>
        <el-form-item label="图标名称：" prop="iconName">
          <el-input v-model="formData.iconName" clearable></el-input>
        </el-form-item>
        <el-form-item label="是否使用选项卡：" prop="useTab">
          <el-switch v-model="formData.useTab"
                     :active-value="dict.menuUseTabValue.true"
                     :inactive-value="dict.menuUseTabValue.false"
                     active-color="#13ce66"
                     inactive-color="#cccccc">
          </el-switch>
        </el-form-item>
        <template v-if="formData.useTab === dict.menuUseTabValue.true">
          <el-form-item label="是否缓存选项卡：" prop="cacheInTab">
            <el-switch v-model="formData.cacheInTab"
                       disabled
                       active-value="true"
                       inactive-value="false"
                       active-color="#13ce66"
                       inactive-color="#cccccc">
            </el-switch>
          </el-form-item>
          <el-form-item label="是否在选项卡中默认打开：" prop="initOpenInTab">
            <el-switch v-model="formData.initOpenInTab"
                       active-value="true"
                       inactive-value="false"
                       active-color="#13ce66"
                       inactive-color="#cccccc">
            </el-switch>
          </el-form-item>
          <el-form-item label="是否可以关闭选项卡：" prop="closableInTab">
            <el-switch v-model="formData.closableInTab"
                       active-value="true"
                       inactive-value="false"
                       active-color="#13ce66"
                       inactive-color="#cccccc">
            </el-switch>
          </el-form-item>
        </template>
        <el-form-item label="菜单描述：" prop="description">
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
  </div>
</template>

<script>

  import Global from '../../../../mixins/global.js';

  import Form from '../../../../../../assets/template/mixins/form.js';
  import merge from "../../../../../../utils/merge.js";
  import ApiGarMenuUrls from "../../urls/api-gar-menu-urls.js";
  import ApiGarMenuGroupUrls from "../../urls/api-gar-menu-group-urls.js";

  export default {

    name: "menu-edit",

    componentName: "MenuEdit",

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
      menuGroupId: {
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
        menuGroup: {},
        formData: {},
        formRules: {
          name: [
            {required: true, message: '请输入菜单名称', trigger: 'blur'},
            {min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur'}
          ],
          value: [
            {required: true, message: '请输入菜单唯一标识符', trigger: 'blur'},
            {min: 1, max: 64, message: '长度在 1 到 64 个字符', trigger: 'blur'},
            {
              validator: (rule, value, callback) => {
                if (!/^[a-zA-Z0-9_]+$/.test(value)) {
                  callback(new Error('菜单唯一标识符格式不正确,只能由字母、数字、下划线组成'));
                  return;
                }
                this.$Ajax.get(ApiGarMenuUrls.get.validateValueCanUseBySubModuleId, [value, this.subModuleId], {
                  params: {moduleId: this.moduleId, excludeValues: this.data.value}
                })
                  .success(true, data => {
                    if (data.records.canUse) {
                      callback();
                    } else {
                      callback(new Error('菜单唯一标识符不可用'));
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

    watch: {
      data(val) {
        this.resetEditForm();
      },
      menuGroupId(val) {
        this.getMenuGroup();
      }
    },

    methods: {
      getMenuGroup() {
        if (this.menuGroupId) {
          this.$Ajax.get(ApiGarMenuGroupUrls.get.menuGroupByMenuGroupId, [this.menuGroupId], {
            params: {moduleId: this.moduleId}
          })
            .success(true, data => {
              this.menuGroup = data.records.menuGroup;
            });
        }
      },
      resetEditForm() {
        this.formData = merge({}, this.data);
      },
      submit() {
        this.$refs[this.formRefValue$].validate((valid) => {
          if (valid) {
            this.formLoading = true;
            this.$Ajax.put(ApiGarMenuUrls.put.menuByMenuId, merge({
              moduleId: this.moduleId,
              subModuleId: this.subModuleId,
              menuId: this.formData.id
            }, this.formData))
              .success('修改菜单成功', () => {
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
      this.getMenuGroup();
      this.resetEditForm();
    }
  }
</script>
