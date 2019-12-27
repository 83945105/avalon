package pub.avalonframework.core.spi.beans;

/**
 * @author baichao
 */
public interface SpiService {

    /**
     * Get the service name to determine which implementation to use.
     *
     * @return the service name.
     */
    String getServiceName();
}