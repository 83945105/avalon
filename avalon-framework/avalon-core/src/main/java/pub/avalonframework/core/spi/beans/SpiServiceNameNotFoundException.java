package pub.avalonframework.core.spi.beans;

/**
 * @author baichao
 */
public final class SpiServiceNameNotFoundException extends SpiInstantiationException {

    private String serviceName;

    public SpiServiceNameNotFoundException(String serviceName, Class<?> spiClass) {
        super(spiClass, " No implementation class with service name " + serviceName + " was found.");
        this.serviceName = serviceName;
    }

    public SpiServiceNameNotFoundException(String serviceName, Class<?> spiClass, Throwable cause) {
        super(spiClass, " No implementation class with service name " + serviceName + " was found.", cause);
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return serviceName;
    }
}