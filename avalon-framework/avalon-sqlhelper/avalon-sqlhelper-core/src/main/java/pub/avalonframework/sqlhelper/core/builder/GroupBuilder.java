package pub.avalonframework.sqlhelper.core.builder;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.block.callback.CallbackGroupBlock;
import pub.avalonframework.sqlhelper.core.block.helper.HelperGroupBlock;
import pub.avalonframework.sqlhelper.core.builder.beans.AbstractGroupBuilderBean;
import pub.avalonframework.sqlhelper.core.builder.beans.GroupBuilderBean;
import pub.avalonframework.sqlhelper.core.builder.beans.GroupBuilderBeanJoin;
import pub.avalonframework.sqlhelper.core.callback.GroupCallback;
import pub.avalonframework.sqlhelper.core.data.inject.GroupDataInjector;
import pub.avalonframework.sqlhelper.core.helper.*;
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

    private List<AbstractGroupBuilderBean> groupBuilderBeans = new ArrayList<>(1);

    @Override
    public GroupBuilder<TG> groupBy(GroupHelper<?>... groupHelpers) {
        this.groupBuilderBeans.add(new GroupBuilderBean<>(this.groupHelper, this.tableAlias).setGroupHelpers(groupHelpers));
        return this;
    }

    @Override
    public GroupBuilder<TG> groupBy(GroupCallback<TG> groupCallback) {
        this.groupBuilderBeans.add(new GroupBuilderBean<>(this.groupHelper, this.tableAlias).setGroupCallback(groupCallback));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> GroupBuilder<TG> groupBy(Class<S> tableHelperClass, String tableAlias, GroupCallback<SG> groupCallback) {
        this.groupBuilderBeans.add(new GroupBuilderBeanJoin<>(tableHelperClass, tableAlias, groupCallback));
        return this;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<AbstractGroupBuilderBean> getGroupBuilderBeans() {
        return groupBuilderBeans;
    }

    public void execute(SqlBuilderConfiguration sqlBuilderConfiguration, Supplier<GroupDataInjector> supplier) {
        execute(this, sqlBuilderConfiguration, supplier);
    }

    public static <FG extends GroupHelper<FG>> void execute(GroupBuilder<FG> groupBuilder, SqlBuilderConfiguration sqlBuilderConfiguration, Supplier<GroupDataInjector> supplier) {
        if (supplier == null) {
            return;
        }
        GroupDataInjector groupDataInjector = supplier.get();
        if (groupDataInjector == null) {
            return;
        }
        groupBuilder.getGroupBuilderBeans().forEach(sqlGroupBean -> sqlGroupBean.execute(sqlBuilderConfiguration).forEach(groupDataInjector::addTableGroupDatum));
    }
}