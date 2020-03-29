package pub.avalonframework.security.data;

import pub.avalonframework.security.data.expression.LogicExpression;
import pub.avalonframework.security.data.expression.LogicOperator;

import java.util.List;

/**
 * @author baichao
 */
public interface ColumnRuleOperations {

    LogicExpression addLogicExpression(LogicOperator logicOperator);

    String getTableName();

    String getTableAlias();

    List<LogicExpression> getLogicExpressions();
}