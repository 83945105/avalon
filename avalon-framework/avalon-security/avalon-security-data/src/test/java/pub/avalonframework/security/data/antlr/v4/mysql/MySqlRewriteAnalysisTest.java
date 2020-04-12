package pub.avalonframework.security.data.antlr.v4.mysql;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pub.avalonframework.database.sql.expression.ComparisonOperator;
import pub.avalonframework.database.sql.expression.LogicExpression;
import pub.avalonframework.database.sql.expression.LogicOperator;
import pub.avalonframework.database.sql.expression.PredicateExpression;
import pub.avalonframework.security.data.antlr.SqlRewriteBuilder;
import pub.avalonframework.security.data.expression.*;
import pub.avalonframework.security.data.rule.ParsedColumnRule;
import pub.avalonframework.security.data.rule.ParsedTableRule;
import pub.avalonframework.security.data.rule.RuleStore;
import pub.avalonframework.security.data.rule.SqlRewrite;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Mysql重写分析测试
 *
 * @author baichao
 */
public class MySqlRewriteAnalysisTest {

    SqlRewriteBuilder sqlRewriteBuilder = new SqlRewriteBuilder(new HikariDataSource() {{
        setDriverClassName("com.mysql.cj.jdbc.Driver");
        setJdbcUrl("jdbc:mysql://localhost:3306/sql_rewrite?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false");
        setUsername("root");
        setPassword("19910405");
    }});

    private Map<String, ParsedTableRule> getRuntimeTableRule(String sql) {
        SqlRewrite sqlRewrite = sqlRewriteBuilder.build(sql);
        sqlRewrite.run();
        RuleStore ruleStore = sqlRewrite.getRuleStore();
        return ruleStore.getRuntimeTableRuleMap();
    }

    @Test
    void test01() {
        Map<String, ParsedTableRule> ruleMap = getRuntimeTableRule("SELECT * FROM USER");
        Assertions.assertNotNull(ruleMap);
        Assertions.assertEquals(1, ruleMap.size());
        ParsedTableRule rule = ruleMap.get("USER");
        Assertions.assertNotNull(rule);
        Assertions.assertEquals(ParsedTableRule.Type.REAL, rule.getType());
        Assertions.assertEquals("USER", rule.getTableName());
        Assertions.assertEquals("USER", rule.getTableAlias());
        Assertions.assertEquals(0, rule.getOnRules().size());
        Assertions.assertEquals(0, rule.getWhereRules().size());
    }

    @Test
    void test02() {
        Map<String, ParsedTableRule> ruleMap = getRuntimeTableRule("SELECT * FROM USER U");
        Assertions.assertNotNull(ruleMap);
        Assertions.assertEquals(1, ruleMap.size());
        ParsedTableRule rule = ruleMap.get("U");
        Assertions.assertNotNull(rule);
        Assertions.assertEquals(ParsedTableRule.Type.REAL, rule.getType());
        Assertions.assertEquals("USER", rule.getTableName());
        Assertions.assertEquals("U", rule.getTableAlias());
        Assertions.assertEquals(0, rule.getOnRules().size());
        Assertions.assertEquals(0, rule.getWhereRules().size());
    }

