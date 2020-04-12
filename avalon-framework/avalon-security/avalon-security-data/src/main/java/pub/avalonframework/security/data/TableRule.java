package pub.avalonframework.security.data;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author baichao
 */
public class TableRule implements Serializable {

    private Type type;

    private String tableName;

    private String tableAlias;

    private List<ColumnRule> onRules = new LinkedList<>();

    private List<ColumnRule> whereRules = new LinkedList<>();

    private Map<String, RuleStore> subRuleStoreMap = new LinkedHashMap<>();

    public TableRule(String tableName, String tableAlias) {
        this.type = Type.REAL;
        this.tableName = tableName;
        this.tableAlias = tableAlias;
    }

    public TableRule(Type type, String tableAlias) {
        this.type = type;
        this.tableName = tableAlias;
        this.tableAlias = tableAlias;
    }

    public ColumnRule addOnColumnRule() {
        ColumnRule onColumnRule = new ColumnRule(tableName, tableAlias);
        this.onRules.add(onColumnRule);
        return onColumnRule;
    }

    public ColumnRule addWhereColumnRule() {
        ColumnRule whereColumnRule = new ColumnRule(tableName, tableAlias);
        this.whereRules.add(whereColumnRule);
        return whereColumnRule;
    }

    public void addSubRuleStore(String key, RuleStore subRoleStore) {
        this.subRuleStoreMap.put(key, subRoleStore);
    }

    public Type getType() {
        return type;
    }

    public String getTableName() {
        return tableName;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<ColumnRule> getOnRules() {
        return onRules;
    }

    public List<ColumnRule> getWhereRules() {
        return whereRules;
    }

    public Map<String, RuleStore> getSubRuleStoreMap() {
        return subRuleStoreMap;
    }

    public TableRule merge(TableRule target) {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        return sb.toString();
    }

    public enum Type {
        REAL, VIRTUAL
    }
}