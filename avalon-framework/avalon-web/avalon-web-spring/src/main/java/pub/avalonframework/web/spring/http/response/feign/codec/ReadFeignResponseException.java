package pub.avalonframework.web.spring.http.response.feign.codec;

import pub.avalonframework.core.beans.AvalonException;

import java.lang.reflect.Method;

/**
 * @author 白超
 */
public class ReadFeignResponseException extends AvalonException {

    public ReadFeignResponseException(Class<?> exceptionClass, Method exceptionMethod, String message) {
        super(exceptionClass, exceptionMethod, message);
    }

    public ReadFeignResponseException(Class<?> exceptionClass, Method exceptionMethod, String message, Throwable cause) {
        super(exceptionClass, exceptionMethod, message, cause);
    }

    public ReadFeignResponseException(Class<?> exceptionClass, String exceptionMethodName, String message) {
        super(exceptionClass, exceptionMethodName, message);
    }

    public ReadFeignResponseException(Class<?> exceptionClass, String exceptionMethodName, String message, Throwable cause) {
        super(exceptionClass, exceptionMethodName, message, cause);
    }
}
