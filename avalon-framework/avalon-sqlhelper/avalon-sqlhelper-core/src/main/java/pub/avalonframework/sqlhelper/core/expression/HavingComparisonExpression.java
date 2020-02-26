package pub.avalonframework.sqlhelper.core.expression;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.beans.ComparisonRule;
import pub.avalonframework.sqlhelper.core.data.block.HavingDataBlock;
import pub.avalonframework.sqlhelper.core.data.block.builder.HavingDataBlockBuilder;
import pub.avalonframework.sqlhelper.core.expression.impl.*;
import pub.avalonframework.sqlhelper.core.helper.Helper;

/**
 * @author baichao
 */
public interface HavingComparisonExpression<T extends Helper> extends
        ValueComparisonExpression<T>,
        ValueComparisonExpressionImpl<T, HavingDataBlock>,
        ColumnComparisonExpression<T>,
        ColumnComparisonExpressionImpl<T, HavingDataBlock>,
        ColumnCallbackComparisonExpression<T>,
        ColumnCallbackComparisonExpressionImpl<T>,
        SubQueryComparisonExpression<T>,
        SubQueryComparisonExpressionImpl<T, HavingDataBlock>,
        DataBlockBuilderComparisonExpression<T, HavingDataBlockBuilder>,
        DataBlockBuilderComparisonExpressionImpl<T, HavingDataBlock, HavingDataBlockBuilder> {

    /**
     * Get sql builder configuration.
     *
     * @return {@link SqlBuilderConfiguration}
     */
    SqlBuilderConfiguration getSqlBuilderConfiguration();

    @Override
    default ComparisonRule getComparisonRule() {
        return getSqlBuilderConfiguration().getDataBlockBuilder().getHavingComparisonRule();
    }
}