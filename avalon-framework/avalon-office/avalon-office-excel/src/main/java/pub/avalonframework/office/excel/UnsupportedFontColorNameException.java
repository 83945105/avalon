package pub.avalonframework.office.excel;

/**
 * @author baichao
 */
public class UnsupportedFontColorNameException extends ExcelException {

    public UnsupportedFontColorNameException() {
    }

    public UnsupportedFontColorNameException(String message) {
        super(message);
    }

    public UnsupportedFontColorNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedFontColorNameException(Throwable cause) {
        super(cause);
    }
}