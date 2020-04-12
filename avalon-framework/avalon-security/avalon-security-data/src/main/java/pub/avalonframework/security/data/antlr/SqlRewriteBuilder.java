package pub.avalonframework.security.data.antlr;

import pub.avalonframework.database.DatabaseType;
import pub.avalonframework.security.data.antlr.v4.mysql.MySqlRewriteVisitor;
import pub.avalonframework.security.data.rule.SqlRewrite;
import pub.avalonframework.security.data.sql.MysqlCacheJdbcOperations;

import javax.sql.DataSource;

/**
 * @author baichao
 */
public class SqlRewriteBuilder {

    private DatabaseType databaseType;

    private MysqlCacheJdbcOperations jdbcOperations;

    public SqlRewriteBuilder(DataSource dataSource) {
        jdbcOperations = new MysqlCacheJdbcOperations(dataSource);
        databaseType = jdbcOperations.selectDatabaseType();
    }

    public SqlRewrite build(String sql) {
        switch (databaseType) {
            case MYSQL:
                return new MySqlRewriteVisitor(sql, jdbcOperations);
            default:
                throw new RuntimeException("");
        }
    }
}