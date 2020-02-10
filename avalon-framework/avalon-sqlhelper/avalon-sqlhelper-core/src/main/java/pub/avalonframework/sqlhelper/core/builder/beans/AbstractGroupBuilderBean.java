package pub.avalonframework.sqlhelper.core.builder.beans;

import pub.avalonframework.sqlhelper.core.data.TableGroupDatum;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;

import java.util.List;

/**
 * @author baichao
 */
public abstract class AbstractGroupBuilderBean extends BuilderBean {

    public AbstractGroupBuilderBean(String tableAlias) {
        super(tableAlias);
    }

    public abstract List<TableGroupDatum> execute(SqlBuilderOptions sqlBuilderOptions);
}