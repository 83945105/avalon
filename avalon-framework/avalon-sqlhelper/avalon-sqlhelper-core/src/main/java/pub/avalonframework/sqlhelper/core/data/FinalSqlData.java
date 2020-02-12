package pub.avalonframework.sqlhelper.core.data;

import pub.avalonframework.database.DatabaseType;
import pub.avalonframework.sqlhelper.core.api.config.SqlhelperConfiguration;

/**
 * @author baichao
 */
public final class FinalSqlData extends AbstractSqlData {

    public FinalSqlData(DatabaseType databaseType, MainTableDatum mainTableDatum, SqlhelperConfiguration configuration) {
        super(mainTableDatum);
        this.setConfiguration(configuration);
    }
}