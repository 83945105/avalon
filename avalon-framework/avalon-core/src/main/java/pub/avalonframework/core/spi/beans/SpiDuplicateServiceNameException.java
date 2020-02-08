package pub.avalonframework.core.spi.beans;

/**
 * @author baichao
 */
public final class SpiDuplicateServiceNameException extends SpiInstantiationException {

    private SpiService duplicateSpiService;

    public SpiDuplicateServiceNameException(SpiService duplicateSpiService, Class<?> spiClass) {
        super(spiClass, "ServiceName: " + duplicateSpiService.getServiceName() + " has been instantiated, but a duplicate implementation serviceName has been discovered.");
        this.duplicateSpiService = duplicateSpiService;
    }

    public SpiDuplicateServiceNameException(SpiService duplicateSpiService, Class<?> spiClass, Throwable cause) {
        super(spiClass, "ServiceName: " + duplicateSpiService.getServiceName() + " has been instantiated, but a duplicate implementation serviceName has been discovered.", cause);
        this.duplicateSpiService = duplicateSpiService;
    }

    public SpiService getDuplicateSpiService() {
        return duplicateSpiService;
    }
}