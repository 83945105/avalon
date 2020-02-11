package pub.avalonframework.sqlhelper.core.data;

/**
 * @author baichao
 */
public interface SqlDataUpdateProducer extends SqlDataOptionsProducer, SqlDataJoinProducer, SqlDataOnProducer, SqlDataWhereProducer {

    /**
     * add update table column data
     *
     * @param tableColumnDatum {@link TableColumnDatum}
     */
    void addUpdateTableColumnDatum(TableColumnDatum tableColumnDatum);
}