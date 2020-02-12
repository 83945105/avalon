package pub.avalonframework.sqlhelper.core.data;

import pub.avalonframework.database.DatabaseType;
import pub.avalonframework.sqlhelper.core.api.config.SqlhelperConfiguration;
import pub.avalonframework.sqlhelper.core.utils.ExceptionUtils;

import java.util.LinkedHashMap;

/**
 * @author baichao
 */
public abstract class AbstractSqlDataCache implements SqlData {

    private MainTableDatum mainTableDatum;

    private SqlhelperConfiguration sqlhelperConfiguration;

    private DatabaseType databaseType;

    private LinkedHashMap<String, JoinTableDatum> aliasJoinTableData;

    public AbstractSqlDataCache(MainTableDatum mainTableDatum) {
        this.mainTableDatum = mainTableDatum;
    }

    @Override
    public SqlhelperConfiguration getSqlhelperConfiguration() {
        return sqlhelperConfiguration;
    }

    @Override
    public DatabaseType getDatabaseType() {
        return this.databaseType;
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
    public void setSqlhelperConfiguration(SqlhelperConfiguration sqlhelperConfiguration) {
        this.sqlhelperConfiguration = sqlhelperConfiguration;
    }

    @Override
    public void setDatabaseType(DatabaseType databaseType) {
        this.databaseType = databaseType;
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