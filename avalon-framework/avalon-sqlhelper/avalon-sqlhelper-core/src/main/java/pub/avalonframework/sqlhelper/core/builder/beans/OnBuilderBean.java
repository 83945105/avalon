package pub.avalonframework.sqlhelper.core.builder.beans;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.callback.OnCallback;
import pub.avalonframework.sqlhelper.core.callback.executor.CallbackExecutor;
import pub.avalonframework.sqlhelper.core.data.block.TableOnDataBlock;
import pub.avalonframework.sqlhelper.core.helper.OnHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public final class OnBuilderBean<TO extends OnHelper<TO>> extends AbstractOnBuilderBean {

    private TO onHelper;

    private OnHelper<?>[] onHelpers;

    private OnCallback<TO> onCallback;

    public OnBuilderBean(TO onHelper, String tableAlias) {
        super(tableAlias);
        this.onHelper = onHelper;
    }

    public OnBuilderBean<TO> setOnHelpers(OnHelper<?>[] onHelpers) {
        this.onHelpers = onHelpers;
        return this;
    }

    public OnBuilderBean<TO> setOnCallback(OnCallback<TO> onCallback) {
        this.onCallback = onCallback;
        return this;
    }

    @Override
    public List<TableOnDataBlock> execute(SqlBuilderConfiguration sqlBuilderConfiguration) {
        List<TableOnDataBlock> tableOnData = new ArrayList<>(1);
        if (this.onHelpers != null) {
            for (OnHelper<?> onHelper : this.onHelpers) {
                tableOnData.add(onHelper.execute());
            }
        }
        if (this.onCallback != null) {
            tableOnData.add(CallbackExecutor.execute(this.onHelper, this.onCallback, sqlBuilderConfiguration));
        }
        return tableOnData;
    }
}