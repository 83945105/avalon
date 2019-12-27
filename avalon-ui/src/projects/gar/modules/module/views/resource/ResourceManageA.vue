<template>
    <div>
        <!--查询-->
        <div class="gar-search-bar">
            <!--查询条件-->
            <ul class="gar-search-bar-list">
                <li>
                    <em class="list-left">资源类型：</em>
                    <div class="list-right">
                        <el-select v-model="params.typeSet" size="small" multiple placeholder="请选择资源类型"
                                   style="width: 360px">
                            <el-option v-for="(row, index) in dict.resourceTypes"
                                       :key="row.value"
                                       :label="row.label"
                                       :value="row.value">
                            </el-option>
                        </el-select>
                    </div>
                </li>
                <li>
                    <em class="list-left">关键字：</em>
                    <div class="list-right">
                        <el-input v-model="params.likeText" clearable size="small" placeholder="请输入关键字"
                                  prefix-icon="el-icon-search"
                                  style="width: 200px">
                        </el-input>
                    </div>
                </li>
                <li class="spacing">
                    <we-button :loading="tableCellEditLoading" size="small"
                               @click="handleConfirmCloseAllTableCellEdit().then(reGetTableCellEditRows)">搜索
                    </we-button>
                </li>
            </ul>
            <div class="common-float-right">
                <we-button disabled type="danger" size="small">
                    资源树(F2)
                </we-button>
                <we-button type="primary" size="small"
                           @click="handleConfirmCloseAllTableCellEdit().then(() => tableCellEditShowAdd = true)"
                >新建资源
                </we-button>
            </div>
            <div class="common-clear"></div>
            <!--/查询条件-->
        </div>
        <!--/查询-->

        <div v-loading="tableCellEditLoading && tableCellEditSkeleton === false"
             class="gar-list-main is-tools-bar is-border is-paging-size-small">
            <!--工具条（批量操作）-->
            <div class="gar-tools-bar">
                <we-button type="text"
                           :disabled="checkedResourceList.length === 0"
                           @click="handleClickDeleteCheckedResources">删除选中资源
                </we-button>
                <we-button type="text" :disabled="tableCellEditCount === 0"
                           @click="handleCloseAllEditAndLoadingTableCellEditRowColumns(tableCellEditProps)">取消所有编辑
                </we-button>
            </div>
            <!--/工具条（批量操作）-->

            <we-skeleton v-model="tableCellEditSkeleton"
                         animation
                         :paragraph-rows="8"
                         @reload="getTableCellEditRows">
                <div class="gar-list-main-inner is-border is-paging-size">
                    <el-table :data="tableCellEditRows"
                              :size="tableCellEditSize"
                              height="100%"
                              @expand-change="handleExpandChange"
                              @cell-dblclick="handleDbClickCell"
                              @selection-change="handleSelectionChange"
                    >
                        <template slot="empty">
                            <error-page :image-width="180" :image-height="180"></error-page>
                        </template>
                        <el-table-column type="selection" width="40"></el-table-column>
                        <el-table-column v-if="roleList && roleList.length > 0" type="expand" label="所属角色列表"
                                         width="120">
                            <template slot-scope="scope">
                                <template v-if="scope.row.type === dict.resourceTypeValue.NODE">节点不能授予角色</template>
                                <we-skeleton v-else animation v-model="scope.row.expandSkeleton"
                                             @reload="handleExpandChange(scope.row)">
                    <span v-loading="scope.row.checkRolesLoading" style="display: block">
                      <el-checkbox :indeterminate="scope.row.isIndeterminateRoles"
                                   v-model="scope.row.isCheckAllRoles"
                                   :disabled="scope.row.checkRolesLoading"
                                   @change="val => handleCheckAllRoles(scope, val)"
                                   style="margin-left: 10px;margin-bottom: 10px"
                      >全选
                      </el-checkbox>
                      <el-tag size="small" style="margin-left: 10px;margin-bottom: 10px">点击按钮可以将当前资源授予点击角色</el-tag>
                      <el-checkbox-group v-model="scope.row.roleIds"
                                         size="small"
                                         @change="val => handleCheckRoles(scope, val)"
                      >
                        <template v-for="role in roleList">
                          <el-checkbox :label="role.id"
                                       :title="role.value"
                                       :disabled="scope.row.checkRolesLoading || role.status === dict.statusValue.DISABLED"
                                       border
                                       style="margin-left: 10px;margin-bottom: 10px;"
                          >{{role.name}}</el-checkbox>
                        </template>
                      </el-checkbox-group>
                    </span>
                                </we-skeleton>
                            </template>
                        </el-table-column>
                        <el-table-column prop="parentNames"
                                         label="所属节点"
                                         column-key="parentNames"
                                         align="center"
                                         show-overflow-tooltip
                                         width="150"
                                         :formatter="(row, column, cellValue, index) => row[column.property] ? row[column.property] : '-'"
                        >
                            <template slot="header" slot-scope="scope">
                                <span>{{scope.column.label}}</span>
                                <we-icon name="folder-o"></we-icon>
                            </template>
                        </el-table-column>
                        <el-table-column prop="name"
                                         label="资源名称"
                                         align="center"
                                         show-overflow-tooltip
                                         width="150"
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
                          <we-button
                                  :disabled="scope.row[`${scope.column.property}${tableCellEditRowColumnEditLoading$}`]"
                                  type="text" size="mini"
                                  @click="restoreTableCellEditChangeRowColumnValueAndCloseEdit(scope.row, scope.column.property)">关闭</we-button>
                          <we-button
                                  :disabled="scope.row[`${scope.column.property}${tableCellEditRowColumnEditLoading$}`] || !isTableCellEditRowColumnChange(scope.row, scope.column.property)"
                                  size="mini"
                                  @click="restoreTableCellEditChangeRowColumnValue(scope.row, scope.column.property)">还原</we-button>
                          <we-button
                                  :loading="scope.row[`${scope.column.property}${tableCellEditRowColumnEditLoading$}`]"
                                  :disabled="!isTableCellEditRowColumnChange(scope.row, scope.column.property)"
                                  type="primary" size="mini" @click="handleSaveTableCellEdit(scope)">保存</we-button>
                        </template>

                        <el-input
                                v-model="scope.row[`${scope.column.property}${tableCellEditRowColumnEditValueSuffix$}`]"
                                :placeholder="`请输入${scope.column.label}`"
                                :size="tableCellEditSize" @blur="handleBlurTableCellEditColumnChange(scope)"></el-input>
                      </we-tooltip>
                    </template>
                    <template v-else>{{scope.row[scope.column.property]}}</template>
                  </span>
                            </template>
                        </el-table-column>
                        <el-table-column prop="type"
                                         label="资源类型"
                                         align="center"
                                         show-overflow-tooltip
                                         width="180"
                        >
                            <template slot-scope="scope">
                                <template v-if="scope.row.type === dict.resourceTypeValue.NODE">
                                    {{dict.resourceTypeLabel[scope.row[scope.column.property]]}}
                                </template>
                                <template v-else>
                    <span v-loading="{
                      value: scope.row[`${scope.column.property}${tableCellEditRowColumnEditLoading$}`],
                      size: 'mini'
                    }">
                      <template v-if="!scope.row[`${scope.column.property}${tableCellEditRowColumnEditLoading$}`]
                                      && scope.row[`${scope.column.property}${tableCellEditRowColumnEditSuffix$}`]">
                        <el-select
                                v-model="scope.row[`${scope.column.property}${tableCellEditRowColumnEditValueSuffix$}`]"
                                :placeholder="`请选择${scope.column.label}`"
                                :size="tableCellEditSize" @change="() => {
                          if(tableCellEditChangeSaveEdit !== true) return;
                          handleSaveTableCellEdit(scope);
                        }">
                          <el-option v-for="(item, index) in dict.resourceTypes"
                                     v-if="item.value !== dict.resourceTypeValue.NODE"
                                     :key="index"
                                     :label="item.label"
                                     :value="item.value">
                            <i v-if="item.value === dict.resourceTypeValue.NODE" class="we-icon-folder-o"></i>
                            <i v-else-if="item.value === dict.resourceTypeValue.URL" class="we-icon-link"></i>
                            <i v-else-if="item.value === dict.resourceTypeValue.PERMISSION"
                               class="we-icon-jurisdiction-shield"></i>
                            <span>{{item.label}}</span>
                          </el-option>
                        </el-select>
                      </template>
                      <template v-else>{{dict.resourceTypeLabel[scope.row[scope.column.property]]}}</template>
                    </span>
                                </template>
                            </template>
                        </el-table-column>
                        <el-table-column prop="url"
                                         label="统一资源定位符"
                                         header-align="left"
                                         align="left"
                                         min-width="240"
                                         show-overflow-tooltip
                        >

                            <template slot="header" slot-scope="scope">
                                <span>{{scope.column.label}}</span>
                                <we-icon name="link"></we-icon>
                            </template>

                            <template slot-scope="scope">
                                <template v-if="scope.row.type !== dict.resourceTypeValue.URL"></template>
                                <template v-else>
                    <span v-loading="{
                    value: scope.row[`${scope.column.property}${tableCellEditRowColumnEditLoading$}`],
                    size: 'mini'
                  }">
                    <template
                            v-if="!scope.row[`${scope.column.property}${tableCellEditRowColumnEditLoading$}`] && scope.row[`${scope.column.property}${tableCellEditRowColumnEditSuffix$}`]">
                      <we-tooltip :disabled="tableCellEditBlurSaveEdit" placement="top-start" effect="light">

                        <template slot="content">
                          <we-button
                                  :disabled="scope.row[`${scope.column.property}${tableCellEditRowColumnEditLoading$}`]"
                                  type="text" size="mini"
                                  @click="restoreTableCellEditChangeRowColumnValueAndCloseEdit(scope.row, scope.column.property)">关闭</we-button>
                          <we-button
                                  :disabled="scope.row[`${scope.column.property}${tableCellEditRowColumnEditLoading$}`] || !isTableCellEditRowColumnChange(scope.row, scope.column.property)"
                                  size="mini"
                                  @click="restoreTableCellEditChangeRowColumnValue(scope.row, scope.column.property)">还原</we-button>
                          <we-button
                                  :loading="scope.row[`${scope.column.property}${tableCellEditRowColumnEditLoading$}`]"
                                  :disabled="!isTableCellEditRowColumnChange(scope.row, scope.column.property)"
                                  type="primary" size="mini" @click="handleSaveTableCellEdit(scope)">保存</we-button>
                        </template>

                        <el-input
                                v-model="scope.row[`${scope.column.property}${tableCellEditRowColumnEditValueSuffix$}`]"
                                :placeholder="`请输入${scope.column.label}`"
                                :size="tableCellEditSize" @blur="handleBlurTableCellEditColumnChange(scope)"></el-input>
                      </we-tooltip>
                    </template>
                    <template v-else>{{scope.row[scope.column.property]}}</template>
                  </span>
                                </template>
                            </template>
                        </el-table-column>
                        <el-table-column prop="permission"
                                         label="资源许可"
                                         header-align="left"
                                         align="left"
                                         min-width="150"
                                         show-overflow-tooltip
                        >

                            <template slot="header" slot-scope="scope">
                                <span>{{scope.column.label}}</span>
                                <we-icon name="jurisdiction-shield"></we-icon>
                            </template>

                            <template slot-scope="scope">
                                <template v-if="scope.row.type !== dict.resourceTypeValue.PERMISSION"></template>
                                <template v-else>
                    <span v-loading="{
                      value: scope.row[`${scope.column.property}${tableCellEditRowColumnEditLoading$}`],
                      size: 'mini'
                    }">
                      <template
                              v-if="!scope.row[`${scope.column.property}${tableCellEditRowColumnEditLoading$}`] && scope.row[`${scope.column.property}${tableCellEditRowColumnEditSuffix$}`]">
                        <we-tooltip :disabled="tableCellEditBlurSaveEdit" placement="top-start" effect="light">

                          <template slot="content">
                            <we-button
                                    :disabled="scope.row[`${scope.column.property}${tableCellEditRowColumnEditLoading$}`]"
                                    type="text" size="mini"
                                    @click="restoreTableCellEditChangeRowColumnValueAndCloseEdit(scope.row, scope.column.property)">关闭</we-button>
                            <we-button
                                    :disabled="scope.row[`${scope.column.property}${tableCellEditRowColumnEditLoading$}`] || !isTableCellEditRowColumnChange(scope.row, scope.column.property)"
                                    size="mini"
                                    @click="restoreTableCellEditChangeRowColumnValue(scope.row, scope.column.property)">还原</we-button>
                            <we-button
                                    :loading="scope.row[`${scope.column.property}${tableCellEditRowColumnEditLoading$}`]"
                                    :disabled="!isTableCellEditRowColumnChange(scope.row, scope.column.property)"
                                    type="primary" size="mini" @click="handleSaveTableCellEdit(scope)">保存</we-button>
                          </template>

                          <el-input
                                  v-model="scope.row[`${scope.column.property}${tableCellEditRowColumnEditValueSuffix$}`]"
                                  :placeholder="`请输入${scope.column.label}`"
                                  :size="tableCellEditSize"
                                  @blur="handleBlurTableCellEditColumnChange(scope)"></el-input>
                        </we-tooltip>
                      </template>
                      <template v-else>{{scope.row[scope.column.property]}}</template>
                    </span>
                                </template>
                            </template>
                        </el-table-column>
                        <el-table-column prop="status"
                                         label="状态"
                                         align="center"
                                         width="120"
                        >
                            <template slot-scope="scope">
                                <el-switch v-model="scope.row.status"
                                           active-color="#13ce66"
                                           inactive-color="#cccccc"
                                           :active-value="dict.statusValue.NORMAL"
                                           :inactive-value="dict.statusValue.DISABLED"
                                           @change="handleChangeStatus(scope)"
                                ></el-switch>
                            </template>
                        </el-table-column>
                        <el-table-column prop="status"
                                         label="操作"
                                         fixed="right"
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
                                            您确定要<span style="color: red">删除</span>该资源及其<span
                                                style="color: red">所有子资源</span>吗？
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
                               @current-change="v => handleConfirmCloseAllTableCellEdit().then(() => {limit.currentPage = v;getTableCellEditRows();})"
                               @size-change="v => handleConfirmCloseAllTableCellEdit().then(() => {limit.pageSize = v;getTableCellEditRows();})"
                >
                </el-pagination>
            </div>
        </div>

        <we-layer v-model="tableCellEditShowAdd"
                  title="新建资源"
                  :width="80"
                  :height="80"
                  :min-width="500"
                  :show-footer="false"
                  drag
                  resize
        >
            <resource-add :module-id="moduleId"></resource-add>
        </we-layer>

    </div>
