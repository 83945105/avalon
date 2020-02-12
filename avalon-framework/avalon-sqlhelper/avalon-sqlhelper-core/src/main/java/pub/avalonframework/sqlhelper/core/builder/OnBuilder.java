package pub.avalonframework.sqlhelper.core.builder;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.block.callback.CallbackOnBlock;
import pub.avalonframework.sqlhelper.core.block.helper.HelperOnBlock;
import pub.avalonframework.sqlhelper.core.builder.beans.AbstractOnBuilderBean;
import pub.avalonframework.sqlhelper.core.builder.beans.OnBuilderBean;
import pub.avalonframework.sqlhelper.core.builder.beans.OnBuilderBeanJoin;
import pub.avalonframework.sqlhelper.core.callback.OnCallback;
import pub.avalonframework.sqlhelper.core.callback.OnJoinCallback;
import pub.avalonframework.sqlhelper.core.data.SqlDataOnProducer;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.utils.HelperManager;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author baichao
 */
public abstract class OnBuilder<TO extends OnHelper<TO>> implements HelperOnBlock<OnBuilder<TO>>, CallbackOnBlock<TO, OnBuilder<TO>> {

    private TO onHelper;
    private String tableAlias;

    {
        this.onHelper = HelperManager.findOnHelperClassFromAncestorsGenericType(this);
    }

    public OnBuilder() {
        this.tableAlias = this.onHelper.getTableAlias();
    }

    public OnBuilder(String tableAlias) {
        this.onHelper.setTableAlias(tableAlias);
        this.tableAlias = tableAlias;
    }

    private List<AbstractOnBuilderBean> onBuilderBeans = new ArrayList<>(1);

    @Override
    public OnBuilder<TO> on(OnHelper<?>... onHelpers) {
        this.onBuilderBeans.add(new OnBuilderBean<>(this.onHelper, this.tableAlias).setOnHelpers(onHelpers));
        return this;
    }

    @Override
    public OnBuilder<TO> on(OnCallback<TO> onCallback) {
        this.onBuilderBeans.add(new OnBuilderBean<>(this.onHelper, this.tableAlias).setOnCallback(onCallback));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> OnBuilder<TO> on(Class<S> tableHelperClass, String tableAlias, OnJoinCallback<TO, SO> onJoinCallback) {
        this.onBuilderBeans.add(new OnBuilderBeanJoin<>(this.onHelper, tableHelperClass, tableAlias, onJoinCallback));
        return this;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<AbstractOnBuilderBean> getOnBuilderBeans() {
        return onBuilderBeans;
    }

    public void execute(SqlBuilderConfiguration sqlBuilderConfiguration, Supplier<SqlDataOnProducer> supplier) {
        execute(this, sqlBuilderConfiguration, supplier);
    }

    public static <FO extends OnHelper<FO>> void execute(OnBuilder<FO> onBuilder, SqlBuilderConfiguration sqlBuilderConfiguration, Supplier<SqlDataOnProducer> supplier) {
        if (supplier == null) {
            return;
        }
        SqlDataOnProducer sqlDataOnProducer = supplier.get();
        if (sqlDataOnProducer == null) {
            return;
        }
        onBuilder.getOnBuilderBeans().forEach(sqlOnBean -> sqlOnBean.execute(sqlBuilderConfiguration).forEach(sqlDataOnProducer::addTableOnDatum));
    }
}