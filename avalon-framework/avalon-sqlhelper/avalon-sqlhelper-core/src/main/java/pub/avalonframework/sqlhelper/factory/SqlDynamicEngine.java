package pub.avalonframework.sqlhelper.factory;

import pub.avalonframework.database.DatabaseType;
import pub.avalonframework.sqlhelper.core.engine.SqlHelperEngine;
import pub.avalonframework.sqlhelper.core.helper.*;

/**
 * Sql动态引擎
 *
 * @author baichao
 * @date 2019/2/22
 */
public class SqlDynamicEngine {

    public static <T extends TableHelper<T, TO, TC, TW, TG, TH, TS>,
            TO extends OnHelper<TO>,
            TC extends ColumnHelper<TC>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> table(DatabaseType databaseType, String tableName, Class<T> tableHelperClass) {
        return new SqlHelperEngine<>(databaseType, tableName, tableHelperClass);
    }

    public static <T extends TableHelper<T, TO, TC, TW, TG, TH, TS>,
            TO extends OnHelper<TO>,
            TC extends ColumnHelper<TC>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> table(String tableName, Class<T> tableHelperClass) {
        return new SqlHelperEngine<>(null, tableName, tableHelperClass);
    }

    public static <T extends TableHelper<T, TO, TC, TW, TG, TH, TS>,
            TO extends OnHelper<TO>,
            TC extends ColumnHelper<TC>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> table(DatabaseType databaseType, Class<T> tableHelperClass) {
        return new SqlHelperEngine<>(databaseType, tableHelperClass);
    }

    public static <T extends TableHelper<T, TO, TC, TW, TG, TH, TS>,
            TO extends OnHelper<TO>,
            TC extends ColumnHelper<TC>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> table(Class<T> tableHelperClass) {
        return new SqlHelperEngine<>(null, tableHelperClass);
    }

}
