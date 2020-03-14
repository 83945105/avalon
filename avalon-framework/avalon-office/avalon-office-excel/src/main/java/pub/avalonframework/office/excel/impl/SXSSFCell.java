package pub.avalonframework.office.excel.impl;

import pub.avalonframework.office.excel.CellStyle;
import pub.avalonframework.office.excel.ExcelWorkBookExport;

/**
 * SXSSFWorkbook 单元格
 *
 * @author baichao
 */
public class SXSSFCell extends XSSFCell {

    public SXSSFCell(ExcelWorkBookExport excelWorkBookExport, CellStyle cellStyle, int startRowNum, int startColNum) {
        super(excelWorkBookExport, cellStyle, startRowNum, startColNum);
    }

    public SXSSFCell(ExcelWorkBookExport excelWorkBookExport, CellStyle cellStyle, int startRowNum, int startColNum, int rowSpan, int colSpan) {
        super(excelWorkBookExport, cellStyle, startRowNum, startColNum, rowSpan, colSpan);
    }
}