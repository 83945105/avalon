package pub.avalonframework.office.excel;

/**
 * @author 白超
 */
public interface ExcelTitleCellOperations extends CellOperations {

    /**
     * 获取标题文本
     *
     * @return
     */
    String getTitle();

    /**
     * 设置标题文本
     *
     * @param title
     */
    void setTitle(String title);

    /**
     * 获取单元格宽度
     *
     * @return
     */
    int getWidth();

    /**
     * 设置单元格宽度
     *
     * @param width
     */
    void setWidth(int width);
}