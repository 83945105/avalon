package pub.avalonframework.office.excel;

/**
 * @author baichao
 */
public class ExportException extends ExcelException {

    public ExportException() {
    }

    public ExportException(String message) {
        super(message);
    }

    public ExportException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExportException(Throwable cause) {
        super(cause);
    }
}