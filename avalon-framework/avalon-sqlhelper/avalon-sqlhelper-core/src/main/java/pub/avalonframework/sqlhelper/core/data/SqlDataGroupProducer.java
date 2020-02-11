package pub.avalonframework.sqlhelper.core.data;

/**
 * @author baichao
 */
public interface SqlDataGroupProducer {

    /**
     * add table group data
     *
     * @param tableGroupDatum {@link TableGroupDatum}
     */
    void addTableGroupDatum(TableGroupDatum tableGroupDatum);
}