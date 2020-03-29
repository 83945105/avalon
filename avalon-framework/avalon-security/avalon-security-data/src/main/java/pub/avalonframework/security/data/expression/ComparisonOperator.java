package pub.avalonframework.security.data.expression;

/**
 * @author baichao
 */
public enum ComparisonOperator {

    /**
     * None
     */
    NONE(null),
    /**
     * Is null
     */
    IS_NULL(null),
    /**
     * Is not null
     */
    IS_NOT_NULL(null),
    /**
     * Equal to
     */
    EQ(new String[]{"="}),
    /**
     * Not equal to
     */
    NEQ(new String[]{"!=", "<>"}),
    /**
     * Greater than
     */
    GT(new String[]{">"}),
    /**
     * Greater than or equal to
     */
    GTE(new String[]{">="}),
    /**
     * Less than
     */
    LT(new String[]{"<"}),
    /**
     * Less than or equal to
     */
    LTE(new String[]{"<="}),
    /**
     * Between ... and ...
     */
    BT(new String[]{"BETWEEN", "between"}),
    /**
     * Like
     */
    LK(new String[]{"LIKE", "like"}),
    /**
     * In
     */
    IN(new String[]{"IN", "in"}),
    /**
     * Not in
     */
    NIN(new String[]{"NOT IN", "not in"});

    private String[] symbols;

    ComparisonOperator(String[] symbols) {
        this.symbols = symbols;
    }

    public static ComparisonOperator parseComparison(String symbol) {
        if (symbol == null) {
            return null;
        }
        String[] symbols;
        for (ComparisonOperator each : ComparisonOperator.values()) {
            symbols = each.symbols;
            if (symbols == null || symbols.length == 0) {
                continue;
            }
            for (String str : symbols) {
                if (symbol.equals(str)) {
                    return each;
                }
            }
        }
        return null;
    }
}