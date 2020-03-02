package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.data.inject.WhereDataInjector;
import pub.avalonframework.sqlhelper.core.expression.WhereExpression;
import pub.avalonframework.sqlhelper.core.expression.lambda.WhereJoinLambdaCallable;
import pub.avalonframework.sqlhelper.core.expression.lambda.WhereLambdaCallable;
import pub.avalonframework.sqlhelper.core.expression.lambda.WhereLambdaExpression;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.utils.HelperManager;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author baichao
 */
public abstract class WhereBuilder<TW extends WhereHelper<TW>> implements WhereExpression<WhereBuilder<TW>>, WhereLambdaExpression<TW, WhereBuilder<TW>> {

    private TW whereHelper;
    private String tableAlias;

    {
        this.whereHelper = HelperManager.findWhereHelperClassFromAncestorsGenericType(this);
    }

    public WhereBuilder() {
        this.tableAlias = this.whereHelper.getTableAlias();
    }

    public WhereBuilder(String tableAlias) {
        this.whereHelper.setTableAlias(tableAlias);
        this.tableAlias = tableAlias;
    }

    private List<AbstractWhereBuilderBean> whereBuilderBeans = new ArrayList<>(1);

    @Override
    public WhereBuilder<TW> where(WhereHelper<?>... whereHelpers) {
        this.whereBuilderBeans.add(new WhereBuilderBean<>(this.whereHelper, this.tableAlias).setWhereHelpers(whereHelpers));
        return this;
    }

    @Override
    public WhereBuilder<TW> where(WhereLambdaCallable<TW> whereLambdaCallable) {
        this.whereBuilderBeans.add(new WhereBuilderBean<>(this.whereHelper, this.tableAlias).setWhereLambdaCallable(whereLambdaCallable));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> WhereBuilder<TW> where(Class<S> tableHelperClass, String tableAlias, WhereJoinLambdaCallable<TW, SW> whereJoinLambdaCallable) {
        this.whereBuilderBeans.add(new WhereBuilderBeanJoin<>(this.whereHelper, tableHelperClass, tableAlias, whereJoinLambdaCallable));
        return this;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<AbstractWhereBuilderBean> getWhereBuilderBeans() {
        return whereBuilderBeans;
    }

    public void execute(SqlBuilderConfiguration sqlBuilderConfiguration, Supplier<WhereDataInjector<?>> supplier) {
        execute(this, sqlBuilderConfiguration, supplier);
    }

    public static <FW extends WhereHelper<FW>> void execute(WhereBuilder<FW> whereBuilder, SqlBuilderConfiguration sqlBuilderConfiguration, Supplier<WhereDataInjector<?>> supplier) {
        if (supplier == null) {
            return;
        }
        WhereDataInjector<?> whereDataInjector = supplier.get();
        if (whereDataInjector == null) {
            return;
        }
        whereBuilder.getWhereBuilderBeans().forEach(sqlWhereBean -> sqlWhereBean.execute(sqlBuilderConfiguration).forEach(whereDataInjector::addTableWhereDataBlock));
    }
}