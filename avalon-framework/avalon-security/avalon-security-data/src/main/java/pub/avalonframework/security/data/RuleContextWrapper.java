package pub.avalonframework.security.data;

import pub.avalonframework.security.data.sql.MysqlCacheJdbcOperations;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author baichao
 */
public class RuleContextWrapper implements RuleContextOperations {

    private RuntimeRuleContext runtimeRuleContext;

    public RuleContextWrapper(RuntimeRuleContext runtimeRuleContext) {
        this.runtimeRuleContext = runtimeRuleContext;
    }

    @Override
    public void addRuntimeAllColumn(String tableAlias) {
        runtimeRuleContext.addRuntimeAllColumn(tableAlias);
    }

    @Override
    public void addRuntimeStarColumn() {
        runtimeRuleContext.addRuntimeStarColumn();
    }

    @Override
    public List<String> getRuntimeAllColumnAlias() {
        return runtimeRuleContext.getRuntimeAllColumnAlias();
    }

    @Override
    public void addRuntimeTableColumn(String tableAlias, String columnName, String columnAlias) {
        runtimeRuleContext.addRuntimeTableColumn(tableAlias, columnName, columnAlias);
    }

    @Override
    public void addRuntimeTableColumnFinish() {
        runtimeRuleContext.addRuntimeTableColumnFinish();
    }

    @Override
    public void setRuntimeRealMasterTable(String tableName, String tableAlias) {
        runtimeRuleContext.setRuntimeRealMasterTable(tableName, tableAlias);
    }

    @Override
    public void setRuntimeVirtualMasterTable(String tableAlias) {
        runtimeRuleContext.setRuntimeVirtualMasterTable(tableAlias);
    }

    @Override
    public void addRuntimeRealTable(String tableName, String tableAlias) {
        runtimeRuleContext.addRuntimeRealTable(tableName, tableAlias);
    }

    @Override
    public TableColumnNamesInjector addRuntimeVirtualTable(String tableAlias) {
        return runtimeRuleContext.addRuntimeVirtualTable(tableAlias);
    }

    @Override
    public boolean runtimeOnlyMasterTable() {
        return runtimeRuleContext.runtimeOnlyMasterTable();
    }

    @Override
    public String getRuntimeTableNameByTableAlias(String tableAlias) {
        return runtimeRuleContext.getRuntimeTableNameByTableAlias(tableAlias);
    }

    @Override
    public Set<String> getRuntimeTableNames() {
        return runtimeRuleContext.getRuntimeTableNames();
    }

    @Override
    public TableCache getRuntimeTable(String tableAlias) {
        return runtimeRuleContext.getRuntimeTable(tableAlias);
    }

    @Override
    public List<String> getTableColumnNames(String tableAlias) {
        return runtimeRuleContext.getTableColumnNames(tableAlias);
    }

    @Override
    public void addRuntimeRealTableRule(String tableName, String tableAlias) {
        runtimeRuleContext.addRuntimeRealTableRule(tableName, tableAlias);
    }

    @Override
    public void addRuntimeVirtualTableRule(String tableAlias) {
        runtimeRuleContext.addRuntimeVirtualTableRule(tableAlias);
    }

    @Override
    public void addRuntimeOnColumnRule() {
        runtimeRuleContext.addRuntimeOnColumnRule();
    }

    @Override
    public void addRuntimeWhereColumnRule() {
        runtimeRuleContext.addRuntimeWhereColumnRule();
    }

    @Override
    public void addRuntimeLogicExpression(LogicExpressionOperations.AndOr andOr) {
        runtimeRuleContext.addRuntimeLogicExpression(andOr);
    }

    @Override
    public void addRuntimePredicateExpression() {
        runtimeRuleContext.addRuntimePredicateExpression();
    }

    @Override
    public void addRuntimeSubLogicExpression(LogicExpressionOperations.AndOr andOr) {
        runtimeRuleContext.addRuntimeSubLogicExpression(andOr);
    }

