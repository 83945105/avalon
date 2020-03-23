package pub.avalonframework.security.data;

import java.util.LinkedList;
import java.util.List;

/**
 * @author baichao
 */
public class TableRule implements TableRuleOperations {

    private String tableName;

    private String tableAlias;

    private List<OnColumnRule> onRules = new LinkedList<>();

    private List<WhereColumnRule> whereRules = new LinkedList<>();

    public TableRule(String tableName, String tableAlias) {
        this.tableName = tableName;
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

    public String getTableName() {
        return tableName;
    }

    public String getTableAlias() {
        return tableAlias;
    }
}