package pub.avalonframework.office.excel.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pub.avalonframework.office.excel.ExcelException;
import pub.avalonframework.office.excel.ExcelSheetImport;
import pub.avalonframework.office.excel.ExcelWorkBookImport;
import pub.avalonframework.office.excel.parser.AbstractXSSFExcelParser;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

/**
 * XSSFExcelWorkBookImport 导入Excel
 *
 * @author baichao
 */
public class XSSFExcelWorkBookImport<R> extends AbstractXSSFExcelParser implements ExcelWorkBookImport<R> {

    protected Class<R> defaultSheetRowType;

    protected TypeReference<R> defaultSheetRowTypeReference;

    protected XSSFWorkbook xssfWorkbook;

    protected List<XSSFExcelSheetImport<R>> sheets = new LinkedList<>();

    public XSSFExcelWorkBookImport(Class<R> defaultSheetRowType) {
        this(defaultSheetRowType, new XSSFWorkbook());
    }

    public XSSFExcelWorkBookImport(TypeReference<R> defaultSheetRowTypeReference) {
        this(defaultSheetRowTypeReference, new XSSFWorkbook());
    }

    public XSSFExcelWorkBookImport(TypeReference<R> defaultSheetRowTypeReference, XSSFWorkbook xssfWorkbook) {
        Type type = defaultSheetRowTypeReference.getType();
        if(type instanceof Class<?>) {
            this.defaultSheetRowType = (Class<R>) type;
        }else if(type instanceof ParameterizedType) {
            this.defaultSheetRowType = (Class<R>) ((ParameterizedType) type).getRawType();
        }
        this.xssfWorkbook = xssfWorkbook;
        this.defaultSheetRowTypeReference = defaultSheetRowTypeReference;
    }

    public XSSFExcelWorkBookImport(Class<R> defaultSheetRowType, XSSFWorkbook xssfWorkbook) {
        this.defaultSheetRowType = defaultSheetRowType;
        this.xssfWorkbook = xssfWorkbook;
        this.defaultSheetRowTypeReference = new TypeReference<R>() {
            @Override
            public Type getType() {
                return XSSFExcelWorkBookImport.this.defaultSheetRowType;
            }
        };
    }

    /**
     * 初始化Sheets
     */
    protected void initSheets() {
        for (int i = 0; i < xssfWorkbook.getNumberOfSheets(); i++) {
            this.sheets.add(new XSSFExcelSheetImport<>(defaultSheetRowType, xssfWorkbook.getSheetAt(i), this));
        }
    }

    @Override
    public ExcelWorkBookImport<R> parseFile(InputStream inputStream) {
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
    public ExcelSheetImport<R> getSheet(int index) {
        return this.sheets.get(index);
    }

    @Override
    public int getSheetSize() {
        return this.sheets.size();
    }
}