package pub.avalonframework.security.data;

import java.util.LinkedList;
import java.util.List;

/**
 * @author baichao
 */
public class LogicExpression {

    private AndOr andOr;

    private String tableName;

    private String tableAlias;

    private List<PredicateExpression> predicateExpressions = new LinkedList<>();

    private List<LogicExpression> logicExpressions = new LinkedList<>();

    public LogicExpression(AndOr andOr, String tableName, String tableAlias) {
        this.andOr = andOr;
        this.tableName = tableName;
        this.tableAlias = tableAlias;
    }

    public PredicateExpression addPredicateExpression() {
        PredicateExpression predicateExpression = new PredicateExpression(tableName, tableAlias);
        this.predicateExpressions.add(predicateExpression);
        return predicateExpression;
    }

    public LogicExpression addLogicExpression(AndOr andOr) {
        LogicExpression logicExpression = new LogicExpression(andOr, tableName, tableAlias);
        this.logicExpressions.add(logicExpression);
        return logicExpression;
    }

    public AndOr getAndOr() {
        return andOr;
    }

    public enum AndOr {

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