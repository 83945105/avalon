package pub.avalon.pay.controller;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pub.avalon.holygrail.response.utils.DataViewUtil;
import pub.avalon.holygrail.response.utils.ExceptionUtil;
import pub.avalon.holygrail.response.utils.ResultUtil;
import pub.avalon.holygrail.response.views.DataView;
import pub.avalon.holygrail.response.views.MessageView;
import pub.avalon.pay.api.PayBusinessApi;
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
 * @date 2018/12/1
 */
@RequestMapping("${feign.pay.alipay.pay-business-api-service-path:/api-pay-business}")
@RestController
public class PayBusinessController implements PayBusinessApi, InitializingBean {

    @Autowired(required = false)
    private PayBusinessService payBusinessService;

    @Override
    @RequestMapping(value = "/get/validateAlipayComputerWebsiteNotify")
    public DataView getValidateAlipayComputerWebsiteNotify(AlipayNotifyComputerWebsite record, HttpServletRequest request, HttpServletResponse response) throws Exception {
        boolean bl = this.payBusinessService.getValidateAlipayComputerWebsiteNotify(record, request, response);
        if (bl) {
            return DataViewUtil.getMessageViewSuccess("校验通过");
        }
        return new MessageView(0, ResultUtil.createFail("校验失败"));
    }

    @Override
    @RequestMapping(value = "/get/validateAlipayQrCodeNotify")
    public DataView getValidateAlipayQrCodeNotify(AlipayNotifyQrCode record, HttpServletRequest request, HttpServletResponse response) throws Exception {
        boolean bl = this.payBusinessService.getValidateAlipayQrCodeNotify(record, request, response);
        if (bl) {
            return DataViewUtil.getMessageViewSuccess("校验通过");
        }
        return new MessageView(0, ResultUtil.createFail("校验失败"));
    }

    @Override
    @RequestMapping(value = "/get/validateWeChatPayQrCodeNotify")
    public DataView getValidateWeChatPayQrCodeNotify(WeChatPayNotifyQrCode record, HttpServletRequest request, HttpServletResponse response) throws Exception {
        boolean bl = this.payBusinessService.getValidateWeChatPayQrCodeNotify(record, request, response);
        if (bl) {
            return DataViewUtil.getMessageViewSuccess("校验通过");
        }
        return new MessageView(0, ResultUtil.createFail("校验失败"));
    }

    @Override
    @RequestMapping(value = "/post/alipay/computerWebsiteSuccess/{out_trade_no}")
    public DataView postAlipayComputerWebsiteSuccess(@PathVariable String out_trade_no, HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.payBusinessService.postAlipayComputerWebsiteSuccess(out_trade_no, request, response);
        return DataViewUtil.getMessageViewSuccess("success");
    }

    @Override
    @RequestMapping(value = "/post/alipay/qrCodeSuccess/{out_trade_no}")
    public DataView postAlipayQrCodeSuccess(@PathVariable String out_trade_no, HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.payBusinessService.postAlipayQrCodeSuccess(out_trade_no, request, response);
        return DataViewUtil.getMessageViewSuccess("success");
    }

    @Override
    @RequestMapping(value = "/post/weChatPay/qrCodeSuccess/{out_trade_no}")
    public DataView postWeChatPayQrCodeSuccess(@PathVariable String out_trade_no, HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.payBusinessService.postWeChatPayQrCodeSuccess(out_trade_no, request, response);
        return DataViewUtil.getMessageViewSuccess("success");
    }

    @Override
    @RequestMapping(value = "/post/payAlipayComputerWebsiteFlow")
    public DataView postPayAlipayComputerWebsiteFlow(PayAlipayComputerWebsiteFlow record, HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.payBusinessService.postPayAlipayComputerWebsiteFlow(record, request, response);
        return DataViewUtil.getMessageViewSuccess("success");
    }

    @Override
    @RequestMapping(value = "/post/payAlipayQrCodeFlow")
    public DataView postPayAlipayQrCodeFlow(PayAlipayQrCodeFlow record, HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.payBusinessService.postPayAlipayQrCodeFlow(record, request, response);
        return DataViewUtil.getMessageViewSuccess("success");
    }

    @Override
    @RequestMapping(value = "/post/payWeChatPayQrCodeFlow")
    public DataView postPayWeChatPayQrCodeFlow(PayWeChatPayQrCodeFlow record, HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.payBusinessService.postPayWeChatPayQrCodeFlow(record, request, response);
        return DataViewUtil.getMessageViewSuccess("success");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (this.payBusinessService == null) {
            ExceptionUtil.throwErrorException("您尚未实现或注册PayBusinessService接口的实现,这将导致支付模块无法正常运转！！！");
        }
    }
}
