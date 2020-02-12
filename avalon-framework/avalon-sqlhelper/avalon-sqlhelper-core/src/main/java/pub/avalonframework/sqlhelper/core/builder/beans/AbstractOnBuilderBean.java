package pub.avalonframework.sqlhelper.core.builder.beans;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.data.TableOnDatum;

import java.util.List;

/**
 * @author baichao
 */
public abstract class AbstractOnBuilderBean extends BuilderBean {

    public AbstractOnBuilderBean(String tableAlias) {
        super(tableAlias);
    }

    public abstract List<TableOnDatum> execute(SqlBuilderConfiguration sqlBuilderConfiguration);
}