    @Test
    void test03() {
        Map<String, ParsedTableRule> ruleMap = getRuntimeTableRule("SELECT USER.* FROM USER INNER JOIN ROLE ON ROLE.ID = ''");
        Assertions.assertNotNull(ruleMap);
        ParsedTableRule userRule = ruleMap.get("USER");
        Assertions.assertNotNull(userRule);
        Assertions.assertEquals(ParsedTableRule.Type.REAL, userRule.getType());
        Assertions.assertEquals("USER", userRule.getTableName());
        Assertions.assertEquals("USER", userRule.getTableAlias());
        Assertions.assertEquals(0, userRule.getOnRules().size());
        Assertions.assertEquals(0, userRule.getWhereRules().size());
        ParsedTableRule roleRule = ruleMap.get("ROLE");
        Assertions.assertNotNull(roleRule);
        Assertions.assertEquals(ParsedTableRule.Type.REAL, roleRule.getType());
        Assertions.assertEquals("ROLE", roleRule.getTableName());
        Assertions.assertEquals("ROLE", roleRule.getTableAlias());
        List<ParsedColumnRule> roleRuleOnRules = roleRule.getOnRules();
        Assertions.assertEquals(1, roleRuleOnRules.size());
        Assertions.assertEquals(0, roleRule.getWhereRules().size());
        ParsedColumnRule roleRuleOnRule = roleRuleOnRules.get(0);
        Assertions.assertNotNull(roleRuleOnRule);
        Assertions.assertEquals("ROLE", roleRuleOnRule.getTableName());
        Assertions.assertEquals("ROLE", roleRuleOnRule.getTableAlias());
        List<LogicExpression> logicExpressions = roleRuleOnRule.getLogicExpressions();
        Assertions.assertEquals(1, logicExpressions.size());
        LogicExpression logicExpression = logicExpressions.get(0);
        Assertions.assertNotNull(logicExpression);
        Assertions.assertEquals(LogicOperator.AND, logicExpression.getLogicOperator());
        Assertions.assertEquals(0, logicExpression.getLogicExpressions().size());
        List<PredicateExpression> predicateExpressions = logicExpression.getPredicateExpressions();
        Assertions.assertEquals(1, predicateExpressions.size());
        PredicateExpression predicateExpression = predicateExpressions.get(0);
        Assertions.assertNotNull(predicateExpression);
        Assertions.assertTrue(predicateExpression instanceof BinaryComparisonPredicate);
        BinaryComparisonPredicate binaryComparisonPredicate = (BinaryComparisonPredicate) predicateExpression;
        Predicate masterPredicate = binaryComparisonPredicate.getMasterPredicate();
        Assertions.assertNotNull(masterPredicate);
        Assertions.assertTrue(masterPredicate instanceof ColumnPredicate);
        ColumnPredicate columnPredicate = (ColumnPredicate) masterPredicate;
        Assertions.assertEquals("ROLE", columnPredicate.getTableName());
        Assertions.assertEquals("ROLE", columnPredicate.getTableAlias());
        Assertions.assertEquals("ID", columnPredicate.getColumnName());
        Assertions.assertEquals("ID", columnPredicate.getColumnAlias());
        Assertions.assertEquals(ComparisonOperator.EQ, binaryComparisonPredicate.getComparisonOperator());
        Predicate slavePredicate = binaryComparisonPredicate.getSlavePredicate();
        Assertions.assertNotNull(slavePredicate);
        Assertions.assertTrue(slavePredicate instanceof ConstantPredicate);
        ConstantPredicate constantPredicate = (ConstantPredicate) slavePredicate;
        Assertions.assertEquals("''", constantPredicate.getValueString());
    }

