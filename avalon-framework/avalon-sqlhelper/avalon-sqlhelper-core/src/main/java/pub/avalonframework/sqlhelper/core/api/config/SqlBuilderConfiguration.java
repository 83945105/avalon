package pub.avalonframework.sqlhelper.core.api.config;

import pub.avalonframework.sqlhelper.core.sqlbuilder.template.DataBlockBuilderTemplate;
import pub.avalonframework.sqlhelper.core.sqlbuilder.template.SqlBuilderTemplate;

/**
 * @author baichao
 */
public class SqlBuilderConfiguration {

    private DataBlockBuilderConfiguration dataBlockBuilder;

    private DataBlockBuilderTemplate mysqlDataBlockBuilderTemplate;

    private SqlBuilderTemplate mysqlSqlBuilderTemplate;

    public DataBlockBuilderConfiguration getDataBlockBuilder() {
        return dataBlockBuilder;
    }

    public SqlBuilderConfiguration setDataBlockBuilder(DataBlockBuilderConfiguration dataBlockBuilder) {
        this.dataBlockBuilder = dataBlockBuilder;
        return this;
    }

    public DataBlockBuilderTemplate getMysqlDataBlockBuilderTemplate() {
        return mysqlDataBlockBuilderTemplate;
    }

    public SqlBuilderConfiguration setMysqlDataBlockBuilderTemplate(DataBlockBuilderTemplate mysqlDataBlockBuilderTemplate) {
        this.mysqlDataBlockBuilderTemplate = mysqlDataBlockBuilderTemplate;
        return this;
    }

    public SqlBuilderTemplate getMysqlSqlBuilderTemplate() {
        return mysqlSqlBuilderTemplate;
    }

    public SqlBuilderConfiguration setMysqlSqlBuilderTemplate(SqlBuilderTemplate mysqlSqlBuilderTemplate) {
        this.mysqlSqlBuilderTemplate = mysqlSqlBuilderTemplate;
        return this;
    }
}