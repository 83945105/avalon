package pub.avalonframework.office.excel;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * Sheet导出操作
 *
 * @author baichao
 */
public interface SheetExportOperations extends Sheet<SheetExportOperations> {

    /**
     * {@inheritDoc}
     */
    @Override
    ExcelWorkBookExport getOwnerWorkBook();

    /**
     * {@inheritDoc}
     */
    @Override
    SheetExportOperations setRowCursor(Function<Integer, Integer> handler);

    /**
     * {@inheritDoc}
     */
    @Override
    SheetExportOperations setColCursor(Function<Integer, Integer> handler);

    /**
     * 解析表头json数据
     *
     * @param titlesJson   表头数据json
     * @param exportTitles 是否导出表头
     * @return ExcelWorkBookExport
     */
    SheetExportOperations parseTitlesJson(String titlesJson, boolean exportTitles);

    /**
     * 解析表头json文件
     *
     * @param inputStream  表头数据流
     * @param exportTitles 是否导出表头
     * @return ExcelWorkBookExport
     */
    SheetExportOperations parseTitlesJson(InputStream inputStream, boolean exportTitles);

    /**
     * 解析表头json文件
     *
     * @param file         表头数据文件
     * @param exportTitles 是否导出表头
     * @return ExcelWorkBookExport
     */
    SheetExportOperations parseTitlesJson(File file, boolean exportTitles);

    /**
     * 设置表头
     *
     * @param titles       表头对象
     * @param exportTitles 是否导出表头
     * @return ExcelWorkBookExport
     */
    SheetExportOperations setTitles(BaseExcelTitleCell[][] titles, boolean exportTitles);

    /**
     * 设置列属性
     *
     * @param fields 属性
     * @return ExcelWorkBookExport
     */
    SheetExportOperations setColumnFields(List<String> fields);

    /**
     * 设置列宽
     *
     * @param columnIndex 列号
     * @param width       列宽
     */
    void setColumnWidth(int columnIndex, int width);

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
     * @return ExcelWorkBookExport
     */
    SheetExportOperations insertPicture(InputStream inputStream, ExcelWorkBook.PictureType pictureType, int dx1, int dy1, int dx2, int dy2, int col1, int row1, int col2, int row2);

    /**
     * 设置列对应的数据属性
     *
     * @param fields 属性
     * @return ExcelWorkBookExport
     */
    default SheetExportOperations setColumnFields(String... fields) {
        return setColumnFields(Arrays.asList(fields));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    default SheetExportOperations parseTitlesJson(InputStream inputStream) {
        return parseTitlesJson(inputStream, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    default SheetExportOperations parseTitlesJson(File file) {
        return parseTitlesJson(file, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    default SheetExportOperations parseTitlesJson(String titlesJson) {
        return parseTitlesJson(titlesJson, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    default SheetExportOperations setTitles(BaseExcelTitleCell[][] titles) {
        return setTitles(titles, true);
    }

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
     * @return ExcelWorkBookExport
     * @throws IOException 参考实现
     */
    default SheetExportOperations insertPicture(File file, ExcelWorkBook.PictureType pictureType, int dx1, int dy1, int dx2, int dy2, int col1, int row1, int col2, int row2) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return this;
        }
        return insertPicture(fileInputStream, pictureType, dx1, dy1, dx2, dy2, col1, row1, col2, row2);
    }

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
     * @return ExcelWorkBookExport
     * @throws IOException 参考实现
     */
    default SheetExportOperations insertPicture(String filePath, ExcelWorkBook.PictureType pictureType, int dx1, int dy1, int dx2, int dy2, int col1, int row1, int col2, int row2) {
        return insertPicture(new File(filePath), pictureType, dx1, dy1, dx2, dy2, col1, row1, col2, row2);
    }

    /**
     * 获取表格导入的数据总数
     *
     * @return 数据总数
     */
    int getTotalDataSize();

    /**
     * {@inheritDoc}
     */
    @Override
    default SheetExportOperations setRowNum(Function<Integer, Integer> handler) {
        return Sheet.super.setRowNum(handler);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    default SheetExportOperations setColumnNum(Function<Integer, Integer> handler) {
        return Sheet.super.setColumnNum(handler);
    }
}