package pub.avalonframework.sqlhelper.core.builder.beans;

import pub.avalonframework.sqlhelper.core.data.TableSortDatum;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;

import java.util.List;

/**
 * @author baichao
 */
public abstract class AbstractSortBuilderBean extends BuilderBean {

    public AbstractSortBuilderBean(String tableAlias) {
        super(tableAlias);
    }

    public abstract List<TableSortDatum> execute(SqlBuilderOptions sqlBuilderOptions);
}