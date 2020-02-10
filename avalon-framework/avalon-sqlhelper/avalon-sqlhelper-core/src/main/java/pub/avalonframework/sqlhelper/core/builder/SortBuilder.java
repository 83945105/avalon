package pub.avalonframework.sqlhelper.core.builder;

import pub.avalonframework.sqlhelper.core.block.callback.CallbackSortBlock;
import pub.avalonframework.sqlhelper.core.block.helper.HelperSortBlock;
import pub.avalonframework.sqlhelper.core.builder.beans.AbstractSqlSortBean;
import pub.avalonframework.sqlhelper.core.builder.beans.SqlSortBean;
import pub.avalonframework.sqlhelper.core.builder.beans.SqlSortBeanJoin;
import pub.avalonframework.sqlhelper.core.callback.SortCallback;
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
public class SortBuilder<TS extends SortHelper<TS>> implements HelperSortBlock<SortBuilder<TS>>, CallbackSortBlock<TS, SortBuilder<TS>> {

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

    private List<AbstractSqlSortBean> sqlSortBeans = new ArrayList<>(1);

    @Override
    public SortBuilder<TS> orderBy(SortHelper<?>... sortHelpers) {
        this.sqlSortBeans.add(new SqlSortBean<>(this.sortHelper, this.tableAlias).setSortHelpers(sortHelpers));
        return this;
    }

    @Override
    public SortBuilder<TS> orderBy(SortCallback<TS> sortCallback) {
        this.sqlSortBeans.add(new SqlSortBean<>(this.sortHelper, this.tableAlias).setSortCallback(sortCallback));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SortBuilder<TS> orderBy(Class<S> tableHelperClass, String tableAlias, SortCallback<SS> sortCallback) {
        this.sqlSortBeans.add(new SqlSortBeanJoin<>(tableHelperClass, tableAlias, sortCallback));
        return this;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<AbstractSqlSortBean> getSqlSortBeans() {
        return sqlSortBeans;
    }

    public void execute(SqlBuilderOptions sqlBuilderOptions, Supplier<SqlDataCrudProducer> supplier) {
        execute(this, sqlBuilderOptions, supplier);
    }

    public static <FS extends SortHelper<FS>> void execute(SortBuilder<FS> sqlSort, SqlBuilderOptions sqlBuilderOptions, Supplier<SqlDataCrudProducer> supplier) {
        if (supplier == null) {
            return;
        }
        SqlDataCrudProducer sqlDataCrudProducer = supplier.get();
        if (sqlDataCrudProducer == null) {
            return;
        }
        sqlSort.getSqlSortBeans().forEach(sqlSortBean -> sqlSortBean.execute(sqlBuilderOptions).forEach(sqlDataCrudProducer::addTableSortDatum));
    }
}