package pub.avalonframework.office.excel.impl;

import pub.avalonframework.office.excel.ExcelException;

/**
 * @author baichao
 */
public class UnsupportedFont extends ExcelException {

    public UnsupportedFont() {
    }

    public UnsupportedFont(String message) {
        super(message);
    }

    public UnsupportedFont(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedFont(Throwable cause) {
        super(cause);
    }
}