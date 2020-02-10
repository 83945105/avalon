package pub.avalonframework.sqlhelper.readme.model;

import pub.avalonframework.database.DatabaseType;
import pub.avalonframework.sqlhelper.core.beans.GroupType;
import pub.avalonframework.sqlhelper.core.beans.JoinType;
import pub.avalonframework.sqlhelper.core.engine.SqlHelperEngine;
import pub.avalonframework.sqlhelper.core.engine.builder.SqlColumn;
import pub.avalonframework.sqlhelper.core.engine.builder.SqlJoin;
import pub.avalonframework.sqlhelper.core.engine.builder.SqlOn;
import pub.avalonframework.sqlhelper.readme.entity.RoleResourceHelper;
import pub.avalonframework.sqlhelper.readme.entity.SysUserHelper;
import pub.avalonframework.sqlhelper.readme.entity.UserRoleHelper;

/**
 * Created by 白超 on 2019/5/9.
 */
public class Test {

    public static void main(String[] args) {
        RoleResourceHelper.Column column = RoleResourceHelper.column().id().id();
        SysUserHelper.On join = SysUserHelper.on().id().equalTo(column.resourceName());

        SysUserHelper.Column joinColumn = SysUserHelper.column().userName().userName("");
        RoleResourceHelper.Where where = RoleResourceHelper.where().id().equalTo("1").id().like("");
        RoleResourceHelper.Group group = RoleResourceHelper.groupBy().id().id();
        SysUserHelper.Group joinGroup = SysUserHelper.groupBy().userName().userName();
        RoleResourceHelper.Sort sort = RoleResourceHelper.orderBy().id().asc().id().desc();
        SysUserHelper.Sort joinSort = SysUserHelper.orderBy().userName().asc().userName().desc();

        SqlHelperEngine sqlEngine = new SqlHelperEngine<>(DatabaseType.MYSQL, "", RoleResourceHelper.class)

                .sqlColumn(new SysUserHelper.SqlColumn())

                .sqlColumn(new SqlColumn<RoleResourceHelper.Column>() {

                }.select(table -> table.id().primaryKey()))

                .sqlColumn(new SqlColumn<RoleResourceHelper.Column>() {{

                    if (true) {
                        select(table -> table.id().resourceName());
                    }

                }})

                .sqlColumn(new RoleResourceHelper.SqlColumn().select(table -> table.id().resourceId()))
                .sqlColumn(new SqlColumn<SysUserHelper.Column>() {
                }.select(table -> table.id().loginName()))
                .sqlColumn(new RoleResourceHelper.SqlColumn() {{

                    column(SysUserHelper.class, table -> table.userName().userName(""));

                }})

                .sqlJoin(new SqlJoin<SysUserHelper.On>() {{

                }})

                .sqlOn(new SqlOn<SysUserHelper.On>() {{


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
                .column(SysUserHelper.class, table -> joinColumn)

                .subQueryColumn("", parentTable -> {
                    return new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                            .where((condition, mainTable) -> condition
                                    .and(mainTable.id().equalTo(parentTable.resourceName())))
                            .query();
                })

                .virtualColumn(1, "")
                .groupColumn(GroupType.COUNT, table -> table.id("").id(""))
                .groupColumn(SysUserHelper.class, GroupType.MIN, table -> table.userName().userName(""))
                .groupColumn(SysUserHelper.class, "", GroupType.MIN, table -> table.userName().userName(""))
                .innerJoin(SysUserHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.userName().equalTo(mainTable.id()))
                        .and(mainTable.id().equalTo(joinTable.userName()))
                        .or(joinTable.userName().equalTo("")))
                .join(JoinType.INNER, UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(SysUserHelper.class, table -> table.userName().userName())))
                .join(JoinType.INNER, UserRoleHelper.class)
                .on(join)
                .on(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.roleId())))
                .on((on, mainTable) -> on.and(mainTable.id().equalTo("")))
                .innerJoin(SysUserHelper.class, (on, joinTable, mainTable) -> on
                        .and(o -> o
                                .and(joinTable.userName().equalTo(mainTable.id()))
                                .or(mainTable.id().equalTo(joinTable.userName())))
                        .or(o -> o
                                .and(joinTable.userName().equalTo(mainTable.id()))
                                .or(mainTable.id().equalTo(joinTable.userName()))))
                .where((condition, mainTable) -> condition
                        .and(where)
                        .and(mainTable.sqlPart("").sqlPart("").id().equalTo("")
                                .id().greaterThan("")))
                .where((condition, mainTable) -> condition
                        .and(cd -> cd
                                .and(mainTable.id().equalTo(""))
                                .or(mainTable.id().equalTo(""))))
                .where(SysUserHelper.class, (condition, joinTable, mainTable) -> condition
                        .and(mainTable.id().equalTo(joinTable.userName()))
                        .and(joinTable.userName().equalTo(mainTable.id()))
                        .or(joinTable.userName().equalTo(mainTable.id())))
                .where(SysUserHelper.class, (condition, joinTable, mainTable) -> condition
                        .and(cd -> cd
                                .and(joinTable.userName().equalTo(mainTable.id()))
                                .or(joinTable.userName().equalTo(mainTable.id())))
                        .or(cd -> cd
                                .and(joinTable.userName().equalTo(mainTable.id()))))
                .where((condition, mainTable) -> condition
                        .and(UserRoleHelper.class, (cd, joinTable) -> cd
                                .and(joinTable.roleId().equalTo(""))
                                .and(joinTable.roleId().equalTo(mainTable.id()))
                                .and(mainTable.id().equalTo(joinTable.roleId()))))
                .where(SysUserHelper.class, (condition, joinTable, mainTable) -> condition
                        .and(UserRoleHelper.class, (cd, UserRoleTable) -> cd
                                .and(joinTable.userName().equalTo(UserRoleTable.roleId())))
                        .or(UserRoleHelper.class, (cd, UserRoleTable) -> cd
                                .and(joinTable.userName().equalTo(UserRoleTable.roleId()))))
                .where((condition, mainTable) -> condition
                        .and(mainTable.id().equalTo(SysUserHelper.class, table -> table.userName().userName())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.id().equalToSubQuery(() -> {


                            return new SqlHelperEngine<>(DatabaseType.MYSQL, SysUserHelper.class)
                                    .where((cd, mt) -> cd
                                            .and(mt.id().equalTo("")))
                                    .query();


                        })))
                .where((condition, mainTable) -> condition.and(where))
                .where(where)
                .groupBy(table -> table.id().id())
                .groupBy(SysUserHelper.class, table -> table.userName().userName())
                .groupBy(table -> group)
                .groupBy(SysUserHelper.class, table -> joinGroup)
                .groupBy(group, joinGroup)
                .having((having, mainTable) -> having.and(mainTable.id().max().equalTo("")))
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

