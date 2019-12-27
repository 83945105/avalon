import merge from "../../../utils/merge.js";
import {isFunction} from "../../../utils/util";

export default {

  props: {
    reloadList: Function
  },

  data() {
    return {
      listSelectedRow: {},
      listSelectedRowIndex: -1,

      listSkeleton: true,
      listLoading: false,
      listRows: [],
      //行删除加载特效
      listRowDeleteLoading$: 'delete_loading$',

      listShowAdd: false,
      listAddLoading: false,
      listShowEdit: false,
      listEditLoading: false,
      listShowView: false,
      listViewLoading: false,

      listEditRow: {},
      listEditIndex: -1,
      listViewRow: {},
      listViewIndex: -1
    };
  },

  methods: {
    formatterListRow(row) {
      row[this.listRowDeleteLoading$] = false;
      return row;
    },
    formatterListRows(rows, {formatterRow} = {}) {
      rows.forEach(row => {
        this.formatterListRow(row);
        formatterRow && formatterRow(row);
      });
      return rows;
    },
    reloadListRows({formatterRow}) {
      if (!isFunction(this.reloadList)) return;
      this.listLoading = true;
      new Promise(this.reloadList)
        .then(rows => {
          this.listLoading = false;
          this.listSkeleton = false;
          this.listRows = this.formatterListRows(rows, {formatterRow});
        })
        .catch(err => {
          this.listLoading = false;
          this.listSkeleton = 500;
        });
    },

    /**
     * 追加数据
     * @param row
     * @param formatterCell
     */
    appendListRow(row, {formatterCell} = {}) {
      this.listRows.push(this.formatterListRow(row, {formatterCell}));
    },

    /**
     * 追加数据
     * @param rows
     * @param formatterRow
     * @param formatterCell
     */
    appendListRows(rows, {formatterRow, formatterCell} = {}) {
      this.listRows.push(...this.formatterListRows(rows, {formatterRow, formatterCell}));
    },

    /**
     * 移除数据
     * @param row
     */
    removeListRow(row) {
      if (!row) return;
      for (let i = 0; i < this.listRows.length; i++) {
        if (row === this.listRows[i]) {
          this.listRows.splice(i, 1);
          return;
        }
      }
    },

    /**
     * 移除数据
     * @param rows
     */
    removeListRows(rows) {
      if (!rows || rows.length === 0) return;
      for (let i = 0; i < this.listRows.length; i++) {
        for (let j in rows) {
          if (rows[j] === this.listRows[i]) {
            this.listRows.splice(i, 1);
            i--;
            break;
          }
        }
      }
    },

    handleListShowEdit({row, $index}) {
      this.listSelectedRow = row;
      this.listSelectedRowIndex = $index;
      merge(this.listEditForm, row);
      this.listShowEdit = true;
    }
  }

}
