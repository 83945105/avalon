package pub.avalonframework.sqlhelper.core.data;

import pub.avalonframework.database.DatabaseType;
import pub.avalonframework.sqlhelper.core.api.config.SqlhelperConfiguration;

/**
 * @author baichao
 */
public interface SqlDataOptionsProducer {

    /**
     * set sqlhelper configuration.
     *
     * @param sqlhelperConfiguration {@link SqlhelperConfiguration}
     */
    void setSqlhelperConfiguration(SqlhelperConfiguration sqlhelperConfiguration);

    /**
     * set database type
     *
     * @param databaseType {@link DatabaseType}
     */
    void setDatabaseType(DatabaseType databaseType);
}