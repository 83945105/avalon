package pub.avalonframework.wechat.official.account.core.filter.impl;

import pub.avalonframework.wechat.official.account.core.filter.FilterChain;
import pub.avalonframework.wechat.official.account.core.filter.TextFilter;
import pub.avalonframework.wechat.official.account.core.response.BaseMessage;
import pub.avalonframework.wechat.official.account.core.utils.ResponseMessageUtils;

import java.util.Map;

/**
 * 用户发送什么表情,就回复什么表情
 *
 * @author baichao
 */
public class FaceToFace extends TextFilter {

    @Override
    public BaseMessage doText(String content, BaseMessage respBaseMessage, Map<String, String> requestMap, FilterChain filterChain) {
        try {
            // 判断用户发送的是否是单个QQ表情
            if (this.execution(content)) {
                // 用户发什么QQ表情，就返回什么QQ表情
                return ResponseMessageUtils.buildTextMessage(respBaseMessage, content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filterChain.doFilter(requestMap, respBaseMessage);
    }
}