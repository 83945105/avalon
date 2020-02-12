package pub.avalonframework.sqlhelper.core.rules;

import pub.avalonframework.sqlhelper.core.beans.ComparisonRule;
import pub.avalonframework.sqlhelper.core.data.WhereDatum;
import pub.avalonframework.sqlhelper.core.data.builder.WhereSqlPartDatumBuilder;
import pub.avalonframework.sqlhelper.core.helper.Helper;
import pub.avalonframework.sqlhelper.core.rules.impl.*;

/**
 * @author baichao
 */
public interface WhereComparisonOperator<T extends Helper> extends BaseComparisonOperator<T>,
        BaseComparisonOperatorImpl<T, WhereDatum>,
        ToColumnComparisonOperator<T>,
        ToColumnComparisonOperatorImpl<T, WhereDatum>,
        ToColumnCallbackComparisonOperator<T>,
        ToColumnCallbackComparisonOperatorImpl<T>,
        ToSubQueryComparisonOperator<T>,
        ToSubQueryComparisonOperatorImpl<T, WhereDatum>,
        ToSqlPartBuilderComparisonOperator<T, WhereSqlPartDatumBuilder>,
        ToSqlPartBuilderComparisonOperatorImpl<T, WhereDatum, WhereSqlPartDatumBuilder> {

    @Override
    default ComparisonRule getComparisonRule() {
        return getSqlBuilderConfiguration().getSqlPartDatumBuilder().getWhereComparisonRule();
    }
}