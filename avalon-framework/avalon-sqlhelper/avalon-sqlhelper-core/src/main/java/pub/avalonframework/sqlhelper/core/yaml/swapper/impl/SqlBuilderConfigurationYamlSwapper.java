package pub.avalonframework.sqlhelper.core.yaml.swapper.impl;

import pub.avalonframework.core.yaml.swapper.YamlSwapper;
import pub.avalonframework.sqlhelper.core.api.config.DataBlockBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.sqlbuilder.template.DefaultMySqlBuilderTemplate;
import pub.avalonframework.sqlhelper.core.sqlbuilder.template.DefaultMySqlPartBuilderTemplate;
import pub.avalonframework.sqlhelper.core.sqlbuilder.template.SqlBuilderTemplate;
import pub.avalonframework.sqlhelper.core.sqlbuilder.template.SqlPartBuilderTemplate;
import pub.avalonframework.sqlhelper.core.yaml.config.YamlDataBlockBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.yaml.config.YamlSqlBuilderConfiguration;

/**
 * @author baichao
 */
public final class SqlBuilderConfigurationYamlSwapper implements YamlSwapper<YamlSqlBuilderConfiguration, SqlBuilderConfiguration> {

    private final static SqlPartBuilderTemplate DEFAULT_MYSQL_SQL_PART_BUILDER_TEMPLATE = new DefaultMySqlPartBuilderTemplate();

    private final static SqlBuilderTemplate DEFAULT_MYSQL_SQL_BUILDER_TEMPLATE = new DefaultMySqlBuilderTemplate();

    @Override
    public YamlSqlBuilderConfiguration swap(SqlBuilderConfiguration data) {
        data = data == null ? new SqlBuilderConfiguration() : data;
        YamlSqlBuilderConfiguration configuration = new YamlSqlBuilderConfiguration();
        DataBlockBuilderConfiguration dataBlockBuilder = data.getDataBlockBuilder();
        configuration.setDataBlockBuilder(new DataBlockBuilderConfigurationYamlSwapper().swap(dataBlockBuilder));
        SqlPartBuilderTemplate mysqlSqlPartBuilderTemplate = data.getMysqlSqlPartBuilderTemplate();
        configuration.setMysqlSqlPartBuilderTemplate(mysqlSqlPartBuilderTemplate == null ? DEFAULT_MYSQL_SQL_PART_BUILDER_TEMPLATE : mysqlSqlPartBuilderTemplate);
        SqlBuilderTemplate mysqlSqlBuilderTemplate = data.getMysqlSqlBuilderTemplate();
        configuration.setMysqlSqlBuilderTemplate(mysqlSqlBuilderTemplate == null ? DEFAULT_MYSQL_SQL_BUILDER_TEMPLATE : mysqlSqlBuilderTemplate);
        return configuration;
    }

    @Override
    public SqlBuilderConfiguration swap(YamlSqlBuilderConfiguration yamlConfiguration) {
        yamlConfiguration = yamlConfiguration == null ? new YamlSqlBuilderConfiguration() : yamlConfiguration;
        SqlBuilderConfiguration configuration = new SqlBuilderConfiguration();
        YamlDataBlockBuilderConfiguration dataBlockBuilder = yamlConfiguration.getDataBlockBuilder();
        configuration.setDataBlockBuilder(new DataBlockBuilderConfigurationYamlSwapper().swap(dataBlockBuilder));
        SqlPartBuilderTemplate mysqlSqlPartBuilderTemplate = yamlConfiguration.getMysqlSqlPartBuilderTemplate();
        configuration.setMysqlSqlPartBuilderTemplate(mysqlSqlPartBuilderTemplate == null ? DEFAULT_MYSQL_SQL_PART_BUILDER_TEMPLATE : mysqlSqlPartBuilderTemplate);
        SqlBuilderTemplate mysqlSqlBuilderTemplate = yamlConfiguration.getMysqlSqlBuilderTemplate();
        configuration.setMysqlSqlBuilderTemplate(mysqlSqlBuilderTemplate == null ? DEFAULT_MYSQL_SQL_BUILDER_TEMPLATE : mysqlSqlBuilderTemplate);
        return configuration;
    }
}