package pub.avalonframework.sqlhelper.core.data;

import pub.avalonframework.database.DatabaseType;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;

/**
 * @author baichao
 */
public interface SqlDataUpdateProducer {

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
     * add join table data
     *
     * @param joinTableDatum {@link JoinTableDatum}
     */
    void addJoinTableDatum(JoinTableDatum joinTableDatum);

    /**
     * add table on data
     *
     * @param tableOnDatum {@link TableOnDatum}
     */
    void addTableOnDatum(TableOnDatum tableOnDatum);

    /**
     * add update table column data
     *
     * @param tableColumnDatum {@link TableColumnDatum}
     */
    void addUpdateTableColumnDatum(TableColumnDatum tableColumnDatum);

    /**
     * add table where data
     *
     * @param tableWhereDatum {@link TableWhereDatum}
     */
    void addTableWhereDatum(TableWhereDatum tableWhereDatum);
}