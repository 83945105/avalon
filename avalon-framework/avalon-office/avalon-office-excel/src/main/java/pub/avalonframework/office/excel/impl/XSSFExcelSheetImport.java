package pub.avalonframework.office.excel.impl;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import pub.avalonframework.common.utils.BeanUtils;
import pub.avalonframework.office.excel.*;

import java.io.File;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.*;
import java.util.function.Function;

/**
 * XSSFWorkBook
 *
 * @author baichao
 */
public class XSSFExcelSheetImport<R> extends XSSFExcelWorkBookImport implements ExcelSheetImport<R> {

    /**
     * 上一个
     */
    protected XSSFExcelSheetImport<?> parent;

    /**
     * 行数据类型
     */
    protected Class<R> rowType;

    /**
     * 当前数据表对象
     */
    protected XSSFSheet sheet;

    /**
     * 所属工作簿对象
     */
    protected XSSFExcelWorkBookImport ownerWorkBook;

    /**
     * 表头单元格
     */
    protected List<BaseExcelTitleCell> excelTitleCells;

    /**
     * 与数据相关的表头信息
     */
    protected LinkedList<BaseExcelTitleCell> dataTitleCells = new LinkedList<>();

    /**
     * 行游标,记录读取起始行号
     */
    protected int rowCursor = -1;

    /**
     * 列游标,记录读取起始列号
     */
    protected int colCursor = -1;

    /**
     * 物理行数
     */
    protected int physicalNumberOfRows;

    /**
     * 装载器
     */
    protected XSSFLoader xssfLoader;

    /**
     * 每次读取的数据集合,按照读取次数顺序放入
     */
    protected List<List<R>> rows = new ArrayList<>(1);

    public XSSFExcelSheetImport(Class<R> rowType, XSSFSheet sheet, XSSFExcelWorkBookImport ownerWorkBook) {
        super(ownerWorkBook.xssfWorkbook);
        this.rowType = rowType;
        this.sheet = sheet;
        this.ownerWorkBook = ownerWorkBook;
        this.physicalNumberOfRows = this.sheet.getPhysicalNumberOfRows();
        this.xssfLoader = new XSSFLoader(this.ownerWorkBook.xssfWorkbook);
    }

    public XSSFExcelSheetImport(Class<R> rowType, XSSFExcelSheetImport<?> parent) {
        this(rowType, parent.sheet, parent.ownerWorkBook);
        this.parent = parent;
    }

    @Override
    public ExcelWorkBookImport getOwnerWorkBook() {
        return this.ownerWorkBook;
    }

    @Override
    public int getPhysicalNumberOfRows() {
        return this.physicalNumberOfRows;
    }

    @Override
    public ExcelSheetImport<R> readRows() {
        List<R> rows = this.loadRows(this.rowType);
        this.rows.add(rows);
        return this;
    }

    @Override
    public ExcelSheetImport<R> readRows(HandlerRowA<R> handlerRow) {
        List<R> rows = this.loadRows(this.rowType, handlerRow);
        this.rows.add(rows);
        return this;
    }

    @Override
    public ExcelSheetImport<R> readRows(HandlerRowB<R> handlerRow) {
        List<R> rows = this.loadRows(this.rowType, handlerRow);
        this.rows.add(rows);
        return this;
    }

    @Override
    public List<R> getReadData() {
        return this.rows.get(this.rows.size() - 1);
    }

    @Override
    public List<R> getReadData(int index) {
        return this.rows.get(index);
    }

    @Override
    public List<List<R>> getAllReadData() {
        return this.rows;
    }

    @Override
    public ExcelSheetImport<R> setRowCursor(Function<Integer, Integer> handler) {
        this.rowCursor = handler.apply(this.rowCursor);
        return this;
    }

    @Override
    public ExcelSheetImport<R> setColCursor(Function<Integer, Integer> handler) {
        this.colCursor = handler.apply(this.colCursor);
        return this;
    }

