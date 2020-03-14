package pub.avalonframework.office.excel.impl;

import pub.avalonframework.office.excel.ExcelException;

/**
 * @author baichao
 */
public class UnsupportedCellStyle extends ExcelException {

    public UnsupportedCellStyle() {
    }

    public UnsupportedCellStyle(String message) {
        super(message);
    }

    public UnsupportedCellStyle(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedCellStyle(Throwable cause) {
        super(cause);
    }
}