package pub.avalonframework.web.spring.http.response;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * The result info.
 *
 * @author baichao
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public interface ResultInfo {

    /**
     * Get result code.
     *
     * @return The result code.
     */
    int getCode();

    /**
     * Set result code.
     *
     * @param code The result code.
     */
    void setCode(int code);

    /**
     * Get result message.
     *
     * @return The result message.
     */
    String getMessage();

    /**
     * Set result message.
     *
     * @param message The result message.
     */
    void setMessage(String message);

    /**
     * Is success.
     *
     * @return Is success.
     */
    boolean isSuccess();

    /**
     * Is fail.
     * Failure due to active interception inside the system.
     *
     * @return Is success.
     */
    boolean isFail();

    /**
     * Is error.
     * Failure due to internal system error.
     *
     * @return Is error.
     */
    boolean isError();

    /**
     * Is proxy authentication required.
     *
     * @return Is proxy authentication required.
     */
    boolean isProxyAuthenticationRequired();

    /**
     * Is unauthorized.
     *
     * @return Is unauthorized.
     */
    boolean isUnauthorized();

    /**
     * Get response type.
     *
     * @return The response type.
     */
    ResponseType getResponseType();
}