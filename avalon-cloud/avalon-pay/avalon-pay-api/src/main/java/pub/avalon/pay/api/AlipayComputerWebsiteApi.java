package pub.avalon.pay.api;

import feign.QueryMap;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pub.avalon.holygrail.response.views.DataView;
import pub.avalon.pay.beans.AlipayTradePagePayBizContent;
import pub.avalon.pay.beans.AlipayNotifyComputerWebsite;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 支付宝电脑网站接口
 *
 * @author 白超
 * @date 2018/12/1
 */
@FeignClient(name = "${feign.pay.alipay.computer-website-api-service-name:pay-service}",
        path = "${feign.pay.alipay.computer-website-api-service-path:/api-pay-alipay-computer-website}")
public interface AlipayComputerWebsiteApi {

    String ROOT_PATH = "/pay/api-pay-alipay-computer-website";

    /**
     * 获取交易表单字符串
     *
     * @param record
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/tradePagePayFormString")
    @RequestLine("GET " + ROOT_PATH + "/get/tradePagePayFormString")
    DataView getTradePagePayFormString(@RequestParam("record") @QueryMap AlipayTradePagePayBizContent record,
                                       @RequestParam("request") HttpServletRequest request,
                                       @RequestParam("response") HttpServletResponse response) throws Exception;

    String NOTIFY_URL = "/notifyUrl";

    /**
     * 用于接收支付宝异步通知
     *
     * @param record
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = NOTIFY_URL)
    @RequestLine("GET " + ROOT_PATH + NOTIFY_URL)
    void notifyUrl(@RequestParam("record") @QueryMap AlipayNotifyComputerWebsite record,
                   @RequestParam("request") HttpServletRequest request,
                   @RequestParam("response") HttpServletResponse response) throws Exception;

}
