package pub.avalonframework.sqlhelper.core.yaml.config;

import pub.avalonframework.core.yaml.config.YamlConfiguration;
import pub.avalonframework.sqlhelper.core.sqlbuilder.template.SqlBuilderTemplate;
import pub.avalonframework.sqlhelper.core.sqlbuilder.template.SqlPartBuilderTemplate;

/**
 * @author baichao
 */
public class YamlSqlBuilderConfiguration implements YamlConfiguration {

    private YamlDataBlockBuilderConfiguration dataBlockBuilder;

    private SqlPartBuilderTemplate mysqlSqlPartBuilderTemplate;

    private SqlBuilderTemplate mysqlSqlBuilderTemplate;

    public YamlDataBlockBuilderConfiguration getDataBlockBuilder() {
        return dataBlockBuilder;
    }

    public void setDataBlockBuilder(YamlDataBlockBuilderConfiguration dataBlockBuilder) {
        this.dataBlockBuilder = dataBlockBuilder;
    }

    public SqlPartBuilderTemplate getMysqlSqlPartBuilderTemplate() {
        return mysqlSqlPartBuilderTemplate;
    }

    public void setMysqlSqlPartBuilderTemplate(SqlPartBuilderTemplate mysqlSqlPartBuilderTemplate) {
        this.mysqlSqlPartBuilderTemplate = mysqlSqlPartBuilderTemplate;
    }

    public SqlBuilderTemplate getMysqlSqlBuilderTemplate() {
        return mysqlSqlBuilderTemplate;
    }

    public void setMysqlSqlBuilderTemplate(SqlBuilderTemplate mysqlSqlBuilderTemplate) {
        this.mysqlSqlBuilderTemplate = mysqlSqlBuilderTemplate;
    }
}