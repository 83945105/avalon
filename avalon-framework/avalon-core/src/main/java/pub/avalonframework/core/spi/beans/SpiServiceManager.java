package pub.avalonframework.core.spi.beans;

/**
 * @author baichao
 */
public interface SpiServiceManager<T extends SpiService> {

    /**
     * Get spi service.
     *
     * @param serviceName the service name from {@link SpiService#getServiceName()}
     * @return the spi service.
     */
    T getSpiService(String serviceName);
}