    @Test
    void test04() {
        Map<String, ParsedTableRule> ruleMap = getRuntimeTableRule("SELECT U.* FROM USER U INNER JOIN ROLE R ON R.ID = ''");
        Assertions.assertNotNull(ruleMap);
        ParsedTableRule userRule = ruleMap.get("U");
        Assertions.assertNotNull(userRule);
        Assertions.assertEquals(ParsedTableRule.Type.REAL, userRule.getType());
        Assertions.assertEquals("USER", userRule.getTableName());
        Assertions.assertEquals("U", userRule.getTableAlias());
        Assertions.assertEquals(0, userRule.getOnRules().size());
        Assertions.assertEquals(0, userRule.getWhereRules().size());
        ParsedTableRule roleRule = ruleMap.get("R");
        Assertions.assertNotNull(roleRule);
        Assertions.assertEquals(ParsedTableRule.Type.REAL, roleRule.getType());
        Assertions.assertEquals("ROLE", roleRule.getTableName());
        Assertions.assertEquals("R", roleRule.getTableAlias());
        List<ParsedColumnRule> roleRuleOnRules = roleRule.getOnRules();
        Assertions.assertEquals(1, roleRuleOnRules.size());
        Assertions.assertEquals(0, roleRule.getWhereRules().size());
        ParsedColumnRule roleRuleOnRule = roleRuleOnRules.get(0);
        Assertions.assertNotNull(roleRuleOnRule);
        Assertions.assertEquals("ROLE", roleRuleOnRule.getTableName());
        Assertions.assertEquals("R", roleRuleOnRule.getTableAlias());
        List<LogicExpression> logicExpressions = roleRuleOnRule.getLogicExpressions();
        Assertions.assertEquals(1, logicExpressions.size());
        LogicExpression logicExpression = logicExpressions.get(0);
        Assertions.assertNotNull(logicExpression);
        Assertions.assertEquals(LogicOperator.AND, logicExpression.getLogicOperator());
        Assertions.assertEquals(0, logicExpression.getLogicExpressions().size());
        List<PredicateExpression> predicateExpressions = logicExpression.getPredicateExpressions();
        Assertions.assertEquals(1, predicateExpressions.size());
        PredicateExpression predicateExpression = predicateExpressions.get(0);
        Assertions.assertNotNull(predicateExpression);
        Assertions.assertTrue(predicateExpression instanceof BinaryComparisonPredicate);
        BinaryComparisonPredicate binaryComparisonPredicate = (BinaryComparisonPredicate) predicateExpression;
        Predicate masterPredicate = binaryComparisonPredicate.getMasterPredicate();
        Assertions.assertNotNull(masterPredicate);
        Assertions.assertTrue(masterPredicate instanceof ColumnPredicate);
        ColumnPredicate columnPredicate = (ColumnPredicate) masterPredicate;
        Assertions.assertEquals("ROLE", columnPredicate.getTableName());
        Assertions.assertEquals("R", columnPredicate.getTableAlias());
        Assertions.assertEquals("ID", columnPredicate.getColumnName());
        Assertions.assertEquals("ID", columnPredicate.getColumnAlias());
        Assertions.assertEquals(ComparisonOperator.EQ, binaryComparisonPredicate.getComparisonOperator());
        Predicate slavePredicate = binaryComparisonPredicate.getSlavePredicate();
        Assertions.assertNotNull(slavePredicate);
        Assertions.assertTrue(slavePredicate instanceof ConstantPredicate);
        ConstantPredicate constantPredicate = (ConstantPredicate) slavePredicate;
        Assertions.assertEquals("''", constantPredicate.getValueString());
    }

