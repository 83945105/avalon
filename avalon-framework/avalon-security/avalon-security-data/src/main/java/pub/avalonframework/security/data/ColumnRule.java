package pub.avalonframework.security.data;

import java.util.LinkedList;
import java.util.List;

/**
 * @author baichao
 */
public class ColumnRule implements ColumnRuleOperations {

    private String tableName;

    private String tableAlias;

    private List<LogicExpression> logicExpressions = new LinkedList<>();

    public ColumnRule(String tableName, String tableAlias) {
        this.tableName = tableName;
        this.tableAlias = tableAlias;
    }

    @Override
    public LogicExpression addLogicExpression(LogicExpressionOperations.AndOr andOr) {
        LogicExpression logicExpression = new LogicExpression(andOr, tableName, tableAlias);
        this.logicExpressions.add(logicExpression);
        return logicExpression;
    }
}