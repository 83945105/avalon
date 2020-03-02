package pub.avalonframework.sqlhelper.core.expression.builder;

/**
 * @author baichao
 */
public interface CrudExpressionBuilder<R> extends InsertExpressionBuilder<R>, SelectExpressionBuilder<R>, UpdateExpressionBuilder<R>, DeleteExpressionBuilder<R> {

}