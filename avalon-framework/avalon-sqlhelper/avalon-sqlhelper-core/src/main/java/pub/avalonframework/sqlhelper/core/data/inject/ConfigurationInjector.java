package pub.avalonframework.sqlhelper.core.data.inject;

import pub.avalonframework.sqlhelper.core.api.config.SqlhelperConfiguration;

/**
 * @author baichao
 */
public interface ConfigurationInjector<R> extends DataInjector<R> {

    /**
     * Set configuration.
     *
     * @param configuration {@link SqlhelperConfiguration}
     * @return
     */
    default R setConfiguration(SqlhelperConfiguration configuration) {
        return this.getDataStore().setConfiguration(configuration);
    }
}