package pub.avalonframework.security.data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author baichao
 */
public class RuleStore {

    private Map<String, TableRuleOperations> tableNameTableRuleMap = new LinkedHashMap<>();

    private Map<String, TableRuleOperations> tableAliasTableRuleMap = new LinkedHashMap<>();

    public TableRuleOperations putTableRule(String tableName, String tableAlias) {
        TableRuleOperations tableRule = tableAliasTableRuleMap.get(tableAlias);
        if (tableRule == null) {
            tableRule = new TableRule(tableName, tableAlias);
            tableAliasTableRuleMap.put(tableAlias, tableRule);
        }
        return tableRule;
    }
}