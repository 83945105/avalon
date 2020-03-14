package pub.avalonframework.office.excel.impl;

import pub.avalonframework.office.excel.ExcelException;

/**
 * Excel表头异常
 *
 * @author baichao
 */
public class ExcelTitleException extends ExcelException {

    protected ExcelTitleCellError excelTitleCellError;

    public ExcelTitleException(ExcelTitleCellError excelTitleCellError) {
        this.excelTitleCellError = excelTitleCellError;
    }

    public ExcelTitleException(String message, ExcelTitleCellError excelTitleCellError) {
        super(message);
        this.excelTitleCellError = excelTitleCellError;
    }

    public ExcelTitleException(String message, Throwable cause, ExcelTitleCellError excelTitleCellError) {
        super(message, cause);
        this.excelTitleCellError = excelTitleCellError;
    }

    public ExcelTitleException(Throwable cause, ExcelTitleCellError excelTitleCellError) {
        super(cause);
        this.excelTitleCellError = excelTitleCellError;
    }

    @Override
    public String toString() {
        try {
            return "row:" + excelTitleCellError.getRow()
                    + " col:" + excelTitleCellError.getCol()
                    + " 已被占用,title:"
                    + excelTitleCellError.getExcelTitleCellOperations().getTitle()
                    + " rowSpan:" + excelTitleCellError.getExcelTitleCellOperations().getRowSpan()
                    + " colSpan:" + excelTitleCellError.getExcelTitleCellOperations().getColSpan();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.toString();
    }
}