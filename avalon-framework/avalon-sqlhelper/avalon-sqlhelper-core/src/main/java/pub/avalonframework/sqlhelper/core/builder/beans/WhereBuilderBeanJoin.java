package pub.avalonframework.sqlhelper.core.builder.beans;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.callback.WhereJoinCallback;
import pub.avalonframework.sqlhelper.core.callback.executor.CallbackExecutor;
import pub.avalonframework.sqlhelper.core.data.block.TableWhereDataBlock;
import pub.avalonframework.sqlhelper.core.helper.*;

import java.util.Collections;
import java.util.List;

/**
 * @author baichao
 */
public final class WhereBuilderBeanJoin<TW extends WhereHelper<TW>,
        S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
        SC extends ColumnHelper<SC>,
        SO extends OnHelper<SO>,
        SW extends WhereHelper<SW>,
        SG extends GroupHelper<SG>,
        SH extends HavingHelper<SH>,
        SS extends SortHelper<SS>> extends AbstractWhereBuilderBean {

    private TW whereHelper;

    private Class<S> tableHelperClass;

    private WhereJoinCallback<TW, SW> whereJoinCallback;

    public WhereBuilderBeanJoin(TW whereHelper, Class<S> tableHelperClass, String tableAlias, WhereJoinCallback<TW, SW> whereJoinCallback) {
        super(tableAlias);
        this.whereHelper = whereHelper;
        this.tableHelperClass = tableHelperClass;
        this.whereJoinCallback = whereJoinCallback;
    }

    @Override
    public List<TableWhereDataBlock> execute(SqlBuilderConfiguration sqlBuilderConfiguration) {
        return Collections.singletonList(CallbackExecutor.execute(this.whereHelper, this.tableHelperClass, this.tableAlias, this.whereJoinCallback, sqlBuilderConfiguration));
    }
}