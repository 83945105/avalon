package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.data.block.JoinType;
import pub.avalonframework.sqlhelper.core.data.block.TableJoinDataBlock;
import pub.avalonframework.sqlhelper.core.expression.lambda.OnJoinLambdaCallable;
import pub.avalonframework.sqlhelper.core.expression.lambda.execute.LambdaCallableExecutor;
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

    private OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable;

    public JoinBuilderBean(TO mainOnHelper, JoinType joinType, String joinTableName, Class<S> joinTableHelperClass, String joinTableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        super(joinTableAlias);
        this.mainOnHelper = mainOnHelper;
        this.joinType = joinType;
        this.joinTableName = joinTableName;
        this.joinTableHelperClass = joinTableHelperClass;
        this.onJoinLambdaCallable = onJoinLambdaCallable;
    }

    @Override
    public TableJoinDataBlock getTableJoinDataBlocks(SqlBuilderConfiguration sqlBuilderConfiguration) {
        return LambdaCallableExecutor.execute(this.joinType, this.mainOnHelper, this.joinTableName, this.joinTableHelperClass, this.tableAlias, this.onJoinLambdaCallable, sqlBuilderConfiguration);
    }
}