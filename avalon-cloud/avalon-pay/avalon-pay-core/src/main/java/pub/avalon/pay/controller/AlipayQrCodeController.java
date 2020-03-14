package pub.avalon.pay.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeCancelRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeCancelResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pub.avalon.beans.EnumMethods;
import pub.avalon.holygrail.response.utils.DataViewUtil;
import pub.avalon.holygrail.response.utils.ExceptionUtil;
import pub.avalon.holygrail.response.views.DataView;
import pub.avalon.holygrail.response.views.ModelView;
import pub.avalon.holygrail.utils.StringUtil;
import pub.avalon.pay.api.AlipayQrCodeApi;
import pub.avalon.pay.api.PayBusinessApi;
import pub.avalon.pay.beans.*;
import pub.avalon.pay.config.AlipayConfig;
import pub.avalon.pay.entity.PayAlipayQrCodeFlow;
import pub.avalon.pay.service.PayService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author baichao
 */
@RequestMapping("${feign.pay.alipay.qr-code-api-service-path:/api-pay-alipay-qr-code}")
@RestController
public class AlipayQrCodeController implements AlipayQrCodeApi {

    @Value("${feign.pay.alipay.qr-code-api-service-path:/api-pay-alipay-qr-code}")
    private String MAPPING_PATH;

    @Autowired
    private AlipayClient alipayClient;

    @Autowired
    private PayBusinessApi payBusinessApi;

    @Autowired
    private PayService payService;

