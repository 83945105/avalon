package pub.avalonframework.core.spi.beans;

import pub.avalonframework.core.beans.AvalonException;

/**
 * @author baichao
 */
public class SpiInstantiationException extends AvalonException {

    protected Class<?> spiClass;

    public SpiInstantiationException(Class<?> spiClass, String message) {
        super((spiClass == null ? "" : "The spi class: " + spiClass.getName() + " instance fail. ") + message);
        this.spiClass = spiClass;
    }

    public SpiInstantiationException(Class<?> spiClass, String message, Throwable cause) {
        super((spiClass == null ? "" : "The spi class: " + spiClass.getName() + " instance fail. ") + message, cause);
        this.spiClass = spiClass;
    }

    public Class<?> getSpiClass() {
        return spiClass;
    }
}