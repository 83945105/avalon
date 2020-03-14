package pub.avalonframework.office.excel.impl;

/**
 * @author baichao
 */
public class SXSSFTitleCell extends XSSFTitleCell {

    public SXSSFTitleCell() {
    }

    public SXSSFTitleCell(String field) {
        super(field);
    }

    public SXSSFTitleCell(String title, String field) {
        super(title, field);
    }

    public SXSSFTitleCell(int startRowNum, int startColNum) {
        super(startRowNum, startColNum);
    }

    public SXSSFTitleCell(int startRowNum, int startColNum, int rowSpan, int colSpan) {
        super(startRowNum, startColNum, rowSpan, colSpan);
    }
}