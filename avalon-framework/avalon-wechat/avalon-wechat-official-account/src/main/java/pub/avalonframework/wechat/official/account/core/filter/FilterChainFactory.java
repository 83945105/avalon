package pub.avalonframework.wechat.official.account.core.filter;

/**
 * 过滤器链工厂
 *
 * @author baichao
 */
public interface FilterChainFactory {

    /**
     * 构建文本过滤器链
     */
	FilterChain buildTextFilterChain();

    /**
     * 构建图片过滤器链
     */
	FilterChain buildImageFilterChain();

    /**
     * 构建地理位置过滤器链
     */
	FilterChain buildLocationFilterChain();

    /**
     * 构建链接过滤器链
     */
	FilterChain buildLinkFilterChain();

    /**
     * 构建音频过滤器链
     */
	FilterChain buildVoiceFilterChain();

    /**
     * 构建事件过滤器链
     */
	FilterChain buildEventFilterChain();
}