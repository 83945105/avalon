package pub.avalonframework.core.beans;

import java.lang.reflect.Method;

/**
 * Operation failure exception.
 *
 * @author baichao
 */
public class OperationFailureException extends AvalonException {

    public OperationFailureException(Class<?> exceptionClass, Method exceptionMethod) {
        super(exceptionClass, exceptionMethod, "Operation failed.");
    }

    public OperationFailureException(Class<?> exceptionClass, Method exceptionMethod, String cause) {
        super(exceptionClass, exceptionMethod, "Operation failed, cause: " + cause);
    }
}