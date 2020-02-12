package pub.avalonframework.sqlhelper.core.api.config;

/**
 * Sql print configuration.
 *
 * @author baichao
 */
public class SqlPrintConfiguration {

    private Boolean enabled;

    private Boolean colourEnabled;

    private Boolean sqlEnabled;

    private Boolean argsEnabled;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getColourEnabled() {
        return colourEnabled;
    }

    public void setColourEnabled(Boolean colourEnabled) {
        this.colourEnabled = colourEnabled;
    }

    public Boolean getSqlEnabled() {
        return sqlEnabled;
    }

    public void setSqlEnabled(Boolean sqlEnabled) {
        this.sqlEnabled = sqlEnabled;
    }

    public Boolean getArgsEnabled() {
        return argsEnabled;
    }

    public void setArgsEnabled(Boolean argsEnabled) {
        this.argsEnabled = argsEnabled;
    }
}