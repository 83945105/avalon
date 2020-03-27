package pub.avalonframework.security.data;

import java.util.List;

/**
 * @author baichao
 */
public interface LogicExpressionOperations {

    PredicateExpressionOperations addPredicateExpression();

    LogicExpressionOperations addLogicExpression(AndOr andOr);

    AndOr getAndOr();

    String getTableName();

    String getTableAlias();

    List<PredicateExpressionOperations> getPredicateExpressions();

    List<LogicExpressionOperations> getLogicExpressions();

    enum AndOr {

        AND(new String[]{"AND"}), OR(new String[]{"OR"});

        private String[] symbols;

        AndOr(String[] symbols) {
            this.symbols = symbols;
        }

        public static AndOr parseAndOr(String symbol) {
            if (symbol == null) {
                return null;
            }
            String[] symbols;
            for (AndOr each : AndOr.values()) {
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
}