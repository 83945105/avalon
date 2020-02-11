package pub.avalonframework.sqlhelper.core.data;

/**
 * @author baichao
 */
public interface SqlDataLimitProducer {

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