    @Test
    void test05() {
        Map<String, ParsedTableRule> ruleMap = getRuntimeTableRule("SELECT USER.* FROM USER INNER JOIN ROLE ON ROLE.ID = '' AND ROLE.USER_ID = USER.ID");
        Assertions.assertNotNull(ruleMap);
        ParsedTableRule userRule = ruleMap.get("USER");
        Assertions.assertNotNull(userRule);
        Assertions.assertEquals(ParsedTableRule.Type.REAL, userRule.getType());
        Assertions.assertEquals("USER", userRule.getTableName());
        Assertions.assertEquals("USER", userRule.getTableAlias());
        Assertions.assertEquals(0, userRule.getOnRules().size());
        Assertions.assertEquals(0, userRule.getWhereRules().size());
        ParsedTableRule roleRule = ruleMap.get("ROLE");
        Assertions.assertNotNull(roleRule);
        Assertions.assertEquals(ParsedTableRule.Type.REAL, roleRule.getType());
        Assertions.assertEquals("ROLE", roleRule.getTableName());
        Assertions.assertEquals("ROLE", roleRule.getTableAlias());
        List<ParsedColumnRule> roleRuleOnRules = roleRule.getOnRules();
        Assertions.assertEquals(1, roleRuleOnRules.size());
        Assertions.assertEquals(0, roleRule.getWhereRules().size());
        ParsedColumnRule roleRuleOnRule = roleRuleOnRules.get(0);
        Assertions.assertNotNull(roleRuleOnRule);
        Assertions.assertEquals("ROLE", roleRuleOnRule.getTableName());
        Assertions.assertEquals("ROLE", roleRuleOnRule.getTableAlias());
        List<LogicExpression> logicExpressions = roleRuleOnRule.getLogicExpressions();
        Assertions.assertEquals(1, logicExpressions.size());
        LogicExpression logicExpression = logicExpressions.get(0);
        Assertions.assertNotNull(logicExpression);
        Assertions.assertEquals(LogicOperator.AND, logicExpression.getLogicOperator());
        Assertions.assertEquals(0, logicExpression.getLogicExpressions().size());
        List<PredicateExpression> predicateExpressions = logicExpression.getPredicateExpressions();
        Assertions.assertEquals(2, predicateExpressions.size());
        PredicateExpression predicateExpression = predicateExpressions.get(0);
        Assertions.assertNotNull(predicateExpression);
        Assertions.assertTrue(predicateExpression instanceof BinaryComparisonPredicate);
        BinaryComparisonPredicate binaryComparisonPredicate = (BinaryComparisonPredicate) predicateExpression;
        Predicate masterPredicate = binaryComparisonPredicate.getMasterPredicate();
        Assertions.assertNotNull(masterPredicate);
        Assertions.assertTrue(masterPredicate instanceof ColumnPredicate);
        ColumnPredicate columnPredicate = (ColumnPredicate) masterPredicate;
        Assertions.assertEquals("ROLE", columnPredicate.getTableName());
        Assertions.assertEquals("ROLE", columnPredicate.getTableAlias());
        Assertions.assertEquals("ID", columnPredicate.getColumnName());
        Assertions.assertEquals("ID", columnPredicate.getColumnAlias());
        Assertions.assertEquals(ComparisonOperator.EQ, binaryComparisonPredicate.getComparisonOperator());
        Predicate slavePredicate = binaryComparisonPredicate.getSlavePredicate();
        Assertions.assertNotNull(slavePredicate);
        Assertions.assertTrue(slavePredicate instanceof ConstantPredicate);
        ConstantPredicate constantPredicate = (ConstantPredicate) slavePredicate;
        Assertions.assertEquals("''", constantPredicate.getValueString());
        predicateExpression = predicateExpressions.get(1);
        Assertions.assertNotNull(predicateExpression);
        Assertions.assertTrue(predicateExpression instanceof BinaryComparisonPredicate);
        binaryComparisonPredicate = (BinaryComparisonPredicate) predicateExpression;
        masterPredicate = binaryComparisonPredicate.getMasterPredicate();
        Assertions.assertNotNull(masterPredicate);
        Assertions.assertTrue(masterPredicate instanceof ColumnPredicate);
        columnPredicate = (ColumnPredicate) masterPredicate;
        Assertions.assertEquals("ROLE", columnPredicate.getTableName());
        Assertions.assertEquals("ROLE", columnPredicate.getTableAlias());
        Assertions.assertEquals("USER_ID", columnPredicate.getColumnName());
        Assertions.assertEquals("USER_ID", columnPredicate.getColumnAlias());
        Assertions.assertEquals(ComparisonOperator.EQ, binaryComparisonPredicate.getComparisonOperator());
        slavePredicate = binaryComparisonPredicate.getSlavePredicate();
        Assertions.assertNotNull(slavePredicate);
        Assertions.assertTrue(slavePredicate instanceof ColumnPredicate);
        columnPredicate = (ColumnPredicate) slavePredicate;
        Assertions.assertEquals("USER", columnPredicate.getTableName());
        Assertions.assertEquals("USER", columnPredicate.getTableAlias());
        Assertions.assertEquals("ID", columnPredicate.getColumnName());
        Assertions.assertEquals("ID", columnPredicate.getColumnAlias());
    }

