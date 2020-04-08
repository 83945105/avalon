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
     * 设置运行时非空判断谓语表达式谓语
     *
     * @param tableAlias 表别名
     * @param columnName 列名
     */
    default void setRuntimeIsNullPredicatePredicate(String tableAlias, String columnName) {
        setRuntimeIsNullPredicatePredicate(buildColumnPredicate(tableAlias, columnName));
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
        setRuntimeBinaryComparisonPredicateSlavePredicate(buildColumnPredicate(tableAlias, columnName));
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
     * 设置运行时双边比较谓语表达式从谓语
     */
    default void setRuntimeBinaryComparisonPredicateSlavePredicate() {
        setRuntimeBinaryComparisonPredicateSlavePredicate(new SubqueryPredicate());
    }

    /**
     * 添加运行时模糊匹配谓语表达式
     */
    default void addRuntimeLikePredicate() {
        addRuntimePredicateExpression(new LikePredicate());
    }

    /**
     * 获取运行时模糊匹配谓语表达式
     *
     * @return 模糊匹配谓语表达式
     */
    default LikePredicate getRuntimeLikePredicate() {
        PredicateExpression predicateExpression = getRuntimePredicateExpression();
        if (predicateExpression instanceof LikePredicate) {
            return (LikePredicate) predicateExpression;
        }
        return null;
    }

    /**
     * 是否是运行时模糊匹配谓语表达式
     *
     * @return true | false
     */
    default boolean hasRuntimeLikePredicate() {
        return getRuntimeLikePredicate() != null;
    }

    /**
     * 运行时模糊匹配谓语是否设置过主谓语
     *
     * @return true | false
     */
    default boolean runtimeLikePredicateHasMasterPredicate() {
        LikePredicate likePredicate = getRuntimeLikePredicate();
        if (likePredicate == null) {
            throw new RuleContextException("SQL syntax error: runtimePredicateExpression is not of type LikePredicate.");
        }
        return likePredicate.hasMasterPredicate();
    }

    /**
     * 设置运行时模糊匹配谓语表达式主谓语
     *
     * @param predicate 谓语
     */
    default void setRuntimeLikePredicateMasterPredicate(Predicate predicate) {
        LikePredicate likePredicate = getRuntimeLikePredicate();
        if (likePredicate == null) {
            throw new RuleContextException("SQL syntax error: runtimePredicateExpression is not of type LikePredicate.");
        }
        likePredicate.setMasterPredicate(predicate);
    }

    /**
     * 设置运行时模糊匹配谓语表达式主谓语
     *
     * @param tableAlias 表别名
     * @param columnName 列名
     */
    default void setRuntimeLikePredicateMasterPredicate(String tableAlias, String columnName) {
        setRuntimeLikePredicateMasterPredicate(buildColumnPredicate(tableAlias, columnName));
    }

    /**
     * 运行时模糊匹配谓语是否设置过从谓语
     *
     * @return true | false
     */
    default boolean runtimeLikePredicateHasSlavePredicate() {
        LikePredicate likePredicate = getRuntimeLikePredicate();
        if (likePredicate == null) {
            throw new RuleContextException("SQL syntax error: runtimePredicateExpression is not of type LikePredicate.");
        }
        return likePredicate.hasSlavePredicate();
    }

    /**
     * 添加运行时模糊匹配谓语表达式从谓语
     *
     * @param predicate 谓语
     */
    default void setRuntimeLikePredicateSlavePredicate(Predicate predicate) {
        LikePredicate likePredicate = getRuntimeLikePredicate();
        if (likePredicate == null) {
            throw new RuleContextException("SQL syntax error: runtimePredicateExpression is not of type LikePredicate.");
        }
        likePredicate.setSlavePredicate(predicate);
    }

    /**
     * 设置运行时模糊匹配谓语表达式从谓语
     *
     * @param tableAlias 表别名
     * @param columnName 列名
     */
    default void setRuntimeLikePredicateSlavePredicate(String tableAlias, String columnName) {
        setRuntimeLikePredicateSlavePredicate(buildColumnPredicate(tableAlias, columnName));
    }

    /**
     * 设置运行时模糊匹配谓语表达式从谓语
     *
     * @param value 值
     */
    default void setRuntimeLikePredicateSlavePredicate(Object value) {
        setRuntimeLikePredicateSlavePredicate(new ConstantPredicate(value));
    }

    /**
     * 设置运行时模糊匹配谓语表达式从谓语
     */
    default void setRuntimeLikePredicateSlavePredicate() {
        setRuntimeLikePredicateSlavePredicate(new SubqueryPredicate());
    }

    /**
     * 添加运行时介于谓语表达式
     */
    default void addRuntimeBetweenPredicate() {
        addRuntimePredicateExpression(new BetweenPredicate());
    }

    /**
     * 获取运行时介于谓语表达式
     *
     * @return 介于谓语表达式
     */
    default BetweenPredicate getRuntimeBetweenPredicate() {
        PredicateExpression predicateExpression = getRuntimePredicateExpression();
        if (predicateExpression instanceof BetweenPredicate) {
            return (BetweenPredicate) predicateExpression;
        }
        return null;
    }

    /**
     * 是否是运行时介于谓语表达式
     *
     * @return true | false
     */
    default boolean hasRuntimeBetweenPredicate() {
        return getRuntimeBetweenPredicate() != null;
    }

    /**
     * 运行时介于谓语是否设置过谓语
     *
     * @return true | false
     */
    default boolean runtimeBetweenPredicateHasPredicate() {
        BetweenPredicate betweenPredicate = getRuntimeBetweenPredicate();
        if (betweenPredicate == null) {
            throw new RuleContextException("SQL syntax error: runtimePredicateExpression is not of type BetweenPredicate.");
        }
        return betweenPredicate.hasPredicate();
    }

    /**
     * 设置运行时介于谓语表达式谓语
     *
     * @param predicate 谓语
     */
    default void setRuntimeBetweenPredicatePredicate(Predicate predicate) {
        BetweenPredicate betweenPredicate = getRuntimeBetweenPredicate();
        if (betweenPredicate == null) {
            throw new RuleContextException("SQL syntax error: runtimePredicateExpression is not of type BetweenPredicate.");
        }
        betweenPredicate.setPredicate(predicate);
    }

    /**
     * 设置运行时介于谓语表达式谓语
     *
     * @param tableAlias 表别名
     * @param columnName 列名
     */
    default void setRuntimeBetweenPredicatePredicate(String tableAlias, String columnName) {
        setRuntimeBetweenPredicatePredicate(buildColumnPredicate(tableAlias, columnName));
    }

    /**
     * 运行时介于谓语是否设置过主值谓语
     *
     * @return true | false
     */
    default boolean runtimeBetweenPredicateHasMasterValuePredicate() {
        BetweenPredicate betweenPredicate = getRuntimeBetweenPredicate();
        if (betweenPredicate == null) {
            throw new RuleContextException("SQL syntax error: runtimePredicateExpression is not of type BetweenPredicate.");
        }
        return betweenPredicate.hasMasterValuePredicate();
    }

    /**
     * 添加运行时介于谓语表达式主值谓语
     *
     * @param predicate 谓语
     */
    default void setRuntimeBetweenPredicateMasterValuePredicate(Predicate predicate) {
        BetweenPredicate betweenPredicate = getRuntimeBetweenPredicate();
        if (betweenPredicate == null) {
            throw new RuleContextException("SQL syntax error: runtimePredicateExpression is not of type BetweenPredicate.");
        }
        betweenPredicate.setMasterValuePredicate(predicate);
    }

    /**
     * 添加运行时介于谓语表达式主值谓语
     *
     * @param tableAlias 表别名
     * @param columnName 列名
     */
    default void setRuntimeBetweenPredicateMasterValuePredicate(String tableAlias, String columnName) {
        setRuntimeBetweenPredicateMasterValuePredicate(buildColumnPredicate(tableAlias, columnName));
    }

    /**
     * 添加运行时介于谓语表达式主值谓语
     *
     * @param value 值
     */
    default void setRuntimeBetweenPredicateMasterValuePredicate(Object value) {
        setRuntimeBetweenPredicateMasterValuePredicate(new ConstantPredicate(value));
    }

    /**
     * 添加运行时介于谓语表达式主值谓语
     */
    default void setRuntimeBetweenPredicateMasterValuePredicate() {
        setRuntimeBetweenPredicateMasterValuePredicate(new SubqueryPredicate());
    }

    /**
     * 运行时介于谓语是否设置过从值谓语
     *
     * @return true | false
     */
    default boolean runtimeBetweenPredicateHasSlaveValuePredicate() {
        BetweenPredicate betweenPredicate = getRuntimeBetweenPredicate();
        if (betweenPredicate == null) {
            throw new RuleContextException("SQL syntax error: runtimePredicateExpression is not of type BetweenPredicate.");
        }
        return betweenPredicate.hasSlaveValuePredicate();
    }

    /**
     * 运行时介于谓语是否设置过从值谓语
     *
     * @param predicate 谓语
     */
    default void setRuntimeBetweenPredicateSlaveValuePredicate(Predicate predicate) {
        BetweenPredicate betweenPredicate = getRuntimeBetweenPredicate();
        if (betweenPredicate == null) {
            throw new RuleContextException("SQL syntax error: runtimePredicateExpression is not of type BetweenPredicate.");
        }
        betweenPredicate.setSlaveValuePredicate(predicate);
    }

    /**
     * 运行时介于谓语是否设置过从值谓语
     *
     * @param tableAlias 表别名
     * @param columnName 列名
     */
    default void setRuntimeBetweenPredicateSlaveValuePredicate(String tableAlias, String columnName) {
        setRuntimeBetweenPredicateSlaveValuePredicate(buildColumnPredicate(tableAlias, columnName));
    }

    /**
     * 运行时介于谓语是否设置过从值谓语
     *
     * @param value 值
     */
    default void setRuntimeBetweenPredicateSlaveValuePredicate(Object value) {
        setRuntimeBetweenPredicateSlaveValuePredicate(new ConstantPredicate(value));
    }

    /**
     * 运行时介于谓语是否设置过从值谓语
     */
    default void setRuntimeBetweenPredicateSlaveValuePredicate() {
        setRuntimeBetweenPredicateSlaveValuePredicate(new SubqueryPredicate());
    }

    /**
     * 添加运行时在...内谓语表达式
     */
    default void addRuntimeInPredicate() {
        addRuntimePredicateExpression(new InPredicate());
    }

    /**
     * 获取运行时在...内谓语表达式
     *
     * @return 在...内谓语表达式
     */
    default InPredicate getRuntimeInPredicate() {
        PredicateExpression predicateExpression = getRuntimePredicateExpression();
        if (predicateExpression instanceof InPredicate) {
            return (InPredicate) predicateExpression;
        }
        return null;
    }

    /**
     * 是否是运行时在...内谓语表达式
     *
     * @return true | false
     */
    default boolean hasRuntimeInPredicate() {
        return getRuntimeInPredicate() != null;
    }

    /**
     * 运行时在...内谓语是否设置过谓语
     *
     * @return true | false
     */
    default boolean runtimeInPredicateHasPredicate() {
        InPredicate inPredicate = getRuntimeInPredicate();
        if (inPredicate == null) {
            throw new RuleContextException("SQL syntax error: runtimePredicateExpression is not of type InPredicate.");
        }
        return inPredicate.hasPredicate();
    }

    /**
     * 设置运行时在...内谓语表达式谓语
     *
     * @param predicate 谓语
     */
    default void setRuntimeInPredicatePredicate(Predicate predicate) {
        InPredicate inPredicate = getRuntimeInPredicate();
        if (inPredicate == null) {
            throw new RuleContextException("SQL syntax error: runtimePredicateExpression is not of type InPredicate.");
        }
        inPredicate.setPredicate(predicate);
    }

    /**
     * 设置运行时在...内谓语表达式谓语
     *
     * @param tableAlias 表别名
     * @param columnName 列名
     */
    default void setRuntimeInPredicatePredicate(String tableAlias, String columnName) {
        setRuntimeInPredicatePredicate(buildColumnPredicate(tableAlias, columnName));
    }

    /**
     * 设置运行时在...内谓语表达式比较运算符
     *
     * @param comparisonOperator 比较运算符
     */
    default void setRuntimeInPredicateComparisonOperator(ComparisonOperator comparisonOperator) {
        InPredicate inPredicate = getRuntimeInPredicate();
        if (inPredicate == null) {
            throw new RuleContextException("SQL syntax error: runtimePredicateExpression is not of type InPredicate.");
        }
        inPredicate.setComparisonOperator(comparisonOperator);
    }

    /**
     * 添加运行时在...内谓语表达式值谓语
     *
     * @param predicate 谓语
     */
    default void addRuntimeInPredicateValuePredicate(Predicate predicate) {
        InPredicate inPredicate = getRuntimeInPredicate();
        if (inPredicate == null) {
            throw new RuleContextException("SQL syntax error: runtimePredicateExpression is not of type InPredicate.");
        }
        inPredicate.addValuePredicates(predicate);
    }

    /**
     * 添加运行时在...内谓语表达式值谓语
     *
     * @param tableAlias 表别名
     * @param columnName 列名
     */
    default void addRuntimeInPredicateValuePredicate(String tableAlias, String columnName) {
        InPredicate inPredicate = getRuntimeInPredicate();
        if (inPredicate == null) {
            throw new RuleContextException("SQL syntax error: runtimePredicateExpression is not of type InPredicate.");
        }
        InPredicate slavePredicate = new InPredicate();
        slavePredicate.setPredicate(buildColumnPredicate(tableAlias, columnName));
        inPredicate.addValuePredicates(slavePredicate.getPredicate());
    }

    /**
     * 添加运行时在...内谓语表达式值谓语
     *
     * @param value 值
     */
    default void addRuntimeInPredicateValuePredicate(Object value) {
        addRuntimeInPredicateValuePredicate(new ConstantPredicate(value));
    }

    /**
     * 添加运行时在...内谓语表达式值谓语
     */
    default void addRuntimeInPredicateValuePredicate() {
        addRuntimeInPredicateValuePredicate(new SubqueryPredicate());
    }

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