</template>

<script>

    import Global from '../../../../mixins/global.js';

    const GlobalData = Global.data();

    const Limit = {
        currentPage: 1,
        pageSizes: [10, 20, 50, 100],
        total: 0,
        pageCount: 1
    };

    const Params = {
        likeText: '',
        typeSet: [GlobalData.dict.resourceTypeValue.URL]
    };

    import ErrorPage from "../../../../../../components/error-page/src/ErrorPage.vue";
    import ResourceAdd from "./ResourceAddA.vue";

    import TableCellEdit from '../../../../../../assets/template/mixins/tableCellEdit.js';
    import ApiGarResourceUrls from "../../urls/api-gar-resource-urls.js";
    import merge from "../../../../../../utils/merge.js";
    import ApiGarRoleUrls from "../../urls/api-gar-role-urls.js";
    import ApiGarRoleResourceUrls from "../../urls/api-gar-role-resource-urls.js";
    import {isArrayContains, isArrayIntersection} from "../../../../../../utils/util.js";

    export default {
        components: {ResourceAdd, ErrorPage},
        name: "resource-manage",

        mixins: [Global, TableCellEdit],

        data() {
            return {
                limit: merge({pageSize: 20}, Limit),
                params: merge({}, Params),
                //TODO 配置你要编辑的列
                tableCellEditProps: ['name', 'type', 'url', 'permission'],
                //尺寸
                tableCellEditSize: 'medium',
                //编辑组件失焦时是否保存编辑,用于控制文本框保存形式,开启时失焦判断,关闭时由tooltip控制
                tableCellEditBlurSaveEdit: false,
                //编辑组件改变时是否保存标记,用于控制下拉框
                tableCellEditChangeSaveEdit: true,

                roleList: [],
                checkedResourceList: []
            };
        },

        props: {
            moduleId: {
                type: String,
                required: true
            }
        },

        computed: {
            enabledRoleIds() {
                let rs = [];
                for (let i in this.roleList) {
                    if (this.roleList[i].status === this.dict.statusValue.NORMAL) {
                        rs.push(this.roleList[i].id);
                    }
                }
                return rs;
            }
        },

        methods: {
            reGetTableCellEditRows() {
                merge(this.limit, Limit);
                this.getTableCellEditRows();
            },
            getTableCellEditRows() {
                this.tableCellEditLoading = true;
                new Promise((resolve, reject) => {
                    this.$Ajax.get(ApiGarResourceUrls.get.pageListResource, merge({
                        moduleId: this.moduleId,
                        typeSet: this.params.typeSet.toString()
                    }, this.limit))
                        .success(true, data => {
                            merge(this.limit, data.limit);
                            resolve(data.rows);
                        }).notSuccess(() => reject())
                        .catch(() => reject());
                }).then(rows => {
                    return new Promise((resolve, reject) => {
                        if (rows.length === 0) {
                            this.tableCellEditRows = [];
                            resolve();
                            return;
                        }
                        let ids = [];
                        rows.forEach(row => {
                            if (!row.parentIds) return true;
                            row.parentIds.split("/").forEach(id => {
                                if (id) ids.push(id);
                            });
                        });
                        if (ids.length === 0) {
                            this.tableCellEditRows = this.formatterTableCellEditRows(rows, this.tableCellEditProps, {
                                formatterRow: row => {
                                    row.parentNames = row.parentId ? '获取失败' : '-';
                                    row.expandSkeleton = true;
                                    row.roleIds = [];
                                    row.roleIds_cache = [];
                                    row.isIndeterminateRoles = false;
                                    row.isCheckAllRoles = false;
                                    row.checkRolesLoading = false;
                                }
                            });
                            resolve();
                            return;
                        }
                        this.$Ajax.get(ApiGarResourceUrls.get.listResourceIdAndNameByResourceIds, [ids.toString()], {
                            params: {
                                moduleId: this.moduleId
                            }
                        })
                            .success(true, data => {
                                const idNames = data.records;
                                this.tableCellEditRows = this.formatterTableCellEditRows(rows, this.tableCellEditProps, {
                                    formatterRow: row => {
                                        //计算父节点名称路径
                                        const parentIds = row.parentIds;
                                        if (!parentIds) {
                                            row.parentNames = '-';
                                        } else {
                                            let parentNames = '';
                                            parentIds.split("/").forEach(id => {
                                                if (!id) return true;
                                                if (idNames[id]) parentNames += ` / ${idNames[id]}`;
                                            });
                                            row.parentNames = parentNames.substring(3);
                                        }
                                        row.expandSkeleton = true;
                                        row.roleIds = [];
                                        row.roleIds_cache = [];
                                        row.isIndeterminateRoles = false;
                                        row.isCheckAllRoles = false;
                                        row.checkRolesLoading = false;
                                    }
                                });
                                resolve();
                            })
                            .notSuccess(() => {
                                this.tableCellEditRows = this.formatterTableCellEditRows(rows, this.tableCellEditProps, {
                                    formatterRow: row => {
                                        row.parentNames = row.parentId ? '获取失败' : '-';
                                        row.expandSkeleton = true;
                                        row.roleIds = [];
                                        row.roleIds_cache = [];
                                        row.isIndeterminateRoles = false;
                                        row.isCheckAllRoles = false;
                                        row.checkRolesLoading = false;
                                    }
                                });
                                resolve();
                            });
                    });
                }).then(() => this.tableCellEditSkeleton = false)
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
                    .put(ApiGarResourceUrls.put.resourceByResourceId, merge({
                        moduleId: this.moduleId,
                        resourceId: row.id
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
                    .put(ApiGarResourceUrls.put.resourceByResourceId, merge({
                        moduleId: this.moduleId,
                        resourceId: row.id
                    }, nvs))
                    .success(true, () => {
                        this.syncTableCellEditRowColumnsEditValue(row, editNames);
                    })
                    .finally(() => this.handleCloseLoadingTableCellEditRowColumns(row, props));
            },
            handleChangeStatus({row, column, $index}) {
                this.$Ajax.put(ApiGarResourceUrls.put.resourceByResourceId, {
                    moduleId: this.moduleId,
                    resourceId: row.id,
                    status: row.status
                })
                    .success(true)
                    .notSuccess(() => row.status = row.status === this.dict.statusValue.NORMAL ? this.dict.statusValue.DISABLED : this.dict.statusValue.NORMAL);
            },
            handleDeleteRow(e, vm, {row, column, $index}) {
                row[this.tableCellEditRowDeleteLoading$] = true;
                this.$Ajax.delete(ApiGarResourceUrls.delete.resourceByResourceId, {
                    moduleId: this.moduleId,
                    resourceId: row.id
                })
                    .before(() => row[this.tableCellEditRowDeleteLoading$] = false)
                    .success('删除成功', () => {
                        vm.close();
                        this.reGetTableCellEditRows();
                    });
            },
            handleSelectionChange(selection) {
                this.checkedResourceList = selection;
            },
            handleClickDeleteCheckedResources() {
                this.$confirm({
                    content: '您确定要<span style="color: red">删除</span>选中的资源吗？<p></p>该操作将同时删除<span style="color: red">所有相关数据</span>且<span style="color: red">无法恢复！！！</span>',
                    onClickConfirmButton: (e, vm) => {
                        vm.close();
                        const Loading = this.$loading({fullScreen: true, text: '删除中...请稍等'});
                        this.$Ajax.post(ApiGarResourceUrls.delete.listResourceByResourceIds, {
                            moduleId: this.moduleId,
                            resourceIds: this.checkedResourceList.map(val => val.id).toString()
                        })
                            .success(true, () => this.reGetTableCellEditRows())
                            .finally(() => Loading.close());
                    }
                });
            },
            getRoleList() {
                this.$Ajax.get(ApiGarRoleUrls.get.listRole, {moduleId: this.moduleId})
                    .success(true, data => {
                        this.roleList = data.rows;
                    });
            },
            handleExpandChange(row) {
                if (row.expandSkeleton === false) {
                    return;
                }
                this.$Ajax.get(ApiGarRoleResourceUrls.get.listRoleResourceByResourceId, [row.id], {
                    params: {moduleId: this.moduleId}
                })
                    .success(true, data => {
                        row.expandSkeleton = false;

                        if (data.rows.length > 0) {
                            let roleIds = data.rows.map(val => val.roleId);
                            row.roleIds = roleIds;
                            row.roleIds_cache = roleIds;
                            if (isArrayIntersection(row.roleIds, this.enabledRoleIds)) {
                                row.isIndeterminateRoles = true;
                            }
                            if (isArrayContains(row.roleIds, this.enabledRoleIds)) {
                                row.isIndeterminateRoles = false;
                                row.isCheckAllRoles = true;
                            }

                        }

                    })
                    .notSuccess(() => this.expandSkeleton = 500)
                    .catch(() => row.expandSkeleton = 500);
            },
            handleCheckAllRoles({row, column, $index}, val) {
                row.isIndeterminateRoles = false;
                let rs = [];
                if (val) {
                    rs = [...row.roleIds];
                    for (let i in this.roleList) {
                        if (this.roleList[i].status === this.dict.statusValue.NORMAL && row.roleIds.indexOf(this.roleList[i].id) === -1) {
                            rs.push(this.roleList[i].id);
                        }
                    }
                } else {
                    for (let i in this.roleList) {
                        if (this.roleList[i].status === this.dict.statusValue.DISABLED && row.roleIds.indexOf(this.roleList[i].id) !== -1) {
                            rs.push(this.roleList[i].id);
                        }
                    }
                }
                row.roleIds = rs;
                this.handleSaveCheckRoles(row, row.roleIds);
            },
            handleCheckRoles({row, column, $index}, val) {
                row.isIndeterminateRoles = isArrayIntersection(val, this.enabledRoleIds);
                if (isArrayContains(val, this.enabledRoleIds)) {
                    row.isIndeterminateRoles = false;
                    row.isCheckAllRoles = true;
                } else {
                    row.isCheckAllRoles = false;
                }
                this.handleSaveCheckRoles(row, val);
            },
            handleSaveCheckRoles(row, roleIds) {
                let deleteRoleIds = [];
                let addRoleIds = [];
                let cacheRoleIds = row.roleIds_cache;
                let newCacheRoleIds = [];
                //先从已经拥有的角色(缓存)中找出需要删除的角色ID,并记录新的拥有角色(缓存)
                cacheRoleIds.forEach(roleId => {
                    if (roleIds.indexOf(roleId) === -1) {
                        deleteRoleIds.push(roleId);
                    } else {
                        newCacheRoleIds.push(roleId);
                    }
                });
                //再使用保存的角色ID和已经拥有的角色ID匹配找出需要新增的ID,并记录新的拥有角色(缓存)
                roleIds.forEach(roleId => {
                    if (cacheRoleIds.indexOf(roleId) === -1) {
                        addRoleIds.push(roleId);
                        newCacheRoleIds.push(roleId);
                    }
                });
                if (deleteRoleIds.length === 0 && addRoleIds.length === 0) return;
                if (deleteRoleIds.length > 0 && addRoleIds.length === 0) {
                    row.checkRolesLoading = true;
                    this.$Ajax.delete(ApiGarRoleResourceUrls.delete.listRoleResourceByRoleIdsAndResourceIds, {
                        moduleId: this.moduleId,
                        roleIds: deleteRoleIds.toString(),
                        resourceIds: row.id
                    })
                        .success(true, () => row.roleIds_cache = newCacheRoleIds)
                        .finally(() => row.checkRolesLoading = false);
                    return;
                }
                if (addRoleIds.length > 0 && deleteRoleIds.length === 0) {
                    row.checkRolesLoading = true;
                    this.$Ajax.post(ApiGarRoleResourceUrls.post.listRoleResourceByRoleIdsAndResourceIds, {
                        moduleId: this.moduleId,
                        roleIds: addRoleIds.toString(),
                        resourceIds: row.id
                    })
                        .success(true, () => row.roleIds_cache = newCacheRoleIds)
                        .finally(() => row.checkRolesLoading = false);
                    return;
                }
                row.checkRolesLoading = true;
                new Promise((resolve, reject) => {
                    this.$Ajax.delete(ApiGarRoleResourceUrls.delete.listRoleResourceByRoleIdsAndResourceIds, {
                        moduleId: this.moduleId,
                        roleIds: deleteRoleIds.toString(),
                        resourceIds: row.id
                    })
                        .success(true, resolve)
                        .notSuccess(() => row.checkRolesLoading = false);
                }).then(() => {
                    this.$Ajax.post(ApiGarRoleResourceUrls.post.listRoleResourceByRoleIdsAndResourceIds, {
                        moduleId: this.moduleId,
                        roleIds: addRoleIds.toString(),
                        resourceIds: row.id
                    })
                        .success(true, () => row.roleIds_cache = newCacheRoleIds)
                        .finally(() => row.checkRolesLoading = false);
                });
            },
            handleDbClickCell(row, column, cell, event) {
                if (row.type === this.dict.resourceTypeValue.NODE && column.property !== 'name') {
                    return;
                }
                if (row.type === this.dict.resourceTypeValue.URL && column.property === 'permission') {
                    return;
                }
                if (row.type === this.dict.resourceTypeValue.PERMISSION && column.property === 'url') {
                    return;
                }
                this.handleOpenTableCellEditRowColumnEdit(row, column.property);
            }
        },
        created() {
            this.getRoleList();
            this.getTableCellEditRows();
        }
    }
</script>
<style scoped>
    .btn-p-left {
        padding-left: 3px;
    }

    .list-tools {
        padding: 13px;
        background-color: #FAFAFA;
        border-top: 1px solid #EEEEEE;
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

    .text-selection *::selection {
        background-color: transparent;
    }

    .text-selection *::-moz-selection {
        background-color: transparent;
    }
</style>
