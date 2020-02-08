package pub.avalonframework.core.spi.beans;

/**
 * @author baichao
 */
public final class SpiNoneInstantiationException extends SpiInstantiationException {

    public SpiNoneInstantiationException(Class<?> spiClass) {
        super(spiClass, " none implementation class has been discovered.");
    }

    public SpiNoneInstantiationException(Class<?> spiClass, Throwable cause) {
        super(spiClass, " none implementation class has been discovered.", cause);
    }
}