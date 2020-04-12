package pub.avalonframework.database.sql.expression;

/**
 * @author baichao
 */
public enum LogicOperator {

    AND(new String[]{"AND", "and"}), OR(new String[]{"OR", "or"});

    private String[] symbols;

    LogicOperator(String[] symbols) {
        this.symbols = symbols;
    }

    public String getSymbol(int index) {
        return symbols[index];
    }

    public static LogicOperator parseLogicOperator(String symbol) {
        if (symbol == null) {
            return null;
        }
        String[] symbols;
        for (LogicOperator each : LogicOperator.values()) {
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
