<template>
  <div class="common-padding-all" style="padding-bottom: 0;">
    <el-form :ref="formRefValue$"
             v-loading="formLoading"
             :rules="formRules"
             :model="formData"
             label-width="135px"
             size="small">
      <el-form-item label="所属子模块：" prop="subModuleId">
        <sub-module-select v-model="formData.subModuleId" :module-id="moduleId"></sub-module-select>
      </el-form-item>
      <el-form-item label="模板名称：" prop="name">
        <el-input v-model="formData.name" clearable></el-input>
      </el-form-item>
      <el-form-item label="模板唯一标识符：" prop="value">
        <el-input v-model="formData.value" clearable></el-input>
      </el-form-item>
      <el-form-item label="模板类型：" prop="type">
        <view-type-select v-model="formData.type" init-selected-first></view-type-select>
      </el-form-item>
      <el-form-item label="模板描述：" prop="description">
        <el-input type="textarea"
                  :rows="10"
                  resize="none"
                  v-model="formData.description"></el-input>
      </el-form-item>
      <el-form-item label="立即使用：" prop="status">
        <el-switch v-model="formData.status"
                   active-color="#13ce66"
                   inactive-color="#cccccc"
                   :active-value="dict.statusValue.NORMAL"
                   :inactive-value="dict.statusValue.DISABLED"
        ></el-switch>
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
  import SubModuleSelect from "../../components/sub-module-select/src/SubModuleSelect.vue";
  import ViewTypeSelect from "../../components/sub-module-select/src/ViewTypeSelect.vue";
  import ApiGarTemplateUrls from "../../urls/api-gar-template-urls.js";
  import merge from "../../../../../../utils/merge.js";

  export default {

    name: "template-edit",

    components: {ViewTypeSelect, SubModuleSelect},

    mixins: [Global, Form],

    props: {
      moduleId: {
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
        formData: merge({}, this.data),
        formRules: {
          subModuleId: [
            {required: true, message: '请选择所属子模块', trigger: 'change'}
          ],
          name: [
            {required: true, message: '请输入模板名称', trigger: 'blur'},
            {min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur'}
          ],
          value: [
            {required: true, message: '请输入模板唯一标识符', trigger: 'blur'},
            {min: 1, max: 64, message: '长度在 1 到 64 个字符', trigger: 'blur'},
            {
              validator: (rule, value, callback) => {
                if (!this.formData.subModuleId) {
                  callback(new Error('请选择所属子模块'));
                  return;
                }
                this.$Ajax.get(ApiGarTemplateUrls.get.validateValueCanUseBySubModuleId, [value, this.formData.subModuleId], {
                  params: {moduleId: this.moduleId, excludeValues: this.data.value}
                })
                  .success(true, data => {
                    if (data.records.canUse) {
                      callback();
                    } else {
                      callback(new Error('模板唯一标识符不可用'));
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
          type: [
            {required: true, message: '请选择模板类型', trigger: 'change'}
          ]
        }
      };
    },

    watch: {
      data(val) {
        this.resetEditForm();
      }
    },

    methods: {
      resetEditForm() {
        this.formData = merge({}, this.data);
      },
      submit() {
        this.$refs[this.formRefValue$].validate((valid) => {
          if (valid) {
            this.formLoading = true;
            this.$Ajax.put(ApiGarTemplateUrls.put.templateByTemplateId, merge({
              moduleId: this.moduleId,
              templateId: this.formData.id
            }, this.formData))
              .success('修改模板成功', () => {
                this.$emit('submit-success', this.formData, this);
              })
              .finally(() => this.formLoading = false);
          } else {
            return false;
          }
        });
      }
    }
  }
</script>
