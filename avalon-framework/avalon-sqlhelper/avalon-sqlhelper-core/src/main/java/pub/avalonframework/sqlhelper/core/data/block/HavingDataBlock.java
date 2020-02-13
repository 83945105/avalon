package pub.avalonframework.sqlhelper.core.data.block;

import org.springframework.beans.BeanUtils;
import pub.avalonframework.sqlhelper.core.beans.ColumnHandler;
import pub.avalonframework.sqlhelper.core.data.ComparisonType;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;

import java.util.List;

/**
 * @author baichao
 */
public final class HavingDataBlock extends AbstractComparisonDataBlock<HavingDataBlock> {

    public HavingDataBlock(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias);
    }

    public HavingDataBlock(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
    }

    public HavingDataBlock(String templateTableName, String templateTableAlias, String sqlPart) {
        super(templateTableName, templateTableAlias, sqlPart);
    }

    @Override
    public HavingDataBlock setSqlPart(String sqlPart) {
        super.setSqlPart(sqlPart);
        return this;
    }

    @Override
    public HavingDataBlock setColumnHandler(ColumnHandler columnHandler) {
        super.setColumnHandler(columnHandler);
        return this;
    }

    @Override
    public HavingDataBlock setTargetNoneValue(ComparisonType comparisonType) {
        super.setTargetNoneValue(comparisonType);
        return this;
    }

    @Override
    public HavingDataBlock setTargetSingleValue(ComparisonType comparisonType, Object targetValue) {
        super.setTargetSingleValue(comparisonType, targetValue);
        return this;
    }

    @Override
    public HavingDataBlock setTargetPairValue(ComparisonType comparisonType, Object targetValue, Object targetSecondValue) {
        super.setTargetPairValue(comparisonType, targetValue, targetSecondValue);
        return this;
    }

    @Override
    public HavingDataBlock setTargetMultiValue(ComparisonType comparisonType, Object targetValue) {
        super.setTargetMultiValue(comparisonType, targetValue);
        return this;
    }

    @Override
    public HavingDataBlock setTargetSubQuery(ComparisonType comparisonType, SqlBuilderResult targetSubQuery) {
        super.setTargetSubQuery(comparisonType, targetSubQuery);
        return this;
    }

    @Override
    public HavingDataBlock setTargetSqlPart(String targetSqlPart) {
        super.setTargetSqlPart(targetSqlPart);
        return this;
    }

    @Override
    public HavingDataBlock setTargetSingleSqlPartDatum(ComparisonType comparisonType, AbstractDataBlock targetSqlPartDatum) {
        super.setTargetSingleSqlPartDatum(comparisonType, targetSqlPartDatum);
        return this;
    }

    @Override
    public HavingDataBlock setTargetPairSqlPartDatum(ComparisonType comparisonType, AbstractDataBlock targetSqlPartDatum, AbstractDataBlock targetSecondSqlPartDatum) {
        super.setTargetPairSqlPartDatum(comparisonType, targetSqlPartDatum, targetSecondSqlPartDatum);
        return this;
    }

    @Override
    public HavingDataBlock setTargetMultiSqlPartDatum(ComparisonType comparisonType, List<AbstractDataBlock> targetMultiSqlPartDatum) {
        super.setTargetMultiSqlPartDatum(comparisonType, targetMultiSqlPartDatum);
        return this;
    }

    @Override
    public HavingDataBlock setTableName(String tableName) {
        super.setTableName(tableName);
        return this;
    }

    @Override
    public HavingDataBlock setTableAlias(String tableAlias) {
        super.setTableAlias(tableAlias);
        return this;
    }

    @Override
    public HavingDataBlock setColumnName(String columnName) {
        super.setColumnName(columnName);
        return this;
    }

    @Override
    public HavingDataBlock setColumnAlias(String columnAlias) {
        super.setColumnAlias(columnAlias);
        return this;
    }

    public HavingDataBlock getCloneComparisonSqlPartDatum() {
        HavingDataBlock havingDataBlock = new HavingDataBlock(this.getTemplateTableName(), this.getTemplateTableAlias(), this.getTemplateColumnName(), this.getTemplateColumnAlias());
        BeanUtils.copyProperties(this, havingDataBlock);
        return havingDataBlock;
    }
}