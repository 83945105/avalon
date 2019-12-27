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
                               @click="reGetTableCellEditRows">查询
                    </we-button>
                </li>
            </ul>
            <div class="common-clear"></div>
            <!--/查询条件-->
        </div>
        <!--/查询-->

        <div v-loading="tableCellEditLoading && tableCellEditSkeleton===false"
             class="gar-list-main is-border is-paging-size-small">
            <we-skeleton v-model="tableCellEditSkeleton"
                         animation
                         :paragraph-rows="10"
                         :paragraph-width="'100%'"
                         @reload="reGetTableCellEditRows"
            >
                <div class="gar-list-main-inner">
                    <el-table :ref="tableCellEditRef"
                              :data="tableCellEditRows"
                              stripe
                              @expand-change="handleExpandChange"
                              @selection-change="handleSelectionChange"
                              height="100%"
                              style="overflow: inherit"
                    >
                        <el-table-column type="selection" width="40"></el-table-column>
                        <el-table-column v-if="roleList && roleList.length > 0" type="expand" label="拥有角色列表"
                                         width="120px">
                            <template slot-scope="scope">
                                <we-skeleton animation v-model="scope.row.expandSkeleton"
                                             @reload="handleExpandChange(scope.row)">
                                    <span v-loading="scope.row.checkRolesLoading" style="display: block">
                                      <el-checkbox :indeterminate="scope.row.isIndeterminateRoles"
                                                   v-model="scope.row.isCheckAllRoles"
                                                   :disabled="scope.row.checkRolesLoading"
                                                   @change="val => handleCheckAllRoles(scope, val)"
                                                   style="margin-left: 10px;margin-bottom: 10px"
                                      >全选
                                      </el-checkbox>
                                      <el-tag size="small" style="margin-left: 10px;margin-bottom: 10px">点击按钮可以将点击角色授予给对象</el-tag>
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
                        <template v-for="(row, index) in tableOptions.tableColumns">
                            <el-table-column :label="row.label"
                                             :column-key="row.prop"
                                             :prop="row.prop"
                                             :width="row.width"
                                             :align="row.align"></el-table-column>
                        </template>
                        <el-table-column label="状态" :min-width="15" align="center" fixed="right">
                            <template slot-scope="scope">
                                <el-switch v-model="scope.row.status"
                                           :active-value="dict.statusValue.NORMAL"
                                           :inactive-value="dict.statusValue.DISABLED"
                                           active-color="#13ce66"
                                           inactive-color="#cccccc"
                                           @change="handleChangeStatus(scope)"
                                ></el-switch>
                            </template>
                        </el-table-column>
                    </el-table>
                </div>
            </we-skeleton>

            <!--分页-->
            <div class="gar-list-main-paging gar-paging gar-paging-size-small">
                <el-pagination layout="total, sizes, prev, pager, next, jumper"
                               :current-page="limit.currentPage"
                               :page-size="limit.pageSize"
                               :total="limit.total"
                               :page-count="limit.pageCount"
                               :page-sizes="[10, 50, 100, 200, 300, 400]"
                               @current-change="v => {limit.currentPage = v;getTableCellEditRows();}"
                               @size-change="v => {limit.pageSize = v;getTableCellEditRows();}"
                >
                </el-pagination>
            </div>
            <!--/分页-->
        </div>
    </div>
</template>

