package pub.avalonframework.sqlhelper.core.sqlbuilder.template;

import pub.avalonframework.sqlhelper.core.data.consume.CrudConsumer;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;

/**
 * @author baichao
 */
public interface DataBlockBuilderTemplate {

    /**
     * Build select column sql part result
     *
     * @param consumer {@link CrudConsumer}
     * @return {@link SqlBuilderResult}
     */
    SqlBuilderResult buildSelectColumn(CrudConsumer consumer);

    /**
     * Build join sql part result
     *
     * @param consumer {@link CrudConsumer}
     * @return {@link SqlBuilderResult}
     */
    SqlBuilderResult buildJoin(CrudConsumer consumer);

    /**
     * Build where sql part result
     *
     * @param consumer {@link CrudConsumer}
     * @return {@link SqlBuilderResult}
     */
    SqlBuilderResult buildWhere(CrudConsumer consumer);

    /**
     * Build group sql part result
     *
     * @param consumer {@link CrudConsumer}
     * @return {@link SqlBuilderResult}
     */
    SqlBuilderResult buildGroup(CrudConsumer consumer);

    /**
     * Build having sql part result
     *
     * @param consumer {@link CrudConsumer}er
     * @return {@link SqlBuilderResult}
     */
    SqlBuilderResult buildHaving(CrudConsumer consumer);

    /**
     * Build sort sql part result
     *
     * @param consumer {@link CrudConsumer}
     * @return {@link SqlBuilderResult}
     */
    SqlBuilderResult buildSort(CrudConsumer consumer);

    /**
     * Build limit sql part result
     *
     * @param consumer {@link CrudConsumer}
     * @return {@link SqlBuilderResult}
     */
    SqlBuilderResult buildLimit(CrudConsumer consumer);
}