package pub.avalonframework.sqlhelper.core.expression;

/**
 * @author baichao
 */
public interface LimitExpression<R> {

    R limit(Long limit);

    R offset(Long offset);
}