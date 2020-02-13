package pub.avalonframework.sqlhelper.core.rules;

import pub.avalonframework.sqlhelper.core.beans.ComparisonRule;
import pub.avalonframework.sqlhelper.core.data.block.WhereDataBlock;
import pub.avalonframework.sqlhelper.core.data.block.builder.WhereDataBlockBuilder;
import pub.avalonframework.sqlhelper.core.helper.Helper;
import pub.avalonframework.sqlhelper.core.rules.impl.*;

/**
 * @author baichao
 */
public interface WhereComparisonOperator<T extends Helper> extends BaseComparisonOperator<T>,
        BaseComparisonOperatorImpl<T, WhereDataBlock>,
        ToColumnComparisonOperator<T>,
        ToColumnComparisonOperatorImpl<T, WhereDataBlock>,
        ToColumnCallbackComparisonOperator<T>,
        ToColumnCallbackComparisonOperatorImpl<T>,
        ToSubQueryComparisonOperator<T>,
        ToSubQueryComparisonOperatorImpl<T, WhereDataBlock>,
        ToSqlPartBuilderComparisonOperator<T, WhereDataBlockBuilder>,
        ToSqlPartBuilderComparisonOperatorImpl<T, WhereDataBlock, WhereDataBlockBuilder> {

    @Override
    default ComparisonRule getComparisonRule() {
        return getSqlBuilderConfiguration().getDataBlockBuilder().getWhereComparisonRule();
    }
}