package pub.avalonframework.security.data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author baichao
 */
public class RuleStore {

    private Map<String, TableRule> tableNameTableRuleMap = new LinkedHashMap<>();

    private Map<String, TableRule> tableAliasTableRuleMap = new LinkedHashMap<>();

    public TableRule tableRuleForTableAlias(String tableName, String tableAlias) {
        TableRule tableRule = tableAliasTableRuleMap.get(tableAlias);
        if (tableRule == null) {
            tableRule = new TableRule(tableName, tableAlias);
            tableAliasTableRuleMap.put(tableAlias, tableRule);
        }
        return tableRule;
    }
}