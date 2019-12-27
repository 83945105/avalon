package pub.avalonframework.core.spi.beans;

import java.lang.reflect.Method;

/**
 * @author baichao
 */
public final class SpiNoneInstantiationException extends SpiInstantiationException {

    public SpiNoneInstantiationException(Class<?> spiClass, Class<?> exceptionClass, Method exceptionMethod) {
        super(spiClass, exceptionClass, exceptionMethod, " none implementation class has been discovered.");
    }

    public SpiNoneInstantiationException(Class<?> spiClass, Class<?> exceptionClass, Method exceptionMethod, Throwable cause) {
        super(spiClass, exceptionClass, exceptionMethod, " none implementation class has been discovered.", cause);
    }
}