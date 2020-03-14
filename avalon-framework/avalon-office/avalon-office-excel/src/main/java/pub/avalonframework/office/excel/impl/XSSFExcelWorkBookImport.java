package pub.avalonframework.office.excel.impl;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pub.avalonframework.office.excel.ExcelSheetImport;
import pub.avalonframework.office.excel.ExcelWorkBookImport;
import pub.avalonframework.office.excel.parser.AbstractXSSFExcelParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * XSSFExcelWorkBookImport 导入Excel
 *
 * @author baichao
 */
public class XSSFExcelWorkBookImport extends AbstractXSSFExcelParser implements ExcelWorkBookImport {

    protected XSSFWorkbook xssfWorkbook;

    protected ArrayList<XSSFExcelSheetImport> sheets = new ArrayList<>();

    public XSSFExcelWorkBookImport() {
        this.xssfWorkbook = new XSSFWorkbook();
    }

    public XSSFExcelWorkBookImport(XSSFWorkbook xssfWorkbook) {
        this.xssfWorkbook = xssfWorkbook;
    }

    /**
     * 初始化Sheets
     *
     * @return
     */
    protected ExcelWorkBookImport initSheets() {
        for (int i = 0; i < xssfWorkbook.getNumberOfSheets(); i++) {
            this.sheets.add(new XSSFExcelSheetImport(xssfWorkbook.getSheetAt(i), this));
        }
        return this;
    }

    @Override
    public ExcelWorkBookImport parseFile(InputStream inputStream) throws IOException {
        this.xssfWorkbook = new XSSFWorkbook(inputStream);
        this.initSheets();
        return this;
    }

    @Override
    public ExcelSheetImport getSheet(int index) {
        return this.sheets.get(index);
    }

    @Override
    public int getSheetSize() {
        return this.sheets.size();
    }
}