    @Override
    public LogicExpressionOperations.AndOr getRuntimeLogicExpressionAndOr() {
        return runtimeRuleContext.getRuntimeLogicExpressionAndOr();
    }

    @Override
    public void setRuntimePredicateExpressionColumn(String tableAlias, String columnName) {
        runtimeRuleContext.setRuntimePredicateExpressionColumn(tableAlias, columnName);
    }

    @Override
    public void setRuntimePredicateExpressionComparisonType(ComparisonType comparisonType) {
        runtimeRuleContext.setRuntimePredicateExpressionComparisonType(comparisonType);
    }

    @Override
    public void setRuntimePredicateExpressionConstantTypeValue(Object value) {
        runtimeRuleContext.setRuntimePredicateExpressionConstantTypeValue(value);
    }

    @Override
    public void setRuntimePredicateExpressionColumnTypeValue(String tableAlias, String columnName) {
        runtimeRuleContext.setRuntimePredicateExpressionColumnTypeValue(tableAlias, columnName);
    }

    @Override
    public boolean hasRuntimeMasterTable() {
        return runtimeRuleContext.hasRuntimeMasterTable();
    }

    @Override
    public boolean hasRuntimePredicateExpressionColumn() {
        return runtimeRuleContext.hasRuntimePredicateExpressionColumn();
    }

    @Override
    public boolean hasRuntimePredicateExpressionValue() {
        return runtimeRuleContext.hasRuntimePredicateExpressionValue();
    }

    @Override
    public RuntimeRuleContext addSubRuntimeRuleContext(String key) {
        return runtimeRuleContext.addSubRuntimeRuleContext(key);
    }

    @Override
    public RuntimeRuleContext addSubRuntimeVirtualRuleContext(String key, TableColumnNamesInjector tableColumnNamesInjector) {
        return runtimeRuleContext.addSubRuntimeVirtualRuleContext(key, tableColumnNamesInjector);
    }

    /**
     * 切换到子运行时规则上下文
     *
     * @param key 唯一键
     */
    public void switchToSubRuntimeRuleContext(String key) {
        this.runtimeRuleContext = addSubRuntimeRuleContext(key);
    }

    /**
     * 切换到子运行时虚拟规则上下文
     *
     * @param key                      唯一键
     * @param tableColumnNamesInjector 表列名注入器
     */
    public void switchToSubRuntimeVirtualRuleContext(String key, TableColumnNamesInjector tableColumnNamesInjector) {
        this.runtimeRuleContext = addSubRuntimeVirtualRuleContext(key, tableColumnNamesInjector);
    }

    /**
     * 切换到父级运行时规则上下文
     */
    public void switchToParentRuntimeRuleContext() {
        this.runtimeRuleContext = runtimeRuleContext.parent;
    }

    @Override
    public String getRuntimeMasterTableName() {
        return runtimeRuleContext.getRuntimeMasterTableName();
    }

    @Override
    public String getRuntimeMasterTableAlias() {
        return runtimeRuleContext.getRuntimeMasterTableAlias();
    }

    @Override
    public Stage getRuntimeStage() {
        return runtimeRuleContext.getRuntimeStage();
    }

    @Override
    public void setRuntimeStage(Stage stage) {
        runtimeRuleContext.setRuntimeStage(stage);
    }

    /**
     * 切换至运行时Select阶段
     */
    public void switchToRuntimeSelectStage() {
        setRuntimeStage(Stage.SELECT);
    }

    /**
     * 切换至运行时From阶段
     */
    public void switchToRuntimeFromStage() {
        setRuntimeStage(Stage.FROM);
    }

    /**
     * 切换至运行时On阶段
     */
    public void switchToRuntimeOnStage() {
        setRuntimeStage(Stage.ON);
    }

    /**
     * 切换至运行时Where阶段
     */
    public void switchToRuntimeWhereStage() {
        setRuntimeStage(Stage.WHERE);
    }

