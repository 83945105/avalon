package pub.avalonframework.sqlhelper.core.api.config;

import pub.avalonframework.sqlhelper.core.sqlbuilder.template.SqlBuilderTemplate;

/**
 * @author baichao
 */
public class SqlBuilderConfiguration {

    private SqlBuilderTemplate mysqlSqlBuilderTemplate;

    private DataBlockBuilderConfiguration dataBlockBuilder;

    public SqlBuilderTemplate getMysqlSqlBuilderTemplate() {
        return mysqlSqlBuilderTemplate;
    }

    public SqlBuilderConfiguration setMysqlSqlBuilderTemplate(SqlBuilderTemplate mysqlSqlBuilderTemplate) {
        this.mysqlSqlBuilderTemplate = mysqlSqlBuilderTemplate;
        return this;
    }

    public DataBlockBuilderConfiguration getDataBlockBuilder() {
        return dataBlockBuilder;
    }

    public SqlBuilderConfiguration setDataBlockBuilder(DataBlockBuilderConfiguration dataBlockBuilder) {
        this.dataBlockBuilder = dataBlockBuilder;
        return this;
    }
}