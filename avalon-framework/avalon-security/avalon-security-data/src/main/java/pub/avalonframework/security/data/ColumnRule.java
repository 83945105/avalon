package pub.avalonframework.security.data;

import pub.avalonframework.security.data.expression.LogicExpression;
import pub.avalonframework.security.data.expression.LogicOperator;

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
    public LogicExpression addLogicExpression(LogicOperator logicOperator) {
        LogicExpression logicExpression = new LogicExpression(logicOperator, tableName, tableAlias);
        this.logicExpressions.add(logicExpression);
        return logicExpression;
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public String getTableAlias() {
        return tableAlias;
    }

    @Override
    public List<LogicExpression> getLogicExpressions() {
        return logicExpressions;
    }
}