    @Override
    public ExcelSheetImport<R> parseTitlesJson(InputStream inputStream) {
        XSSFTitleCell[][] excelTitles = (XSSFTitleCell[][]) this.parseCellsJson(inputStream);
        return setTitles(excelTitles);
    }

    @Override
    public ExcelSheetImport<R> parseTitlesJson(File file) {
        XSSFTitleCell[][] excelTitles = (XSSFTitleCell[][]) this.parseCellsJson(file);
        return setTitles(excelTitles);
    }

    @Override
    public ExcelSheetImport<R> parseTitlesJson(String titlesJson) {
        XSSFTitleCell[][] excelTitles = this.parseCellsJson(titlesJson);
        return setTitles(excelTitles);
    }

    @Override
    public ExcelSheetImport<R> setTitles(BaseExcelTitleCell[][] titles) {
        if (!(titles instanceof XSSFTitleCell[][])) {
            throw new ExportException("SXSSFExcelSheetExport setTitles excelTitles类型应该为XSSFTitleCell[][]");
        }
        this.excelTitleCells = handlerExcelTitles(titles);
        this.dataTitleCells = this.searchDataTitleCells(this.excelTitleCells);
        this.parseExportTitles(this.dataTitleCells);
        return this;
    }

    @Override
    public ExcelSheetImport<R> setTitles(BaseExcelTitleCell[][] titles, int rowSpan) {
        if (!(titles instanceof XSSFTitleCell[][])) {
            throw new ExportException("SXSSFExcelSheetExport setTitles excelTitles类型应该为XSSFTitleCell[][]");
        }
        this.excelTitleCells = handlerExcelTitles(titles);
        this.dataTitleCells = this.searchDataTitleCells(this.excelTitleCells);
        this.parseExportTitles(this.dataTitleCells, rowSpan);
        return this;
    }

    @Override
    public <HR> ExcelSheetImport<HR> parseTitlesJson(InputStream inputStream, Class<HR> clazz) {
        XSSFTitleCell[][] excelTitles = (XSSFTitleCell[][]) this.parseCellsJson(inputStream);
        return setTitles(excelTitles, clazz);
    }

    @Override
    public <HR> ExcelSheetImport<HR> parseTitlesJson(File file, Class<HR> clazz) {
        XSSFTitleCell[][] excelTitles = (XSSFTitleCell[][]) this.parseCellsJson(file);
        return setTitles(excelTitles, clazz);
    }

    @Override
    public <HR> ExcelSheetImport<HR> parseTitlesJson(String titlesJson, Class<HR> clazz) {
        XSSFTitleCell[][] excelTitles = this.parseCellsJson(titlesJson);
        return setTitles(excelTitles, clazz);
    }

    @Override
    public <HR> ExcelSheetImport<HR> setTitles(BaseExcelTitleCell[][] titles, Class<HR> clazz) {
        ExcelSheetImport<HR> excelSheetImport = new XSSFExcelSheetImport<>(clazz, this);
        excelSheetImport.setTitles(titles);
        return excelSheetImport;
    }

    @Override
    public <HR> ExcelSheetImport<HR> setTitles(BaseExcelTitleCell[][] titles, int rowSpan, Class<HR> clazz) {
        ExcelSheetImport<HR> excelSheetImport = new XSSFExcelSheetImport<>(clazz, this);
        excelSheetImport.setTitles(titles, rowSpan);
        return excelSheetImport;
    }

    @Override
    public <HR> ExcelSheetImport<HR> setColumnFields(List<String> fields, Class<HR> clazz) {
        XSSFTitleCell[][] excelTitles = new XSSFTitleCell[1][fields.size()];
        for (int i = 0; i < fields.size(); i++) {
            excelTitles[0][i] = new XSSFTitleCell(fields.get(i));
        }
        return setTitles(excelTitles, clazz);
    }

    @Override
    public <HR> ExcelSheetImport<HR> setColumnFields(int rowSpan, List<String> fields, Class<HR> clazz) {
        XSSFTitleCell[][] excelTitles = new XSSFTitleCell[1][fields.size()];
        for (int i = 0; i < fields.size(); i++) {
            excelTitles[0][i] = new XSSFTitleCell(fields.get(i));
        }
        return setTitles(rowSpan, excelTitles, clazz);
    }

