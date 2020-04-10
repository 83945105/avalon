package pub.avalonframework.security.data.utils;

import org.junit.jupiter.api.Test;
import pub.avalonframework.security.data.expression.*;

import java.util.LinkedList;
import java.util.List;

/**
 * @author baichao
 */
public class SqlUtilsTest {

    @Test
    void test() {
        StringBuilder sql = new StringBuilder();
        List<Object> args = new LinkedList<>();
        List<LogicExpression> logicExpressions = new LinkedList<>();
        LogicExpression logicExpression = new LogicExpression(LogicOperator.AND);
        logicExpressions.add(logicExpression);
        List<PredicateExpression> predicateExpressions = new LinkedList<>();
        logicExpression.setPredicateExpressions(predicateExpressions);
        BinaryComparisonPredicate binaryComparisonPredicate = new BinaryComparisonPredicate();

        ColumnPredicate masterPredicate = new ColumnPredicate("USER", "USER", "ID", "ID");
        binaryComparisonPredicate.setMasterPredicate(masterPredicate);
        binaryComparisonPredicate.setComparisonOperator(ComparisonOperator.EQ);
//        ColumnPredicate masterPredicate = new ColumnPredicate("", "", "", "");
//        binaryComparisonPredicate.setSlavePredicate();

        predicateExpressions.add(binaryComparisonPredicate);
        SqlUtils.buildSql(sql, args, logicExpressions, LogicOperator.AND, false);
        System.out.println(sql.toString());
    }
}