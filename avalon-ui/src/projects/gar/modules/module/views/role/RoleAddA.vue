<template>
  <div class="common-padding-all">
    <el-form :ref="formRefValue$"
             v-loading="formLoading"
             :model="formData"
             :rules="formRules"
             label-width="100px"
             size="small">
      <el-form-item label="角色名称：" prop="name">
        <el-input v-model="formData.name" placeholder="请输入角色名称" size="small"></el-input>
      </el-form-item>
      <el-form-item label="角色标识符：" prop="value">
        <el-input v-model="rolePrefix" size="small" disabled style="width: 30%"></el-input>
        <el-input v-model="formData.value" placeholder="请输入角色标识符" size="small"
                  style="width: 69%;float:right;"></el-input>
      </el-form-item>
      <el-form-item label="角色类型：" prop="type">
        <el-select v-model="formData.type" placeholder="请选择角色类型" size="small">
          <el-option v-for="(item, index) in dict.roleTypes"
                     :key="index"
                     :label="item.label"
                     :value="item.value"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="角色描述：" prop="description">
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
        <el-button :loading="formLoading" type="primary" @click="submit">立即创建</el-button>
        <el-button :disabled="formLoading" @click="resetForm">重置</el-button>
        <slot name="button"></slot>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>

  import Global from '../../../../mixins/global.js';

  const GlobalData = Global.data();

  import Form from '../../../../../../assets/template/mixins/form.js';
  import merge from "../../../../../../utils/merge.js";
  import ApiGarRoleUrls from "../../urls/api-gar-role-urls.js";

  const Role = {
    name: '',
    value: '',
    type: '',
    description: '',
    permission: ''
  };

  export default {

    name: "role-add",

    mixins: [Global, Form],

    props: {
      moduleId: {
        type: String,
        required: true
      }
    },

    data() {
      return {
        rolePrefix: `${this.moduleId}_`,

        formData: merge({}, Role, {
          type: GlobalData.dict.roleTypeValue.LOCAL,
          status: GlobalData.dict.statusValue.NORMAL
        }),
        formRules: {
          name: [
            {required: true, message: '请输入角色名称', trigger: 'blur'},
            {min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur'}
          ],
          value: [
            {required: true, message: '请输入角色唯一标识符', trigger: 'blur'},
            {min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur'},
            {
              validator: (rule, value, callback) => {
                this.$Ajax.post(ApiGarRoleUrls.get.validateValueCanUse, {
                  value: `${this.rolePrefix}${value}`,
                  moduleId: this.moduleId
                })
                  .success(true, data => {
                    if (data.records.canUse) {
                      callback();
                    } else {
                      callback(new Error('角色唯一标识符不可用'));
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
            {required: true, message: '请选择资源类型', trigger: 'change'}
          ]
        }
      };
    },

    methods: {
      submit() {
        this.$refs[this.formRefValue$].validate(value => {
          if (value) {
            this.formLoading = true;

            let params = merge({moduleId: this.moduleId}, this.formData, {value: `${this.rolePrefix}${this.formData.value}`});

            this.$Ajax.post(ApiGarRoleUrls.post.role, params)
              .success('新建角色成功', data => {
                this.resetForm();
                this.$emit('submit-success', data.records.role, this);
              })
              .finally(() => this.formLoading = false);
          }
        });
      }
    }

  }
</script>

<style scoped>

  .module-tools i {
    cursor: pointer;
    font-size: 11px;
    width: 14px;
    height: 14px;
    line-height: 14px;
    text-align: center;
    display: inline-block;
    /*background-color: rgba(0, 0, 0, 0.45);*/
    background-color: #AAAAAA;
  }

  .module-tools i:hover {
    /*background-color: rgba(0, 0, 0, 0.6);*/
    background-color: #999999;
  }

</style>
