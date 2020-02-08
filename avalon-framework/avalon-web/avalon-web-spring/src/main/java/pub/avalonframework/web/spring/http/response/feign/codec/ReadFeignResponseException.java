package pub.avalonframework.web.spring.http.response.feign.codec;

import pub.avalonframework.core.beans.AvalonException;

/**
 * @author baichao
 */
public class ReadFeignResponseException extends AvalonException {

    public ReadFeignResponseException() {
    }

    public ReadFeignResponseException(String message) {
        super(message);
    }

    public ReadFeignResponseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReadFeignResponseException(Throwable cause) {
        super(cause);
    }
}
