package pub.avalonframework.office.excel;

import pub.avalonframework.office.excel.impl.XSSFExcelWorkBookImport;

/**
 * 导入
 *
 * @author baichao
 */
public interface ExcelImportFactory {

    /**
     * 构建基于XSSFWorkBook的Excel导出工作簿
     *
     * @return
     */
    static ExcelWorkBookImport buildXSSFImportExcelWorkBook() {
        return new XSSFExcelWorkBookImport();
    }
}
