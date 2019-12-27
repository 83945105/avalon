import merge from "../../../utils/merge.js";
import {isFunction} from "../../../utils/util.js";

export const Data = {
  tableRef: 'table',
  //尺寸
  tableSize: 'medium',

  tableSkeleton: true,
  tableLoading: false,
  tableLoadingText: '加载中...',
  tableRows: [],
  //行删除加载特效
  tableRowDeleteLoading$: 'delete_loading$',

  tableRowColumnEditSuffix$: '_edit$',
  tableRowColumnEditValueSuffix$: '_editValue$',
  tableRowColumnEditLoading$: '_edit_loading$',

  tableShowAdd: false,
  tableAddLoading: false,
  tableShowEdit: false,
  tableEditLoading: false,
  tableShowView: false,
  tableViewLoading: false,

  tableEditRow: {},
  tableEditIndex: -1,
  tableViewRow: {},
  tableViewIndex: -1
};

export default {

  props: {
    reloadTable: Function
  },

  data() {
    return merge({}, Data);
  },


  methods: {
    /**
     * 格式化表格数据
     * @param row 单条数据
     * @param formatterRow 格式化行
     * @returns {*}
     */
    formatterTableRow(row, {formatterRow} = {}) {
      row[this.tableRowDeleteLoading$] = false;
      isFunction(formatterRow) && formatterRow(row);
      return row;
    },
    /**
     * 格式化表格数据
     * @param rows 数据
     * @param formatterRow 格式化行
     * @returns {*}
     */
    formatterTableRows(rows, {formatterRow} = {}) {
      rows.forEach(row => {
        this.formatterTableRow(row, {formatterRow});
      });
      return rows;
    },

    /**
     * 重载表格数据
     * @param formatterRow
     */
    reloadTableRows({formatterRow} = {}) {
      this.tableRows = [];
      if (!isFunction(this.reloadTable)) return;
      this.tableLoading = true;
      new Promise(this.reloadTable)
        .then(rows => {
          this.tableLoading = false;
          this.tableSkeleton = false;
          this.tableRows = this.formatterTableRows(rows, {formatterRow});
        })
        .catch(err => {
          this.tableLoading = false;
          this.tableSkeleton = 500;
        });
    },

    /**
     * 追加表格数据
     * @param row
     * @param formatterRow
     */
    appendTableRow(row, {formatterRow} = {}) {
      this.tableRows.push(this.formatterTableRow(row, {formatterRow}));
    },

    /**
     * 追加表格数据
     * @param rows
     * @param props
     * @param formatterRow
     */
    appendTableRows(rows, {formatterRow} = {}) {
      this.tableRows.push(...this.formatterTableRows(rows, {formatterRow}));
    },

    /**
     * 移除表格行
     * @param row
     */
    removeTableRow(row) {
      if (!row) return;
      for (let i = 0; i < this.tableRows.length; i++) {
        if (row === this.tableRows[i]) {
          this.tableRows.splice(i, 1);
          return;
        }
      }
    },

    /**
     * 移除表格行
     * @param rows
     */
    removeTableRows(rows) {
      if (!rows || rows.length === 0) return;
      for (let i = 0; i < this.tableRows.length; i++) {
        for (let j in rows) {
          if (rows[j] === this.tableRows[i]) {
            this.tableRows.splice(i, 1);
            i--;
            break;
          }
        }
      }
    }
  }

}
