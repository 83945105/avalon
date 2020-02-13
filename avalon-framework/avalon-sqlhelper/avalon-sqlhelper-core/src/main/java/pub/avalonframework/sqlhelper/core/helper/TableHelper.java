package pub.avalonframework.sqlhelper.core.helper;

import pub.avalonframework.sqlhelper.core.beans.TableColumn;

import java.util.Set;

/**
 * @author baichao
 */
public interface TableHelper<T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
        TC extends ColumnHelper<TC>,
        TO extends OnHelper<TO>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>> {

    /**
     * Get table name
     *
     * @return table name
     */
    String getTableName();

    /**
     * Get table alias
     *
     * @return table alias
     */
    String getTableAlias();

    /**
     * Get primary key name
     *
     * @return primary key name
     */
    String getPrimaryKeyName();

    /**
     * Get primary key alias
     *
     * @return primary key alias
     */
    String getPrimaryKeyAlias();

    /**
     * Get table columns
     *
     * @return set {@link TableColumn}
     */
    Set<TableColumn> getTableColumns();

    /**
     * Get extends {@link Helper} single object
     *
     * @return extends {@link Helper} single object
     */
    T getDefaultInstance();

    /**
     * Create new extends {@link ColumnHelper} class object
     *
     * @param tableAlias table alias
     * @return extends {@link ColumnHelper} class object
     */
    TC newColumnHelper(String tableAlias);

    /**
     * Create new extends {@link OnHelper} class object
     *
     * @param tableAlias table alias
     * @return extends {@link OnHelper} class object
     */
    TO newOnHelper(String tableAlias);

    /**
     * Create new extends {@link WhereHelper} class object
     *
     * @param tableAlias table alias
     * @return extends {@link WhereHelper} class object
     */
    TW newWhereHelper(String tableAlias);

    /**
     * Create new extends {@link GroupHelper} class object
     *
     * @param tableAlias table alias
     * @return extends {@link GroupHelper} class object
     */
    TG newGroupHelper(String tableAlias);

    /**
     * Create new extends {@link HavingHelper} class object
     *
     * @param tableAlias table alias
     * @return extends {@link HavingHelper} class object
     */
    TH newHavingHelper(String tableAlias);

    /**
     * Create new extends {@link SortHelper} class object
     *
     * @param tableAlias table alias
     * @return extends {@link SortHelper} class object
     */
    TS newSortHelper(String tableAlias);
}