package pub.avalonframework.sqlhelper.core.expression.lambda;

import pub.avalonframework.sqlhelper.core.helper.ColumnHelper;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.SelectSqlBuilderResult;

/**
 * @author baichao
 */
@FunctionalInterface
public interface SubQueryColumnLambdaCallable<TC extends ColumnHelper<TC>> {

    SelectSqlBuilderResult apply(TC parentTable);
}