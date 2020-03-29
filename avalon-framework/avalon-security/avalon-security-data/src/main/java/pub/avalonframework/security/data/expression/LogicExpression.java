package pub.avalonframework.security.data.expression;

import java.util.LinkedList;
import java.util.List;

/**
 * @author baichao
 */
public class LogicExpression {

    private LogicOperator logicOperator;

    private List<PredicateExpression> predicateExpressions = new LinkedList<>();

    private List<LogicExpression> logicExpressions = new LinkedList<>();

    public LogicExpression(LogicOperator logicOperator) {
        this.logicOperator = logicOperator;
    }

    public void addPredicateExpression(PredicateExpression predicateExpression) {
        this.predicateExpressions.add(predicateExpression);
    }

    public void addLogicExpression(LogicExpression logicExpression) {
        this.logicExpressions.add(logicExpression);
    }

    public LogicOperator getLogicOperator() {
        return logicOperator;
    }

    public List<PredicateExpression> getPredicateExpressions() {
        return predicateExpressions;
    }

    public List<LogicExpression> getLogicExpressions() {
        return logicExpressions;
    }
}