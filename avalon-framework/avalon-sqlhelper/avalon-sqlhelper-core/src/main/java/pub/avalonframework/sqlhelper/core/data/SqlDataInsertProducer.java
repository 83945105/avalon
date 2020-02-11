package pub.avalonframework.sqlhelper.core.data;

/**
 * @author baichao
 */
public interface SqlDataInsertProducer extends SqlDataOptionsProducer {

    /**
     * add insert table column data
     *
     * @param tableColumnDatum {@link TableColumnDatum}
     */
    void addInsertTableColumnDatum(TableColumnDatum tableColumnDatum);
}