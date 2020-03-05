package pub.avalonframework.sqlhelper.core.sql;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pub.avalonframework.database.DatabaseType;
import pub.avalonframework.sqlhelper.AbstractTest;
import pub.avalonframework.sqlhelper.core.data.block.GroupType;
import pub.avalonframework.sqlhelper.core.data.block.JoinType;
import pub.avalonframework.sqlhelper.core.engine.SqlHelperEngine;
import pub.avalonframework.sqlhelper.core.expression.ComparisonRule;
import pub.avalonframework.sqlhelper.core.sqlbuilder.SqlBuilder;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;
import pub.avalonframework.sqlhelper.readme.entity.RoleResourceHelper;
import pub.avalonframework.sqlhelper.readme.entity.SysUserHelper;
import pub.avalonframework.sqlhelper.readme.entity.UserRoleHelper;

import java.util.Arrays;

/**
 * MySql动态引擎 - 查询
 */
public class MySqlQueryTest extends AbstractTest {

    /**
     * 查询总数
     */
    @Test
    void Test_count() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .queryCount();
        Assertions.assertEquals("select count(1) from `sys_user` SysUser", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 查询总数 - 指定表名
     */
    @Test
    void Test_count_assignTableName() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, "sys_user_custom", SysUserHelper.class)
                .queryCount();
        Assertions.assertEquals("select count(1) from `sys_user_custom` SysUser", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 查询总数 - 指定表别名
     */
    @Test
    void Test_count_assignTableAlias() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class, "A")
                .queryCount();
        Assertions.assertEquals("select count(1) from `sys_user` A", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 查询总数 - 所有where条件
     */
    @Test
    void Test_count_allWhere() {
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
                .queryCount();
        Assertions.assertEquals("select count(1) from `sys_user` SysUser where SysUser.`id` is null and SysUser.`id` is not null and SysUser.`id` = ? and SysUser.`id` = ? and SysUser.`id` != ? and SysUser.`id` != ? and SysUser.`id` > ? and SysUser.`id` > ? and SysUser.`id` >= ? and SysUser.`id` >= ? and SysUser.`id` < ? and SysUser.`id` < ? and SysUser.`id` <= ? and SysUser.`id` <= ? and SysUser.`id` between ? and ? and SysUser.`id` between ? and ? and SysUser.`id` like ? and SysUser.`id` like ? and SysUser.`id` in (?,?) and SysUser.`id` in (?) and SysUser.`id` in (?,?) and SysUser.`id` in (?) and SysUser.`id` in (?,?) and SysUser.`id` in (?,?) and SysUser.`id` in (?,?) and SysUser.`id` in (?,?) and SysUser.`id` not in (?,?) and SysUser.`id` not in (?) and SysUser.`id` not in (?,?) and SysUser.`id` not in (?) and SysUser.`id` not in (?,?) and SysUser.`id` not in (?,?) and SysUser.`id` not in (?,?) and SysUser.`id` not in (?,?)", sqlBuilderResult.getPreparedStatementSql());
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
     * 查询总数 - 指定关联表
     */
    @Test
    void Test_count_assignJoin() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .join(JoinType.INNER, UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().eq(mainTable.id())))
                .queryCount();
        Assertions.assertEquals("select count(1) from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id`", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 查询总数 - 指定表名 & 指定关联表
     */
    @Test
    void Test_count_assignTableName_assignJoin() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, "sys_user_custom", SysUserHelper.class)
                .join(JoinType.INNER, "user_role_custom", UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().eq(mainTable.id())))
                .queryCount();
        Assertions.assertEquals("select count(1) from `sys_user_custom` SysUser inner join `user_role_custom` UserRole on UserRole.`role_id` = SysUser.`id`", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 查询总数 - 指定表名 & 指定表别名 & 指定关联表
     */
    @Test
    void Test_count_assignTableName_assignTableAlias_assignJoin() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, "sys_user_custom", SysUserHelper.class, "A")
                .join(JoinType.INNER, "user_role_custom", UserRoleHelper.class, "B", (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().eq(mainTable.id())))
                .queryCount();
        Assertions.assertEquals("select count(1) from `sys_user_custom` A inner join `user_role_custom` B on B.`role_id` = A.`id`", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 查询总数 - 全部on条件
     */
    @Test
    void Test_count_join_allOn() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .join(JoinType.INNER, UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable
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
                .queryCount();
        Assertions.assertEquals("select count(1) from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`id` is null and UserRole.`id` is not null and UserRole.`id` = ? and UserRole.`id` = ? and UserRole.`id` != ? and UserRole.`id` != ? and UserRole.`id` > ? and UserRole.`id` > ? and UserRole.`id` >= ? and UserRole.`id` >= ? and UserRole.`id` < ? and UserRole.`id` < ? and UserRole.`id` <= ? and UserRole.`id` <= ? and UserRole.`id` between ? and ? and UserRole.`id` between ? and ? and UserRole.`id` like ? and UserRole.`id` like ? and UserRole.`id` in (?,?) and UserRole.`id` in (?) and UserRole.`id` in (?,?) and UserRole.`id` in (?) and UserRole.`id` in (?,?) and UserRole.`id` in (?,?) and UserRole.`id` in (?,?) and UserRole.`id` in (?,?) and UserRole.`id` not in (?,?) and UserRole.`id` not in (?) and UserRole.`id` not in (?,?) and UserRole.`id` not in (?) and UserRole.`id` not in (?,?) and UserRole.`id` not in (?,?) and UserRole.`id` not in (?,?) and UserRole.`id` not in (?,?)", sqlBuilderResult.getPreparedStatementSql());
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
     * 查询总数 - 指定内连接关联表
     */
    @Test
    void Test_count_assignInnerJoin() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().eq(mainTable.id())))
                .queryCount();
        Assertions.assertEquals("select count(1) from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id`", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 查询总数 - 指定左连接关联表
     */
    @Test
    void Test_count_assignLeftJoin() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .leftJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().eq(mainTable.id())))
                .queryCount();
        Assertions.assertEquals("select count(1) from `sys_user` SysUser left join `user_role` UserRole on UserRole.`role_id` = SysUser.`id`", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 查询总数 - 指定右连接关联表
     */
    @Test
    void Test_count_assignRightJoin() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .rightJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().eq(mainTable.id())))
                .queryCount();
        Assertions.assertEquals("select count(1) from `sys_user` SysUser right join `user_role` UserRole on UserRole.`role_id` = SysUser.`id`", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    @Test
    void Test_queryByPrimaryKey() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .queryByPrimaryKey("1");
        Assertions.assertEquals("select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where SysUser.`id` = ?", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{"1"}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    @Test
    void TestColumn01() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .select(table -> table)
                .queryByPrimaryKey(arg());
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where SysUser.`id` = ?");
    }

    @Test
    void TestColumn02() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .select(SysUserHelper.Column::id)
                .queryByPrimaryKey(arg());
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id` from `sys_user` SysUser where SysUser.`id` = ?");
    }

    @Test
    void TestColumn03() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .select(table -> table.loginName().userName("AA"))
                .queryByPrimaryKey(arg());
        setSqlBuilder(sqlBuilderResult, "select SysUser.`login_name` `loginName`,SysUser.`user_name` `AA` from `sys_user` SysUser where SysUser.`id` = ?");
    }

    @Test
    void TestVirtualColumn01() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .virtualColumn("1", "AA")
                .virtualColumn(1, "BB")
                .virtualColumn(1L, "CC")
                .virtualColumn(null, "DD")
                .queryByPrimaryKey(arg());
        setSqlBuilder(sqlBuilderResult, "select '1' `AA`,1 `BB`,1 `CC`,null `DD` from `sys_user` SysUser where SysUser.`id` = ?");
    }

    @Test
    void TestColumnAndVirtualColumn01() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .virtualColumn(1, "AA")
                .select(table -> table)
                .queryByPrimaryKey(arg());
        setSqlBuilder(sqlBuilderResult, "select 1 `AA`,SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where SysUser.`id` = ?");
    }

    @Test
    void TestGroupColumn01() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .groupColumn(GroupType.COUNT, SysUserHelper.Column::id)
                .queryByPrimaryKey(arg());
        setSqlBuilder(sqlBuilderResult, "select count(SysUser.`id`) `id` from `sys_user` SysUser where SysUser.`id` = ?");
    }

    @Test
    void TestGroupColumnAndVirtualColumn01() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .groupColumn(GroupType.COUNT, SysUserHelper.Column::id)
                .virtualColumn(1, "AA")
                .queryByPrimaryKey(arg());
        setSqlBuilder(sqlBuilderResult, "select count(SysUser.`id`) `id`,1 `AA` from `sys_user` SysUser where SysUser.`id` = ?");
    }

    @Test
    void TestGroupColumnAndVirtualColumnAndColumn01() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .groupColumn(GroupType.COUNT, table -> table.id("idCount"))
                .virtualColumn(1, "AA")
                .select(table -> table)
                .queryByPrimaryKey(arg());
        setSqlBuilder(sqlBuilderResult, "select count(SysUser.`id`) `idCount`,1 `AA`,SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where SysUser.`id` = ?");
    }

    @Test
    void TestWhere01() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .select(table -> table)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().eq(arg())))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where SysUser.`user_name` = ?");
    }

    @Test
    void TestWhere02() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .select(table -> table)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().eq(arg())
                                .loginName().eq(arg())))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where SysUser.`user_name` = ? and SysUser.`login_name` = ?");
    }

    @Test
    void TestWhere03() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .select(table -> table)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().eq(arg())
                                .loginName().eq(arg()))
                        .or(mainTable.id().gt(arg())))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where SysUser.`user_name` = ? and SysUser.`login_name` = ? or SysUser.`id` > ?");
    }

    @Test
    void TestWhere04() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .select(table -> table)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().eq(arg())
                                .loginName().eq(arg()))
                        .or(mainTable.id().gt(arg())
                                .loginName().bt(arg(), arg()))
                        .and(mainTable.loginName().lk(arg())))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where SysUser.`user_name` = ? and SysUser.`login_name` = ? or (SysUser.`id` > ? and SysUser.`login_name` between ? and ?) and SysUser.`login_name` like ?");
    }

    @Test
    void TestWhere05() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .select(table -> table)
                .where((condition, mainTable) -> condition
                        .and(cd -> cd
                                .and(mainTable.userName().neq(arg()))
                                .or(mainTable.loginName().gt(arg())))
                        .or(mainTable.id().gt(arg())
                                .userName().bt(arg(), arg()))
                        .and(cd -> cd
                                .and(mainTable.userName().eq(arg()))
                                .or(mainTable.loginName().eq(arg()))))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where (SysUser.`user_name` != ? or SysUser.`login_name` > ?) or (SysUser.`id` > ? and SysUser.`user_name` between ? and ?) and (SysUser.`user_name` = ? or SysUser.`login_name` = ?)");
    }

    @Test
    void TestWhere06() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .select(table -> table)
                .where((condition, mainTable) -> condition
                        .and(cd -> cd
                                .and(mainTable.userName().neq(arg()))
                                .or(mainTable.userName().gt(arg())))
                        .or(mainTable.id().gt(arg())
                                .userName().bt(arg(), arg()))
                        .and(cd -> cd
                                .and(mainTable.userName().eq(arg()))
                                .or(mainTable.loginName().eq(arg()))))
                .where((condition, mainTable) -> condition
                        .and(cd -> cd
                                .and(mainTable.userName().gte(arg()))
                                .or(mainTable.loginName().lte(arg()))))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where ((SysUser.`user_name` != ? or SysUser.`user_name` > ?) or (SysUser.`id` > ? and SysUser.`user_name` between ? and ?) and (SysUser.`user_name` = ? or SysUser.`login_name` = ?)) and (SysUser.`user_name` >= ? or SysUser.`login_name` <= ?)");
    }

    @Test
    void TestJoin01() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .select(table -> table)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().eq(mainTable.id())))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id`");
    }

    @Test
    void TestJoin02() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .select(table -> table)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().eq(mainTable.id()).roleName().eq(arg())))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` and UserRole.`role_name` = ?");
    }

    @Test
    void TestJoin03() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .select(table -> table)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().eq(mainTable.id()).roleName().eq(arg())))
                .leftJoin(RoleResourceHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().eq(mainTable.id())))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` and UserRole.`role_name` = ? left join `role_resource` RoleResource on RoleResource.`role_id` = SysUser.`id`");
    }

    @Test
    void TestJoin04() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .select(table -> table)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().eq(mainTable.id()).roleName().eq(arg())))
                .leftJoin(RoleResourceHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().eq(mainTable.id())))
                .rightJoin(UserRoleHelper.class, "UR", (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().eq(mainTable.id())))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` and UserRole.`role_name` = ? left join `role_resource` RoleResource on RoleResource.`role_id` = SysUser.`id` right join `user_role` UR on UR.`role_id` = SysUser.`id`");
    }

    @Test
    void TestJoinAndWhere01() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .select(table -> table)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().eq(mainTable.id())))
                .where(UserRoleHelper.class, (condition, table, mainTable) -> condition
                        .and(table.roleName().lk(arg())))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` where UserRole.`role_name` like ?");

    }

    @Test
    void TestJoinAndWhere02() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .select(table -> table)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().eq(mainTable.id())
                                .roleName().eq(arg())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().neq(arg()))
                        .or(mainTable.loginName().eq(arg())))
                .where(UserRoleHelper.class, (condition, table, mainTable) -> condition
                        .and(table.roleName().eq(arg())))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` and UserRole.`role_name` = ? where (SysUser.`user_name` != ? or SysUser.`login_name` = ?) and UserRole.`role_name` = ?");
    }

    @Test
    void TestJoinAndWhere03() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .select(table -> table)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().eq(mainTable.id()).roleName().eq(arg())))
                .leftJoin(RoleResourceHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().eq(mainTable.id()))
                        .and(joinTable.roleId().eq(UserRoleHelper.class, UserRoleHelper.Column::roleId)))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` and UserRole.`role_name` = ? left join `role_resource` RoleResource on RoleResource.`role_id` = SysUser.`id` and RoleResource.`role_id` = UserRole.`role_id`");
    }

    @Test
    void TestJoinAndWhere04() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .select(table -> table)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().eq(mainTable.id())
                                .roleName().eq(arg())))
                .rightJoin(UserRoleHelper.class, "UR", (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().eq(mainTable.id())))
                .leftJoin(RoleResourceHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().eq(mainTable.id())
                                .roleId().lt(UserRoleHelper.class, "UR", UserRoleHelper.Column::roleId)))
                .where(UserRoleHelper.class, "UR", (condition, table, mainTable) -> condition
                        .and(table.roleName().eq(arg())))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` and UserRole.`role_name` = ? right join `user_role` UR on UR.`role_id` = SysUser.`id` left join `role_resource` RoleResource on RoleResource.`role_id` = SysUser.`id` and RoleResource.`role_id` < UR.`role_id` where UR.`role_name` = ?");
    }

    @Test
    void TestJoinWhereAndOr01() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .select(table -> table)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().eq(mainTable.id())
                                .roleName().eq(arg())))
                .rightJoin(UserRoleHelper.class, "UR", (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().eq(mainTable.id())))
                .leftJoin(RoleResourceHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().eq(mainTable.id())
                                .roleId().lt(UserRoleHelper.class, "UR", UserRoleHelper.Column::roleId)))
                .where(UserRoleHelper.class, "UR", (condition, table, mainTable) -> condition
                        .and(cd -> cd
                                .and(table.roleName().eq(arg()))
                                .or(table.roleId().eq(arg())))
                        .and(table.sortIndex().eq(arg())))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` and UserRole.`role_name` = ? right join `user_role` UR on UR.`role_id` = SysUser.`id` left join `role_resource` RoleResource on RoleResource.`role_id` = SysUser.`id` and RoleResource.`role_id` < UR.`role_id` where (UR.`role_name` = ? or UR.`role_id` = ?) and UR.`sort_index` = ?");
    }

    @Test
    void TestGroup01() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .select(table -> table)
                .groupBy(SysUserHelper.Group::userName)
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser group by SysUser.`user_name`");

    }

    @Test
    void TestGroup02() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .select(table -> table)
                .groupBy(table -> table.userName().id())
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser group by SysUser.`user_name`,SysUser.`id`");
    }

    @Test
    void TestGroupAndJoinAndWhere01() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .select(table -> table)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().eq(arg())))
                .groupBy(table -> table.id().loginName())
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where SysUser.`user_name` = ? group by SysUser.`id`,SysUser.`login_name`");

    }

    @Test
    void TestGroupAndJoinAndWhere02() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .select(table -> table)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().eq(mainTable.id())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().eq(arg())))
                .groupBy(table -> table.userName().id())
                .groupBy(UserRoleHelper.class, UserRoleHelper.Group::roleId)
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` where SysUser.`user_name` = ? group by SysUser.`user_name`,SysUser.`id`,UserRole.`role_id`");
    }

    @Test
    void TestSort01() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .select(table -> table)
                .orderBy(table -> table.id().asc())
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser order by SysUser.`id` asc");
    }

    @Test
    void TestSort02() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .select(table -> table)
                .orderBy(table -> table.id().asc().userName().desc())
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser order by SysUser.`id` asc,SysUser.`user_name` desc");
    }

    @Test
    void TestSort03() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, "sys_user_201903", SysUserHelper.class)
                .select(table -> table)
                .orderBy(table -> table.id().asc().userName().desc())
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user_201903` SysUser order by SysUser.`id` asc,SysUser.`user_name` desc");
    }

    @Test
    void TestSort04() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .select(table -> table)
                .innerJoin(UserRoleHelper.class, "UserRoleAlias", (on, joinTable, mainTable) -> on
                        .and(joinTable.userId().eq(mainTable.id())))
                .orderBy(table -> table.id().asc().userName().desc())
                .orderBy(UserRoleHelper.class, "UserRoleAlias", table -> table.sortIndex().desc())
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRoleAlias on UserRoleAlias.`user_id` = SysUser.`id` order by SysUser.`id` asc,SysUser.`user_name` desc,UserRoleAlias.`sort_index` desc");
    }

    @Test
    void TestSortAndJoinAndWhere01() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .select(table -> table)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().eq(arg())))
                .orderBy(table -> table.id().desc())
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where SysUser.`user_name` = ? order by SysUser.`id` desc");

    }

    @Test
    void TestSortAndJoinAndWhere02() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .select(table -> table)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().eq(mainTable.id())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.id().eq(arg())))
                .orderBy(UserRoleHelper.class, table -> table.id().desc())
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` where SysUser.`id` = ? order by UserRole.`id` desc");
    }

    @Test
    void TestLimit02() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .select(table -> table)
                .limit(arg(10L))
                .offset(arg(20L))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser limit ? offset ?");
    }

    @Test
    void TestJoinOn01() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .select(table -> table)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(o -> o
                                .and(joinTable.id().eq(mainTable.userName()))
                                .or(joinTable.roleName().eq(mainTable.primaryKey())))
                        .or(joinTable.id().eq(mainTable.loginName())))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on (UserRole.`id` = SysUser.`user_name` or UserRole.`role_name` = SysUser.`id`) or UserRole.`id` = SysUser.`login_name`");

    }

    @Test
    void TestJoinOn02() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .select(table -> table)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(o -> o
                                .and(joinTable.id().eq(mainTable.userName()).roleId().eq(arg()))
                                .or(joinTable.roleName().eq(mainTable.loginName()))
                                .and(o1 -> o1
                                        .and(joinTable.id().eq(arg())
                                                .roleName().eq(arg()))))
                        .or(joinTable.roleName().eq(arg()))
                        .or(joinTable.id().eq(mainTable.userName())))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on (UserRole.`id` = SysUser.`user_name` and UserRole.`role_id` = ? or UserRole.`role_name` = SysUser.`login_name` and UserRole.`id` = ? and UserRole.`role_name` = ?) or UserRole.`role_name` = ? or UserRole.`id` = SysUser.`user_name`");
    }

    @Test
    void TestWhereJoin01() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .select(table -> table)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(o -> o
                                .and(joinTable.id().eq(mainTable.userName()))
                                .or(joinTable.roleName().eq(mainTable.primaryKey())))
                        .or(joinTable.id().eq(mainTable.loginName())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().eq(UserRoleHelper.class, UserRoleHelper.Column::roleName)))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on (UserRole.`id` = SysUser.`user_name` or UserRole.`role_name` = SysUser.`id`) or UserRole.`id` = SysUser.`login_name` where SysUser.`user_name` = UserRole.`role_name`");
    }

    @Test
    void TestWhereJoin02() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .select(table -> table)
                .innerJoin("user_role_20190413", UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(o -> o
                                .and(joinTable.id().eq(mainTable.userName()))
                                .or(joinTable.roleName().eq(mainTable.primaryKey())))
                        .or(joinTable.id().eq(mainTable.loginName())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().eq(UserRoleHelper.class, UserRoleHelper.Column::id)))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role_20190413` UserRole on (UserRole.`id` = SysUser.`user_name` or UserRole.`role_name` = SysUser.`id`) or UserRole.`id` = SysUser.`login_name` where SysUser.`user_name` = UserRole.`id`");
    }

