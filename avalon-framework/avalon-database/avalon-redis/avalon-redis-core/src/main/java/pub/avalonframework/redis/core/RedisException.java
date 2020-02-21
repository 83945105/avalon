package pub.avalonframework.redis.core;

import pub.avalonframework.core.beans.AvalonException;

/**
 * @author baichao
 */
public class RedisException extends AvalonException {

    public RedisException() {
    }

    public RedisException(String message) {
        super(message);
    }

    public RedisException(String message, Throwable cause) {
        super(message, cause);
    }

    public RedisException(Throwable cause) {
        super(cause);
    }
}