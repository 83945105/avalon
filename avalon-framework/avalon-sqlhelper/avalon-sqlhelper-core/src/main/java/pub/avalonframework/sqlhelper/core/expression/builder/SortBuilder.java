package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.builder.beans.AbstractSortBuilderBean;
import pub.avalonframework.sqlhelper.core.builder.beans.SortBuilderBean;
import pub.avalonframework.sqlhelper.core.builder.beans.SortBuilderBeanJoin;
import pub.avalonframework.sqlhelper.core.data.inject.SortDataInjector;
import pub.avalonframework.sqlhelper.core.expression.SortExpression;
import pub.avalonframework.sqlhelper.core.expression.lambda.SortLambdaCallable;
import pub.avalonframework.sqlhelper.core.expression.lambda.SortLambdaExpression;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.utils.HelperManager;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author baichao
 */
public class SortBuilder<TS extends SortHelper<TS>> implements SortExpression<SortBuilder<TS>>, SortLambdaExpression<TS, SortBuilder<TS>> {

    private TS sortHelper;
    private String tableAlias;

    {
        this.sortHelper = HelperManager.findSortHelperClassFromAncestorsGenericType(this);
    }

    public SortBuilder() {
        this.tableAlias = this.sortHelper.getTableAlias();
    }

    public SortBuilder(String tableAlias) {
        this.sortHelper.setTableAlias(tableAlias);
        this.tableAlias = tableAlias;
    }

    private List<AbstractSortBuilderBean> sortBuilderBeans = new ArrayList<>(1);

    @Override
    public SortBuilder<TS> orderBy(SortHelper<?>... sortHelpers) {
        this.sortBuilderBeans.add(new SortBuilderBean<>(this.sortHelper, this.tableAlias).setSortHelpers(sortHelpers));
        return this;
    }

    @Override
    public SortBuilder<TS> orderBy(SortLambdaCallable<TS> sortLambdaCallable) {
        this.sortBuilderBeans.add(new SortBuilderBean<>(this.sortHelper, this.tableAlias).setSortLambdaCallable(sortLambdaCallable));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SortBuilder<TS> orderBy(Class<S> tableHelperClass, String tableAlias, SortLambdaCallable<SS> sortLambdaCallable) {
        this.sortBuilderBeans.add(new SortBuilderBeanJoin<>(tableHelperClass, tableAlias, sortLambdaCallable));
        return this;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<AbstractSortBuilderBean> getSortBuilderBeans() {
        return sortBuilderBeans;
    }

    public void execute(SqlBuilderConfiguration sqlBuilderConfiguration, Supplier<SortDataInjector<?>> supplier) {
        execute(this, sqlBuilderConfiguration, supplier);
    }

    public static <FS extends SortHelper<FS>> void execute(SortBuilder<FS> sortBuilder, SqlBuilderConfiguration sqlBuilderConfiguration, Supplier<SortDataInjector<?>> supplier) {
        if (supplier == null) {
            return;
        }
        SortDataInjector<?> sortDataInjector = supplier.get();
        if (sortDataInjector == null) {
            return;
        }
        sortBuilder.getSortBuilderBeans().forEach(sqlSortBean -> sqlSortBean.execute(sqlBuilderConfiguration).forEach(sortDataInjector::addTableSortDataBlock));
    }
}