package pub.avalonframework.sqlhelper.readme.entity;

import pub.avalonframework.sqlhelper.core.beans.ColumnHandler;
import pub.avalonframework.sqlhelper.core.beans.TableColumn;
import pub.avalonframework.sqlhelper.core.data.block.builder.*;
import pub.avalonframework.sqlhelper.core.helper.*;

import java.util.LinkedHashSet;
import java.util.Set;

@SuppressWarnings("all")
public class UserRoleHelper implements TableHelper<UserRoleHelper, UserRoleHelper.Column, UserRoleHelper.On, UserRoleHelper.Where, UserRoleHelper.Group, UserRoleHelper.Having, UserRoleHelper.Sort> {

    public final static UserRoleHelper DEFAULT_INSTANCE = new UserRoleHelper();

        /**
     * table name
     */
        public final static String TABLE_NAME = "user_role";
        /**
     * table alias
     */
    public final static String TABLE_ALIAS = "UserRole";
                /**
     * primary key name
     */
        public final static String PRIMARY_KEY_NAME = "id";
        /**
     * primary key alias
     */
        public final static String PRIMARY_KEY_ALIAS = "id";
    
            /**
     * 
     */
        public final static String ID = "id";
        /**
     *  alias
     */
        public final static String ID_ALIAS = "id";
            /**
     * 
     */
        public final static String USER_ID = "user_id";
        /**
     *  alias
     */
        public final static String USER_ID_ALIAS = "userId";
            /**
     * 
     */
        public final static String ROLE_ID = "role_id";
        /**
     *  alias
     */
        public final static String ROLE_ID_ALIAS = "roleId";
            /**
     * 
     */
        public final static String ROLE_NAME = "role_name";
        /**
     *  alias
     */
        public final static String ROLE_NAME_ALIAS = "roleName";
            /**
     * 
     */
        public final static String SORT_INDEX = "sort_index";
        /**
     *  alias
     */
        public final static String SORT_INDEX_ALIAS = "sortIndex";
    
        /**
     * table columns
     */
        public final static Set<TableColumn> TABLE_COLUMNS;

    static {
        TABLE_COLUMNS = new LinkedHashSet<>(5);
        TableColumn primaryKeyTableColumn = new TableColumn(PRIMARY_KEY_NAME, PRIMARY_KEY_ALIAS, TABLE_NAME, TABLE_ALIAS, null, TABLE_COLUMNS);
        primaryKeyTableColumn.setPrimaryKeyColumnInfo(primaryKeyTableColumn);
                TABLE_COLUMNS.add(new TableColumn(ID, ID_ALIAS, TABLE_NAME, TABLE_ALIAS, primaryKeyTableColumn, TABLE_COLUMNS));
                TABLE_COLUMNS.add(new TableColumn(USER_ID, USER_ID_ALIAS, TABLE_NAME, TABLE_ALIAS, primaryKeyTableColumn, TABLE_COLUMNS));
                TABLE_COLUMNS.add(new TableColumn(ROLE_ID, ROLE_ID_ALIAS, TABLE_NAME, TABLE_ALIAS, primaryKeyTableColumn, TABLE_COLUMNS));
                TABLE_COLUMNS.add(new TableColumn(ROLE_NAME, ROLE_NAME_ALIAS, TABLE_NAME, TABLE_ALIAS, primaryKeyTableColumn, TABLE_COLUMNS));
                TABLE_COLUMNS.add(new TableColumn(SORT_INDEX, SORT_INDEX_ALIAS, TABLE_NAME, TABLE_ALIAS, primaryKeyTableColumn, TABLE_COLUMNS));
            }

    public static UserRoleHelper newInstance() {
        return new UserRoleHelper();
    }

    public static Column column() {
        return new Column(TABLE_ALIAS);
    }

    public static Column column(String tableAlias) {
        return new Column(tableAlias);
    }

    public static On on() {
        return new On(TABLE_ALIAS);
    }

