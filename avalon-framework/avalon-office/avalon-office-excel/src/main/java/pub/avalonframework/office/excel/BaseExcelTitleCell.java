package pub.avalonframework.office.excel;

/**
 * Excel表头单元格属性
 *
 * @author baichao
 */
public abstract class BaseExcelTitleCell extends BaseCell implements ExcelTitleCellOperations {

    /**
     * 默认表头单元格样式
     */
    public static CellStyle DEFAULT_TITLE_CELL_STYLE = new CellStyle.DefaultCellStyle();

    /**
     * 列标题文本
     */
    protected String title = "";

    /**
     * 单元格宽-默认10
     */
    protected int width = 10;

    /**
     * 单元格样式
     */
    protected CellStyle cellStyle = DEFAULT_TITLE_CELL_STYLE;

    public BaseExcelTitleCell() {
    }

    public BaseExcelTitleCell(String field) {
        this.field = field;
    }

    public BaseExcelTitleCell(String title, String field) {
        this.title = title;
        this.field = field;
    }

    public BaseExcelTitleCell(String title, String field, int rowSpan, int colSpan) {
        super(field, rowSpan, colSpan);
        this.title = title;
    }

    public BaseExcelTitleCell(int startRowNum, int startColNum) {
        super(startRowNum, startColNum);
    }

    public BaseExcelTitleCell(int startRowNum, int startColNum, int rowSpan, int colSpan) {
        super(startRowNum, startColNum, rowSpan, colSpan);
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public Object getCellValue() {
        return this.getTitle();
    }

    @Override
    public CellStyle getCellStyle() {
        return this.cellStyle;
    }

    @Override
    public void setCellStyle(CellStyle cellStyle) {
        this.cellStyle = cellStyle;
    }
}