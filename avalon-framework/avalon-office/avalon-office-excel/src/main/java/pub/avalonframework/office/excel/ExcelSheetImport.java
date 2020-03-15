package pub.avalonframework.office.excel;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * Excel Sheet导入
 *
 * @author baichao
 */
public interface ExcelSheetImport<R> extends SheetImportOperations<R, ExcelSheetImport<R>> {

    /**
     * {@inheritDoc}
     */
    @Override
    ExcelWorkBookImport getOwnerWorkBook();

    /**
     * {@inheritDoc}
     */
    @Override
    int getPhysicalNumberOfRows();

    /**
     * {@inheritDoc}
     */
    @Override
    ExcelSheetImport<R> readRows();

    /**
     * {@inheritDoc}
     */
    @Override
    ExcelSheetImport<R> readRows(HandlerRowA<R> handlerRow);

    /**
     * {@inheritDoc}
     */
    @Override
    ExcelSheetImport<R> readRows(HandlerRowB<R> handlerRow);

    /**
     * {@inheritDoc}
     */
    @Override
    List<R> getReadData();

    /**
     * {@inheritDoc}
     */
    @Override
    List<R> getReadData(int index);

    /**
     * {@inheritDoc}
     */
    @Override
    List<List<R>> getAllReadData();

    /**
     * {@inheritDoc}
     */
    @Override
    ExcelSheetImport<R> setRowCursor(Function<Integer, Integer> handler);

    /**
     * {@inheritDoc}
     */
    @Override
    ExcelSheetImport<R> setColCursor(Function<Integer, Integer> handler);

    /**
     * {@inheritDoc}
     */
    @Override
    ExcelSheetImport<R> parseTitlesJson(InputStream inputStream);

    /**
     * {@inheritDoc}
     */
    @Override
    ExcelSheetImport<R> parseTitlesJson(File file);

    /**
     * {@inheritDoc}
     */
    @Override
    ExcelSheetImport<R> parseTitlesJson(String titlesJson);

    /**
     * {@inheritDoc}
     */
    @Override
    ExcelSheetImport<R> setTitles(BaseExcelTitleCell[][] titles);

    /**
     * 设置表头
     *
     * @param titles  表头
     * @param rowSpan 表头占用行数
     * @return ExcelSheetImport
     */
    ExcelSheetImport<R> setTitles(BaseExcelTitleCell[][] titles, int rowSpan);

    /**
     * {@inheritDoc}
     */
    @Override
    default ExcelSheetImport<R> setRowNum(Function<Integer, Integer> handler) {
        return SheetImportOperations.super.setRowNum(handler);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    default ExcelSheetImport<R> setColumnNum(Function<Integer, Integer> handler) {
        return SheetImportOperations.super.setColumnNum(handler);
    }

    /**
     * 解析表头json文件
     *
     * @param inputStream 表头数据流
     * @param clazz       数据容器
     * @return ExcelSheetImport
     */
    <HR> ExcelSheetImport<HR> parseTitlesJson(InputStream inputStream, Class<HR> clazz);

    /**
     * 解析表头json文件
     *
     * @param file  表头数据文件
     * @param clazz 数据容器
     * @return ExcelSheetImport
     */
    <HR> ExcelSheetImport<HR> parseTitlesJson(File file, Class<HR> clazz);

    /**
     * 解析表头json数据
     *
     * @param titlesJson 表头数据json
     * @param clazz      数据容器
     * @return ExcelSheetImport
     */
    <HR> ExcelSheetImport<HR> parseTitlesJson(String titlesJson, Class<HR> clazz);

    /**
     * 设置表头
     *
     * @param titles 表头对象
     * @param clazz  数据容器
     * @return ExcelSheetImport
     */
    <HR> ExcelSheetImport<HR> setTitles(BaseExcelTitleCell[][] titles, Class<HR> clazz);

    /**
     * 设置表头
     *
     * @param titles  表头对象
     * @param rowSpan 占用行数
     * @param clazz   数据容器
     * @return ExcelSheetImport
     */
    <HR> ExcelSheetImport<HR> setTitles(BaseExcelTitleCell[][] titles, int rowSpan, Class<HR> clazz);

    /**
     * 设置列对应的数据属性
     *
     * @param fields 属性
     * @param clazz  数据容器
     * @return ExcelSheetImport
     */
    <HR> ExcelSheetImport<HR> setColumnFields(List<String> fields, Class<HR> clazz);

    /**
     * 设置列值
     * 注意,使用该方法读取数据,设置的field应该与对应数据的列号相同
     *
     * @param fields 列对应属性
     * @return ExcelSheetImport
     */
    default ExcelSheetImport<SheetRowMap> setColumnFields(String... fields) {
        return setColumnFields(Arrays.asList(fields), SheetRowMap.class);
    }

    /**
     * 设置列对应的数据属性
     *
     * @param rowSpan 占用行数
     * @param fields  属性
     * @param clazz   数据容器
     * @return ExcelSheetImport
     */
    <HR> ExcelSheetImport<HR> setColumnFields(int rowSpan, List<String> fields, Class<HR> clazz);

    /**
     * 设置列值
     * 注意,使用该方法读取数据,设置的field应该与对应数据的列号相同
     *
     * @param rowSpan 占用行数
     * @param fields  列对应的属性
     * @return ExcelSheetImport
     */
    default ExcelSheetImport<SheetRowMap> setColumnFields(int rowSpan, String... fields) {
        return setColumnFields(rowSpan, Arrays.asList(fields), SheetRowMap.class);
    }

    /**
     * 读取数据
     *
     * @param clazz 数据类型
     * @return ExcelSheetImport
     */
    <HR> ExcelSheetImport<HR> readRows(Class<HR> clazz);

    /**
     * 读取数据
     *
     * @param clazz      数据类型
     * @param handlerRow 操作当前行数据
     * @return ExcelSheetImport
     */
    <HR> ExcelSheetImport<HR> readRows(Class<HR> clazz, HandlerRowA<HR> handlerRow);

    /**
     * 读取数据
     *
     * @param clazz      数据类型
     * @param handlerRow 操作当前行数据,返回false不继续读取下一行
     * @return ExcelSheetImport
     */
    <HR> ExcelSheetImport<HR> readRows(Class<HR> clazz, HandlerRowB<HR> handlerRow);
}