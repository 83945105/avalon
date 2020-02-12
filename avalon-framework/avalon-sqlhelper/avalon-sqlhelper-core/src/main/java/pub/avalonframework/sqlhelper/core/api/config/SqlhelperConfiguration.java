package pub.avalonframework.sqlhelper.core.api.config;

import pub.avalonframework.database.DatabaseType;

/**
 * Sqlhelper configuration.
 *
 * @author baichao
 */
public class SqlhelperConfiguration {

    private DatabaseType databaseType;

    private SqlPrintConfiguration sqlPrint;

    private SqlBuilderConfiguration sqlBuilder;

    public DatabaseType getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(DatabaseType databaseType) {
        this.databaseType = databaseType;
    }

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