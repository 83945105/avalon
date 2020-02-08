package pub.avalonframework.core.beans;

/**
 * @author baichao
 */
public class AvalonException extends RuntimeException {

    public AvalonException() {
    }

    public AvalonException(String message) {
        super(message);
    }

    public AvalonException(String message, Throwable cause) {
        super(message, cause);
    }

    public AvalonException(Throwable cause) {
        super(cause);
    }
}