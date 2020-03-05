package pub.avalonframework.sqlhelper.core.data.block;

/**
 * @author baichao
 */
public enum ComparisonValueType {

    /**
     * None value
     */
    NONE_VALUE,
    /**
     * Single value
     */
    SINGLE_VALUE,
    /**
     * Pair value
     */
    PAIR_VALUE,
    /**
     * Multi value
     */
    MULTI_VALUE,
    /**
     * Sub query value
     */
    SUB_QUERY,
    /**
     * Custom sql value
     */
    SQL_PART,
    /**
     * Single data block
     */
    SINGLE_DATA_BLOCK,
    /**
     * Pair data block
     */
    PAIR_DATA_BLOCK,
    /**
     * Multi data block
     */
    MULTI_DATA_BLOCK
}