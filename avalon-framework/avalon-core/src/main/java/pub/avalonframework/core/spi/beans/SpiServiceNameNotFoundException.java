package pub.avalonframework.core.spi.beans;

import java.lang.reflect.Method;

/**
 * @author baichao
 */
public final class SpiServiceNameNotFoundException extends SpiInstantiationException {

    private String serviceName;

    public SpiServiceNameNotFoundException(String serviceName, Class<?> spiClass, Class<?> exceptionClass, Method exceptionMethod) {
        super(spiClass, exceptionClass, exceptionMethod, " No implementation class with service name " + serviceName + " was found.");
        this.serviceName = serviceName;
    }

    public SpiServiceNameNotFoundException(String serviceName, Class<?> spiClass, Class<?> exceptionClass, Method exceptionMethod, Throwable cause) {
        super(spiClass, exceptionClass, exceptionMethod, " No implementation class with service name " + serviceName + " was found.", cause);
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return serviceName;
    }
}