    @Test
    void test06() {
        Map<String, ParsedTableRule> ruleMap = getRuntimeTableRule("SELECT USER.* FROM ( SELECT * FROM USER ) USER INNER JOIN ROLE ON ROLE.ID = ''");
        Assertions.assertNotNull(ruleMap);

        ParsedTableRule userRule = ruleMap.get("USER");
        Assertions.assertNotNull(userRule);
        Assertions.assertEquals(ParsedTableRule.Type.VIRTUAL, userRule.getType());
        Assertions.assertEquals("USER", userRule.getTableName());
        Assertions.assertEquals("USER", userRule.getTableAlias());
        Assertions.assertEquals(0, userRule.getOnRules().size());
        Assertions.assertEquals(0, userRule.getWhereRules().size());

        ParsedTableRule roleRule = ruleMap.get("ROLE");
        Assertions.assertNotNull(roleRule);
        Assertions.assertEquals(ParsedTableRule.Type.REAL, roleRule.getType());
        Assertions.assertEquals("ROLE", roleRule.getTableName());
        Assertions.assertEquals("ROLE", roleRule.getTableAlias());
        List<ParsedColumnRule> roleRuleOnRules = roleRule.getOnRules();
        Assertions.assertEquals(1, roleRuleOnRules.size());
        Assertions.assertEquals(0, roleRule.getWhereRules().size());
        ParsedColumnRule roleRuleOnRule = roleRuleOnRules.get(0);
        Assertions.assertNotNull(roleRuleOnRule);
        Assertions.assertEquals("ROLE", roleRuleOnRule.getTableName());
        Assertions.assertEquals("ROLE", roleRuleOnRule.getTableAlias());
        List<LogicExpression> logicExpressions = roleRuleOnRule.getLogicExpressions();
        Assertions.assertEquals(1, logicExpressions.size());
        LogicExpression logicExpression = logicExpressions.get(0);
        Assertions.assertNotNull(logicExpression);
        Assertions.assertEquals(LogicOperator.AND, logicExpression.getLogicOperator());
        Assertions.assertEquals(0, logicExpression.getLogicExpressions().size());
        List<PredicateExpression> predicateExpressions = logicExpression.getPredicateExpressions();
        Assertions.assertEquals(1, predicateExpressions.size());
        PredicateExpression predicateExpression = predicateExpressions.get(0);
        Assertions.assertNotNull(predicateExpression);
        Assertions.assertTrue(predicateExpression instanceof BinaryComparisonPredicate);
        BinaryComparisonPredicate binaryComparisonPredicate = (BinaryComparisonPredicate) predicateExpression;
        Predicate masterPredicate = binaryComparisonPredicate.getMasterPredicate();
        Assertions.assertNotNull(masterPredicate);
        Assertions.assertTrue(masterPredicate instanceof ColumnPredicate);
        ColumnPredicate columnPredicate = (ColumnPredicate) masterPredicate;
        Assertions.assertEquals("ROLE", columnPredicate.getTableName());
        Assertions.assertEquals("ROLE", columnPredicate.getTableAlias());
        Assertions.assertEquals("ID", columnPredicate.getColumnName());
        Assertions.assertEquals("ID", columnPredicate.getColumnAlias());
        Assertions.assertEquals(ComparisonOperator.EQ, binaryComparisonPredicate.getComparisonOperator());
        Predicate slavePredicate = binaryComparisonPredicate.getSlavePredicate();
        Assertions.assertNotNull(slavePredicate);
        Assertions.assertTrue(slavePredicate instanceof ConstantPredicate);
        ConstantPredicate constantPredicate = (ConstantPredicate) slavePredicate;
        Assertions.assertEquals("''", constantPredicate.getValueString());

        Map<String, RuleStore> subRuleStoreMap = userRule.getSubRuleStoreMap();
        Assertions.assertNotNull(subRuleStoreMap);
        Assertions.assertEquals(1, subRuleStoreMap.size());

        RuleStore subRuleStore = subRuleStoreMap.get("USER");
        Assertions.assertNotNull(subRuleStore);
        ruleMap = subRuleStore.getRuntimeTableRuleMap();
        Assertions.assertNotNull(ruleMap);
        Assertions.assertEquals(1, ruleMap.size());
        ParsedTableRule rule = ruleMap.get("USER");
        Assertions.assertNotNull(rule);
        Assertions.assertEquals(ParsedTableRule.Type.REAL, rule.getType());
        Assertions.assertEquals("USER", rule.getTableName());
        Assertions.assertEquals("USER", rule.getTableAlias());
        Assertions.assertEquals(0, rule.getOnRules().size());
        Assertions.assertEquals(0, rule.getWhereRules().size());
    }

