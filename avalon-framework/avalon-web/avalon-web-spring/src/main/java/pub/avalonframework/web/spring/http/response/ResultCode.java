package pub.avalonframework.web.spring.http.response;

/**
 * The result code.
 *
 * @author baichao
 */
public enum ResultCode {
    /**
     * 失败
     */
    FAIL(0),
    /**
     * 成功
     */
    SUCCESS(1),
    /**
     * 警告
     */
    WARN(2),
    /**
     * 信息提示
     */
    INFO(3),
    /**
     * 错误
     */
    ERROR(4),
    /**
     * 需要登录
     */
    NEED_LOGIN(5),
    /**
     * 无权
     */
    NO_AUTHORITY(6),
    /**
     * 资源不存在
     */
    NOT_FOUND(404);

    private final int code;

    ResultCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}