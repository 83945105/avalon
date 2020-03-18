package pub.avalonframework.wechat.official.account.core.filter.impl;

import pub.avalonframework.wechat.official.account.core.filter.FilterChain;
import pub.avalonframework.wechat.official.account.core.filter.TextFilter;
import pub.avalonframework.wechat.official.account.core.response.BaseMessage;
import pub.avalonframework.wechat.official.account.core.utils.ResponseMessageUtils;

import java.util.Map;

public class DefaultTextFilter extends TextFilter {

    @Override
    public BaseMessage doText(String content, BaseMessage respBaseMessage, Map<String, String> requestMap,
                              FilterChain filterChain) {

        return ResponseMessageUtils.buildTextMessage(respBaseMessage, "功能开发中...敬请期待！！！");
    }
}