package pub.avalonframework.sqlhelper.core.data;

/**
 * @author baichao
 */
public enum ComparisonType {

    /**
     * None
     */
    NONE,
    /**
     * Is null
     */
    IS_NULL,
    /**
     * Is not null
     */
    IS_NOT_NULL,
    /**
     * Equal to
     */
    EQUAL,
    /**
     * Not equal to
     */
    NOT_EQUAL,
    /**
     * Greater than
     */
    GREATER,
    /**
     * Greater than or equal to
     */
    GREATER_EQUAL,
    /**
     * Less than
     */
    LESS,
    /**
     * Less than or equal to
     */
    LESS_EQUAL,
    /**
     * Between ... and ...
     */
    BETWEEN,
    /**
     * Like
     */
    LIKE,
    /**
     * In
     */
    IN,
    /**
     * Not in
     */
    NOT_IN
}