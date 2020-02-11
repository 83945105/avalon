package pub.avalonframework.sqlhelper.core.data;

/**
 * @author baichao
 */
public interface SqlDataHavingProducer {

    /**
     * add table having data
     *
     * @param tableHavingDatum {@link TableHavingDatum}
     */
    void addTableHavingDatum(TableHavingDatum tableHavingDatum);
}