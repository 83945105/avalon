package pub.avalonframework.security.data.rule;

import pub.avalonframework.database.sql.rule.TableRule;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author baichao
 */
public final class ParsedTableRule extends TableRule {

    private Type type;

    private Map<String, RuleStore> subRuleStoreMap = new LinkedHashMap<>();

    public ParsedTableRule(String tableName, String tableAlias) {
        this.type = Type.REAL;
        this.tableName = tableName;
        this.tableAlias = tableAlias;
    }

    public ParsedTableRule(Type type, String tableAlias) {
        this.type = type;
        this.tableName = tableAlias;
        this.tableAlias = tableAlias;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void addSubRuleStore(String key, RuleStore subRoleStore) {
        this.subRuleStoreMap.put(key, subRoleStore);
    }

    public Map<String, RuleStore> getSubRuleStoreMap() {
        return subRuleStoreMap;
    }

    public void setSubRuleStoreMap(Map<String, RuleStore> subRuleStoreMap) {
        this.subRuleStoreMap = subRuleStoreMap;
    }

    public enum Type {
        REAL, VIRTUAL
    }
}