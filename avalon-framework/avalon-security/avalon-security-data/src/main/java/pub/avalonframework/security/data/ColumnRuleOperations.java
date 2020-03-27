package pub.avalonframework.security.data;

import java.util.List;

/**
 * @author baichao
 */
public interface ColumnRuleOperations {

    LogicExpressionOperations addLogicExpression(LogicExpressionOperations.AndOr andOr);

    String getTableName();

    String getTableAlias();

    List<LogicExpression> getLogicExpressions();
}