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
             class="gar-list-main is-tools-bar is-border">
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
                <div class="gar-list-main-inner is-border">
                    <el-table :data="tableCellEditRows"
                              :size="tableCellEditSize"
                              ref="tableResource"
                              height="100%"
                              @expand-change="handleExpandChange"
                              @cell-dblclick="handleDbClickCell"
                              @selection-change="handleSelectionChange"
                    >
                        <template slot="empty">
                            <error-page :image-width="180" :image-height="180"></error-page>
                        </template>
                        <el-table-column type="selection" width="40"></el-table-column>
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
                                <div @click="openRows(scope)" class="common-tal">
                                    <span :style="{'padding-left': `${scope.row.indent}px`}">
                                        <we-icon v-if="!scope.row.isOpen" name="caret-right"></we-icon>
                                        <we-icon v-else name="caret-down"></we-icon>
                                    </span>
                                    {{scope.row.name}}
                                </div>
                            </template>
                        </el-table-column>
                        <el-table-column prop="isOpen"
                                         label="资源名称"
                                         align="center"
                                         show-overflow-tooltip
                                         width="150"
                        >
                        </el-table-column>
                        <el-table-column prop="type"
                                         label="资源类型"
                                         align="center"
                                         show-overflow-tooltip
                                         width="180"
                        >
                            <template slot-scope="scope">
                                {{scope.row.type}}
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
                                {{scope.row.url}}
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
                                <span class="">{{scope.column.label}}</span>
                                <we-icon name="jurisdiction-shield"></we-icon>
                            </template>

                            <template slot-scope="scope">
                                {{scope.row.permission}}
                            </template>
                        </el-table-column>
                        <el-table-column prop="operation"
                                         fixed="right"
                                         label="操作"
                                         width="120"
                                         show-overflow-tooltip
                        >
                            <template slot-scope="scope">
                                <we-button type="text" @click="roleListShow = true">角色管理</we-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </div>
            </we-skeleton>
        </div>

        <we-layer v-model="roleListShow"
                  title="角色管理"
                  :width="800"
                  :height="60"
                  drag
                  resize
                  confirmButtonText="保存"
        >
            <div style="padding: 16px;">
                <el-checkbox :indeterminate="isIndeterminate" v-model="roleCheckAll" @change="handleCheckAllChange">全选
                </el-checkbox>
            </div>

            <el-checkbox-group v-model="checkedRoles" @change="handleCheckedRolesChange">
                <ul class="gar-list-ul" style="padding: 0 8px;">
                    <li v-for="role in roles">
                        <el-checkbox :label="role.label" :key="role.key" border size="medium" style="width: 100%">
                            {{role.label}}
                        </el-checkbox>
                    </li>
                </ul>
            </el-checkbox-group>
        </we-layer>

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

    const Params = {
        likeText: '',
        typeSet: [GlobalData.dict.resourceTypeValue.URL]
    };

    const RolesOptions = [{
        label: "超级管理员",
        key: "1"
    }, {
        label: "开发人员",
        key: "2"
    }, {
        label: "Vip用户",
        key: "3"
    }];

    import ErrorPage from "../../../../../../components/error-page/src/ErrorPage.vue";
    import ResourceAdd from "./ResourceAddA.vue";

    import TableCellEdit from '../../../../../../assets/template/mixins/tableCellEdit.js';
    import ApiGarResourceUrls from "../../urls/api-gar-resource-urls.js";
    import merge from "../../../../../../utils/merge.js";
    import ApiGarRoleUrls from "../../urls/api-gar-role-urls.js";
    import ApiGarRoleResourceUrls from "../../urls/api-gar-role-resource-urls.js";
    import {isArrayContains, isArrayIntersection} from "../../../../../../utils/util.js";
    import {isEmpty} from "../../../../../../utils/util";

    export default {
        components: {ResourceAdd, ErrorPage},
        name: "resource-manage",

        mixins: [Global, TableCellEdit],

        data() {
            return {
                params: merge({}, Params),
                //TODO 配置你要编辑的列
                tableCellEditProps: ['name', 'type', 'url', 'permission'],
                //尺寸
                tableCellEditSize: 'medium',
                //编辑组件失焦时是否保存编辑,用于控制文本框保存形式,开启时失焦判断,关闭时由tooltip控制
                tableCellEditBlurSaveEdit: false,
                //编辑组件改变时是否保存标记,用于控制下拉框
                tableCellEditChangeSaveEdit: true,

                roleCheckAll: false,
                roleListShow: false,
                roleList: [],
                checkedRoles: [],
                roles: RolesOptions,
                isIndeterminate: false,
                checkedResourceList: [],

                rows: [{
                    id: '1',
                    parentId: '',
                    name: '通用权限',
                    type: 'NODE',
                    url: '',
                    permission: ''
                }, {
                    id: '2',
                    parentId: '',
                    name: '基础权限',
                    type: 'NODE',
                    url: '',
                    permission: ''
                }, {
                    id: '3',
                    parentId: '',
                    name: '账户权限',
                    type: 'NODE',
                    url: '',
                    permission: ''
                }, {
                    id: '4',
                    parentId: '',
                    name: '模块权限',
                    type: 'NODE',
                    url: '',
                    permission: ''
                }, {
                    id: '5',
                    parentId: '',
                    name: '对象权限',
                    type: 'NODE',
                    url: '',
                    permission: ''
                }, {
                    id: '6',
                    parentId: '',
                    name: '角色权限',
                    type: 'NODE',
                    url: '',
                    permission: ''
                }, {
                    id: '7',
                    parentId: '',
                    name: '对象角色权限',
                    type: 'NODE',
                    url: '',
                    permission: ''
                }, {
                    id: '8',
                    parentId: '',
                    name: '资源权限',
                    type: 'NODE',
                    url: '',
                    permission: ''
                }, {
                    id: '9',
                    parentId: '2',
                    name: '角色资源权限',
                    type: 'NODE',
                    url: '',
                    permission: ''
                }, {
                    id: '10',
                    parentId: '2',
                    name: '获取当前在线信息',
                    type: 'URL',
                    url: '/api-gar/get/online',
                    permission: ''
                }, {
                    id: '11',
                    parentId: '2',
                    name: '登录',
                    type: 'URL',
                    url: '/api-gar/post/login',
                    permission: ''
                }, {
                    id: '12',
                    parentId: '2',
                    name: '登出',
                    type: 'URL',
                    url: '/api-gar/get/logout',
                    permission: ''
                }, {
                    id: '13',
                    parentId: '3',
                    name: '查询权限',
                    type: 'URL',
                    url: '/api-gar-account-number/get/**',
                    permission: ''
                }, {
                    id: '14',
                    parentId: '3',
                    name: '新增权限',
                    type: 'URL',
                    url: '/api-gar-account-number/post/**',
                    permission: ''
                }, {
                    id: '15',
                    parentId: '3',
                    name: '修改权限',
                    type: 'URL',
                    url: '/api-gar-account-number/put/**',
                    permission: ''
                }, {
                    id: '16',
                    parentId: '3',
                    name: '修改权限',
                    type: 'URL',
                    url: '',
                    permission: ''
                }, {
                    id: '17',
                    parentId: '4',
                    name: '查询权限',
                    type: 'URL',
                    url: '',
                    permission: ''
                }, {
                    id: '18',
                    parentId: '4',
                    name: '新增权限',
                    type: 'URL',
                    url: '',
                    permission: ''
                }, {
                    id: '19',
                    parentId: '4',
                    name: '修改权限',
                    type: 'URL',
                    url: '',
                    permission: ''
                }, {
                    id: '20',
                    parentId: '4',
                    name: '修改权限',
                    type: 'URL',
                    url: '',
                    permission: ''
                }],

                currRows: []
            }
        },

        props: {
            moduleId: {
                type: String,
                required: true
            }
        },

        computed: {
            enabledRoleIds() {

            }
        },

        methods: {
            handleCheckAllChange(val) {
                this.checkedRoles = val ? RolesOptions : [];
                this.isIndeterminate = false;
            },
            handleCheckedRolesChange(val) {
                let checkedCount = val.length;
                this.roleCheckAll = checkedCount === this.roles.length;
                this.isIndeterminate = checkedCount > 0 && checkedCount < this.roles.length;
            },
            reGetTableCellEditRows() {

            },
            getTableCellEditRows() {
                this.tableCellEditLoading = true;
                new Promise((resolve, reject) => {
                    if (this.rows.length === 0) {
                        this.tableCellEditRows = [];
                        resolve();
                        return;
                    }

                    this.rows.forEach((row) => {
                        if (isEmpty(row.parentId)) {
                            this.currRows.push(row);
                        }
                    });

                    this.tableCellEditRows = this.formatterTableCellEditRows(this.currRows, this.tableCellEditProps, {
                        formatterRow: row => {
                            row.parentNames = row.parentId ? '获取失败' : '-';
                            row.expandSkeleton = true;
                            row.roleIds = [];
                            row.roleIds_cache = [];
                            row.isIndeterminateRoles = false;
                            row.isCheckAllRoles = false;
                            row.checkRolesLoading = false;
                            row.isOpen = false;
                            row.indent = 0;
                            row.checked = false;
                        }
                    });
                    resolve();

                }).then(() => this.tableCellEditSkeleton = false)
                    .catch(() => this.tableCellEditSkeleton = 500)
                    .finally(() => this.tableCellEditLoading = false);
            },
            openRows({row, $index}) {
                if (!row.isOpen) {
                    row.isOpen = true;
                    let subRow = [];
                    this.rows.forEach((item) => {
                        if (item.parentId === row.id) {
                            subRow.push(merge({
                                parentNames: item.parentId ? '获取失败' : '-',
                                expandSkeleton: true,
                                roleIds: [],
                                roleIds_cache: [],
                                isIndeterminateRoles: false,
                                isCheckAllRoles: false,
                                checkRolesLoading: false,
                                isOpen: false,
                                indent: 10,
                                checked: false
                            }, item));
                        }
                    });
                    this.tableCellEditRows.splice($index + 1, 0, ...subRow);
                } else {
                    row.isOpen = false;
                    let delRowsIndex = 0;
                    this.tableCellEditRows.forEach((item) => {
                        if (item.parentId === row.id) {
                            delRowsIndex++;
                        }
                    });
                    this.tableCellEditRows.splice($index + 1, delRowsIndex);
                }
            },
            handleSaveTableCellEdit({row, column, $index}) {

            },
            handleSaveTableRowEdit({row, column, $index}, props = this.tableCellEditProps) {

            },
            handleChangeStatus({row, column, $index}) {

            },
            handleDeleteRow(e, vm, {row, column, $index}) {

            },
            handleSelectionChange(selection) {
                this.checkedResourceList = selection;
            },
            handleClickDeleteCheckedResources() {

            },
            getRoleList() {

            },
            handleExpandChange(row) {

            },
            handleCheckAllRoles({row, column, $index}, val) {

            },
            handleCheckRoles({row, column, $index}, val) {

            },
            handleSaveCheckRoles(row, roleIds) {

            },
            handleDbClickCell(row, column, cell, event) {

            }
        },
        created() {
            this.getTableCellEditRows();
        }
    }
</script>
