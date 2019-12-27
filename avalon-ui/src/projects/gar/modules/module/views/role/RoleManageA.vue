<template>
  <div>
    <!--查询-->
    <div class="gar-search-bar">
      <!--查询条件-->
      <ul class="gar-search-bar-list">
        <li>
          <em class="list-left">关键字：</em>
          <div class="list-right common-w-large">
            <el-input v-model="params.likeText" clearable size="small" placeholder="请输入关键字"></el-input>
          </div>
        </li>
        <li class="spacing">
          <we-button :loading="tableCellEditLoading" clearable size="small"
                     @click="handleConfirmCloseAllTableCellEdit().then(reGetTableCellEditRows)">查询
          </we-button>
        </li>
      </ul>
      <div class="common-float-right">
        <we-button size="small" type="success"
                   @click="handleConfirmCloseAllTableCellEdit().then(() => tableCellEditShowAdd = true)">新增角色
        </we-button>
      </div>
      <div class="common-clear"></div>
      <!--/查询条件-->
    </div>
    <!--/查询-->

    <!--<div class="list-tools avalon-merlin-hidden">-->
    <!--<el-button type="danger" size="small"-->
    <!--:disabled="checkedRoleList.length === 0"-->
    <!--:loading="deleteRoleLoading"-->
    <!--@click="handleClickDeleteCheckedRoles">删除角色-->
    <!--</el-button>-->
    <!--<el-button type="warning" size="small" :disabled="tableCellEditCount === 0"-->
    <!--@click="handleCloseAllEditAndLoadingTableCellEditRowColumns(tableCellEditProps)">取消所有编辑-->
    <!--</el-button>-->
    <!--</div>-->

    <!--列表-->
    <div v-loading="tableCellEditLoading && tableCellEditSkeleton === false"
         class="gar-list-main is-tools-bar is-border is-paging-size-small">
      <!--工具条（批量操作）-->
      <div class="gar-tools-bar">
        <we-button type="text"
                   :disabled="checkedRoleList.length === 0"
                   :loading="deleteRoleLoading"
                   @click="handleClickDeleteCheckedRoles">删除角色
        </we-button>
        <we-button type="text" :disabled="tableCellEditCount === 0"
                   @click="handleCloseAllEditAndLoadingTableCellEditRowColumns(tableCellEditProps)">取消所有编辑
        </we-button>
      </div>
      <!--/工具条（批量操作）-->

      <we-skeleton v-model="tableCellEditSkeleton"
                   animation
                   :paragraph-rows="10"
                   :paragraph-width="'100%'"
                   @reload="getTableCellEditRows"
      >
        <div class="gar-list-main-inner is-border">
          <el-table :data="tableCellEditRows"
                    :size="tableCellEditSize"
                    fit
                    height="100%"
                    @cell-dblclick="(row, column, cell, event) => handleOpenTableCellEditRowColumnEdit(row, column.property)"
          >
            <template slot="empty">
              <error-page :image-width="180" :image-height="180"></error-page>
            </template>
            <el-table-column type="selection" width="40"></el-table-column>
            <el-table-column prop="name"
                             label="角色名称"
                             align="center"
                             show-overflow-tooltip
            >
              <template slot-scope="scope">
                <span v-loading="{
                  value: scope.row[`${scope.column.property}${tableCellEditRowColumnEditLoading$}`],
                  size: 'mini'
                }">
                  <template
                    v-if="!scope.row[`${scope.column.property}${tableCellEditRowColumnEditLoading$}`] && scope.row[`${scope.column.property}${tableCellEditRowColumnEditSuffix$}`]">
                    <we-tooltip :disabled="tableCellEditBlurSaveEdit" placement="top-start" effect="light">

                      <template slot="content">
                        <we-button :disabled="scope.row[`${scope.column.property}${tableCellEditRowColumnEditLoading$}`]"
                                   type="text" size="mini"
                                   @click="restoreTableCellEditChangeRowColumnValueAndCloseEdit(scope.row, scope.column.property)">关闭</we-button>
                        <we-button
                          :disabled="scope.row[`${scope.column.property}${tableCellEditRowColumnEditLoading$}`] || !isTableCellEditRowColumnChange(scope.row, scope.column.property)"
                          size="mini"
                          @click="restoreTableCellEditChangeRowColumnValue(scope.row, scope.column.property)">还原</we-button>
                        <we-button :loading="scope.row[`${scope.column.property}${tableCellEditRowColumnEditLoading$}`]"
                                   :disabled="!isTableCellEditRowColumnChange(scope.row, scope.column.property)"
                                   type="primary" size="mini" @click="handleSaveTableCellEdit(scope)">保存</we-button>
                      </template>

                      <el-input v-model="scope.row[`${scope.column.property}${tableCellEditRowColumnEditValueSuffix$}`]"
                                :placeholder="`请输入${scope.column.label}`"
                                :size="tableCellEditSize" @blur="handleBlurTableCellEditColumnChange(scope)"></el-input>
                    </we-tooltip>
                  </template>
                  <template v-else>{{scope.row[scope.column.property]}}</template>
                </span>
              </template>
            </el-table-column>
            <el-table-column prop="value"
                             label="角色标识符"
                             align="center"
                             show-overflow-tooltip
            >
              <template slot-scope="scope">
              <span v-loading="{
                value: scope.row[`${scope.column.property}${tableCellEditRowColumnEditLoading$}`],
                size: 'mini'
              }">
                <template
                  v-if="!scope.row[`${scope.column.property}${tableCellEditRowColumnEditLoading$}`] && scope.row[`${scope.column.property}${tableCellEditRowColumnEditSuffix$}`]">
                  <we-tooltip :disabled="tableCellEditBlurSaveEdit" placement="top-start" effect="light">

                    <template slot="content">
                      <we-button :disabled="scope.row[`${scope.column.property}${tableCellEditRowColumnEditLoading$}`]"
                                 type="text" size="mini"
                                 @click="restoreTableCellEditChangeRowColumnValueAndCloseEdit(scope.row, scope.column.property)">关闭</we-button>
                      <we-button
                        :disabled="scope.row[`${scope.column.property}${tableCellEditRowColumnEditLoading$}`] || !isTableCellEditRowColumnChange(scope.row, scope.column.property)"
                        size="mini"
                        @click="restoreTableCellEditChangeRowColumnValue(scope.row, scope.column.property)">还原</we-button>
                      <we-button :loading="scope.row[`${scope.column.property}${tableCellEditRowColumnEditLoading$}`]"
                                 :disabled="!isTableCellEditRowColumnChange(scope.row, scope.column.property)"
                                 type="primary" size="mini" @click="handleSaveTableCellEdit(scope)">保存</we-button>
                    </template>

                    <el-input v-model="scope.row[`${scope.column.property}${tableCellEditRowColumnEditValueSuffix$}`]"
                              :placeholder="`请输入${scope.column.label}`"
                              :size="tableCellEditSize" @blur="handleBlurTableCellEditColumnChange(scope)"></el-input>
                  </we-tooltip>
                </template>
                <template v-else>{{scope.row[scope.column.property]}}</template>
              </span>
              </template>
            </el-table-column>
            <el-table-column prop="type"
                             label="角色类型"
                             align="center"
                             show-overflow-tooltip
            >
              <template slot-scope="scope">
                <span v-loading="{
                  value: scope.row[`${scope.column.property}${tableCellEditRowColumnEditLoading$}`],
                  size: 'mini'
                }">
                  <template
                    v-if="!scope.row[`${scope.column.property}${tableCellEditRowColumnEditLoading$}`] && scope.row[`${scope.column.property}${tableCellEditRowColumnEditSuffix$}`]">
                    <el-select v-model="scope.row[`${scope.column.property}${tableCellEditRowColumnEditValueSuffix$}`]"
                               :placeholder="`请选择${scope.column.label}`"
                               :size="tableCellEditSize" @change="() => {
                      if(tableCellEditChangeSaveEdit !== true) return;
                      handleSaveTableCellEdit(scope);
                    }">
                      <el-option v-for="(item, index) in dict.roleTypes"
                                 :key="item.value"
                                 :label="item.label"
                                 :value="item.value">
                      </el-option>
                    </el-select>
                  </template>
                  <template v-else>{{dict.roleTypeLabel[scope.row[scope.column.property]]}}</template>
                </span>
              </template>
            </el-table-column>
            <el-table-column prop="status"
                             label="状态"
                             align="center"
            >
              <template slot-scope="scope">
                <template v-if="scope.row.type === dict.resourceTypeValue.NODE">-</template>
                <el-switch v-else
                           active-color="#13ce66"
                           inactive-color="#cccccc"
                           v-model="scope.row.status"
                           :active-value="dict.statusValue.NORMAL"
                           :inactive-value="dict.statusValue.DISABLED"
                           @change="handleChangeStatus(scope)"
                ></el-switch>
              </template>
            </el-table-column>
            <el-table-column prop="status"
                             label="操作"
                             align="center"
                             width="270"
                             show-overflow-tooltip
            >
              <template slot-scope="scope">
                <el-button type="text"
                           size="mini"
                           :disabled="!isEnableTableCellEditConfirmEditRow(scope, tableCellEditProps)"
                           @click="handleOpenTableCellEditRowColumnsEdit(scope.row, tableCellEditProps)">
                  编辑此行
                </el-button>
                <el-button type="text"
                           size="mini"
                           :disabled="!isEnableTableCellEditCancelEditRow(scope, tableCellEditProps)"
                           @click="handleCloseEditAndLoadingTableCellEditRowColumns(scope.row, tableCellEditProps)">
                  取消编辑此行
                </el-button>
                <el-button type="text"
                           size="mini"
                           :disabled="!isEnableTableCellEditSaveEditRow(scope, tableCellEditProps)"
                           @click="handleSaveTableRowEdit(scope, tableCellEditProps)">
                  保存此行
                </el-button>
                <we-popover title="操作" type="confirm"
                            placement="top-start"
                            :manual="scope.row[tableCellEditRowDeleteLoading$]"
                            confirm-button-text="删了吧"
                            :cancel-button-text="scope.row[tableCellEditRowDeleteLoading$] ? '请稍等' : '我再想想'"
                            :cancel-button-options="{disabled: scope.row[tableCellEditRowDeleteLoading$]}"
                            :confirm-button-options="{loading: scope.row[tableCellEditRowDeleteLoading$]}"
                            @click-cancel-button="(e, vm) => vm.close()"
                            @click-confirm-button="(e, vm) => handleDeleteRow(e, vm, scope)">
                  <template slot="content">
                    <template v-if="scope.row[tableCellEditRowDeleteLoading$]">
                      <span style="color: red">删除中...</span>删除完成后将自动关闭气泡
                    </template>
                    <template v-else>
                      您确定要<span style="color: red">删除</span>该角色<span style="color: red">及相关数据</span>吗？
                      <div style="color: red">删除后不可恢复！！！</div>
                    </template>
                  </template>
                  <el-button type="text" size="small">删除</el-button>
                </we-popover>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </we-skeleton>

      <div class="gar-list-main-paging gar-paging gar-paging-size-small">
        <el-pagination layout="total, sizes, prev, pager, next, jumper"
                       :current-page="limit.currentPage"
                       :page-size="limit.pageSize"
                       :total="limit.total"
                       :page-count="limit.pageCount"
                       :page-sizes="[10, 20, 50]"
                       @current-change="v => {limit.currentPage = v;getTableCellEditRows();}"
                       @size-change="v => {limit.pageSize = v;getTableCellEditRows();}"
        >
        </el-pagination>
      </div>
    </div>

    <we-layer v-model="tableCellEditShowAdd"
              title="新建角色"
              :width="50"
              :height="60"
              :min-width="500"
              :show-footer="false"
              drag
              resize
    >
      <role-add :module-id="moduleId"
                @submit-success="handleSubmitSuccess">
        <template slot="button">
          <el-button @click="tableCellEditShowAdd = false">关闭</el-button>
        </template>
      </role-add>
    </we-layer>
  </div>
