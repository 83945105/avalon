package pub.avalonframework.sqlhelper.core.builder.beans;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.callback.WhereCallback;
import pub.avalonframework.sqlhelper.core.callback.executor.CallbackExecutor;
import pub.avalonframework.sqlhelper.core.data.block.TableWhereDataBlock;
import pub.avalonframework.sqlhelper.core.helper.WhereHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public final class WhereBuilderBean<TW extends WhereHelper<TW>> extends AbstractWhereBuilderBean {

    private TW whereHelper;

    private WhereHelper<?>[] whereHelpers;

    private WhereCallback<TW> whereCallback;

    public WhereBuilderBean(TW whereHelper, String tableAlias) {
        super(tableAlias);
        this.whereHelper = whereHelper;
    }

    public WhereBuilderBean<TW> setWhereHelpers(WhereHelper<?>[] whereHelpers) {
        this.whereHelpers = whereHelpers;
        return this;
    }

    public WhereBuilderBean<TW> setWhereCallback(WhereCallback<TW> whereCallback) {
        this.whereCallback = whereCallback;
        return this;
    }

    @Override
    public List<TableWhereDataBlock> execute(SqlBuilderConfiguration sqlBuilderConfiguration) {
        List<TableWhereDataBlock> tableWhereData = new ArrayList<>(1);
        if (this.whereHelpers != null) {
            for (WhereHelper<?> whereHelper : this.whereHelpers) {
                tableWhereData.add(whereHelper.execute());
            }
        }
        if (this.whereCallback != null) {
            tableWhereData.add(CallbackExecutor.execute(this.whereHelper, this.whereCallback, sqlBuilderConfiguration));
        }
        return tableWhereData;
    }
}