    @Test
    void test07() {
        Map<String, ParsedTableRule> ruleMap = getRuntimeTableRule("SELECT U.*, ( SELECT ID FROM USER LIMIT 1 ) subQuery FROM ( SELECT * FROM USER ) U INNER JOIN ( SELECT * FROM ROLE ) R ON R.ID = U.ID");
        Assertions.assertNotNull(ruleMap);

        ParsedTableRule userRule = ruleMap.get("U");
        Assertions.assertNotNull(userRule);
        Assertions.assertEquals(ParsedTableRule.Type.VIRTUAL, userRule.getType());
        Assertions.assertEquals("U", userRule.getTableName());
        Assertions.assertEquals("U", userRule.getTableAlias());
        Assertions.assertEquals(0, userRule.getOnRules().size());
        Assertions.assertEquals(0, userRule.getWhereRules().size());

        ParsedTableRule roleRule = ruleMap.get("R");
        Assertions.assertNotNull(roleRule);
        Assertions.assertEquals(ParsedTableRule.Type.VIRTUAL, roleRule.getType());
        Assertions.assertEquals("R", roleRule.getTableName());
        Assertions.assertEquals("R", roleRule.getTableAlias());
        List<ParsedColumnRule> roleRuleOnRules = roleRule.getOnRules();
        Assertions.assertEquals(1, roleRuleOnRules.size());
        Assertions.assertEquals(0, roleRule.getWhereRules().size());
        ParsedColumnRule roleRuleOnRule = roleRuleOnRules.get(0);
        Assertions.assertNotNull(roleRuleOnRule);
        Assertions.assertEquals("R", roleRuleOnRule.getTableName());
        Assertions.assertEquals("R", roleRuleOnRule.getTableAlias());
        List<LogicExpression> logicExpressions = roleRuleOnRule.getLogicExpressions();
        Assertions.assertEquals(1, logicExpressions.size());
        LogicExpression logicExpression = logicExpressions.get(0);
        Assertions.assertNotNull(logicExpression);
        Assertions.assertEquals(LogicOperator.AND, logicExpression.getLogicOperator());
        List<PredicateExpression> predicateExpressions = logicExpression.getPredicateExpressions();
        Assertions.assertEquals(1, predicateExpressions.size());
        PredicateExpression predicateExpression = predicateExpressions.get(0);
        Assertions.assertNotNull(predicateExpression);
        Assertions.assertTrue(predicateExpression instanceof BinaryComparisonPredicate);
        BinaryComparisonPredicate binaryComparisonPredicate = (BinaryComparisonPredicate) predicateExpression;
        Predicate masterPredicate = binaryComparisonPredicate.getMasterPredicate();
        Assertions.assertNotNull(masterPredicate);
        Assertions.assertTrue(masterPredicate instanceof ColumnPredicate);
        ColumnPredicate columnPredicate = (ColumnPredicate) masterPredicate;
        Assertions.assertEquals("R", columnPredicate.getTableName());
        Assertions.assertEquals("R", columnPredicate.getTableAlias());
        Assertions.assertEquals("ID", columnPredicate.getColumnName());
        Assertions.assertEquals("ID", columnPredicate.getColumnAlias());
        Assertions.assertEquals(ComparisonOperator.EQ, binaryComparisonPredicate.getComparisonOperator());
        Predicate slavePredicate = binaryComparisonPredicate.getSlavePredicate();
        Assertions.assertNotNull(slavePredicate);
        Assertions.assertTrue(slavePredicate instanceof ColumnPredicate);
        columnPredicate = (ColumnPredicate) slavePredicate;
        Assertions.assertEquals("U", columnPredicate.getTableName());
        Assertions.assertEquals("U", columnPredicate.getTableAlias());
        Assertions.assertEquals("ID", columnPredicate.getColumnName());
        Assertions.assertEquals("ID", columnPredicate.getColumnAlias());

        Map<String, RuleStore> subRuleStoreMap = userRule.getSubRuleStoreMap();
        Assertions.assertNotNull(subRuleStoreMap);
        Assertions.assertEquals(3, subRuleStoreMap.size());

        RuleStore subRuleStore = subRuleStoreMap.get("U");
        Assertions.assertNotNull(subRuleStore);
        ruleMap = subRuleStore.getRuntimeTableRuleMap();
        Assertions.assertNotNull(ruleMap);
        Assertions.assertEquals(1, ruleMap.size());
        ParsedTableRule rule = ruleMap.get("USER");
        Assertions.assertNotNull(rule);
        Assertions.assertEquals(ParsedTableRule.Type.REAL, rule.getType());
        Assertions.assertEquals("USER", rule.getTableName());
        Assertions.assertEquals("USER", rule.getTableAlias());
        Assertions.assertEquals(0, rule.getOnRules().size());
        Assertions.assertEquals(0, rule.getWhereRules().size());

        subRuleStore = subRuleStoreMap.get("R");
        Assertions.assertNotNull(subRuleStore);
        ruleMap = subRuleStore.getRuntimeTableRuleMap();
        Assertions.assertNotNull(ruleMap);
        Assertions.assertEquals(1, ruleMap.size());
        rule = ruleMap.get("ROLE");
        Assertions.assertNotNull(rule);
        Assertions.assertEquals(ParsedTableRule.Type.REAL, rule.getType());
        Assertions.assertEquals("ROLE", rule.getTableName());
        Assertions.assertEquals("ROLE", rule.getTableAlias());
        Assertions.assertEquals(0, rule.getOnRules().size());
        Assertions.assertEquals(0, rule.getWhereRules().size());

        subRuleStoreMap.remove("U");
        subRuleStoreMap.remove("R");
        subRuleStore = subRuleStoreMap.get(subRuleStoreMap.keySet().iterator().next());
        Assertions.assertNotNull(subRuleStore);
        ruleMap = subRuleStore.getRuntimeTableRuleMap();
        Assertions.assertNotNull(ruleMap);
        Assertions.assertEquals(1, ruleMap.size());
        rule = ruleMap.get("USER");
        Assertions.assertNotNull(rule);
        Assertions.assertEquals(ParsedTableRule.Type.REAL, rule.getType());
        Assertions.assertEquals("USER", rule.getTableName());
        Assertions.assertEquals("USER", rule.getTableAlias());
        Assertions.assertEquals(0, rule.getOnRules().size());
        Assertions.assertEquals(0, rule.getWhereRules().size());
    }

