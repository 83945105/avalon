package pub.avalonframework.security.data.rule;

import pub.avalonframework.database.sql.rule.TableRule;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author baichao
 */
public final class ParsedTableRule extends TableRule {

    private Type type;

    private String tableAlias;

    private List<ParsedColumnRule> onRules = new LinkedList<>();

    private List<ParsedColumnRule> whereRules = new LinkedList<>();

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

    public String getTableAlias() {
        return tableAlias;
    }

    public ParsedColumnRule addOnColumnRule() {
        ParsedColumnRule onColumnRule = new ParsedColumnRule(tableName, tableAlias);
        this.onRules.add(onColumnRule);
        return onColumnRule;
    }

    @Override
    public List<ParsedColumnRule> getOnRules() {
        return onRules;
    }

    public ParsedColumnRule addWhereColumnRule() {
        ParsedColumnRule whereColumnRule = new ParsedColumnRule(tableName, tableAlias);
        this.whereRules.add(whereColumnRule);
        return whereColumnRule;
    }

    @Override
    public List<ParsedColumnRule> getWhereRules() {
        return whereRules;
    }

    public void addSubRuleStore(String key, RuleStore subRoleStore) {
        this.subRuleStoreMap.put(key, subRoleStore);
    }

    public Map<String, RuleStore> getSubRuleStoreMap() {
        return subRuleStoreMap;
    }

    public enum Type {
        REAL, VIRTUAL
    }
}
