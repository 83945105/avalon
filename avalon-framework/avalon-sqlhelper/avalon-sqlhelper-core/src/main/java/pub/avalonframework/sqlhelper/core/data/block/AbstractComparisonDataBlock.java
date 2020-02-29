package pub.avalonframework.sqlhelper.core.data.block;

import pub.avalonframework.sqlhelper.core.data.ColumnType;
import pub.avalonframework.sqlhelper.core.data.ComparisonType;
import pub.avalonframework.sqlhelper.core.data.Type;
import pub.avalonframework.sqlhelper.core.data.ValueType;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;

import java.util.List;

/**
 * @author baichao
 */
public abstract class AbstractComparisonDataBlock<T extends AbstractComparisonDataBlock<T>> extends AbstractDataBlock<T> {

    protected Type type = Type.DEFAULT;

    protected String sqlPart;

    protected ColumnType columnType = ColumnType.DEFAULT;

    // 已经尝试过ColumnHandler[] columnHandlers, 这么做后面解析过于麻烦, 放弃
    protected ColumnHandler columnHandler;

    protected ComparisonType comparisonType = ComparisonType.NONE;

    protected ValueType valueType = ValueType.SINGLE_VALUE;

    protected Object targetValue;

    protected Object targetSecondValue;

    protected SqlBuilderResult targetSubQuery;

    protected String targetSqlPart;

    protected AbstractDataBlock<?> targetDataBlock;

    protected AbstractDataBlock<?> targetSecondDataBlock;

    protected List<AbstractDataBlock<?>> targetMultiDataBlock;

    public AbstractComparisonDataBlock(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias);
    }

    public AbstractComparisonDataBlock(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
    }

    public AbstractComparisonDataBlock(String templateTableName, String templateTableAlias, String sqlPart) {
        super(templateTableName, templateTableAlias, null, null);
        this.setSqlPart(sqlPart);
    }

    public AbstractComparisonDataBlock<T> setSqlPart(String sqlPart) {
        if (sqlPart == null) {
            return this;
        }
        this.type = Type.SQL_PART;
        this.sqlPart = sqlPart;
        return this;
    }

    public AbstractComparisonDataBlock<T> setColumnHandler(ColumnHandler columnHandler) {
        if (columnHandler == null) {
            return this;
        }
        this.columnType = ColumnType.HANDLER;
        this.columnHandler = columnHandler;
        return this;
    }

    public AbstractComparisonDataBlock<T> setTargetNoneValue(ComparisonType comparisonType) {
        if (comparisonType == null) {
            return this;
        }
        this.comparisonType = comparisonType;
        this.valueType = ValueType.NONE_VALUE;
        return this;
    }

    public AbstractComparisonDataBlock<T> setTargetSingleValue(ComparisonType comparisonType, Object targetValue) {
        if (comparisonType == null || targetValue == null) {
            return this;
        }
        this.comparisonType = comparisonType;
        this.valueType = ValueType.SINGLE_VALUE;
        this.targetValue = targetValue;
        return this;
    }

    public AbstractComparisonDataBlock<T> setTargetPairValue(ComparisonType comparisonType, Object targetValue, Object targetSecondValue) {
        if (comparisonType == null || targetValue == null || targetSecondValue == null) {
            return this;
        }
        this.comparisonType = comparisonType;
        this.valueType = ValueType.PAIR_VALUE;
        this.targetValue = targetValue;
        this.targetSecondValue = targetSecondValue;
        return this;
    }

    public AbstractComparisonDataBlock<T> setTargetMultiValue(ComparisonType comparisonType, Object targetValue) {
        if (comparisonType == null || targetValue == null) {
            return this;
        }
        this.comparisonType = comparisonType;
        this.valueType = ValueType.MULTI_VALUE;
        this.targetValue = targetValue;
        return this;
    }

    public AbstractComparisonDataBlock<T> setTargetSubQuery(ComparisonType comparisonType, SqlBuilderResult targetSubQuery) {
        if (comparisonType == null || targetSubQuery == null) {
            return this;
        }
        this.comparisonType = comparisonType;
        this.valueType = ValueType.SUB_QUERY;
        this.targetSubQuery = targetSubQuery;
        return this;
    }

    public AbstractComparisonDataBlock<T> setTargetSqlPart(String targetSqlPart) {
        if (targetSqlPart == null) {
            return this;
        }
        this.comparisonType = ComparisonType.NONE;
        this.valueType = ValueType.SQL_PART;
        this.targetSqlPart = targetSqlPart;
        return this;
    }

    public AbstractComparisonDataBlock<T> setTargetSingleDataBlock(ComparisonType comparisonType, AbstractDataBlock<?> targetDataBlock) {
        if (comparisonType == null || targetDataBlock == null) {
            return this;
        }
        this.comparisonType = comparisonType;
        this.valueType = ValueType.SINGLE_DATA_BLOCK;
        this.targetDataBlock = targetDataBlock;
        return this;
    }

    public AbstractComparisonDataBlock<T> setTargetPairDataBlock(ComparisonType comparisonType, AbstractDataBlock<?> targetDataBlock, AbstractDataBlock<?> targetSecondDataBlock) {
        if (comparisonType == null || targetDataBlock == null || targetSecondDataBlock == null) {
            return this;
        }
        this.comparisonType = comparisonType;
        this.valueType = ValueType.PAIR_DATA_BLOCK;
        this.targetDataBlock = targetDataBlock;
        this.targetSecondDataBlock = targetSecondDataBlock;
        return this;
    }

    public AbstractComparisonDataBlock<T> setTargetMultiDataBlock(ComparisonType comparisonType, List<AbstractDataBlock<?>> targetMultiDataBlock) {
        if (comparisonType == null || targetMultiDataBlock == null) {
            return this;
        }
        this.comparisonType = comparisonType;
        this.valueType = ValueType.MULTI_DATA_BLOCK;
        this.targetMultiDataBlock = targetMultiDataBlock;
        return this;
    }

    public Type getType() {
        return type;
    }

    public String getSqlPart() {
        return sqlPart;
    }

    public AbstractDataBlock<?> getTargetDataBlock() {
        return targetDataBlock;
    }

    public ColumnType getColumnType() {
        return columnType;
    }

    public ColumnHandler getColumnHandler() {
        return columnHandler;
    }

    public ComparisonType getComparisonType() {
        return comparisonType;
    }

    public ValueType getValueType() {
        return valueType;
    }

    public Object getTargetValue() {
        return targetValue;
    }

    public Object getTargetSecondValue() {
        return targetSecondValue;
    }

    public SqlBuilderResult getTargetSubQuery() {
        return targetSubQuery;
    }

    public String getTargetSqlPart() {
        return targetSqlPart;
    }

    public AbstractDataBlock<?> getTargetSecondDataBlock() {
        return targetSecondDataBlock;
    }

    public List<AbstractDataBlock<?>> getTargetMultiDataBlock() {
        return targetMultiDataBlock;
    }
}