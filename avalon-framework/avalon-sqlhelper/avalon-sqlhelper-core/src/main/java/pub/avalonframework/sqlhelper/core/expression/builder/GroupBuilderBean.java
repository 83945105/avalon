package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.data.block.TableGroupDataBlock;
import pub.avalonframework.sqlhelper.core.expression.lambda.GroupLambdaCallable;
import pub.avalonframework.sqlhelper.core.expression.lambda.execute.LambdaCallableExecutor;
import pub.avalonframework.sqlhelper.core.helper.GroupHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public final class GroupBuilderBean<TG extends GroupHelper<TG>> extends AbstractGroupBuilderBean {

    private TG groupHelper;

    private GroupHelper<?>[] groupHelpers;

    private GroupLambdaCallable<TG> groupLambdaCallable;

    public GroupBuilderBean(TG groupHelper, String tableAlias) {
        super(tableAlias);
        this.groupHelper = groupHelper;
    }

    public GroupBuilderBean<TG> setGroupHelpers(GroupHelper<?>[] groupHelpers) {
        this.groupHelpers = groupHelpers;
        return this;
    }

    public GroupBuilderBean<TG> setGroupLambdaCallable(GroupLambdaCallable<TG> groupLambdaCallable) {
        this.groupLambdaCallable = groupLambdaCallable;
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
        if (this.groupLambdaCallable != null) {
            tableGroupDataBlocks.add(LambdaCallableExecutor.execute(this.groupHelper, this.groupLambdaCallable, sqlBuilderConfiguration));
        }
        return tableGroupDataBlocks;
    }
}