package pub.avalonframework.sqlhelper.core.builder.beans;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.callback.GroupCallback;
import pub.avalonframework.sqlhelper.core.callback.executor.CallbackExecutor;
import pub.avalonframework.sqlhelper.core.data.block.TableGroupDataBlock;
import pub.avalonframework.sqlhelper.core.helper.GroupHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public final class GroupBuilderBean<TG extends GroupHelper<TG>> extends AbstractGroupBuilderBean {

    private TG groupHelper;

    private GroupHelper<?>[] groupHelpers;

    private GroupCallback<TG> groupCallback;

    public GroupBuilderBean(TG groupHelper, String tableAlias) {
        super(tableAlias);
        this.groupHelper = groupHelper;
    }

    public GroupBuilderBean<TG> setGroupHelpers(GroupHelper<?>[] groupHelpers) {
        this.groupHelpers = groupHelpers;
        return this;
    }

    public GroupBuilderBean<TG> setGroupCallback(GroupCallback<TG> groupCallback) {
        this.groupCallback = groupCallback;
        return this;
    }

    @Override
    public List<TableGroupDataBlock> execute(SqlBuilderConfiguration sqlBuilderConfiguration) {
        List<TableGroupDataBlock> tableGroupDataBlocks = new ArrayList<>(1);
        if (this.groupHelpers != null) {
            for (GroupHelper<?> groupHelper : this.groupHelpers) {
                tableGroupDataBlocks.add(groupHelper.execute());
            }
        }
        if (this.groupCallback != null) {
            tableGroupDataBlocks.add(CallbackExecutor.execute(this.groupHelper, this.groupCallback, sqlBuilderConfiguration));
        }
        return tableGroupDataBlocks;
    }
}