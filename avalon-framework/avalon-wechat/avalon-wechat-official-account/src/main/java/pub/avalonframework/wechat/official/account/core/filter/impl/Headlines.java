package pub.avalonframework.wechat.official.account.core.filter.impl;

import com.alibaba.fastjson.JSONObject;
import pub.avalonframework.wechat.official.account.core.filter.FilterChain;
import pub.avalonframework.wechat.official.account.core.filter.TextFilter;
import pub.avalonframework.wechat.official.account.core.response.BaseMessage;
import pub.avalonframework.wechat.official.account.core.utils.ResponseMessageUtils;

import java.util.List;
import java.util.Map;

/**
 * 新闻头条
 *
 * @author baichao
 */
public class Headlines extends TextFilter {

    /**
     * 请求地址
     */
    public final static String url = "http://v.juhe.cn/toutiao/index";
    /**
     * 聚合数据申请的key
     */
    public final static String key = "fc718085f581112b2af5bad3a958ee3f";

    @SuppressWarnings("unchecked")
    @Override
    public BaseMessage doText(String content, BaseMessage respBaseMessage, Map<String, String> requestMap,
                              FilterChain filterChain) {
//        try {
//            if (this.execution(content)) {
//
//                String type = "top";
//
//                try {
//                    switch (content.split("-")[1]) {
//                        case "top":
//
//                            break;
//                        case "shehui":
//                            type = "shehui";
//                            break;
//                        case "guonei":
//                            type = "guonei";
//                            break;
//                        case "guoji":
//                            type = "guoji";
//                            break;
//                        case "yule":
//                            type = "yule";
//                            break;
//                        case "tiyu":
//                            type = "tiyu";
//                            break;
//                        case "junshi":
//                            type = "junshi";
//                            break;
//                        case "keji":
//                            type = "keji";
//                            break;
//                        case "caijing":
//                            type = "caijing";
//                            break;
//                        case "shishang":
//                            type = "shishang";
//                            break;
//                        default:
//                            break;
//                    }
//                } catch (Exception e) {
//                	e.printStackTrace();
//                }
//
//                Map<String, Object> result = (Map<String, Object>) JSONObject.parse(HttpUtil.doGet(url
//                        + "?type=" + type + "&"
//                        + "key=" + key));
//                if ("0".equals(result.get("error_code").toString())) {
//                    List<Map<String, String>> news = ((Map<String, List<Map<String, String>>>) (result.get("result"))).get("data");
//                    Map<String, String> _new = news.get(DataUtil.getRandomNum(news.size()));
//
//
//                    return MessageUtils.buildNewsMessage(respBaseMessage, MessageUtils.buildArticle(_new.get("title")
//                            , _new.get("category") + "-" + _new.get("author_name")
//                            , _new.get("thumbnail_pic_s")
//                            , _new.get("url")));
//
//                } else {
//                    return MessageUtils.buildTextMessage(respBaseMessage, "系统繁忙...请稍后再试");
//                }
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return filterChain.doFilter(requestMap, respBaseMessage);
    }
}