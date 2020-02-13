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
    T equalTo(Object value, ComparisonRule comparisonRule);

    /**
     * Equal to
     *
     * @param value comparison value
     * @return extends {@link Helper} object
     */
    default T equalTo(Object value) {
        return equalTo(value, getComparisonRule());
    }

    /**
     * Not equal to
     *
     * @param value          comparison value
     * @param comparisonRule {@link ComparisonRule}
     * @return extends {@link Helper} object
     */
    T notEqualTo(Object value, ComparisonRule comparisonRule);

    /**
     * Not equal to
     *
     * @param value comparison value
     * @return extends {@link Helper} object
     */
    default T notEqualTo(Object value) {
        return notEqualTo(value, getComparisonRule());
    }

    /**
     * Greater than
     *
     * @param value          comparison value
     * @param comparisonRule {@link ComparisonRule}
     * @return extends {@link Helper} object
     */
    T greaterThan(Object value, ComparisonRule comparisonRule);

    /**
     * Greater than
     *
     * @param value comparison value
     * @return extends {@link Helper} object
     */
    default T greaterThan(Object value) {
        return greaterThan(value, getComparisonRule());
    }

    /**
     * Greater than or equal to
     *
     * @param value          comparison value
     * @param comparisonRule {@link ComparisonRule}
     * @return extends {@link Helper} object
     */
    T greaterThanAndEqualTo(Object value, ComparisonRule comparisonRule);

    /**
     * Greater than or equal to
     *
     * @param value comparison value
     * @return extends {@link Helper} object
     */
    default T greaterThanAndEqualTo(Object value) {
        return greaterThanAndEqualTo(value, getComparisonRule());
    }

    /**
     * Less than
     *
     * @param value          comparison value
     * @param comparisonRule {@link ComparisonRule}
     * @return extends {@link Helper} object
     */
    T lessThan(Object value, ComparisonRule comparisonRule);

    /**
     * Less than
     *
     * @param value comparison value
     * @return extends {@link Helper} object
     */
    default T lessThan(Object value) {
        return lessThan(value, getComparisonRule());
    }

    /**
     * Less than or equal to
     *
     * @param value          comparison value
     * @param comparisonRule {@link ComparisonRule}
     * @return extends {@link Helper} object
     */
    T lessThanAndEqualTo(Object value, ComparisonRule comparisonRule);

    /**
     * Less than or equal to
     *
     * @param value comparison value
     * @return extends {@link Helper} object
     */
    default T lessThanAndEqualTo(Object value) {
        return lessThanAndEqualTo(value, getComparisonRule());
    }

    /**
     * Between ... and ...
     *
     * @param value          comparison start value
     * @param secondValue    comparison end value
     * @param comparisonRule {@link ComparisonRule}
     * @return extends {@link Helper} object
     */
    T between(Object value, Object secondValue, ComparisonRule comparisonRule);

    /**
     * Between ... and ...
     *
     * @param value       comparison start value
     * @param secondValue comparison end value
     * @return extends {@link Helper} object
     */
    default T between(Object value, Object secondValue) {
        return between(value, secondValue, getComparisonRule());
    }

    /**
     * Like
     *
     * @param value          comparison value
     * @param comparisonRule {@link ComparisonRule}
     * @return extends {@link Helper} object
     */
    T like(Object value, ComparisonRule comparisonRule);

    /**
     * Like
     *
     * @param value comparison value
     * @return extends {@link Helper} object
     */
    default T like(Object value) {
        return like(value, getComparisonRule());
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
    T notIn(Object[] values, ComparisonRule comparisonRule);

    /**
     * Not in
     *
     * @param values         comparison values
     * @param comparisonRule {@link ComparisonRule}
     * @return extends {@link Helper} object
     */
    default T notIn(ComparisonRule comparisonRule, Object... values) {
        return notIn(values, comparisonRule);
    }

    /**
     * Not in
     *
     * @param values comparison values
     * @return extends {@link Helper} object
     */
    default T notIn(Object... values) {
        return notIn(getComparisonRule(), values);
    }

    /**
     * Not in
     *
     * @param values         comparison values
     * @param comparisonRule {@link ComparisonRule}
     * @return extends {@link Helper} object
     */
    T notIn(Collection<Object> values, ComparisonRule comparisonRule);

    /**
     * Not in
     *
     * @param values comparison values
     * @return extends {@link Helper} object
     */
    default T notIn(Collection<Object> values) {
        return notIn(values, getComparisonRule());
    }
}