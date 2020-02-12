package pub.avalonframework.sqlhelper.core.builder;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.builder.beans.AbstractColumnBuilderBean;
import pub.avalonframework.sqlhelper.core.builder.beans.ColumnBuilderBean;
import pub.avalonframework.sqlhelper.core.callback.ColumnCallback;
import pub.avalonframework.sqlhelper.core.data.SqlDataInsertProducer;
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
        ColumnBuilderBean<TC> sqlColumnBean = new ColumnBuilderBean<>(this.columnHelper, this.tableAlias).setColumnHelpers(columnHelpers);
        this.insertColumnBuilderBeans.add(sqlColumnBean);
        return this;
    }

    public InsertColumnBuilder<TC> insert(ColumnCallback<TC> columnCallback) {
        ColumnBuilderBean<TC> sqlColumnBean = new ColumnBuilderBean<>(this.columnHelper, this.tableAlias).setColumnCallback(columnCallback);
        this.insertColumnBuilderBeans.add(sqlColumnBean);
        return this;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<AbstractColumnBuilderBean> getInsertColumnBuilderBeans() {
        return insertColumnBuilderBeans;
    }

    public void execute(SqlBuilderConfiguration sqlBuilderConfiguration, Supplier<SqlDataInsertProducer> supplier) {
        execute(this, sqlBuilderConfiguration, supplier);
    }

    public static <FC extends ColumnHelper<FC>> void execute(InsertColumnBuilder<FC> insertColumnBuilder, SqlBuilderConfiguration sqlBuilderConfiguration, Supplier<SqlDataInsertProducer> supplier) {
        if (supplier == null) {
            return;
        }
        SqlDataInsertProducer sqlDataInsertProducer = supplier.get();
        if (sqlDataInsertProducer == null) {
            return;
        }
        insertColumnBuilder.getInsertColumnBuilderBeans().forEach(sqlColumnBean -> sqlColumnBean.execute(sqlBuilderConfiguration).forEach(sqlDataInsertProducer::addInsertTableColumnDatum));
    }
}