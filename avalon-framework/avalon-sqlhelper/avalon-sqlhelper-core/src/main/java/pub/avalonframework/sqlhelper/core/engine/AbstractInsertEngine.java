package pub.avalonframework.sqlhelper.core.engine;

import pub.avalonframework.database.DatabaseType;
import pub.avalonframework.sqlhelper.core.api.config.SqlhelperConfiguration;
import pub.avalonframework.sqlhelper.core.data.TableColumnDatum;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.sqlbuilder.InsertSqlBuilder;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.InsertSqlBuilderResult;

import java.util.Collection;

/**
 * @author baichao
 */
public abstract class AbstractInsertEngine<T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
        TC extends ColumnHelper<TC>,
        TO extends OnHelper<TO>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>> extends AbstractEngine<T, TC, TO, TW, TG, TH, TS> implements InsertSqlBuilder, InsertEngine<T, TC, TO, TW, TG, TH, TS> {

    public AbstractInsertEngine(DatabaseType databaseType, Class<T> tableHelperClass) {
        super(databaseType, tableHelperClass);
    }

    public AbstractInsertEngine(DatabaseType databaseType, Class<T> tableHelperClass, SqlhelperConfiguration sqlhelperConfiguration) {
        super(databaseType, tableHelperClass, sqlhelperConfiguration);
    }

    public AbstractInsertEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass) {
        super(databaseType, tableName, tableHelperClass);
    }

    public AbstractInsertEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, SqlhelperConfiguration sqlhelperConfiguration) {
        super(databaseType, tableName, tableHelperClass, sqlhelperConfiguration);
    }

    public AbstractInsertEngine(DatabaseType databaseType, Class<T> tableHelperClass, String tableAlias) {
        super(databaseType, tableHelperClass, tableAlias);
    }

    public AbstractInsertEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, String tableAlias) {
        super(databaseType, tableName, tableHelperClass, tableAlias);
    }

    public AbstractInsertEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, String tableAlias, SqlhelperConfiguration sqlhelperConfiguration) {
        super(databaseType, tableName, tableHelperClass, tableAlias, sqlhelperConfiguration);
    }

    @Override
    public InsertSqlBuilderResult insertArgs(Object... args) {
        return this.crudSqlBuilder.insertArgs(args);
    }

    @Override
    public InsertSqlBuilderResult insertJavaBean(Object javaBean) {
        return this.crudSqlBuilder.insertJavaBean(javaBean);
    }

    @Override
    public InsertSqlBuilderResult insertJavaBeanSelective(Object javaBean) {
        return this.crudSqlBuilder.insertJavaBeanSelective(javaBean);
    }

    @Override
    public InsertSqlBuilderResult batchInsertJavaBeans(Collection<?> javaBeans) {
        return this.crudSqlBuilder.batchInsertJavaBeans(javaBeans);
    }

    public DatabaseType getDatabaseType() {
        return this.sqlData.getDatabaseType();
    }

    @Override
    public void addInsertTableColumnDatum(TableColumnDatum tableColumnDatum) {
        this.sqlData.addInsertTableColumnDatum(tableColumnDatum);
    }
}