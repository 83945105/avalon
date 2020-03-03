package pub.avalonframework.core.yaml.swapper.impl;

import pub.avalonframework.core.beans.AvalonException;

/**
 * EhCache configuration exception.
 *
 * @author baichao
 */
public class EhCacheConfigurationException extends AvalonException {

    public EhCacheConfigurationException(String message) {
        super(message);
    }

    public EhCacheConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}
