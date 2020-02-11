package pub.avalonframework.jdbc.core.engine;

import org.junit.jupiter.api.Test;
import pub.avalonframework.database.DatabaseType;
import pub.avalonframework.sqlhelper.jdbc.core.engine.JdbcSelectEngine;
import pub.avalonframework.sqlhelper.readme.entity.SysUserHelper;

/**
 * @author baichao
 */
public class JdbcSelectEngineTest {

    @Test
    void Test() {
        new JdbcSelectEngine<>(DatabaseType.MYSQL, SysUserHelper.class);
    }
}