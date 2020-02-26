package pub.avalonframework.sqlhelper.core.expression;

/**
 * @author baichao
 */
public interface GroupByExpression<T> {

    T min();

    T max();

    T count();

    T sum();

    T avg();

    T stddev();

    T variance();
}