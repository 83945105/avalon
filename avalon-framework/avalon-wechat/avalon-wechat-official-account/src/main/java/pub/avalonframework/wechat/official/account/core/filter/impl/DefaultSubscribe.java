package pub.avalonframework.wechat.official.account.core.filter.impl;

import pub.avalonframework.wechat.official.account.core.JsonKey;
import pub.avalonframework.wechat.official.account.core.filter.EventFilter;
import pub.avalonframework.wechat.official.account.core.filter.FilterChain;
import pub.avalonframework.wechat.official.account.core.response.BaseMessage;

import java.util.Map;

/**
 * 默认用户关注公众号提示消息过滤器
 *
 * @author baichao
 */
public class DefaultSubscribe extends EventFilter {

    @Override
    public BaseMessage doEvent(String eventType, String eventKey, BaseMessage respBaseMessage,
                               Map<String, String> requestMap, FilterChain filterChain) {
//        try {
//            if (this.execution(eventType)) {
//                return BaseMessageUtil.buildTextMessage(respBaseMessage, Config.DEFAULT_SUBSCRIBE_MSG);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return filterChain.doFilter(requestMap, respBaseMessage);
    }

    @Override
    public boolean execution(String content) throws Exception {
		return JsonKey.MessageTypeKey.EVENT_SUBSCRIBE.getValue().equals(content);
	}
}