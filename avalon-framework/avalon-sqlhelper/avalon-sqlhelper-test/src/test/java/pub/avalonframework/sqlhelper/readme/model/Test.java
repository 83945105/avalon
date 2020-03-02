package pub.avalonframework.sqlhelper.readme.model;

import pub.avalonframework.database.DatabaseType;
import pub.avalonframework.sqlhelper.core.data.block.GroupType;
import pub.avalonframework.sqlhelper.core.data.block.JoinType;
import pub.avalonframework.sqlhelper.core.engine.SqlHelperEngine;
import pub.avalonframework.sqlhelper.core.expression.builder.JoinBuilder;
import pub.avalonframework.sqlhelper.core.expression.builder.OnBuilder;
import pub.avalonframework.sqlhelper.core.expression.builder.SelectColumnBuilder;
import pub.avalonframework.sqlhelper.readme.entity.RoleResourceHelper;
import pub.avalonframework.sqlhelper.readme.entity.SysUserHelper;
import pub.avalonframework.sqlhelper.readme.entity.UserRoleHelper;

/**
 * Created by 白超 on 2019/5/9.
 */
public class Test {

    public static void main(String[] args) {
        RoleResourceHelper.Column column = RoleResourceHelper.column().id().id();
        SysUserHelper.On join = SysUserHelper.on().id().eq(column.resourceName());

        SysUserHelper.Column joinColumn = SysUserHelper.column().userName().userName("");
        RoleResourceHelper.Where where = RoleResourceHelper.where().id().eq("1").id().lk("");
        RoleResourceHelper.Group group = RoleResourceHelper.groupBy().id().id();
        SysUserHelper.Group joinGroup = SysUserHelper.groupBy().userName().userName();
        RoleResourceHelper.Sort sort = RoleResourceHelper.orderBy().id().asc().id().desc();
        SysUserHelper.Sort joinSort = SysUserHelper.orderBy().userName().asc().userName().desc();

        SqlHelperEngine sqlEngine = new SqlHelperEngine<>(DatabaseType.MYSQL, "", RoleResourceHelper.class)

                .buildSelectColumnExpression(new SysUserHelper.SelectColumnBuilder())

                .buildSelectColumnExpression(new SelectColumnBuilder<RoleResourceHelper.Column>() {

                }.select(table -> table.id().primaryKey()))

                .buildSelectColumnExpression(new SelectColumnBuilder<RoleResourceHelper.Column>() {{

                    if (true) {
                        select(table -> table.id().resourceName());
                    }

                }})

                .buildSelectColumnExpression(new RoleResourceHelper.SelectColumnBuilder().select(table -> table.id().resourceId()))
                .buildSelectColumnExpression(new SelectColumnBuilder<SysUserHelper.Column>() {
                }.select(table -> table.id().loginName()))
                .buildSelectColumnExpression(new RoleResourceHelper.SelectColumnBuilder() {{

                    select(SysUserHelper.class, table -> table.userName().userName(""));

                }})

                .buildJoinExpression(new JoinBuilder<SysUserHelper.On>() {{

                }})

                .buildOnExpression(new OnBuilder<SysUserHelper.On>() {{


                }})

                .select(


                )
                .select(table -> table.id().sqlPart("").resourceId())
                .select(table -> table.id().id().id(""))
                .select(SysUserHelper.class, table -> table.userName().userName("").id(GroupType.COUNT))
                .select(SysUserHelper.class, "", table -> table.userName().userName(""))
                .select(table -> column)
                .select(column)
                .select(column, column, column, joinColumn)
                .select(SysUserHelper.class, table -> joinColumn)

                .subQueryColumn("", parentTable -> {
                    return new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                            .where((condition, mainTable) -> condition
                                    .and(mainTable.id().eq(parentTable.resourceName())))
                            .query();
                })

                .virtualColumn(1, "")
                .groupColumn(GroupType.COUNT, table -> table.id("").id(""))
                .groupColumn(SysUserHelper.class, GroupType.MIN, table -> table.userName().userName(""))
                .groupColumn(SysUserHelper.class, "", GroupType.MIN, table -> table.userName().userName(""))
                .innerJoin(SysUserHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.userName().eq(mainTable.id()))
                        .and(mainTable.id().eq(joinTable.userName()))
                        .or(joinTable.userName().eq("")))
                .join(JoinType.INNER, UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().eq(SysUserHelper.class, table -> table.userName().userName())))
                .join(JoinType.INNER, UserRoleHelper.class)
                .on(join)
                .on(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().eq(mainTable.roleId())))
                .on((on, mainTable) -> on.and(mainTable.id().eq("")))
                .innerJoin(SysUserHelper.class, (on, joinTable, mainTable) -> on
                        .and(o -> o
                                .and(joinTable.userName().eq(mainTable.id()))
                                .or(mainTable.id().eq(joinTable.userName())))
                        .or(o -> o
                                .and(joinTable.userName().eq(mainTable.id()))
                                .or(mainTable.id().eq(joinTable.userName()))))
                .where((condition, mainTable) -> condition
                        .and(where)
                        .and(mainTable.sqlPart("").sqlPart("").id().eq("")
                                .id().gt("")))
                .where((condition, mainTable) -> condition
                        .and(cd -> cd
                                .and(mainTable.id().eq(""))
                                .or(mainTable.id().eq(""))))
                .where(SysUserHelper.class, (condition, joinTable, mainTable) -> condition
                        .and(mainTable.id().eq(joinTable.userName()))
                        .and(joinTable.userName().eq(mainTable.id()))
                        .or(joinTable.userName().eq(mainTable.id())))
                .where(SysUserHelper.class, (condition, joinTable, mainTable) -> condition
                        .and(cd -> cd
                                .and(joinTable.userName().eq(mainTable.id()))
                                .or(joinTable.userName().eq(mainTable.id())))
                        .or(cd -> cd
                                .and(joinTable.userName().eq(mainTable.id()))))
                .where((condition, mainTable) -> condition
                        .and(UserRoleHelper.class, (cd, joinTable) -> cd
                                .and(joinTable.roleId().eq(""))
                                .and(joinTable.roleId().eq(mainTable.id()))
                                .and(mainTable.id().eq(joinTable.roleId()))))
                .where(SysUserHelper.class, (condition, joinTable, mainTable) -> condition
                        .and(UserRoleHelper.class, (cd, UserRoleTable) -> cd
                                .and(joinTable.userName().eq(UserRoleTable.roleId())))
                        .or(UserRoleHelper.class, (cd, UserRoleTable) -> cd
                                .and(joinTable.userName().eq(UserRoleTable.roleId()))))
                .where((condition, mainTable) -> condition
                        .and(mainTable.id().eq(SysUserHelper.class, table -> table.userName().userName())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.id().eqSq(() -> {


                            return new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                                    .where((cd, mt) -> cd
                                            .and(mt.id().eq("")))
                                    .query();


                        })))
                .where((condition, mainTable) -> condition.and(where))
                .where(where)
                .groupBy(table -> table.id().id())
                .groupBy(SysUserHelper.class, table -> table.userName().userName())
                .groupBy(table -> group)
                .groupBy(SysUserHelper.class, table -> joinGroup)
                .groupBy(group, joinGroup)
                .having((having, mainTable) -> having.and(mainTable.id().max().eq("")))
                .having(SysUserHelper.class, (expression, joinTable, mainTable) -> expression.and(joinTable.id().eq(mainTable.id())))
                .orderBy(table -> table.id().asc().id().desc())
                .orderBy(SysUserHelper.class, table -> table.userName().asc().userName().desc())
                .orderBy(table -> sort)
                .orderBy(SysUserHelper.class, table -> joinSort)
                .orderBy(sort, joinSort)
                .limit(1L)
                .offset(2L);


        sqlEngine.query();
        sqlEngine.updateArgsByPrimaryKey("", "");

    }

}

