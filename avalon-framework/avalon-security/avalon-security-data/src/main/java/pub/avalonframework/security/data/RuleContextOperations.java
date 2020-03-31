package pub.avalonframework.security.data;

import pub.avalonframework.security.data.expression.*;

import java.util.List;
import java.util.Set;

/**
 * @author baichao
 */
public interface RuleContextOperations {

    /**
     * 获取运行时规则库
     *
     * @return RuleStore
     */
    RuleStore getRuntimeRuleStore();

    /**
     * 添加运行时表所有列
     *
     * @param tableAlias 表别名
     */
    void addRuntimeAllColumn(String tableAlias);

    /**
     * 添加运行时所有表所有列 -> *
     */
    void addRuntimeStarColumn();

    /**
     * 获取运行时所有字段别名
     *
     * @return 字段别名集合
     */
    List<String> getRuntimeAllColumnAlias();

    /**
     * 添加运行时表列
     *
     * @param tableAlias  表别名
     * @param columnName  列名
     * @param columnAlias 列别名
     */
    void addRuntimeTableColumn(String tableAlias, String columnName, String columnAlias);

    /**
     * 添加运行时表列结束
     * <p>
     * 临时处理, 后续使用事件方式实现这块逻辑
     */
    void addRuntimeTableColumnFinish();

    /**
     * 设置运行时真实主表
     *
     * @param tableName  表名
     * @param tableAlias 表别名
     */
    void setRuntimeRealMasterTable(String tableName, String tableAlias);

    /**
     * 设置运行时虚拟主表
     *
     * @param tableAlias 表别名
     */
    void setRuntimeVirtualMasterTable(String tableAlias);

    /**
     * 添加运行时真实表
     *
     * @param tableName  表名
     * @param tableAlias 表别名
     */
    void addRuntimeRealTable(String tableName, String tableAlias);

    /**
     * 添加运行时虚拟表
     *
     * @param tableAlias 表别名
     * @return 表字段注入器
     */
    TableColumnNamesInjector addRuntimeVirtualTable(String tableAlias);

    /**
     * 运行时是否只有主表
     *
     * @return true | false
     */
    boolean runtimeOnlyMasterTable();

    /**
     * 是否有运行时主表
     *
     * @return true | false
     */
    boolean hasRuntimeMasterTable();

    /**
     * 获取运行时主表名
     *
     * @return 主表名
     */
    String getRuntimeMasterTableName();

    /**
     * 获取运行时主表别名
     *
     * @return 主表别名
     */
    String getRuntimeMasterTableAlias();

    /**
     * 根据表别名获取运行时表名
     *
     * @param tableAlias 表别名
     * @return 表名
     */
    String getRuntimeTableNameByTableAlias(String tableAlias);

    /**
     * 获取运行时所有表名
     *
     * @return 表名集合
     */
    Set<String> getRuntimeTableNames();

    /**
     * 获取运行时所有表别名
     *
     * @return 表别名集合
     */
    Set<String> getRuntimeTableAliases();

    /**
     * 根据表别名获取运行时表信息
     *
     * @param tableAlias 表别名
     * @return 表信息
     */
    TableCache getRuntimeTable(String tableAlias);

    /**
     * 获取运行时所有表信息
     *
     * @return 表信息集合
     */
    List<TableCache> getRuntimeTables();

    /**
     * 根据表别名获取表所有列名
     *
     * @param tableAlias 表别名
     * @return 列名集合
     */
    List<String> getTableColumnNames(String tableAlias);

    /**
     * 添加运行时真实表规则
     *
     * @param tableName  表名
     * @param tableAlias 表别名
     */
    void addRuntimeRealTableRule(String tableName, String tableAlias);

    /**
     * 添加运行时虚拟表规则
     *
     * @param tableAlias 表别名
     */
    void addRuntimeVirtualTableRule(String tableAlias);

    /**
     * 添加运行时 On 列规则
     */
    void addRuntimeOnColumnRule();

    /**
     * 添加运行时 Where 列规则
     */
    void addRuntimeWhereColumnRule();

    /**
     * 添加运行时逻辑表达式
     *
     * @param logicOperator and | or
     */
    void addRuntimeLogicExpression(LogicOperator logicOperator);

    /**
     * 添加运行时谓语表达式
     *
     * @param predicateExpression 谓语表达式
     */
    void addRuntimePredicateExpression(PredicateExpression predicateExpression);

    /**
     * 获取运行时谓语表达式
     *
     * @return 谓语表达式
     */
    PredicateExpression getRuntimePredicateExpression();

