package pub.avalonframework.sqlhelper.core.data.store;

import pub.avalonframework.sqlhelper.core.api.config.SqlhelperConfiguration;
import pub.avalonframework.sqlhelper.core.data.JoinTableDatum;
import pub.avalonframework.sqlhelper.core.data.MainTableDatum;
import pub.avalonframework.sqlhelper.core.data.TableOnDatum;
import pub.avalonframework.sqlhelper.core.utils.ExceptionUtils;

import java.util.LinkedHashMap;

/**
 * @author baichao
 */
public abstract class AbstractDataStoreCache implements DataStore {

    private MainTableDatum mainTableDatum;

    private SqlhelperConfiguration configuration;

    private LinkedHashMap<String, JoinTableDatum> aliasJoinTableData;

    public AbstractDataStoreCache(MainTableDatum mainTableDatum) {
        this.mainTableDatum = mainTableDatum;
    }

    @Override
    public SqlhelperConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public MainTableDatum getMainTableDatum() {
        return this.mainTableDatum;
    }

    @Override
    public LinkedHashMap<String, JoinTableDatum> getAliasJoinTableData() {
        return this.aliasJoinTableData;
    }

    @Override
    public void setConfiguration(SqlhelperConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void addJoinTableDatum(JoinTableDatum joinTableDatum) {
        if (joinTableDatum == null) {
            return;
        }
        if (this.aliasJoinTableData == null) {
            this.aliasJoinTableData = new LinkedHashMap<>();
        }
        JoinTableDatum cache = this.aliasJoinTableData.get(joinTableDatum.getTableAlias());
        if (cache == null) {
            this.aliasJoinTableData.put(joinTableDatum.getTableAlias(), joinTableDatum);
            return;
        }
        cache.merge(joinTableDatum);
    }

    @Override
    public void addTableOnDatum(TableOnDatum tableOnDatum) {
        if (tableOnDatum == null) {
            return;
        }
        JoinTableDatum joinTableDatum = this.aliasJoinTableData.get(tableOnDatum.getTableAlias());
        if (joinTableDatum == null) {
            ExceptionUtils.notJoinException(tableOnDatum.getTableAlias());
        }
        joinTableDatum.appendTableOnDatum(tableOnDatum);
    }
}