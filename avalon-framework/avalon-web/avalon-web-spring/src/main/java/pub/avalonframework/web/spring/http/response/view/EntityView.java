package pub.avalonframework.web.spring.http.response.view;

import java.lang.reflect.Type;

/**
 * The entity view.
 *
 * @author baichao
 */
public interface EntityView<T> extends ResponseView {

    /**
     * Get entity.
     *
     * @return The entity.
     */
    T getEntity();

    /**
     * Set entity.
     *
     * @param entity The entity.
     */
    void setEntity(T entity);

    /**
     * Set entity.
     *
     * @param entityJson The entity json.
     * @param type       The entity type.
     */
    void setEntity(String entityJson, Type type);
}