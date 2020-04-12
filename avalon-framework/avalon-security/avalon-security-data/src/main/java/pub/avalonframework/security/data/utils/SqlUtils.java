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
                sql.append(" and ");
            }
            buildSql(sql, args, predicateExpressions.get(i));
        }
        int i = 0;
        for (PredicateExpression predicateExpression : predicateExpressions) {
            if (i++ > 0) {
                sql.append(" and ");
            }
            buildSql(sql, args, predicateExpression);
        }
        if (logicOperator == LogicOperator.OR && predicateExpressions.size() > 1) {
            sql.append(")");
        }
    }

    public static void buildSql(StringBuilder sql, List<Object> args, PredicateExpression predicateExpression) {
        if (predicateExpression instanceof BinaryComparisonPredicate) {
            buildSql(sql, args, (BinaryComparisonPredicate) predicateExpression);
        } else if (predicateExpression instanceof IsNullPredicate) {
            buildSql(sql, args, (IsNullPredicate) predicateExpression);
        } else if (predicateExpression instanceof LikePredicate) {
            buildSql(sql, args, (LikePredicate) predicateExpression);
        } else if (predicateExpression instanceof InPredicate) {
            buildSql(sql, args, (InPredicate) predicateExpression);
        } else if (predicateExpression instanceof BetweenPredicate) {
            buildSql(sql, args, (BetweenPredicate) predicateExpression);
        } else {
            throw new SqlBuilderException("Unsupported predicateExpression type.");
        }
    }

    public static void buildSql(StringBuilder sql, List<Object> args, BinaryComparisonPredicate binaryComparisonPredicate) {
        buildSql(sql, args, binaryComparisonPredicate.getMasterPredicate());
        sql.append(" ");
        buildSql(sql, args, binaryComparisonPredicate.getComparisonOperator());
        sql.append(" ");
        buildSql(sql, args, binaryComparisonPredicate.getSlavePredicate());
    }

    public static void buildSql(StringBuilder sql, List<Object> args, IsNullPredicate isNullPredicate) {
        buildSql(sql, args, isNullPredicate.getPredicate());
        sql.append(" ");
        buildSql(sql, args, isNullPredicate.getComparisonOperator());
    }

    public static void buildSql(StringBuilder sql, List<Object> args, LikePredicate likePredicate) {
        buildSql(sql, args, likePredicate.getMasterPredicate());
        sql.append(" ");
        buildSql(sql, args, likePredicate.getComparisonOperator());
        sql.append(" ");
        buildSql(sql, args, likePredicate.getSlavePredicate());
    }

    public static void buildSql(StringBuilder sql, List<Object> args, InPredicate inPredicate) {
        buildSql(sql, args, inPredicate.getPredicate());
        sql.append(" ");
        buildSql(sql, args, inPredicate.getComparisonOperator());
        sql.append(" ( ");
        List<Predicate> predicates = inPredicate.getValuePredicates();
        int len = predicates.size();
        for (int i = 0; i < len; i++) {
            if (i > 0) {
                sql.append(", ");
            }
            buildSql(sql, args, predicates.get(i));
        }
        sql.append(" )");
    }

    public static void buildSql(StringBuilder sql, List<Object> args, BetweenPredicate betweenPredicate) {
        buildSql(sql, args, betweenPredicate.getPredicate());
        sql.append(" ");
        buildSql(sql, args, betweenPredicate.getComparisonOperator());
        sql.append(" ");
        buildSql(sql, args, betweenPredicate.getMasterValuePredicate());
        sql.append(" AND ");
        buildSql(sql, args, betweenPredicate.getSlaveValuePredicate());
    }

    public static void buildSql(StringBuilder sql, List<Object> args, LogicOperator logicOperator) {
        sql.append(logicOperator.getSymbol(0));
    }

    public static void buildSql(StringBuilder sql, List<Object> args, ComparisonOperator comparisonOperator) {
        sql.append(comparisonOperator.getSymbol(0));
    }

    public static void buildSql(StringBuilder sql, List<Object> args, Predicate predicate) {
        if (predicate instanceof ColumnPredicate) {
            buildSql(sql, args, (ColumnPredicate) predicate);
        } else if (predicate instanceof ConstantPredicate) {
            buildSql(sql, args, (ConstantPredicate) predicate);
        } else {
            throw new SqlBuilderException("Unsupported predicate type.");
        }
    }

    public static void buildSql(StringBuilder sql, List<Object> args, ColumnPredicate columnPredicate) {
        sql.append(columnPredicate.getTableName())
                .append(".")
                .append(columnPredicate.getColumnName());
    }

    public static void buildSql(StringBuilder sql, List<Object> args, ConstantPredicate constantPredicate) {
        sql.append(constantPredicate.getValueString());
    }
}