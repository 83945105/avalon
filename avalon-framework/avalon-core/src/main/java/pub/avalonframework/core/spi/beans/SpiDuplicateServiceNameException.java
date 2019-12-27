package pub.avalonframework.core.spi.beans;

import java.lang.reflect.Method;

/**
 * @author baichao
 */
public final class SpiDuplicateServiceNameException extends SpiInstantiationException {

    private SpiService duplicateSpiService;

    public SpiDuplicateServiceNameException(SpiService duplicateSpiService, Class<?> spiClass, Class<?> exceptionClass, Method exceptionMethod) {
        super(spiClass, exceptionClass, exceptionMethod, "ServiceName: " + duplicateSpiService.getServiceName() + " has been instantiated, but a duplicate implementation serviceName has been discovered.");
        this.duplicateSpiService = duplicateSpiService;
    }

    public SpiDuplicateServiceNameException(SpiService duplicateSpiService, Class<?> spiClass, Class<?> exceptionClass, Method exceptionMethod, Throwable cause) {
        super(spiClass, exceptionClass, exceptionMethod, "ServiceName: " + duplicateSpiService.getServiceName() + " has been instantiated, but a duplicate implementation serviceName has been discovered.", cause);
        this.duplicateSpiService = duplicateSpiService;
    }

    public SpiService getDuplicateSpiService() {
        return duplicateSpiService;
    }
}