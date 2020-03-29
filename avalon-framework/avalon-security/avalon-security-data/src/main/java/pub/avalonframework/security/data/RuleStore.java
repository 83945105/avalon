package pub.avalonframework.security.data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author baichao
 */
public class RuleStore {

    /**
     * 存储自定义表规则
     * key - tableName
     * value - tableRule
     */
    private Map<String, TableRuleOperations> customTableRuleMap = new LinkedHashMap<>();

    /**
     * 运行时解析的表规则
     * key - tableAlias
     * value - tableRule
     */
    private Map<String, TableRuleOperations> runtimeTableRuleMap = new LinkedHashMap<>();

    private Map<String, RuleStore> runtimeSubRuleStoreMap = new LinkedHashMap<>();

    public TableRuleOperations addRuntimeRealTableRule(String tableName, String tableAlias) {
        TableRuleOperations tableRule = runtimeTableRuleMap.get(tableAlias);
        if (tableRule == null) {
            tableRule = new TableRule(tableName, tableAlias);
            runtimeTableRuleMap.put(tableAlias, tableRule);
        }
        return tableRule;
    }

    public TableRuleOperations addRuntimeVirtualTableRule(String tableAlias) {
        TableRuleOperations tableRule = runtimeTableRuleMap.get(tableAlias);
        if (tableRule == null) {
            tableRule = new TableRule(TableRuleOperations.Type.VIRTUAL, tableAlias);
            runtimeTableRuleMap.put(tableAlias, tableRule);
        }
        return tableRule;
    }

    public void addRuntimeSubRuleStore(String tableAlias, String key, RuleStore subRuleStore) {
        if (tableAlias == null || key == null || subRuleStore == null) {
            throw new RuleContextException();
        }
        if (runtimeSubRuleStoreMap.containsKey(key)) {
            throw new RuleContextException();
        }
        TableRuleOperations tableRule = runtimeTableRuleMap.get(tableAlias);
        if (tableRule == null) {
            throw new RuleContextException();
        }
        tableRule.addSubRuleStore(key, subRuleStore);
        runtimeSubRuleStoreMap.put(key, subRuleStore);
    }

    public Map<String, TableRuleOperations> getCustomTableRuleMap() {
        return customTableRuleMap;
    }

    public Map<String, TableRuleOperations> getRuntimeTableRuleMap() {
        return runtimeTableRuleMap;
    }

    public Map<String, RuleStore> getRuntimeSubRuleStoreMap() {
        return runtimeSubRuleStoreMap;
    }
}