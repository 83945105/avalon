package pub.avalonframework.jdbc.core.spring.dao;

/**
 * @author baichao
 */
public class ColumnRule {

    private String columnName;

    private Op op;

    private Object value;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Op getOp() {
        return op;
    }

    public void setOp(Op op) {
        this.op = op;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}