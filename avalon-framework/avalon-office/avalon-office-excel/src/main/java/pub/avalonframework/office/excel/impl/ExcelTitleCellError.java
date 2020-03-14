package pub.avalonframework.office.excel.impl;

import pub.avalonframework.office.excel.ExcelTitleCellOperations;

/**
 * @author baichao
 */
public class ExcelTitleCellError {

    /**
     * 行号
     */
    private int row;
    /**
     * 列号
     */
    private int col;
    /**
     * Excel表头单元格操作
     */
    private ExcelTitleCellOperations excelTitleCellOperations;

    public ExcelTitleCellError() {
    }

    public ExcelTitleCellError(int row, int col, ExcelTitleCellOperations excelTitleCellOperations) {
        this.row = row;
        this.col = col;
        this.excelTitleCellOperations = excelTitleCellOperations;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public ExcelTitleCellOperations getExcelTitleCellOperations() {
        return excelTitleCellOperations;
    }

    public void setExcelTitleCellOperations(ExcelTitleCellOperations excelTitleCellOperations) {
        this.excelTitleCellOperations = excelTitleCellOperations;
    }
}