package pub.avalonframework.sqlhelper.core.data.block;

import org.springframework.beans.BeanUtils;
import pub.avalonframework.sqlhelper.core.beans.ColumnHandler;
import pub.avalonframework.sqlhelper.core.data.ComparisonType;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;

import java.util.List;

/**
 * @author baichao
 */
public final class OnDataBlock extends AbstractComparisonDataBlock<OnDataBlock> {

    public OnDataBlock(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias);
    }

    public OnDataBlock(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
    }

    public OnDataBlock(String templateTableName, String templateTableAlias, String sqlPart) {
        super(templateTableName, templateTableAlias, sqlPart);
    }

    @Override
    public OnDataBlock setSqlPart(String sqlPart) {
        super.setSqlPart(sqlPart);
        return this;
    }

    @Override
    public OnDataBlock setColumnHandler(ColumnHandler columnHandler) {
        super.setColumnHandler(columnHandler);
        return this;
    }

    @Override
    public OnDataBlock setTargetNoneValue(ComparisonType comparisonType) {
        super.setTargetNoneValue(comparisonType);
        return this;
    }

    @Override
    public OnDataBlock setTargetSingleValue(ComparisonType comparisonType, Object targetValue) {
        super.setTargetSingleValue(comparisonType, targetValue);
        return this;
    }

    @Override
    public OnDataBlock setTargetPairValue(ComparisonType comparisonType, Object targetValue, Object targetSecondValue) {
        super.setTargetPairValue(comparisonType, targetValue, targetSecondValue);
        return this;
    }

    @Override
    public OnDataBlock setTargetMultiValue(ComparisonType comparisonType, Object targetValue) {
        super.setTargetMultiValue(comparisonType, targetValue);
        return this;
    }

    @Override
    public OnDataBlock setTargetSubQuery(ComparisonType comparisonType, SqlBuilderResult targetSubQuery) {
        super.setTargetSubQuery(comparisonType, targetSubQuery);
        return this;
    }

    @Override
    public OnDataBlock setTargetSqlPart(String targetSqlPart) {
        super.setTargetSqlPart(targetSqlPart);
        return this;
    }

    @Override
    public OnDataBlock setTargetSingleDataBlock(ComparisonType comparisonType, AbstractDataBlock<?> targetDataBlock) {
        super.setTargetSingleDataBlock(comparisonType, targetDataBlock);
        return this;
    }

    @Override
    public OnDataBlock setTargetPairDataBlock(ComparisonType comparisonType, AbstractDataBlock<?> targetDataBlock, AbstractDataBlock<?> targetSecondDataBlock) {
        super.setTargetPairDataBlock(comparisonType, targetDataBlock, targetSecondDataBlock);
        return this;
    }

    @Override
    public OnDataBlock setTargetMultiDataBlock(ComparisonType comparisonType, List<AbstractDataBlock<?>> targetMultiDataBlock) {
        super.setTargetMultiDataBlock(comparisonType, targetMultiDataBlock);
        return this;
    }

    @Override
    public OnDataBlock setTableName(String tableName) {
        super.setTableName(tableName);
        return this;
    }

    @Override
    public OnDataBlock setTableAlias(String tableAlias) {
        super.setTableAlias(tableAlias);
        return this;
    }

    @Override
    public OnDataBlock setColumnName(String columnName) {
        super.setColumnName(columnName);
        return this;
    }

    @Override
    public OnDataBlock setColumnAlias(String columnAlias) {
        super.setColumnAlias(columnAlias);
        return this;
    }

    public OnDataBlock getCloneOnDataBlock() {
        OnDataBlock onDataBlock = new OnDataBlock(this.getTemplateTableName(), this.getTemplateTableAlias(), this.getTemplateColumnName(), this.getTemplateColumnAlias());
        BeanUtils.copyProperties(this, onDataBlock);
        return onDataBlock;
    }
}