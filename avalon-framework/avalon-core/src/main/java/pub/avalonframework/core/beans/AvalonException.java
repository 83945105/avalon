package pub.avalonframework.core.beans;

import java.lang.reflect.Method;

/**
 * @author baichao
 */
public class AvalonException extends RuntimeException {

    protected Class<?> exceptionClass;

    protected Method exceptionMethod;

    public AvalonException(Class<?> exceptionClass, Method exceptionMethod, String message) {
        super((exceptionClass == null ? "" : "Class: " + exceptionClass.getName() + " ") + (exceptionMethod == null ? "" : "Method: " + exceptionMethod.getName() + " ") + message);
        this.exceptionClass = exceptionClass;
        this.exceptionMethod = exceptionMethod;
    }

    public AvalonException(Class<?> exceptionClass, Method exceptionMethod, String message, Throwable cause) {
        super((exceptionClass == null ? "" : "Class: " + exceptionClass.getName() + " ") + (exceptionMethod == null ? "" : "Method: " + exceptionMethod.getName() + " ") + message, cause);
        this.exceptionClass = exceptionClass;
        this.exceptionMethod = exceptionMethod;
    }

    public Class<?> getExceptionClass() {
        return exceptionClass;
    }

    public Method getExceptionMethod() {
        return exceptionMethod;
    }
}