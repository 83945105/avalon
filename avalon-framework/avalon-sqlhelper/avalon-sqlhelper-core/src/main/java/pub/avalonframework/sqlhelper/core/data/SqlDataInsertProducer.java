package pub.avalonframework.sqlhelper.core.data;

import pub.avalonframework.database.DatabaseType;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;

/**
 * @author baichao
 */
public interface SqlDataInsertProducer {

    /**
     * set sql builder options
     *
     * @param sqlBuilderOptions {@link SqlBuilderOptions}
     */
    void setSqlBuilderOptions(SqlBuilderOptions sqlBuilderOptions);

    /**
     * set database type
     *
     * @param databaseType {@link DatabaseType}
     */
    void setDatabaseType(DatabaseType databaseType);

    /**
     * add insert table column data
     *
     * @param tableColumnDatum {@link TableColumnDatum}
     */
    void addInsertTableColumnDatum(TableColumnDatum tableColumnDatum);
}