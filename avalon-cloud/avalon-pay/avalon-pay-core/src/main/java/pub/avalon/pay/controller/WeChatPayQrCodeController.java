package pub.avalon.pay.controller;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
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
import pub.avalon.pay.api.PayBusinessApi;
import pub.avalon.pay.api.WeChatPayQrCodeApi;
import pub.avalon.pay.beans.*;
import pub.avalon.pay.config.WeChatPayConfig;
import pub.avalon.pay.entity.PayWeChatPayQrCodeFlow;
import pub.avalon.pay.service.PayService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author baichao
 * @date 2019/1/7
 */
@RequestMapping(value = "${feign.pay.we-chat-pay.qr-code-api-service-path:/api-pay-weChatPay-qr-code}")
@RestController
public class WeChatPayQrCodeController implements WeChatPayQrCodeApi {

    @Value("${feign.pay.we-chat-pay.qr-code-api-service-path:/api-pay-weChatPay-qr-code}")
    private String MAPPING_PATH;

    @Autowired
    private WXPay wxPay;

    @Autowired
    private PayBusinessApi payBusinessApi;

    @Autowired
    private PayService payService;

    @Override
    @RequestMapping(value = "/get/tradePreCreate")
    public DataView getTradePreCreate(WeChatPayTradePreCreateBizContent record, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (StringUtil.isEmpty(record.getOut_trade_no())) {
            ExceptionUtil.throwFailException("商户订单号不能为空");
        }
        if (StringUtil.isEmpty(record.getBody())) {
            ExceptionUtil.throwFailException("商品描述不能为空");
        }
        if (StringUtil.isEmpty(record.getTotal_fee())) {
            ExceptionUtil.throwFailException("订单金额不能为空");
        }

        Map<String, String> data = new HashMap<>(16);
        data.put("body", record.getBody());
        data.put("out_trade_no", record.getOut_trade_no());
        data.put("total_fee", record.getTotal_fee());
        data.put("spbill_create_ip", WeChatPayConfig.SPBILL_CREATE_IP);
        data.put("notify_url", WeChatPayConfig.NOTIFY_SERVER_PATH + MAPPING_PATH + NOTIFY_URL);
        data.put("trade_type", "NATIVE");
        data.put("product_id", record.getOut_trade_no());
        data.put("time_expire", WeChatPayConfig.getTimeExpire());

        Map<String, String> resp = wxPay.unifiedOrder(data);
        if ("SUCCESS".equals(resp.get("return_code"))) {
            Map<String, Object> results = new HashMap<>(2);
            results.put("qr_code", resp.get("code_url"));
            results.put("timeout_express", WeChatPayConfig.TIME_OUT);

            // 轮询检测
            WeChatPayTradeQuery query = new WeChatPayTradeQuery();
            query.setOut_trade_no(record.getOut_trade_no());
            new ThreadPoolExecutor(1, 1,
                    0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<>(), r -> {
                Thread thread = new Thread(r, "WeChatPayQrCode支付状态查询");
                thread.setDaemon(true);
                return thread;
            }).submit(() -> {
                Long time = 0L;
                try {
                    while (true) {
                        Thread.sleep(WeChatPayConfig.TRADE_QUERY_POLLING_INTERVAL);
                        time += WeChatPayConfig.TRADE_QUERY_POLLING_INTERVAL;
                        ModelView modelView = (ModelView) this.getTradeQuerySuccess(query, request, response);
                        if (modelView.getResultInfo().isSuccess()) {
                            if ((Boolean) modelView.getRecords().get("success")) {
                                // 订单完成
                                this.payBusinessApi.postWeChatPayQrCodeSuccess(record.getOut_trade_no(), request, response);
                                break;
                            }
                        }
                        if (time >= WeChatPayConfig.TIME_OUT) {
                            // 订单超时
                            WeChatPayTradeCancel tradeCancel = new WeChatPayTradeCancel();
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
        ExceptionUtil.throwFailException("获取微信交易二维码失败");
        return null;
    }

    @Override
    @RequestMapping(value = "/get/tradeQuerySuccess")
    public DataView getTradeQuerySuccess(WeChatPayTradeQuery record, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (StringUtil.isEmpty(record.getOut_trade_no()) && StringUtil.isEmpty(record.getTransaction_id())) {
            ExceptionUtil.throwFailException("请输入商户订单号或微信订单号");
        }
        Map<String, String> data = new HashMap<>(1);
        if (!StringUtil.isEmpty(record.getOut_trade_no())) {
            data.put("out_trade_no", record.getOut_trade_no());
        }
        if (!StringUtil.isEmpty(record.getTransaction_id())) {
            data.put("transaction_id", record.getTransaction_id());
        }

        Map<String, String> resp = wxPay.orderQuery(data);
        if ("SUCCESS".equals(resp.get("return_code"))) {
            if (EnumMethods.equalsTo(resp.get("trade_state"), WeChatPayTradeStatus.SUCCESS)) {
                return DataViewUtil.getModelViewSuccess(Collections.singletonMap("success", true));
            }
        }
        return DataViewUtil.getModelViewSuccess(Collections.singletonMap("success", false));
    }

    @Override
    @RequestMapping(value = "/get/tradeCancel")
    public DataView getTradeCancel(WeChatPayTradeCancel record, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (StringUtil.isEmpty(record.getOut_trade_no())) {
            ExceptionUtil.throwFailException("请输入商户订单号");
        }
        Map<String, String> data = new HashMap<>(1);
        data.put("out_trade_no", record.getOut_trade_no());

        Map<String, String> resp = wxPay.refundQuery(data);
        if ("SUCCESS".equals(resp.get("return_code"))) {
            return DataViewUtil.getModelViewSuccess(Collections.singletonMap("success", true));
        }
        return DataViewUtil.getModelViewSuccess(Collections.singletonMap("success", false));
    }

    @Override
    @RequestMapping(value = NOTIFY_URL)
    public void notifyUrl(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            InputStream inStream = request.getInputStream();
            int bufferSize = 1024;
            if (inStream != null) {
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                byte[] tempBytes = new byte[bufferSize];
                int count;
                while ((count = inStream.read(tempBytes, 0, bufferSize)) != -1) {
                    outStream.write(tempBytes, 0, count);
                }
                outStream.flush();
                //将流转换成字符串
                String result = new String(outStream.toByteArray(), StandardCharsets.UTF_8);
                //将字符串解析成XML
                Document doc = DocumentHelper.parseText(result);

                Map<String, String> notifyMap = WXPayUtil.xmlToMap(doc.asXML());

                if (wxPay.isPayResultNotifySignatureValid(notifyMap)) {
                    // 签名正确
                    // 进行处理。
                    // 注意特殊情况：订单已经退款，但收到了支付结果成功的通知，不应把商户侧订单状态从退款改成支付成功
                    //进行业务校验
                    WeChatPayNotifyQrCode weChatPayNotifyQrCode = new WeChatPayNotifyQrCode(notifyMap);
                    DataView dataView = this.payBusinessApi.getValidateWeChatPayQrCodeNotify(weChatPayNotifyQrCode, request, response);
                    if (DataViewUtil.isSuccess(dataView)) {
                        if (WeChatPayTradeStatus.SUCCESS.name().equals(weChatPayNotifyQrCode.getResult_code())) {
                            PayWeChatPayQrCodeFlow flow = new PayWeChatPayQrCodeFlow();
                            flow.setReturnCode(weChatPayNotifyQrCode.getReturn_code());
                            flow.setReturnMsg(weChatPayNotifyQrCode.getReturn_msg());
                            flow.setAppid(weChatPayNotifyQrCode.getAppid());
                            flow.setMchId(weChatPayNotifyQrCode.getMch_id());
                            flow.setDeviceInfo(weChatPayNotifyQrCode.getDevice_info());
                            flow.setNonceStr(weChatPayNotifyQrCode.getNonce_str());
                            flow.setSign(weChatPayNotifyQrCode.getSign());
                            flow.setSignType(weChatPayNotifyQrCode.getSign_type());
                            flow.setResultCode(weChatPayNotifyQrCode.getResult_code());
                            flow.setErrCode(weChatPayNotifyQrCode.getErr_code());
                            flow.setErrCodeDes(weChatPayNotifyQrCode.getErr_code_des());
                            flow.setOpenid(weChatPayNotifyQrCode.getOpenid());
                            flow.setIsSubscribe(weChatPayNotifyQrCode.getIs_subscribe());
                            flow.setTradeType(weChatPayNotifyQrCode.getTrade_type());
                            flow.setBankType(weChatPayNotifyQrCode.getBank_type());
                            flow.setTotalFee(weChatPayNotifyQrCode.getTotal_fee());
                            flow.setSettlementTotalFee(weChatPayNotifyQrCode.getSettlement_total_fee());
                            flow.setFeeType(weChatPayNotifyQrCode.getFee_type());
                            flow.setCashFee(weChatPayNotifyQrCode.getCash_fee());
                            flow.setCashFeeType(weChatPayNotifyQrCode.getCash_fee_type());
                            flow.setCouponFee(weChatPayNotifyQrCode.getCoupon_fee());
                            flow.setCouponCount(weChatPayNotifyQrCode.getCoupon_count());
                            flow.setCouponTypeN(weChatPayNotifyQrCode.getCoupon_type_$n());
                            flow.setCouponIdN(weChatPayNotifyQrCode.getCoupon_id_$n());
                            flow.setCouponFeeN(weChatPayNotifyQrCode.getCoupon_fee_$n());
                            flow.setTransactionId(weChatPayNotifyQrCode.getTransaction_id());
                            flow.setOutTradeNo(weChatPayNotifyQrCode.getOut_trade_no());
                            flow.setAttach(weChatPayNotifyQrCode.getAttach());
                            flow.setTimeEnd(weChatPayNotifyQrCode.getTime_end());
                            // 记录流水
                            if (WeChatPayConfig.ENABLED_RECORD_QR_CODE_PAY_FLOW) {
                                this.payService.postWeChatPayQrCodePayFlow(flow, request, response);
                            }
                            this.payBusinessApi.postPayWeChatPayQrCodeFlow(flow, request, response);
                            response.getWriter().print("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
                            return;
                        }
                    }
                    //通知微信支付系统接收到信息
                    response.getWriter().print("fail");
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //如果失败返回错误，微信会再次发送支付信息
        response.getWriter().print("fail");
    }
}
