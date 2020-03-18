package pub.avalonframework.wechat.official.account.core.filter.impl;

import com.alibaba.fastjson.JSONObject;
import pub.avalonframework.wechat.official.account.core.filter.FilterChain;
import pub.avalonframework.wechat.official.account.core.filter.TextFilter;
import pub.avalonframework.wechat.official.account.core.response.BaseMessage;
import pub.avalonframework.wechat.official.account.core.utils.ResponseMessageUtils;

import java.util.Map;

/**
 * 手机号码归属地查询
 * @author baichao
 */
public class MobilePhoneNumberAttribution extends TextFilter {

	/**请求地址*/
	public final static String url = "http://apis.juhe.cn/mobile/get";
	/**聚合数据申请的key*/
	public final static String key = "dee9ab4ec0d7299cdbce7a6cd05cae87";
	/**查询的手机号*/
	private String cellPhoneNumber = null;
	
	@SuppressWarnings("unchecked")
	@Override
	public BaseMessage doText(String content, BaseMessage respBaseMessage, Map<String, String> requestMap,
							  FilterChain filterChain) {
//		try {
//			if(this.execution(content)) {
//				Map<String, Object> result = (Map<String, Object>) JSONObject.parse(HttpUtil.doGet(url
//						+ "?phone="
//						+ cellPhoneNumber
//						+ "&dtype=json&key="
//						+ key));
//				if("0".equals(result.get("error_code").toString())) {
//					Map<String, String> message = (Map<String, String>)result.get("result");
//					return MessageUtils.buildTextMessage(respBaseMessage, "归属地:" + message.get("province") + "-" + message.get("city") + MessageUtil.NEWLINE
//							+ "所属公司:" + message.get("company") + MessageUtil.NEWLINE
//							+ "区号:" + message.get("areacode") + MessageUtil.NEWLINE
//							+ "邮编:" + message.get("zip"));
//				}else {
//					return MessageUtils.buildTextMessage(respBaseMessage, "请输入正确的手机号码！");
//				}
//			}
//		} catch (Exception e) {
//			return MessageUtils.buildTextMessage(respBaseMessage, "请输入您要查询的手机号码！");
//		}
		return filterChain.doFilter(requestMap, respBaseMessage);
	}

	/**
	 * 设置要查询的手机号
	 */
	public void setCellPhoneNumber(String cellPhoneNumber) {
		this.cellPhoneNumber = cellPhoneNumber;
	}
}