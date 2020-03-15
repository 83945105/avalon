package pub.avalonframework.office.excel;

import pub.avalonframework.office.excel.impl.SXSSFExcelSheetExport;

import java.io.*;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

/**
 * Excel Sheet导出
 *
 * @author baichao
 */
public interface ExcelSheetExport extends SheetExportOperations<ExcelSheetExport> {

    @Override
    ExcelWorkBookExport getOwnerWorkBook();

    @Override
    default ExcelSheetExport parseTitlesJson(InputStream inputStream) {
        return SheetExportOperations.super.parseTitlesJson(inputStream);
    }

    @Override
    default ExcelSheetExport parseTitlesJson(File file) {
        return SheetExportOperations.super.parseTitlesJson(file);
    }

    @Override
    default ExcelSheetExport parseTitlesJson(String titlesJson) {
        return SheetExportOperations.super.parseTitlesJson(titlesJson);
    }

    @Override
    default ExcelSheetExport setTitles(BaseExcelTitleCell[][] titles) {
        return SheetExportOperations.super.setTitles(titles);
    }

    @Override
    ExcelSheetExport parseTitlesJson(String titlesJson, boolean exportTitles);

    @Override
    ExcelSheetExport parseTitlesJson(InputStream inputStream, boolean exportTitles);

    @Override
    ExcelSheetExport parseTitlesJson(File file, boolean exportTitles);

    @Override
    ExcelSheetExport setTitles(BaseExcelTitleCell[][] titles, boolean exportTitles);

    @Override
    default ExcelSheetExport setColumnFields(List<String> fields) {
        return SheetExportOperations.super.setColumnFields(fields);
    }

    @Override
    default ExcelSheetExport setColumnFields(String... fields) {
        return SheetExportOperations.super.setColumnFields(fields);
    }

    @Override
    ExcelSheetExport setColumnFields(List<String> fields, boolean exportTitles);

    @Override
    default ExcelSheetExport setColumnFields(boolean exportTitles, String... fields) {
        return SheetExportOperations.super.setColumnFields(exportTitles, fields);
    }

    @Override
    ExcelSheetExport setColumnWidth(int columnIndex, int width);

    @Override
    ExcelSheetExport insertPicture(InputStream inputStream, ExcelWorkBook.PictureType pictureType, int dx1, int dy1, int dx2, int dy2, int col1, int row1, int col2, int row2);

    @Override
    default ExcelSheetExport insertPicture(File file, ExcelWorkBook.PictureType pictureType, int dx1, int dy1, int dx2, int dy2, int col1, int row1, int col2, int row2) {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return this;
        }
        return insertPicture(fileInputStream, pictureType, dx1, dy1, dx2, dy2, col1, row1, col2, row2);
    }

    @Override
    default ExcelSheetExport insertPicture(String filePath, ExcelWorkBook.PictureType pictureType, int dx1, int dy1, int dx2, int dy2, int col1, int row1, int col2, int row2) {
        return SheetExportOperations.super.insertPicture(filePath, pictureType, dx1, dy1, dx2, dy2, col1, row1, col2, row2);
    }

    @Override
    ExcelSheetExport setRowCursor(Function<Integer, Integer> handler);

    @Override
    ExcelSheetExport setColCursor(Function<Integer, Integer> handler);

    @Override
    default ExcelSheetExport setRowNum(Function<Integer, Integer> handler) {
        return SheetExportOperations.super.setRowNum(handler);
    }

    @Override
    default ExcelSheetExport setColumnNum(Function<Integer, Integer> handler) {
        return SheetExportOperations.super.setColumnNum(handler);
    }

    /**
     * 导入数据
     *
     * @param records 数据集合
     * @return ExcelSheetExport
     */
    <T> ExcelSheetExport importData(Collection<T> records);

    /**
     * 导入数据
     *
     * @param records   数据集合
     * @param formatter 格式化
     * @return ExcelSheetExport
     */
    <T> ExcelSheetExport importData(Collection<T> records, SXSSFExcelSheetExport.FormatterCell<T> formatter);

    /**
     * 导出Excel
     *
     * @param outFile 目标文件
     * @throws IOException 参考实现
     */
    void export(File outFile) throws IOException;

    /**
     * 导出Excel
     *
     * @param outPath 导出地址
     * @throws IOException 参考实现
     */
    void export(String outPath) throws IOException;
}