    /**
     * 切换至运行时Group阶段
     */
    public void switchToRuntimeGroupStage() {
        setRuntimeStage(Stage.GROUP);
    }

    /**
     * 切换至运行时Having阶段
     */
    public void switchToRuntimeHavingStage() {
        setRuntimeStage(Stage.HAVING);
    }

    /**
     * 切换至运行时Order阶段
     */
    public void switchToRuntimeOrderStage() {
        setRuntimeStage(Stage.ORDER);
    }

    /**
     * 切换至运行时Limit阶段
     */
    public void switchToRuntimeLimitStage() {
        setRuntimeStage(Stage.LIMIT);
    }

    /**
     * 是否是谓语表达式阶段
     *
     * @return true | false
     */
    public boolean runtimePredicateExpressionStage() {
        Stage stage = getRuntimeStage();
        return stage == Stage.ON || stage == Stage.WHERE;
    }

    public static class RuntimeRuleContext implements RuleContextOperations {

        private RuntimeRuleContext parent;

        private MysqlCacheJdbcOperations jdbcOperations;

        private Stage stage;

        // 记录当前上下文中最终结果的 字段别名 和 字段名
        private Map<String, String> columnAliasCache = new LinkedHashMap<>();

        private String masterTableName;

        private String masterTableAlias;

        // 记录当前上下文中涉及到的 表别名 和 表信息
        private Map<String, TableCache> tableCacheMap = new HashMap<>(3);

        private RuleStore ruleStore = new RuleStore();

        private TableRuleOperations tableRule;

        private ColumnRuleOperations columnRule;

        private LogicExpressionOperations logicExpression;

        private PredicateExpressionOperations predicateExpression;

        // 记录当前上下文中 包含的 子规则上下文规则 key为唯一件
        private Map<String, RuntimeRuleContext> subRuntimeRuleContextMap;

        public RuntimeRuleContext(MysqlCacheJdbcOperations jdbcOperations) {
            this.jdbcOperations = jdbcOperations;
        }

        public RuntimeRuleContext(RuntimeRuleContext parent, MysqlCacheJdbcOperations jdbcOperations) {
            this(jdbcOperations);
            this.parent = parent;
        }

        @Override
        public void addRuntimeAllColumn(String tableAlias) {
            if (tableAlias == null) {
                throw new RuleContextException("SQL syntax error: columnAlias is null.");
            }
            List<String> columnNames = getTableColumnNames(tableAlias);
            for (String columnName : columnNames) {
                addRuntimeTableColumn(tableAlias, columnName, columnName);
            }
        }

        @Override
        public void addRuntimeStarColumn() {
            Set<String> tableNames = getRuntimeTableNames();
            if (tableNames.size() == 0) {
                throw new RuleContextException("SQL syntax error: no tables.");
            }
            for (String tableName : tableNames) {
                addRuntimeAllColumn(tableName);
            }
        }

        @Override
        public List<String> getRuntimeAllColumnAlias() {
            return new ArrayList<>(columnAliasCache.keySet());
        }

        @Override
        public void addRuntimeTableColumn(String tableAlias, String columnName, String columnAlias) {
            if (columnName == null || columnAlias == null) {
                throw new RuleContextException("SQL syntax error: columnName or columnAlias is null.");
            }
            if (columnAliasCache.containsKey(columnAlias)) {
                throw new RuleContextException("SQL syntax error: column alias: " + columnAlias + " already exists");
            }
            columnAliasCache.put(columnAlias, columnName);
        }

        @Override
        public void addRuntimeTableColumnFinish() {
            // do nothing.
        }

        @Override
        public void setRuntimeRealMasterTable(String tableName, String tableAlias) {
            masterTableName = tableName;
            masterTableAlias = tableAlias;
        }

        @Override
        public void setRuntimeVirtualMasterTable(String tableAlias) {
            masterTableName = tableAlias;
            masterTableAlias = tableAlias;
        }

