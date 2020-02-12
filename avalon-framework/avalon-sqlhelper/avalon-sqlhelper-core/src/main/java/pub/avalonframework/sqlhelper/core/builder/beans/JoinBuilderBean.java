package pub.avalonframework.sqlhelper.core.builder.beans;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.beans.JoinType;
import pub.avalonframework.sqlhelper.core.block.callback.executor.CallbackBlockExecutor;
import pub.avalonframework.sqlhelper.core.callback.OnJoinCallback;
import pub.avalonframework.sqlhelper.core.data.JoinTableDatum;
import pub.avalonframework.sqlhelper.core.helper.*;

/**
 * @author baichao
 */
public final class JoinBuilderBean<TO extends OnHelper<TO>,
        S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
        SC extends ColumnHelper<SC>,
        SO extends OnHelper<SO>,
        SW extends WhereHelper<SW>,
        SG extends GroupHelper<SG>,
        SH extends HavingHelper<SH>,
        SS extends SortHelper<SS>> extends AbstractJoinBuilderBean {

    private TO mainOnHelper;

    private JoinType joinType;

    private String joinTableName;

    private Class<S> joinTableHelperClass;

    private OnJoinCallback<TO, SO> onJoinCallback;

    public JoinBuilderBean(TO mainOnHelper, JoinType joinType, String joinTableName, Class<S> joinTableHelperClass, String joinTableAlias, OnJoinCallback<TO, SO> onJoinCallback) {
        super(joinTableAlias);
        this.mainOnHelper = mainOnHelper;
        this.joinType = joinType;
        this.joinTableName = joinTableName;
        this.joinTableHelperClass = joinTableHelperClass;
        this.onJoinCallback = onJoinCallback;
    }

    @Override
    public JoinTableDatum execute(SqlBuilderConfiguration sqlBuilderConfiguration) {
        return CallbackBlockExecutor.execute(this.joinType, this.mainOnHelper, this.joinTableName, this.joinTableHelperClass, this.tableAlias, this.onJoinCallback, sqlBuilderConfiguration);
    }
}