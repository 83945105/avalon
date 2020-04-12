package pub.avalonframework.security.data.rule;

import pub.avalonframework.database.sql.expression.LogicExpression;
import pub.avalonframework.database.sql.expression.LogicOperator;
import pub.avalonframework.database.sql.rule.ColumnRule;

import java.util.LinkedList;
import java.util.List;

/**
 * @author baichao
 */
public final class ParsedColumnRule extends ColumnRule {

    private String tableName;

    private String tableAlias;

    private List<LogicExpression> logicExpressions = new LinkedList<>();

    public ParsedColumnRule(String tableName, String tableAlias) {
        this.tableName = tableName;
        this.tableAlias = tableAlias;
    }

    @Override
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

    @Override
    public List<LogicExpression> getLogicExpressions() {
        return logicExpressions;
    }
}