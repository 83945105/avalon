package pub.avalonframework.core.spi.beans;

import pub.avalonframework.core.beans.AvalonException;

import java.lang.reflect.Method;

/**
 * @author baichao
 */
public class SpiInstantiationException extends AvalonException {

    protected Class<?> spiClass;

    public SpiInstantiationException(Class<?> spiClass, Class<?> exceptionClass, Method exceptionMethod, String message) {
        super(exceptionClass, exceptionMethod, (spiClass == null ? "" : "The spi class: " + spiClass.getName() + " instance fail. ") + message);
        this.spiClass = spiClass;
    }

    public SpiInstantiationException(Class<?> spiClass, Class<?> exceptionClass, Method exceptionMethod, String message, Throwable cause) {
        super(exceptionClass, exceptionMethod, (spiClass == null ? "" : "The spi class: " + spiClass.getName() + " instance fail. ") + message, cause);
        this.spiClass = spiClass;
    }

    public Class<?> getSpiClass() {
        return spiClass;
    }
}