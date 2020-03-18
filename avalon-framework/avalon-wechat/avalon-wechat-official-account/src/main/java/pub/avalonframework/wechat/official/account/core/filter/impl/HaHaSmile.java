package pub.avalonframework.wechat.official.account.core.filter.impl;

import pub.avalonframework.wechat.official.account.core.filter.FilterChain;
import pub.avalonframework.wechat.official.account.core.filter.TextFilter;
import pub.avalonframework.wechat.official.account.core.response.BaseMessage;
import pub.avalonframework.wechat.official.account.core.utils.ResponseMessageUtils;

import java.util.List;
import java.util.Map;

/**
 * 哈哈一笑
 *
 * @author baichao
 */
public class HaHaSmile extends TextFilter {

    /**
     * 请求地址
     */
    //public final static String url = "http://japi.juhe.cn/joke/content/list.from";
    public final static String url = "http://japi.juhe.cn/joke/content/text.from";
    /**
     * 聚合数据申请的key
     */
    public final static String key = "59a7f2e5974624c23dba4c010a385092";
    /**
     * 最大页数
     */
    public final static int MaxPage = 523833;

    @Override
    public BaseMessage doText(String content, BaseMessage respBaseMessage, Map<String, String> requestMap,
                              FilterChain filterChain) {
//        try {
//            if (this.execution(content)) {
//                @SuppressWarnings("unchecked")
//                //Map<String, Object> result = (Map<String, Object>) JSONUtils.parse(HttpUtil.doGet(url
//                        //		+ "?key=" + key + "&page=" + DataUtil.getRandomNum(MaxPage) + "&pagesize=1&sort=desc&time=" + DateUtil.getTimeStamp()));
//                        Map<String, Object> result = (Map<String, Object>) JSONUtils.parse(HttpUtil.doGet(url
//                        + "?page=" + DataUtil.getRandomNum(MaxPage) + "&pagesize=1&key=" + key));
//                if ("Success".equals(result.get("reason"))) {
//                    @SuppressWarnings("unchecked")
//                    Map<String, Object> resultMap = ((Map<String, List<Map<String, Object>>>) result.get("result")).get("data").get(0);
//
//                    return MessageUtils.buildTextMessage(respBaseMessage, resultMap.get("content").toString());
//                } else {
//                    return MessageUtils.buildTextMessage(respBaseMessage, "系统繁忙,请稍后尝试！");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return filterChain.doFilter(requestMap, respBaseMessage);
    }
}