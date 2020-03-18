package pub.avalonframework.wechat.official.account.core.filter;

import pub.avalonframework.wechat.official.account.core.JsonKey;
import pub.avalonframework.wechat.official.account.core.response.BaseMessage;

import java.util.Map;

/**
 * 事件过滤器
 *
 * @author baichao
 */
public abstract class EventFilter implements Filter {

    @Override
    public BaseMessage doFilter(Map<String, String> requestMap, BaseMessage respBaseMessage, FilterChain filterChain) {
        return this.doEvent(requestMap.get(JsonKey.EventFilterKey.REQ_TYPE.getValue())
                , requestMap.get(JsonKey.EventFilterKey.REQ_KEY.getValue()), respBaseMessage, requestMap, filterChain);
    }

    /**
     * 处理事件请求
     *
     * @param eventKey        事件号
     * @param respBaseMessage 返回的信息,默认以接收方(公众账号)发送给请求方(请求用户)
     * @param requestMap      全部的信息集合
     * @param filterChain     过滤器链
     * @return 返回的信息, 可以改变信息内容
     */
    public abstract BaseMessage doEvent(String eventType, String eventKey, BaseMessage respBaseMessage, Map<String, String> requestMap, FilterChain filterChain);

    @Override
    public boolean execution(String content) throws Exception {
        return false;
    }
}