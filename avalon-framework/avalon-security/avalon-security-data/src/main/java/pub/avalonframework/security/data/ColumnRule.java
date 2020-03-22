package pub.avalonframework.security.data;

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

    public LogicExpression addLogicExpression(LogicExpression.AndOr andOr) {
        LogicExpression logicExpression = new LogicExpression(andOr, tableName, tableAlias);
        this.logicExpressions.add(logicExpression);
        return logicExpression;
    }
}