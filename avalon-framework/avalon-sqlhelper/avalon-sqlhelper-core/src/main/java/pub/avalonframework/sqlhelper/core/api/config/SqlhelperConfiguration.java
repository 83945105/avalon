package pub.avalonframework.sqlhelper.core.api.config;

import pub.avalonframework.database.DatabaseType;

/**
 * Sqlhelper configuration.
 *
 * @author baichao
 */
public class SqlhelperConfiguration {

    private DatabaseType databaseType;

    private PrintConfiguration print;

    private SqlBuilderConfiguration sqlBuilder;

    private JdbcConfiguration jdbc;

    public DatabaseType getDatabaseType() {
        return databaseType;
    }

    public SqlhelperConfiguration setDatabaseType(DatabaseType databaseType) {
        this.databaseType = databaseType;
        return this;
    }

    public PrintConfiguration getPrint() {
        return print;
    }

    public SqlhelperConfiguration setPrint(PrintConfiguration print) {
        this.print = print;
        return this;
    }

    public SqlBuilderConfiguration getSqlBuilder() {
        return sqlBuilder;
    }

    public SqlhelperConfiguration setSqlBuilder(SqlBuilderConfiguration sqlBuilder) {
        this.sqlBuilder = sqlBuilder;
        return this;
    }

    public JdbcConfiguration getJdbc() {
        return jdbc;
    }

    public void setJdbc(JdbcConfiguration jdbc) {
        this.jdbc = jdbc;
    }
}