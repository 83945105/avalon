package pub.avalonframework.web.spring.http.response.utils;

import pub.avalonframework.core.beans.AvalonException;

import java.lang.reflect.Method;

/**
 * @author baichao
 */
public class ObjectToJsonException extends AvalonException {

    public ObjectToJsonException(Class<?> exceptionClass, Method exceptionMethod, String message) {
        super(exceptionClass, exceptionMethod, message);
    }

    public ObjectToJsonException(Class<?> exceptionClass, Method exceptionMethod, String message, Throwable cause) {
        super(exceptionClass, exceptionMethod, message, cause);
    }

    public ObjectToJsonException(Class<?> exceptionClass, String exceptionMethodName, String message) {
        super(exceptionClass, exceptionMethodName, message);
    }

    public ObjectToJsonException(Class<?> exceptionClass, String exceptionMethodName, String message, Throwable cause) {
        super(exceptionClass, exceptionMethodName, message, cause);
    }
}