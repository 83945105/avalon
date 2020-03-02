package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.data.block.TableOnDataBlock;
import pub.avalonframework.sqlhelper.core.expression.lambda.OnLambdaCallable;
import pub.avalonframework.sqlhelper.core.expression.lambda.execute.LambdaCallableExecutor;
import pub.avalonframework.sqlhelper.core.helper.OnHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public final class OnBuilderBean<TO extends OnHelper<TO>> extends AbstractOnBuilderBean {

    private TO onHelper;

    private OnHelper<?>[] onHelpers;

    private OnLambdaCallable<TO> onLambdaCallable;

    public OnBuilderBean(TO onHelper, String tableAlias) {
        super(tableAlias);
        this.onHelper = onHelper;
    }

    public OnBuilderBean<TO> setOnHelpers(OnHelper<?>[] onHelpers) {
        this.onHelpers = onHelpers;
        return this;
    }

    public OnBuilderBean<TO> setOnLambdaCallable(OnLambdaCallable<TO> onLambdaCallable) {
        this.onLambdaCallable = onLambdaCallable;
        return this;
    }

    @Override
    public List<TableOnDataBlock> execute(SqlBuilderConfiguration sqlBuilderConfiguration) {
        List<TableOnDataBlock> tableOnDataBlocks = new ArrayList<>(1);
        if (this.onHelpers != null) {
            for (OnHelper<?> onHelper : this.onHelpers) {
                tableOnDataBlocks.add(onHelper.execute());
            }
        }
        if (this.onLambdaCallable != null) {
            tableOnDataBlocks.add(LambdaCallableExecutor.execute(this.onHelper, this.onLambdaCallable, sqlBuilderConfiguration));
        }
        return tableOnDataBlocks;
    }
}