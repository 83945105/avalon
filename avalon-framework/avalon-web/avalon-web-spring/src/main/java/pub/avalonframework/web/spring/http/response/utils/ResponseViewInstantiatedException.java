package pub.avalonframework.web.spring.http.response.utils;

import pub.avalonframework.core.beans.AvalonException;

import java.lang.reflect.Method;

/**
 * @author baichao
 */
public class ResponseViewInstantiatedException extends AvalonException {

    public ResponseViewInstantiatedException(Class<?> exceptionClass, Method exceptionMethod, String message) {
        super(exceptionClass, exceptionMethod, message);
    }

    public ResponseViewInstantiatedException(Class<?> exceptionClass, Method exceptionMethod, String message, Throwable cause) {
        super(exceptionClass, exceptionMethod, message, cause);
    }

    public ResponseViewInstantiatedException(Class<?> exceptionClass, String exceptionMethodName, String message) {
        super(exceptionClass, exceptionMethodName, message);
    }

    public ResponseViewInstantiatedException(Class<?> exceptionClass, String exceptionMethodName, String message, Throwable cause) {
        super(exceptionClass, exceptionMethodName, message, cause);
    }
}