package pub.avalonframework.office.excel;

import com.fasterxml.jackson.core.type.TypeReference;
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
    static ExcelWorkBookImport<SheetRowList> buildXSSFImportExcelWorkBook() {
        return new XSSFExcelWorkBookImport<>(SheetRowList.class);
    }

    /**
     * 构建基于XSSFWorkBook的Excel导出工作簿
     *
     * @param defaultSheetRowType 默认的工作表行类型
     * @return
     */
    static <R> ExcelWorkBookImport<R> buildXSSFImportExcelWorkBook(Class<R> defaultSheetRowType) {
        return new XSSFExcelWorkBookImport<>(defaultSheetRowType);
    }

    /**
     * 构建基于XSSFWorkBook的Excel导出工作簿
     *
     * @param defaultSheetRowTypeReference 默认的工作表行类型
     * @return
     */
    static <R> ExcelWorkBookImport<R> buildXSSFImportExcelWorkBook(TypeReference<R> defaultSheetRowTypeReference) {
        return new XSSFExcelWorkBookImport<>(defaultSheetRowTypeReference);
    }
}
