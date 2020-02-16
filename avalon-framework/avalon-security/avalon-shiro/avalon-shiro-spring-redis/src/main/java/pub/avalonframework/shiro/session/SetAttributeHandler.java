package pub.avalonframework.shiro.session;

/**
 * @author baichao
 */
@FunctionalInterface
public interface SetAttributeHandler {

    void accept(Object key, Object value);
}