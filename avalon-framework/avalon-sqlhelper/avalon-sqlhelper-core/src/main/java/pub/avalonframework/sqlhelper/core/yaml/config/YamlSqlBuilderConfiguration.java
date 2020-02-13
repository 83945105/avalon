package pub.avalonframework.sqlhelper.core.yaml.config;

import pub.avalonframework.core.yaml.config.YamlConfiguration;
import pub.avalonframework.sqlhelper.core.sqlbuilder.template.DataBlockBuilderTemplate;
import pub.avalonframework.sqlhelper.core.sqlbuilder.template.SqlBuilderTemplate;

/**
 * @author baichao
 */
public class YamlSqlBuilderConfiguration implements YamlConfiguration {

    private YamlDataBlockBuilderConfiguration dataBlockBuilder;

    private DataBlockBuilderTemplate mysqlSqlPartBuilderTemplate;

    private SqlBuilderTemplate mysqlSqlBuilderTemplate;

    public YamlDataBlockBuilderConfiguration getDataBlockBuilder() {
        return dataBlockBuilder;
    }

    public void setDataBlockBuilder(YamlDataBlockBuilderConfiguration dataBlockBuilder) {
        this.dataBlockBuilder = dataBlockBuilder;
    }

    public DataBlockBuilderTemplate getMysqlSqlPartBuilderTemplate() {
        return mysqlSqlPartBuilderTemplate;
    }

    public void setMysqlSqlPartBuilderTemplate(DataBlockBuilderTemplate mysqlSqlPartBuilderTemplate) {
        this.mysqlSqlPartBuilderTemplate = mysqlSqlPartBuilderTemplate;
    }

    public SqlBuilderTemplate getMysqlSqlBuilderTemplate() {
        return mysqlSqlBuilderTemplate;
    }

    public void setMysqlSqlBuilderTemplate(SqlBuilderTemplate mysqlSqlBuilderTemplate) {
        this.mysqlSqlBuilderTemplate = mysqlSqlBuilderTemplate;
    }
}