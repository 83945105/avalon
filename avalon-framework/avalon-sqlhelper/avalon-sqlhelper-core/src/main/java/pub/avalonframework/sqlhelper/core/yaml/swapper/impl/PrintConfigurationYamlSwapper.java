package pub.avalonframework.sqlhelper.core.yaml.swapper.impl;

import pub.avalonframework.core.yaml.swapper.YamlSwapper;
import pub.avalonframework.sqlhelper.core.api.config.PrintConfiguration;
import pub.avalonframework.sqlhelper.core.yaml.config.YamlPrintConfiguration;

/**
 * @author baichao
 */
public final class PrintConfigurationYamlSwapper implements YamlSwapper<YamlPrintConfiguration, PrintConfiguration> {

    private final static Boolean DEFAULT_ENABLED = Boolean.FALSE;

    private final static Boolean DEFAULT_COLOUR_ENABLED = Boolean.FALSE;

    private final static Boolean DEFAULT_SQL_ENABLED = Boolean.TRUE;

    private final static Boolean DEFAULT_ARGS_ENABLED = Boolean.TRUE;

    @Override
    public YamlPrintConfiguration swap(PrintConfiguration data) {
        data = data == null ? new PrintConfiguration() : data;
        YamlPrintConfiguration configuration = new YamlPrintConfiguration();
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
    public PrintConfiguration swap(YamlPrintConfiguration yamlConfiguration) {
        yamlConfiguration = yamlConfiguration == null ? new YamlPrintConfiguration() : yamlConfiguration;
        PrintConfiguration configuration = new PrintConfiguration();
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