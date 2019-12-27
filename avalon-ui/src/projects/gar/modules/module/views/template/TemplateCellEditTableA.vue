<template>
    <we-skeleton v-model="tableCellEditSkeleton"
                 animation
                 :paragraph-rows="10"
                 :title-width="'50%'"
                 :paragraph-width="'100%'"
                 @reload="reloadTableCellEditRows"
    >
        <div v-loading="tableCellEditLoading && tableCellEditSkeleton === false" class="gar-list-main-inner">
            <el-table :ref="tableCellEditRef"
                      :data="tableCellEditRows"
                      :size="tableCellEditSize"
                      :row-key="row => row.id"
                      width="100%"
                      height="100%"
                      @cell-dblclick="(row, column, cell, event) => handleOpenTableCellEditRowColumnEdit(row, column.property)"
                      @selection-change="handleSelectionChange"
            >
                <template slot="empty">
                    <error-page :image-width="180" :image-height="180"></error-page>
                </template>
                <slot name="table-column-before"></slot>
                <el-table-column prop="subModuleName"
                                 label="所属子模块名称"
                                 align="center"
                                 show-overflow-tooltip>
                </el-table-column>
                <el-table-column prop="name"
                                 label="模板名称"
                                 align="center"
                                 show-overflow-tooltip>
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
                                 label="模板唯一标识符"
                                 align="center"
                                 show-overflow-tooltip>
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
                                 label="模板类型"
                                 align="center"
                                 show-overflow-tooltip>
                    <template slot-scope="scope">
        <span v-loading="{
          value: scope.row[`${scope.column.property}${tableCellEditRowColumnEditLoading$}`],
          size: 'mini'
        }">
          <template
                  v-if="!scope.row[`${scope.column.property}${tableCellEditRowColumnEditLoading$}`] && scope.row[`${scope.column.property}${tableCellEditRowColumnEditSuffix$}`]">
            <view-type-select :size="tableCellEditSize"
                              v-model="scope.row[`${scope.column.property}${tableCellEditRowColumnEditValueSuffix$}`]"
                              @change="() => {
                                if(tableCellEditChangeSaveEdit !== true) return;
                                handleSaveTableCellEdit(scope);
                              }"
            ></view-type-select>
          </template>
          <template v-else>{{dict.templateTypeLabel[scope.row[scope.column.property]]}}</template>
        </span>
                    </template>
                </el-table-column>
                <el-table-column prop="description"
                                 label="模板描述"
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
                <we-button
                        :disabled="scope.row[`${scope.column.property}${tableCellEditRowColumnEditLoading$}`]"
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
                <el-table-column prop="status"
                                 label="状态"
                                 align="center"
                                 show-overflow-tooltip>
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
                                 width="405"
                                 show-overflow-tooltip
                >
                    <template slot-scope="scope">
                        <div class="common-tal">
                            <we-button type="text"
                                       size="mini"
                                       :disabled="!isEnableTableCellEditConfirmEditRow(scope, tableCellEditProps)"
                                       @click="handleOpenTableCellEditRowColumnsEdit(scope.row, tableCellEditProps)">
                                编辑此行
                            </we-button>
                            <we-button type="text"
                                       size="mini"
                                       :disabled="!isEnableTableCellEditCancelEditRow(scope, tableCellEditProps)"
                                       @click="handleCloseEditAndLoadingTableCellEditRowColumns(scope.row, tableCellEditProps)">
                                取消编辑此行
                            </we-button>
                            <we-button type="text"
                                       size="mini"
                                       :disabled="!isEnableTableCellEditSaveEditRow(scope, tableCellEditProps)"
                                       @click="handleSaveTableRowEdit(scope, tableCellEditProps)">
                                保存此行
                            </we-button>
                            <we-button type="text" size="small"
                                       @click="showEdit(scope)">
                                编辑
                            </we-button>
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
                                        您确定要<span style="color: red">删除</span>该模板<span style="color: red">及相关数据</span>吗？
                                        <div style="color: red">删除后不可恢复！！！</div>
                                    </template>
                                </template>
                                <we-button type="text" size="small">删除</we-button>
                            </we-popover>
                            <span style="padding-left:8px">
                <we-button v-if="scope.$index > 0" :loading="orderLoading" shape="circle"
                           size="mini"
                           icon-name="arrow-up"
                           @click="handleSwitchOrder({source: scope.row, sourceIndex: scope.$index, target: tableCellEditRows[scope.$index - 1], targetIndex: scope.$index - 1})"></we-button>
                <we-button v-if="scope.$index < tableCellEditRows.length - 1" :loading="orderLoading" shape="circle"
                           size="mini"
                           icon-name="arrow-down"
                           @click="handleSwitchOrder({source: scope.row, sourceIndex: scope.$index, target: tableCellEditRows[scope.$index + 1], targetIndex: scope.$index + 1})"></we-button>
                <we-button shape="circle"
                           size="mini"
                           icon-name="gar-drag-all"
                           @click="" style="cursor: move" class="cursor-move"></we-button>
              </span>
                        </div>
                    </template>
                </el-table-column>
            </el-table>

            <!--新增模板-->
            <we-layer v-model="tableCellEditShowAdd"
                      title="新增模板"
                      :width="50"
                      :height="65"
                      :min-width="500"
                      :show-footer="false"
                      drag
                      resize
            >
                <template-add :module-id="moduleId"
                              @submit-success="(view, vm) => $emit('table-cell-edit-add-success', {row: view, vm: vm})">
                    <template slot="button">
                        <el-button @click="tableCellEditShowAdd = false">关闭</el-button>
                    </template>
                </template-add>
            </we-layer>
            <!--/新增模板-->

            <!--修改模板-->
            <we-layer v-model="tableCellEditShowEdit"
                      title="编辑模板"
                      :width="50"
                      :height="65"
                      :min-width="500"
                      :show-footer="false"
                      drag
                      resize
            >
                <template-edit :module-id="moduleId"
                               :data="tableCellEditEditRow"
                               @submit-success="handleSubmitEditRowSuccess">
                    <template slot="button">
                        <el-button @click="tableCellEditShowEdit = false">关闭</el-button>
                    </template>
                </template-edit>
            </we-layer>
            <!--/修改模板-->
        </div>
    </we-skeleton>
