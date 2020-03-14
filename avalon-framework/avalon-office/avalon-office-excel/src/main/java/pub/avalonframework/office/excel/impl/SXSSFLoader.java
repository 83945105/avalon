package pub.avalonframework.office.excel.impl;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * SXSSF装载器
 *
 * @author baichao
 */
public class SXSSFLoader extends XSSFLoader {

    public SXSSFLoader(Workbook workbook) {
        super(workbook);
    }
}
