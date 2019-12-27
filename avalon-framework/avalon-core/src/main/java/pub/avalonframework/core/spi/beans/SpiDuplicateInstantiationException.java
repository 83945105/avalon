package pub.avalonframework.core.spi.beans;

import java.lang.reflect.Method;

/**
 * @author baichao
 */
public final class SpiDuplicateInstantiationException extends SpiInstantiationException {

    private Class<?> instanceSpiClass;

    private Class<?> duplicateSpiClass;

    public SpiDuplicateInstantiationException(Class<?> instanceSpiClass, Class<?> duplicateSpiClass, Class<?> spiClass, Class<?> exceptionClass, Method exceptionMethod) {
        super(spiClass, exceptionClass, exceptionMethod, "Class: " + instanceSpiClass.getName() + " has been instantiated, but a duplicate implementation class: " + duplicateSpiClass.getName() + " has been discovered.");
    }

    public SpiDuplicateInstantiationException(Class<?> instanceSpiClass, Class<?> duplicateSpiClass, Class<?> spiClass, Class<?> exceptionClass, Method exceptionMethod, Throwable cause) {
        super(spiClass, exceptionClass, exceptionMethod, "Class: " + instanceSpiClass.getName() + " has been instantiated, but a duplicate implementation class: " + duplicateSpiClass.getName() + " has been discovered.", cause);
    }

    public Class<?> getInstanceSpiClass() {
        return instanceSpiClass;
    }

    public Class<?> getDuplicateSpiClass() {
        return duplicateSpiClass;
    }
}