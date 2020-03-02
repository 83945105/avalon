package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.data.block.TableWhereDataBlock;
import pub.avalonframework.sqlhelper.core.expression.lambda.WhereLambdaCallable;
import pub.avalonframework.sqlhelper.core.expression.lambda.execute.LambdaCallableExecutor;
import pub.avalonframework.sqlhelper.core.helper.WhereHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public final class WhereBuilderBean<TW extends WhereHelper<TW>> extends AbstractWhereBuilderBean {

    private TW whereHelper;

    private WhereHelper<?>[] whereHelpers;

    private WhereLambdaCallable<TW> whereLambdaCallable;

    public WhereBuilderBean(TW whereHelper, String tableAlias) {
        super(tableAlias);
        this.whereHelper = whereHelper;
    }

    public WhereBuilderBean<TW> setWhereHelpers(WhereHelper<?>[] whereHelpers) {
        this.whereHelpers = whereHelpers;
        return this;
    }

    public WhereBuilderBean<TW> setWhereLambdaCallable(WhereLambdaCallable<TW> whereLambdaCallable) {
        this.whereLambdaCallable = whereLambdaCallable;
        return this;
    }

    @Override
    public List<TableWhereDataBlock> getTableWhereDataBlocks(SqlBuilderConfiguration sqlBuilderConfiguration) {
        List<TableWhereDataBlock> tableWhereDataBlocks = new ArrayList<>(1);
        if (this.whereHelpers != null) {
            for (WhereHelper<?> whereHelper : this.whereHelpers) {
                tableWhereDataBlocks.add(whereHelper.execute());
            }
        }
        if (this.whereLambdaCallable != null) {
            tableWhereDataBlocks.add(LambdaCallableExecutor.execute(this.whereHelper, this.whereLambdaCallable, sqlBuilderConfiguration));
        }
        return tableWhereDataBlocks;
    }
}