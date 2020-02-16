package pub.avalonframework.sqlhelper.core.api.config;

/**
 * Sql print configuration.
 *
 * @author baichao
 */
public class PrintConfiguration {

    private Boolean enabled;

    private Boolean colourEnabled;

    private Boolean sqlEnabled;

    private Boolean argsEnabled;

    public Boolean getEnabled() {
        return enabled;
    }

    public PrintConfiguration setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public Boolean getColourEnabled() {
        return colourEnabled;
    }

    public PrintConfiguration setColourEnabled(Boolean colourEnabled) {
        this.colourEnabled = colourEnabled;
        return this;
    }

    public Boolean getSqlEnabled() {
        return sqlEnabled;
    }

    public PrintConfiguration setSqlEnabled(Boolean sqlEnabled) {
        this.sqlEnabled = sqlEnabled;
        return this;
    }

    public Boolean getArgsEnabled() {
        return argsEnabled;
    }

    public PrintConfiguration setArgsEnabled(Boolean argsEnabled) {
        this.argsEnabled = argsEnabled;
        return this;
    }
}