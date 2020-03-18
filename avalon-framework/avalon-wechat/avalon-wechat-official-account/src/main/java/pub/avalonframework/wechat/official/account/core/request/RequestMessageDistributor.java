package pub.avalonframework.wechat.official.account.core.request;

import pub.avalonframework.wechat.official.account.core.JsonKey;
import pub.avalonframework.wechat.official.account.core.filter.DefaultFilterChainFactory;
import pub.avalonframework.wechat.official.account.core.filter.FilterChainFactory;
import pub.avalonframework.wechat.official.account.core.response.BaseMessage;

import java.util.Date;
import java.util.Map;

/**
 * 请求消息分发器
 *
 * @author baichao
 */
public class RequestMessageDistributor {

    private FilterChainFactory filterChainFactory;//这里要进行处理,将过滤器链做成连接池

    public RequestMessageDistributor() {
        super();
//        filterChainFactory = BeanUtil.inject(FilterChainFactory.class);
        filterChainFactory = filterChainFactory == null ? new DefaultFilterChainFactory() : filterChainFactory;
    }

    /**
     * 处理消息
     *
     * @param fromUserName 发送方帐号（open_id）
     * @param toUserName   公众帐号
     * @param msgType      消息类型
     * @return 处理后的返回消息
     */
    public pub.avalonframework.wechat.official.account.core.response.BaseMessage doMessage(Map<String, String> requestMap, String fromUserName, String toUserName, String msgType) {
        pub.avalonframework.wechat.official.account.core.response.BaseMessage respBaseMessage = new BaseMessage();
        respBaseMessage.setToUserName(fromUserName);
        respBaseMessage.setFromUserName(toUserName);
        respBaseMessage.setCreateTime(new Date().getTime());
        respBaseMessage.setFuncFlag(0);

        if (msgType.equals(JsonKey.MessageTypeKey.REQ_TEXT.getValue())) {// 文本消息
            return filterChainFactory.buildTextFilterChain().doFilter(requestMap, respBaseMessage);
        } else if (msgType.equals(JsonKey.MessageTypeKey.REQ_IMAGE.getValue())) {//图片消息
            return filterChainFactory.buildImageFilterChain().doFilter(requestMap, respBaseMessage);
        } else if (msgType.equals(JsonKey.MessageTypeKey.REQ_LOCATION.getValue())) {//地理位置消息
            return filterChainFactory.buildLocationFilterChain().doFilter(requestMap, respBaseMessage);
        } else if (msgType.equals(JsonKey.MessageTypeKey.REQ_LINK.getValue())) {//链接消息
            return filterChainFactory.buildLinkFilterChain().doFilter(requestMap, respBaseMessage);
        } else if (msgType.equals(JsonKey.MessageTypeKey.REQ_VOICE.getValue())) {//音频消息
            return filterChainFactory.buildVoiceFilterChain().doFilter(requestMap, respBaseMessage);
        } else if (msgType.equals(JsonKey.MessageTypeKey.REQ_EVENT.getValue())) {//事件推送
            return filterChainFactory.buildEventFilterChain().doFilter(requestMap, respBaseMessage);
/*            // 事件类型
            String eventType = requestMap.get("Event");
            // 订阅
            if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
               // respContent = "谢谢您的关注！";
            }
            // 取消订阅
            else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
                // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
            }
            // 自定义菜单点击事件
            else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                // TODO 自定义菜单权没有开放，暂不处理该类消息
            }  */
        }
        return null;
    }
}