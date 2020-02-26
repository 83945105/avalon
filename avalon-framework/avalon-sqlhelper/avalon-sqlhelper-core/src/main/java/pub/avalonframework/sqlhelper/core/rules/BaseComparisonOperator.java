package pub.avalonframework.sqlhelper.core.rules;

import pub.avalonframework.sqlhelper.core.beans.ComparisonRule;
import pub.avalonframework.sqlhelper.core.helper.Helper;

import java.util.Collection;

/**
 * @author baichao
 */
public interface BaseComparisonOperator<T> {

    /**
     * Get comparison rule
     *
     * @return {@link ComparisonRule}
     */
    ComparisonRule getComparisonRule();

    /**
     * Custom sql
     *
     * @param targetSqlPart sql part
     * @return extends {@link Helper} object
     */
    T sqlPart(String targetSqlPart);

    /**
     * Is null
     *
     * @return extends {@link Helper} object
     */
    T isNull();

    /**
     * Is not null
     *
     * @return extends {@link Helper} object
     */
    T isNotNull();

    /**
     * Equal to
     *
     * @param value          comparison value
     * @param comparisonRule {@link ComparisonRule}
     * @return extends {@link Helper} object
     */
    T eq(Object value, ComparisonRule comparisonRule);

    /**
     * Equal to
     *
     * @param value comparison value
     * @return extends {@link Helper} object
     */
    default T eq(Object value) {
        return eq(value, getComparisonRule());
    }

    /**
     * Not equal to
     *
     * @param value          comparison value
     * @param comparisonRule {@link ComparisonRule}
     * @return extends {@link Helper} object
     */
    T neq(Object value, ComparisonRule comparisonRule);

    /**
     * Not equal to
     *
     * @param value comparison value
     * @return extends {@link Helper} object
     */
    default T neq(Object value) {
        return neq(value, getComparisonRule());
    }

    /**
     * Greater than
     *
     * @param value          comparison value
     * @param comparisonRule {@link ComparisonRule}
     * @return extends {@link Helper} object
     */
    T gt(Object value, ComparisonRule comparisonRule);

    /**
     * Greater than
     *
     * @param value comparison value
     * @return extends {@link Helper} object
     */
    default T gt(Object value) {
        return gt(value, getComparisonRule());
    }

    /**
     * Greater than or equal to
     *
     * @param value          comparison value
     * @param comparisonRule {@link ComparisonRule}
     * @return extends {@link Helper} object
     */
    T gte(Object value, ComparisonRule comparisonRule);

    /**
     * Greater than or equal to
     *
     * @param value comparison value
     * @return extends {@link Helper} object
     */
    default T gte(Object value) {
        return gte(value, getComparisonRule());
    }

    /**
     * Less than
     *
     * @param value          comparison value
     * @param comparisonRule {@link ComparisonRule}
     * @return extends {@link Helper} object
     */
    T lt(Object value, ComparisonRule comparisonRule);

    /**
     * Less than
     *
     * @param value comparison value
     * @return extends {@link Helper} object
     */
    default T lt(Object value) {
        return lt(value, getComparisonRule());
    }

    /**
     * Less than or equal to
     *
     * @param value          comparison value
     * @param comparisonRule {@link ComparisonRule}
     * @return extends {@link Helper} object
     */
    T lte(Object value, ComparisonRule comparisonRule);

    /**
     * Less than or equal to
     *
     * @param value comparison value
     * @return extends {@link Helper} object
     */
    default T lte(Object value) {
        return lte(value, getComparisonRule());
    }

    /**
     * Between ... and ...
     *
     * @param value          comparison start value
     * @param secondValue    comparison end value
     * @param comparisonRule {@link ComparisonRule}
     * @return extends {@link Helper} object
     */
    T bt(Object value, Object secondValue, ComparisonRule comparisonRule);

    /**
     * Between ... and ...
     *
     * @param value       comparison start value
     * @param secondValue comparison end value
     * @return extends {@link Helper} object
     */
    default T bt(Object value, Object secondValue) {
        return bt(value, secondValue, getComparisonRule());
    }

    /**
     * Like
     *
     * @param value          comparison value
     * @param comparisonRule {@link ComparisonRule}
     * @return extends {@link Helper} object
     */
    T lk(Object value, ComparisonRule comparisonRule);

    /**
     * Like
     *
     * @param value comparison value
     * @return extends {@link Helper} object
     */
    default T lk(Object value) {
        return lk(value, getComparisonRule());
    }

    /**
     * In
     *
     * @param values         comparison values
     * @param comparisonRule {@link ComparisonRule}
     * @return extends {@link Helper} object
     */
    T in(Object[] values, ComparisonRule comparisonRule);

    /**
     * In
     *
     * @param values         comparison values
     * @param comparisonRule {@link ComparisonRule}
     * @return extends {@link Helper} object
     */
    default T in(ComparisonRule comparisonRule, Object... values) {
        return in(values, comparisonRule);
    }

    /**
     * In
     *
     * @param values comparison values
     * @return extends {@link Helper} object
     */
    default T in(Object... values) {
        return in(getComparisonRule(), values);
    }

    /**
     * In
     *
     * @param values         comparison values
     * @param comparisonRule {@link ComparisonRule}
     * @return extends {@link Helper} object
     */
    T in(Collection<Object> values, ComparisonRule comparisonRule);

    /**
     * In
     *
     * @param values comparison values
     * @return extends {@link Helper} object
     */
    default T in(Collection<Object> values) {
        return in(values, getComparisonRule());
    }

    /**
     * Not in
     *
     * @param values         comparison values
     * @param comparisonRule {@link ComparisonRule}
     * @return extends {@link Helper} object
     */
    T nin(Object[] values, ComparisonRule comparisonRule);

    /**
     * Not in
     *
     * @param values         comparison values
     * @param comparisonRule {@link ComparisonRule}
     * @return extends {@link Helper} object
     */
    default T nin(ComparisonRule comparisonRule, Object... values) {
        return nin(values, comparisonRule);
    }

    /**
     * Not in
     *
     * @param values comparison values
     * @return extends {@link Helper} object
     */
    default T nin(Object... values) {
        return nin(getComparisonRule(), values);
    }

    /**
     * Not in
     *
     * @param values         comparison values
     * @param comparisonRule {@link ComparisonRule}
     * @return extends {@link Helper} object
     */
    T nin(Collection<Object> values, ComparisonRule comparisonRule);

    /**
     * Not in
     *
     * @param values comparison values
     * @return extends {@link Helper} object
     */
    default T nin(Collection<Object> values) {
        return nin(values, getComparisonRule());
    }
}