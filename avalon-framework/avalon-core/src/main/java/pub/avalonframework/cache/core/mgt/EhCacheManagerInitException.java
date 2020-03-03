package pub.avalonframework.cache.core.mgt;

import pub.avalonframework.core.beans.AvalonException;

/**
 * @author baichao
 */
public class EhCacheManagerInitException extends AvalonException {

    public EhCacheManagerInitException(String message) {
        super(message);
    }

    public EhCacheManagerInitException(String message, Throwable cause) {
        super(message, cause);
    }
}
