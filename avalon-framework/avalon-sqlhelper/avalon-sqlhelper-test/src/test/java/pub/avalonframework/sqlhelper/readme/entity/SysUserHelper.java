package pub.avalonframework.sqlhelper.readme.entity;

import pub.avalonframework.sqlhelper.core.beans.ColumnHandler;
import pub.avalonframework.sqlhelper.core.beans.TableColumn;
import pub.avalonframework.sqlhelper.core.data.block.builder.HavingDataBlockBuilder;
import pub.avalonframework.sqlhelper.core.data.block.builder.OnDataBlockBuilder;
import pub.avalonframework.sqlhelper.core.data.block.builder.SortDataBlockBuilder;
import pub.avalonframework.sqlhelper.core.data.block.builder.WhereDataBlockBuilder;
import pub.avalonframework.sqlhelper.core.helper.*;

import java.util.LinkedHashSet;
import java.util.Set;

@SuppressWarnings("all")
public class SysUserHelper implements TableHelper<SysUserHelper, SysUserHelper.Column, SysUserHelper.On, SysUserHelper.Where, SysUserHelper.Group, SysUserHelper.Having, SysUserHelper.Sort> {

    public final static SysUserHelper DEFAULT_INSTANCE = new SysUserHelper();

        /**
     * table name
     */
        public final static String TABLE_NAME = "sys_user";
        /**
     * table alias
     */
    public final static String TABLE_ALIAS = "SysUser";
                /**
     * primary key name
     */
        public final static String PRIMARY_KEY_NAME = "id";
        /**
     * primary key alias
     */
        public final static String PRIMARY_KEY_ALIAS = "id";

            /**
     * 主键ID
     */
        public final static String ID = "id";
        /**
     * 主键ID alias
     */
        public final static String ID_ALIAS = "id";
            /**
     * 用户名
     */
        public final static String USER_NAME = "user_name";
        /**
     * 用户名 alias
     */
        public final static String USER_NAME_ALIAS = "userName";
            /**
     * 登录名
     */
        public final static String LOGIN_NAME = "login_name";
        /**
     * 登录名 alias
     */
        public final static String LOGIN_NAME_ALIAS = "loginName";

        /**
     * table columns
     */
        public final static Set<TableColumn> TABLE_COLUMNS;

    static {
        TABLE_COLUMNS = new LinkedHashSet<>(3);
        TableColumn primaryKeyTableColumn = new TableColumn(PRIMARY_KEY_NAME, PRIMARY_KEY_ALIAS, TABLE_NAME, TABLE_ALIAS, null, TABLE_COLUMNS);
        primaryKeyTableColumn.setPrimaryKeyColumnInfo(primaryKeyTableColumn);
                TABLE_COLUMNS.add(new TableColumn(ID, ID_ALIAS, TABLE_NAME, TABLE_ALIAS, primaryKeyTableColumn, TABLE_COLUMNS));
                TABLE_COLUMNS.add(new TableColumn(USER_NAME, USER_NAME_ALIAS, TABLE_NAME, TABLE_ALIAS, primaryKeyTableColumn, TABLE_COLUMNS));
                TABLE_COLUMNS.add(new TableColumn(LOGIN_NAME, LOGIN_NAME_ALIAS, TABLE_NAME, TABLE_ALIAS, primaryKeyTableColumn, TABLE_COLUMNS));
            }

    public static SysUserHelper newInstance() {
        return new SysUserHelper();
    }

    public static On on() {
        return new On(TABLE_ALIAS);
    }

    public static On on(String tableAlias) {
        return new On(tableAlias);
    }

    public static Column column() {
        return new Column(TABLE_ALIAS);
    }

    public static Column column(String tableAlias) {
        return new Column(tableAlias);
    }

    public static Where where() {
        return new Where(TABLE_ALIAS);
    }

    public static Where where(String tableAlias) {
        return new Where(tableAlias);
    }

    public static Group groupBy() {
        return new Group(TABLE_ALIAS);
    }

    public static Group groupBy(String tableAlias) {
        return new Group(tableAlias);
    }

    public static Having having() {
        return new Having(TABLE_ALIAS);
    }

    public static Having having(String tableAlias) {
        return new Having(tableAlias);
    }

