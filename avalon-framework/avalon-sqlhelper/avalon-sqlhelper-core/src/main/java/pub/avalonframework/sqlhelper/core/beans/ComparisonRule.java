package pub.avalonframework.sqlhelper.core.beans;

import pub.avalonframework.sqlhelper.core.exception.ComparisonRuleNullException;

/**
 * @author baichao
 */
public enum ComparisonRule {
    /**
     * When the parameter is null,the current rule is invalid.
     */
    NULL_SKIP,
    /**
     * When the parameter is null,throw exception.
     * {@link ComparisonRuleNullException}
     */
    NULL_THROW_EXCEPTION
}