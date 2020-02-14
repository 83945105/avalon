package pub.avalonframework.sqlhelper.core.data.store;

import pub.avalonframework.sqlhelper.core.api.config.SqlhelperConfiguration;
import pub.avalonframework.sqlhelper.core.data.block.TableMainDataBlock;

/**
 * @author baichao
 */
public final class SqlDataStore<R> extends AbstractDataStore<R> {

    public SqlDataStore(R owner, String tableName, Class<?> tableHelperClass, String tableAlias, SqlhelperConfiguration configuration) {
        super(owner, new TableMainDataBlock(tableHelperClass, tableName, tableAlias));
        this.setConfiguration(configuration);
    }
}