    public static Sort orderBy() {
        return new Sort(TABLE_ALIAS);
    }

    public static Sort orderBy(String tableAlias) {
        return new Sort(tableAlias);
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String getTableAlias() {
        return TABLE_ALIAS;
    }

    @Override
    public String getPrimaryKeyName() {
        return PRIMARY_KEY_NAME;
    }

    @Override
    public String getPrimaryKeyAlias() {
        return PRIMARY_KEY_ALIAS;
    }

    @Override
    public Set<TableColumn> getTableColumns() {
        return TABLE_COLUMNS;
    }

    @Override
    public SysUserHelper getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    @Override
    public Column newColumnHelper(String tableAlias) {
        return column(tableAlias);
    }

    @Override
    public On newOnHelper(String tableAlias) {
        return on(tableAlias);
    }

    @Override
    public Where newWhereHelper(String tableAlias) {
        return where(tableAlias);
    }

    @Override
    public Group newGroupHelper(String tableAlias) {
        return groupBy(tableAlias);
    }

    @Override
    public Having newHavingHelper(String tableAlias) {
        return having(tableAlias);
    }

    @Override
    public Sort newSortHelper(String tableAlias) {
        return orderBy(tableAlias);
    }

    public final static class On extends OnHelper<On> {

        public On() {
            super(TABLE_ALIAS);
        }

        public On(String tableAlias) {
            super(tableAlias);
        }

        @Override
        public SysUserHelper getDefaultTableHelper() {
            return DEFAULT_INSTANCE.getDefaultInstance();
        }

        public OnDataBlockBuilder<On> sqlPart(String sqlPart) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, sqlPart);
        }

