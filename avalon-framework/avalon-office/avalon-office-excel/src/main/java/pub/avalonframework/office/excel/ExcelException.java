package pub.avalonframework.office.excel;

import pub.avalonframework.core.beans.AvalonException;

/**
 * @author baichao
 */
public class ExcelException extends AvalonException {

    public ExcelException() {
    }

    public ExcelException(String message) {
        super(message);
    }

    public ExcelException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExcelException(Throwable cause) {
        super(cause);
    }
}