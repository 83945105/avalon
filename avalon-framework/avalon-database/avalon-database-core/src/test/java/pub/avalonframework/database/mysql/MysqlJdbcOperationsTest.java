package pub.avalonframework.database.mysql;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pub.avalonframework.database.table.column.Column;

import java.util.List;

/**
 * @author baichao
 */
@Disabled
public class MysqlJdbcOperationsTest {

    MysqlJdbcOperations operations = new MysqlJdbcOperations(new HikariDataSource() {{
        setDriverClassName("com.mysql.cj.jdbc.Driver");
        setJdbcUrl("jdbc:mysql://localhost:3306/sql_rewrite?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false");
        setUsername("root");
        setPassword("19910405");
    }});

    @Test
    void TestSelectDatabaseName() {
        Assertions.assertEquals("sql_rewrite", operations.selectDatabaseName());
    }

    @Test
    void TestSelectAllTableNames() {
        List<String> tableNames = operations.selectTableNames();
        Assertions.assertNotNull(tableNames);
        Assertions.assertEquals(2, tableNames.size());
    }

    @Test
    void TestSelectTableColumns() {
        List<Column> columns = operations.selectTableColumns("sql_rewrite", "user");
    }
}