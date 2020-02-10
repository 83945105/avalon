package pub.avalonframework.sqlhelper.core.builder;

import pub.avalonframework.sqlhelper.core.helper.*;

/**
 * @author baichao
 */
public interface BuilderSelectBlock<TC extends ColumnHelper<TC>,
        TO extends OnHelper<TO>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>> {

}