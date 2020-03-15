package pub.avalonframework.office.excel;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * Sheet导出操作
 *
 * @author baichao
 */
public interface SheetExportOperations<T extends SheetExportOperations<T>> extends Sheet<T> {

    /**
     * {@inheritDoc}
     */
    @Override
    ExcelWorkBookExport getOwnerWorkBook();

    /**
     * {@inheritDoc}
     */
    @Override
    default T parseTitlesJson(InputStream inputStream) {
        return parseTitlesJson(inputStream, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    default T parseTitlesJson(File file) {
        return parseTitlesJson(file, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    default T parseTitlesJson(String titlesJson) {
        return parseTitlesJson(titlesJson, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    default T setTitles(BaseExcelTitleCell[][] titles) {
        return setTitles(titles, true);
    }

    /**
     * 解析表头json数据
     *
     * @param titlesJson   表头数据json
     * @param exportTitles 是否导出表头
     * @return ExcelWorkBookExport
     */
    T parseTitlesJson(String titlesJson, boolean exportTitles);

    /**
     * 解析表头json文件
     *
     * @param inputStream  表头数据流
     * @param exportTitles 是否导出表头
     * @return T extends SheetExportOperations<T>
     */
    T parseTitlesJson(InputStream inputStream, boolean exportTitles);

    /**
     * 解析表头json文件
     *
     * @param file         表头数据文件
     * @param exportTitles 是否导出表头
     * @return T extends SheetExportOperations<T>
     */
    T parseTitlesJson(File file, boolean exportTitles);

    /**
     * 设置表头
     *
     * @param titles       表头对象
     * @param exportTitles 是否导出表头
     * @return T extends SheetExportOperations<T>
     */
    T setTitles(BaseExcelTitleCell[][] titles, boolean exportTitles);

    /**
     * 设置列属性
     *
     * @param fields 属性
     * @return T extends SheetExportOperations<T>
     */
    default T setColumnFields(List<String> fields) {
        return setColumnFields(fields, false);
    }

    /**
     * 设置列对应的数据属性
     *
     * @param fields 属性
     * @return T extends SheetExportOperations<T>
     */
    default T setColumnFields(String... fields) {
        return setColumnFields(false, fields);
    }

    /**
     * 设置列属性
     *
     * @param fields       属性
     * @param exportTitles 是否导出表头
     * @return T extends SheetExportOperations<T>
     */
    T setColumnFields(List<String> fields, boolean exportTitles);

    /**
     * 设置列属性
     *
     * @param exportTitles 是否导出表头
     * @param fields       属性
     * @return T extends SheetExportOperations<T>
     */
    default T setColumnFields(boolean exportTitles, String... fields) {
        return setColumnFields(Arrays.asList(fields), exportTitles);
    }

    /**
     * 设置列宽
     *
     * @param columnIndex 列号
     * @param width       列宽
     * @return T extends SheetExportOperations<T>
     */
    T setColumnWidth(int columnIndex, int width);

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
     * @return T extends SheetExportOperations<T>
     */
    T insertPicture(InputStream inputStream, ExcelWorkBook.PictureType pictureType, int dx1, int dy1, int dx2, int dy2, int col1, int row1, int col2, int row2);

    /**
     * 插入图片
     *
     * @param file        图片文件
     * @param pictureType 图片类型
     * @param dx1         起始单元格的x偏移量
     * @param dy1         起始单元格的y偏移量
     * @param dx2         终止单元格的x偏移量
     * @param dy2         终止单元格的y偏移量
     * @param col1        起始单元格列序号,从0开始计算
     * @param row1        起始单元格行序号,从0开始计算
     * @param col2        终止单元格列序号,从0开始计算
     * @param row2        终止单元格行序号,从0开始计算
     * @return T extends SheetExportOperations<T>
     */
    T insertPicture(File file, ExcelWorkBook.PictureType pictureType, int dx1, int dy1, int dx2, int dy2, int col1, int row1, int col2, int row2);

    /**
     * 插入图片
     *
     * @param filePath    图片路径
     * @param pictureType 图片类型
     * @param dx1         起始单元格的x偏移量
     * @param dy1         起始单元格的y偏移量
     * @param dx2         终止单元格的x偏移量
     * @param dy2         终止单元格的y偏移量
     * @param col1        起始单元格列序号,从0开始计算
     * @param row1        起始单元格行序号,从0开始计算
     * @param col2        终止单元格列序号,从0开始计算
     * @param row2        终止单元格行序号,从0开始计算
     * @return T extends SheetExportOperations<T>
     */
    default T insertPicture(String filePath, ExcelWorkBook.PictureType pictureType, int dx1, int dy1, int dx2, int dy2, int col1, int row1, int col2, int row2) {
        return insertPicture(new File(filePath), pictureType, dx1, dy1, dx2, dy2, col1, row1, col2, row2);
    }

    /**
     * 获取表格导入的数据总数
     *
     * @return 数据总数
     */
    int getTotalDataSize();
}