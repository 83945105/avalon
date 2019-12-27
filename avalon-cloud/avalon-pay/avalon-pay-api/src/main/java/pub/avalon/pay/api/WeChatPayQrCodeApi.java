package pub.avalon.pay.api;

import feign.QueryMap;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pub.avalon.holygrail.response.views.DataView;
import pub.avalon.pay.beans.WeChatPayTradeCancel;
import pub.avalon.pay.beans.WeChatPayTradePreCreateBizContent;
import pub.avalon.pay.beans.WeChatPayTradeQuery;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 微信支付二维码支付接口
 *
 * @author 白超
 * @date 2018/12/11
 */
@FeignClient(name = "${feign.pay.we-chat-pay.qr-code-api-service-name:pay-service}",
        path = "${feign.pay.we-chat-pay.qr-code-api-service-path:/api-pay-weChatPay-qr-code}")
public interface WeChatPayQrCodeApi {

    String ROOT_PATH = "/pay/api-pay-weChatPay-qr-code";

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
    DataView getTradePreCreate(@RequestParam("record") @QueryMap WeChatPayTradePreCreateBizContent record,
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
    DataView getTradeQuerySuccess(@RequestParam("record") @QueryMap WeChatPayTradeQuery record,
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
    DataView getTradeCancel(@RequestParam("record") @QueryMap WeChatPayTradeCancel record,
                            @RequestParam("request") HttpServletRequest request,
                            @RequestParam("response") HttpServletResponse response) throws Exception;

    String NOTIFY_URL = "/notifyUrl";

    /**
     * 用于接收微信异步通知
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = NOTIFY_URL)
    @RequestLine("GET " + ROOT_PATH + NOTIFY_URL)
    void notifyUrl(@RequestParam("request") HttpServletRequest request,
                   @RequestParam("response") HttpServletResponse response) throws Exception;

}
