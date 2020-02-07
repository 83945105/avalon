package pub.avalonframework.web.spring.http.response.view;

import pub.avalonframework.database.Limit;

/**
 * The limit view.
 *
 * @author baichao
 */
public interface LimitView extends ResponseView {

    /**
     * Get limit.
     *
     * @return The limit.
     */
    Limit getLimit();

    /**
     * Set limit.
     *
     * @param limit The limit.
     */
    void setLimit(Limit limit);

    /**
     * Set limit json.
     *
     * @param limitJson The limit json.
     */
    void setLimit(String limitJson);
}