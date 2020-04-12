package pub.avalonframework.database.sql.rule;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @author baichao
 */
public class TableRule implements Serializable {

    protected String tableName;

    protected List<ColumnRule> onRules = new LinkedList<>();

    protected List<ColumnRule> whereRules = new LinkedList<>();

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<? extends ColumnRule> getOnRules() {
        return onRules;
    }

    public void setOnRules(List<ColumnRule> onRules) {
        this.onRules = onRules;
    }

    public List<? extends ColumnRule> getWhereRules() {
        return whereRules;
    }

    public void setWhereRules(List<ColumnRule> whereRules) {
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