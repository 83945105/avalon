package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.data.inject.SelectInjector;
import pub.avalonframework.sqlhelper.core.expression.lambda.ColumnLambdaCallable;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.utils.HelperManager;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author baichao
 */
public abstract class SelectColumnBuilder<TC extends ColumnHelper<TC>> {

    private TC columnHelper;
    private String tableAlias;

    {
        this.columnHelper = HelperManager.findColumnHelperClassFromAncestorsGenericType(this);
    }

    public SelectColumnBuilder() {
        this.tableAlias = this.columnHelper.getTableAlias();
    }

    public SelectColumnBuilder(String tableAlias) {
        this.columnHelper.setTableAlias(tableAlias);
        this.tableAlias = tableAlias;
    }

    private List<AbstractColumnBuilderBean> selectColumnBuilderBeans = new ArrayList<>(1);

    public SelectColumnBuilder<TC> select(ColumnHelper<?>... columnHelpers) {
        ColumnBuilderBean<TC> columnBuilderBean = new ColumnBuilderBean<>(this.columnHelper, this.tableAlias).setColumnHelpers(columnHelpers);
        this.selectColumnBuilderBeans.add(columnBuilderBean);
        return this;
    }

    public SelectColumnBuilder<TC> select(ColumnLambdaCallable<TC> columnLambdaCallable) {
        ColumnBuilderBean<TC> columnBuilderBean = new ColumnBuilderBean<>(this.columnHelper, this.tableAlias).setColumnLambdaCallable(columnLambdaCallable);
        this.selectColumnBuilderBeans.add(columnBuilderBean);
        return this;
    }

    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SelectColumnBuilder<TC> select(Class<S> tableHelperClass, String tableAlias, ColumnLambdaCallable<SC> columnLambdaCallable) {
        tableAlias = tableAlias == null ? HelperManager.defaultTableHelper(tableHelperClass).getTableAlias() : tableAlias;
        ColumnBuilderBeanJoin<S, SC, SO, SW, SG, SH, SS> columnBuilderBeanJoin = new ColumnBuilderBeanJoin<>(tableHelperClass, tableAlias, columnLambdaCallable);
        this.selectColumnBuilderBeans.add(columnBuilderBeanJoin);
        return this;
    }

    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SelectColumnBuilder<TC> select(Class<S> tableHelperClass, ColumnLambdaCallable<SC> columnLambdaCallable) {
        ColumnBuilderBeanJoin<S, SC, SO, SW, SG, SH, SS> columnBuilderBeanJoin = new ColumnBuilderBeanJoin<>(tableHelperClass, null, columnLambdaCallable);
        this.selectColumnBuilderBeans.add(columnBuilderBeanJoin);
        return this;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<AbstractColumnBuilderBean> getSelectColumnBuilderBeans() {
        return selectColumnBuilderBeans;
    }

    public void execute(SqlBuilderConfiguration sqlBuilderConfiguration, Supplier<SelectInjector<?>> supplier) {
        execute(this, sqlBuilderConfiguration, supplier);
    }

    public static <FC extends ColumnHelper<FC>> void execute(SelectColumnBuilder<FC> selectColumnBuilder, SqlBuilderConfiguration sqlBuilderConfiguration, Supplier<SelectInjector<?>> supplier) {
        if (supplier == null) {
            return;
        }
        SelectInjector<?> selectInjector = supplier.get();
        if (selectInjector == null) {
            return;
        }
        selectColumnBuilder.getSelectColumnBuilderBeans().forEach(sqlColumnBean -> sqlColumnBean.execute(sqlBuilderConfiguration).forEach(selectInjector::addSelectTableColumnDataBlock));
    }
}