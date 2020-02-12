package pub.avalonframework.sqlhelper.core.data.inject;

/**
 * @author baichao
 */
public interface LimitDataInjector {

    /**
     * set limit data
     *
     * @param limit The limit data
     */
    void setLimit(Long limit);

    /**
     * set offset data
     *
     * @param offset The offset data
     */
    void setOffset(Long offset);
}