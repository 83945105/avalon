package pub.avalonframework.security.data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author baichao
 */
public class RuleStore {

    private Map<String, TableRuleOperations> customTableRuleMap = new LinkedHashMap<>();

    private Map<String, TableRuleOperations> runtimeTableRuleMap = new LinkedHashMap<>();

    public TableRuleOperations addRuntimeTableRule(String tableName, String tableAlias) {
        TableRuleOperations tableRule = runtimeTableRuleMap.get(tableAlias);
        if (tableRule == null) {
            tableRule = new TableRule(tableName, tableAlias);
            runtimeTableRuleMap.put(tableAlias, tableRule);
        }
        return tableRule;
    }

    public Map<String, TableRuleOperations> getCustomTableRuleMap() {
        return customTableRuleMap;
    }

    public Map<String, TableRuleOperations> getRuntimeTableRuleMap() {
        return runtimeTableRuleMap;
    }
}