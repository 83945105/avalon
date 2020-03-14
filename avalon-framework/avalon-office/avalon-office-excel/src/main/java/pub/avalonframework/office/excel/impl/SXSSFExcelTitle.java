package pub.avalonframework.office.excel.impl;

/**
 * @author baichao
 */
public class SXSSFExcelTitle extends SXSSFTitleCell {

    public SXSSFExcelTitle() {
    }

    public SXSSFExcelTitle(String field) {
        super(field);
    }

    public SXSSFExcelTitle(String title, String field) {
        super(title, field);
    }

    public SXSSFExcelTitle(int startRowNum, int startColNum) {
        super(startRowNum, startColNum);
    }

    public SXSSFExcelTitle(int startRowNum, int startColNum, int rowSpan, int colSpan) {
        super(startRowNum, startColNum, rowSpan, colSpan);
    }
}
