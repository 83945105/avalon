package pub.avalonframework.sqlhelper.core.builder.beans;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.callback.ColumnCallback;
import pub.avalonframework.sqlhelper.core.callback.executor.CallbackExecutor;
import pub.avalonframework.sqlhelper.core.data.block.TableColumnDataBlock;
import pub.avalonframework.sqlhelper.core.helper.ColumnHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public final class ColumnBuilderBean<TC extends ColumnHelper<TC>> extends AbstractColumnBuilderBean {

    private TC columnHelper;

    private ColumnHelper<?>[] columnHelpers;

    private ColumnCallback<TC> columnCallback;

    public ColumnBuilderBean(TC columnHelper, String tableAlias) {
        super(tableAlias);
        this.columnHelper = columnHelper;
    }

    public ColumnBuilderBean<TC> setColumnHelpers(ColumnHelper<?>[] columnHelpers) {
        this.columnHelpers = columnHelpers;
        return this;
    }

    public ColumnBuilderBean<TC> setColumnCallback(ColumnCallback<TC> columnCallback) {
        this.columnCallback = columnCallback;
        return this;
    }

    @Override
    public List<TableColumnDataBlock> execute(SqlBuilderConfiguration sqlBuilderConfiguration) {
        List<TableColumnDataBlock> tableColumnDataBlocks = new ArrayList<>(1);
        if (this.columnHelpers != null) {
            for (ColumnHelper<?> columnHelper : this.columnHelpers) {
                tableColumnDataBlocks.add(columnHelper.execute());
            }
        }
        if (this.columnCallback != null) {
            tableColumnDataBlocks.add(CallbackExecutor.execute(this.columnHelper, this.columnCallback, sqlBuilderConfiguration));
        }
        return tableColumnDataBlocks;
    }
}