</template>

<script>

    import Global from '../../../../mixins/global.js';
    import ErrorPage from "../../../../../../components/error-page/src/ErrorPage.vue";
    import TemplateAdd from "./TemplateAddA.vue";
    import TemplateEdit from "./TemplateEditA.vue";
    import ViewTypeSelect from "../../components/sub-module-select/src/ViewTypeSelect.vue";

    import TableCellEdit from '../../../../../../assets/template/mixins/tableCellEdit.js';
    import ApiGarTemplateUrls from "../../urls/api-gar-template-urls.js";
    import merge from "../../../../../../utils/merge.js";

    export default {

        name: "template-cell-edit-table",

        components: {ViewTypeSelect, TemplateEdit, TemplateAdd, ErrorPage},

        mixins: [Global, TableCellEdit],

        props: {
            moduleId: {
                type: String,
                required: true
            },
            sortableOptions: {
                type: Object,
                default() {
                    return {
                        ghostClass: 'sortable-ghost',
                        handle: '.cursor-move',
                        animation: 150,
                        setData: function (dataTransfer, dragEl) {
                            dataTransfer.setData('Text', dragEl.textContent);
                        },
                        onEnd: (evt) => {
                            let {item, to, from, oldIndex, newIndex, oldDraggableIndex, newDraggableIndex, clone, pullMode} = evt;
                            if (oldDraggableIndex === newDraggableIndex) return;
                            this.handleDragTemplateListRow({
                                sourceIndex: oldDraggableIndex,
                                targetIndex: newDraggableIndex
                            })
                        }
                    }
                }
            }
        },

        data() {
            return {
                //TODO 配置你要编辑的列
                tableCellEditProps: ['name', 'value', 'type', 'description'],
                //尺寸
                tableCellEditSize: 'medium',
                //编辑组件失焦时是否保存编辑,用于控制文本框保存形式,开启时失焦判断,关闭时由tooltip控制
                tableCellEditBlurSaveEdit: false,
                //编辑组件改变时是否保存标记,用于控制下拉框
                tableCellEditChangeSaveEdit: true,

                orderLoading: false

            };
        },

        methods: {
            showAdd() {
                this.handleConfirmCloseAllTableCellEdit().then(() => this.tableCellEditShowAdd = true);
            },
            showEdit({row, $index}) {
                this.handleConfirmCloseAllTableCellEdit().then(() => {
                    this.tableCellEditEditRow = row;
                    this.tableCellEditEditIndex = $index;
                    this.tableCellEditShowEdit = true;
                });
            },
            appendRows(rows) {
                this.appendTableCellEditRows(rows, this.tableCellEditProps);
            },
            handleSaveTableCellEdit({row, column, $index}) {
                let nvs = this.getTableCellEditChangeRowColumnNewValues(row, [column.property]);
                const editNames = Object.getOwnPropertyNames(nvs);
                this.handleCloseEditAndLoadingTableCellEditRowColumn(row, column.property);
                if (editNames.length === 0) return;
                this.handleOpenTableCellEditRowColumnsLoading(row, editNames);
                this.$Ajax
                    .put(ApiGarTemplateUrls.put.templateByTemplateId, merge({
                        moduleId: this.moduleId,
                        subModuleId: row.subModuleId,
                        templateId: row.id
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
                    .put(ApiGarTemplateUrls.put.templateByTemplateId, merge({
                        moduleId: this.moduleId,
                        subModuleId: row.subModuleId,
                        templateId: row.id
                    }, nvs))
                    .success(true, () => {
                        this.syncTableCellEditRowColumnsEditValue(row, editNames);
                    })
                    .finally(() => this.handleCloseLoadingTableCellEditRowColumns(row, props));
            },
            handleChangeStatus({row, column, $index}) {
                this.$Ajax.put(ApiGarTemplateUrls.put.templateByTemplateId, {
                    moduleId: this.moduleId,
                    templateId: row.id,
                    status: row.status
                })
                    .success(true)
                    .notSuccess(() => row.status = row.status === this.dict.statusValue.NORMAL ? this.dict.statusValue.DISABLED : this.dict.statusValue.NORMAL);
            },
            handleDeleteRow(e, vm, {row, column, $index}) {
                row[this.tableCellEditRowDeleteLoading$] = true;
                this.$Ajax.delete(ApiGarTemplateUrls.delete.templateByTemplateId, {
                    moduleId: this.moduleId,
                    templateId: row.id
                })
                    .before(() => row[this.tableCellEditRowDeleteLoading$] = false)
                    .success('删除成功', () => {
                        this.tableCellEditRows.splice($index, 1);
                        vm.close();
                    });
            },
            handleSwitchOrder({source, sourceIndex, target, targetIndex}) {
                this.orderLoading = true;
                this.$Ajax.put(ApiGarTemplateUrls.put.switchTemplateIndexByTemplateId, {
                    moduleId: this.moduleId,
                    sourceTemplateId: source.id,
                    targetTemplateId: target.id
                })
                    .success(true, () => {
                        this.$set(this.tableCellEditRows, sourceIndex, target);
                        this.$set(this.tableCellEditRows, targetIndex, source);
                    })
                    .finally(() => this.orderLoading = false);
            },
            handleDragTemplateListRow({sourceIndex, targetIndex}) {
                if (sourceIndex === targetIndex) {
                    return;
                }
                let sourceTemplate = this.tableCellEditRows[sourceIndex];
                let targetTemplate = this.tableCellEditRows[targetIndex];
                this.orderLoading = true;
                let cache = this.tableCellEditRows;
                this.$Ajax.put(ApiGarTemplateUrls.put.dragTemplateListRow, {
                    moduleId: this.moduleId,
                    dragTemplateId: sourceTemplate.id,
                    dragSubModuleId: sourceTemplate.subModuleId,
                    dropTemplateId: targetTemplate.id,
                    dropSubModuleId: targetTemplate.subModuleId,
                    dropType: sourceIndex > targetIndex ? 'before' : 'after'
                })
                    .success(true, () => {
                        this.tableCellEditRows.splice(targetIndex, 0, this.tableCellEditRows.splice(sourceIndex, 1)[0]);
                    })
                    .notSuccess(() => {
                        this.tableCellEditRows = [];
                        this.$nextTick(() => {
                            this.tableCellEditRows = cache;
                        });
                    })
                    .catch(() => {
                        this.tableCellEditRows = [];
                        this.$nextTick(() => {
                            this.tableCellEditRows = cache;
                        });
                    })
                    .finally(() => this.orderLoading = false);
            },
            handleSubmitEditRowSuccess(view, vm) {
                merge(this.tableCellEditRows[this.tableCellEditEditIndex], view);
                this.tableCellEditShowEdit = false
            },
            handleSelectionChange(selection) {
                this.$emit('table-selection-change', selection, this);
            }
        }
    }
</script>

<style type="text/css">
    .sortable-ghost {
        opacity: 0.8 !important;
        background-color: #fff4bc !important;
    }
</style>
