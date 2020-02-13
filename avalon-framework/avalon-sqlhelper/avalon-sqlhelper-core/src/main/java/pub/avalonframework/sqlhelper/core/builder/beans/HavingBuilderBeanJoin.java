package pub.avalonframework.sqlhelper.core.builder.beans;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.callback.HavingJoinCallback;
import pub.avalonframework.sqlhelper.core.callback.executor.CallbackExecutor;
import pub.avalonframework.sqlhelper.core.data.block.TableHavingDataBlock;
import pub.avalonframework.sqlhelper.core.helper.*;

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
    public List<TableHavingDataBlock> execute(SqlBuilderConfiguration sqlBuilderConfiguration) {
        return Collections.singletonList(CallbackExecutor.execute(this.havingHelper, this.tableHelperClass, this.tableAlias, this.havingJoinCallback, sqlBuilderConfiguration));
    }
}