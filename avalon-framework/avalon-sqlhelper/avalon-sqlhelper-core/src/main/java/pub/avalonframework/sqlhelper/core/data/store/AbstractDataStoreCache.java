package pub.avalonframework.sqlhelper.core.data.store;

import pub.avalonframework.sqlhelper.core.api.config.SqlhelperConfiguration;
import pub.avalonframework.sqlhelper.core.data.block.TableJoinDataBlock;
import pub.avalonframework.sqlhelper.core.data.block.TableMainDataBlock;
import pub.avalonframework.sqlhelper.core.data.block.TableOnDataBlock;
import pub.avalonframework.sqlhelper.core.utils.ExceptionUtils;

import java.util.LinkedHashMap;

/**
 * @author baichao
 */
public abstract class AbstractDataStoreCache implements DataStore {

    private TableMainDataBlock tableMainDataBlock;

    private SqlhelperConfiguration configuration;

    private LinkedHashMap<String, TableJoinDataBlock> aliasTableJoinDataBlockCache;

    public AbstractDataStoreCache(TableMainDataBlock tableMainDataBlock) {
        this.tableMainDataBlock = tableMainDataBlock;
    }

    @Override
    public SqlhelperConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public TableMainDataBlock getTableMainDataBlock() {
        return this.tableMainDataBlock;
    }

    @Override
    public LinkedHashMap<String, TableJoinDataBlock> getAliasTableJoinDataBlockMap() {
        return this.aliasTableJoinDataBlockCache;
    }

    @Override
    public void setConfiguration(SqlhelperConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void addTableJoinDataBlock(TableJoinDataBlock tableJoinDataBlock) {
        if (tableJoinDataBlock == null) {
            return;
        }
        if (this.aliasTableJoinDataBlockCache == null) {
            this.aliasTableJoinDataBlockCache = new LinkedHashMap<>();
        }
        TableJoinDataBlock cache = this.aliasTableJoinDataBlockCache.get(tableJoinDataBlock.getTableAlias());
        if (cache == null) {
            this.aliasTableJoinDataBlockCache.put(tableJoinDataBlock.getTableAlias(), tableJoinDataBlock);
            return;
        }
        cache.merge(tableJoinDataBlock);
    }

    @Override
    public void addTableOnDataBlock(TableOnDataBlock tableOnDataBlock) {
        if (tableOnDataBlock == null) {
            return;
        }
        TableJoinDataBlock tableJoinDataBlock = this.aliasTableJoinDataBlockCache.get(tableOnDataBlock.getTableAlias());
        if (tableJoinDataBlock == null) {
            ExceptionUtils.notJoinException(tableOnDataBlock.getTableAlias());
        }
        tableJoinDataBlock.appendTableOnDataBlock(tableOnDataBlock);
    }
}