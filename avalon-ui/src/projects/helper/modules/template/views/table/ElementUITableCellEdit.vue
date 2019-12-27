<template>
  <div>
    <div style="margin: 10px 10px 10px 10px">
      <we-button :disabled="tableCellEditCount === 0" type="danger"
                 @click="handleCloseAllEditAndLoadingTableCellEditRowColumns(tableCellEditProps)">取消所有编辑
      </we-button>
    </div>
    <div v-loading="tableCellEditLoading && tableCellEditSkeleton === false" style="height: 500px">
      <we-skeleton v-model="tableCellEditSkeleton"
                   animation
                   :paragraph-rows="8"
                   @reload="getTableCellEditRows">
        <el-table :data="tableCellEditRows"
                  :size="tableCellEditSize"
                  fit
                  border
                  height="100%"
                  @cell-dblclick="(row, column, cell, event) => handleOpenTableCellEditRowColumnEdit(row, column.property)"
        >
          <template slot="empty">
            <error-page :image-width="180" :image-height="180"></error-page>
          </template>
          <el-table-column prop="canEdit1"
                           label="可编辑文本框"
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
          <el-table-column prop="canEdit2"
                           label="可编辑数字框"
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
                  <we-tooltip :disabled="tableCellEditBlurSaveEdit" placement="top-start"
                              effect="light">
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

                    <el-input-number
                      v-model="scope.row[`${scope.column.property}${tableCellEditRowColumnEditValueSuffix$}`]"
                      :min="0"
                      :size="tableCellEditSize" @blur="handleBlurTableCellEditColumnChange(scope)"></el-input-number>
                  </we-tooltip>
                </template>
                <template v-else>{{scope.row[scope.column.property]}}</template>
              </span>
            </template>
          </el-table-column>
          <el-table-column prop="name"
                           label="不可编辑列"
                           align="center"
                           show-overflow-tooltip
          >
          </el-table-column>
          <el-table-column prop="canEdit3"
                           label="可编辑下拉框"
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
                    <el-option label="A" value="A"></el-option>
                    <el-option label="B" value="B"></el-option>
                    <el-option label="C" value="C"></el-option>
                  </el-select>
                </template>
                <template v-else>{{scope.row[scope.column.property]}}</template>
              </span>
            </template>
          </el-table-column>
          <el-table-column prop="status"
                           label="操作"
                           align="center"
                           width="225"
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
            </template>
          </el-table-column>
        </el-table>
      </we-skeleton>
    </div>
  </div>
</template>

<script>

  import TableCellEdit from '../../../../../../assets/template/mixins/tableCellEdit.js';
  import ErrorPage from "../../../../../../components/error-page/src/ErrorPage.vue";
  import merge from "../../../../../../utils/merge.js";

  export default {
    name: "element-ui-table-edit",
    components: {ErrorPage},
    mixins: [TableCellEdit],
    data() {
      return {
        //TODO 配置你要编辑的列
        tableCellEditProps: ['canEdit1', 'canEdit2', 'canEdit3'],
        //尺寸
        tableCellEditSize: 'medium',
        //编辑组件失焦时是否保存编辑,用于控制文本框保存形式,开启时失焦判断,关闭时由tooltip控制
        tableCellEditBlurSaveEdit: false,
        //编辑组件改变时是否保存标记,用于控制下拉框
        tableCellEditChangeSaveEdit: true
      };
    },
    methods: {
      getTableCellEditRows() {
        this.tableCellEditLoading = true;
        this.$Ajax
          .mock(require('./ElementUITableCellEdit.json').getTableCellEditRows)
          //TODO 写你的逻辑
          .get('')
          .success(true, data => {
            this.tableCellEditSkeleton = false;
            this.tableCellEditRows = this.formatterTableCellEditRows(data.rows, this.tableCellEditProps);
          })
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
          .mock(require('./ElementUITableCellEdit.json').handleSaveTableCellEdit)
          //TODO 写你的逻辑
          .put('', merge({}, nvs))
          .success(true, () => {
            //TODO 写你的逻辑

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
          .mock(require('./ElementUITableCellEdit.json').handleSaveTableCellEdit)
          //TODO 写你的逻辑
          .put('', merge({}, nvs))
          .success(true, () => {
            //TODO 写你的逻辑

            this.syncTableCellEditRowColumnsEditValue(row, editNames);
          })
          .finally(() => this.handleCloseLoadingTableCellEditRowColumns(row, props));
      }
    },
    created() {
      this.getTableCellEditRows();
    }

  }
</script>

<style scoped>

</style>
