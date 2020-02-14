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

    public SqlhelperConfiguration setDatabaseType(DatabaseType databaseType) {
        this.databaseType = databaseType;
        return this;
    }

    public SqlPrintConfiguration getSqlPrint() {
        return sqlPrint;
    }

    public SqlhelperConfiguration setSqlPrint(SqlPrintConfiguration sqlPrint) {
        this.sqlPrint = sqlPrint;
        return this;
    }

    public SqlBuilderConfiguration getSqlBuilder() {
        return sqlBuilder;
    }

    public SqlhelperConfiguration setSqlBuilder(SqlBuilderConfiguration sqlBuilder) {
        this.sqlBuilder = sqlBuilder;
        return this;
    }
}