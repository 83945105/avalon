package pub.avalonframework.sqlhelper.core.builder.beans;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.data.block.TableHavingDataBlock;

import java.util.List;

/**
 * @author baichao
 */
public abstract class AbstractHavingBuilderBean extends BuilderBean {

    public AbstractHavingBuilderBean(String tableAlias) {
        super(tableAlias);
    }

    public abstract List<TableHavingDataBlock> execute(SqlBuilderConfiguration sqlBuilderConfiguration);
}