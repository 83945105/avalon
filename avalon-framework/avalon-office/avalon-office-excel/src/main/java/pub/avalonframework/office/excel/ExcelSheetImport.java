package pub.avalonframework.office.excel;

import com.fasterxml.jackson.core.type.TypeReference;

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
    ExcelWorkBookImport<?> getOwnerWorkBook();

    /**
     * {@inheritDoc}
     */
    @Override
    int getPhysicalNumberOfRows();

    /**
     * {@inheritDoc}
     */
    @Override
    default ExcelSheetImport<R> setColumnFields(List<String> fields) {
        return SheetImportOperations.super.setColumnFields(fields);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    default ExcelSheetImport<R> setColumnFields(String... fields) {
        return SheetImportOperations.super.setColumnFields(fields);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    ExcelSheetImport<R> setColumnFields(List<String> fields, int expectedRowSpan);

    /**
     * {@inheritDoc}
     */
    @Override
    default ExcelSheetImport<R> setColumnFields(int expectedRowSpan, String... fields) {
        return SheetImportOperations.super.setColumnFields(expectedRowSpan, fields);
    }

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
    ExcelSheetImport<R> setTitles(ExcelTitleCell[][] titles);

    /**
     * 设置表头
     *
     * @param titles          表头
     * @param expectedRowSpan 期望占用行数
     * @return ExcelSheetImport
     */
    ExcelSheetImport<R> setTitles(ExcelTitleCell[][] titles, int expectedRowSpan);

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
    <HR> ExcelSheetImport<HR> setTitles(ExcelTitleCell[][] titles, Class<HR> clazz);

    /**
     * 设置表头
     *
     * @param titles          表头对象
     * @param expectedRowSpan 期望占用行数
     * @param clazz           数据容器
     * @return ExcelSheetImport
     */
    <HR> ExcelSheetImport<HR> setTitles(ExcelTitleCell[][] titles, int expectedRowSpan, Class<HR> clazz);

    /**
     * 设置列对应的数据属性
     *
     * @param fields 属性
     * @param clazz  数据容器
     * @return ExcelSheetImport
     */
    default <HR> ExcelSheetImport<HR> setColumnFields(List<String> fields, Class<HR> clazz) {
        return setColumnFields(fields, 0, clazz);
    }

    /**
     * 设置列对应的数据属性
     *
     * @param clazz  数据容器
     * @param fields 属性
     * @return ExcelSheetImport
     */
    default <HR> ExcelSheetImport<HR> setColumnFields(Class<HR> clazz, String... fields) {
        return setColumnFields(Arrays.asList(fields), clazz);
    }

    /**
     * 设置列对应的数据属性
     *
     * @param fields          属性
     * @param expectedRowSpan 期望占用行数
     * @param clazz           数据容器
     * @return ExcelSheetImport
     */
    <HR> ExcelSheetImport<HR> setColumnFields(List<String> fields, int expectedRowSpan, Class<HR> clazz);

    /**
     * 设置列对应的数据属性
     *
     * @param clazz           数据容器
     * @param expectedRowSpan 期望占用行数
     * @param fields          属性
     * @return ExcelSheetImport
     */
    default <HR> ExcelSheetImport<HR> setColumnFields(Class<HR> clazz, int expectedRowSpan, String... fields) {
        return setColumnFields(Arrays.asList(fields), expectedRowSpan, clazz);
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
     * @param typeReference 数据类型
     * @return ExcelSheetImport
     */
    <HR> ExcelSheetImport<HR> readRows(TypeReference<HR> typeReference);

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
     * @param typeReference 数据类型
     * @param handlerRow    操作当前行数据
     * @return ExcelSheetImport
     */
    <HR> ExcelSheetImport<HR> readRows(TypeReference<HR> typeReference, HandlerRowA<HR> handlerRow);

    /**
     * 读取数据
     *
     * @param clazz      数据类型
     * @param handlerRow 操作当前行数据,返回false不继续读取下一行
     * @return ExcelSheetImport
     */
    <HR> ExcelSheetImport<HR> readRows(Class<HR> clazz, HandlerRowB<HR> handlerRow);

    /**
     * 读取数据
     *
     * @param typeReference 数据类型
     * @param handlerRow    操作当前行数据
     * @return ExcelSheetImport
     */
    <HR> ExcelSheetImport<HR> readRows(TypeReference<HR> typeReference, HandlerRowB<HR> handlerRow);
}