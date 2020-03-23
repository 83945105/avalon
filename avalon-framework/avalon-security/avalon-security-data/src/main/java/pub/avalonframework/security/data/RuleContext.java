package pub.avalonframework.security.data;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author baichao
 */
public class RuleContext {

    private RuleContext parent;

    private RuleContext runtimeRuleContext;

    private String masterTableName;

    private String masterTableAlias;

    private Map<String, TableAliasCache> tableAliasCacheMap = new HashMap<>(3);

    private RuleStore ruleStore = new RuleStore();

    private TableRuleOperations tableRule;

    private ColumnRuleOperations columnRule;

    private LogicExpressionOperations logicExpression;

    private PredicateExpressionOperations predicateExpression;

    private Map<String, RuleContext> subRuleContextMap;

    public RuleContext() {
        this.runtimeRuleContext = this;
    }

    public RuleContext(RuleContext parent) {
        this();
        this.parent = parent;
    }

    public void setRuntimeMasterTableNameAlias(String tableName, String tableAlias) {
        runtimeRuleContext.masterTableName = tableName;
        runtimeRuleContext.masterTableAlias = tableAlias;
    }

    public void addRuntimeTableAliasTableName(String tableName, String tableAlias) {
        if (runtimeRuleContext.tableAliasCacheMap.containsKey(tableAlias)) {
            throw new RuleContextException("SQL syntax error: table alias: " + tableAlias + " already exists");
        }
        runtimeRuleContext.tableAliasCacheMap.put(tableAlias, new TableAliasCache(tableName, tableAlias));
    }

    public boolean runtimeOnlyMasterTable() {
        if (runtimeRuleContext.tableAliasCacheMap.size() == 0) {
            throw new RuleContextException("SQL syntax error: no primary table specified.");
        }
        return runtimeRuleContext.tableAliasCacheMap.size() == 1;
    }

    public String getRuntimeTableNameByTableAlias(String tableAlias) {
        TableAliasCache tableAliasCache = runtimeRuleContext.tableAliasCacheMap.get(tableAlias);
        if (tableAliasCache == null) {
            throw new RuleContextException("SQL syntax error: nonexistent table alias: " + tableAlias);
        }
        return tableAliasCache.getTableName();
    }

    public Set<String> getRuntimeTableNames() {
        return runtimeRuleContext.tableAliasCacheMap.values().stream().map(TableAliasCache::getTableName).collect(Collectors.toSet());
    }

    public TableRuleOperations putRuntimeTableRule(String tableName, String tableAlias) {
        this.runtimeRuleContext.tableRule = ruleStore.putTableRule(tableName, tableAlias);
        return this.runtimeRuleContext.tableRule;
    }

    public ColumnRuleOperations addRuntimeOnColumnRule() {
        this.runtimeRuleContext.columnRule = tableRule.addOnColumnRule();
        return this.runtimeRuleContext.columnRule;
    }

    public ColumnRuleOperations addRuntimeWhereColumnRule() {
        this.runtimeRuleContext.columnRule = runtimeRuleContext.tableRule.addWhereColumnRule();
        return this.runtimeRuleContext.columnRule;
    }

    public LogicExpressionOperations addRuntimeLogicExpression(LogicExpressionOperations.AndOr andOr) {
        this.runtimeRuleContext.logicExpression = runtimeRuleContext.columnRule.addLogicExpression(andOr);
        return this.runtimeRuleContext.logicExpression;
    }

    public PredicateExpressionOperations addRuntimePredicateExpression() {
        this.runtimeRuleContext.predicateExpression = runtimeRuleContext.logicExpression.addPredicateExpression();
        return this.runtimeRuleContext.predicateExpression;
    }

    public LogicExpressionOperations addRuntimeSubLogicExpression(LogicExpressionOperations.AndOr andOr) {
        this.runtimeRuleContext.logicExpression = runtimeRuleContext.logicExpression.addLogicExpression(andOr);
        return this.runtimeRuleContext.logicExpression;
    }

    public LogicExpressionOperations.AndOr getRuntimeAndOr() {
        return this.runtimeRuleContext.logicExpression.getAndOr();
    }

    public void setRuntimeColumnNameAlias(String columnName, String columnAlias) {
        runtimeRuleContext.predicateExpression.setColumnNameAlias(columnName, columnAlias);
    }

    public void setRuntimeComparisonType(ComparisonType comparisonType) {
        runtimeRuleContext.predicateExpression.setComparisonType(comparisonType);
    }

    public void setRuntimeValue(Object value) {
        runtimeRuleContext.predicateExpression.setValue(value);
    }

    public boolean hasMasterTable() {
        return runtimeRuleContext.masterTableName != null;
    }

    public boolean hasRuntimeColumn() {
        return runtimeRuleContext.predicateExpression.hasColumn();
    }

    public boolean hasRuntimeValue() {
        return runtimeRuleContext.predicateExpression.hasValue();
    }

    public RuleContext addRuntimeSubRuleContext(String key) {
        if (runtimeRuleContext.subRuleContextMap == null) {
            runtimeRuleContext.subRuleContextMap = new LinkedHashMap<>();
        }
        RuleContext ruleContext = runtimeRuleContext.subRuleContextMap.get(key);
        if (ruleContext != null) {
            throw new RuleContextException("The key: " + key + " rule context already exists.");
        }
        ruleContext = new RuleContext(runtimeRuleContext);
        runtimeRuleContext.subRuleContextMap.put(key, ruleContext);
        this.runtimeRuleContext = ruleContext;
        return ruleContext;
    }

    public RuleContext getParentRuleContext() {
        this.runtimeRuleContext = this.runtimeRuleContext.parent;
        return this.runtimeRuleContext;
    }

    public String getMasterTableName() {
        return runtimeRuleContext.masterTableName;
    }

    public String getMasterTableAlias() {
        return runtimeRuleContext.masterTableAlias;
    }

    private final static class TableAliasCache {

        private String tableName;

        private String tableAlias;

        private Map<String, String> columnAliasColumnNameMap;

        public TableAliasCache(String tableName, String tableAlias) {
            this.tableName = tableName;
            this.tableAlias = tableAlias;
        }

        public String getTableName() {
            return tableName;
        }
    }
}