import merge from "../../../utils/merge.js";
import {isFunction} from "../../../utils/util.js";
import Sortable from 'sortablejs';

export const Data = {
    tableCellEditRef: 'tableCellEdit',
    //配置你要编辑的列
    tableCellEditProps: [],
    //尺寸
    tableCellEditSize: 'medium',
    //编辑组件失焦时是否保存编辑,用于控制文本框保存形式,开启时失焦判断,关闭时由tooltip控制
    tableCellEditBlurSaveEdit: true,
    //编辑组件改变时是否保存标记,用于控制下拉框
    tableCellEditChangeSaveEdit: true,

    tableCellEditSkeleton: true,
    tableCellEditLoading: false,
    tableCellEditRows: [],
    //行删除加载特效
    tableCellEditRowDeleteLoading$: 'delete_loading$',

    tableCellEditRowColumnEditSuffix$: '_edit$',
    tableCellEditRowColumnEditValueSuffix$: '_editValue$',
    tableCellEditRowColumnEditLoading$: '_edit_loading$',
    tableCellEditShowAdd: false,
    tableCellEditAddLoading: false,
    tableCellEditShowEdit: false,
    tableCellEditEditLoading: false,

    tableCellEditEditRow: {},
    tableCellEditEditIndex: -1
};

export default {

    props: {
        reloadTableCellEdit: Function,
        enableSortable: {
            type: Boolean,
            default: true
        },
        sortableOptions: {
            type: Object,
            default() {
                return {
                    onEnd: (evt) => {
                        this.$Message.warn({
                            content: '拖拽未实现'
                        });
                    }
                }
            }
        }
    },

    data() {
        return merge({}, Data);
    },

    computed: {
        tableCellEditCount() {
            let count = 0;
            this.tableCellEditRows.forEach(row => {
                this.tableCellEditProps.forEach(prop => {
                    if (row[`${prop}${this.tableCellEditRowColumnEditSuffix$}`] === true) count++;
                });
            });
            return count;
        }
    },

    methods: {
        initDrag() {
            const el = document.querySelectorAll('.el-table__body-wrapper > table > tbody')[0];
            Sortable.create(el, {
                ...this.sortableOptions,
                disabled: !this.enableSortable
            });
        },
        /**
         * 格式化表格数据使其可以编辑
         * @param row 单条数据
         * @param props 要格式化的列
         * @param formatterCell 格式化单元格
         * @returns {*}
         */
        formatterTableCellEditRow(row, props = this.tableCellEditProps, {formatterCell} = {}) {
            row[this.tableCellEditRowDeleteLoading$] = false;
            props.forEach(prop => {
                row[`${prop}${this.tableCellEditRowColumnEditSuffix$}`] = false;
                row[`${prop}${this.tableCellEditRowColumnEditValueSuffix$}`] = row[prop];
                row[`${prop}${this.tableCellEditRowColumnEditLoading$}`] = false;
                formatterCell && formatterCell(row, prop);
            });
            return row;
        },
        /**
         * 格式化表格数据使其可以编辑
         * @param rows 数据
         * @param props 要格式化的列
         * @param formatterRow 格式化行
         * @param formatterCell 格式化单元格
         * @returns {*}
         */
        formatterTableCellEditRows(rows, props = this.tableCellEditProps, {formatterRow, formatterCell} = {}) {
            rows.forEach(row => {
                formatterRow && formatterRow(row);
                this.formatterTableCellEditRow(row, props, {formatterCell});
            });
            return rows;
        },

        reloadTableCellEditRows({formatterRow, formatterCell} = {}) {
            if (!isFunction(this.reloadTableCellEdit)) return;
            this.tableCellEditLoading = true;
            this.tableCellEditRows = [];
            new Promise(this.reloadTableCellEdit)
                .then(rows => {
                    this.tableCellEditLoading = false;
                    this.tableCellEditSkeleton = false;
                    this.tableCellEditRows = this.formatterTableCellEditRows(rows, this.tableCellEditProps, {
                        formatterRow,
                        formatterCell
                    });
                    this.$nextTick(this.initDrag);
                })
                .catch(err => {
                    this.tableCellEditLoading = false;
                    this.tableCellEditSkeleton = 500;
                });
        },

        /**
         * 追加表格数据
         * @param row
         * @param props
         * @param formatterCell
         */
        appendTableCellEditRow(row, props = this.tableCellEditProps, {formatterCell} = {}) {
            this.tableCellEditRows.push(this.formatterTableCellEditRow(row, props, {formatterCell}));
        },

        /**
         * 追加表格数据
         * @param rows
         * @param props
         * @param formatterRow
         * @param formatterCell
         */
        appendTableCellEditRows(rows, props = this.tableCellEditProps, {formatterRow, formatterCell} = {}) {
            this.tableCellEditRows.push(...this.formatterTableCellEditRows(rows, props, {formatterRow, formatterCell}));
        },

        /**
         * 移除表格行
         * @param row
         */
        removeTableCellEditRow(row) {
            if (!row) return;
            for (let i = 0; i < this.tableCellEditRows.length; i++) {
                if (row === this.tableCellEditRows[i]) {
                    this.tableCellEditRows.splice(i, 1);
                    return;
                }
            }
        },

        /**
         * 移除表格行
         * @param rows
         */
        removeTableCellEditRows(rows) {
            if (!rows || rows.length === 0) return;
            for (let i = 0; i < this.tableCellEditRows.length; i++) {
                for (let j in rows) {
                    if (rows[j] === this.tableCellEditRows[i]) {
                        this.tableCellEditRows.splice(i, 1);
                        i--;
                        break;
                    }
                }
            }
        },

        /**
         * 关闭指定行指定列的Loading
         * @param row
         * @param prop
         */
        handleCloseLoadingTableCellEditRowColumn(row, prop) {
            row[`${prop}${this.tableCellEditRowColumnEditLoading$}`] = false;
        },

        /**
         * 关闭指定行Loading
         * @param row
         * @param props
         */
        handleCloseLoadingTableCellEditRowColumns(row, props = this.tableCellEditProps) {
            props.forEach(prop => this.handleCloseLoadingTableCellEditRowColumn(row, prop));
        },

        /**
         * 关闭指定行指定列的编辑和Loading
         * @param row
         * @param prop
         */
        handleCloseEditAndLoadingTableCellEditRowColumn(row, prop) {
            row[`${prop}${this.tableCellEditRowColumnEditSuffix$}`] = false;
            row[`${prop}${this.tableCellEditRowColumnEditLoading$}`] = false;
        },

        /**
         * 关闭指定行编辑中的列和Loading
         * @param row
         * @param props
         */
        handleCloseEditAndLoadingTableCellEditRowColumns(row, props = this.tableCellEditProps) {
            props.forEach(prop => this.handleCloseEditAndLoadingTableCellEditRowColumn(row, prop));
        },

        /**
         * 关闭所有正在编辑的指定列和Loading
         * @param props 列名
         */
        handleCloseAllEditAndLoadingTableCellEditRowColumns(props = this.tableCellEditProps) {
            this.tableCellEditRows.forEach(row => this.handleCloseEditAndLoadingTableCellEditRowColumns(row, props));
        },

        /**
         * 确认是否关闭指定(默认所有)正在编辑的列
         * @param props 列名
         * @returns {Promise<any>}
         */
        handleConfirmCloseAllTableCellEdit(props = this.tableCellEditProps) {
            return new Promise((resolve, reject) => {
                if (this.tableCellEditCount === 0) {
                    resolve();
                    return;
                }
                this.$confirm({
                    content: '是否取消编辑中的单元格?',
                    onClickConfirmButton: (e, vm) => {
                        this.handleCloseAllEditAndLoadingTableCellEditRowColumns(props);
                        vm.close();
                        resolve();
                    }
                });
            });
        },

        /**
         * 开启指定行指定列的编辑
         * @param row
         * @param prop
         */
        handleOpenTableCellEditRowColumnEdit(row, prop) {
            if (this.tableCellEditProps.indexOf(prop) === -1) return;
            row[`${prop}${this.tableCellEditRowColumnEditSuffix$}`] = true;
        },

        /**
         * 开启指定行的编辑
         * @param row
         * @param props
         */
        handleOpenTableCellEditRowColumnsEdit(row, props = this.tableCellEditProps) {
            props.forEach(prop => this.handleOpenTableCellEditRowColumnEdit(row, prop));
        },

        /**
         * 开启指定行指定列的Loading
         * @param row
         * @param prop
         */
        handleOpenTableCellEditRowColumnLoading(row, prop) {
            row[`${prop}${this.tableCellEditRowColumnEditLoading$}`] = true;
        },

        /**
         * 开启指定行的Loading
         * @param row
         * @param props
         */
        handleOpenTableCellEditRowColumnsLoading(row, props = this.tableCellEditProps) {
            props.forEach(prop => this.handleOpenTableCellEditRowColumnLoading(row, prop));
        },

        /**
         * 判断指定行指定列的值是否变化
         * @param row
         * @param prop
         * @returns {boolean}
         */
        isTableCellEditRowColumnChange(row, prop) {
            return row[`${prop}${this.tableCellEditRowColumnEditValueSuffix$}`] !== row[prop];
        },

        /**
         * 判断指定行指定列是否有变化
         * @param row
         * @param props
         */
        isTableCellEditRowChange(row, props = this.tableCellEditProps) {
            for (let i in props) {
                if (this.isTableCellEditRowColumnChange(row, props[i])) {
                    return true;
                }
            }
            return false;
        },

        /**
         * 获取指定行编辑列的新值
         * @param row 行数据
         * @param props 指定列
         */
        getTableCellEditChangeRowColumnNewValues(row, props = this.tableCellEditProps) {
            let nvs = {};
            props.forEach(prop => {
                if (this.isTableCellEditRowColumnChange(row, prop)) {
                    nvs[prop] = row[`${prop}${this.tableCellEditRowColumnEditValueSuffix$}`];
                }
            });
            return nvs;
        },

        /**
         * 还原指定行指定列编辑的值
         * @param row
         * @param prop
         */
        restoreTableCellEditChangeRowColumnValue(row, prop) {
            if (this.isTableCellEditRowColumnChange(row, prop)) {
                row[`${prop}${this.tableCellEditRowColumnEditValueSuffix$}`] = row[prop];
            }
        },

        /**
         * 还原指定行编辑列的值
         * @param row
         * @param props
         */
        restoreTableCellEditChangeRowColumnsValue(row, props = this.tableCellEditProps) {
            props.forEach(prop => this.restoreTableCellEditChangeRowColumnValue(row, prop));
        },

        /**
         * 还原指定行指定列编辑的值并关闭编辑
         * @param row
         * @param prop
         */
        restoreTableCellEditChangeRowColumnValueAndCloseEdit(row, prop) {
            if (this.isTableCellEditRowColumnChange(row, prop)) {
                row[`${prop}${this.tableCellEditRowColumnEditValueSuffix$}`] = row[prop];
            }
            row[`${prop}${this.tableCellEditRowColumnEditSuffix$}`] = false;
        },

        /**
         * 还原指定行编辑列的值并关闭编辑
         * @param row
         * @param props
         */
        restoreTableCellEditChangeRowColumnsValueAndCloseEdit(row, props = this.tableCellEditProps) {
            props.forEach(prop => this.restoreTableCellEditChangeRowColumnValueAndCloseEdit(row, prop));
        },

        /**
         * 同步指定行指定列的编辑值
         * @param row
         * @param prop
         */
        syncTableCellEditRowColumnEditValue(row, prop) {
            if (this.isTableCellEditRowColumnChange(row, prop)) {
                row[prop] = row[`${prop}${this.tableCellEditRowColumnEditValueSuffix$}`];
            }
        },

        /**
         * 同步指定行的编辑值
         * @param row
         * @param props
         */
        syncTableCellEditRowColumnsEditValue(row, props = this.tableCellEditProps) {
            props.forEach(prop => this.syncTableCellEditRowColumnEditValue(row, prop));
        },

        /**
         * 文本框失焦操作
         * @param scope
         */
        handleBlurTableCellEditColumnChange(scope) {
            if (!this.tableCellEditBlurSaveEdit) return;
            if (this.isTableCellEditRowColumnChange(scope.row, scope.column.property)) {
                this.handleSaveTableCellEdit(scope);
                return;
            }
            this.restoreTableCellEditChangeRowColumnValueAndCloseEdit(scope.row, scope.column.property);
        },

        /**
         * 是否开启行编辑
         * @param scope
         * @param props
         * @returns {boolean}
         */
        isEnableTableCellEditConfirmEditRow(scope, props = this.tableCellEditProps) {
            for (let i in props) {
                if (scope.row[`${props[i]}${this.tableCellEditRowColumnEditLoading$}`]) return false;
                if (!scope.row[`${props[i]}${this.tableCellEditRowColumnEditSuffix$}`]) return true;
            }
            return false;
        },

        /**
         * 是否开启取消行编辑
         * @param scope
         * @param props
         * @returns {boolean}
         */
        isEnableTableCellEditCancelEditRow(scope, props = this.tableCellEditProps) {
            for (let i in props) {
                if (scope.row[`${props[i]}${this.tableCellEditRowColumnEditLoading$}`]) return false;
                if (scope.row[`${props[i]}${this.tableCellEditRowColumnEditSuffix$}`]) return true;
            }
            return false;
        },

        /**
         * 是否开启保存行编辑
         * @param scope
         * @param props
         */
        isEnableTableCellEditSaveEditRow(scope, props = this.tableCellEditProps) {
            for (let i in props) {
                if (scope.row[`${props[i]}${this.tableCellEditRowColumnEditLoading$}`]) return false;
                if (scope.row[`${props[i]}${this.tableCellEditRowColumnEditSuffix$}`]
                    && this.isTableCellEditRowColumnChange(scope.row, props[i])) return true;
            }
            return false;
        }
    }

}
