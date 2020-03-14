package pub.avalonframework.office.excel.impl;

import pub.avalonframework.office.excel.BaseExcelCell;
import pub.avalonframework.office.excel.CellStyle;
import pub.avalonframework.office.excel.ExcelWorkBookExport;

/**
 * XSSFWorkbook 单元格
 *
 * @author baicho
 */
public class XSSFCell extends BaseExcelCell {

    public XSSFCell(ExcelWorkBookExport excelWorkBookExport, CellStyle cellStyle, int startRowNum, int startColNum) {
        super(excelWorkBookExport, cellStyle, startRowNum, startColNum);
    }

    public XSSFCell(ExcelWorkBookExport excelWorkBookExport, CellStyle cellStyle, int startRowNum, int startColNum, int rowSpan, int colSpan) {
        super(excelWorkBookExport, cellStyle, startRowNum, startColNum, rowSpan, colSpan);
    }
}