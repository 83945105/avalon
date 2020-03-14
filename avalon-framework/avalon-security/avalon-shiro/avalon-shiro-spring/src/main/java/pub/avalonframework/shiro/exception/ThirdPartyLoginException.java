package pub.avalonframework.shiro.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * 第三方登录异常
 *
 * @author baichao
 */
public class ThirdPartyLoginException extends AuthenticationException {

    public ThirdPartyLoginException() {
    }

    public ThirdPartyLoginException(String message) {
        super(message);
    }

    public ThirdPartyLoginException(Throwable cause) {
        super(cause);
    }

    public ThirdPartyLoginException(String message, Throwable cause) {
        super(message, cause);
    }
}
