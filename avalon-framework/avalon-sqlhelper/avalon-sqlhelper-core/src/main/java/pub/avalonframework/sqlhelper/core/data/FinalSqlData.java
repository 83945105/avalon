package pub.avalonframework.sqlhelper.core.data;

import pub.avalonframework.database.DatabaseType;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;

/**
 * @author baichao
 */
public final class FinalSqlData extends AbstractSqlData {

    public FinalSqlData(DatabaseType databaseType, MainTableDatum mainTableDatum, SqlBuilderOptions sqlBuilderOptions) {
        super(mainTableDatum);
        this.setSqlBuilderOptions(sqlBuilderOptions);
        this.setDatabaseType(databaseType);
    }
}