package pub.avalonframework.sqlhelper.core.expression.builder;

/**
 * @author baichao
 */
public interface DeleteExpressionBuilder<R> extends
        JoinExpressionBuilder<R>,
        OnExpressionBuilder<R>,
        WhereExpressionBuilder<R> {

}