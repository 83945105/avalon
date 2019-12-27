package pub.avalonframework.shiro.session;

/**
 * @author baichao
 */
@FunctionalInterface
public interface RemoveAttributeHandler {

    Object apply(Object key, Object removeValue);
}