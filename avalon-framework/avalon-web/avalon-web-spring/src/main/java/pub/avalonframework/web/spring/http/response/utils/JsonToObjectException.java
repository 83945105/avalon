package pub.avalonframework.web.spring.http.response.utils;

import pub.avalonframework.core.beans.AvalonException;

import java.lang.reflect.Method;

/**
 * @author baichao
 */
public class JsonToObjectException extends AvalonException {

    public JsonToObjectException(Class<?> exceptionClass, Method exceptionMethod, String message) {
        super(exceptionClass, exceptionMethod, message);
    }

    public JsonToObjectException(Class<?> exceptionClass, Method exceptionMethod, String message, Throwable cause) {
        super(exceptionClass, exceptionMethod, message, cause);
    }

    public JsonToObjectException(Class<?> exceptionClass, String exceptionMethodName, String message) {
        super(exceptionClass, exceptionMethodName, message);
    }

    public JsonToObjectException(Class<?> exceptionClass, String exceptionMethodName, String message, Throwable cause) {
        super(exceptionClass, exceptionMethodName, message, cause);
    }
}