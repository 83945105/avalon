package pub.avalonframework.sqlhelper.core.rules;

import pub.avalonframework.sqlhelper.core.beans.ComparisonRule;
import pub.avalonframework.sqlhelper.core.data.block.WhereDataBlock;
import pub.avalonframework.sqlhelper.core.data.builder.WhereSqlPartDatumBuilder;
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
        ToSqlPartBuilderComparisonOperator<T, WhereSqlPartDatumBuilder>,
        ToSqlPartBuilderComparisonOperatorImpl<T, WhereDataBlock, WhereSqlPartDatumBuilder> {

    @Override
    default ComparisonRule getComparisonRule() {
        return getSqlBuilderConfiguration().getDataBlockBuilder().getWhereComparisonRule();
    }
}