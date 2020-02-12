package pub.avalonframework.sqlhelper.core.builder.beans;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.data.TableWhereDatum;

import java.util.List;

/**
 * @author baichao
 */
public abstract class AbstractWhereBuilderBean extends BuilderBean {

    public AbstractWhereBuilderBean(String tableAlias) {
        super(tableAlias);
    }

    public abstract List<TableWhereDatum> execute(SqlBuilderConfiguration sqlBuilderConfiguration);
}