package pub.avalonframework.sqlhelper.core.builder.beans;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.data.JoinTableDatum;

/**
 * @author baichao
 */
public abstract class AbstractJoinBuilderBean extends BuilderBean {

    public AbstractJoinBuilderBean(String tableAlias) {
        super(tableAlias);
    }

    public abstract JoinTableDatum execute(SqlBuilderConfiguration sqlBuilderConfiguration);
}