package pub.avalonframework.security.data;

import pub.avalonframework.security.data.expression.LogicExpression;
import pub.avalonframework.security.data.expression.LogicOperator;

import java.util.LinkedList;
import java.util.List;

/**
 * @author baichao
 */
public class ColumnRule {

    private String tableName;

    private String tableAlias;

    private List<LogicExpression> logicExpressions = new LinkedList<>();

    public ColumnRule(String tableName, String tableAlias) {
        this.tableName = tableName;
        this.tableAlias = tableAlias;
    }

    public LogicExpression addLogicExpression(LogicOperator logicOperator) {
        LogicExpression logicExpression = new LogicExpression(logicOperator);
        this.logicExpressions.add(logicExpression);
        return logicExpression;
    }

    public String getTableName() {
        return tableName;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<LogicExpression> getLogicExpressions() {
        return logicExpressions;
    }
}