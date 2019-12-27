<template>
  <div class="common-padding-all">
    <el-form :ref="formRefValue$"
             v-loading="formLoading"
             :rules="formRules"
             :model="formData"
             label-width="120px"
             size="small">
      <el-form-item label="所属子模块：">
        <el-input v-model="formData.subModuleName" disabled></el-input>
      </el-form-item>
      <el-form-item label="菜单组名称：" prop="name">
        <el-input v-model="formData.name" clearable></el-input>
      </el-form-item>
      <el-form-item label="菜单组类型：" prop="type">
        <el-select v-model="formData.type" size="small" class="common-w-all" placeholder="请选择菜单组类型">
          <el-option v-for="(row, index) in dict.menuGroupTypes"
                     :key="row.value"
                     :label="row.label"
                     :value="row.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="菜单组描述：" prop="description">
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
  import ErrorPage from "../../../../../../components/error-page/src/ErrorPage.vue";
  import merge from "../../../../../../utils/merge.js";
  import ApiGarMenuGroupUrls from "../../urls/api-gar-menu-group-urls.js";

  export default {

    name: "menu-group-edit",

    componentName: "MenuGroupEdit",

    components: {ErrorPage},

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
        formData: {},
        formRules: {
          name: [
            {required: true, message: '请输入菜单组名称', trigger: 'blur'},
            {min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur'}
          ],
          type: [
            {required: true, message: '请选择菜单组类型', trigger: 'change'}
          ]
        }
      }
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
            this.$Ajax.put(ApiGarMenuGroupUrls.put.menuGroupByMenuGroupId, merge({
              moduleId: this.moduleId,
              menuGroupId: this.formData.id
            }, this.formData))
              .success('修改菜单组成功', () => {
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
