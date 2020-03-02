package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.data.block.TableWhereDataBlock;

import java.util.List;

/**
 * @author baichao
 */
public abstract class AbstractWhereBuilderBean extends BuilderBean {

    public AbstractWhereBuilderBean(String tableAlias) {
        super(tableAlias);
    }

    public abstract List<TableWhereDataBlock> execute(SqlBuilderConfiguration sqlBuilderConfiguration);
}