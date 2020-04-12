package pub.avalonframework.security.data.rule;

import pub.avalonframework.database.sql.rule.TableRule;

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
    private Map<String, TableRule> customTableRuleMap = new LinkedHashMap<>();

    /**
     * 运行时解析的表规则
     * key - tableAlias
     * value - tableRule
     */
    private Map<String, ParsedTableRule> runtimeTableRuleMap = new LinkedHashMap<>();

    private Map<String, RuleStore> runtimeSubRuleStoreMap = new LinkedHashMap<>();

    public ParsedTableRule addRuntimeRealTableRule(String tableName, String tableAlias) {
        ParsedTableRule tableRule = runtimeTableRuleMap.get(tableAlias);
        if (tableRule == null) {
            tableRule = new ParsedTableRule(tableName, tableAlias);
            runtimeTableRuleMap.put(tableAlias, tableRule);
        }
        return tableRule;
    }

    public ParsedTableRule addRuntimeVirtualTableRule(String tableAlias) {
        ParsedTableRule tableRule = runtimeTableRuleMap.get(tableAlias);
        if (tableRule == null) {
            tableRule = new ParsedTableRule(ParsedTableRule.Type.VIRTUAL, tableAlias);
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
        ParsedTableRule tableRule = runtimeTableRuleMap.get(tableAlias);
        if (tableRule == null) {
            throw new RuleContextException();
        }
        tableRule.addSubRuleStore(key, subRuleStore);
        runtimeSubRuleStoreMap.put(key, subRuleStore);
    }

    public Map<String, TableRule> getCustomTableRuleMap() {
        return customTableRuleMap;
    }

    public Map<String, ParsedTableRule> getRuntimeTableRuleMap() {
        return runtimeTableRuleMap;
    }

    public Map<String, RuleStore> getRuntimeSubRuleStoreMap() {
        return runtimeSubRuleStoreMap;
    }
}