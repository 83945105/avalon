package pub.avalonframework.security.core.api.beans;

import pub.avalonframework.core.beans.CacheException;

import java.lang.reflect.Method;

/**
 * @author baichao
 */
public final class IncorrectCacheTypeException extends CacheException {

    private CacheType incorrectCacheType;

    public IncorrectCacheTypeException(CacheType incorrectCacheType, Class<?> exceptionClass, Method exceptionMethod) {
        super(exceptionClass, exceptionMethod, "Incorrect cache type : " + incorrectCacheType.name());
        this.incorrectCacheType = incorrectCacheType;
    }

    public IncorrectCacheTypeException(CacheType incorrectCacheType, Class<?> exceptionClass, Method exceptionMethod, Throwable cause) {
        super(exceptionClass, exceptionMethod, "Incorrect cache type : " + incorrectCacheType.name(), cause);
        this.incorrectCacheType = incorrectCacheType;
    }

    public CacheType getIncorrectCacheType() {
        return incorrectCacheType;
    }
}