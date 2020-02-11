package pub.avalonframework.sqlhelper.core.data;

/**
 * @author baichao
 */
public interface SqlDataSelectProducer extends SqlDataOptionsProducer, SqlDataColumnProducer, SqlDataJoinProducer, SqlDataOnProducer, SqlDataWhereProducer, SqlDataGroupProducer, SqlDataHavingProducer, SqlDataSortProducer, SqlDataLimitProducer {

    /**
     * add select table column data
     *
     * @param tableColumnDatum {@link TableColumnDatum}
     */
    void addSelectTableColumnDatum(TableColumnDatum tableColumnDatum);
}