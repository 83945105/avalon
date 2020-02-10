package pub.avalonframework.sqlhelper.core.builder;

import pub.avalonframework.sqlhelper.core.block.callback.CallbackGroupBlock;
import pub.avalonframework.sqlhelper.core.block.helper.HelperGroupBlock;
import pub.avalonframework.sqlhelper.core.builder.beans.AbstractSqlGroupBean;
import pub.avalonframework.sqlhelper.core.builder.beans.SqlGroupBean;
import pub.avalonframework.sqlhelper.core.builder.beans.SqlGroupBeanJoin;
import pub.avalonframework.sqlhelper.core.callback.GroupCallback;
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
public abstract class GroupBuilder<TG extends GroupHelper<TG>> implements HelperGroupBlock<GroupBuilder<TG>>, CallbackGroupBlock<TG, GroupBuilder<TG>> {

    private TG groupHelper;
    private String tableAlias;

    {
        this.groupHelper = HelperManager.findGroupHelperClassFromAncestorsGenericType(this);
    }

    public GroupBuilder() {
        this.tableAlias = this.groupHelper.getTableAlias();
    }

    public GroupBuilder(String tableAlias) {
        this.groupHelper.setTableAlias(tableAlias);
        this.tableAlias = tableAlias;
    }

    private List<AbstractSqlGroupBean> sqlGroupBeans = new ArrayList<>(1);

    @Override
    public GroupBuilder<TG> groupBy(GroupHelper<?>... groupHelpers) {
        this.sqlGroupBeans.add(new SqlGroupBean<>(this.groupHelper, this.tableAlias).setGroupHelpers(groupHelpers));
        return this;
    }

    @Override
    public GroupBuilder<TG> groupBy(GroupCallback<TG> groupCallback) {
        this.sqlGroupBeans.add(new SqlGroupBean<>(this.groupHelper, this.tableAlias).setGroupCallback(groupCallback));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> GroupBuilder<TG> groupBy(Class<S> tableHelperClass, String tableAlias, GroupCallback<SG> groupCallback) {
        this.sqlGroupBeans.add(new SqlGroupBeanJoin<>(tableHelperClass, tableAlias, groupCallback));
        return this;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<AbstractSqlGroupBean> getSqlGroupBeans() {
        return sqlGroupBeans;
    }

    public void execute(SqlBuilderOptions sqlBuilderOptions, Supplier<SqlDataCrudProducer> supplier) {
        execute(this, sqlBuilderOptions, supplier);
    }

    public static <FG extends GroupHelper<FG>> void execute(GroupBuilder<FG> sqlGroup, SqlBuilderOptions sqlBuilderOptions, Supplier<SqlDataCrudProducer> supplier) {
        if (supplier == null) {
            return;
        }
        SqlDataCrudProducer sqlDataCrudProducer = supplier.get();
        if (sqlDataCrudProducer == null) {
            return;
        }
        sqlGroup.getSqlGroupBeans().forEach(sqlGroupBean -> sqlGroupBean.execute(sqlBuilderOptions).forEach(sqlDataCrudProducer::addTableGroupDatum));
    }
}