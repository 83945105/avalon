<template>
  <el-form ref="form"
           v-loading="formLoading"
           :model="formData"
           :rules="formRules"
           label-width="80px"
           style="margin: 30px 30px 30px 30px">
    <el-form-item label="资源名称" prop="name">
      <el-input v-model="formData.name" placeholder="请输入资源名称" size="small"></el-input>
    </el-form-item>
    <el-form-item label="资源类型" prop="type">
      <el-select v-model="formData.type" placeholder="请选择资源类型" size="small">
        <el-option v-for="(item, index) in dict.resourceTypes"
                   :key="index"
                   :label="item.label"
                   :title="item.value"
                   :value="item.value">
          <i v-if="item.value === dict.resourceTypeValue.NODE" class="we-icon-folder-o"></i>
          <i v-else-if="item.value === dict.resourceTypeValue.URL" class="we-icon-link"></i>
          <i v-else-if="item.value === dict.resourceTypeValue.PERMISSION" class="we-icon-jurisdiction-shield"></i>
          <span>{{item.label}}</span>
        </el-option>
      </el-select>
    </el-form-item>
    <el-form-item v-if="formData.type === dict.resourceTypeValue.URL" label="资源地址" prop="url">
      <el-input v-model="formData.url" placeholder="请输入资源请求地址URL(相对路径,不包含项目根路径,以 / 开头)" size="small"></el-input>
    </el-form-item>
    <el-form-item v-if="formData.type === dict.resourceTypeValue.PERMISSION" label="资源许可" prop="permission">
      <el-input v-model="formData.permission" placeholder="请输入资源许可(用于Shiro注解)" size="small"></el-input>
    </el-form-item>
    <el-form-item label="资源描述" prop="description">
      <el-input v-model="formData.description" type="textarea" autosize placeholder="请输入资源描述"
                size="small"></el-input>
    </el-form-item>
    <el-form-item v-if="formData.type !== dict.resourceTypeValue.NODE" label="立即使用" prop="status">
      <el-switch v-model="formData.status"
                 active-color="#13ce66"
                 inactive-color="#cccccc"
                 :active-value="dict.statusValue.NORMAL"
                 :inactive-value="dict.statusValue.DISABLED"
      ></el-switch>
    </el-form-item>
    <el-form-item v-if="formData.type !== dict.resourceTypeValue.NODE"
                  v-loading="roleLoading"
                  label="选择角色"
                  prop="roleIds">
      <el-checkbox :indeterminate="isIndeterminateRoles"
                   v-model="isCheckAllRoles"
                   @change="handleCheckAllRoles">全选
      </el-checkbox>
      <el-checkbox-group v-model="roleIds"
                         size="small"
                         @change="handleCheckRoles"
      >
        <ul class="button-list">
          <li v-for="role in roleList">
            <el-checkbox :label="role.id"
                         :title="role.value"
                         :disabled="role.status === dict.statusValue.DISABLED"
                         border
            >{{role.name}}
            </el-checkbox>
          </li>
        </ul>
      </el-checkbox-group>
    </el-form-item>
    <el-form-item label="所属节点" prop="roles">
      <resource-tree ref="resourceTree"
                     :module-id="moduleId"
                     :query-params="{typeSet: dict.resourceTypeValue.NODE, childTypeSet: dict.resourceTypeValue.NODE}"
                     :query-child-params="{typeSet: dict.resourceTypeValue.NODE, childTypeSet: dict.resourceTypeValue.NODE}"
      ></resource-tree>
    </el-form-item>
    <el-form-item>
      <el-button :loading="formLoading" type="primary" @click="submit">立即创建</el-button>
      <el-button :disabled="formLoading" @click="$refs.form.resetFields()">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script>

  import Global from '../../../../mixins/global.js';
  import {Data as GlobalData} from '../../../../mixins/global.js';
  import merge from "../../../../../../utils/merge.js";
  import ApiGarRoleUrls from "../../urls/api-gar-role-urls.js";
  import ApiGarResourceUrls from "../../urls/api-gar-resource-urls.js";
  import ResourceTree from "./ResourceTreeA.vue";

  const Resource = {
    name: '',
    type: '',
    url: '',
    path: '',
    description: '',
    permission: '',
    status: ''
  };

  export default {

    name: "resource-add",

    components: {ResourceTree},

    mixins: [Global],

    props: {
      moduleId: {
        type: String,
        required: true
      }
    },

    data() {
      return {
        formLoading: false,
        formData: merge({}, Resource, {
          type: GlobalData.dict.resourceTypeValue.URL,
          status: GlobalData.dict.statusValue.NORMAL
        }),
        formRules: {
          name: [
            {required: true, message: '请输入资源名称', trigger: 'blur'},
            {min: 2, max: 64, message: '长度在 2 到 64 个字符', trigger: 'blur'}
          ],
          type: [
            {required: true, message: '请选择资源类型', trigger: 'change'}
          ],
          url: [
            {required: true, message: '请输入资源地址', trigger: 'blur'}
          ],
          permission: [
            {required: true, message: '请输入资源许可', trigger: 'blur'}
          ]
        },
        roleList: [],
        roleLoading: false,
        roleIds: [],
        isIndeterminateRoles: false,
        isCheckAllRoles: false

      };
    },

    methods: {
      getRoleList() {
        this.roleLoading = true;
        this.$Ajax.get(ApiGarRoleUrls.get.listRole, {moduleId: this.moduleId})
          .success(true, data => {
            this.roleList = data.rows;
          })
          .finally(() => this.roleLoading = false);
      },
      handleCheckAllRoles(val) {
        this.isIndeterminateRoles = false;
        this.roleIds = val ? this.roleList.map(val => val.id) : [];
      },
      handleCheckRoles(val) {
        let checkedCount = val.length;
        this.isCheckAllRoles = checkedCount === this.roleList.length;
        this.isIndeterminateRoles = checkedCount > 0 && checkedCount < this.roleList.length;
      },
      submit() {
        this.$refs.form.validate(value => {
          if (value) {
            this.formLoading = true;

            let params = merge({moduleId: this.moduleId}, this.formData);
            if (this.roleIds.length > 0) {
              params.roleIdSet = this.roleIds.toString();
            }

            const parentIds = this.$refs.resourceTree.getCheckedKeys();
            if (parentIds.length > 0) {
              params.parentIdSet = parentIds.toString();
            }
            this.$Ajax.post(ApiGarResourceUrls.post.resource, params)
              .success('新建资源成功', data => {
                const rows = data.rows;
                if (rows && rows.length > 0) {
                  rows.forEach(row => {
                    if (row.type !== this.dict.resourceTypeValue.NODE) return true;
                    if (!row.parentId) {
                      //插入到根节点最后
                      this.$refs.resourceTree.insertAfterRoot(row);
                    } else {
                      //插入到指定节点下
                      this.$refs.resourceTree.append(row, row.parentId);
                    }
                  });
                }
                this.$emit('submit-success', data, this);
              })
              .finally(() => this.formLoading = false);
          }
        });
      }
    },
    created() {
      this.getRoleList();
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

  .text-selection *::selection {
    background-color: transparent;
  }

  .text-selection *::-moz-selection {
    background-color: transparent;
  }

  .button-list li {
    width: 15.66%;
    float: left;
    padding: 5px 1% 5px 0;
  }

  .button-list li .el-checkbox {
    display: block;
    max-width: 100%;
    white-space: nowrap;
    text-overflow: ellipsis;
    overflow: hidden;
  }
</style>
