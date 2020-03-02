package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.data.block.TableGroupDataBlock;

import java.util.List;

/**
 * @author baichao
 */
public abstract class AbstractGroupBuilderBean extends BuilderBean {

    public AbstractGroupBuilderBean(String tableAlias) {
        super(tableAlias);
    }

    public abstract List<TableGroupDataBlock> getTableGroupDataBlocks(SqlBuilderConfiguration sqlBuilderConfiguration);
}