    @Override
    public <HR> ExcelSheetImport<HR> readRows(Class<HR> clazz) {
        this.loadRows(clazz);
        return null;
    }

    @Override
    public <HR> ExcelSheetImport<HR> readRows(Class<HR> clazz, HandlerRowA<HR> handlerRow) {
        this.loadRows(clazz, handlerRow);
        return null;
    }

    @Override
    public <HR> ExcelSheetImport<HR> readRows(Class<HR> clazz, HandlerRowB<HR> handlerRow) {
        this.loadRows(clazz, handlerRow);
        return null;
    }

    public <HR> ExcelSheetImport<HR> setTitles(int rowSpan, BaseExcelTitleCell[][] excelTitles, Class<HR> clazz) {
        return null;
    }

    /**
     * 解析行
     *
     * @param row        当前行
     * @param cellParser 单元格解析器
     */
    protected void parseRow(Row row, CellParser cellParser) {
        Iterator<Cell> cells = row.iterator();
        int j = 0;
        org.apache.poi.xssf.usermodel.XSSFCell cell;
        while (cells.hasNext()) {
            //小于列游标不读
            if (j < this.colCursor) {
                continue;
            }
            cell = (org.apache.poi.xssf.usermodel.XSSFCell) cells.next();
            cellParser.accept(cell);
        }
    }

    /**
     * 注入Map
     *
     * @param row       当前行
     * @param container 目标Map
     */
    protected <M extends Map<String, Object>> void injectMap(Row row, M container) {
        this.parseRow(row, cell -> {
            BaseExcelTitleCell titleCell = this.searchTitleCell(this.dataTitleCells, cell.getColumnIndex());
            if (titleCell == null) {
                container.put(Sheet.getColumnName(cell.getColumnIndex() + 1) + (cell.getRowIndex() + 1), xssfLoader.getValue(cell));
            } else {
                container.put(titleCell.getField(), xssfLoader.getValue(cell));
            }
        });
    }

    /**
     * 注入集合
     *
     * @param row       当前行
     * @param container 目标集合
     */
    protected <C extends Collection<Object>> void injectCollection(Row row, C container) {
        this.parseRow(row, cell -> container.add(xssfLoader.getValue(cell)));
    }

    /**
     * 注入对象
     *
     * @param row    当前行
     * @param target 目标对象
     */
    protected <O> void injectObject(Row row, O target) {
        this.parseRow(row, cell -> {
            BaseExcelTitleCell titleCell = this.searchTitleCell(this.dataTitleCells, cell.getColumnIndex());
            if (titleCell != null) {
                BeanUtils.setPropertyValue(target, titleCell.getField(), xssfLoader.getValue(cell));
            }
        });
    }

    /**
     * 装载行
     *
     * @param row            当前行
     * @param containerClass 容器类型
     * @return 装载后的对象
     */
    @SuppressWarnings("unchecked")
    protected <T> T injectRow(Row row, Class<T> containerClass) {
        //设置行游标
        this.setRowCursor(idx -> row.getRowNum());
        T rs;
        try {
            rs = containerClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new ExcelException(e);
        }
        //Map集合
        if (Map.class.isAssignableFrom(containerClass)) {
            this.injectMap(row, (Map<String, Object>) rs);
            return rs;
        }
        //表示使用集合去装载数据,此时不记录field
        if (Collection.class.isAssignableFrom(containerClass)) {
            this.injectCollection(row, (Collection<Object>) rs);
            return rs;
        }
        //对象
        this.injectObject(row, rs);
        return rs;
    }

