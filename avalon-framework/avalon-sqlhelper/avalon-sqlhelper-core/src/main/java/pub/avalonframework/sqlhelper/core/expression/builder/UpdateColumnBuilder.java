package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.data.inject.UpdateInjector;
import pub.avalonframework.sqlhelper.core.expression.lambda.ColumnLambdaCallable;
import pub.avalonframework.sqlhelper.core.helper.ColumnHelper;
import pub.avalonframework.sqlhelper.core.utils.HelperManager;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author baichao
 */
public abstract class UpdateColumnBuilder<TC extends ColumnHelper<TC>> {

    private TC columnHelper;
    private String tableAlias;

    {
        this.columnHelper = HelperManager.findColumnHelperClassFromAncestorsGenericType(this);
    }

    public UpdateColumnBuilder() {
        this.tableAlias = this.columnHelper.getTableAlias();
    }

    public UpdateColumnBuilder(String tableAlias) {
        this.columnHelper.setTableAlias(tableAlias);
        this.tableAlias = tableAlias;
    }

    private List<AbstractColumnBuilderBean> updateColumnBuilderBeans = new ArrayList<>(1);

    public UpdateColumnBuilder<TC> update(ColumnHelper<?>... columnHelpers) {
        ColumnBuilderBean<TC> columnBuilderBean = new ColumnBuilderBean<>(this.columnHelper, this.tableAlias).setColumnHelpers(columnHelpers);
        this.updateColumnBuilderBeans.add(columnBuilderBean);
        return this;
    }

    public UpdateColumnBuilder<TC> update(ColumnLambdaCallable<TC> columnLambdaCallable) {
        ColumnBuilderBean<TC> columnBuilderBean = new ColumnBuilderBean<>(this.columnHelper, this.tableAlias).setColumnLambdaCallable(columnLambdaCallable);
        this.updateColumnBuilderBeans.add(columnBuilderBean);
        return this;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<AbstractColumnBuilderBean> getUpdateColumnBuilderBeans() {
        return updateColumnBuilderBeans;
    }

    public void execute(SqlBuilderConfiguration sqlBuilderConfiguration, Supplier<UpdateInjector<?>> supplier) {
        execute(this, sqlBuilderConfiguration, supplier);
    }

    public static <FC extends ColumnHelper<FC>> void execute(UpdateColumnBuilder<FC> updateColumnBuilder, SqlBuilderConfiguration sqlBuilderConfiguration, Supplier<UpdateInjector<?>> supplier) {
        if (supplier == null) {
            return;
        }
        UpdateInjector<?> updateInjector = supplier.get();
        if (updateInjector == null) {
            return;
        }
        updateColumnBuilder.getUpdateColumnBuilderBeans().forEach(sqlColumnBean -> sqlColumnBean.execute(sqlBuilderConfiguration).forEach(updateInjector::addUpdateTableColumnDataBlock));
    }
}