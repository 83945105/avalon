package pub.avalonframework.sqlhelper.core.builder.beans;

import pub.avalonframework.sqlhelper.core.data.TableWhereDatum;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;

import java.util.List;

/**
 * @author baichao
 */
public abstract class AbstractWhereBuilderBean extends BuilderBean {

    public AbstractWhereBuilderBean(String tableAlias) {
        super(tableAlias);
    }

    public abstract List<TableWhereDatum> execute(SqlBuilderOptions sqlBuilderOptions);
}