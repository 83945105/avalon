package pub.avalonframework.sqlhelper.core.data.inject;

/**
 * @author baichao
 */
public interface LimitDataInjector<R> extends DataInjector<R> {

    /**
     * Set limit data.
     *
     * @param limit The limit data
     * @return
     */
    default R setLimit(Long limit) {
        return getDataStore().setLimit(limit);
    }

    /**
     * Set offset data
     *
     * @param offset The offset data
     * @return
     */
    default R setOffset(Long offset) {
        return getDataStore().setOffset(offset);
    }
}