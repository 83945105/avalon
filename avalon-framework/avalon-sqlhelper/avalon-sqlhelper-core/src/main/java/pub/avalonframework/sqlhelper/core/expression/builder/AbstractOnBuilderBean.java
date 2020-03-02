package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.data.block.TableOnDataBlock;

import java.util.List;

/**
 * @author baichao
 */
public abstract class AbstractOnBuilderBean extends BuilderBean {

    public AbstractOnBuilderBean(String tableAlias) {
        super(tableAlias);
    }

    public abstract List<TableOnDataBlock> execute(SqlBuilderConfiguration sqlBuilderConfiguration);
}