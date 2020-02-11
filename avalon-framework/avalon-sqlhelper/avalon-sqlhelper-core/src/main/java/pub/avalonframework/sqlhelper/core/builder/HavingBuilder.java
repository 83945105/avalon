package pub.avalonframework.sqlhelper.core.builder;

import pub.avalonframework.sqlhelper.core.block.callback.CallbackHavingBlock;
import pub.avalonframework.sqlhelper.core.block.helper.HelperHavingBlock;
import pub.avalonframework.sqlhelper.core.builder.beans.AbstractHavingBuilderBean;
import pub.avalonframework.sqlhelper.core.builder.beans.HavingBuilderBean;
import pub.avalonframework.sqlhelper.core.builder.beans.HavingBuilderBeanJoin;
import pub.avalonframework.sqlhelper.core.callback.HavingCallback;
import pub.avalonframework.sqlhelper.core.callback.HavingJoinCallback;
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
public abstract class HavingBuilder<TH extends HavingHelper<TH>> implements HelperHavingBlock<HavingBuilder<TH>>, CallbackHavingBlock<TH, HavingBuilder<TH>> {

    private TH havingHelper;
    private String tableAlias;

    {
        this.havingHelper = HelperManager.findHavingHelperClassFromAncestorsGenericType(this);
    }

    public HavingBuilder() {
        this.tableAlias = this.havingHelper.getTableAlias();
    }

    public HavingBuilder(String tableAlias) {
        this.havingHelper.setTableAlias(tableAlias);
        this.tableAlias = tableAlias;
    }

    private List<AbstractHavingBuilderBean> havingBuilderBeans = new ArrayList<>(1);

    @Override
    public HavingBuilder<TH> having(HavingHelper<?>... havingHelpers) {
        this.havingBuilderBeans.add(new HavingBuilderBean<>(this.havingHelper, this.tableAlias).setHavingHelpers(havingHelpers));
        return this;
    }

    @Override
    public HavingBuilder<TH> having(HavingCallback<TH> havingCallback) {
        this.havingBuilderBeans.add(new HavingBuilderBean<>(this.havingHelper, this.tableAlias).setHavingCallback(havingCallback));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> HavingBuilder<TH> having(Class<S> tableHelperClass, String tableAlias, HavingJoinCallback<TH, SH> havingJoinCallback) {
        this.havingBuilderBeans.add(new HavingBuilderBeanJoin<>(this.havingHelper, tableHelperClass, tableAlias, havingJoinCallback));
        return this;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<AbstractHavingBuilderBean> getHavingBuilderBeans() {
        return havingBuilderBeans;
    }

    public void execute(SqlBuilderOptions sqlBuilderOptions, Supplier<SqlDataCrudProducer> supplier) {
        execute(this, sqlBuilderOptions, supplier);
    }

    public static <FW extends HavingHelper<FW>> void execute(HavingBuilder<FW> havingBuilder, SqlBuilderOptions sqlBuilderOptions, Supplier<SqlDataCrudProducer> supplier) {
        if (supplier == null) {
            return;
        }
        SqlDataCrudProducer sqlDataCrudProducer = supplier.get();
        if (sqlDataCrudProducer == null) {
            return;
        }
        havingBuilder.getHavingBuilderBeans().forEach(sqlHavingBean -> sqlHavingBean.execute(sqlBuilderOptions).forEach(sqlDataCrudProducer::addTableHavingDatum));
    }
}