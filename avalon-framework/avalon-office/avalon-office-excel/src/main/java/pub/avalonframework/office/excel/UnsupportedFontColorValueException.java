package pub.avalonframework.office.excel;

/**
 * @author baichao
 */
public class UnsupportedFontColorValueException extends ExcelException {

    public UnsupportedFontColorValueException() {
    }

    public UnsupportedFontColorValueException(String message) {
        super(message);
    }

    public UnsupportedFontColorValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedFontColorValueException(Throwable cause) {
        super(cause);
    }
}