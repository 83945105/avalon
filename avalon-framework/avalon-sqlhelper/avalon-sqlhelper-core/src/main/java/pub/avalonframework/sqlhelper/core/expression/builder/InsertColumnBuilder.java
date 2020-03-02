package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.builder.beans.AbstractColumnBuilderBean;
import pub.avalonframework.sqlhelper.core.builder.beans.ColumnBuilderBean;
import pub.avalonframework.sqlhelper.core.data.inject.InsertInjector;
import pub.avalonframework.sqlhelper.core.expression.lambda.ColumnLambdaCallable;
import pub.avalonframework.sqlhelper.core.helper.ColumnHelper;
import pub.avalonframework.sqlhelper.core.utils.HelperManager;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author baichao
 */
public abstract class InsertColumnBuilder<TC extends ColumnHelper<TC>> {

    private TC columnHelper;
    private String tableAlias;

    {
        this.columnHelper = HelperManager.findColumnHelperClassFromAncestorsGenericType(this);
    }

    public InsertColumnBuilder() {
        this.tableAlias = this.columnHelper.getTableAlias();
    }

    public InsertColumnBuilder(String tableAlias) {
        this.columnHelper.setTableAlias(tableAlias);
        this.tableAlias = tableAlias;
    }

    private List<AbstractColumnBuilderBean> insertColumnBuilderBeans = new ArrayList<>(1);

    public InsertColumnBuilder<TC> insert(ColumnHelper<?>... columnHelpers) {
        ColumnBuilderBean<TC> columnBuilderBean = new ColumnBuilderBean<>(this.columnHelper, this.tableAlias).setColumnHelpers(columnHelpers);
        this.insertColumnBuilderBeans.add(columnBuilderBean);
        return this;
    }

    public InsertColumnBuilder<TC> insert(ColumnLambdaCallable<TC> columnLambdaCallable) {
        ColumnBuilderBean<TC> columnBuilderBean = new ColumnBuilderBean<>(this.columnHelper, this.tableAlias).setColumnLambdaCallable(columnLambdaCallable);
        this.insertColumnBuilderBeans.add(columnBuilderBean);
        return this;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<AbstractColumnBuilderBean> getInsertColumnBuilderBeans() {
        return insertColumnBuilderBeans;
    }

    public void execute(SqlBuilderConfiguration sqlBuilderConfiguration, Supplier<InsertInjector<?>> supplier) {
        execute(this, sqlBuilderConfiguration, supplier);
    }

    public static <FC extends ColumnHelper<FC>> void execute(InsertColumnBuilder<FC> insertColumnBuilder, SqlBuilderConfiguration sqlBuilderConfiguration, Supplier<InsertInjector<?>> supplier) {
        if (supplier == null) {
            return;
        }
        InsertInjector<?> insertInjector = supplier.get();
        if (insertInjector == null) {
            return;
        }
        insertColumnBuilder.getInsertColumnBuilderBeans().forEach(sqlColumnBean -> sqlColumnBean.execute(sqlBuilderConfiguration).forEach(insertInjector::addInsertTableColumnDataBlock));
    }
}