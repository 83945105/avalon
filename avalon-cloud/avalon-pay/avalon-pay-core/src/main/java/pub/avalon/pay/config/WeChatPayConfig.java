package pub.avalon.pay.config;

import com.github.wxpay.sdk.IWXPayDomain;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pub.avalon.beans.Time;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 白超
 */
@Configuration
public class WeChatPayConfig {

    @Bean
    public WXPay wxPay() throws Exception {
        return new WXPay(new WXPayConfigImpl(APP_ID, MCH_ID, KEY, new FileInputStream(new File(CERT_PATH)), new IWXPayDomain() {
            @Override
            public void report(String s, long l, Exception e) {

            }

            @Override
            public DomainInfo getDomain(WXPayConfig wxPayConfig) {
                return new IWXPayDomain.DomainInfo(WXPayConstants.DOMAIN_API, true);
            }
        }) {
        });
    }

    public static String APP_ID;
    public static String MCH_ID;
    public static String KEY;
    public static String CERT_PATH;
    /**
     * 超时时间
     */
    public static Long TIME_OUT;
    /**
     * 交易查询轮询间隔
     */
    public static Long TRADE_QUERY_POLLING_INTERVAL;
    /**
     * 微信支付服务器主动通知商户服务器地址
     */
    public static String NOTIFY_SERVER_PATH;
    public static String SPBILL_CREATE_IP;
    /**
     * 是否记录二维码支付流水
     */
    public static boolean ENABLED_RECORD_QR_CODE_PAY_FLOW;

    @Value("${weChatPay.app_id}")
    public void setAppId(String appId) {
        APP_ID = appId;
    }

    @Value("${weChatPay.mch_id}")
    public void setMchId(String mchId) {
        MCH_ID = mchId;
    }

    @Value("${weChatPay.key}")
    public void setKEY(String key) {
        KEY = key;
    }

    @Value("${weChatPay.cert_path}")
    public void setCertPath(String certPath) {
        CERT_PATH = certPath;
    }

    @Value("${weChatPay.time_out}")
    public void setTimeOut(Long timeOut) {
        int minTimeout = 60000;
        if (timeOut < minTimeout) {
            throw new RuntimeException("支付超时时间不能小于60000毫秒");
        }
        TIME_OUT = timeOut;
    }

    public static final SimpleDateFormat TIME_OUT_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * 获取交易结束时间
     *
     * @return
     */
    public static String getTimeExpire() {
        return TIME_OUT_FORMAT.format(new Date(Time.timeStamp() + TIME_OUT));
    }

    @Value("${weChatPay.trade_query_polling_interval}")
    public void setTradeQueryPollingInterval(Long tradeQueryPollingInterval) {
        TRADE_QUERY_POLLING_INTERVAL = tradeQueryPollingInterval;
    }

    @Value("${weChatPay.notify_server_path}")
    public void setNotifyServerPath(String notifyServerPath) {
        NOTIFY_SERVER_PATH = notifyServerPath;
    }

    @Value("${weChatPay.spbill_create_ip}")
    public void setSpbillCreateIp(String spbillCreateIp) {
        SPBILL_CREATE_IP = spbillCreateIp;
    }

    @Value("${weChatPay.enabled_record_qr_code_pay_flow}")
    public void setEnabledRecordQrCodePayFlow(boolean enabledRecordQrCodePayFlow) {
        ENABLED_RECORD_QR_CODE_PAY_FLOW = enabledRecordQrCodePayFlow;
    }

    public class WXPayConfigImpl extends WXPayConfig {

        private String appId;
        private String mchId;
        private String key;
        private InputStream certStream;
        private IWXPayDomain iwxPayDomain;

        public WXPayConfigImpl(String appId, String mchId, String key, InputStream certStream, IWXPayDomain iwxPayDomain) {
            this.appId = appId;
            this.mchId = mchId;
            this.key = key;
            this.certStream = certStream;
            this.iwxPayDomain = iwxPayDomain;
        }

        @Override
        public String getAppID() {
            return appId;
        }

        @Override
        public String getMchID() {
            return mchId;
        }

        @Override
        public String getKey() {
            return key;
        }

        @Override
        public InputStream getCertStream() {
            return certStream;
        }

        @Override
        public IWXPayDomain getWXPayDomain() {
            return iwxPayDomain;
        }

    }

}
