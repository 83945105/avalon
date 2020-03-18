package pub.avalonframework.wechat.official.account.core.filter;

/**
 * 过滤器链类型
 *
 * @author baichao
 */
public enum FilterChainType {

    /**
     * 文本过滤器
     */
    TEXT_FILTERCHAIN,
    /**
     * 图片过滤器
     */
    IMAGE_FILTERCHAIN,
    /**
     * 地理位置过滤器
     */
    LOCATION_FILTERCHAIN,
    /**
     * 链接过滤器
     */
    LINK_FILTERCHAIN,
    /**
     * 音频过滤器
     */
    VOICE_FILTERCHAIN,
    /**
     * 事件过滤器
     */
    EVENT_FILTERCHAIN
}