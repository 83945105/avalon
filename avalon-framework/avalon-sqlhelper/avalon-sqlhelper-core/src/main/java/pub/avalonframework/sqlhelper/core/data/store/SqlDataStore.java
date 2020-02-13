package pub.avalonframework.sqlhelper.core.data.store;

import pub.avalonframework.sqlhelper.core.api.config.SqlhelperConfiguration;
import pub.avalonframework.sqlhelper.core.data.block.TableMainDataBlock;

/**
 * @author baichao
 */
public final class SqlDataStore extends AbstractDataStore {

    public SqlDataStore(TableMainDataBlock mainTableDatum, SqlhelperConfiguration configuration) {
        super(mainTableDatum);
        this.setConfiguration(configuration);
    }
}