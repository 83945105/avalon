package pub.avalonframework.sqlhelper.core.builder.beans;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.data.TableColumnDatum;

import java.util.List;

/**
 * @author baichao
 */
public abstract class AbstractColumnBuilderBean extends BuilderBean {

    public AbstractColumnBuilderBean(String tableAlias) {
        super(tableAlias);
    }

    abstract public List<TableColumnDatum> execute(SqlBuilderConfiguration sqlBuilderConfiguration);
}