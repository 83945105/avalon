package pub.avalonframework.sqlhelper.core.sqlbuilder;

import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.SelectSqlBuilderResult;

/**
 * @author baichao
 */
public interface SelectSqlBuilder extends SqlBuilder {

    /**
     * Query
     *
     * @return {@link SelectSqlBuilderResult}
     */
    SelectSqlBuilderResult query();

    /**
     * Query count
     *
     * @return {@link SelectSqlBuilderResult}
     */
    SelectSqlBuilderResult queryCount();

    /**
     * Query by primary key
     *
     * @param primaryKeyValue primary key value
     * @return {@link SelectSqlBuilderResult}
     */
    SelectSqlBuilderResult queryByPrimaryKey(Object primaryKeyValue);
}