/*    @Test
    void TestSubQuery() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL,SysUserHelper.class)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.userId().equalTo(mainTable.id())))
                .subQuery(UserRoleHelper.class, "AA", (mainTable, query) -> query
                        .select(UserRoleHelper.Column::id)
                        .where((cd, mt) -> cd
                                .and(mt.roleId().equalTo(arg())
                                        .roleName().equalTo(mainTable.userName()))
                                .and(mainTable.id().equalTo(mt.id()))
                                .and(mt.sortIndex().equalTo(UserRoleHelper.class, UserRoleHelper.Column::id)))
                        .limit(arg(1), arg(1)).table(), "subQuery")
                .select(table -> table)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())))
                .query();
        setSqlBuilder(sqlBuilderResult, "select (select AA.`id` `id` from `user_role` AA where AA.`role_id` = ? and AA.`role_name` = SysUser.`user_name` and SysUser.`id` = AA.`id` and AA.`sort_index` = UserRole.`id` limit ?,?) subQuery, SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`user_id` = SysUser.`id` where SysUser.`user_name` = ?");
    }*/

/*    @Test
    void TestWhereSubQuery() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL,SysUserHelper.class)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.userId().equalTo(mainTable.id())))
                .select(table -> table)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().likeSubQuery(UserRoleHelper.class, (mt, query) -> query
                                .select(UserRoleHelper.Column::id)
                                .where((ct, m) -> ct
                                        .and(m.roleName().equalTo(arg())))
                                .limitOne()
                                .table())))
                .query();
        arg(0);
        arg(1);
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`user_id` = SysUser.`id` where SysUser.`user_name` like (select UserRole.`id` `id` from `user_role` UserRole where UserRole.`role_name` = ? limit ?,?)");

        sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL,SysUserHelper.class)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.userId().equalTo(mainTable.id())))
                .select(table -> table)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().likeSubQuery(UserRoleHelper.class, (mt, query) -> query
                                .select(UserRoleHelper.Column::id)
                                .where((ct, m) -> ct
                                        .and(m.userId().between(arg(), arg())))
                                .limitOne()
                                .table())))
                .query();
        arg(0);
        arg(1);
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`user_id` = SysUser.`id` where SysUser.`user_name` like (select UserRole.`id` `id` from `user_role` UserRole where UserRole.`user_id` between ? and ? limit ?,?)");
    }*/

    @Test
    void TestWhereSqlPart() {
        SqlBuilderResult sqlBuilderResult = new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                .select(table -> table)
                .where((condition, mainTable) -> condition
                        .and(mainTable.loginName().sqlPart(" =NOW()")))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where SysUser.`login_name` =NOW()");
    }

}
