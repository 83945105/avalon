package pub.avalonframework.core.beans;

import java.lang.reflect.Method;

/**
 * @author baichao
 */
public class CacheException extends AvalonException {

    public CacheException(Class<?> exceptionClass, Method exceptionMethod, String message) {
        super(exceptionClass, exceptionMethod, message);
    }

    public CacheException(Class<?> exceptionClass, Method exceptionMethod, String message, Throwable cause) {
        super(exceptionClass, exceptionMethod, message, cause);
    }
}