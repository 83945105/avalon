package pub.avalonframework.sqlhelper.core.yaml.swapper.impl;

import pub.avalonframework.core.yaml.swapper.YamlSwapper;
import pub.avalonframework.sqlhelper.core.api.config.JdbcConfiguration;
import pub.avalonframework.sqlhelper.core.yaml.config.YamlJdbcConfiguration;

/**
 * @author baichao
 */
public final class JdbcConfigurationYamlSwapper implements YamlSwapper<YamlJdbcConfiguration, JdbcConfiguration> {

    private final static Boolean DEFAULT_ALLOW_RETURN_NULL = Boolean.TRUE;

    @Override
    public YamlJdbcConfiguration swap(JdbcConfiguration data) {
        data = data == null ? new JdbcConfiguration() : data;
        YamlJdbcConfiguration configuration = new YamlJdbcConfiguration();
        Boolean allowReturnNull = data.getAllowReturnNull();
        configuration.setAllowReturnNull(allowReturnNull == null ? DEFAULT_ALLOW_RETURN_NULL : allowReturnNull);
        Long maxCurrentPage = data.getMaxCurrentPage();
        configuration.setMaxCurrentPage(maxCurrentPage);
        Long maxPageSize = data.getMaxPageSize();
        configuration.setMaxPageSize(maxPageSize);
        return configuration;
    }

    @Override
    public JdbcConfiguration swap(YamlJdbcConfiguration yamlConfiguration) {
        yamlConfiguration = yamlConfiguration == null ? new YamlJdbcConfiguration() : yamlConfiguration;
        JdbcConfiguration configuration = new JdbcConfiguration();
        Boolean allowReturnNull = yamlConfiguration.getAllowReturnNull();
        configuration.setAllowReturnNull(allowReturnNull == null ? DEFAULT_ALLOW_RETURN_NULL : allowReturnNull);
        Long maxCurrentPage = yamlConfiguration.getMaxCurrentPage();
        configuration.setMaxCurrentPage(maxCurrentPage);
        Long maxPageSize = yamlConfiguration.getMaxPageSize();
        configuration.setMaxPageSize(maxPageSize);
        return configuration;
    }
}