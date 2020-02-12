package pub.avalonframework.sqlhelper.core.data;

import pub.avalonframework.sqlhelper.core.api.config.SqlhelperConfiguration;

/**
 * @author baichao
 */
public interface SqlDataOptionsProducer {

    /**
     * set configuration.
     *
     * @param configuration {@link SqlhelperConfiguration}
     */
    void setConfiguration(SqlhelperConfiguration configuration);
}