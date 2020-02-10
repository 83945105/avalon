package pub.avalonframework.sqlhelper.core.builder.beans;

import pub.avalonframework.sqlhelper.core.data.TableOnDatum;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;

import java.util.List;

/**
 * @author baichao
 */
public abstract class AbstractOnBuilderBean extends BuilderBean {

    public AbstractOnBuilderBean(String tableAlias) {
        super(tableAlias);
    }

    public abstract List<TableOnDatum> execute(SqlBuilderOptions sqlBuilderOptions);
}