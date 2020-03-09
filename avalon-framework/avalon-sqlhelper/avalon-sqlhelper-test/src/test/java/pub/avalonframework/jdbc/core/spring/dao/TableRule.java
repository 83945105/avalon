package pub.avalonframework.jdbc.core.spring.dao;

import java.util.LinkedHashMap;

/**
 * @author baichao
 */
public class TableRule {

    private String tableName;

    private LinkedHashMap<String, ColumnRule> columnRules = new LinkedHashMap<>();

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public LinkedHashMap<String, ColumnRule> getColumnRules() {
        return columnRules;
    }

    public void setColumnRules(LinkedHashMap<String, ColumnRule> columnRules) {
        this.columnRules = columnRules;
    }
}