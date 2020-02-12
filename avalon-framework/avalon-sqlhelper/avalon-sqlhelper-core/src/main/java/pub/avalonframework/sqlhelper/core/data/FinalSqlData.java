package pub.avalonframework.sqlhelper.core.data;

import pub.avalonframework.database.DatabaseType;
import pub.avalonframework.sqlhelper.core.api.config.SqlhelperConfiguration;

/**
 * @author baichao
 */
public final class FinalSqlData extends AbstractSqlData {

    public FinalSqlData(DatabaseType databaseType, MainTableDatum mainTableDatum, SqlhelperConfiguration sqlhelperConfiguration) {
        super(mainTableDatum);
        this.setSqlhelperConfiguration(sqlhelperConfiguration);
        this.setDatabaseType(databaseType);
    }
}