package pub.avalonframework.security.data;

import pub.avalonframework.security.data.sql.MysqlCacheJdbcOperations;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author baichao
 */
public class RuleContext {

    private RuleContext parent;

    private MysqlCacheJdbcOperations jdbcOperations;

    private RuleContext runtimeRuleContext;

    private String masterTableName;

    private String masterTableAlias;

    private Map<String, TableCache> tableCacheMap = new HashMap<>(3);

    private RuleStore ruleStore = new RuleStore();

    private TableRuleOperations tableRule;

    private ColumnRuleOperations columnRule;

    private LogicExpressionOperations logicExpression;

    private PredicateExpressionOperations predicateExpression;

    private Map<String, RuleContext> subRuleContextMap;

    public RuleContext(MysqlCacheJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
        this.runtimeRuleContext = this;
    }

    public RuleContext(RuleContext parent, MysqlCacheJdbcOperations jdbcOperations) {
        this(jdbcOperations);
        this.parent = parent;
    }

    /**
     * 添加运行时表所有列
     *
     * @param tableAlias 表别名
     */
    public void addRuntimeAllColumn(String tableAlias) {
        if (tableAlias == null) {
            throw new RuleContextException("SQL syntax error: columnAlias is null.");
        }
        List<String> columnNames = getRuntimeTableColumnNames(tableAlias);
        for (String columnName : columnNames) {
            addRuntimeTableColumn(tableAlias, columnName, columnName);
        }
    }

    /**
     * 添加运行时所有表所有列 -> *
     */
    public void addRuntimeStarColumn() {
        Set<String> tableNames = getRuntimeTableNames();
        if (tableNames.size() == 0) {
            throw new RuleContextException("SQL syntax error: no tables.");
        }
        for (String tableName : tableNames) {
            addRuntimeAllColumn(tableName);
        }
    }

    /**
     * 添加运行时表列
     *
     * @param tableAlias  表别名
     * @param columnName  列名
     * @param columnAlias 列别名
     */
    public void addRuntimeTableColumn(String tableAlias, String columnName, String columnAlias) {
        if (columnName == null || columnAlias == null) {
            throw new RuleContextException("SQL syntax error: columnName or columnAlias is null.");
        }
        System.out.println("tableAlias: " + tableAlias + " columnName: " + columnName + " columnAlias: " + columnAlias);
//        if (columnAliasColumnCache.containsKey(columnAlias)) {
//            throw new RuleContextException("SQL syntax error: column alias: " + columnAlias + " already exists");
//        }
//        columnAliasColumnCache.put(columnAlias, new ColumnCache(tableAlias, columnName, columnAlias));
    }

    public void setRuntimeMasterTableNameAlias(String tableName, String tableAlias) {
        runtimeRuleContext.masterTableName = tableName;
        runtimeRuleContext.masterTableAlias = tableAlias;
    }

    /**
     * 添加运行时真实表
     *
     * @param tableName  表名
     * @param tableAlias 表别名
     */
    public void addRuntimeRealTable(String tableName, String tableAlias) {
        if (runtimeRuleContext.tableCacheMap.containsKey(tableAlias)) {
            throw new RuleContextException("SQL syntax error: table alias: " + tableAlias + " already exists");
        }
        if (tableName == null) {
            throw new RuleContextException("SQL syntax error: tableName is null.");
        }
        List<String> columnNames = jdbcOperations.selectTableColumnNames(tableName);
        if (columnNames.size() < 1) {
            throw new RuleContextException("SQL syntax error: table: " + tableName + " has no column.");
        }
        runtimeRuleContext.tableCacheMap.put(tableAlias, new TableCache(tableName, tableAlias, columnNames));
    }

    /**
     * 添加运行时虚拟表
     *
     * @param tableAlias 表别名
     * @return 表字段注入器
     */
    public TableColumnNamesInjector addRuntimeVirtualTable(String tableAlias) {
        if (runtimeRuleContext.tableCacheMap.containsKey(tableAlias)) {
            throw new RuleContextException("SQL syntax error: table alias: " + tableAlias + " already exists");
        }
        TableCache tableCache = new TableCache(null, tableAlias);
        runtimeRuleContext.tableCacheMap.put(tableAlias, tableCache);
        return tableCache;
    }

