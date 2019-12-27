package pub.avalon.pay.api;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pub.avalon.holygrail.response.views.DataView;
import pub.avalon.pay.beans.AlipayNotifyComputerWebsite;
import pub.avalon.pay.beans.AlipayNotifyQrCode;
import pub.avalon.pay.beans.WeChatPayNotifyQrCode;
import pub.avalon.pay.entity.PayAlipayComputerWebsiteFlow;
import pub.avalon.pay.entity.PayAlipayQrCodeFlow;
import pub.avalon.pay.entity.PayWeChatPayQrCodeFlow;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 支付业务接口
 *
 * @author 白超
 * @date 2018/12/1
 */
@FeignClient(name = "${feign.pay.alipay.pay-business-api-service-name:pay-service}", path = "${feign.pay.alipay.pay-business-api-service-path:/api-pay-business}")
public interface PayBusinessApi {

    String ROOT_PATH = "/pay/api-pay-business";

    /**
     * 校验支付宝PC网页支付异步回调
     * 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号
     * 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额）
     * 3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
     * 4、验证app_id是否为该商户本身。上述1、2、3、4有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
     *
     * @param record
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/validateAlipayComputerWebsiteNotify")
    @RequestLine("GET " + ROOT_PATH + "/get/validateAlipayComputerWebsiteNotify")
    DataView getValidateAlipayComputerWebsiteNotify(@RequestParam("record") @QueryMap AlipayNotifyComputerWebsite record,
                                                    @RequestParam("request") HttpServletRequest request,
                                                    @RequestParam("response") HttpServletResponse response) throws Exception;

    /**
     * 校验支付宝二维码支付异步回调
     * 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号
     * 2、并判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额）
     * 3、同时需要校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
     * 4、上述有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
     *
     * @param record
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/validateAlipayQrCodeNotify")
    @RequestLine("GET " + ROOT_PATH + "/get/validateAlipayQrCodeNotify")
    DataView getValidateAlipayQrCodeNotify(@RequestParam("record") @QueryMap AlipayNotifyQrCode record,
                                           @RequestParam("request") HttpServletRequest request,
                                           @RequestParam("response") HttpServletResponse response) throws Exception;

    /**
     * 校验微信支付二维码支付异步回调
     *
     * @param record
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/validateWeChatPayQrCodeNotify")
    @RequestLine("GET " + ROOT_PATH + "/get/validateWeChatPayQrCodeNotify")
    DataView getValidateWeChatPayQrCodeNotify(@RequestParam("record") @QueryMap WeChatPayNotifyQrCode record,
                                              @RequestParam("request") HttpServletRequest request,
                                              @RequestParam("response") HttpServletResponse response) throws Exception;

    /**
     * 支付宝PC网页支付成功
     *
     * @param out_trade_no
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/post/alipay/computerWebsiteSuccess/{out_trade_no}")
    @RequestLine("POST " + ROOT_PATH + "/post/alipay/computerWebsiteSuccess/{out_trade_no}")
    DataView postAlipayComputerWebsiteSuccess(@PathVariable("out_trade_no") @Param("out_trade_no") String out_trade_no,
                                              @RequestParam("request") HttpServletRequest request,
                                              @RequestParam("response") HttpServletResponse response) throws Exception;

    /**
     * 支付宝二维码支付成功
     *
     * @param out_trade_no
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/post/alipay/qrCodeSuccess/{out_trade_no}")
    @RequestLine("POST " + ROOT_PATH + "/post/alipay/qrCodeSuccess/{out_trade_no}")
    DataView postAlipayQrCodeSuccess(@PathVariable("out_trade_no") @Param("out_trade_no") String out_trade_no,
                                     @RequestParam("request") HttpServletRequest request,
                                     @RequestParam("response") HttpServletResponse response) throws Exception;

    /**
     * 微信支付二维码支付成功
     *
     * @param out_trade_no
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/post/weChatPay/qrCodeSuccess/{out_trade_no}")
    @RequestLine("POST " + ROOT_PATH + "/post/weChatPay/qrCodeSuccess/{out_trade_no}")
    DataView postWeChatPayQrCodeSuccess(@PathVariable("out_trade_no") @Param("out_trade_no") String out_trade_no,
                                        @RequestParam("request") HttpServletRequest request,
                                        @RequestParam("response") HttpServletResponse response) throws Exception;

    /**
     * 支付宝PC网页支付流水
     *
     * @param record
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/post/payAlipayComputerWebsiteFlow")
    @RequestLine("POST " + ROOT_PATH + "/post/payAlipayComputerWebsiteFlow")
    DataView postPayAlipayComputerWebsiteFlow(@RequestParam("record") @QueryMap PayAlipayComputerWebsiteFlow record,
                                              @RequestParam("request") HttpServletRequest request,
                                              @RequestParam("response") HttpServletResponse response) throws Exception;

    /**
     * 支付宝二维码支付流水
     *
     * @param record
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/post/payAlipayQrCodeFlow")
    @RequestLine("POST " + ROOT_PATH + "/post/payAlipayQrCodeFlow")
    DataView postPayAlipayQrCodeFlow(@RequestParam("record") @QueryMap PayAlipayQrCodeFlow record,
                                     @RequestParam("request") HttpServletRequest request,
                                     @RequestParam("response") HttpServletResponse response) throws Exception;

    /**
     * 微信二维码支付流水
     *
     * @param record
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/post/payWeChatPayQrCodeFlow")
    @RequestLine("POST " + ROOT_PATH + "/post/payWeChatPayQrCodeFlow")
    DataView postPayWeChatPayQrCodeFlow(@RequestParam("record") @QueryMap PayWeChatPayQrCodeFlow record,
                                        @RequestParam("request") HttpServletRequest request,
                                        @RequestParam("response") HttpServletResponse response) throws Exception;

}
