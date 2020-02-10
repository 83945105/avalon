package pub.avalonframework.sqlhelper.core.sql;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pub.avalonframework.database.DatabaseType;
import pub.avalonframework.sqlhelper.core.beans.JoinType;
import pub.avalonframework.sqlhelper.core.engine.SqlHelperEngine;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;
import pub.avalonframework.sqlhelper.readme.entity.SysUserHelper;
import pub.avalonframework.sqlhelper.readme.entity.UserRoleHelper;

/**
 * MySql动态引擎 - 列测试
 */
public class MySqlDynamicEngineJoinTest {

    /**
     * 测试join - 默认
     */
    @Test
    void Test_join_default() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL,SysUserHelper.class)
                .column(table -> table)
                .join(JoinType.INNER, UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.userId().equalTo(mainTable.id())))
                .query();
        Assertions.assertEquals("select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`user_id` = SysUser.`id`", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 测试join - 两次
     */
    @Test
    void Test_join_twice() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL,SysUserHelper.class)
                .column(table -> table)
                .join(JoinType.INNER, UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.userId().equalTo(mainTable.id())))
                .join(JoinType.INNER, UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .query();
        Assertions.assertEquals("select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`user_id` = SysUser.`id` and UserRole.`role_id` = SysUser.`id`", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 测试join - 指定表名 & 指定表别名
     */
    @Test
    void Test_join_assignTableName_assignTableAlias() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL,"sys_user_custom", SysUserHelper.class, "A")
                .column(table -> table)
                .join(JoinType.INNER, "user_role_custom", UserRoleHelper.class, "B", (on, joinTable, mainTable) -> on
                        .and(joinTable.userId().equalTo(mainTable.id())))
                .query();
        Assertions.assertEquals("select A.`id` `id`,A.`user_name` `userName`,A.`login_name` `loginName` from `sys_user_custom` A inner join `user_role_custom` B on B.`user_id` = A.`id`", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }
}
