package pub.avalonframework.sqlhelper.core.sqlbuilder.template;

import pub.avalonframework.sqlhelper.core.data.consume.CrudConsumer;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;

/**
 * @author baichao
 */
public interface SqlPartBuilderTemplate {

    /**
     * build select column sql part result
     *
     * @param consumer {@link CrudConsumer}
     * @return {@link SqlBuilderResult}
     */
    SqlBuilderResult buildSelectColumn(CrudConsumer consumer);

    /**
     * build join sql part result
     *
     * @param consumer {@link CrudConsumer}
     * @return {@link SqlBuilderResult}
     */
    SqlBuilderResult buildJoin(CrudConsumer consumer);

    /**
     * build where sql part result
     *
     * @param consumer {@link CrudConsumer}
     * @return {@link SqlBuilderResult}
     */
    SqlBuilderResult buildWhere(CrudConsumer consumer);

    /**
     * build group sql part result
     *
     * @param consumer {@link CrudConsumer}
     * @return {@link SqlBuilderResult}
     */
    SqlBuilderResult buildGroup(CrudConsumer consumer);

    /**
     * build having sql part result
     *
     * @param consumer {@link CrudConsumer}er
     * @return {@link SqlBuilderResult}
     */
    SqlBuilderResult buildHaving(CrudConsumer consumer);

    /**
     * build sort sql part result
     *
     * @param consumer {@link CrudConsumer}
     * @return {@link SqlBuilderResult}
     */
    SqlBuilderResult buildSort(CrudConsumer consumer);

    /**
     * build limit sql part result
     *
     * @param consumer {@link CrudConsumer}
     * @return {@link SqlBuilderResult}
     */
    SqlBuilderResult buildLimit(CrudConsumer consumer);
}