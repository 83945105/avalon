package pub.avalonframework.database.sql.rule;

import pub.avalonframework.database.sql.expression.LogicExpression;
import pub.avalonframework.database.sql.expression.LogicOperator;

import java.util.LinkedList;
import java.util.List;

/**
 * @author baichao
 */
public class ColumnRule {

    private List<LogicExpression> logicExpressions = new LinkedList<>();

    public LogicExpression addLogicExpression(LogicOperator logicOperator) {
        LogicExpression logicExpression = new LogicExpression(logicOperator);
        this.logicExpressions.add(logicExpression);
        return logicExpression;
    }

    public List<LogicExpression> getLogicExpressions() {
        return logicExpressions;
    }
}