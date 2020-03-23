package pub.avalonframework.security.data;

import pub.avalonframework.core.beans.AvalonException;

/**
 * @author baichao
 */
public class RuleContextException extends AvalonException {

    public RuleContextException() {
    }

    public RuleContextException(String message) {
        super(message);
    }

    public RuleContextException(String message, Throwable cause) {
        super(message, cause);
    }

    public RuleContextException(Throwable cause) {
        super(cause);
    }
}