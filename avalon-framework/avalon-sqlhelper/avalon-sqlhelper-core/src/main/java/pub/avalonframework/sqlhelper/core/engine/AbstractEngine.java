package pub.avalonframework.sqlhelper.core.engine;

import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.utils.ExceptionUtils;

/**
 * @author baichao
 */
public abstract class AbstractEngine<T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
        TC extends ColumnHelper<TC>,
        TO extends OnHelper<TO>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>> implements Engine<T, TC, TO, TW, TG, TH, TS> {

    protected Class<T> tableHelperClass;

    protected String tableAlias;

    public AbstractEngine(Class<T> tableHelperClass, String tableAlias) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        if (tableAlias == null) {
            ExceptionUtils.tableAliasNullException();
        }
        this.tableHelperClass = tableHelperClass;
        this.tableAlias = tableAlias;
    }

    @Override
    public Class<T> getTableHelperClass() {
        return tableHelperClass;
    }

    @Override
    public String getTableAlias() {
        return tableAlias;
    }

    public void setTableHelperClass(Class<T> tableHelperClass) {
        this.tableHelperClass = tableHelperClass;
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }
}