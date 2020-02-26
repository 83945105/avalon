package pub.avalonframework.jdbc.core.engine;

import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import pub.avalonframework.sqlhelper.core.api.config.SqlhelperConfiguration;
import pub.avalonframework.sqlhelper.jdbc.core.factory.JdbcFactory;
import pub.avalonframework.sqlhelper.readme.entity.SysUser;
import pub.avalonframework.sqlhelper.readme.entity.SysUserHelper;

import java.util.List;
import java.util.Map;

/**
 * @author baichao
 */
public class JdbcSelectEngineTest {

    private JdbcFactory jdbcFactory;

    //    @Test
    void Test() {
        List<SysUser> rows = jdbcFactory
                .select(SysUserHelper.column().id(), SysUserHelper.column().userName())
                .from(SysUserHelper.class)
                .select(table -> table.id().id())
                .where(SysUserHelper.where().id().eq(""))
                .groupBy(SysUserHelper.groupBy().id())
                .orderBy(SysUserHelper.orderBy().id().asc())
                .limit(1L)
                .offset(1L)
                .setConfiguration(new SqlhelperConfiguration())
                .fetch(new RowMapperResultSetExtractor<>(new BeanPropertyRowMapper<>(SysUser.class)));

        List<SysUser> rows2 = jdbcFactory
                .select(SysUserHelper.column().id(), SysUserHelper.column().userName())
                .from(SysUserHelper.class)
                .select(table -> table.id().id())
                .where(SysUserHelper.where().id().eq(""))
                .groupBy(SysUserHelper.groupBy().id())
                .orderBy(SysUserHelper.orderBy().id().asc())
                .limit(1L)
                .offset(1L)
                .setConfiguration(new SqlhelperConfiguration())
                .fetch(new BeanPropertyRowMapper<>(SysUser.class));

        SysUser row = jdbcFactory
                .select(SysUserHelper.column().id(), SysUserHelper.column().userName())
                .from(SysUserHelper.class)
                .select(table -> table.id().id())
                .where(SysUserHelper.where().id().eq(""))
                .groupBy(SysUserHelper.groupBy().id())
                .orderBy(SysUserHelper.orderBy().id().asc())
                .limit(1L)
                .offset(1L)
                .setConfiguration(new SqlhelperConfiguration())
                .fetchOne(new BeanPropertyRowMapper<>(SysUser.class));
    }
}