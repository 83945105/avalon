package pub.avalonframework.security.data;

/**
 * @author baichao
 */
public enum ComparisonType {

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
    EQUAL(new String[]{"="}),
    /**
     * Not equal to
     */
    NOT_EQUAL(new String[]{"!=", "<>"}),
    /**
     * Greater than
     */
    GREATER(new String[]{">"}),
    /**
     * Greater than or equal to
     */
    GREATER_EQUAL(new String[]{">="}),
    /**
     * Less than
     */
    LESS(new String[]{"<"}),
    /**
     * Less than or equal to
     */
    LESS_EQUAL(new String[]{"<="}),
    /**
     * Between ... and ...
     */
    BETWEEN(new String[]{"BETWEEN", "between"}),
    /**
     * Like
     */
    LIKE(new String[]{"LIKE", "like"}),
    /**
     * In
     */
    IN(new String[]{"IN", "in"}),
    /**
     * Not in
     */
    NOT_IN(new String[]{"NOT IN", "not in"});

    private String[] symbols;

    ComparisonType(String[] symbols) {
        this.symbols = symbols;
    }

    public static ComparisonType parseComparison(String symbol) {
        if (symbol == null) {
            return null;
        }
        String[] symbols;
        for (ComparisonType each : ComparisonType.values()) {
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