    /**
     * 构建列谓语
     *
     * @param tableAlias 表别名
     * @param columnName 列名
     * @return 列谓语
     */
    default ColumnPredicate buildColumnPredicate(String tableAlias, String columnName) {
        if (columnName == null) {
            throw new RuleContextException("SQL syntax error: columnName is null.");
        }
        String tableName = tableAlias;
        if (tableAlias == null) {
            if (runtimeOnlyMasterTable()) {
                // 有且只有主表
                tableName = getRuntimeMasterTableName();
                tableAlias = getRuntimeMasterTableAlias();
            } else {
                for (TableCache runtimeTable : getRuntimeTables()) {
                    if (runtimeTable.getColumnNames().contains(columnName)) {
                        if (tableAlias != null) {
                            throw new RuleContextException("SQL syntax error: Column " + columnName + " in field list is ambiguous.");
                        }
                        tableName = runtimeTable.getTableName();
                        tableAlias = runtimeTable.getTableAlias();
                    }
                }
                if (tableName == null || tableAlias == null) {
                    throw new RuleContextException("SQL syntax error: Unknown column " + columnName + " in clause.");
                }
            }
        } else {
            tableName = getRuntimeTableNameByTableAlias(tableAlias);
        }
        if (tableName == null || tableAlias == null) {
            throw new RuleContextException("SQL syntax error: tableName or tableAlias is null.");
        }
        return new ColumnPredicate(tableName, tableAlias, columnName, columnName);
    }

    /**
     * 添加运行时非空判断谓语表达式
     */
    default void addRuntimeIsNullPredicate() {
        addRuntimePredicateExpression(new IsNullPredicate());
    }

    /**
     * 获取运行时非空判断谓语表达式
     *
     * @return 非空判断谓语表达式
     */
    default IsNullPredicate getRuntimeIsNullPredicate() {
        PredicateExpression predicateExpression = getRuntimePredicateExpression();
        if (predicateExpression instanceof IsNullPredicate) {
            return (IsNullPredicate) predicateExpression;
        }
        return null;
    }

    /**
     * 是否是运行时非空判断谓语表达式
     *
     * @return true | false
     */
    default boolean hasRuntimeIsNullPredicate() {
        return getRuntimeIsNullPredicate() != null;
    }

    /**
     * 运行时非空判断谓语表达式是否设置过谓语
     *
     * @return true | false
     */
    default boolean runtimeIsNullPredicateHasPredicate() {
        IsNullPredicate isNullPredicate = getRuntimeIsNullPredicate();
        if (isNullPredicate == null) {
            throw new RuleContextException("SQL syntax error: runtimePredicateExpression is not of type IsNullPredicate.");
        }
        return isNullPredicate.hasPredicate();
    }

    /**
     * 设置运行时非空判断谓语表达式谓语
     *
     * @param predicate 谓语
     */
    default void setRuntimeIsNullPredicatePredicate(Predicate predicate) {
        IsNullPredicate isNullPredicate = getRuntimeIsNullPredicate();
        if (isNullPredicate == null) {
            throw new RuleContextException("SQL syntax error: runtimePredicateExpression is not of type IsNullPredicate.");
        }
        isNullPredicate.setPredicate(predicate);
    }

    /**
     * 设置运行时非空判断谓语表达式比较运算符
     *
     * @param comparisonOperator 比较运算符
     */
    default void setRuntimeIsNullPredicateComparisonOperator(ComparisonOperator comparisonOperator) {
        IsNullPredicate isNullPredicate = getRuntimeIsNullPredicate();
        if (isNullPredicate == null) {
            throw new RuleContextException("SQL syntax error: runtimePredicateExpression is not of type IsNullPredicate.");
        }
        isNullPredicate.setComparisonOperator(comparisonOperator);
    }

    /**
     * 添加运行时双边比较谓语表达式
     */
    default void addRuntimeBinaryComparisonPredicate() {
        addRuntimePredicateExpression(new BinaryComparisonPredicate());
    }

    /**
     * 获取运行时双边比较谓语表达式
     *
     * @return 双边比较谓语表达式
     */
    default BinaryComparisonPredicate getRuntimeBinaryComparisonPredicate() {
        PredicateExpression predicateExpression = getRuntimePredicateExpression();
        if (predicateExpression instanceof BinaryComparisonPredicate) {
            return (BinaryComparisonPredicate) predicateExpression;
        }
        return null;
    }

    /**
     * 是否是运行时双边比较谓语表达式
     *
     * @return true | false
     */
    default boolean hasRuntimeBinaryComparisonPredicate() {
        return getRuntimeBinaryComparisonPredicate() != null;
    }

    /**
     * 运行时双边比较谓语是否设置过主谓语
     *
     * @return true | false
     */
    default boolean runtimeBinaryComparisonPredicateHasMasterPredicate() {
        BinaryComparisonPredicate binaryComparisonPredicate = getRuntimeBinaryComparisonPredicate();
        if (binaryComparisonPredicate == null) {
            throw new RuleContextException("SQL syntax error: runtimePredicateExpression is not of type BinaryComparisonPredicate.");
        }
        return binaryComparisonPredicate.hasMasterPredicate();
    }

    /**
     * 设置运行时双边比较谓语表达式主谓语
     *
     * @param predicate 谓语
     */
    default void setRuntimeBinaryComparisonPredicateMasterPredicate(Predicate predicate) {
        BinaryComparisonPredicate binaryComparisonPredicate = getRuntimeBinaryComparisonPredicate();
        if (binaryComparisonPredicate == null) {
            throw new RuleContextException("SQL syntax error: runtimePredicateExpression is not of type BinaryComparisonPredicate.");
        }
        binaryComparisonPredicate.setMasterPredicate(predicate);
    }

