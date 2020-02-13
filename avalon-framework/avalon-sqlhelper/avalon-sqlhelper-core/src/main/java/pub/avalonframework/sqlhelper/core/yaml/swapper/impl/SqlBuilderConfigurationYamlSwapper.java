package pub.avalonframework.sqlhelper.core.yaml.swapper.impl;

import pub.avalonframework.core.yaml.swapper.YamlSwapper;
import pub.avalonframework.sqlhelper.core.api.config.DataBlockBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.sqlbuilder.template.DataBlockBuilderTemplate;
import pub.avalonframework.sqlhelper.core.sqlbuilder.template.DefaultMySqlDataBlockBuilderTemplate;
import pub.avalonframework.sqlhelper.core.sqlbuilder.template.DefaultMySqlSqlBuilderTemplate;
import pub.avalonframework.sqlhelper.core.sqlbuilder.template.SqlBuilderTemplate;
import pub.avalonframework.sqlhelper.core.yaml.config.YamlDataBlockBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.yaml.config.YamlSqlBuilderConfiguration;

/**
 * @author baichao
 */
public final class SqlBuilderConfigurationYamlSwapper implements YamlSwapper<YamlSqlBuilderConfiguration, SqlBuilderConfiguration> {

    private final static DataBlockBuilderTemplate DEFAULT_MYSQL_SQL_PART_BUILDER_TEMPLATE = new DefaultMySqlDataBlockBuilderTemplate();

    private final static SqlBuilderTemplate DEFAULT_MYSQL_SQL_BUILDER_TEMPLATE = new DefaultMySqlSqlBuilderTemplate();

    @Override
    public YamlSqlBuilderConfiguration swap(SqlBuilderConfiguration data) {
        data = data == null ? new SqlBuilderConfiguration() : data;
        YamlSqlBuilderConfiguration configuration = new YamlSqlBuilderConfiguration();
        DataBlockBuilderConfiguration dataBlockBuilder = data.getDataBlockBuilder();
        configuration.setDataBlockBuilder(new DataBlockBuilderConfigurationYamlSwapper().swap(dataBlockBuilder));
        DataBlockBuilderTemplate mysqlSqlPartBuilderTemplate = data.getMysqlDataBlockBuilderTemplate();
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
        DataBlockBuilderTemplate mysqlSqlPartBuilderTemplate = yamlConfiguration.getMysqlSqlPartBuilderTemplate();
        configuration.setMysqlDataBlockBuilderTemplate(mysqlSqlPartBuilderTemplate == null ? DEFAULT_MYSQL_SQL_PART_BUILDER_TEMPLATE : mysqlSqlPartBuilderTemplate);
        SqlBuilderTemplate mysqlSqlBuilderTemplate = yamlConfiguration.getMysqlSqlBuilderTemplate();
        configuration.setMysqlSqlBuilderTemplate(mysqlSqlBuilderTemplate == null ? DEFAULT_MYSQL_SQL_BUILDER_TEMPLATE : mysqlSqlBuilderTemplate);
        return configuration;
    }
}