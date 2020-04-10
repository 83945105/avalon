package pub.avalonframework.security.data.utils;

import pub.avalonframework.security.data.expression.*;

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
        int i = 0;
        boolean brackets = false;
        for (LogicExpression logicExpression : logicExpressions) {
            predicateExpressions = logicExpression.getPredicateExpressions();
            List<LogicExpression> childLogicExpressions = logicExpression.getLogicExpressions();
            if (predicateExpressions != null && predicateExpressions.size() > 0) {
                switch (logicExpression.getLogicOperator()) {
                    case AND:
                        if (i++ > 0) {
                            sql.append(" and ");
                        }
                        buildSql(sql, args, predicateExpressions, LogicOperator.AND);
                        continue;
                    case OR:
                        if (i++ > 0) {
                            sql.append(" or ");
                            brackets = checkBrackets;
                        }
                        buildSql(sql, args, predicateExpressions, LogicOperator.OR);
                        continue;
                    default:
                        throw new SqlBuilderException("Unsupported logicOperator type.");
                }
            } else if (childLogicExpressions != null && childLogicExpressions.size() > 0) {
                switch (logicExpression.getLogicOperator()) {
                    case AND:
                        if (i++ > 0) {
                            sql.append(" and ");
                        }
                        buildSql(sql, args, childLogicExpressions, LogicOperator.AND, true);
                        continue;
                    case OR:
                        if (i++ > 0) {
                            sql.append(" or ");
                            brackets = checkBrackets;
                        }
                        buildSql(sql, args, childLogicExpressions, LogicOperator.OR, true);
                        continue;
                    default:
                        throw new SqlBuilderException("Unsupported logicOperator type.");
                }
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
        if (predicateExpressions == null || predicateExpressions.size() == 0) {
            return;
        }
        if (logicOperator == LogicOperator.OR && predicateExpressions.size() > 1) {
            sql.append("(");
        }
        int i = 0;
        for (PredicateExpression predicateExpression : predicateExpressions) {
            if (i++ > 0) {
                sql.append(" and ");
            }
            buildSql(sql, predicateExpression);
        }
        if (logicOperator == LogicOperator.OR && predicateExpressions.size() > 1) {
            sql.append(")");
        }
    }

    public static void buildSql(StringBuilder sql, PredicateExpression predicateExpression) {
        if (predicateExpression instanceof BinaryComparisonPredicate) {
            buildSql(sql, (BinaryComparisonPredicate) predicateExpression);
        } else if (predicateExpression instanceof IsNullPredicate) {
            buildSql(sql, (IsNullPredicate) predicateExpression);
        } else if (predicateExpression instanceof LikePredicate) {
            buildSql(sql, (LikePredicate) predicateExpression);
        } else if (predicateExpression instanceof InPredicate) {
            buildSql(sql, (InPredicate) predicateExpression);
        } else if (predicateExpression instanceof BetweenPredicate) {
            buildSql(sql, (BetweenPredicate) predicateExpression);
        } else {
            throw new SqlBuilderException("Unsupported predicateExpression type.");
        }
    }

    public static void buildSql(StringBuilder sql, BinaryComparisonPredicate binaryComparisonPredicate) {

    }

    public static void buildSql(StringBuilder sql, IsNullPredicate isNullPredicate) {

    }

    public static void buildSql(StringBuilder sql, LikePredicate likePredicate) {

    }

    public static void buildSql(StringBuilder sql, InPredicate inPredicate) {

    }

    public static void buildSql(StringBuilder sql, BetweenPredicate betweenPredicate) {

    }
}