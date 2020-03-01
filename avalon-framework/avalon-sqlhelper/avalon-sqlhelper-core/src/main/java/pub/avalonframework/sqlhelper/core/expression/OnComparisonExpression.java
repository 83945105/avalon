package pub.avalonframework.sqlhelper.core.expression;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.data.block.OnDataBlock;
import pub.avalonframework.sqlhelper.core.expression.impl.ColumnCallbackComparisonExpressionImpl;
import pub.avalonframework.sqlhelper.core.expression.impl.ColumnComparisonExpressionImpl;
import pub.avalonframework.sqlhelper.core.expression.impl.DataBlockBuilderComparisonExpressionImpl;
import pub.avalonframework.sqlhelper.core.expression.impl.ValueComparisonExpressionImpl;
import pub.avalonframework.sqlhelper.core.expression.lambda.SubQueryComparisonLambdaExpression;
import pub.avalonframework.sqlhelper.core.expression.lambda.impl.SubQueryComparisonLambdaExpressionImpl;
import pub.avalonframework.sqlhelper.core.helper.Helper;

/**
 * @author baichao
 */
public interface OnComparisonExpression<T extends Helper> extends
        ValueComparisonExpression<T>,
        ValueComparisonExpressionImpl<T, OnDataBlock>,
        ColumnComparisonExpression<T>,
        ColumnComparisonExpressionImpl<T, OnDataBlock>,
        ColumnCallbackComparisonExpression<T>,
        ColumnCallbackComparisonExpressionImpl<T>,
        SubQueryComparisonLambdaExpression<T>,
        SubQueryComparisonLambdaExpressionImpl<T, OnDataBlock>,
        DataBlockBuilderComparisonExpression<T>,
        DataBlockBuilderComparisonExpressionImpl<T, OnDataBlock> {

    /**
     * Get sql builder configuration.
     *
     * @return {@link SqlBuilderConfiguration}
     */
    SqlBuilderConfiguration getSqlBuilderConfiguration();

    @Override
    default ComparisonRule getComparisonRule() {
        return getSqlBuilderConfiguration().getDataBlockBuilder().getOnComparisonRule();
    }
}