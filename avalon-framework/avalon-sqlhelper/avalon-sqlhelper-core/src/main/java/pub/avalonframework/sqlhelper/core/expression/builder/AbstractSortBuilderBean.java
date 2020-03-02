package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.data.block.TableSortDataBlock;

import java.util.List;

/**
 * @author baichao
 */
public abstract class AbstractSortBuilderBean extends BuilderBean {

    public AbstractSortBuilderBean(String tableAlias) {
        super(tableAlias);
    }

    public abstract List<TableSortDataBlock> getTableSortDataBlocks(SqlBuilderConfiguration sqlBuilderConfiguration);
}