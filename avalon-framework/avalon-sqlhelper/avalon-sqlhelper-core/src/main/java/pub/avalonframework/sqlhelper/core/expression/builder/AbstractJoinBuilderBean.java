package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.data.block.TableJoinDataBlock;

/**
 * @author baichao
 */
public abstract class AbstractJoinBuilderBean extends BuilderBean {

    public AbstractJoinBuilderBean(String tableAlias) {
        super(tableAlias);
    }

    public abstract TableJoinDataBlock getTableJoinDataBlocks(SqlBuilderConfiguration sqlBuilderConfiguration);
}