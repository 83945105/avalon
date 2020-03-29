package pub.avalonframework.security.data.antlr.v4.mysql;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pub.avalonframework.security.data.*;
import pub.avalonframework.security.data.antlr.SqlRewriteBuilder;

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

    private Map<String, TableRuleOperations> getRuntimeTableRule(String sql) {
        SqlRewrite sqlRewrite = sqlRewriteBuilder.build(sql);
        sqlRewrite.run();
        RuleStore ruleStore = sqlRewrite.getRuleStore();
        return ruleStore.getRuntimeTableRuleMap();
    }

    @Test
    void test01() {
        Map<String, TableRuleOperations> ruleMap = getRuntimeTableRule("SELECT * FROM USER");
        Assertions.assertNotNull(ruleMap);
        Assertions.assertEquals(1, ruleMap.size());
        TableRuleOperations rule = ruleMap.get("USER");
        Assertions.assertNotNull(rule);
        Assertions.assertEquals(TableRuleOperations.Type.REAL, rule.getType());
        Assertions.assertEquals("USER", rule.getTableName());
        Assertions.assertEquals("USER", rule.getTableAlias());
        Assertions.assertEquals(0, rule.getOnRules().size());
        Assertions.assertEquals(0, rule.getWhereRules().size());
    }

    @Test
    void test02() {
        Map<String, TableRuleOperations> ruleMap = getRuntimeTableRule("SELECT * FROM USER U");
        Assertions.assertNotNull(ruleMap);
        Assertions.assertEquals(1, ruleMap.size());
        TableRuleOperations rule = ruleMap.get("U");
        Assertions.assertNotNull(rule);
        Assertions.assertEquals(TableRuleOperations.Type.REAL, rule.getType());
        Assertions.assertEquals("USER", rule.getTableName());
        Assertions.assertEquals("U", rule.getTableAlias());
        Assertions.assertEquals(0, rule.getOnRules().size());
        Assertions.assertEquals(0, rule.getWhereRules().size());
    }

    @Test
    void test03() {
        Map<String, TableRuleOperations> ruleMap = getRuntimeTableRule("SELECT USER.* FROM USER INNER JOIN ROLE ON ROLE.ID = ''");
        Assertions.assertNotNull(ruleMap);
        TableRuleOperations userRule = ruleMap.get("USER");
        Assertions.assertNotNull(userRule);
        Assertions.assertEquals(TableRuleOperations.Type.REAL, userRule.getType());
        Assertions.assertEquals("USER", userRule.getTableName());
        Assertions.assertEquals("USER", userRule.getTableAlias());
        Assertions.assertEquals(0, userRule.getOnRules().size());
        Assertions.assertEquals(0, userRule.getWhereRules().size());
        TableRuleOperations roleRule = ruleMap.get("ROLE");
        Assertions.assertNotNull(roleRule);
        Assertions.assertEquals(TableRuleOperations.Type.REAL, roleRule.getType());
        Assertions.assertEquals("ROLE", roleRule.getTableName());
        Assertions.assertEquals("ROLE", roleRule.getTableAlias());
        List<OnColumnRule> roleRuleOnRules = roleRule.getOnRules();
        Assertions.assertEquals(1, roleRuleOnRules.size());
        Assertions.assertEquals(0, roleRule.getWhereRules().size());
        OnColumnRule roleRuleOnRule = roleRuleOnRules.get(0);
        Assertions.assertNotNull(roleRuleOnRule);
        Assertions.assertEquals("ROLE", roleRuleOnRule.getTableName());
        Assertions.assertEquals("ROLE", roleRuleOnRule.getTableAlias());
        List<LogicExpression> logicExpressions = roleRuleOnRule.getLogicExpressions();
        Assertions.assertEquals(1, logicExpressions.size());
        LogicExpression logicExpression = logicExpressions.get(0);
        Assertions.assertNotNull(logicExpression);
        Assertions.assertEquals(LogicExpressionOperations.AndOr.AND, logicExpression.getAndOr());
        Assertions.assertEquals("ROLE", logicExpression.getTableName());
        Assertions.assertEquals("ROLE", logicExpression.getTableAlias());
        Assertions.assertEquals(0, logicExpression.getLogicExpressions().size());
        List<PredicateExpressionOperations> predicateExpressions = logicExpression.getPredicateExpressions();
        Assertions.assertEquals(1, predicateExpressions.size());
        PredicateExpressionOperations predicateExpression = predicateExpressions.get(0);
        Assertions.assertNotNull(predicateExpression);
        Assertions.assertEquals("ROLE", predicateExpression.getTableName());
        Assertions.assertEquals("ROLE", predicateExpression.getTableAlias());
        Assertions.assertEquals("ID", predicateExpression.getColumnName());
        Assertions.assertEquals("ID", predicateExpression.getColumnAlias());
        Assertions.assertEquals(ComparisonType.EQUAL, predicateExpression.getComparisonType());
        Assertions.assertEquals(PredicateExpressionOperations.ValueType.CONSTANT, predicateExpression.getValueType());
        Assertions.assertEquals("''", predicateExpression.getValue());
    }

    @Test
    void test04() {
        Map<String, TableRuleOperations> ruleMap = getRuntimeTableRule("SELECT U.* FROM USER U INNER JOIN ROLE R ON R.ID = ''");
        Assertions.assertNotNull(ruleMap);
        TableRuleOperations userRule = ruleMap.get("U");
        Assertions.assertNotNull(userRule);
        Assertions.assertEquals(TableRuleOperations.Type.REAL, userRule.getType());
        Assertions.assertEquals("USER", userRule.getTableName());
        Assertions.assertEquals("U", userRule.getTableAlias());
        Assertions.assertEquals(0, userRule.getOnRules().size());
        Assertions.assertEquals(0, userRule.getWhereRules().size());
        TableRuleOperations roleRule = ruleMap.get("R");
        Assertions.assertNotNull(roleRule);
        Assertions.assertEquals(TableRuleOperations.Type.REAL, roleRule.getType());
        Assertions.assertEquals("ROLE", roleRule.getTableName());
        Assertions.assertEquals("R", roleRule.getTableAlias());
        List<OnColumnRule> roleRuleOnRules = roleRule.getOnRules();
        Assertions.assertEquals(1, roleRuleOnRules.size());
        Assertions.assertEquals(0, roleRule.getWhereRules().size());
        OnColumnRule roleRuleOnRule = roleRuleOnRules.get(0);
        Assertions.assertNotNull(roleRuleOnRule);
        Assertions.assertEquals("ROLE", roleRuleOnRule.getTableName());
        Assertions.assertEquals("R", roleRuleOnRule.getTableAlias());
        List<LogicExpression> logicExpressions = roleRuleOnRule.getLogicExpressions();
        Assertions.assertEquals(1, logicExpressions.size());
        LogicExpression logicExpression = logicExpressions.get(0);
        Assertions.assertNotNull(logicExpression);
        Assertions.assertEquals(LogicExpressionOperations.AndOr.AND, logicExpression.getAndOr());
        Assertions.assertEquals("ROLE", logicExpression.getTableName());
        Assertions.assertEquals("R", logicExpression.getTableAlias());
        Assertions.assertEquals(0, logicExpression.getLogicExpressions().size());
        List<PredicateExpressionOperations> predicateExpressions = logicExpression.getPredicateExpressions();
        Assertions.assertEquals(1, predicateExpressions.size());
        PredicateExpressionOperations predicateExpression = predicateExpressions.get(0);
        Assertions.assertNotNull(predicateExpression);
        Assertions.assertEquals("ROLE", predicateExpression.getTableName());
        Assertions.assertEquals("R", predicateExpression.getTableAlias());
        Assertions.assertEquals("ID", predicateExpression.getColumnName());
        Assertions.assertEquals("ID", predicateExpression.getColumnAlias());
        Assertions.assertEquals(ComparisonType.EQUAL, predicateExpression.getComparisonType());
        Assertions.assertEquals(PredicateExpressionOperations.ValueType.CONSTANT, predicateExpression.getValueType());
        Assertions.assertEquals("''", predicateExpression.getValue());
    }

    @Test
    void test05() {
        Map<String, TableRuleOperations> ruleMap = getRuntimeTableRule("SELECT USER.* FROM USER INNER JOIN ROLE ON ROLE.ID = '' AND ROLE.USER_ID = USER.ID");
        Assertions.assertNotNull(ruleMap);
        TableRuleOperations userRule = ruleMap.get("USER");
        Assertions.assertNotNull(userRule);
        Assertions.assertEquals(TableRuleOperations.Type.REAL, userRule.getType());
        Assertions.assertEquals("USER", userRule.getTableName());
        Assertions.assertEquals("USER", userRule.getTableAlias());
        Assertions.assertEquals(0, userRule.getOnRules().size());
        Assertions.assertEquals(0, userRule.getWhereRules().size());
        TableRuleOperations roleRule = ruleMap.get("ROLE");
        Assertions.assertNotNull(roleRule);
        Assertions.assertEquals(TableRuleOperations.Type.REAL, roleRule.getType());
        Assertions.assertEquals("ROLE", roleRule.getTableName());
        Assertions.assertEquals("ROLE", roleRule.getTableAlias());
        List<OnColumnRule> roleRuleOnRules = roleRule.getOnRules();
        Assertions.assertEquals(1, roleRuleOnRules.size());
        Assertions.assertEquals(0, roleRule.getWhereRules().size());
        OnColumnRule roleRuleOnRule = roleRuleOnRules.get(0);
        Assertions.assertNotNull(roleRuleOnRule);
        Assertions.assertEquals("ROLE", roleRuleOnRule.getTableName());
        Assertions.assertEquals("ROLE", roleRuleOnRule.getTableAlias());
        List<LogicExpression> logicExpressions = roleRuleOnRule.getLogicExpressions();
        Assertions.assertEquals(1, logicExpressions.size());
        LogicExpression logicExpression = logicExpressions.get(0);
        Assertions.assertNotNull(logicExpression);
        Assertions.assertEquals(LogicExpressionOperations.AndOr.AND, logicExpression.getAndOr());
        Assertions.assertEquals("ROLE", logicExpression.getTableName());
        Assertions.assertEquals("ROLE", logicExpression.getTableAlias());
        Assertions.assertEquals(0, logicExpression.getLogicExpressions().size());
        List<PredicateExpressionOperations> predicateExpressions = logicExpression.getPredicateExpressions();
        Assertions.assertEquals(2, predicateExpressions.size());
        PredicateExpressionOperations predicateExpression = predicateExpressions.get(0);
        Assertions.assertNotNull(predicateExpression);
        Assertions.assertEquals("ROLE", predicateExpression.getTableName());
        Assertions.assertEquals("ROLE", predicateExpression.getTableAlias());
        Assertions.assertEquals("ID", predicateExpression.getColumnName());
        Assertions.assertEquals("ID", predicateExpression.getColumnAlias());
        Assertions.assertEquals(ComparisonType.EQUAL, predicateExpression.getComparisonType());
        Assertions.assertEquals(PredicateExpressionOperations.ValueType.CONSTANT, predicateExpression.getValueType());
        Assertions.assertEquals("''", predicateExpression.getValue());
        predicateExpression = predicateExpressions.get(1);
        Assertions.assertNotNull(predicateExpression);
        Assertions.assertEquals("ROLE", predicateExpression.getTableName());
        Assertions.assertEquals("ROLE", predicateExpression.getTableAlias());
        Assertions.assertEquals("USER_ID", predicateExpression.getColumnName());
        Assertions.assertEquals("USER_ID", predicateExpression.getColumnAlias());
        Assertions.assertEquals(ComparisonType.EQUAL, predicateExpression.getComparisonType());
        Assertions.assertEquals(PredicateExpressionOperations.ValueType.PREDICATE_EXPRESSION, predicateExpression.getValueType());
        Object value = predicateExpression.getValue();
        Assertions.assertNotNull(value);
        Assertions.assertTrue(value instanceof PredicateExpressionOperations);
        predicateExpression = (PredicateExpressionOperations) value;
        Assertions.assertEquals("USER", predicateExpression.getTableName());
        Assertions.assertEquals("USER", predicateExpression.getTableAlias());
        Assertions.assertEquals("ID", predicateExpression.getColumnName());
        Assertions.assertEquals("ID", predicateExpression.getColumnAlias());
        Assertions.assertEquals(ComparisonType.EQUAL, predicateExpression.getComparisonType());
        Assertions.assertEquals(PredicateExpressionOperations.ValueType.PREDICATE_EXPRESSION, predicateExpression.getValueType());
    }

    @Test
    void test06() {
        Map<String, TableRuleOperations> ruleMap = getRuntimeTableRule("SELECT USER.* FROM ( SELECT * FROM USER ) USER INNER JOIN ROLE ON ROLE.ID = ''");
        Assertions.assertNotNull(ruleMap);
        TableRuleOperations userRule = ruleMap.get("USER");
        Assertions.assertNotNull(userRule);
        Assertions.assertEquals(TableRuleOperations.Type.VIRTUAL, userRule.getType());
        Assertions.assertEquals("USER", userRule.getTableName());
        Assertions.assertEquals("USER", userRule.getTableAlias());
        Assertions.assertEquals(0, userRule.getOnRules().size());
        Assertions.assertEquals(0, userRule.getWhereRules().size());
        TableRuleOperations roleRule = ruleMap.get("ROLE");
        Assertions.assertNotNull(roleRule);
        Assertions.assertEquals(TableRuleOperations.Type.REAL, roleRule.getType());
        Assertions.assertEquals("ROLE", roleRule.getTableName());
        Assertions.assertEquals("ROLE", roleRule.getTableAlias());
        List<OnColumnRule> roleRuleOnRules = roleRule.getOnRules();
        Assertions.assertEquals(1, roleRuleOnRules.size());
        Assertions.assertEquals(0, roleRule.getWhereRules().size());
        OnColumnRule roleRuleOnRule = roleRuleOnRules.get(0);
        Assertions.assertNotNull(roleRuleOnRule);
        Assertions.assertEquals("ROLE", roleRuleOnRule.getTableName());
        Assertions.assertEquals("ROLE", roleRuleOnRule.getTableAlias());
        List<LogicExpression> logicExpressions = roleRuleOnRule.getLogicExpressions();
        Assertions.assertEquals(1, logicExpressions.size());
        LogicExpression logicExpression = logicExpressions.get(0);
        Assertions.assertNotNull(logicExpression);
        Assertions.assertEquals(LogicExpressionOperations.AndOr.AND, logicExpression.getAndOr());
        Assertions.assertEquals("ROLE", logicExpression.getTableName());
        Assertions.assertEquals("ROLE", logicExpression.getTableAlias());
        Assertions.assertEquals(0, logicExpression.getLogicExpressions().size());
        List<PredicateExpressionOperations> predicateExpressions = logicExpression.getPredicateExpressions();
        Assertions.assertEquals(1, predicateExpressions.size());
        PredicateExpressionOperations predicateExpression = predicateExpressions.get(0);
        Assertions.assertNotNull(predicateExpression);
        Assertions.assertEquals("ROLE", predicateExpression.getTableName());
        Assertions.assertEquals("ROLE", predicateExpression.getTableAlias());
        Assertions.assertEquals("ID", predicateExpression.getColumnName());
        Assertions.assertEquals("ID", predicateExpression.getColumnAlias());
        Assertions.assertEquals(ComparisonType.EQUAL, predicateExpression.getComparisonType());
        Assertions.assertEquals(PredicateExpressionOperations.ValueType.CONSTANT, predicateExpression.getValueType());
        Assertions.assertEquals("''", predicateExpression.getValue());

        Map<String, RuleStore> subRoleStoreMap = userRule.getSubRuleStoreMap();
        Assertions.assertNotNull(subRoleStoreMap);
        Assertions.assertEquals(1, subRoleStoreMap.size());
        RuleStore subRuleStore = subRoleStoreMap.get("USER");
        Assertions.assertNotNull(subRuleStore);
        ruleMap = subRuleStore.getRuntimeTableRuleMap();
        Assertions.assertNotNull(ruleMap);
        Assertions.assertEquals(1, ruleMap.size());
        TableRuleOperations rule = ruleMap.get("USER");
        Assertions.assertNotNull(rule);
        Assertions.assertEquals(TableRuleOperations.Type.REAL, rule.getType());
        Assertions.assertEquals("USER", rule.getTableName());
        Assertions.assertEquals("USER", rule.getTableAlias());
        Assertions.assertEquals(0, rule.getOnRules().size());
        Assertions.assertEquals(0, rule.getWhereRules().size());
    }

    @Test
    void test07() {
        Map<String, TableRuleOperations> ruleMap = getRuntimeTableRule("SELECT U.*, ( SELECT ID FROM USER LIMIT 1 ) subQuery FROM ( SELECT * FROM USER ) U INNER JOIN ( SELECT * FROM ROLE ) R ON R.ID = U.ID");
        Assertions.assertNotNull(ruleMap);
        TableRuleOperations userRule = ruleMap.get("U");
        Assertions.assertNotNull(userRule);
        Assertions.assertEquals(TableRuleOperations.Type.VIRTUAL, userRule.getType());
        Assertions.assertEquals("U", userRule.getTableName());
        Assertions.assertEquals("U", userRule.getTableAlias());
        Assertions.assertEquals(0, userRule.getOnRules().size());
        Assertions.assertEquals(0, userRule.getWhereRules().size());
        TableRuleOperations roleRule = ruleMap.get("R");
        Assertions.assertNotNull(roleRule);
        Assertions.assertEquals(TableRuleOperations.Type.VIRTUAL, roleRule.getType());
        Assertions.assertEquals("R", roleRule.getTableName());
        Assertions.assertEquals("R", roleRule.getTableAlias());
        List<OnColumnRule> roleRuleOnRules = roleRule.getOnRules();
        Assertions.assertEquals(1, roleRuleOnRules.size());
        Assertions.assertEquals(0, roleRule.getWhereRules().size());
        OnColumnRule roleRuleOnRule = roleRuleOnRules.get(0);
        Assertions.assertNotNull(roleRuleOnRule);
        Assertions.assertEquals("R", roleRuleOnRule.getTableName());
        Assertions.assertEquals("R", roleRuleOnRule.getTableAlias());
        List<LogicExpression> logicExpressions = roleRuleOnRule.getLogicExpressions();
        Assertions.assertEquals(1, logicExpressions.size());
        LogicExpression logicExpression = logicExpressions.get(0);
        Assertions.assertNotNull(logicExpression);
        Assertions.assertEquals(LogicExpressionOperations.AndOr.AND, logicExpression.getAndOr());
        Assertions.assertEquals("R", logicExpression.getTableName());
        Assertions.assertEquals("R", logicExpression.getTableAlias());
        Assertions.assertEquals(0, logicExpression.getLogicExpressions().size());
        List<PredicateExpressionOperations> predicateExpressions = logicExpression.getPredicateExpressions();
        Assertions.assertEquals(1, predicateExpressions.size());
        PredicateExpressionOperations predicateExpression = predicateExpressions.get(0);
        Assertions.assertNotNull(predicateExpression);
        Assertions.assertEquals("R", predicateExpression.getTableName());
        Assertions.assertEquals("R", predicateExpression.getTableAlias());
        Assertions.assertEquals("ID", predicateExpression.getColumnName());
        Assertions.assertEquals("ID", predicateExpression.getColumnAlias());
        Assertions.assertEquals(ComparisonType.EQUAL, predicateExpression.getComparisonType());
        Assertions.assertEquals(PredicateExpressionOperations.ValueType.PREDICATE_EXPRESSION, predicateExpression.getValueType());
        Object value = predicateExpression.getValue();
        Assertions.assertNotNull(value);
        Assertions.assertTrue(value instanceof PredicateExpressionOperations);
        predicateExpression = (PredicateExpressionOperations) value;
        Assertions.assertEquals("U", predicateExpression.getTableName());
        Assertions.assertEquals("U", predicateExpression.getTableAlias());
        Assertions.assertEquals("ID", predicateExpression.getColumnName());
        Assertions.assertEquals("ID", predicateExpression.getColumnAlias());
        Assertions.assertEquals(ComparisonType.EQUAL, predicateExpression.getComparisonType());
        Assertions.assertEquals(PredicateExpressionOperations.ValueType.PREDICATE_EXPRESSION, predicateExpression.getValueType());

        Map<String, RuleStore> subRoleStoreMap = userRule.getSubRuleStoreMap();
        Assertions.assertNotNull(subRoleStoreMap);
        Assertions.assertEquals(3, subRoleStoreMap.size());
        RuleStore subRuleStore = subRoleStoreMap.get("U");
        Assertions.assertNotNull(subRuleStore);
        ruleMap = subRuleStore.getRuntimeTableRuleMap();
        Assertions.assertNotNull(ruleMap);
        Assertions.assertEquals(1, ruleMap.size());
        TableRuleOperations rule = ruleMap.get("USER");
        Assertions.assertNotNull(rule);
        Assertions.assertEquals(TableRuleOperations.Type.REAL, rule.getType());
        Assertions.assertEquals("USER", rule.getTableName());
        Assertions.assertEquals("USER", rule.getTableAlias());
        Assertions.assertEquals(0, rule.getOnRules().size());
        Assertions.assertEquals(0, rule.getWhereRules().size());
        subRuleStore = subRoleStoreMap.get("R");
        Assertions.assertNotNull(subRuleStore);
        ruleMap = subRuleStore.getRuntimeTableRuleMap();
        Assertions.assertNotNull(ruleMap);
        Assertions.assertEquals(1, ruleMap.size());
        rule = ruleMap.get("ROLE");
        Assertions.assertNotNull(rule);
        Assertions.assertEquals(TableRuleOperations.Type.REAL, rule.getType());
        Assertions.assertEquals("ROLE", rule.getTableName());
        Assertions.assertEquals("ROLE", rule.getTableAlias());
        Assertions.assertEquals(0, rule.getOnRules().size());
        Assertions.assertEquals(0, rule.getWhereRules().size());
    }

    @Test
    void test08() {
        Map<String, TableRuleOperations> ruleMap = getRuntimeTableRule("SELECT U.*, ( SELECT ID FROM USER LIMIT 1 ) subQuery FROM ( SELECT * FROM USER ) U INNER JOIN ( SELECT * FROM ROLE ) R ON R.ID = ( SELECT ID FROM RESOURCE LIMIT 1 )");
        Assertions.assertNotNull(ruleMap);
        TableRuleOperations userRule = ruleMap.get("U");
        Assertions.assertNotNull(userRule);
        Assertions.assertEquals(TableRuleOperations.Type.VIRTUAL, userRule.getType());
        Assertions.assertEquals("U", userRule.getTableName());
        Assertions.assertEquals("U", userRule.getTableAlias());
        Assertions.assertEquals(0, userRule.getOnRules().size());
        Assertions.assertEquals(0, userRule.getWhereRules().size());
        TableRuleOperations roleRule = ruleMap.get("R");
        Assertions.assertNotNull(roleRule);
        Assertions.assertEquals(TableRuleOperations.Type.VIRTUAL, roleRule.getType());
        Assertions.assertEquals("R", roleRule.getTableName());
        Assertions.assertEquals("R", roleRule.getTableAlias());
        List<OnColumnRule> roleRuleOnRules = roleRule.getOnRules();
        Assertions.assertEquals(1, roleRuleOnRules.size());
        Assertions.assertEquals(0, roleRule.getWhereRules().size());
        OnColumnRule roleRuleOnRule = roleRuleOnRules.get(0);
        Assertions.assertNotNull(roleRuleOnRule);
        Assertions.assertEquals("R", roleRuleOnRule.getTableName());
        Assertions.assertEquals("R", roleRuleOnRule.getTableAlias());
        List<LogicExpression> logicExpressions = roleRuleOnRule.getLogicExpressions();
        Assertions.assertEquals(1, logicExpressions.size());
        LogicExpression logicExpression = logicExpressions.get(0);
        Assertions.assertNotNull(logicExpression);
        Assertions.assertEquals(LogicExpressionOperations.AndOr.AND, logicExpression.getAndOr());
        Assertions.assertEquals("R", logicExpression.getTableName());
        Assertions.assertEquals("R", logicExpression.getTableAlias());
        Assertions.assertEquals(0, logicExpression.getLogicExpressions().size());
        List<PredicateExpressionOperations> predicateExpressions = logicExpression.getPredicateExpressions();
        Assertions.assertEquals(1, predicateExpressions.size());
        PredicateExpressionOperations predicateExpression = predicateExpressions.get(0);
        Assertions.assertNotNull(predicateExpression);
        Assertions.assertEquals("R", predicateExpression.getTableName());
        Assertions.assertEquals("R", predicateExpression.getTableAlias());
        Assertions.assertEquals("ID", predicateExpression.getColumnName());
        Assertions.assertEquals("ID", predicateExpression.getColumnAlias());
        Assertions.assertEquals(ComparisonType.EQUAL, predicateExpression.getComparisonType());
        Assertions.assertEquals(PredicateExpressionOperations.ValueType.PREDICATE_EXPRESSION, predicateExpression.getValueType());
        Object value = predicateExpression.getValue();
        Assertions.assertNotNull(value);
        Assertions.assertTrue(value instanceof PredicateExpressionOperations);
        predicateExpression = (PredicateExpressionOperations) value;
        Assertions.assertEquals("U", predicateExpression.getTableName());
        Assertions.assertEquals("U", predicateExpression.getTableAlias());
        Assertions.assertEquals("ID", predicateExpression.getColumnName());
        Assertions.assertEquals("ID", predicateExpression.getColumnAlias());
        Assertions.assertEquals(ComparisonType.EQUAL, predicateExpression.getComparisonType());
        Assertions.assertEquals(PredicateExpressionOperations.ValueType.PREDICATE_EXPRESSION, predicateExpression.getValueType());

        Map<String, RuleStore> subRoleStoreMap = userRule.getSubRuleStoreMap();
        Assertions.assertNotNull(subRoleStoreMap);
        Assertions.assertEquals(3, subRoleStoreMap.size());
        RuleStore subRuleStore = subRoleStoreMap.get("U");
        Assertions.assertNotNull(subRuleStore);
        ruleMap = subRuleStore.getRuntimeTableRuleMap();
        Assertions.assertNotNull(ruleMap);
        Assertions.assertEquals(1, ruleMap.size());
        TableRuleOperations rule = ruleMap.get("USER");
        Assertions.assertNotNull(rule);
        Assertions.assertEquals(TableRuleOperations.Type.REAL, rule.getType());
        Assertions.assertEquals("USER", rule.getTableName());
        Assertions.assertEquals("USER", rule.getTableAlias());
        Assertions.assertEquals(0, rule.getOnRules().size());
        Assertions.assertEquals(0, rule.getWhereRules().size());
        subRuleStore = subRoleStoreMap.get("R");
        Assertions.assertNotNull(subRuleStore);
        ruleMap = subRuleStore.getRuntimeTableRuleMap();
        Assertions.assertNotNull(ruleMap);
        Assertions.assertEquals(1, ruleMap.size());
        rule = ruleMap.get("ROLE");
        Assertions.assertNotNull(rule);
        Assertions.assertEquals(TableRuleOperations.Type.REAL, rule.getType());
        Assertions.assertEquals("ROLE", rule.getTableName());
        Assertions.assertEquals("ROLE", rule.getTableAlias());
        Assertions.assertEquals(0, rule.getOnRules().size());
        Assertions.assertEquals(0, rule.getWhereRules().size());
    }
}