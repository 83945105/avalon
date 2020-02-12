package pub.avalonframework.sqlhelper.core.yaml.swapper.impl;

import pub.avalonframework.core.yaml.swapper.YamlSwapper;
import pub.avalonframework.sqlhelper.core.api.config.SqlPrintConfiguration;
import pub.avalonframework.sqlhelper.core.yaml.config.YamlSqlPrintConfiguration;

/**
 * @author baichao
 */
public final class SqlPrintConfigurationYamlSwapper implements YamlSwapper<YamlSqlPrintConfiguration, SqlPrintConfiguration> {

    private final static Boolean DEFAULT_ENABLED = Boolean.FALSE;

    private final static Boolean DEFAULT_COLOUR_ENABLED = Boolean.FALSE;

    private final static Boolean DEFAULT_SQL_ENABLED = Boolean.TRUE;

    private final static Boolean DEFAULT_ARGS_ENABLED = Boolean.TRUE;

    @Override
    public YamlSqlPrintConfiguration swap(SqlPrintConfiguration data) {
        data = data == null ? new SqlPrintConfiguration() : data;
        YamlSqlPrintConfiguration configuration = new YamlSqlPrintConfiguration();
        Boolean enabled = data.getEnabled();
        configuration.setEnabled(enabled == null ? DEFAULT_ENABLED : enabled);
        Boolean colourEnabled = data.getColourEnabled();
        configuration.setColourEnabled(colourEnabled == null ? DEFAULT_COLOUR_ENABLED : colourEnabled);
        Boolean sqlEnabled = data.getSqlEnabled();
        configuration.setSqlEnabled(sqlEnabled == null ? DEFAULT_SQL_ENABLED : sqlEnabled);
        Boolean argsEnabled = data.getArgsEnabled();
        configuration.setArgsEnabled(argsEnabled == null ? DEFAULT_ARGS_ENABLED : argsEnabled);
        return configuration;
    }

    @Override
    public SqlPrintConfiguration swap(YamlSqlPrintConfiguration yamlConfiguration) {
        yamlConfiguration = yamlConfiguration == null ? new YamlSqlPrintConfiguration() : yamlConfiguration;
        SqlPrintConfiguration configuration = new SqlPrintConfiguration();
        Boolean enabled = yamlConfiguration.getEnabled();
        configuration.setEnabled(enabled == null ? DEFAULT_ENABLED : enabled);
        Boolean colourEnabled = yamlConfiguration.getColourEnabled();
        configuration.setColourEnabled(colourEnabled == null ? DEFAULT_COLOUR_ENABLED : colourEnabled);
        Boolean sqlEnabled = yamlConfiguration.getSqlEnabled();
        configuration.setSqlEnabled(sqlEnabled == null ? DEFAULT_SQL_ENABLED : sqlEnabled);
        Boolean argsEnabled = yamlConfiguration.getArgsEnabled();
        configuration.setArgsEnabled(argsEnabled == null ? DEFAULT_ARGS_ENABLED : argsEnabled);
        return configuration;
    }
}