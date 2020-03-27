package pub.avalonframework.security.data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author baichao
 */
public class RuleStore {

    private Map<String, TableRuleOperations> customTableRuleMap = new LinkedHashMap<>();

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

    public void addRuntimeSubRuleStore(String key, RuleStore subRuleStore) {
        if (runtimeSubRuleStoreMap.containsKey(key)) {
            throw new RuleContextException();
        }
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