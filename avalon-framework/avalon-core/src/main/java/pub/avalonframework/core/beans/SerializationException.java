package pub.avalonframework.core.beans;

import java.lang.reflect.Method;

/**
 * Serialization exception.
 *
 * @author baichao
 */
public class SerializationException extends AvalonException {

    public SerializationException(Class<?> exceptionClass, Method exceptionMethod) {
        super(exceptionClass, exceptionMethod, "Serialization failed.");
    }

    public SerializationException(Class<?> exceptionClass, Method exceptionMethod, Throwable cause) {
        super(exceptionClass, exceptionMethod, "Serialization failed.", cause);
    }
}