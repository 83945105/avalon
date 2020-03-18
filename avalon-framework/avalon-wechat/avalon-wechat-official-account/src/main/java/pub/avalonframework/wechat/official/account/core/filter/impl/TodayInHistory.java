package pub.avalonframework.wechat.official.account.core.filter.impl;

import com.alibaba.fastjson.JSONObject;
import pub.avalonframework.wechat.official.account.core.filter.FilterChain;
import pub.avalonframework.wechat.official.account.core.filter.TextFilter;
import pub.avalonframework.wechat.official.account.core.response.BaseMessage;
import pub.avalonframework.wechat.official.account.core.utils.ResponseMessageUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 历史上的今天
 * @author baichao
 */
public class TodayInHistory extends TextFilter {
	
	/**请求地址*/
	public final static String url = "http://v.juhe.cn/todayOnhistory/queryEvent.php";
	/**聚合数据申请的key*/
	public final static String key = "6ae5e923c1108d24b71a1e388066f6a5";
	/**查看详情方法*/
	private String viewDetailsMethod = "查看详情方法待编辑...";
	/**最大查询条数*/
	public final static int manSize = 20;

	@SuppressWarnings("unchecked")
	@Override
	public BaseMessage doText(String content, BaseMessage respBaseMessage, Map<String, String> requestMap,
							  FilterChain filterChain) {
//		try {
//			if(this.execution(content)) {
//				String[] dateParams = getDateParams();
//				Map<String, Object> result = JSONObject.parseObject(HttpUtil.doGet(url
//						+ "?date=" + dateParams[1] + "/" + dateParams[2] + "&key=" + key));
//				if("0".equals(result.get("error_code").toString())) {
//					List<Map<String, String>> results = (List<Map<String, String>>) result.get("result");
//					StringBuilder sb = new StringBuilder("历史的" + dateParams[1] + "月" + dateParams[2] + "号有如下事件发生:" + MessageUtil.NEWLINEX2);
//					for (int i = 0; i < results.size() && i < manSize; i++) {
//						sb.append(results.get(i).get("date") + "-"
//								+ results.get(i).get("title") + "-事件号:"
//								+ results.get(i).get("e_id") + MessageUtil.NEWLINE);
//					}
//					sb.append(viewDetailsMethod);
//					return MessageUtils.buildTextMessage(respBaseMessage, sb.toString());
//				}else {
//					return MessageUtils.buildTextMessage(respBaseMessage, "系统繁忙...请稍后再试！！！");
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return filterChain.doFilter(requestMap, respBaseMessage);
	}

	private String[] getDateParams() {
		return new SimpleDateFormat().format(new Date()).split(" ")[0].split("-");
	}

	/**
	 * 设置查看详情方法信息
	 */
	public void setViewDetailsMethod(String viewDetailsMethod) {
		this.viewDetailsMethod = viewDetailsMethod;
	}
}