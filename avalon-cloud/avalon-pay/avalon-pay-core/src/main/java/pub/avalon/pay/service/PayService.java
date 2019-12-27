package pub.avalon.pay.service;

import pub.avalon.pay.entity.PayAlipayComputerWebsiteFlow;
import pub.avalon.pay.entity.PayAlipayQrCodeFlow;
import pub.avalon.pay.entity.PayWeChatPayQrCodeFlow;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 支付
 *
 * @author 白超
 * @date 2018/12/2
 */
public interface PayService {

    /**
     * 记录支付宝PC网页支付流水
     *
     * @param record
     * @param request
     * @param response
     * @throws Exception
     */
    void postAlipayComputerWebsitePayFlow(PayAlipayComputerWebsiteFlow record, HttpServletRequest request, HttpServletResponse response) throws Exception;

    /**
     * 记录支付宝二维码支付流水
     *
     * @param record
     * @param request
     * @param response
     * @throws Exception
     */
    void postAlipayQrCodePayFlow(PayAlipayQrCodeFlow record, HttpServletRequest request, HttpServletResponse response) throws Exception;

    /**
     * 记录微信二维码支付流水
     *
     * @param record
     * @param request
     * @param response
     * @throws Exception
     */
    void postWeChatPayQrCodePayFlow(PayWeChatPayQrCodeFlow record, HttpServletRequest request, HttpServletResponse response) throws Exception;

}
