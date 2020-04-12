package pub.avalonframework.database.sql.utils;

import pub.avalonframework.database.sql.expression.*;

import java.util.List;

/**
 * @author baichao
 */
public final class SqlUtils {

    private SqlUtils() {
    }

    public static void buildSql(StringBuilder sql, List<Object> args, List<LogicExpression> logicExpressions, LogicOperator logicOperator, boolean checkBrackets) {
        if (logicExpressions == null || logicExpressions.size() == 0) {
            return;
        }
        int length = sql.length();
        List<PredicateExpression> predicateExpressions;
        List<LogicExpression> childLogicExpressions;
        int i = 0;
        boolean brackets = false;
        for (LogicExpression logicExpression : logicExpressions) {
            predicateExpressions = logicExpression.getPredicateExpressions();
            childLogicExpressions = logicExpression.getLogicExpressions();
            if (predicateExpressions.size() < 1 && childLogicExpressions.size() < 1) {
                throw new SqlBuilderException("LogicExpression is empty.");
            }
            sql.append(" ");
            if (i++ > 0) {
                buildSql(sql, args, logicOperator);
                sql.append(" ");
            }
            if (predicateExpressions.size() > 0) {
                buildSql(sql, args, predicateExpressions, logicExpression.getLogicOperator());
                continue;
            }
            if (childLogicExpressions.size() > 0) {
                buildSql(sql, args, childLogicExpressions, LogicOperator.AND, true);
            }
        }
        if (!checkBrackets) {
            return;
        }
        brackets = brackets || logicOperator == LogicOperator.OR && i > 1;
        if (!brackets) {
            return;
        }
        sql.insert(length, "(").append(")");
    }

    public static void buildSql(StringBuilder sql, List<Object> args, List<PredicateExpression> predicateExpressions, LogicOperator logicOperator) {
        int len = predicateExpressions.size();
        if (len < 1) {
            return;
        }
        if (logicOperator == LogicOperator.OR && len > 1) {
            // OR ( XX = xx AND XX = xx )
            sql.append("(");
        }
        for (int i = 0; i < len; i++) {
            if (i++ > 0) {
                sql.append(" AND ");
            }
            buildSql(sql, args, predicateExpressions.get(i));
        }
        int i = 0;
        for (PredicateExpression predicateExpression : predicateExpressions) {
            if (i++ > 0) {
                sql.append(" AND ");
            }
            buildSql(sql, args, predicateExpression);
        }
        if (logicOperator == LogicOperator.OR && predicateExpressions.size() > 1) {
            sql.append(")");
        }
    }

    public static void buildSql(StringBuilder sql, List<Object> args, PredicateExpression predicateExpression) {
    }

    public static void buildSql(StringBuilder sql, List<Object> args, LogicOperator logicOperator) {
        sql.append(logicOperator.getSymbol(0));
    }

    public static void buildSql(StringBuilder sql, List<Object> args, ComparisonOperator comparisonOperator) {
        sql.append(comparisonOperator.getSymbol(0));
    }

}