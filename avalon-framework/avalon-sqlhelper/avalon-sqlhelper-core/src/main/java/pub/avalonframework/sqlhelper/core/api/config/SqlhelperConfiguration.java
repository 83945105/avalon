package pub.avalonframework.sqlhelper.core.api.config;

/**
 * Sqlhelper configuration.
 *
 * @author baichao
 */
public class SqlhelperConfiguration {

    private SqlPrintConfiguration sqlPrint;

    private SqlBuilderConfiguration sqlBuilder;

    public SqlPrintConfiguration getSqlPrint() {
        return sqlPrint;
    }

    public void setSqlPrint(SqlPrintConfiguration sqlPrint) {
        this.sqlPrint = sqlPrint;
    }

    public SqlBuilderConfiguration getSqlBuilder() {
        return sqlBuilder;
    }

    public void setSqlBuilder(SqlBuilderConfiguration sqlBuilder) {
        this.sqlBuilder = sqlBuilder;
    }
}