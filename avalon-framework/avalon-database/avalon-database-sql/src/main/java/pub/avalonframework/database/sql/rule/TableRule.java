package pub.avalonframework.database.sql.rule;

import pub.avalonframework.database.sql.expression.LogicExpression;

import java.io.Serializable;

/**
 * @author baichao
 */
public class TableRule implements Serializable {

    protected String tableName;

    protected String tableAlias;

    protected LogicExpression onRules;

    protected LogicExpression whereRules;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    public LogicExpression getOnRules() {
        return onRules;
    }

    public void setOnRules(LogicExpression onRules) {
        this.onRules = onRules;
    }

    public LogicExpression getWhereRules() {
        return whereRules;
    }

    public void setWhereRules(LogicExpression whereRules) {
        this.whereRules = whereRules;
    }

    public TableRule merge(TableRule target) {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        return sb.toString();
    }
}