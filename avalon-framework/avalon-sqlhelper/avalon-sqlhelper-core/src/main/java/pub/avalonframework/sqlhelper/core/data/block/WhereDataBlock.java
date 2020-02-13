package pub.avalonframework.sqlhelper.core.data.block;

import org.springframework.beans.BeanUtils;
import pub.avalonframework.sqlhelper.core.beans.ColumnHandler;
import pub.avalonframework.sqlhelper.core.data.ComparisonType;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;

import java.util.List;

/**
 * @author baichao
 */
public final class WhereDataBlock extends AbstractComparisonDataBlock<WhereDataBlock> {

    public WhereDataBlock(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias);
    }

    public WhereDataBlock(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
    }

    public WhereDataBlock(String templateTableName, String templateTableAlias, String sqlPart) {
        super(templateTableName, templateTableAlias, sqlPart);
    }

    @Override
    public WhereDataBlock setSqlPart(String sqlPart) {
        super.setSqlPart(sqlPart);
        return this;
    }

    @Override
    public WhereDataBlock setColumnHandler(ColumnHandler columnHandler) {
        super.setColumnHandler(columnHandler);
        return this;
    }

    @Override
    public WhereDataBlock setTargetNoneValue(ComparisonType comparisonType) {
        super.setTargetNoneValue(comparisonType);
        return this;
    }

    @Override
    public WhereDataBlock setTargetSingleValue(ComparisonType comparisonType, Object targetValue) {
        super.setTargetSingleValue(comparisonType, targetValue);
        return this;
    }

    @Override
    public WhereDataBlock setTargetPairValue(ComparisonType comparisonType, Object targetValue, Object targetSecondValue) {
        super.setTargetPairValue(comparisonType, targetValue, targetSecondValue);
        return this;
    }

    @Override
    public WhereDataBlock setTargetMultiValue(ComparisonType comparisonType, Object targetValue) {
        super.setTargetMultiValue(comparisonType, targetValue);
        return this;
    }

    @Override
    public WhereDataBlock setTargetSubQuery(ComparisonType comparisonType, SqlBuilderResult targetSubQuery) {
        super.setTargetSubQuery(comparisonType, targetSubQuery);
        return this;
    }

    @Override
    public WhereDataBlock setTargetSqlPart(String targetSqlPart) {
        super.setTargetSqlPart(targetSqlPart);
        return this;
    }

    @Override
    public WhereDataBlock setTargetSingleSqlPartDatum(ComparisonType comparisonType, AbstractDataBlock targetSqlPartDatum) {
        super.setTargetSingleSqlPartDatum(comparisonType, targetSqlPartDatum);
        return this;
    }

    @Override
    public WhereDataBlock setTargetPairSqlPartDatum(ComparisonType comparisonType, AbstractDataBlock targetSqlPartDatum, AbstractDataBlock targetSecondSqlPartDatum) {
        super.setTargetPairSqlPartDatum(comparisonType, targetSqlPartDatum, targetSecondSqlPartDatum);
        return this;
    }

    @Override
    public WhereDataBlock setTargetMultiSqlPartDatum(ComparisonType comparisonType, List<AbstractDataBlock> targetMultiSqlPartDatum) {
        super.setTargetMultiSqlPartDatum(comparisonType, targetMultiSqlPartDatum);
        return this;
    }

    @Override
    public WhereDataBlock setTableName(String tableName) {
        super.setTableName(tableName);
        return this;
    }

    @Override
    public WhereDataBlock setTableAlias(String tableAlias) {
        super.setTableAlias(tableAlias);
        return this;
    }

    @Override
    public WhereDataBlock setColumnName(String columnName) {
        super.setColumnName(columnName);
        return this;
    }

    @Override
    public WhereDataBlock setColumnAlias(String columnAlias) {
        super.setColumnAlias(columnAlias);
        return this;
    }

    public WhereDataBlock getCloneComparisonSqlPartDatum() {
        WhereDataBlock whereDatum = new WhereDataBlock(this.getTemplateTableName(), this.getTemplateTableAlias(), this.getTemplateColumnName(), this.getTemplateColumnAlias());
        BeanUtils.copyProperties(this, whereDatum);
        return whereDatum;
    }
}