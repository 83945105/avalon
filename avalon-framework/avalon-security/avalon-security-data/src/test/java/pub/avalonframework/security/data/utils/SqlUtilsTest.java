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

        // USER.ID = ROLE.USER_ID
        BinaryComparisonPredicate binaryComparisonPredicate = new BinaryComparisonPredicate();
        ColumnPredicate masterColumnPredicate = new ColumnPredicate("USER", "USER", "ID", "ID");
        binaryComparisonPredicate.setMasterPredicate(masterColumnPredicate);
        binaryComparisonPredicate.setComparisonOperator(ComparisonOperator.EQ);
        ColumnPredicate slaveColumnPredicate = new ColumnPredicate("ROLE", "ROLE", "USER_ID", "USER_ID");
        binaryComparisonPredicate.setSlavePredicate(slaveColumnPredicate);
        predicateExpressions.add(binaryComparisonPredicate);

        // ROLE.ID = '1'
        binaryComparisonPredicate = new BinaryComparisonPredicate();
        masterColumnPredicate = new ColumnPredicate("ROLE","ROLE","ID","ID");
        binaryComparisonPredicate.setMasterPredicate(masterColumnPredicate);
        binaryComparisonPredicate.setComparisonOperator(ComparisonOperator.NEQ);
        ConstantPredicate slaveConstantPredicate = new ConstantPredicate("1");
        binaryComparisonPredicate.setSlavePredicate(slaveConstantPredicate);
        predicateExpressions.add(binaryComparisonPredicate);

        // ROLE.ID IN ( '1', '2' )
        InPredicate inPredicate = new InPredicate();
        masterColumnPredicate = new ColumnPredicate("ROLE","ROLE","ID","ID");
        inPredicate.setPredicate(masterColumnPredicate);
        inPredicate.setComparisonOperator(ComparisonOperator.IN);
        ConstantPredicate constantPredicate1 = new ConstantPredicate("1");
        inPredicate.addValuePredicates(constantPredicate1);
        ConstantPredicate constantPredicate2 = new ConstantPredicate("2");
        inPredicate.addValuePredicates(constantPredicate2);
        predicateExpressions.add(inPredicate);

        SqlUtils.buildSql(sql, args, logicExpressions, LogicOperator.AND, false);
        System.out.println(sql.toString());
    }
}