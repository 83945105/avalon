package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.data.block.TableColumnDataBlock;

import java.util.List;

/**
 * @author baichao
 */
public abstract class AbstractColumnBuilderBean extends BuilderBean {

    public AbstractColumnBuilderBean(String tableAlias) {
        super(tableAlias);
    }

    abstract public List<TableColumnDataBlock> execute(SqlBuilderConfiguration sqlBuilderConfiguration);
}