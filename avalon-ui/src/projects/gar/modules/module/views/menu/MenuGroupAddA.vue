<template>
  <div class="common-padding-all">
    <el-form :ref="formRefValue$"
             v-loading="formLoading"
             :rules="formRules"
             :model="formData"
             label-width="120px"
             size="small">
      <el-form-item label="所属子模块：">
        <el-input v-model="subModule.name" disabled></el-input>
      </el-form-item>
      <el-form-item label="菜单组名称：" prop="name">
        <el-input v-model="formData.name" clearable></el-input>
      </el-form-item>
      <el-form-item label="菜单组类型：" prop="type">
        <el-select v-model="formData.type" size="small" class="common-w-all" placeholder="请选择菜单类型">
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
        <el-button :loading="formLoading" type="primary" @click="submit">立即创建</el-button>
        <el-button :disabled="formLoading" @click="resetForm">重置</el-button>
        <slot name="button"></slot>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>

  import Global from '../../../../mixins/global.js';
  import {Data as GlobalData} from '../../../../mixins/global.js';

  import Form from '../../../../../../assets/template/mixins/form.js';
  import ErrorPage from "../../../../../../components/error-page/src/ErrorPage.vue";
  import merge from "../../../../../../utils/merge.js";
  import ApiGarSubModuleUrls from "../../urls/api-gar-sub-module-urls.js";
  import ApiGarMenuGroupUrls from "../../urls/api-gar-menu-group-urls.js";

  const MenuGroup = {
    name: '',
    type: '',
    description: '',
    status: ''
  };

  export default {

    name: "menu-group-add",

    componentName: "MenuGroupAdd",

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
      }
    },

    data() {
      return {
        subModule: {},

        formData: merge({}, MenuGroup, {
          status: GlobalData.dict.statusValue.NORMAL
        }),
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
      subModuleId(val) {
        this.getSubModule();
      }
    },

    methods: {
      getSubModule() {
        if (this.subModuleId) {
          this.$Ajax.get(ApiGarSubModuleUrls.get.subModuleBySubModuleId, [this.subModuleId], {
            params: {moduleId: this.moduleId}
          })
            .success(true, data => {
              this.subModule = data.records.subModule;
            });
        }
      },
      submit() {
        this.$refs[this.formRefValue$].validate((valid) => {
          if (valid) {
            this.formLoading = true;
            this.$Ajax.post(ApiGarMenuGroupUrls.post.menuGroup, merge({
              moduleId: this.moduleId,
              subModuleId: this.subModuleId
            }, this.formData))
              .success('新增菜单组成功', data => {
                this.$emit('submit-success', data.records.menuGroup, this);
                this.resetForm();
              })
              .finally(() => this.formLoading = false);
          } else {
            return false;
          }
        });
      }
    },
    created() {
      this.getSubModule();
    }
  }
</script>