<script>

    import Global from '../../../../mixins/global.js';
    import TableCellEdit from '../../../../../../assets/template/mixins/tableCellEdit.js';
    import ApiGarObjectUrls from "../../urls/api-gar-object-urls";
    import merge from "../../../../../../utils/merge";
    import ApiGarRoleUrls from "../../urls/api-gar-role-urls";
    import ApiGarObjectRoleUrls from "../../urls/api-gar-object-role-urls";
    import {isArrayContains, isArrayIntersection} from "../../../../../../utils/util";

    const Limit = {
        currentPage: 1,
        pageSize: 50,
        total: 0,
        pageCount: 1
    };

    export default {

        name: "object-manage",

        mixins: [Global, TableCellEdit],

        props: {
            moduleId: {
                type: String,
                required: true
            }
        },

        data() {
            return {
                tableOptions: {
                    tableColumns: []
                },
                params: {
                    likeText: ''
                },
                limit: merge({}, Limit),
                roleList: [],
                checkedObjectList: []
            };
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
            getTableOptions() {
                this.$Ajax.get(ApiGarObjectUrls.get.tableOptions)
                    .success(true, data => {
                        this.tableOptions = data.records.table;
                        this.reGetTableCellEditRows();
                    });
            },
            reGetTableCellEditRows() {
                merge(this.limit, Limit);
                this.getTableCellEditRows();
            },
            getTableCellEditRows() {
                this.tableCellEditLoading = true;
                new Promise((resolve, reject) => {
                    this.$Ajax.get(ApiGarObjectUrls.get.pageListObject, null, {
                        params: merge({moduleId: this.moduleId}, this.params, this.limit)
                    })
                        .success(true, data => {
                            merge(this.limit, data.limit);
                            resolve(data.rows);
                        })
                        .notSuccess(() => reject())
                        .catch(() => reject());
                }).then(rows => {
                    return new Promise((resolve, reject) => {
                        this.tableCellEditRows = this.formatterTableCellEditRows(rows, this.tableCellEditProps, {
                            formatterRow: row => {
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
                }).then(() => this.tableCellEditSkeleton = false)
                    .catch(() => this.tableCellEditSkeleton = 500)
                    .finally(() => this.tableCellEditLoading = false);
            },
            handleChangeStatus({row, column, $index}) {
                if (row.status === this.dict.statusValue.DISABLED) {
                    this.$Ajax.put(ApiGarObjectUrls.put.objectDisabledByObjectId, {
                        moduleId: this.moduleId,
                        objectId: row.id
                    })
                        .success(true)
                        .notSuccess(() => row.status = row.status === this.dict.statusValue.NORMAL ? this.dict.statusValue.DISABLED : this.dict.statusValue.NORMAL);
                } else if (row.status === this.dict.statusValue.NORMAL) {
                    this.$Ajax.put(ApiGarObjectUrls.put.objectEnabledByObjectId, {
                        moduleId: this.moduleId,
                        objectId: row.id
                    })
                        .success(true)
                        .notSuccess(() => row.status = row.status === this.dict.statusValue.NORMAL ? this.dict.statusValue.DISABLED : this.dict.statusValue.NORMAL);
                }
            },
            handleSelectionChange(selection) {
                this.checkedObjectList = selection;
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
                this.$Ajax.get(ApiGarObjectRoleUrls.get.listObjectRoleByObjectId, [row.id], {
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
                    this.$Ajax.delete(ApiGarObjectRoleUrls.delete.listObjectRoleByObjectIdsAndRoleIds, {
                        moduleId: this.moduleId,
                        objectIds: row.id,
                        roleIds: deleteRoleIds.toString()
                    })
                        .success(true, () => row.roleIds_cache = newCacheRoleIds)
                        .finally(() => row.checkRolesLoading = false);
                    return;
                }
                if (addRoleIds.length > 0 && deleteRoleIds.length === 0) {
                    row.checkRolesLoading = true;
                    this.$Ajax.post(ApiGarObjectRoleUrls.post.listObjectRoleByObjectIdsAndRoleIds, {
                        moduleId: this.moduleId,
                        objectIds: row.id,
                        roleIds: addRoleIds.toString()
                    })
                        .success(true, () => row.roleIds_cache = newCacheRoleIds)
                        .finally(() => row.checkRolesLoading = false);
                    return;
                }
                row.checkRolesLoading = true;
                new Promise((resolve, reject) => {
                    this.$Ajax.delete(ApiGarObjectRoleUrls.delete.listObjectRoleByObjectIdsAndRoleIds, {
                        moduleId: this.moduleId,
                        objectIds: row.id,
                        roleIds: deleteRoleIds.toString()
                    })
                        .success(true, resolve)
                        .notSuccess(() => row.checkRolesLoading = false);
                }).then(() => {
                    this.$Ajax.post(ApiGarObjectRoleUrls.post.listObjectRoleByObjectIdsAndRoleIds, {
                        moduleId: this.moduleId,
                        objectIds: row.id,
                        roleIds: addRoleIds.toString()
                    })
                        .success(true, () => row.roleIds_cache = newCacheRoleIds)
                        .finally(() => row.checkRolesLoading = false);
                });
            }
        },

        created() {
            this.getTableOptions();
            this.getRoleList();
        }

    }
</script>