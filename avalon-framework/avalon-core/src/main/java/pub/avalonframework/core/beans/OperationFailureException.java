package pub.avalonframework.core.beans;

/**
 * Operation failure exception.
 *
 * @author baichao
 */
public class OperationFailureException extends AvalonException {

    public OperationFailureException() {
        super("Operation failed.");
    }

    public OperationFailureException(String cause) {
        super("Operation failed, cause: " + cause);
    }
}