</template>

<script>

  import Global from '../../../../mixins/global.js';

  const GlobalData = Global.data();

  import TableCellEdit from '../../../../../../assets/template/mixins/tableCellEdit.js';
  import merge from "../../../../../../utils/merge.js";

  import RoleUserManager from "../object/ObjectRoleManageA.vue";
  import RoleResourceManager from "./RoleResourceManageA.vue";
  import ErrorPage from "../../../../../../components/error-page/src/ErrorPage.vue";
  import ApiGarRoleUrls from "../../urls/api-gar-role-urls.js";
  import RoleAdd from "./RoleAddA.vue";

  const Limit = {
    currentPage: 1,
    pageSizes: [10, 20, 50, 100],
    total: 0,
    pageCount: 1
  };

  const Params = {
    likeText: ''
  };

  export default {
    components: {
      RoleAdd,
      ErrorPage,
      RoleResourceManager,
      RoleUserManager
    },

    name: "role-manage",

    mixins: [Global, TableCellEdit],

    props: {
      moduleId: {
        type: String,
        required: true
      }
    },

    data() {
      return {
        limit: merge({pageSize: 20}, Limit),
        params: merge({}, Params),
        //TODO 配置你要编辑的列
        tableCellEditProps: ['name', 'type'],
        //尺寸
        tableCellEditSize: 'medium',
        //编辑组件失焦时是否保存编辑,用于控制文本框保存形式,开启时失焦判断,关闭时由tooltip控制
        tableCellEditBlurSaveEdit: false,
        //编辑组件改变时是否保存标记,用于控制下拉框
        tableCellEditChangeSaveEdit: true,

        checkedRoleList: [],

        deleteRoleLoading: false
      };
    },

    methods: {
      reGetTableCellEditRows() {
        merge(this.limit, Limit);
        this.getTableCellEditRows();
      },
      getTableCellEditRows() {
        this.tableCellEditLoading = true;
        this.$Ajax
          .get(ApiGarRoleUrls.get.pageListRole, merge({moduleId: this.moduleId}, this.limit, this.params))
          .success(true, data => {
            this.tableCellEditSkeleton = false;
            this.tableCellEditRows = this.formatterTableCellEditRows(data.rows, this.tableCellEditProps);

            merge(this.limit, data.limit);
          })
          .notSuccess(() => this.tableCellEditSkeleton = 500)
          .catch(() => this.tableCellEditSkeleton = 500)
          .finally(() => this.tableCellEditLoading = false);
      },
      handleSaveTableCellEdit({row, column, $index}) {
        let nvs = this.getTableCellEditChangeRowColumnNewValues(row, [column.property]);
        const editNames = Object.getOwnPropertyNames(nvs);
        this.handleCloseEditAndLoadingTableCellEditRowColumn(row, column.property);
        if (editNames.length === 0) return;
        this.handleOpenTableCellEditRowColumnsLoading(row, editNames);
        this.$Ajax
          .put(ApiGarRoleUrls.put.roleByRoleId, merge({
            moduleId: this.moduleId,
            roleId: row.id
          }, nvs))
          .success(true, () => {
            this.syncTableCellEditRowColumnEditValue(row, column.property);
          })
          .finally(() => this.handleCloseLoadingTableCellEditRowColumn(row, column.property));
      },
      handleSaveTableRowEdit({row, column, $index}, props = this.tableCellEditProps) {
        let nvs = this.getTableCellEditChangeRowColumnNewValues(row, props);
        const editNames = Object.getOwnPropertyNames(nvs);
        this.handleCloseEditAndLoadingTableCellEditRowColumns(row, props);
        if (editNames.length === 0) return;
        this.handleOpenTableCellEditRowColumnsLoading(row, editNames);
        this.$Ajax
          .put(ApiGarRoleUrls.put.roleByRoleId, merge({
            moduleId: this.moduleId,
            roleId: row.id
          }, nvs))
          .success(true, () => {
            this.syncTableCellEditRowColumnsEditValue(row, editNames);
          })
          .finally(() => this.handleCloseLoadingTableCellEditRowColumns(row, props));
      },
      handleChangeStatus({row, column, $index}) {
        this.$Ajax.put(ApiGarRoleUrls.put.roleByRoleId, {
          moduleId: this.moduleId,
          roleId: row.id,
          status: row.status
        })
          .success(true)
          .notSuccess(() => row.status = row.status === this.dict.statusValue.NORMAL ? this.dict.statusValue.DISABLED : this.dict.statusValue.NORMAL);
      },
      handleDeleteRow(e, vm, {row, column, $index}) {
        row[this.tableCellEditRowDeleteLoading$] = true;
        this.$Ajax.delete(ApiGarRoleUrls.delete.roleByRoleId, {
          moduleId: this.moduleId,
          roleId: row.id
        })
          .before(() => row[this.tableCellEditRowDeleteLoading$] = false)
          .success('删除成功', () => {
            this.tableCellEditRows.splice($index, 1);
            vm.close();
          });
      },
      handleClickDeleteCheckedRoles() {

      },
      handleSubmitSuccess(role, vm) {
        this.tableCellEditRows.push(this.formatterTableCellEditRow(role, this.tableCellEditProps));
      },
    },

    created() {
      this.getTableCellEditRows();
    }
  }
</script>
