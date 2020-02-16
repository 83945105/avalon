package pub.avalonframework.sqlhelper.core.yaml.swapper.impl;

import pub.avalonframework.core.yaml.swapper.YamlSwapper;
import pub.avalonframework.sqlhelper.core.api.config.DataBlockBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.sqlbuilder.template.DefaultMySqlSqlBuilderTemplate;
import pub.avalonframework.sqlhelper.core.sqlbuilder.template.SqlBuilderTemplate;
import pub.avalonframework.sqlhelper.core.yaml.config.YamlDataBlockBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.yaml.config.YamlSqlBuilderConfiguration;

/**
 * @author baichao
 */
public final class SqlBuilderConfigurationYamlSwapper implements YamlSwapper<YamlSqlBuilderConfiguration, SqlBuilderConfiguration> {

    private final static String DEFAULT_MYSQL_SQL_BUILDER_TEMPLATE_NAME = DefaultMySqlSqlBuilderTemplate.class.getName();

    private final static SqlBuilderTemplate DEFAULT_MYSQL_SQL_BUILDER_TEMPLATE = new DefaultMySqlSqlBuilderTemplate();

    @Override
    public YamlSqlBuilderConfiguration swap(SqlBuilderConfiguration data) {
        data = data == null ? new SqlBuilderConfiguration() : data;
        YamlSqlBuilderConfiguration configuration = new YamlSqlBuilderConfiguration();
        SqlBuilderTemplate mysqlSqlBuilderTemplate = data.getMysqlSqlBuilderTemplate();
        configuration.setMysqlSqlBuilderTemplateName(mysqlSqlBuilderTemplate == null ? DEFAULT_MYSQL_SQL_BUILDER_TEMPLATE_NAME : mysqlSqlBuilderTemplate.getClass().getName());
        DataBlockBuilderConfiguration dataBlockBuilder = data.getDataBlockBuilder();
        configuration.setDataBlockBuilder(new DataBlockBuilderConfigurationYamlSwapper().swap(dataBlockBuilder));
        return configuration;
    }

    @Override
    public SqlBuilderConfiguration swap(YamlSqlBuilderConfiguration yamlConfiguration) {
        yamlConfiguration = yamlConfiguration == null ? new YamlSqlBuilderConfiguration() : yamlConfiguration;
        SqlBuilderConfiguration configuration = new SqlBuilderConfiguration();
        String mysqlSqlBuilderTemplateName = yamlConfiguration.getMysqlSqlBuilderTemplateName();
        configuration.setMysqlSqlBuilderTemplate(mysqlSqlBuilderTemplateName == null ? DEFAULT_MYSQL_SQL_BUILDER_TEMPLATE : createSqlBuilderTemplate(mysqlSqlBuilderTemplateName));
        YamlDataBlockBuilderConfiguration dataBlockBuilder = yamlConfiguration.getDataBlockBuilder();
        configuration.setDataBlockBuilder(new DataBlockBuilderConfigurationYamlSwapper().swap(dataBlockBuilder));
        return configuration;
    }

    private SqlBuilderTemplate createSqlBuilderTemplate(String sqlBuilderTemplateName) {
        try {
            return (SqlBuilderTemplate) Class.forName(sqlBuilderTemplateName).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}