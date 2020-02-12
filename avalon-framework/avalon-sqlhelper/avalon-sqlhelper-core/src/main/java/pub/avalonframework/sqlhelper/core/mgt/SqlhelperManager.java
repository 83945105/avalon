package pub.avalonframework.sqlhelper.core.mgt;

import pub.avalonframework.sqlhelper.core.api.config.SqlhelperConfiguration;
import pub.avalonframework.sqlhelper.core.yaml.config.YamlSqlhelperConfiguration;
import pub.avalonframework.sqlhelper.core.yaml.swapper.impl.SqlhelperConfigurationYamlSwapper;

/**
 * @author baichao
 */
public final class SqlhelperManager {

    private SqlhelperManager() {
    }

    private static SqlhelperConfiguration DEFAULT_SQLHELPER_CONFIGURATION = new SqlhelperConfigurationYamlSwapper().swap(new YamlSqlhelperConfiguration());

    public static SqlhelperConfiguration getDefaultSqlhelperConfiguration() {
        return DEFAULT_SQLHELPER_CONFIGURATION;
    }

    public static void setDefaultSqlhelperConfiguration(SqlhelperConfiguration sqlhelperConfiguration) {
        DEFAULT_SQLHELPER_CONFIGURATION = sqlhelperConfiguration;
    }
}