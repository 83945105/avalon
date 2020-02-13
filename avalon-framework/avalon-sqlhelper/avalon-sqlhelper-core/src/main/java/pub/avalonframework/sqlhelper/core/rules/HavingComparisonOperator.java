package pub.avalonframework.sqlhelper.core.rules;

import pub.avalonframework.sqlhelper.core.beans.ComparisonRule;
import pub.avalonframework.sqlhelper.core.data.block.HavingDataBlock;
import pub.avalonframework.sqlhelper.core.data.block.builder.HavingDataBlockBuilder;
import pub.avalonframework.sqlhelper.core.helper.Helper;
import pub.avalonframework.sqlhelper.core.rules.impl.*;

/**
 * @author baichao
 */
public interface HavingComparisonOperator<T extends Helper> extends BaseComparisonOperator<T>,
        BaseComparisonOperatorImpl<T, HavingDataBlock>,
        ToColumnComparisonOperator<T>,
        ToColumnComparisonOperatorImpl<T, HavingDataBlock>,
        ToColumnCallbackComparisonOperator<T>,
        ToColumnCallbackComparisonOperatorImpl<T>,
        ToSubQueryComparisonOperator<T>,
        ToSubQueryComparisonOperatorImpl<T, HavingDataBlock>,
        ToDataBlockBuilderComparisonOperator<T, HavingDataBlockBuilder>,
        ToDataBlockBuilderComparisonOperatorImpl<T, HavingDataBlock, HavingDataBlockBuilder> {

    @Override
    default ComparisonRule getComparisonRule() {
        return getSqlBuilderConfiguration().getDataBlockBuilder().getHavingComparisonRule();
    }
}