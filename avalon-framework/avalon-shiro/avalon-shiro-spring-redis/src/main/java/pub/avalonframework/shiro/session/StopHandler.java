package pub.avalonframework.shiro.session;

import java.util.Date;

/**
 * @author baichao
 */
@FunctionalInterface
public interface StopHandler {

    Date stop();
}