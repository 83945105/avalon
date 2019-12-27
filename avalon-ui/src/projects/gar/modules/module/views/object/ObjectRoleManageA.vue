<template>
  <div>
    <!--查询-->
    <div class="gar-search-bar">
      <!--查询条件-->
      <ul class="gar-search-bar-list">
        <li>
          <em class="list-left">关键字：</em>
          <div class="list-right common-w-large">
            <el-input v-model="searchUser.searchText" size="small"
                      placeholder="请输入关键字" clearable></el-input>
          </div>
        </li>
        <li class="spacing">
          <we-button :loading="loading" size="small" @click="reGetUserPageList">查询</we-button>
        </li>
      </ul>
      <div class="common-clear"></div>
      <!--/查询条件-->
    </div>
    <!--/查询-->

    <div class="gar-list-main" style="top: 62px;">
      <we-skeleton v-model="tableSkeleton" animation :paragraph-rows="10"
                   :title-width="'50%'" :paragraph-width="'100%'"
                   @reload="getUserPageList"
      >
        <div v-loading="!tableSkeleton && tableLoading" class="gar-list-main-inner" style="bottom: 62px">
          <el-table :data="userList"
                    @selection-change="handleSelectionChange"
                    size="medium"
                    width="100%"
                    height="100%"
          >
            <template slot="empty">
              <error-page :image-width="180" :image-height="180"></error-page>
            </template>
            <template v-for="(row, index) in userColumnList">
              <el-table-column :label="row.label" :column-key="row.columnKey" :prop="row.columnKey"
                               :min-width="row.minWidth"
                               :align="row.align"></el-table-column>
            </template>
            <el-table-column label="操作" width="200" align="center" fixed="right">
              <template slot-scope="scope">
                <el-switch v-model="scope.row.checked"
                           active-text="授予"
                           inactive-text="取消"
                           active-color="#13ce66"
                           inactive-color="#cccccc"
                           :disabled="scope.row.disabled"

                           @change="handleChangeUserRoleCheck(scope.row, $event)"
                ></el-switch>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </we-skeleton>

      <div class="gar-list-main-paging gar-paging">
        <el-pagination
          :current-page="limit.currentPage"
          :page-sizes="[10, 50, 100, 200, 300, 400]"
          :page-size="limit.pageSize"
          :total="limit.total"
          layout="total, sizes, prev, pager, next, jumper"

          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        >
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>

  import Global from '../../../../mixins/global.js';
  import ErrorPage from "../../../../../../components/error-page/src/ErrorPage.vue";

  const Limit = {
    currentPage: 1,
    pageCount: 1,
    pageSize: 10,
    total: 0
  };

  export default {

    name: "object-role-manage",

    mixins: [Global],

    components: {ErrorPage},

    data() {
      return {
        tableHeight: 0,
        userColumnList: [],

        searchUser: {
          searchText: ''
        },

        tableSkeleton: false,
        tableLoading: false,
        userList: [],
        limit: Object.assign({}, Limit),

        checkedUserList: [],

        roleUserList: []

      };
    },

    props: {
      moduleId: {
        type: String,
        required: true
      },
      roleId: {
        type: String,
        required: true
      },
      role: {
        type: String,
        required: true
      },
      roleName: {
        type: String,
        required: true
      }
    },

    computed: {
      hasUserIdsMap() {
        let map = new Map();
        for (let rr of this.roleUserList) {
          map.set(rr.userId, true);
        }
        return map;
      },
      disabledUserIdsMap() {
        let map = new Map();
        for (let rr of this.roleUserList) {
          if (rr.type === 'OTHER') {
            map.set(rr.userId, true);
          }
        }
        return map;
      }
    },

    methods: {
      getUserColumnList() {
        this.$Ajax.get(UserManagerUrl.get.userColumnList)
          .success(true, data => {
            this.userColumnList = data.rows;
            this.getUserPageList();
          });
      },
      reGetUserPageList() {
        Object.assign(this.limit, Limit);
        this.getUserPageList();
      },
      userListProcess(userList) {
        let map = this.hasUserIdsMap;
        let disMap = this.disabledUserIdsMap;
        for (let i in userList) {
          userList[i].checked = !!map.get(userList[i].id);
          userList[i].disabled = !!disMap.get(userList[i].id);
        }
        return userList;
      },
      getUserPageList() {
        this.tableLoading = true;
        this.$Ajax.get(UserManagerUrl.get.userPageList, {
          moduleId: this.moduleId,
          currentPage: this.limit.currentPage,
          pageSize: this.limit.pageSize,
          searchText: this.searchUser.searchText
        })
          .success(true, data => {
            this.tableSkeleton = false;
            Object.assign(this.limit, data.limit);
            this.userList = this.userListProcess(data.rows);
          })
          .notSuccess(() => this.tableSkeleton = 500)
          .catch(() => this.tableSkeleton = 500)
          .finally(() => this.tableLoading = false);
      },
      handleSelectionChange(selection) {
        this.checkedUserList = selection;
      },
      handleSizeChange(pageSize) {
        this.limit.pageSize = pageSize;
        this.getUserPageList();
      },
      handleCurrentChange(currentPage) {
        this.limit.currentPage = currentPage;
        this.getUserPageList();
      },
      getRoleUserListByRoleId() {
        this.$Ajax.get(RoleUserManagerUrl.get.roleUserListByRoleId, [this.roleId], {params: {moduleId: this.moduleId}})
          .success(true, data => {
            this.roleUserList = data.rows;
            this.getUserColumnList();
          });
      },
      handleChangeUserRoleCheck(row, value) {
        if (value) {
          this.$Ajax.post(RoleUserManagerUrl.post.grantRoleToUser, {
            moduleId: this.moduleId,
            roleId: this.roleId,
            userId: row.id
          })
            .success('授予角色成功')
            .notSuccess(() => row.checked = !value);
        } else {
          this.$Ajax.post(RoleUserManagerUrl.post.unGrantRoleToUser, {
            moduleId: this.moduleId,
            roleId: this.roleId,
            userId: row.id
          })
            .success('取消角色成功')
            .notSuccess(() => row.checked = !value);
        }
      }
    },

    created() {
      this.getRoleUserListByRoleId();
    },

    mounted() {
      this.tableHeight = document.body.clientHeight - 265;
    }
  }
</script>

<style scoped>
  .btn-p-left {
    padding-left: 3px;
  }

  .tree-module {
    position: absolute;
    top: 138px;
    bottom: 15px;
    left: 15px;
    right: 15px;
    padding: 10px;
    border: 1px solid #E5E5E5;
  }

  .tree-module-inner {
    position: absolute;
    top: 52px;
    bottom: 10px;
    left: 8px;
    right: 8px;
  }

  .tree-module-default {
    padding: 10px;
    border: 1px solid #E5E5E5;
  }

  .list-tools {
    padding: 13px;
    background-color: #FAFAFA;
    border-top: 1px solid #EEEEEE;
  }

  .module-tools {
    display: block;
    position: absolute;
    top: -6px;
    left: 0;
    right: 0;
    z-index: 2;
    color: #FFFFFF;
  }

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

  .module-tools .tools-left {
    float: left;
  }

  .module-tools .tools-right {
    float: right;
  }

  .el-table__body-wrapper {
    overflow: inherit;
  }
</style>
