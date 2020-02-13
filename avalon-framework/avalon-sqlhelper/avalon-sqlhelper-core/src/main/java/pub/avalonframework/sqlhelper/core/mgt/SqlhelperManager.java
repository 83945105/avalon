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

    private static SqlhelperConfiguration DEFAULT_CONFIGURATION = new SqlhelperConfigurationYamlSwapper().swap(new YamlSqlhelperConfiguration());

    public static SqlhelperConfiguration getDefaultConfiguration() {
        return DEFAULT_CONFIGURATION;
    }

    public static void setDefaultConfiguration(SqlhelperConfiguration configuration) {
        DEFAULT_CONFIGURATION = configuration;
    }
}