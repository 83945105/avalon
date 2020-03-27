package pub.avalonframework.security.data;

import java.util.LinkedList;
import java.util.List;

/**
 * @author baichao
 */
public class TableRule implements TableRuleOperations {

    private Type type;

    private String tableName;

    private String tableAlias;

    private List<OnColumnRule> onRules = new LinkedList<>();

    private List<WhereColumnRule> whereRules = new LinkedList<>();

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
    public OnColumnRule addOnColumnRule() {
        OnColumnRule onColumnRule = new OnColumnRule(tableName, tableAlias);
        this.onRules.add(onColumnRule);
        return onColumnRule;
    }

    @Override
    public WhereColumnRule addWhereColumnRule() {
        WhereColumnRule whereColumnRule = new WhereColumnRule(tableName, tableAlias);
        this.whereRules.add(whereColumnRule);
        return whereColumnRule;
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
    public List<OnColumnRule> getOnRules() {
        return onRules;
    }

    @Override
    public List<WhereColumnRule> getWhereRules() {
        return whereRules;
    }
}