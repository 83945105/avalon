package pub.avalonframework.security.core.api.beans;

import pub.avalonframework.core.beans.CacheException;

/**
 * @author baichao
 */
public final class IncorrectCacheTypeException extends CacheException {

    private CacheType incorrectCacheType;

    public IncorrectCacheTypeException(CacheType incorrectCacheType) {
        super("Incorrect cache type : " + incorrectCacheType.name());
        this.incorrectCacheType = incorrectCacheType;
    }

    public IncorrectCacheTypeException(CacheType incorrectCacheType, Throwable cause) {
        super("Incorrect cache type : " + incorrectCacheType.name(), cause);
        this.incorrectCacheType = incorrectCacheType;
    }

    public CacheType getIncorrectCacheType() {
        return incorrectCacheType;
    }
}