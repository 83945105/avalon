package pub.avalonframework.sqlhelper.core.yaml.swapper.impl;

import pub.avalonframework.core.yaml.swapper.YamlSwapper;
import pub.avalonframework.database.DatabaseType;
import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.api.config.SqlPrintConfiguration;
import pub.avalonframework.sqlhelper.core.api.config.SqlhelperConfiguration;
import pub.avalonframework.sqlhelper.core.yaml.config.YamlSqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.yaml.config.YamlSqlPrintConfiguration;
import pub.avalonframework.sqlhelper.core.yaml.config.YamlSqlhelperConfiguration;

/**
 * @author baichao
 */
public final class SqlhelperConfigurationYamlSwapper implements YamlSwapper<YamlSqlhelperConfiguration, SqlhelperConfiguration> {

    @Override
    public YamlSqlhelperConfiguration swap(SqlhelperConfiguration data) {
        data = data == null ? new SqlhelperConfiguration() : data;
        YamlSqlhelperConfiguration configuration = new YamlSqlhelperConfiguration();
        DatabaseType databaseType = data.getDatabaseType();
        configuration.setDatabaseType(databaseType);
        SqlPrintConfiguration sqlPrint = data.getSqlPrint();
        configuration.setSqlPrint(new SqlPrintConfigurationYamlSwapper().swap(sqlPrint));
        SqlBuilderConfiguration sqlBuilder = data.getSqlBuilder();
        configuration.setSqlBuilder(new SqlBuilderConfigurationYamlSwapper().swap(sqlBuilder));
        return configuration;
    }

    @Override
    public SqlhelperConfiguration swap(YamlSqlhelperConfiguration yamlConfiguration) {
        yamlConfiguration = yamlConfiguration == null ? new YamlSqlhelperConfiguration() : yamlConfiguration;
        SqlhelperConfiguration configuration = new SqlhelperConfiguration();
        DatabaseType databaseType = yamlConfiguration.getDatabaseType();
        configuration.setDatabaseType(databaseType);
        YamlSqlPrintConfiguration sqlPrint = yamlConfiguration.getSqlPrint();
        configuration.setSqlPrint(new SqlPrintConfigurationYamlSwapper().swap(sqlPrint));
        YamlSqlBuilderConfiguration sqlBuilder = yamlConfiguration.getSqlBuilder();
        configuration.setSqlBuilder(new SqlBuilderConfigurationYamlSwapper().swap(sqlBuilder));
        return configuration;
    }
}