    /**
     * 设置运行时双边比较谓语表达式主谓语
     *
     * @param tableAlias 表别名
     * @param columnName 列名
     */
    default void setRuntimeBinaryComparisonPredicateMasterPredicate(String tableAlias, String columnName) {
        setRuntimeBinaryComparisonPredicateMasterPredicate(buildColumnPredicate(tableAlias, columnName));
    }

    /**
     * 设置运行时谓语表达式比较运算符
     *
     * @param comparisonOperator 比较运算符
     */
    default void setRuntimeBinaryComparisonPredicateComparisonOperator(ComparisonOperator comparisonOperator) {
        BinaryComparisonPredicate binaryComparisonPredicate = getRuntimeBinaryComparisonPredicate();
        if (binaryComparisonPredicate == null) {
            throw new RuleContextException("SQL syntax error: runtimePredicateExpression is not of type BinaryComparisonPredicate.");
        }
        binaryComparisonPredicate.setComparisonOperator(comparisonOperator);
    }

    /**
     * 运行时双边比较谓语是否设置过从谓语
     *
     * @return true | false
     */
    default boolean runtimeBinaryComparisonPredicateHasSlavePredicate() {
        BinaryComparisonPredicate binaryComparisonPredicate = getRuntimeBinaryComparisonPredicate();
        if (binaryComparisonPredicate == null) {
            throw new RuleContextException("SQL syntax error: runtimePredicateExpression is not of type BinaryComparisonPredicate.");
        }
        return binaryComparisonPredicate.hasSlavePredicate();
    }

    /**
     * 添加运行时双边比较谓语表达式从谓语
     *
     * @param predicate 谓语
     */
    default void setRuntimeBinaryComparisonPredicateSlavePredicate(Predicate predicate) {
        BinaryComparisonPredicate binaryComparisonPredicate = getRuntimeBinaryComparisonPredicate();
        if (binaryComparisonPredicate == null) {
            throw new RuleContextException("SQL syntax error: runtimePredicateExpression is not of type BinaryComparisonPredicate.");
        }
        binaryComparisonPredicate.setSlavePredicate(predicate);
    }

    /**
     * 设置运行时双边比较谓语表达式从谓语
     *
     * @param tableAlias 表别名
     * @param columnName 列名
     */
    default void setRuntimeBinaryComparisonPredicateSlavePredicate(String tableAlias, String columnName) {
        BinaryComparisonPredicate binaryComparisonPredicate = getRuntimeBinaryComparisonPredicate();
        if (binaryComparisonPredicate == null) {
            throw new RuleContextException("SQL syntax error: runtimePredicateExpression is not of type BinaryComparisonPredicate.");
        }
        BinaryComparisonPredicate slavePredicate = new BinaryComparisonPredicate();
        slavePredicate.setMasterPredicate(buildColumnPredicate(tableAlias, columnName));
        slavePredicate.setComparisonOperator(binaryComparisonPredicate.getComparisonOperator());
        slavePredicate.setSlavePredicate(binaryComparisonPredicate.getMasterPredicate());
        binaryComparisonPredicate.setSlavePredicate(slavePredicate.getMasterPredicate());
    }

    /**
     * 设置运行时双边比较谓语表达式从谓语
     *
     * @param value 值
     */
    default void setRuntimeBinaryComparisonPredicateSlavePredicate(Object value) {
        setRuntimeBinaryComparisonPredicateSlavePredicate(new ConstantPredicate(value));
    }

    /**
     * 添加运行时子逻辑表达式
     *
     * @param logicOperator and | or
     */
    void addRuntimeSubLogicExpression(LogicOperator logicOperator);

    /**
     * 获取运行时逻辑表达式符号
     *
     * @return and | or
     */
    LogicOperator getRuntimeLogicOperator();

    /**
     * 添加运行时子规则上下文
     *
     * @param key 唯一键
     * @return 规则上下文
     */
    RuleContextOperations addSubRuntimeRuleContext(String key);

    /**
     * 添加运行时子虚拟规则上下文
     *
     * @param key                      唯一键
     * @param tableColumnNamesInjector 表列名注入器
     * @return 规则上下文
     */
    RuleContextOperations addSubRuntimeVirtualRuleContext(String key, TableColumnNamesInjector tableColumnNamesInjector);

    /**
     * 获取当前阶段
     *
     * @return 阶段
     */
    Stage getRuntimeStage();

    /**
     * 设置当前阶段
     *
     * @param stage 阶段
     */
    void setRuntimeStage(Stage stage);

    /**
     * 阶段
     */
    enum Stage {
        SELECT, FROM, ON, WHERE, GROUP, HAVING, ORDER, LIMIT
    }

    final class TableCache implements TableColumnNamesInjector {

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
    interface TableColumnNamesInjector {

        void accept(List<String> columnNames);
    }
}