    public boolean runtimeOnlyMasterTable() {
        if (runtimeRuleContext.tableCacheMap.size() == 0) {
            throw new RuleContextException("SQL syntax error: no primary table specified.");
        }
        return runtimeRuleContext.tableCacheMap.size() == 1;
    }

    public String getRuntimeTableNameByTableAlias(String tableAlias) {
        TableCache tableCache = runtimeRuleContext.tableCacheMap.get(tableAlias);
        if (tableCache == null) {
            throw new RuleContextException("SQL syntax error: nonexistent table alias: " + tableAlias);
        }
        return tableCache.getTableName();
    }

    public Set<String> getRuntimeTableNames() {
        return runtimeRuleContext.tableCacheMap.values().stream().map(TableCache::getTableName).collect(Collectors.toSet());
    }

    public List<String> getRuntimeTableColumnNames(String tableAlias) {
        TableCache tableCache = runtimeRuleContext.tableCacheMap.get(tableAlias);
        if (tableCache == null) {
            throw new RuleContextException("SQL syntax error: table: " + tableAlias + " non existent.");
        }
        return tableCache.getColumnNames();
    }

    /**
     * 添加运行时真实表规则
     *
     * @param tableName  表名
     * @param tableAlias 表别名
     * @return 表规则
     */
    public TableRuleOperations addRuntimeRealTableRule(String tableName, String tableAlias) {
        this.runtimeRuleContext.tableRule = ruleStore.addTableRule(tableName, tableAlias);
        return this.runtimeRuleContext.tableRule;
    }

    /**
     * 添加运行时虚拟表规则
     *
     * @param tableAlias 表别名
     * @return 表规则
     */
    public TableRuleOperations addRuntimeVirtualTableRule(String tableAlias) {
        this.runtimeRuleContext.tableRule = ruleStore.addTableRule(null, tableAlias);
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

    /**
     * 添加运行时子规则上下文
     *
     * @param key 唯一键
     * @return 规则上下文
     */
    public RuleContext addRuntimeSubRuleContext(String key) {
        if (runtimeRuleContext.subRuleContextMap == null) {
            runtimeRuleContext.subRuleContextMap = new LinkedHashMap<>();
        }
        RuleContext ruleContext = runtimeRuleContext.subRuleContextMap.get(key);
        if (ruleContext != null) {
            throw new RuleContextException("The key: " + key + " rule context already exists.");
        }
        ruleContext = new RuleContext(runtimeRuleContext, jdbcOperations);
        runtimeRuleContext.subRuleContextMap.put(key, ruleContext);
        this.runtimeRuleContext = ruleContext;
        return ruleContext;
    }

    /**
     * 添加运行时子虚拟规则上下文
     *
     * @param key                      唯一键
     * @param tableColumnNamesInjector 表列名注入器
     * @return 规则上下文
     */
    public RuleContext addRuntimeSubVirtualRuleContext(String key, TableColumnNamesInjector tableColumnNamesInjector) {
        if (runtimeRuleContext.subRuleContextMap == null) {
            runtimeRuleContext.subRuleContextMap = new LinkedHashMap<>();
        }
        RuleContext ruleContext = runtimeRuleContext.subRuleContextMap.get(key);
        if (ruleContext != null) {
            throw new RuleContextException("The key: " + key + " rule context already exists.");
        }
        ruleContext = new VirtualRuleContext(runtimeRuleContext, jdbcOperations, tableColumnNamesInjector);
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

    private final static class TableCache implements TableColumnNamesInjector {

        private String tableName;

        private String tableAlias;

        private List<String> columnNames;

        public TableCache(String tableName, String tableAlias) {
            this.tableName = tableName;
            this.tableAlias = tableAlias;
        }

        public TableCache(String tableName, String tableAlias, List<String> columnNames) {
            this.tableName = tableName;
            this.tableAlias = tableAlias;
            this.columnNames = columnNames;
        }

        public String getTableName() {
            return tableName;
        }

        public String getTableAlias() {
            return tableAlias;
        }

        public List<String> getColumnNames() {
            return columnNames;
        }

        @Override
        public void accept(List<String> columnNames) {
            this.columnNames = columnNames;
        }
    }

    @FunctionalInterface
    public interface TableColumnNamesInjector {

        void accept(List<String> columnNames);
    }
}