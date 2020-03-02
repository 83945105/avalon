package pub.avalonframework.sqlhelper.core.expression;

/**
 * @author baichao
 */
public interface CrudExpression<R> extends InsertExpression<R>, SelectExpression<R>, UpdateExpression<R>, DeleteExpression<R> {

}