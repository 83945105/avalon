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

    private TableMainDataBlock mainTableDatum;

    private SqlhelperConfiguration configuration;

    private LinkedHashMap<String, TableJoinDataBlock> aliasJoinTableData;

    public AbstractDataStoreCache(TableMainDataBlock mainTableDatum) {
        this.mainTableDatum = mainTableDatum;
    }

    @Override
    public SqlhelperConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public TableMainDataBlock getMainTableDatum() {
        return this.mainTableDatum;
    }

    @Override
    public LinkedHashMap<String, TableJoinDataBlock> getAliasJoinTableData() {
        return this.aliasJoinTableData;
    }

    @Override
    public void setConfiguration(SqlhelperConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void addJoinTableDatum(TableJoinDataBlock joinTableDatum) {
        if (joinTableDatum == null) {
            return;
        }
        if (this.aliasJoinTableData == null) {
            this.aliasJoinTableData = new LinkedHashMap<>();
        }
        TableJoinDataBlock cache = this.aliasJoinTableData.get(joinTableDatum.getTableAlias());
        if (cache == null) {
            this.aliasJoinTableData.put(joinTableDatum.getTableAlias(), joinTableDatum);
            return;
        }
        cache.merge(joinTableDatum);
    }

    @Override
    public void addTableOnDatum(TableOnDataBlock tableOnDatum) {
        if (tableOnDatum == null) {
            return;
        }
        TableJoinDataBlock joinTableDatum = this.aliasJoinTableData.get(tableOnDatum.getTableAlias());
        if (joinTableDatum == null) {
            ExceptionUtils.notJoinException(tableOnDatum.getTableAlias());
        }
        joinTableDatum.appendTableOnDatum(tableOnDatum);
    }
}