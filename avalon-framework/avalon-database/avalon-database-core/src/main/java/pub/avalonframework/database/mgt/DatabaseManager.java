package pub.avalonframework.database.mgt;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The database manager.
 *
 * @author baichao
 */
public final class DatabaseManager {

    private final static Map<DataSource, DatabaseManager> DATA_SOURCE_DATABASE_MANAGER_CACHE = new ConcurrentHashMap<>();

    private DataSource dataSource;

    private DatabaseManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static DatabaseManager getInstance(DataSource dataSource) {
        DatabaseManager databaseManager = DATA_SOURCE_DATABASE_MANAGER_CACHE.get(dataSource);
        if (databaseManager == null) {
            databaseManager = new DatabaseManager(dataSource);
            DATA_SOURCE_DATABASE_MANAGER_CACHE.put(dataSource, databaseManager);
        }
        return databaseManager;
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}