    @Override
    @RequestMapping(value = "/get/tradePreCreate")
    public DataView getTradePreCreate(AlipayTradePreCreateBizContent record, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (StringUtil.isEmpty(record.getOut_trade_no())) {
            ExceptionUtil.throwFailException("商户订单号不能为空");
        }
        if (StringUtil.isEmpty(record.getTotal_amount())) {
            ExceptionUtil.throwFailException("订单金额不能为空");
        }
        if (StringUtil.isEmpty(record.getSubject())) {
            ExceptionUtil.throwFailException("订单标题不能为空");
        }
        //创建API对应的request类
        AlipayTradePrecreateRequest alipayTradePrecreateRequest = new AlipayTradePrecreateRequest();
        //设置业务参数
        record.setTimeout_express(AlipayConfig.TIMEOUT_EXPRESS);
        alipayTradePrecreateRequest.setBizContent(record.toJsonString());
        alipayTradePrecreateRequest.setNotifyUrl(AlipayConfig.NOTIFY_SERVER_PATH + MAPPING_PATH + NOTIFY_URL);
        AlipayTradePrecreateResponse alipayTradePrecreateResponse = this.alipayClient.execute(alipayTradePrecreateRequest);
        if (alipayTradePrecreateResponse.isSuccess()) {
            Map<String, Object> results = new HashMap<>(2);
            results.put("qr_code", alipayTradePrecreateResponse.getQrCode());
            results.put("timeout_express", AlipayConfig.TIMEOUT_EXPRESS);

            // 轮询检测
            AlipayTradeQuery query = new AlipayTradeQuery();
            query.setOut_trade_no(record.getOut_trade_no());
            new ThreadPoolExecutor(1, 1,
                    0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<>(), r -> {
                Thread thread = new Thread(r, "AlipayQrCode支付状态查询");
                thread.setDaemon(true);
                return thread;
            }).submit(() -> {
                Long time = 0L;
                try {
                    while (true) {
                        Thread.sleep(AlipayConfig.TRADE_QUERY_POLLING_INTERVAL);
                        time += AlipayConfig.TRADE_QUERY_POLLING_INTERVAL;
                        ModelView modelView = (ModelView) this.getTradeQuerySuccess(query, request, response);
                        if (modelView.getResultInfo().isSuccess()) {
                            if ((Boolean) modelView.getRecords().get("success")) {
                                // 订单完成
                                this.payBusinessApi.postAlipayQrCodeSuccess(record.getOut_trade_no(), request, response);
                                break;
                            }
                        }
                        if (time >= AlipayConfig.TIME_OUT) {
                            // 订单超时
                            AlipayTradeCancel tradeCancel = new AlipayTradeCancel();
                            tradeCancel.setOut_trade_no(record.getOut_trade_no());
                            this.getTradeCancel(tradeCancel, request, response);
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });

            return DataViewUtil.getModelViewSuccess(results);
        }
        ExceptionUtil.throwFailException("获取支付宝交易二维码失败");
        return null;
    }

    @Override
    @RequestMapping(value = "/get/tradeQuerySuccess")
    public DataView getTradeQuerySuccess(AlipayTradeQuery record, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (StringUtil.isEmpty(record.getOut_trade_no()) && StringUtil.isEmpty(record.getTrade_no())) {
            ExceptionUtil.throwFailException("请输入商户订单号或支付宝交易号");
        }
        AlipayTradeQueryRequest alipayTradeQueryRequest = new AlipayTradeQueryRequest();
        alipayTradeQueryRequest.setBizContent(record.toJsonString());
        AlipayTradeQueryResponse alipayTradeQueryResponse = this.alipayClient.execute(alipayTradeQueryRequest);
        if (alipayTradeQueryResponse.isSuccess()) {
            if (EnumMethods.contains(alipayTradeQueryResponse.getTradeStatus(), new AlipayTradeStatus[]{AlipayTradeStatus.TRADE_SUCCESS, AlipayTradeStatus.TRADE_FINISHED})) {
                return DataViewUtil.getModelViewSuccess(Collections.singletonMap("success", true));
            }
        }
        return DataViewUtil.getModelViewSuccess(Collections.singletonMap("success", false));
    }

    @Override
    @RequestMapping(value = "/get/tradeCancel")
    public DataView getTradeCancel(AlipayTradeCancel record, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (StringUtil.isEmpty(record.getOut_trade_no()) && StringUtil.isEmpty(record.getTrade_no())) {
            ExceptionUtil.throwFailException("请输入商户订单号或支付宝交易号");
        }
        AlipayTradeCancelRequest alipayTradeCancelRequest = new AlipayTradeCancelRequest();
        alipayTradeCancelRequest.setBizContent(record.toJsonString());
        AlipayTradeCancelResponse alipayTradeCancelResponse = this.alipayClient.execute(alipayTradeCancelRequest);
        if (alipayTradeCancelResponse.isSuccess()) {
            return DataViewUtil.getModelViewSuccess(Collections.singletonMap("success", true));
        }
        return DataViewUtil.getModelViewSuccess(Collections.singletonMap("success", false));
    }

    @Override
    @RequestMapping(value = NOTIFY_URL)
    public void notifyUrl(AlipayNotifyQrCode record, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
                DataView dataView = this.payBusinessApi.getValidateAlipayQrCodeNotify(record, request, response);
                if (DataViewUtil.isSuccess(dataView)) {
                    if (AlipayTradeStatus.TRADE_SUCCESS.name().equals(record.getTrade_status()) || AlipayTradeStatus.TRADE_FINISHED.name().equals(record.getTrade_status())) {
                        PayAlipayQrCodeFlow flow = new PayAlipayQrCodeFlow();
                        flow.setNotifyTime(record.getNotify_time());
                        flow.setNotifyType(record.getNotify_type());
                        flow.setNotifyId(record.getNotify_id());
                        flow.setSignType(record.getSign_type());
                        flow.setSign(record.getSign());
                        flow.setTradeNo(record.getTrade_no());
                        flow.setAppId(record.getApp_id());
                        flow.setOutTradeNo(record.getOut_trade_no());
                        flow.setOutBizNo(record.getOut_biz_no());
                        flow.setBuyerId(record.getBuyer_id());
                        flow.setBuyerLogonId(record.getBuyer_logon_id());
                        flow.setSellerId(record.getSeller_id());
                        flow.setSellerEmail(record.getSeller_email());
                        flow.setTradeStatus(record.getTrade_status());
                        flow.setTotalAmount(record.getTotal_amount());
                        flow.setReceiptAmount(record.getReceipt_amount());
                        flow.setInvoiceAmount(record.getInvoice_amount());
                        flow.setBuyerPayAmount(record.getBuyer_pay_amount());
                        flow.setPointAmount(record.getPoint_amount());
                        flow.setRefundFee(record.getRefund_fee());
                        flow.setSendBackFee(record.getSend_back_fee());
                        flow.setSubject(record.getSubject());
                        flow.setBody(record.getBody());
                        flow.setGmtCreate(record.getGmt_create());
                        flow.setGmtPayment(record.getGmt_payment());
                        flow.setGmtRefund(record.getGmt_refund());
                        flow.setGmtClose(record.getGmt_close());
                        flow.setFundBillList(record.getFund_bill_list());
                        // 记录流水
                        if (AlipayConfig.ENABLED_RECORD_QR_CODE_PAY_FLOW) {
                            this.payService.postAlipayQrCodePayFlow(flow, request, response);
                        }
                        this.payBusinessApi.postPayAlipayQrCodeFlow(flow, request, response);
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
