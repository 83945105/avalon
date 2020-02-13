package pub.avalonframework.sqlhelper.core.rules;

import pub.avalonframework.sqlhelper.core.beans.ComparisonRule;
import pub.avalonframework.sqlhelper.core.data.block.OnDataBlock;
import pub.avalonframework.sqlhelper.core.data.builder.OnSqlPartDatumBuilder;
import pub.avalonframework.sqlhelper.core.helper.Helper;
import pub.avalonframework.sqlhelper.core.rules.impl.*;

/**
 * @author baichao
 */
public interface OnComparisonOperator<T extends Helper> extends BaseComparisonOperator<T>,
        BaseComparisonOperatorImpl<T, OnDataBlock>,
        ToColumnComparisonOperator<T>,
        ToColumnComparisonOperatorImpl<T, OnDataBlock>,
        ToColumnCallbackComparisonOperator<T>,
        ToColumnCallbackComparisonOperatorImpl<T>,
        ToSubQueryComparisonOperator<T>,
        ToSubQueryComparisonOperatorImpl<T, OnDataBlock>,
        ToSqlPartBuilderComparisonOperator<T, OnSqlPartDatumBuilder>,
        ToSqlPartBuilderComparisonOperatorImpl<T, OnDataBlock, OnSqlPartDatumBuilder> {

    @Override
    default ComparisonRule getComparisonRule() {
        return getSqlBuilderConfiguration().getDataBlockBuilder().getOnComparisonRule();
    }
}