        @Override
        public void addRuntimeRealTable(String tableName, String tableAlias) {
            if (tableCacheMap.containsKey(tableAlias)) {
                throw new RuleContextException("SQL syntax error: table alias: " + tableAlias + " already exists");
            }
            if (tableName == null) {
                throw new RuleContextException("SQL syntax error: tableName is null.");
            }
            List<String> columnNames = jdbcOperations.selectTableColumnNames(tableName);
            if (columnNames.size() < 1) {
                throw new RuleContextException("SQL syntax error: table: " + tableName + " has no column.");
            }
            tableCacheMap.put(tableAlias, new TableCache(tableName, tableAlias, columnNames));
        }

        @Override
        public TableColumnNamesInjector addRuntimeVirtualTable(String tableAlias) {
            if (tableCacheMap.containsKey(tableAlias)) {
                throw new RuleContextException("SQL syntax error: table alias: " + tableAlias + " already exists");
            }
            TableCache tableCache = new TableCache(tableAlias, tableAlias);
            tableCacheMap.put(tableAlias, tableCache);
            return tableCache;
        }

        @Override
        public boolean runtimeOnlyMasterTable() {
            if (tableCacheMap.size() == 0) {
                throw new RuleContextException("SQL syntax error: no primary table specified.");
            }
            return tableCacheMap.size() == 1;
        }

        @Override
        public String getRuntimeTableNameByTableAlias(String tableAlias) {
            TableCache tableCache = tableCacheMap.get(tableAlias);
            if (tableCache == null) {
                throw new RuleContextException("SQL syntax error: nonexistent table alias: " + tableAlias);
            }
            return tableCache.getTableName();
        }

        @Override
        public Set<String> getRuntimeTableNames() {
            return tableCacheMap.values().stream().map(TableCache::getTableName).collect(Collectors.toSet());
        }

        @Override
        public TableCache getRuntimeTable(String tableAlias) {
            TableCache tableCache = tableCacheMap.get(tableAlias);
            if (tableCache == null) {
                throw new RuleContextException("SQL syntax error: table: " + tableAlias + " non existent.");
            }
            return tableCache;
        }

        @Override
        public List<String> getTableColumnNames(String tableAlias) {
            List<String> columnNames = getRuntimeTable(tableAlias).getColumnNames();
            if (columnNames == null) {
                throw new RuleContextException("SQL syntax error: table: " + tableAlias + " has no column.");
            }
            return columnNames;
        }

        @Override
        public void addRuntimeRealTableRule(String tableName, String tableAlias) {
            tableRule = ruleStore.addTableRule(tableName, tableAlias);
        }

        @Override
        public void addRuntimeVirtualTableRule(String tableAlias) {
            tableRule = this.ruleStore.addTableRule(null, tableAlias);
        }

        @Override
        public void addRuntimeOnColumnRule() {
            columnRule = tableRule.addOnColumnRule();
        }

        @Override
        public void addRuntimeWhereColumnRule() {
            columnRule = tableRule.addWhereColumnRule();
        }

        @Override
        public void addRuntimeLogicExpression(LogicExpressionOperations.AndOr andOr) {
            logicExpression = columnRule.addLogicExpression(andOr);
        }

        @Override
        public void addRuntimePredicateExpression() {
            predicateExpression = logicExpression.addPredicateExpression();
        }

        @Override
        public void addRuntimeSubLogicExpression(LogicExpressionOperations.AndOr andOr) {
            logicExpression = logicExpression.addLogicExpression(andOr);
        }

        @Override
        public LogicExpressionOperations.AndOr getRuntimeLogicExpressionAndOr() {
            return logicExpression.getAndOr();
        }

