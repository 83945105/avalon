package pub.avalonframework.sqlhelper.core.builder;

import pub.avalonframework.sqlhelper.core.builder.beans.AbstractColumnBuilderBean;
import pub.avalonframework.sqlhelper.core.builder.beans.ColumnBuilderBean;
import pub.avalonframework.sqlhelper.core.callback.ColumnCallback;
import pub.avalonframework.sqlhelper.core.data.SqlDataCrudProducer;
import pub.avalonframework.sqlhelper.core.helper.ColumnHelper;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;
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
        ColumnBuilderBean<TC> sqlColumnBean = new ColumnBuilderBean<>(this.columnHelper, this.tableAlias).setColumnHelpers(columnHelpers);
        this.updateColumnBuilderBeans.add(sqlColumnBean);
        return this;
    }

    public UpdateColumnBuilder<TC> update(ColumnCallback<TC> columnCallback) {
        ColumnBuilderBean<TC> sqlColumnBean = new ColumnBuilderBean<>(this.columnHelper, this.tableAlias).setColumnCallback(columnCallback);
        this.updateColumnBuilderBeans.add(sqlColumnBean);
        return this;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<AbstractColumnBuilderBean> getUpdateColumnBuilderBeans() {
        return updateColumnBuilderBeans;
    }

    public void execute(SqlBuilderOptions sqlBuilderOptions, Supplier<SqlDataCrudProducer> supplier) {
        execute(this, sqlBuilderOptions, supplier);
    }

    public static <FC extends ColumnHelper<FC>> void execute(UpdateColumnBuilder<FC> updateColumnBuilder, SqlBuilderOptions sqlBuilderOptions, Supplier<SqlDataCrudProducer> supplier) {
        if (supplier == null) {
            return;
        }
        SqlDataCrudProducer sqlDataCrudProducer = supplier.get();
        if (sqlDataCrudProducer == null) {
            return;
        }
        updateColumnBuilder.getUpdateColumnBuilderBeans().forEach(sqlColumnBean -> sqlColumnBean.execute(sqlBuilderOptions).forEach(sqlDataCrudProducer::addUpdateTableColumnDatum));
    }
}