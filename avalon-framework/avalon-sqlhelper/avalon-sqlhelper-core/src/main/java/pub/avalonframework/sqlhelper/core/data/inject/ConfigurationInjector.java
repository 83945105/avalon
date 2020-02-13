package pub.avalonframework.sqlhelper.core.data.inject;

import pub.avalonframework.sqlhelper.core.api.config.SqlhelperConfiguration;

/**
 * @author baichao
 */
public interface ConfigurationInjector {

    /**
     * Set configuration.
     *
     * @param configuration {@link SqlhelperConfiguration}
     */
    void setConfiguration(SqlhelperConfiguration configuration);
}