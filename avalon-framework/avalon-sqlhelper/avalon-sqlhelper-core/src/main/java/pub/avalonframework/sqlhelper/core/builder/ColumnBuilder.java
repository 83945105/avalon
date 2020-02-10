package pub.avalonframework.sqlhelper.core.builder;

import pub.avalonframework.sqlhelper.core.beans.GroupType;
import pub.avalonframework.sqlhelper.core.block.callback.CallbackColumnBlock;
import pub.avalonframework.sqlhelper.core.block.helper.HelperColumnBlock;
import pub.avalonframework.sqlhelper.core.builder.beans.AbstractSqlColumnBean;
import pub.avalonframework.sqlhelper.core.builder.beans.SqlColumnBean;
import pub.avalonframework.sqlhelper.core.builder.beans.SqlColumnBeanJoin;
import pub.avalonframework.sqlhelper.core.callback.ColumnCallback;
import pub.avalonframework.sqlhelper.core.callback.SubQueryColumnCallback;
import pub.avalonframework.sqlhelper.core.data.SqlDataCrudProducer;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalonframework.sqlhelper.core.utils.HelperManager;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author baichao
 */
public abstract class ColumnBuilder<TC extends ColumnHelper<TC>> implements HelperColumnBlock<ColumnBuilder<TC>>, CallbackColumnBlock<TC, ColumnBuilder<TC>> {

    private TC columnHelper;
    private String tableAlias;

    {
        this.columnHelper = HelperManager.findColumnHelperClassFromAncestorsGenericType(this);
    }

    public ColumnBuilder() {
        this.tableAlias = this.columnHelper.getTableAlias();
    }

    public ColumnBuilder(String tableAlias) {
        this.columnHelper.setTableAlias(tableAlias);
        this.tableAlias = tableAlias;
    }

    private List<AbstractSqlColumnBean> selectSqlColumnBeans = new ArrayList<>(1);
    private List<AbstractSqlColumnBean> insertSqlColumnBeans = new ArrayList<>(1);
    private List<AbstractSqlColumnBean> updateSqlColumnBeans = new ArrayList<>(1);

    public ColumnBuilder<TC> select(ColumnHelper<?>... columnHelpers) {
        SqlColumnBean<TC> sqlColumnBean = new SqlColumnBean<>(this.columnHelper, this.tableAlias).setColumnHelpers(columnHelpers);
        this.selectSqlColumnBeans.add(sqlColumnBean);
        return this;
    }

    public ColumnBuilder<TC> insert(ColumnHelper<?>... columnHelpers) {
        SqlColumnBean<TC> sqlColumnBean = new SqlColumnBean<>(this.columnHelper, this.tableAlias).setColumnHelpers(columnHelpers);
        this.insertSqlColumnBeans.add(sqlColumnBean);
        return this;
    }

    public ColumnBuilder<TC> update(ColumnHelper<?>... columnHelpers) {
        SqlColumnBean<TC> sqlColumnBean = new SqlColumnBean<>(this.columnHelper, this.tableAlias).setColumnHelpers(columnHelpers);
        this.updateSqlColumnBeans.add(sqlColumnBean);
        return this;
    }

    public ColumnBuilder<TC> select(ColumnCallback<TC> columnCallback) {
        SqlColumnBean<TC> sqlColumnBean = new SqlColumnBean<>(this.columnHelper, this.tableAlias).setColumnCallback(columnCallback);
        this.selectSqlColumnBeans.add(sqlColumnBean);
        return this;
    }

    public ColumnBuilder<TC> insert(ColumnCallback<TC> columnCallback) {
        SqlColumnBean<TC> sqlColumnBean = new SqlColumnBean<>(this.columnHelper, this.tableAlias).setColumnCallback(columnCallback);
        this.insertSqlColumnBeans.add(sqlColumnBean);
        return this;
    }

    public ColumnBuilder<TC> update(ColumnCallback<TC> columnCallback) {
        SqlColumnBean<TC> sqlColumnBean = new SqlColumnBean<>(this.columnHelper, this.tableAlias).setColumnCallback(columnCallback);
        this.updateSqlColumnBeans.add(sqlColumnBean);
        return this;
    }

    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> ColumnBuilder<TC> select(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback) {
        tableAlias = tableAlias == null ? HelperManager.defaultTableHelper(tableHelperClass).getTableAlias() : tableAlias;
        SqlColumnBeanJoin sqlColumnBeanJoin = new SqlColumnBeanJoin<>(tableHelperClass, tableAlias, columnCallback);
        this.selectSqlColumnBeans.add(sqlColumnBeanJoin);
        return this;
    }

    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> ColumnBuilder<TC> select(Class<S> tableHelperClass, ColumnCallback<SC> columnCallback) {
        SqlColumnBeanJoin sqlColumnBeanJoin = new SqlColumnBeanJoin<>(tableHelperClass, null, columnCallback);
        this.selectSqlColumnBeans.add(sqlColumnBeanJoin);
        return this;
    }

    @Override
    public ColumnBuilder<TC> virtualColumn(Object columnValue, String columnAlias) {
        return null;
    }

    @Override
    public ColumnBuilder<TC> groupColumn(GroupType groupType, ColumnCallback<TC> columnCallback) {
        return null;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> ColumnBuilder<TC> groupColumn(Class<S> tableHelperClass, String tableAlias, GroupType groupType, ColumnCallback<SC> columnCallback) {
        return null;
    }

    @Override
    public ColumnBuilder<TC> subQueryColumn(String columnAlias, SubQueryColumnCallback<TC> subQueryColumnCallback) {
        return null;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<AbstractSqlColumnBean> getSelectSqlColumnBeans() {
        return selectSqlColumnBeans;
    }

    public List<AbstractSqlColumnBean> getInsertSqlColumnBeans() {
        return insertSqlColumnBeans;
    }

    public List<AbstractSqlColumnBean> getUpdateSqlColumnBeans() {
        return updateSqlColumnBeans;
    }

    public void execute(SqlBuilderOptions sqlBuilderOptions, Supplier<SqlDataCrudProducer> supplier) {
        execute(this, sqlBuilderOptions, supplier);
    }

    public static <FC extends ColumnHelper<FC>> void execute(ColumnBuilder<FC> sqlColumn, SqlBuilderOptions sqlBuilderOptions, Supplier<SqlDataCrudProducer> supplier) {
        if (supplier == null) {
            return;
        }
        SqlDataCrudProducer sqlDataCrudProducer = supplier.get();
        if (sqlDataCrudProducer == null) {
            return;
        }
        sqlColumn.getSelectSqlColumnBeans().forEach(sqlColumnBean -> sqlColumnBean.execute(sqlBuilderOptions).forEach(sqlDataCrudProducer::addSelectTableColumnDatum));
        sqlColumn.getInsertSqlColumnBeans().forEach(sqlColumnBean -> sqlColumnBean.execute(sqlBuilderOptions).forEach(sqlDataCrudProducer::addInsertTableColumnDatum));
        sqlColumn.getUpdateSqlColumnBeans().forEach(sqlColumnBean -> sqlColumnBean.execute(sqlBuilderOptions).forEach(sqlDataCrudProducer::addUpdateTableColumnDatum));
    }
}