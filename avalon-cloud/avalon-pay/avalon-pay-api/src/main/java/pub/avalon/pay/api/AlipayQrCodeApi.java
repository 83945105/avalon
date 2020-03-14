package pub.avalon.pay.api;

import feign.QueryMap;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pub.avalon.holygrail.response.views.DataView;
import pub.avalon.pay.beans.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 支付宝二维码支付接口
 *
 * @author 白超
 */
@FeignClient(name = "${feign.pay.alipay.qr-code-api-service-name:pay-service}",
        path = "${feign.pay.alipay.qr-code-api-service-path:/api-pay-alipay-qr-code}")
public interface AlipayQrCodeApi {

    String ROOT_PATH = "/pay/api-pay-alipay-qr-code";

    /**
     * 获取预下单信息
     *
     * @param record
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/tradePreCreate")
    @RequestLine("GET " + ROOT_PATH + "/get/tradePreCreate")
    DataView getTradePreCreate(@RequestParam("record") @QueryMap AlipayTradePreCreateBizContent record,
                               @RequestParam("request") HttpServletRequest request,
                               @RequestParam("response") HttpServletResponse response) throws Exception;

    /**
     * 查询交易是否成功
     *
     * @param record
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/tradeQuerySuccess")
    @RequestLine("GET " + ROOT_PATH + "/get/tradeQuerySuccess")
    DataView getTradeQuerySuccess(@RequestParam("record") @QueryMap AlipayTradeQuery record,
                                  @RequestParam("request") HttpServletRequest request,
                                  @RequestParam("response") HttpServletResponse response) throws Exception;

    /**
     * 交易撤销
     *
     * @param record
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/tradeCancel")
    @RequestLine("GET " + ROOT_PATH + "/get/tradeCancel")
    DataView getTradeCancel(@RequestParam("record") @QueryMap AlipayTradeCancel record,
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
    void notifyUrl(@RequestParam("record") @QueryMap AlipayNotifyQrCode record,
                   @RequestParam("request") HttpServletRequest request,
                   @RequestParam("response") HttpServletResponse response) throws Exception;

}
