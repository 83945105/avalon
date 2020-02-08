package pub.avalonframework.core.beans;

/**
 * Serialization exception.
 *
 * @author baichao
 */
public class SerializationException extends AvalonException {

    public SerializationException() {
    }

    public SerializationException(String message) {
        super(message);
    }

    public SerializationException(String message, Throwable cause) {
        super(message, cause);
    }

    public SerializationException(Throwable cause) {
        super(cause);
    }
}