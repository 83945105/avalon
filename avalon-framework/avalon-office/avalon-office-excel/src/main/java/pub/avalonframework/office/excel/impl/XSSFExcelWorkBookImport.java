package pub.avalonframework.office.excel.impl;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pub.avalonframework.office.excel.ExcelException;
import pub.avalonframework.office.excel.ExcelSheetImport;
import pub.avalonframework.office.excel.ExcelWorkBookImport;
import pub.avalonframework.office.excel.parser.AbstractXSSFExcelParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * XSSFExcelWorkBookImport 导入Excel
 *
 * @author baichao
 */
public class XSSFExcelWorkBookImport extends AbstractXSSFExcelParser implements ExcelWorkBookImport {

    protected XSSFWorkbook xssfWorkbook;

    protected List<XSSFExcelSheetImport<?>> sheets = new LinkedList<>();

    public XSSFExcelWorkBookImport() {
        this.xssfWorkbook = new XSSFWorkbook();
    }

    public XSSFExcelWorkBookImport(XSSFWorkbook xssfWorkbook) {
        this.xssfWorkbook = xssfWorkbook;
    }

    /**
     * 初始化Sheets
     */
    protected void initSheets() {
        for (int i = 0; i < xssfWorkbook.getNumberOfSheets(); i++) {
            this.sheets.add(new XSSFExcelSheetImport<>(ExcelSheetImport.SheetRowMap.class, xssfWorkbook.getSheetAt(i), this));
        }
    }

    @Override
    public ExcelWorkBookImport parseFile(InputStream inputStream) {
        try {
            this.xssfWorkbook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ExcelException(e);
        }
        this.initSheets();
        return this;
    }

    @Override
    public ExcelSheetImport<?> getSheet(int index) {
        return this.sheets.get(index);
    }

    @Override
    public int getSheetSize() {
        return this.sheets.size();
    }
}