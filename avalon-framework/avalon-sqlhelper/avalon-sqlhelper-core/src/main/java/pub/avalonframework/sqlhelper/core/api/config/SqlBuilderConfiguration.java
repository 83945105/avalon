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

    public void setDataBlockBuilder(DataBlockBuilderConfiguration dataBlockBuilder) {
        this.dataBlockBuilder = dataBlockBuilder;
    }

    public DataBlockBuilderTemplate getMysqlDataBlockBuilderTemplate() {
        return mysqlDataBlockBuilderTemplate;
    }

    public void setMysqlDataBlockBuilderTemplate(DataBlockBuilderTemplate mysqlDataBlockBuilderTemplate) {
        this.mysqlDataBlockBuilderTemplate = mysqlDataBlockBuilderTemplate;
    }

    public SqlBuilderTemplate getMysqlSqlBuilderTemplate() {
        return mysqlSqlBuilderTemplate;
    }

    public void setMysqlSqlBuilderTemplate(SqlBuilderTemplate mysqlSqlBuilderTemplate) {
        this.mysqlSqlBuilderTemplate = mysqlSqlBuilderTemplate;
    }
}