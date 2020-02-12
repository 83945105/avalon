package pub.avalonframework.sqlhelper.core.api.config;

import pub.avalonframework.sqlhelper.core.sqlbuilder.template.SqlBuilderTemplate;
import pub.avalonframework.sqlhelper.core.sqlbuilder.template.SqlPartBuilderTemplate;

/**
 * @author baichao
 */
public class SqlBuilderConfiguration {

    private SqlPartDatumBuilderConfiguration sqlPartDatumBuilder;

    private SqlPartBuilderTemplate mysqlSqlPartBuilderTemplate;

    private SqlBuilderTemplate mysqlSqlBuilderTemplate;

    public SqlPartDatumBuilderConfiguration getSqlPartDatumBuilder() {
        return sqlPartDatumBuilder;
    }

    public void setSqlPartDatumBuilder(SqlPartDatumBuilderConfiguration sqlPartDatumBuilder) {
        this.sqlPartDatumBuilder = sqlPartDatumBuilder;
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