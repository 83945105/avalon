package pub.avalonframework.sqlhelper.core.builder.beans;

import pub.avalonframework.sqlhelper.core.callback.HavingJoinCallback;
import pub.avalonframework.sqlhelper.core.callback.executor.CallbackExecutor;
import pub.avalonframework.sqlhelper.core.data.TableHavingDatum;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;

import java.util.Collections;
import java.util.List;

/**
 * @author baichao
 */
public final class HavingBuilderBeanJoin<TH extends HavingHelper<TH>,
        S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
        SC extends ColumnHelper<SC>,
        SO extends OnHelper<SO>,
        SW extends WhereHelper<SW>,
        SG extends GroupHelper<SG>,
        SH extends HavingHelper<SH>,
        SS extends SortHelper<SS>> extends AbstractHavingBuilderBean {

    private TH havingHelper;

    private Class<S> tableHelperClass;

    private HavingJoinCallback<TH, SH> havingJoinCallback;

    public HavingBuilderBeanJoin(TH havingHelper, Class<S> tableHelperClass, String tableAlias, HavingJoinCallback<TH, SH> havingJoinCallback) {
        super(tableAlias);
        this.havingHelper = havingHelper;
        this.tableHelperClass = tableHelperClass;
        this.havingJoinCallback = havingJoinCallback;
    }

    @Override
    public List<TableHavingDatum> execute(SqlBuilderOptions sqlBuilderOptions) {
        return Collections.singletonList(CallbackExecutor.execute(this.havingHelper, this.tableHelperClass, this.tableAlias, this.havingJoinCallback, sqlBuilderOptions));
    }
}