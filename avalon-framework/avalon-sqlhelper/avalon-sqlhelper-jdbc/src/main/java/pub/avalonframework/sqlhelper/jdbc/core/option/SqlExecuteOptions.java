package pub.avalonframework.sqlhelper.jdbc.core.option;

/**
 * @author baichao
 */
public final class SqlExecuteOptions {

    private boolean allowReturnNull = true;

    public boolean isAllowReturnNull() {
        return allowReturnNull;
    }

    public void setAllowReturnNull(boolean allowReturnNull) {
        this.allowReturnNull = allowReturnNull;
    }
}