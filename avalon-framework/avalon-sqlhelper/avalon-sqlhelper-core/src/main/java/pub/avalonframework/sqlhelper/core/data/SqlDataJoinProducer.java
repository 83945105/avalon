package pub.avalonframework.sqlhelper.core.data;

/**
 * @author baichao
 */
public interface SqlDataJoinProducer {

    /**
     * add join table data
     *
     * @param joinTableDatum {@link JoinTableDatum}
     */
    void addJoinTableDatum(JoinTableDatum joinTableDatum);
}