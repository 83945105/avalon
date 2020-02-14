package pub.avalonframework.jdbc.core.engine;

import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.ResultSetExtractor;
import pub.avalonframework.sqlhelper.core.api.config.SqlhelperConfiguration;
import pub.avalonframework.sqlhelper.jdbc.core.api.config.SqlhelperJdbcConfiguration;
import pub.avalonframework.sqlhelper.jdbc.core.factory.JdbcFactory;
import pub.avalonframework.sqlhelper.readme.entity.SysUserHelper;

import java.util.List;
import java.util.Map;

/**
 * @author baichao
 */
public class JdbcSelectEngineTest {

    private JdbcFactory jdbcFactory;

    @Test
    void Test() {
        List<Map<String, String>> rows = jdbcFactory
                .select(SysUserHelper.column().id())
                .from(SysUserHelper.class)
                .select(table -> table.id().id())
                .where(SysUserHelper.where().id().equalTo(""))
                .groupBy(SysUserHelper.groupBy().id())
                .orderBy(SysUserHelper.orderBy().id().asc())
                .limit(1L)
                .offset(1L)
                .setConfiguration(new SqlhelperJdbcConfiguration())
                .fetch((ResultSetExtractor<List<Map<String, String>>>) rs -> null);
    }
}