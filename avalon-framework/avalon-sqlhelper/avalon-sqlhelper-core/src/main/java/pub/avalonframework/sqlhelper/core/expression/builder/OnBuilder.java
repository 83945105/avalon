package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.builder.beans.AbstractOnBuilderBean;
import pub.avalonframework.sqlhelper.core.builder.beans.OnBuilderBean;
import pub.avalonframework.sqlhelper.core.builder.beans.OnBuilderBeanJoin;
import pub.avalonframework.sqlhelper.core.data.inject.OnDataInjector;
import pub.avalonframework.sqlhelper.core.expression.OnExpression;
import pub.avalonframework.sqlhelper.core.expression.lambda.OnJoinLambdaCallable;
import pub.avalonframework.sqlhelper.core.expression.lambda.OnLambdaCallable;
import pub.avalonframework.sqlhelper.core.expression.lambda.OnLambdaExpression;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.utils.HelperManager;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author baichao
 */
public abstract class OnBuilder<TO extends OnHelper<TO>> implements OnExpression<OnBuilder<TO>>, OnLambdaExpression<TO, OnBuilder<TO>> {

    private TO onHelper;
    private String tableAlias;

    {
        this.onHelper = HelperManager.findOnHelperClassFromAncestorsGenericType(this);
    }

    public OnBuilder() {
        this.tableAlias = this.onHelper.getTableAlias();
    }

    public OnBuilder(String tableAlias) {
        this.onHelper.setTableAlias(tableAlias);
        this.tableAlias = tableAlias;
    }

    private List<AbstractOnBuilderBean> onBuilderBeans = new ArrayList<>(1);

    @Override
    public OnBuilder<TO> on(OnHelper<?>... onHelpers) {
        this.onBuilderBeans.add(new OnBuilderBean<>(this.onHelper, this.tableAlias).setOnHelpers(onHelpers));
        return this;
    }

    @Override
    public OnBuilder<TO> on(OnLambdaCallable<TO> onLambdaCallable) {
        this.onBuilderBeans.add(new OnBuilderBean<>(this.onHelper, this.tableAlias).setOnLambdaCallable(onLambdaCallable));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> OnBuilder<TO> on(Class<S> tableHelperClass, String tableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        this.onBuilderBeans.add(new OnBuilderBeanJoin<>(this.onHelper, tableHelperClass, tableAlias, onJoinLambdaCallable));
        return this;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<AbstractOnBuilderBean> getOnBuilderBeans() {
        return onBuilderBeans;
    }

    public void execute(SqlBuilderConfiguration sqlBuilderConfiguration, Supplier<OnDataInjector<?>> supplier) {
        execute(this, sqlBuilderConfiguration, supplier);
    }

    public static <FO extends OnHelper<FO>> void execute(OnBuilder<FO> onBuilder, SqlBuilderConfiguration sqlBuilderConfiguration, Supplier<OnDataInjector<?>> supplier) {
        if (supplier == null) {
            return;
        }
        OnDataInjector<?> onDataInjector = supplier.get();
        if (onDataInjector == null) {
            return;
        }
        onBuilder.getOnBuilderBeans().forEach(sqlOnBean -> sqlOnBean.execute(sqlBuilderConfiguration).forEach(onDataInjector::addTableOnDataBlock));
    }
}