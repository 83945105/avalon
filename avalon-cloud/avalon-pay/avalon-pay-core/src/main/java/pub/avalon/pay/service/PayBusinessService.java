package pub.avalon.pay.service;

import pub.avalon.pay.beans.AlipayNotifyComputerWebsite;
import pub.avalon.pay.beans.AlipayNotifyQrCode;
import pub.avalon.pay.beans.WeChatPayNotifyQrCode;
import pub.avalon.pay.entity.PayAlipayComputerWebsiteFlow;
import pub.avalon.pay.entity.PayAlipayQrCodeFlow;
import pub.avalon.pay.entity.PayWeChatPayQrCodeFlow;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 白超
 */
public interface PayBusinessService {

    /**
     * 校验支付宝PC网页支付通知
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
    boolean getValidateAlipayComputerWebsiteNotify(AlipayNotifyComputerWebsite record, HttpServletRequest request, HttpServletResponse response) throws Exception;

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
    boolean getValidateAlipayQrCodeNotify(AlipayNotifyQrCode record, HttpServletRequest request, HttpServletResponse response) throws Exception;

    /**
     * 校验微信支付二维码支付异步回调
     *
     * @param record
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    boolean getValidateWeChatPayQrCodeNotify(WeChatPayNotifyQrCode record, HttpServletRequest request, HttpServletResponse response) throws Exception;

    /**
     * 支付宝PC网页支付成功
     *
     * @param out_trade_no
     * @param request
     * @param response
     * @throws Exception
     */
    void postAlipayComputerWebsiteSuccess(String out_trade_no, HttpServletRequest request, HttpServletResponse response) throws Exception;

    /**
     * 支付宝二维码支付成功
     *
     * @param out_trade_no
     * @param request
     * @param response
     * @throws Exception
     */
    void postAlipayQrCodeSuccess(String out_trade_no, HttpServletRequest request, HttpServletResponse response) throws Exception;

    /**
     * 微信支付二维码支付成功
     *
     * @param out_trade_no
     * @param request
     * @param response
     * @throws Exception
     */
    void postWeChatPayQrCodeSuccess(String out_trade_no, HttpServletRequest request, HttpServletResponse response) throws Exception;

    /**
     * 记录支付宝PC网页支付流水
     *
     * @param record
     * @param request
     * @param response
     * @throws Exception
     */
    void postPayAlipayComputerWebsiteFlow(PayAlipayComputerWebsiteFlow record, HttpServletRequest request, HttpServletResponse response) throws Exception;

    /**
     * 记录支付宝二维码支付流水
     *
     * @param record
     * @param request
     * @param response
     * @throws Exception
     */
    void postPayAlipayQrCodeFlow(PayAlipayQrCodeFlow record, HttpServletRequest request, HttpServletResponse response) throws Exception;

    /**
     * 记录微信二维码支付流水
     *
     * @param record
     * @param request
     * @param response
     * @throws Exception
     */
    void postPayWeChatPayQrCodeFlow(PayWeChatPayQrCodeFlow record, HttpServletRequest request, HttpServletResponse response) throws Exception;

}
