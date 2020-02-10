package pub.avalonframework.sqlhelper.core.builder.beans;

/**
 * @author baichao
 */
public abstract class BuilderBean {

    protected String tableAlias;

    public BuilderBean(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }
}