package pub.avalonframework.security.data;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author baichao
 */
public class TableRule implements TableRuleOperations {

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

    @Override
    public ColumnRule addOnColumnRule() {
        ColumnRule onColumnRule = new ColumnRule(tableName, tableAlias);
        this.onRules.add(onColumnRule);
        return onColumnRule;
    }

    @Override
    public ColumnRule addWhereColumnRule() {
        ColumnRule whereColumnRule = new ColumnRule(tableName, tableAlias);
        this.whereRules.add(whereColumnRule);
        return whereColumnRule;
    }

    @Override
    public void addSubRuleStore(String key, RuleStore subRoleStore) {
        this.subRuleStoreMap.put(key, subRoleStore);
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public String getTableAlias() {
        return tableAlias;
    }

    @Override
    public List<ColumnRule> getOnRules() {
        return onRules;
    }

    @Override
    public List<ColumnRule> getWhereRules() {
        return whereRules;
    }

    @Override
    public Map<String, RuleStore> getSubRuleStoreMap() {
        return subRuleStoreMap;
    }

    @Override
    public TableRuleOperations merge(TableRuleOperations target) {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        return sb.toString();
    }
}