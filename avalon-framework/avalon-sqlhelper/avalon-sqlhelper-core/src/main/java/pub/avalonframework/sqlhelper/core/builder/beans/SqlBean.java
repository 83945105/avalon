package pub.avalonframework.sqlhelper.core.builder.beans;

/**
 * @author baichao
 */
public abstract class SqlBean {

    protected String tableAlias;

    public SqlBean(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }
}