package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.builder.beans.AbstractHavingBuilderBean;
import pub.avalonframework.sqlhelper.core.builder.beans.HavingBuilderBean;
import pub.avalonframework.sqlhelper.core.builder.beans.HavingBuilderBeanJoin;
import pub.avalonframework.sqlhelper.core.data.inject.HavingDataInjector;
import pub.avalonframework.sqlhelper.core.expression.HavingExpression;
import pub.avalonframework.sqlhelper.core.expression.lambda.HavingJoinLambdaCallable;
import pub.avalonframework.sqlhelper.core.expression.lambda.HavingLambdaCallable;
import pub.avalonframework.sqlhelper.core.expression.lambda.HavingLambdaExpression;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.utils.HelperManager;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author baichao
 */
public abstract class HavingBuilder<TH extends HavingHelper<TH>> implements HavingExpression<HavingBuilder<TH>>, HavingLambdaExpression<TH, HavingBuilder<TH>> {

    private TH havingHelper;
    private String tableAlias;

    {
        this.havingHelper = HelperManager.findHavingHelperClassFromAncestorsGenericType(this);
    }

    public HavingBuilder() {
        this.tableAlias = this.havingHelper.getTableAlias();
    }

    public HavingBuilder(String tableAlias) {
        this.havingHelper.setTableAlias(tableAlias);
        this.tableAlias = tableAlias;
    }

    private List<AbstractHavingBuilderBean> havingBuilderBeans = new ArrayList<>(1);

    @Override
    public HavingBuilder<TH> having(HavingHelper<?>... havingHelpers) {
        this.havingBuilderBeans.add(new HavingBuilderBean<>(this.havingHelper, this.tableAlias).setHavingHelpers(havingHelpers));
        return this;
    }

    @Override
    public HavingBuilder<TH> having(HavingLambdaCallable<TH> havingLambdaCallable) {
        this.havingBuilderBeans.add(new HavingBuilderBean<>(this.havingHelper, this.tableAlias).setHavingLambdaCallable(havingLambdaCallable));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> HavingBuilder<TH> having(Class<S> tableHelperClass, String tableAlias, HavingJoinLambdaCallable<TH, SH> havingJoinLambdaCallable) {
        this.havingBuilderBeans.add(new HavingBuilderBeanJoin<>(this.havingHelper, tableHelperClass, tableAlias, havingJoinLambdaCallable));
        return this;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<AbstractHavingBuilderBean> getHavingBuilderBeans() {
        return havingBuilderBeans;
    }

    public void execute(SqlBuilderConfiguration sqlBuilderConfiguration, Supplier<HavingDataInjector<?>> supplier) {
        execute(this, sqlBuilderConfiguration, supplier);
    }

    public static <FW extends HavingHelper<FW>> void execute(HavingBuilder<FW> havingBuilder, SqlBuilderConfiguration sqlBuilderConfiguration, Supplier<HavingDataInjector<?>> supplier) {
        if (supplier == null) {
            return;
        }
        HavingDataInjector<?> havingDataInjector = supplier.get();
        if (havingDataInjector == null) {
            return;
        }
        havingBuilder.getHavingBuilderBeans().forEach(sqlHavingBean -> sqlHavingBean.execute(sqlBuilderConfiguration).forEach(havingDataInjector::addTableHavingDataBlock));
    }
}