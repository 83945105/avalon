package pub.avalonframework.office.excel;

import pub.avalonframework.office.excel.utils.ExcelUtils;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * 工作表
 *
 * @author baichao
 */
public interface Sheet<T extends Sheet<T>> {

    /**
     * 获取所属Excel工作簿
     *
     * @return Excel工作簿
     */
    ExcelWorkBook getOwnerWorkBook();

    /**
     * 设置行游标
     *
     * @param handler 接收行号,返回你想设置的行号
     * @return T extends Sheet<T>
     */
    T setRowCursor(Function<Integer, Integer> handler);

    /**
     * 设置列游标
     *
     * @param handler 接收列号,返回你想设置的列号
     * @return T extends Sheet<T>
     */
    T setColCursor(Function<Integer, Integer> handler);

    /**
     * 解析表头json文件
     *
     * @param inputStream 表头数据流
     * @return T extends Sheet<T>
     */
    T parseTitlesJson(InputStream inputStream);

    /**
     * 解析表头json文件
     *
     * @param file 表头数据文件
     * @return T extends Sheet<T>
     */
    T parseTitlesJson(File file);

    /**
     * 解析表头json数据
     *
     * @param titlesJson 表头数据json
     * @return T extends Sheet<T>
     */
    T parseTitlesJson(String titlesJson);

    /**
     * 设置表头
     *
     * @param titles 表头对象
     * @return T extends Sheet<T>
     */
    T setTitles(BaseExcelTitleCell[][] titles);

    /**
     * 设置列属性
     *
     * @param fields 属性
     * @return T extends Sheet<T>
     */
    T setColumnFields(List<String> fields);

    /**
     * 设置列对应的数据属性
     *
     * @param fields 属性
     * @return T extends Sheet<T>
     */
    default T setColumnFields(String... fields) {
        return setColumnFields(Arrays.asList(fields));
    }

    /**
     * 设置行号
     *
     * @param handler 接收当前行号,返回你想设置的行号
     * @return T extends Sheet<T>
     */
    default T setRowNum(Function<Integer, Integer> handler) {
        return this.setRowCursor(rowCursor -> handler.apply(rowCursor + 1) - 1);
    }

    /**
     * 设置列号
     *
     * @param handler 接收当前列号,返回你想设置的列号
     * @return T extends Sheet<T>
     */
    default T setColumnNum(Function<Integer, Integer> handler) {
        return this.setColCursor(colCursor -> handler.apply(colCursor + 1) - 1);
    }

    /**
     * 工作表列名
     */
    String[] SHEET_COLUMN_NAMES = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    /**
     * 获取表格列名
     *
     * @param columnNum 列号
     * @return 列名
     */
    static String getColumnName(int columnNum) {
        return ExcelUtils.calculateStrByNumber(columnNum, SHEET_COLUMN_NAMES);
    }

    /**
     * 获取表格列号
     *
     * @param columnName 列名
     * @return 列号
     */
    static Integer getColumnNum(String columnName) {
        return ExcelUtils.calculateNumByString(columnName, SHEET_COLUMN_NAMES).intValue();
    }
}