package pub.avalonframework.sqlhelper.core.builder.beans;

import pub.avalonframework.sqlhelper.core.callback.HavingCallback;
import pub.avalonframework.sqlhelper.core.callback.executor.CallbackExecutor;
import pub.avalonframework.sqlhelper.core.data.TableHavingDatum;
import pub.avalonframework.sqlhelper.core.helper.HavingHelper;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public final class HavingBuilderBean<TH extends HavingHelper<TH>> extends AbstractHavingBuilderBean {

    private TH havingHelper;

    private HavingHelper<?>[] havingHelpers;

    private HavingCallback<TH> havingCallback;

    public HavingBuilderBean(TH havingHelper, String tableAlias) {
        super(tableAlias);
        this.havingHelper = havingHelper;
    }

    public HavingBuilderBean<TH> setHavingHelpers(HavingHelper<?>[] havingHelpers) {
        this.havingHelpers = havingHelpers;
        return this;
    }

    public HavingBuilderBean<TH> setHavingCallback(HavingCallback<TH> havingCallback) {
        this.havingCallback = havingCallback;
        return this;
    }

    @Override
    public List<TableHavingDatum> execute(SqlBuilderOptions sqlBuilderOptions) {
        List<TableHavingDatum> tableHavingData = new ArrayList<>(1);
        if (this.havingHelpers != null) {
            for (HavingHelper<?> havingHelper : this.havingHelpers) {
                tableHavingData.add(havingHelper.execute());
            }
        }
        if (this.havingCallback != null) {
            tableHavingData.add(CallbackExecutor.execute(this.havingHelper, this.havingCallback, sqlBuilderOptions));
        }
        return tableHavingData;
    }
}