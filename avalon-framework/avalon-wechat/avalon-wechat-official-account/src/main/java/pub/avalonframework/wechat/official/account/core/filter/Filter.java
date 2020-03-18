package pub.avalonframework.wechat.official.account.core.filter;

import pub.avalonframework.wechat.official.account.core.response.BaseMessage;

import java.util.Map;

/**
 * 过滤器接口
 *
 * @author baichao
 */
public interface Filter {

    /**
     * 消息过滤
     *
     * @param requestMap      请求参数集合
     * @param respBaseMessage 返回的信息,默认以接收方(公众账号)发送给请求方(请求用户)
     * @param filterChain     消息过滤链
     * @return 返回的信息, 可以改变信息内容
     */
    BaseMessage doFilter(Map<String, String> requestMap, BaseMessage respBaseMessage, FilterChain filterChain);

    /**
     * 过滤器执行条件
     */
    boolean execution(String content) throws Exception;
}