    /**
     * 读取所有行
     *
     * @param clazz      数据类型
     * @param handlerRow 操作行
     * @return LinkedList
     */
    protected <T> List<T> loadRows(Class<T> clazz, HandlerRowA<T> handlerRow) {
        Iterator<Row> rows = this.sheet.iterator();
        int i = 0;
        //开始读取的行号
        int start = this.rowCursor;
        LinkedList<T> records = new LinkedList<>();
        while (rows.hasNext()) {
            //小于等于行游标的不读
            if (i++ <= start) {
                rows.next();
                continue;
            }
            T row = this.injectRow(rows.next(), clazz);
            records.add(row);
            handlerRow.accept(row, records, this.rowCursor + 1, records.size() - 1);
        }
        return records;
    }

    /**
     * 读取所有行
     *
     * @param clazz      数据类型
     * @param handlerRow 操作行,返回false不继续读取下一行
     * @return LinkedList
     */
    protected <T> List<T> loadRows(Class<T> clazz, HandlerRowB<T> handlerRow) {
        Iterator<Row> rows = this.sheet.iterator();
        int i = 0;
        //开始读取的行号
        int start = this.rowCursor;
        LinkedList<T> records = new LinkedList<>();
        while (rows.hasNext()) {
            //小于等于行游标的不读
            if (i++ <= start) {
                rows.next();
                continue;
            }
            T row = this.injectRow(rows.next(), clazz);
            records.add(row);
            boolean goon = handlerRow.apply(row, records, this.rowCursor + 1, records.size() - 1);
            if (!goon) {
                break;
            }
        }
        return records;
    }

    /**
     * 读取所有行
     *
     * @param clazz 数据类型
     * @return LinkedList
     */
    protected <T> List<T> loadRows(Class<T> clazz) {
        Iterator<Row> rows = this.sheet.iterator();
        int i = 0;
        //开始读取的行号
        int start = this.rowCursor;
        LinkedList<T> records = new LinkedList<>();
        while (rows.hasNext()) {
            //小于等于行游标的不读
            if (i++ <= start) {
                rows.next();
                continue;
            }
            T row = this.injectRow(rows.next(), clazz);
            records.add(row);
        }
        return records;
    }

    /**
     * 解析表头
     *
     * @param titles 表头合并单元格信息
     */
    protected void parseExportTitles(Collection<BaseExcelTitleCell> titles) {
        int maxRowNum = this.rowCursor + 1;
        for (BaseExcelTitleCell title : titles) {
            XSSFTitleCell titleCell = (XSSFTitleCell) title;
            if (titleCell.getEndRowNum() > maxRowNum) {
                maxRowNum = titleCell.getEndRowNum();
            }
        }
        //记录行号
        int finalMaxRowIndex = maxRowNum - 1;
        setRowCursor(idx -> finalMaxRowIndex);
    }

    /**
     * 解析表头
     *
     * @param titles  表头合并单元格信息
     * @param rowSpan 占用行数
     */
    protected void parseExportTitles(Collection<BaseExcelTitleCell> titles, int rowSpan) {
        //无穷小
        double maxRowNum = Double.NEGATIVE_INFINITY;
        //无穷大
        double minRowNum = Double.POSITIVE_INFINITY;
        for (BaseExcelTitleCell title : titles) {
            XSSFTitleCell titleCell = (XSSFTitleCell) title;
            if (titleCell.getEndRowNum() > maxRowNum) {
                maxRowNum = titleCell.getEndRowNum();
            }
            if (titleCell.getStartRowNum() < minRowNum) {
                minRowNum = titleCell.getStartRowNum();
            }
        }
        //记录行号
        NumberFormat nf = NumberFormat.getInstance();
        int finalMaxRowIndex = Integer.parseInt(nf.format(maxRowNum)) - 1;
        int finalMinRowIndex = Integer.parseInt(nf.format(minRowNum)) - 1;
        int index = finalMinRowIndex + rowSpan - 1;
        setRowCursor(idx -> Math.max(finalMaxRowIndex, index));
    }

    @FunctionalInterface
    private interface CellParser {

        /**
         * 处理单元格
         *
         * @param cell 单元格
         */
        void accept(org.apache.poi.xssf.usermodel.XSSFCell cell);
    }
}