package pub.avalon.pay.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pub.avalon.holygrail.response.utils.DataViewUtil;
import pub.avalon.holygrail.response.utils.ExceptionUtil;
import pub.avalon.holygrail.response.views.DataView;
import pub.avalon.holygrail.utils.StringUtil;
import pub.avalon.pay.api.AlipayComputerWebsiteApi;
import pub.avalon.pay.api.PayBusinessApi;
import pub.avalon.pay.beans.AlipayNotifyComputerWebsite;
import pub.avalon.pay.beans.AlipayTradePagePayBizContent;
import pub.avalon.pay.beans.AlipayTradeStatus;
import pub.avalon.pay.config.AlipayConfig;
import pub.avalon.pay.entity.PayAlipayComputerWebsiteFlow;
import pub.avalon.pay.service.PayService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 白超
 */
@RequestMapping("${feign.pay.alipay.computer-website-api-service-path:/api-pay-alipay-computer-website}")
@RestController
public class AlipayComputerWebsiteController implements AlipayComputerWebsiteApi {

    @Value("${feign.pay.alipay.computer-website-api-service-path:/api-pay-alipay-computer-website}")
    private String MAPPING_PATH;

    @Autowired
    private AlipayClient alipayClient;

    @Autowired
    private PayBusinessApi payBusinessApi;

    @Autowired
    private PayService payService;

    @Override
    @RequestMapping(value = "/get/tradePagePayFormString")
    public DataView getTradePagePayFormString(AlipayTradePagePayBizContent record, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //创建API对应的request
        AlipayTradePagePayRequest alipayTradePagePayRequest = new AlipayTradePagePayRequest();
        //在公共参数中设置回跳和通知地址
        alipayTradePagePayRequest.setReturnUrl(AlipayConfig.RETURN_URL);
        alipayTradePagePayRequest.setNotifyUrl(AlipayConfig.NOTIFY_SERVER_PATH + MAPPING_PATH + NOTIFY_URL);
        //填充业务参数
        alipayTradePagePayRequest.setBizContent(record.toJsonString());
        //调用SDK生成表单
        String form = alipayClient.pageExecute(alipayTradePagePayRequest).getBody();
        if (StringUtil.isEmpty(form)) {
            ExceptionUtil.throwErrorException("获取支付宝Form表单失败");
        }
        return DataViewUtil.getModelViewSuccess(Collections.singletonMap("form", form));
    }

    @Override
    @RequestMapping(value = NOTIFY_URL)
    public void notifyUrl(AlipayNotifyComputerWebsite record, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //将异步通知中收到的所有参数都存放到map中
        Map<String, String> paramsMap = new HashMap<>(6);
        Map<?, ?> requestParams = request.getParameterMap();
        for (Object o : requestParams.keySet()) {
            String name = (String) o;
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            paramsMap.put(name, valueStr);
        }
        //调用SDK验证签名
        try {
            boolean signVerified = AlipaySignature.rsaCheckV1(paramsMap, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.SIGN_TYPE);
            //验签成功则继续业务操作，最后在response中返回success
            if (signVerified) {
                //进行业务校验
                DataView dataView = this.payBusinessApi.getValidateAlipayComputerWebsiteNotify(record, request, response);
                if (DataViewUtil.isSuccess(dataView)) {
                    if (AlipayTradeStatus.TRADE_SUCCESS.name().equals(record.getTrade_status()) || AlipayTradeStatus.TRADE_FINISHED.name().equals(record.getTrade_status())) {
                        PayAlipayComputerWebsiteFlow flow = new PayAlipayComputerWebsiteFlow();
                        flow.setNotifyTime(record.getNotify_time());
                        flow.setNotifyType(record.getNotify_type());
                        flow.setNotifyId(record.getNotify_id());
                        flow.setCharset(record.getCharset());
                        flow.setVersion(record.getVersion());
                        flow.setSignType(record.getSign_type());
                        flow.setSign(record.getSign());
                        flow.setAuthAppId(record.getAuth_app_id());
                        flow.setTradeNo(record.getTrade_no());
                        flow.setAppId(record.getApp_id());
                        flow.setOutTradeNo(record.getOut_trade_no());
                        flow.setOutBizNo(record.getOut_biz_no());
                        flow.setBuyerId(record.getBuyer_id());
                        flow.setSellerId(record.getSeller_id());
                        flow.setTradeStatus(record.getTrade_status());
                        flow.setTotalAmount(record.getTotal_amount());
                        flow.setReceiptAmount(record.getReceipt_amount());
                        flow.setInvoiceAmount(record.getInvoice_amount());
                        flow.setBuyerPayAmount(record.getBuyer_pay_amount());
                        flow.setPointAmount(record.getPoint_amount());
                        flow.setRefundFee(record.getRefund_fee());
                        flow.setSubject(record.getSubject());
                        flow.setBody(record.getBody());
                        flow.setGmtCreate(record.getGmt_create());
                        flow.setGmtPayment(record.getGmt_payment());
                        flow.setGmtRefund(record.getGmt_refund());
                        flow.setGmtClose(record.getGmt_close());
                        flow.setFundBillList(record.getFund_bill_list());
                        flow.setVoucherDetailList(record.getVoucher_detail_list());
                        flow.setPassbackParams(record.getPassback_params());
                        // 记录流水
                        if (AlipayConfig.ENABLED_RECORD_COMPUTER_WEBSITE_PAY_FLOW) {
                            this.payService.postAlipayComputerWebsitePayFlow(flow, request, response);
                        }
                        this.payBusinessApi.postPayAlipayComputerWebsiteFlow(flow, request, response);
                        response.getWriter().print("success");
                        return;
                    }
                }
            }
        } catch (AlipayApiException | IOException e) {
            e.printStackTrace();
        }
        //验签失败则记录异常日志，并在response中返回failure.
        response.getWriter().print("failure");
    }
}
