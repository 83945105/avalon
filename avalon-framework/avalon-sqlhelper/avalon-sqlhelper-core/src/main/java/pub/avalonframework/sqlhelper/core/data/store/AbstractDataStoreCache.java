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
public abstract class AbstractDataStoreCache<R> implements DataStore<R> {

    protected R owner;

    private TableMainDataBlock tableMainDataBlock;

    private SqlhelperConfiguration configuration;

    private LinkedHashMap<String, TableJoinDataBlock> aliasTableJoinDataBlockCache;

    public AbstractDataStoreCache(R owner, TableMainDataBlock tableMainDataBlock) {
        this.owner = owner;
        this.tableMainDataBlock = tableMainDataBlock;
    }

    public R getOwner() {
        return owner;
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
    public DataStore<R> getDataStore() {
        return this;
    }

    @Override
    public R setConfiguration(SqlhelperConfiguration configuration) {
        this.configuration = configuration;
        return owner;
    }

    @Override
    public R addTableJoinDataBlock(TableJoinDataBlock tableJoinDataBlock) {
        if (tableJoinDataBlock == null) {
            return owner;
        }
        if (this.aliasTableJoinDataBlockCache == null) {
            this.aliasTableJoinDataBlockCache = new LinkedHashMap<>();
        }
        TableJoinDataBlock cache = this.aliasTableJoinDataBlockCache.get(tableJoinDataBlock.getTableAlias());
        if (cache == null) {
            this.aliasTableJoinDataBlockCache.put(tableJoinDataBlock.getTableAlias(), tableJoinDataBlock);
            return owner;
        }
        cache.merge(tableJoinDataBlock);
        return owner;
    }

    @Override
    public R addTableOnDataBlock(TableOnDataBlock tableOnDataBlock) {
        if (tableOnDataBlock == null) {
            return owner;
        }
        TableJoinDataBlock tableJoinDataBlock = this.aliasTableJoinDataBlockCache.get(tableOnDataBlock.getTableAlias());
        if (tableJoinDataBlock == null) {
            ExceptionUtils.notJoinException(tableOnDataBlock.getTableAlias());
        }
        tableJoinDataBlock.appendTableOnDataBlock(tableOnDataBlock);
        return owner;
    }
}