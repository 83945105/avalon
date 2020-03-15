package pub.avalonframework.office.excel.impl;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import pub.avalonframework.office.excel.*;
import pub.avalonframework.office.excel.parser.AbstractSXSSFExcelParser;

import java.io.*;
import java.util.ArrayList;

/**
 * SXSSFWorkbook 导出Excel
 *
 * @author baichao
 */
public class SXSSFExcelWorkBookExport extends AbstractSXSSFExcelParser implements ExcelWorkBookExport {

    protected SXSSFWorkbook sxssfWorkbook;

    protected ArrayList<SXSSFExcelSheetExport> sheets = new ArrayList<>();

    private ArrayList<CellStyle> cellStyles = new ArrayList<>();

    private ArrayList<Font> fonts = new ArrayList<>();

    public SXSSFExcelWorkBookExport() {
        this.sxssfWorkbook = new SXSSFWorkbook();
    }

    public SXSSFExcelWorkBookExport(int rowAccessWindowSize) {
        this.sxssfWorkbook = new SXSSFWorkbook(rowAccessWindowSize);
    }

    public SXSSFExcelWorkBookExport(SXSSFWorkbook workbook) {
        this.sxssfWorkbook = workbook;
    }

    @Override
    public ExcelSheetExport createSheet() {
        return this.createSheet("sheet" + sheets.size());
    }

    @Override
    public ExcelSheetExport createSheet(String sheetName) {
        SXSSFExcelSheetExport sheet = new SXSSFExcelSheetExport(sheetName, this);
        this.sheets.add(sheet);
        return sheet;
    }

    @Override
    public ExcelSheetExport getSheet(int index) {
        return this.sheets.get(index);
    }

    @Override
    public CellStyle findCellStyle(int index) {
        return this.cellStyles.get(index);
    }

    @Override
    public Font findFont(int index) {
        return this.fonts.get(index);
    }

    @Override
    public CellStyle createCellStyle(int index) {
        CellStyle cellStyle = new CellStyleWrapper(this.sxssfWorkbook.createCellStyle());
        this.cellStyles.add(index, cellStyle);
        return cellStyle;
    }

    @Override
    public Font createFont(int index) {
        Font font = new FontWrapper(this.sxssfWorkbook.createFont());
        this.fonts.add(index, font);
        return font;
    }

    @Override
    public int getSheetSize() {
        return sheets.size();
    }

    @Override
    public void export(File outFile) throws IOException {
        FileOutputStream fos = null;
        OutputStream osw = null;
        try {
            fos = new FileOutputStream(outFile);
            osw = new BufferedOutputStream(fos);
            this.sxssfWorkbook.write(osw);
        } finally {
            try {
                if (osw != null) {
                    osw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public BaseExcelTitleCell buildExcelTitleCell(BaseExcelTitleCell excelTitle, int startRow, int endRow, int startCol, int endCol) {
        excelTitle.setStartRowNum(startRow + 1);
        excelTitle.setStartColNum(startCol + 1);
        return excelTitle;
    }
}