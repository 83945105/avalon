package pub.avalonframework.sqlhelper.core.expression;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.data.block.WhereDataBlock;
import pub.avalonframework.sqlhelper.core.expression.impl.*;
import pub.avalonframework.sqlhelper.core.helper.Helper;

/**
 * @author baichao
 */
public interface WhereComparisonExpression<T extends Helper> extends
        ValueComparisonExpression<T>,
        ValueComparisonExpressionImpl<T, WhereDataBlock>,
        ColumnComparisonExpression<T>,
        ColumnComparisonExpressionImpl<T, WhereDataBlock>,
        ColumnCallbackComparisonExpression<T>,
        ColumnCallbackComparisonExpressionImpl<T>,
        SubQueryComparisonExpression<T>,
        SubQueryComparisonExpressionImpl<T, WhereDataBlock>,
        DataBlockBuilderComparisonExpressionImpl<T, WhereDataBlock> {

    /**
     * Get sql builder configuration.
     *
     * @return {@link SqlBuilderConfiguration}
     */
    SqlBuilderConfiguration getSqlBuilderConfiguration();

    @Override
    default ComparisonRule getComparisonRule() {
        return getSqlBuilderConfiguration().getDataBlockBuilder().getWhereComparisonRule();
    }
}