        public OnDataBlockBuilder<On> primaryKey() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, PRIMARY_KEY_NAME, PRIMARY_KEY_ALIAS, PRIMARY_KEY_ALIAS);
        }

                public OnDataBlockBuilder<On> id() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ID, ID_ALIAS, ID_ALIAS);
        }
                public OnDataBlockBuilder<On> userName() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, USER_NAME, USER_NAME_ALIAS, USER_NAME_ALIAS);
        }
                public OnDataBlockBuilder<On> loginName() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, LOGIN_NAME, LOGIN_NAME_ALIAS, LOGIN_NAME_ALIAS);
        }
            }

    public final static class Column extends ColumnHelper<Column> {

        public Column() {
            super(TABLE_ALIAS);
        }

        public Column(String tableAlias) {
            super(tableAlias);
        }

        @Override
        public SysUserHelper getDefaultTableHelper() {
            return DEFAULT_INSTANCE.getDefaultInstance();
        }

        public Column sqlPart(String sqlPart) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, sqlPart).getHelper();
        }

        public Column primaryKey() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, PRIMARY_KEY_NAME, PRIMARY_KEY_ALIAS, PRIMARY_KEY_ALIAS).getHelper();
        }

        public Column primaryKey(ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, PRIMARY_KEY_NAME, PRIMARY_KEY_ALIAS, PRIMARY_KEY_ALIAS, columnHandlers).getHelper();
        }

        public Column primaryKey(String alias) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, PRIMARY_KEY_NAME, PRIMARY_KEY_ALIAS, alias).getHelper();
        }

        public Column primaryKey(String alias, ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, PRIMARY_KEY_NAME, PRIMARY_KEY_ALIAS, alias, columnHandlers).getHelper();
        }

                public Column id() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ID, ID_ALIAS, ID_ALIAS).getHelper();
        }

        public Column id(ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ID, ID_ALIAS, ID_ALIAS, columnHandlers).getHelper();
        }

        public Column id(String alias) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ID, ID_ALIAS, alias).getHelper();
        }

        public Column id(String alias, ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ID, ID_ALIAS, alias, columnHandlers).getHelper();
        }
                public Column userName() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, USER_NAME, USER_NAME_ALIAS, USER_NAME_ALIAS).getHelper();
        }

        public Column userName(ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, USER_NAME, USER_NAME_ALIAS, USER_NAME_ALIAS, columnHandlers).getHelper();
        }

        public Column userName(String alias) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, USER_NAME, USER_NAME_ALIAS, alias).getHelper();
        }

        public Column userName(String alias, ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, USER_NAME, USER_NAME_ALIAS, alias, columnHandlers).getHelper();
        }
                public Column loginName() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, LOGIN_NAME, LOGIN_NAME_ALIAS, LOGIN_NAME_ALIAS).getHelper();
        }

        public Column loginName(ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, LOGIN_NAME, LOGIN_NAME_ALIAS, LOGIN_NAME_ALIAS, columnHandlers).getHelper();
        }

        public Column loginName(String alias) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, LOGIN_NAME, LOGIN_NAME_ALIAS, alias).getHelper();
        }

        public Column loginName(String alias, ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, LOGIN_NAME, LOGIN_NAME_ALIAS, alias, columnHandlers).getHelper();
        }
            }

    public final static class Where extends WhereHelper<Where> {

        public Where() {
            super(TABLE_ALIAS);
        }

        public Where(String tableAlias) {
            super(tableAlias);
        }

        @Override
        public SysUserHelper getDefaultTableHelper() {
            return DEFAULT_INSTANCE.getDefaultInstance();
        }

        public WhereDataBlockBuilder<Where> sqlPart(String sqlPart) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, sqlPart);
        }

        public WhereDataBlockBuilder<Where> primaryKey() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, PRIMARY_KEY_NAME, PRIMARY_KEY_ALIAS, PRIMARY_KEY_ALIAS);
        }

                public WhereDataBlockBuilder<Where> id() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ID, ID_ALIAS, ID_ALIAS);
        }
                public WhereDataBlockBuilder<Where> userName() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, USER_NAME, USER_NAME_ALIAS, USER_NAME_ALIAS);
        }
                public WhereDataBlockBuilder<Where> loginName() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, LOGIN_NAME, LOGIN_NAME_ALIAS, LOGIN_NAME_ALIAS);
        }
            }

    public final static class Group extends GroupHelper<Group> {

        public Group() {
            super(TABLE_ALIAS);
        }

        public Group(String tableAlias) {
            super(tableAlias);
        }

        @Override
        public SysUserHelper getDefaultTableHelper() {
            return DEFAULT_INSTANCE.getDefaultInstance();
        }

        public Group sqlPart(String sqlPart) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, sqlPart).getHelper();
        }

        public Group primaryKey() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, PRIMARY_KEY_NAME, PRIMARY_KEY_ALIAS, PRIMARY_KEY_ALIAS).getHelper();
        }

                public Group id() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ID, ID_ALIAS, ID_ALIAS).getHelper();
        }
                public Group userName() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, USER_NAME, USER_NAME_ALIAS, USER_NAME_ALIAS).getHelper();
        }
                public Group loginName() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, LOGIN_NAME, LOGIN_NAME_ALIAS, LOGIN_NAME_ALIAS).getHelper();
        }
            }

    public final static class Having extends HavingHelper<Having> {

        public Having() {
            super(TABLE_ALIAS);
        }

        public Having(String tableAlias) {
            super(tableAlias);
        }

        @Override
        public SysUserHelper getDefaultTableHelper() {
            return DEFAULT_INSTANCE.getDefaultInstance();
        }

        public HavingDataBlockBuilder<Having> sqlPart(String sqlPart) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, sqlPart);
        }

        public HavingDataBlockBuilder<Having> primaryKey() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, PRIMARY_KEY_NAME, PRIMARY_KEY_ALIAS, PRIMARY_KEY_ALIAS);
        }

        public HavingDataBlockBuilder<Having> primaryKey(ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, PRIMARY_KEY_NAME, PRIMARY_KEY_ALIAS, PRIMARY_KEY_ALIAS, columnHandlers);
        }

                public HavingDataBlockBuilder<Having> id() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ID, ID_ALIAS, ID_ALIAS);
        }

        public HavingDataBlockBuilder<Having> id(ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ID, ID_ALIAS, ID_ALIAS, columnHandlers);
        }
                public HavingDataBlockBuilder<Having> userName() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, USER_NAME, USER_NAME_ALIAS, USER_NAME_ALIAS);
        }

        public HavingDataBlockBuilder<Having> userName(ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, USER_NAME, USER_NAME_ALIAS, USER_NAME_ALIAS, columnHandlers);
        }
                public HavingDataBlockBuilder<Having> loginName() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, LOGIN_NAME, LOGIN_NAME_ALIAS, LOGIN_NAME_ALIAS);
        }

        public HavingDataBlockBuilder<Having> loginName(ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, LOGIN_NAME, LOGIN_NAME_ALIAS, LOGIN_NAME_ALIAS, columnHandlers);
        }
            }

    public final static class Sort extends SortHelper<Sort> {

        public Sort() {
            super(TABLE_ALIAS);
        }

        public Sort(String tableAlias) {
            super(tableAlias);
        }

        @Override
        public SysUserHelper getDefaultTableHelper() {
            return DEFAULT_INSTANCE.getDefaultInstance();
        }

        public SortDataBlockBuilder<Sort> sqlPart(String sqlPart) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, sqlPart);
        }

        public SortDataBlockBuilder<Sort> primaryKey() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, PRIMARY_KEY_NAME, PRIMARY_KEY_ALIAS, PRIMARY_KEY_ALIAS);
        }

                public SortDataBlockBuilder<Sort> id() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ID, ID_ALIAS, ID_ALIAS);
        }
                public SortDataBlockBuilder<Sort> userName() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, USER_NAME, USER_NAME_ALIAS, USER_NAME_ALIAS);
        }
                public SortDataBlockBuilder<Sort> loginName() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, LOGIN_NAME, LOGIN_NAME_ALIAS, LOGIN_NAME_ALIAS);
        }
            }

    public static class SqlBuilder extends pub.avalonframework.sqlhelper.core.builder.SqlBuilder<SysUserHelper, Column, On, Where, Group, Having, Sort> {
        public SqlBuilder() {
            super(TABLE_ALIAS);
        }
        public SqlBuilder(String tableAlias) {
            super(tableAlias);
        }
    }

    public static class InsertColumnBuilder extends pub.avalonframework.sqlhelper.core.builder.InsertColumnBuilder<Column> {
        public InsertColumnBuilder() {
            super(TABLE_ALIAS);
        }
        public InsertColumnBuilder(String tableAlias) {
            super(tableAlias);
        }
    }

    public static class SelectColumnBuilder extends pub.avalonframework.sqlhelper.core.builder.SelectColumnBuilder<Column> {
        public SelectColumnBuilder() {
            super(TABLE_ALIAS);
        }
        public SelectColumnBuilder(String tableAlias) {
            super(tableAlias);
        }
    }

    public static class UpdateColumnBuilder extends pub.avalonframework.sqlhelper.core.builder.UpdateColumnBuilder<Column> {
        public UpdateColumnBuilder() {
            super(TABLE_ALIAS);
        }
        public UpdateColumnBuilder(String tableAlias) {
            super(tableAlias);
        }
    }

    public static class ColumnBuilder extends pub.avalonframework.sqlhelper.core.builder.ColumnBuilder<Column> {
        public ColumnBuilder() {
            super(TABLE_ALIAS);
        }
        public ColumnBuilder(String tableAlias) {
            super(tableAlias);
        }
    }

    public static class JoinBuilder extends pub.avalonframework.sqlhelper.core.builder.JoinBuilder<On> {
        public JoinBuilder() {
            super(TABLE_ALIAS);
        }
        public JoinBuilder(String tableAlias) {
            super(tableAlias);
        }
    }

    public static class WhereBuilder extends pub.avalonframework.sqlhelper.core.builder.WhereBuilder<Where> {
        public WhereBuilder() {
            super(TABLE_ALIAS);
        }
        public WhereBuilder(String tableAlias) {
            super(tableAlias);
        }
    }

    public static class GroupBuilder extends pub.avalonframework.sqlhelper.core.builder.GroupBuilder<Group> {
        public GroupBuilder() {
            super(TABLE_ALIAS);
        }
        public GroupBuilder(String tableAlias) {
            super(tableAlias);
        }
    }

    public static class HavingBuilder extends pub.avalonframework.sqlhelper.core.builder.HavingBuilder<Having> {
        public HavingBuilder() {
            super(TABLE_ALIAS);
        }
        public HavingBuilder(String tableAlias) {
            super(tableAlias);
        }
    }

    public static class SortBuilder extends pub.avalonframework.sqlhelper.core.builder.SortBuilder<Sort> {
        public SortBuilder() {
            super(TABLE_ALIAS);
        }
        public SortBuilder(String tableAlias) {
            super(tableAlias);
        }
    }
}