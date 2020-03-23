package pub.avalonframework.security.data;

import java.util.LinkedList;
import java.util.List;

/**
 * @author baichao
 */
public class LogicExpression implements LogicExpressionOperations {

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

    @Override
    public PredicateExpression addPredicateExpression() {
        PredicateExpression predicateExpression = new PredicateExpression(tableName, tableAlias);
        this.predicateExpressions.add(predicateExpression);
        return predicateExpression;
    }

    @Override
    public LogicExpression addLogicExpression(AndOr andOr) {
        LogicExpression logicExpression = new LogicExpression(andOr, tableName, tableAlias);
        this.logicExpressions.add(logicExpression);
        return logicExpression;
    }

    @Override
    public AndOr getAndOr() {
        return andOr;
    }
}