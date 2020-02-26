package pub.avalonframework.sqlhelper.core.sql;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pub.avalonframework.database.DatabaseType;
import pub.avalonframework.sqlhelper.core.beans.ComparisonRule;
import pub.avalonframework.sqlhelper.core.beans.JoinType;
import pub.avalonframework.sqlhelper.core.engine.SqlHelperEngine;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;
import pub.avalonframework.sqlhelper.readme.entity.RoleResourceHelper;
import pub.avalonframework.sqlhelper.readme.entity.SysUserHelper;
import pub.avalonframework.sqlhelper.readme.entity.UserRoleHelper;

import java.util.Arrays;

/**
 * MySql动态引擎 - 删除
 */
public class MySqlDeleteTest {

    /**
     * 主键删除
     */
    @Test
    void Test_deleteByPrimaryKey() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .deleteByPrimaryKey("1");
        Assertions.assertEquals("delete from `sys_user` where `id` = ?", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{"1"}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 主键删除 - 指定表名
     */
    @Test
    void Test_deleteByPrimaryKey_assignTableName() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, "sys_user_custom", SysUserHelper.class)
                .deleteByPrimaryKey("1");
        Assertions.assertEquals("delete from `sys_user_custom` where `id` = ?", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{"1"}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 主键批量删除
     */
    @Test
    void Test_batchDeleteByPrimaryKeys() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .batchDeleteByPrimaryKeys("1", "2");
        Assertions.assertEquals("delete from `sys_user` where `id` in (?,?)", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{"1", "2"}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 主键批量删除 - 指定表名
     */
    @Test
    void Test_batchDeleteByPrimaryKeys_assignTableName() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, "sys_user_custom", SysUserHelper.class)
                .batchDeleteByPrimaryKeys("1", "2");
        Assertions.assertEquals("delete from `sys_user_custom` where `id` in (?,?)", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{"1", "2"}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 条件删除
     */
    @Test
    void Test_delete() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .delete();
        Assertions.assertEquals("delete SysUser from `sys_user` SysUser", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 条件删除 - 指定表名
     */
    @Test
    void Test_delete_assignTableName() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, "sys_user_custom", SysUserHelper.class)
                .delete();
        Assertions.assertEquals("delete SysUser from `sys_user_custom` SysUser", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 条件删除 - 所有Where条件
     */
    @Test
    void Test_delete_allWhere() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable

                                .id().isNull()

                                .id().isNotNull()

                                .id().eq("A-1")
                                .id().eq("A-2", ComparisonRule.NULL_SKIP)

                                .id().neq("B-1")
                                .id().neq("B-2", ComparisonRule.NULL_SKIP)

                                .id().gt("C-1")
                                .id().gt("C-2", ComparisonRule.NULL_SKIP)

                                .id().gte("D-1")
                                .id().gte("D-2", ComparisonRule.NULL_SKIP)

                                .id().lt("E-1")
                                .id().lt("E-2", ComparisonRule.NULL_SKIP)

                                .id().lte("F-1")
                                .id().lte("F-2", ComparisonRule.NULL_SKIP)

                                .id().bt("G-1", "G-2")
                                .id().bt("G-3", "G-4", ComparisonRule.NULL_SKIP)

                                .id().lk("H-1")
                                .id().lk("H-2", ComparisonRule.NULL_SKIP)

                                .id().in(new Object[]{"I-1", "I-2"}, ComparisonRule.NULL_SKIP)

                                .id().in(ComparisonRule.NULL_SKIP)
                                .id().in(ComparisonRule.NULL_SKIP, "J-1")
                                .id().in(ComparisonRule.NULL_SKIP, new Object[]{"J-2", "J-3"})
                                .id().in("J-4")
                                .id().in(new Object[]{"J-5", "J-6"})
                                .id().in("J-7", "J-8")

                                .id().in(Arrays.asList("K-1", "K-2"))
                                .id().in(Arrays.asList("K-3", "K-4"), ComparisonRule.NULL_SKIP)

                                .id().nin(new Object[]{"L-1", "L-2"}, ComparisonRule.NULL_SKIP)

                                .id().nin(ComparisonRule.NULL_SKIP)
                                .id().nin(ComparisonRule.NULL_SKIP, "M-1")
                                .id().nin(ComparisonRule.NULL_SKIP, new Object[]{"M-2", "M-3"})
                                .id().nin("M-4")
                                .id().nin(new Object[]{"M-5", "M-6"})
                                .id().nin("M-7", "M-8")

                                .id().nin(Arrays.asList("N-1", "N-2"))
                                .id().nin(Arrays.asList("N-3", "N-4"), ComparisonRule.NULL_SKIP)
                        ))
                .delete();
        Assertions.assertEquals("delete SysUser from `sys_user` SysUser where SysUser.`id` is null and SysUser.`id` is not null and SysUser.`id` = ? and SysUser.`id` = ? and SysUser.`id` != ? and SysUser.`id` != ? and SysUser.`id` > ? and SysUser.`id` > ? and SysUser.`id` >= ? and SysUser.`id` >= ? and SysUser.`id` < ? and SysUser.`id` < ? and SysUser.`id` <= ? and SysUser.`id` <= ? and SysUser.`id` between ? and ? and SysUser.`id` between ? and ? and SysUser.`id` like ? and SysUser.`id` like ? and SysUser.`id` in (?,?) and SysUser.`id` in (?) and SysUser.`id` in (?,?) and SysUser.`id` in (?) and SysUser.`id` in (?,?) and SysUser.`id` in (?,?) and SysUser.`id` in (?,?) and SysUser.`id` in (?,?) and SysUser.`id` not in (?,?) and SysUser.`id` not in (?) and SysUser.`id` not in (?,?) and SysUser.`id` not in (?) and SysUser.`id` not in (?,?) and SysUser.`id` not in (?,?) and SysUser.`id` not in (?,?) and SysUser.`id` not in (?,?)",
                sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{
                "A-1", "A-2",
                "B-1", "B-2",
                "C-1", "C-2",
                "D-1", "D-2",
                "E-1", "E-2",
                "F-1", "F-2",
                "G-1", "G-2", "G-3", "G-4",
                "H-1", "H-2",
                "I-1", "I-2",
                "J-1", "J-2", "J-3", "J-4", "J-5", "J-6", "J-7", "J-8",
                "K-1", "K-2", "K-3", "K-4",
                "L-1", "L-2",
                "M-1", "M-2", "M-3", "M-4", "M-5", "M-6", "M-7", "M-8",
                "N-1", "N-2", "N-3", "N-4"
        }, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 条件删除 - 组合Where条件
     */
    @Test
    void Test_delete_combinationWhere() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable
                                .id().gt("A-1").id().gt("A-2")
                                .id().gte("B-1").id().gte("B-2"))
                        .and(mainTable
                                .id().lt("C-1").id().lt("C-2")
                                .id().lte("D-1").id().lte("D-2"))
                        .or(mainTable
                                .id().eq("E-1"))

                        .or(mainTable
                                .id().eq("F-1").id().eq("F-2"))
                        .or(cd -> cd
                                .and(mainTable.id().eq("G-1").id().eq("G-2"))
                                .and(mainTable.id().eq("H-1")))
                        .and(cd -> cd
                                .and(mainTable.id().eq("I-1"))
                                .or(mainTable.id().eq("J-1"))))
                .where((condition, mainTable) -> condition
                        .and(mainTable.id().eq("K-1")))
                .delete();
        Assertions.assertEquals("delete SysUser from `sys_user` SysUser where (SysUser.`id` > ? and SysUser.`id` > ? and SysUser.`id` >= ? and SysUser.`id` >= ? and SysUser.`id` < ? and SysUser.`id` < ? and SysUser.`id` <= ? and SysUser.`id` <= ? or SysUser.`id` = ? or (SysUser.`id` = ? and SysUser.`id` = ?) or (SysUser.`id` = ? and SysUser.`id` = ? and SysUser.`id` = ?) and (SysUser.`id` = ? or SysUser.`id` = ?)) and SysUser.`id` = ?",
                sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{
                "A-1", "A-2",
                "B-1", "B-2",
                "C-1", "C-2",
                "D-1", "D-2",
                "E-1",
                "F-1", "F-2",
                "G-1", "G-2",
                "H-1",
                "I-1",
                "J-1",
                "K-1"
        }, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 条件删除 - 指定内连接
     */
    @Test
    void Test_delete_assignInnerJoin() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .join(JoinType.INNER, UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.userId().eq(mainTable.id())))
                .delete();
        Assertions.assertEquals("delete SysUser from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`user_id` = SysUser.`id`",
                sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 条件删除 - 指定左连接
     */
    @Test
    void Test_delete_assignLeftJoin() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .join(JoinType.LEFT, UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.userId().eq(mainTable.id())))
                .delete();
        Assertions.assertEquals("delete SysUser from `sys_user` SysUser left join `user_role` UserRole on UserRole.`user_id` = SysUser.`id`",
                sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 条件删除 - 指定右连接
     */
    @Test
    void Test_delete_assignRightJoin() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .join(JoinType.RIGHT, UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.userId().eq(mainTable.id())))
                .delete();
        Assertions.assertEquals("delete SysUser from `sys_user` SysUser right join `user_role` UserRole on UserRole.`user_id` = SysUser.`id`",
                sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 条件删除 - 所有Where条件关联表
     */
    @Test
    void Test_delete_allWhereJoin() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .join(JoinType.INNER, UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.userId().eq(mainTable.id())))
                .where(UserRoleHelper.class, (condition, joinTable, mainTable) -> condition
                        .and(joinTable
                                .userId().isNull()
                                .userId().isNotNull()
                                .userId().eq(mainTable.id())
                                .userId().neq(mainTable.id())
                                .userId().gt(mainTable.id())
                                .userId().gte(mainTable.id())
                                .userId().lt(mainTable.id())
                                .userId().lte(mainTable.id())
                                .userId().bt(mainTable.id(), mainTable.id())
                                .userId().lk(mainTable.id())
                                .userId().in(mainTable.id())
                                .userId().nin(mainTable.id())
                        ))
                .delete();
        Assertions.assertEquals("delete SysUser from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`user_id` = SysUser.`id` where UserRole.`user_id` is null and UserRole.`user_id` is not null and UserRole.`user_id` = SysUser.`id` and UserRole.`user_id` != SysUser.`id` and UserRole.`user_id` > SysUser.`id` and UserRole.`user_id` >= SysUser.`id` and UserRole.`user_id` < SysUser.`id` and UserRole.`user_id` <= SysUser.`id` and UserRole.`user_id` between SysUser.`id` and SysUser.`id` and UserRole.`user_id` like SysUser.`id` and UserRole.`user_id` in (SysUser.`id`) and UserRole.`user_id` not in (SysUser.`id`)",
                sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 条件删除 - 所有Where条件列
     */
    @Test
    void Test_delete_allWhereColumn() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .join(JoinType.INNER, UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.userId().eq(mainTable.id())))
                .join(JoinType.INNER, RoleResourceHelper.class, "RR", (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().eq(UserRoleHelper.class, UserRoleHelper.Column::id)))
                .where((condition, mainTable) -> condition
                        .and(mainTable
                                .id().eq(UserRoleHelper.class, table -> table.roleId().roleName())
                                .id().neq(UserRoleHelper.class, table -> table.roleId().roleName())
                                .id().gt(UserRoleHelper.class, table -> table.roleId().roleName())
                                .id().gte(UserRoleHelper.class, table -> table.roleId().roleName())
                                .id().lt(UserRoleHelper.class, table -> table.roleId().roleName())
                                .id().lte(UserRoleHelper.class, table -> table.roleId().roleName())
                                .id().bt(UserRoleHelper.class, table -> table.roleId().roleName(), table -> table.roleId().roleId())
                                .id().lk(UserRoleHelper.class, table -> table.roleId().roleName())
                                .id().in(UserRoleHelper.class, table -> table.roleId().roleName())
                                .id().nin(UserRoleHelper.class, table -> table.roleId().roleName())

                                .id().eq(RoleResourceHelper.class, "RR", table -> table.resourceId().resourceName())
                                .id().neq(RoleResourceHelper.class, "RR", table -> table.resourceId().resourceName())
                                .id().gt(RoleResourceHelper.class, "RR", table -> table.resourceId().resourceName())
                                .id().gte(RoleResourceHelper.class, "RR", table -> table.resourceId().resourceName())
                                .id().lt(RoleResourceHelper.class, "RR", table -> table.resourceId().resourceName())
                                .id().lte(RoleResourceHelper.class, "RR", table -> table.resourceId().resourceName())
                                .id().bt(RoleResourceHelper.class, "RR", table -> table.resourceId().resourceName(), table -> table.resourceName().resourceName())
                                .id().lk(RoleResourceHelper.class, "RR", table -> table.resourceId().resourceName())
                                .id().in(RoleResourceHelper.class, "RR", table -> table.resourceId().resourceName())
                                .id().nin(RoleResourceHelper.class, "RR", table -> table.resourceId().resourceName())
                        ))
                .delete();
        Assertions.assertEquals("delete SysUser from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`user_id` = SysUser.`id` inner join `role_resource` RR on RR.`role_id` = UserRole.`id` where SysUser.`id` = UserRole.`role_id` and SysUser.`id` = UserRole.`role_name` and SysUser.`id` != UserRole.`role_id` and SysUser.`id` != UserRole.`role_name` and SysUser.`id` > UserRole.`role_id` and SysUser.`id` > UserRole.`role_name` and SysUser.`id` >= UserRole.`role_id` and SysUser.`id` >= UserRole.`role_name` and SysUser.`id` < UserRole.`role_id` and SysUser.`id` < UserRole.`role_name` and SysUser.`id` <= UserRole.`role_id` and SysUser.`id` <= UserRole.`role_name` and SysUser.`id` between UserRole.`role_id` and UserRole.`role_id` and SysUser.`id` between UserRole.`role_name` and UserRole.`role_id` and SysUser.`id` like UserRole.`role_id` and SysUser.`id` like UserRole.`role_name` and SysUser.`id` in (UserRole.`role_id`,UserRole.`role_name`) and SysUser.`id` not in (UserRole.`role_id`,UserRole.`role_name`) and SysUser.`id` = RR.`resource_id` and SysUser.`id` = RR.`resource_name` and SysUser.`id` != RR.`resource_id` and SysUser.`id` != RR.`resource_name` and SysUser.`id` > RR.`resource_id` and SysUser.`id` > RR.`resource_name` and SysUser.`id` >= RR.`resource_id` and SysUser.`id` >= RR.`resource_name` and SysUser.`id` < RR.`resource_id` and SysUser.`id` < RR.`resource_name` and SysUser.`id` <= RR.`resource_id` and SysUser.`id` <= RR.`resource_name` and SysUser.`id` between RR.`resource_id` and RR.`resource_name` and SysUser.`id` between RR.`resource_name` and RR.`resource_name` and SysUser.`id` like RR.`resource_id` and SysUser.`id` like RR.`resource_name` and SysUser.`id` in (RR.`resource_id`,RR.`resource_name`) and SysUser.`id` not in (RR.`resource_id`,RR.`resource_name`)",
                sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 条件删除 - 所有Where条件子查询
     */
//    @Test
/*    void Test_delete_allWhereSubQuery() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL,SysUserHelper.class)
                .join(JoinType.INNER, UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.userId().equalTo(mainTable.id())))
                .join(JoinType.INNER, RoleResourceHelper.class, "RR", (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(UserRoleHelper.class, UserRoleHelper.Column::id)))
                .where((condition, mainTable) -> condition
                        .and(mainTable
                                .id().equalToSubQuery(UserRoleHelper.class, query -> query)
                        ))
                .delete();
        Assertions.assertEquals("",
                sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }*/

}
