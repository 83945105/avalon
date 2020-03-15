package pub.avalonframework.office.excel;

import pub.avalonframework.office.excel.impl.SXSSFExcelSheetExport;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

/**
 * Excel Sheet导出
 *
 * @author baichao
 */
@SuppressWarnings("unused")
public interface ExcelSheetExport extends SheetExportOperations {

    /**
     * 设置行游标
     *
     * @param handler 接收行号,返回你想设置的行号
     * @return ExcelSheetExport
     */
    @Override
    ExcelSheetExport setRowCursor(Function<Integer, Integer> handler);

    /**
     * 设置列游标
     *
     * @param handler 接收列号,返回你想设置的列号
     * @return ExcelSheetExport
     */
    @Override
    ExcelSheetExport setColCursor(Function<Integer, Integer> handler);

    /**
     * 解析表头json数据
     *
     * @param titlesJson   表头数据json
     * @param exportTitles 是否导出表头
     * @return ExcelSheetExport
     */
    @Override
    ExcelSheetExport parseTitlesJson(String titlesJson, boolean exportTitles);

    /**
     * 解析表头json文件
     *
     * @param inputStream  表头数据流
     * @param exportTitles 是否导出表头
     * @return ExcelSheetExport
     */
    @Override
    ExcelSheetExport parseTitlesJson(InputStream inputStream, boolean exportTitles);

    /**
     * 解析表头json文件
     *
     * @param file         表头数据文件
     * @param exportTitles 是否导出表头
     * @return ExcelSheetExport
     */
    @Override
    ExcelSheetExport parseTitlesJson(File file, boolean exportTitles);

    /**
     * 设置表头
     *
     * @param titles       表头对象
     * @param exportTitles 是否导出表头
     * @return ExcelSheetExport
     */
    @Override
    ExcelSheetExport setTitles(BaseExcelTitleCell[][] titles, boolean exportTitles);

    /**
     * 设置列属性
     *
     * @param fields 属性
     * @return ExcelSheetExport
     */
    @Override
    ExcelSheetExport setColumnFields(List<String> fields);

    /**
     * 插入图片
     *
     * @param inputStream 图片流
     * @param pictureType 图片类型
     * @param dx1         起始单元格的x偏移量
     * @param dy1         起始单元格的y偏移量
     * @param dx2         终止单元格的x偏移量
     * @param dy2         终止单元格的y偏移量
     * @param col1        起始单元格列序号,从0开始计算
     * @param row1        起始单元格行序号,从0开始计算
     * @param col2        终止单元格列序号,从0开始计算
     * @param row2        终止单元格行序号,从0开始计算
     * @return ExcelSheetExport
     */
    @Override
    ExcelSheetExport insertPicture(InputStream inputStream, ExcelWorkBook.PictureType pictureType, int dx1, int dy1, int dx2, int dy2, int col1, int row1, int col2, int row2);

    /**
     * 设置列对应的数据属性
     *
     * @param fields 属性
     * @return ExcelSheetExport
     */
    @Override
    default ExcelSheetExport setColumnFields(String... fields) {
        return setColumnFields(Arrays.asList(fields));
    }

    /**
     * 解析表头json文件
     *
     * @param inputStream 表头数据流
     * @return ExcelSheetExport
     * @throws IOException 参考实现
     */
    @Override
    default ExcelSheetExport parseTitlesJson(InputStream inputStream) {
        return parseTitlesJson(inputStream, true);
    }

    /**
     * 解析表头json文件
     *
     * @param file 表头数据文件
     * @return ExcelSheetExport
     * @throws IOException 参考实现
     */
    @Override
    default ExcelSheetExport parseTitlesJson(File file) {
        return parseTitlesJson(file, true);
    }

    /**
     * 解析表头json数据
     *
     * @param titlesJson 表头数据json
     * @return ExcelSheetExport
     */
    @Override
    default ExcelSheetExport parseTitlesJson(String titlesJson) {
        return parseTitlesJson(titlesJson, true);
    }

    /**
     * 设置表头
     *
     * @param titles 表头对象
     * @return ExcelSheetExport
     */
    @Override
    default ExcelSheetExport setTitles(BaseExcelTitleCell[][] titles) {
        return setTitles(titles, true);
    }

    /**
     * 导入数据
     *
     * @param records 数据集合
     * @param <T>     数据类型
     * @return ExcelSheetExport
     */
    <T> ExcelSheetExport importData(Collection<T> records);

    /**
     * 导入数据
     *
     * @param records   数据集合
     * @param <T>       数据类型
     * @param formatter 格式化函数,接收5个参数,分别为 当前当前数据对象record、单元格信息、当前列值、游标、当前记录下标,需要返回要设置的单元格值
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

    /**
     * 设置行号
     *
     * @param handler 接收当前行号,返回你想设置的行号
     * @return ExcelSheetExport
     */
    @Override
    default ExcelSheetExport setRowNum(Function<Integer, Integer> handler) {
        return this.setRowCursor(rowCursor -> handler.apply(rowCursor + 1) - 1);
    }

    /**
     * 设置列号
     *
     * @param handler 接收当前列号,返回你想设置的列号
     * @return ExcelSheetExport
     */
    @Override
    default ExcelSheetExport setColumnNum(Function<Integer, Integer> handler) {
        return this.setColCursor(colCursor -> handler.apply(colCursor + 1) - 1);
    }
}