    @Test
    void test08() {
        Map<String, ParsedTableRule> ruleMap = getRuntimeTableRule("SELECT U.*, ( SELECT ID FROM USER LIMIT 1 ) subQuery FROM ( SELECT * FROM USER ) U INNER JOIN ( SELECT * FROM ROLE ) R ON R.ID = ( SELECT ID FROM RESOURCE LIMIT 1 )");
        Assertions.assertNotNull(ruleMap);

        ParsedTableRule userRule = ruleMap.get("U");
        Assertions.assertNotNull(userRule);
        Assertions.assertEquals(ParsedTableRule.Type.VIRTUAL, userRule.getType());
        Assertions.assertEquals("U", userRule.getTableName());
        Assertions.assertEquals("U", userRule.getTableAlias());
        Assertions.assertEquals(0, userRule.getOnRules().size());
        Assertions.assertEquals(0, userRule.getWhereRules().size());

        ParsedTableRule roleRule = ruleMap.get("R");
        Assertions.assertNotNull(roleRule);
        Assertions.assertEquals(ParsedTableRule.Type.VIRTUAL, roleRule.getType());
        Assertions.assertEquals("R", roleRule.getTableName());
        Assertions.assertEquals("R", roleRule.getTableAlias());
        List<ParsedColumnRule> roleRuleOnRules = roleRule.getOnRules();
        Assertions.assertEquals(1, roleRuleOnRules.size());
        Assertions.assertEquals(0, roleRule.getWhereRules().size());
        ParsedColumnRule roleRuleOnRule = roleRuleOnRules.get(0);
        Assertions.assertNotNull(roleRuleOnRule);
        Assertions.assertEquals("R", roleRuleOnRule.getTableName());
        Assertions.assertEquals("R", roleRuleOnRule.getTableAlias());
        List<LogicExpression> logicExpressions = roleRuleOnRule.getLogicExpressions();
        Assertions.assertEquals(1, logicExpressions.size());
        LogicExpression logicExpression = logicExpressions.get(0);
        Assertions.assertNotNull(logicExpression);
        Assertions.assertEquals(LogicOperator.AND, logicExpression.getLogicOperator());
        List<PredicateExpression> predicateExpressions = logicExpression.getPredicateExpressions();
        Assertions.assertEquals(1, predicateExpressions.size());
        PredicateExpression predicateExpression = predicateExpressions.get(0);
        Assertions.assertNotNull(predicateExpression);
        Assertions.assertTrue(predicateExpression instanceof BinaryComparisonPredicate);
        BinaryComparisonPredicate binaryComparisonPredicate = (BinaryComparisonPredicate) predicateExpression;
        Predicate masterPredicate = binaryComparisonPredicate.getMasterPredicate();
        Assertions.assertNotNull(masterPredicate);
        Assertions.assertTrue(masterPredicate instanceof ColumnPredicate);
        ColumnPredicate columnPredicate = (ColumnPredicate) masterPredicate;
        Assertions.assertEquals("R", columnPredicate.getTableName());
        Assertions.assertEquals("R", columnPredicate.getTableAlias());
        Assertions.assertEquals("ID", columnPredicate.getColumnName());
        Assertions.assertEquals("ID", columnPredicate.getColumnAlias());
        Assertions.assertEquals(ComparisonOperator.EQ, binaryComparisonPredicate.getComparisonOperator());
        Predicate slavePredicate = binaryComparisonPredicate.getSlavePredicate();
        Assertions.assertNotNull(slavePredicate);
        Assertions.assertTrue(slavePredicate instanceof SubqueryPredicate);
        SubqueryPredicate subqueryPredicate = (SubqueryPredicate) slavePredicate;
        //TODO 暂不对 subqueryPredicate 进行校验

        Map<String, RuleStore> subRuleStoreMap = userRule.getSubRuleStoreMap();
        Assertions.assertNotNull(subRuleStoreMap);
        Assertions.assertEquals(4, subRuleStoreMap.size());

        RuleStore subRuleStore = subRuleStoreMap.get("U");
        Assertions.assertNotNull(subRuleStore);
        ruleMap = subRuleStore.getRuntimeTableRuleMap();
        Assertions.assertNotNull(ruleMap);
        Assertions.assertEquals(1, ruleMap.size());
        ParsedTableRule rule = ruleMap.get("USER");
        Assertions.assertNotNull(rule);
        Assertions.assertEquals(ParsedTableRule.Type.REAL, rule.getType());
        Assertions.assertEquals("USER", rule.getTableName());
        Assertions.assertEquals("USER", rule.getTableAlias());
        Assertions.assertEquals(0, rule.getOnRules().size());
        Assertions.assertEquals(0, rule.getWhereRules().size());

        subRuleStore = subRuleStoreMap.get("R");
        Assertions.assertNotNull(subRuleStore);
        ruleMap = subRuleStore.getRuntimeTableRuleMap();
        Assertions.assertNotNull(ruleMap);
        Assertions.assertEquals(1, ruleMap.size());
        rule = ruleMap.get("ROLE");
        Assertions.assertNotNull(rule);
        Assertions.assertEquals(ParsedTableRule.Type.REAL, rule.getType());
        Assertions.assertEquals("ROLE", rule.getTableName());
        Assertions.assertEquals("ROLE", rule.getTableAlias());
        Assertions.assertEquals(0, rule.getOnRules().size());
        Assertions.assertEquals(0, rule.getWhereRules().size());

        subRuleStoreMap.remove("U");
        subRuleStoreMap.remove("R");

        Iterator<String> iterator = subRuleStoreMap.keySet().iterator();

        //TODO 暂不校验 iterator 元素
    }

    @Test
    void test09() {
        Map<String, ParsedTableRule> ruleMap = getRuntimeTableRule("SELECT * FROM USER WHERE ( ID = '1' OR ID = '2' ) OR ID = '3'");
        Assertions.assertNotNull(ruleMap);
    }
}