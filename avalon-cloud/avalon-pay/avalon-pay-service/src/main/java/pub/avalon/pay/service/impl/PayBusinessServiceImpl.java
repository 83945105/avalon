package pub.avalon.pay.service.impl;

import org.springframework.stereotype.Service;
import pub.avalon.pay.beans.AlipayNotifyComputerWebsite;
import pub.avalon.pay.beans.AlipayNotifyQrCode;
import pub.avalon.pay.beans.WeChatPayNotifyQrCode;
import pub.avalon.pay.entity.PayAlipayComputerWebsiteFlow;
import pub.avalon.pay.entity.PayAlipayQrCodeFlow;
import pub.avalon.pay.entity.PayWeChatPayQrCodeFlow;
import pub.avalon.pay.service.PayBusinessService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 白超
 */
@Service
public class PayBusinessServiceImpl implements PayBusinessService {

    @Override
    public boolean getValidateAlipayComputerWebsiteNotify(AlipayNotifyComputerWebsite record, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return true;
    }

    @Override
    public boolean getValidateAlipayQrCodeNotify(AlipayNotifyQrCode record, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return true;
    }

    @Override
    public boolean getValidateWeChatPayQrCodeNotify(WeChatPayNotifyQrCode record, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return true;
    }

    @Override
    public void postAlipayComputerWebsiteSuccess(String out_trade_no, HttpServletRequest request, HttpServletResponse response) throws Exception {

    }

    @Override
    public void postAlipayQrCodeSuccess(String out_trade_no, HttpServletRequest request, HttpServletResponse response) throws Exception {

    }

    @Override
    public void postWeChatPayQrCodeSuccess(String out_trade_no, HttpServletRequest request, HttpServletResponse response) throws Exception {

    }

    @Override
    public void postPayAlipayComputerWebsiteFlow(PayAlipayComputerWebsiteFlow record, HttpServletRequest request, HttpServletResponse response) throws Exception {

    }

    @Override
    public void postPayAlipayQrCodeFlow(PayAlipayQrCodeFlow record, HttpServletRequest request, HttpServletResponse response) throws Exception {

    }

    @Override
    public void postPayWeChatPayQrCodeFlow(PayWeChatPayQrCodeFlow record, HttpServletRequest request, HttpServletResponse response) throws Exception {

    }
}
