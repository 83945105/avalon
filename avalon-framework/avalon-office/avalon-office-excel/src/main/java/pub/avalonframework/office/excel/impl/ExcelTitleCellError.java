package pub.avalonframework.office.excel.impl;

import pub.avalonframework.office.excel.ExcelTitleCellHandler;

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
     * 单元格
     */
    private ExcelTitleCellHandler excelTitleCellHandler;

    public ExcelTitleCellError() {
    }

    public ExcelTitleCellError(int row, int col, ExcelTitleCellHandler excelTitleCellHandler) {
        this.row = row;
        this.col = col;
        this.excelTitleCellHandler = excelTitleCellHandler;
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

    public ExcelTitleCellHandler getExcelTitleCellHandler() {
        return excelTitleCellHandler;
    }

    public void setExcelTitleCellHandler(ExcelTitleCellHandler excelTitleCellHandler) {
        this.excelTitleCellHandler = excelTitleCellHandler;
    }
}