    public static On on(String tableAlias) {
        return new On(tableAlias);
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
    public UserRoleHelper getDefaultInstance() {
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

    public final static class Column extends ColumnHelper<Column> {

        public Column() {
            super(TABLE_ALIAS);
        }

        public Column(String tableAlias) {
            super(tableAlias);
        }

        @Override
        public UserRoleHelper getDefaultTableHelper() {
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
                public Column userId() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, USER_ID, USER_ID_ALIAS, USER_ID_ALIAS).getHelper();
        }

        public Column userId(ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, USER_ID, USER_ID_ALIAS, USER_ID_ALIAS, columnHandlers).getHelper();
        }

        public Column userId(String alias) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, USER_ID, USER_ID_ALIAS, alias).getHelper();
        }

        public Column userId(String alias, ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, USER_ID, USER_ID_ALIAS, alias, columnHandlers).getHelper();
        }
                public Column roleId() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_ID, ROLE_ID_ALIAS, ROLE_ID_ALIAS).getHelper();
        }

        public Column roleId(ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_ID, ROLE_ID_ALIAS, ROLE_ID_ALIAS, columnHandlers).getHelper();
        }

        public Column roleId(String alias) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_ID, ROLE_ID_ALIAS, alias).getHelper();
        }

        public Column roleId(String alias, ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_ID, ROLE_ID_ALIAS, alias, columnHandlers).getHelper();
        }
                public Column roleName() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_NAME, ROLE_NAME_ALIAS, ROLE_NAME_ALIAS).getHelper();
        }

        public Column roleName(ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_NAME, ROLE_NAME_ALIAS, ROLE_NAME_ALIAS, columnHandlers).getHelper();
        }

        public Column roleName(String alias) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_NAME, ROLE_NAME_ALIAS, alias).getHelper();
        }

        public Column roleName(String alias, ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_NAME, ROLE_NAME_ALIAS, alias, columnHandlers).getHelper();
        }
                public Column sortIndex() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, SORT_INDEX, SORT_INDEX_ALIAS, SORT_INDEX_ALIAS).getHelper();
        }

        public Column sortIndex(ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, SORT_INDEX, SORT_INDEX_ALIAS, SORT_INDEX_ALIAS, columnHandlers).getHelper();
        }

        public Column sortIndex(String alias) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, SORT_INDEX, SORT_INDEX_ALIAS, alias).getHelper();
        }

        public Column sortIndex(String alias, ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, SORT_INDEX, SORT_INDEX_ALIAS, alias, columnHandlers).getHelper();
        }
            }

    public final static class On extends OnHelper<On> {

        public On() {
            super(TABLE_ALIAS);
        }

        public On(String tableAlias) {
            super(tableAlias);
        }

        @Override
        public UserRoleHelper getDefaultTableHelper() {
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
                    public OnDataBlockBuilder<On> userId() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, USER_ID, USER_ID_ALIAS, USER_ID_ALIAS);
        }
                    public OnDataBlockBuilder<On> roleId() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_ID, ROLE_ID_ALIAS, ROLE_ID_ALIAS);
        }
                    public OnDataBlockBuilder<On> roleName() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_NAME, ROLE_NAME_ALIAS, ROLE_NAME_ALIAS);
        }
                    public OnDataBlockBuilder<On> sortIndex() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, SORT_INDEX, SORT_INDEX_ALIAS, SORT_INDEX_ALIAS);
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
        public UserRoleHelper getDefaultTableHelper() {
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
                public WhereDataBlockBuilder<Where> userId() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, USER_ID, USER_ID_ALIAS, USER_ID_ALIAS);
        }
                public WhereDataBlockBuilder<Where> roleId() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_ID, ROLE_ID_ALIAS, ROLE_ID_ALIAS);
        }
                public WhereDataBlockBuilder<Where> roleName() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_NAME, ROLE_NAME_ALIAS, ROLE_NAME_ALIAS);
        }
                public WhereDataBlockBuilder<Where> sortIndex() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, SORT_INDEX, SORT_INDEX_ALIAS, SORT_INDEX_ALIAS);
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
        public UserRoleHelper getDefaultTableHelper() {
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
                public Group userId() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, USER_ID, USER_ID_ALIAS, USER_ID_ALIAS).getHelper();
        }
                public Group roleId() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_ID, ROLE_ID_ALIAS, ROLE_ID_ALIAS).getHelper();
        }
                public Group roleName() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_NAME, ROLE_NAME_ALIAS, ROLE_NAME_ALIAS).getHelper();
        }
                public Group sortIndex() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, SORT_INDEX, SORT_INDEX_ALIAS, SORT_INDEX_ALIAS).getHelper();
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
        public UserRoleHelper getDefaultTableHelper() {
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
                public HavingDataBlockBuilder<Having> userId() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, USER_ID, USER_ID_ALIAS, USER_ID_ALIAS);
        }

        public HavingDataBlockBuilder<Having> userId(ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, USER_ID, USER_ID_ALIAS, USER_ID_ALIAS, columnHandlers);
        }
                public HavingDataBlockBuilder<Having> roleId() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_ID, ROLE_ID_ALIAS, ROLE_ID_ALIAS);
        }

        public HavingDataBlockBuilder<Having> roleId(ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_ID, ROLE_ID_ALIAS, ROLE_ID_ALIAS, columnHandlers);
        }
                public HavingDataBlockBuilder<Having> roleName() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_NAME, ROLE_NAME_ALIAS, ROLE_NAME_ALIAS);
        }

        public HavingDataBlockBuilder<Having> roleName(ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_NAME, ROLE_NAME_ALIAS, ROLE_NAME_ALIAS, columnHandlers);
        }
                public HavingDataBlockBuilder<Having> sortIndex() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, SORT_INDEX, SORT_INDEX_ALIAS, SORT_INDEX_ALIAS);
        }

        public HavingDataBlockBuilder<Having> sortIndex(ColumnHandler... columnHandlers) {
            return this.apply(TABLE_NAME, TABLE_ALIAS, SORT_INDEX, SORT_INDEX_ALIAS, SORT_INDEX_ALIAS, columnHandlers);
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
        public UserRoleHelper getDefaultTableHelper() {
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
                public SortDataBlockBuilder<Sort> userId() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, USER_ID, USER_ID_ALIAS, USER_ID_ALIAS);
        }
                public SortDataBlockBuilder<Sort> roleId() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_ID, ROLE_ID_ALIAS, ROLE_ID_ALIAS);
        }
                public SortDataBlockBuilder<Sort> roleName() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, ROLE_NAME, ROLE_NAME_ALIAS, ROLE_NAME_ALIAS);
        }
                public SortDataBlockBuilder<Sort> sortIndex() {
            return this.apply(TABLE_NAME, TABLE_ALIAS, SORT_INDEX, SORT_INDEX_ALIAS, SORT_INDEX_ALIAS);
        }
            }

    public static class SqlBuilder extends pub.avalonframework.sqlhelper.core.builder.SqlBuilder<UserRoleHelper, Column, On, Where, Group, Having, Sort> {
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