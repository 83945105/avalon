package pub.avalonframework.sqlhelper.core.beans;

/**
 * @author baichao
 */
public enum GroupType implements ColumnHandler {
    /**
     * Min
     */
    MIN("min"),
    /**
     * Max
     */
    MAX("max"),
    /**
     * Total
     */
    COUNT("count"),
    /**
     * Sum
     */
    SUM("sum"),
    /**
     * Average
     */
    AVG("avg"),
    /**
     * Standard deviation
     */
    STDDEV("stddev"),
    /**
     * Variance
     */
    VARIANCE("variance");

    private String methodName;

    GroupType(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public String execute(String columnSql) {
        return this.methodName + "(" + columnSql + ")";
    }
}