        @Override
        public void setRuntimePredicateExpressionColumn(String tableAlias, String columnName) {
            if (columnName == null) {
                throw new RuleContextException("SQL syntax error: columnName is null.");
            }
            String tableName = tableAlias;
            if (tableAlias == null) {
                TableCache tableCache;
                for (Map.Entry<String, TableCache> entry : tableCacheMap.entrySet()) {
                    tableCache = entry.getValue();
                    if (tableCache.getColumnNames().contains(columnName)) {
                        if (tableAlias != null) {
                            throw new RuleContextException("SQL syntax error: Column " + columnName + " in field list is ambiguous.");
                        }
                        tableName = tableCache.getTableName();
                        tableAlias = tableCache.getTableAlias();
                    }
                }
            } else {
                tableName = getRuntimeTableNameByTableAlias(tableAlias);
            }
            predicateExpression.setColumn(tableName, tableAlias, columnName, columnName);
        }

        @Override
        public void setRuntimePredicateExpressionComparisonType(ComparisonType comparisonType) {
            predicateExpression.setComparisonType(comparisonType);
        }

        @Override
        public void setRuntimePredicateExpressionConstantTypeValue(Object value) {
            predicateExpression.setValue(value);
        }

        @Override
        public void setRuntimePredicateExpressionColumnTypeValue(String tableAlias, String columnName) {

        }

        @Override
        public boolean hasRuntimeMasterTable() {
            return masterTableName != null;
        }

        @Override
        public boolean hasRuntimePredicateExpressionColumn() {
            return predicateExpression.hasColumn();
        }

        @Override
        public boolean hasRuntimePredicateExpressionValue() {
            return predicateExpression.hasValue();
        }

        @Override
        public RuntimeRuleContext addSubRuntimeRuleContext(String key) {
            if (subRuntimeRuleContextMap == null) {
                subRuntimeRuleContextMap = new LinkedHashMap<>();
            }
            RuntimeRuleContext runtimeRuleContext = subRuntimeRuleContextMap.get(key);
            if (runtimeRuleContext != null) {
                throw new RuleContextException("The key: " + key + " rule context already exists.");
            }
            runtimeRuleContext = new RuntimeRuleContext(this, jdbcOperations);
            subRuntimeRuleContextMap.put(key, runtimeRuleContext);
            return runtimeRuleContext;
        }

        @Override
        public RuntimeRuleContext addSubRuntimeVirtualRuleContext(String key, TableColumnNamesInjector tableColumnNamesInjector) {
            if (subRuntimeRuleContextMap == null) {
                subRuntimeRuleContextMap = new LinkedHashMap<>();
            }
            RuntimeRuleContext runtimeRuleContext = subRuntimeRuleContextMap.get(key);
            if (runtimeRuleContext != null) {
                throw new RuleContextException("The key: " + key + " rule context already exists.");
            }
            runtimeRuleContext = new RuntimeVirtualRuleContext(this, jdbcOperations, tableColumnNamesInjector);
            subRuntimeRuleContextMap.put(key, runtimeRuleContext);
            return runtimeRuleContext;
        }

        @Override
        public String getRuntimeMasterTableName() {
            return masterTableName;
        }

        @Override
        public String getRuntimeMasterTableAlias() {
            return masterTableAlias;
        }

        @Override
        public Stage getRuntimeStage() {
            return stage;
        }

        @Override
        public void setRuntimeStage(Stage stage) {
            this.stage = stage;
        }
    }

    private static class RuntimeVirtualRuleContext extends RuntimeRuleContext {

        private TableColumnNamesInjector tableColumnNamesInjector;

        public RuntimeVirtualRuleContext(MysqlCacheJdbcOperations jdbcOperations, TableColumnNamesInjector tableColumnNamesInjector) {
            super(jdbcOperations);
            this.tableColumnNamesInjector = tableColumnNamesInjector;
        }

        public RuntimeVirtualRuleContext(RuntimeRuleContext parent, MysqlCacheJdbcOperations jdbcOperations, TableColumnNamesInjector tableColumnNamesInjector) {
            super(parent, jdbcOperations);
            this.tableColumnNamesInjector = tableColumnNamesInjector;
        }

        @Override
        public void addRuntimeTableColumnFinish() {
            tableColumnNamesInjector.accept(getRuntimeAllColumnAlias());
        }
    }
}