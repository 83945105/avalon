package pub.avalonframework.sqlhelper.core.data.block;

import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;

/**
 * @author baichao
 */
public final class ColumnDataBlock extends AbstractDataBlock<ColumnDataBlock> {

    private Type type = Type.DEFAULT;

    private ColumnHandler[] columnHandlers;

    private String sqlPart;

    private Object columnValue;

    private SqlBuilderResult sqlBuilderResult;

    public ColumnDataBlock(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias);
    }

    public ColumnDataBlock(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
    }

    public ColumnDataBlock(String templateTableName, String templateTableAlias, Object columnValue, String mappingFieldName) {
        super(templateTableName, templateTableAlias, null, null, mappingFieldName);
        this.columnValue = columnValue;
        this.type = Type.VIRTUAL;
    }

    public ColumnDataBlock(String templateTableName, String templateTableAlias, SqlBuilderResult sqlBuilderResult, String mappingFieldName) {
        super(templateTableName, templateTableAlias, null, null, mappingFieldName);
        this.sqlBuilderResult = sqlBuilderResult;
        this.type = Type.SUB_QUERY;
    }

    public ColumnDataBlock(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, ColumnHandler... columnHandlers) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias);
        this.columnHandlers = columnHandlers;
        this.type = Type.HANDLER;
    }

    public ColumnDataBlock(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName, ColumnHandler... columnHandlers) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
        this.columnHandlers = columnHandlers;
        this.type = Type.HANDLER;
    }

    public ColumnDataBlock(String templateTableName, String templateTableAlias, String sqlPart) {
        super(templateTableName, templateTableAlias, null, null);
        this.sqlPart = sqlPart;
        this.type = Type.SQL_PART;
    }

    public Type getType() {
        return type;
    }

    public ColumnHandler[] getColumnHandlers() {
        return columnHandlers;
    }

    public String getSqlPart() {
        return sqlPart;
    }

    public Object getColumnValue() {
        return columnValue;
    }

    public SqlBuilderResult getSqlBuilderResult() {
        return sqlBuilderResult;
    }

    public ColumnDataBlock setType(Type type) {
        this.type = type;
        return this;
    }

    public ColumnDataBlock setColumnHandlers(ColumnHandler... columnHandlers) {
        this.columnHandlers = columnHandlers;
        this.setType(Type.HANDLER);
        return this;
    }

    public ColumnDataBlock setVirtualColumn(Object columnValue, String columnAlias) {
        this.columnValue = columnValue;
        this.setColumnAlias(columnAlias);
        this.setType(Type.VIRTUAL);
        return this;
    }

    public ColumnDataBlock setSubQueryColumn(SqlBuilderResult sqlBuilderResult, String columnAlias) {
        this.sqlBuilderResult = sqlBuilderResult;
        this.setColumnAlias(columnAlias);
        this.setType(Type.SUB_QUERY);
        return this;
    }

    public enum Type {
        /**
         * default type
         */
        DEFAULT,
        /**
         * virtual column
         */
        VIRTUAL,
        /**
         * sub query column
         */
        SUB_QUERY,
        /**
         * column handler {@link ColumnHandler}
         */
        HANDLER,
        /**
         * custom column sql
         */
        SQL_PART
    }

    @Override
    public ColumnDataBlock setTableName(String tableName) {
        super.setTableName(tableName);
        return this;
    }

    @Override
    public ColumnDataBlock setTableAlias(String tableAlias) {
        super.setTableAlias(tableAlias);
        return this;
    }

    @Override
    public ColumnDataBlock setColumnName(String columnName) {
        super.setColumnName(columnName);
        return this;
    }

    @Override
    public ColumnDataBlock setColumnAlias(String columnAlias) {
        super.setColumnAlias(columnAlias);
        return this;
    }
}