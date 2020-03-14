package pub.avalonframework.office.excel.impl;

import pub.avalonframework.office.excel.BaseExcelTitleCell;

/**
 * @author baichao
 */
public class XSSFTitleCell extends BaseExcelTitleCell {

    public XSSFTitleCell() {
    }

    public XSSFTitleCell(String field) {
        super(field);
    }

    public XSSFTitleCell(String title, String field) {
        super(title, field);
    }

    public XSSFTitleCell(int startRowNum, int startColNum) {
        super(startRowNum, startColNum);
    }

    public XSSFTitleCell(int startRowNum, int startColNum, int rowSpan, int colSpan) {
        super(startRowNum, startColNum, rowSpan, colSpan);
    }
}