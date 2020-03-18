package pub.avalonframework.wechat.official.account.core.filter.impl;

import com.alibaba.fastjson.JSONObject;
import pub.avalonframework.wechat.official.account.core.filter.FilterChain;
import pub.avalonframework.wechat.official.account.core.filter.TextFilter;
import pub.avalonframework.wechat.official.account.core.response.BaseMessage;
import pub.avalonframework.wechat.official.account.core.utils.ResponseMessageUtils;

import java.util.Map;

/**
 * 问答机器人
 *
 * @author baicaho
 */
public class QA_Robot extends TextFilter {

    /**
     * 请求地址
     */
    public final static String url = "http://op.juhe.cn/robot/index";
    /**
     * 聚合数据申请的key
     */
    public final static String key = "56c5431c536a90b224c79ab056ba488e";

    @SuppressWarnings("unchecked")
    @Override
    public BaseMessage doText(String content, BaseMessage respBaseMessage, Map<String, String> requestMap,
                              FilterChain filterChain) {
//        try {
//            if (this.execution(content)) {
//                Map<String, Object> result = (Map<String, Object>) JSONObject.parse(HttpUtil.doGet(url
//                        + "?info=" + content.trim()
//                        + "&key=" + key
//                        + "&userid=" + respBaseMessage.getToUserName()));
//                if ("0".equals(result.get("error_code").toString())) {
//                    return MessageUtils.buildTextMessage(respBaseMessage, ((Map<String, Object>) result.get("result")).get("text").toString().replace("图灵机器人", "小白"));
//                } else {
//                    return MessageUtils.buildTextMessage(respBaseMessage, "你是外星人吗?");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return filterChain.doFilter(requestMap, respBaseMessage);
    }
}