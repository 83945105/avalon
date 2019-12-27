package pub.avalon.pay.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 白超
 * @date 2018/8/30
 */
@Configuration
public class AlipayConfig {

    @Bean
    public AlipayClient alipayClient() {
        return new DefaultAlipayClient(AlipayConfig.URL,
                AlipayConfig.APP_ID,
                AlipayConfig.APP_PRIVATE_KEY,
                AlipayConfig.FORMAT,
                AlipayConfig.CHARSET,
                AlipayConfig.ALIPAY_PUBLIC_KEY,
                AlipayConfig.SIGN_TYPE);
    }

    /**
     * 支付宝支付成功码
     */
    public static final int ALIPAY_SUCCESS_CODE = 10000;
    /**
     * 支付宝支付失败码
     */
    public static final int ALIPAY_FAIL_CODE = 40004;
    /**
     * 支付宝等待用户付款码
     */
    public static final int ALIPAY_WAIT_USER_PAY_CODE = 10003;
    /**
     * 支付宝未知异常码
     */
    public static final int ALIPAY_UNKNOWN_EXCEPTION_CODE = 20000;


    /**
     * 支付宝网关（固定）
     */
    public static String URL;
    /**
     * APPID即创建应用后生成
     */
    public static String APP_ID;
    /**
     * 开发者应用私钥，由开发者自己生成
     */
    public static String APP_PRIVATE_KEY;
    /**
     * 参数返回格式，只支持json
     */
    public static String FORMAT;
    /**
     * 请求和签名使用的字符编码格式，支持GBK和UTF-8
     */
    public static String CHARSET;
    /**
     * 支付宝公钥，由支付宝生成
     */
    public static String ALIPAY_PUBLIC_KEY;
    /**
     * 商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2
     */
    public static String SIGN_TYPE;
    /**
     * 商户UID
     */
    public static String SELLER_UID;
    /**
     * 超时时间
     */
    public static Long TIME_OUT;
    /**
     * 交易查询轮询间隔
     */
    public static Long TRADE_QUERY_POLLING_INTERVAL;
    /**
     * 订单超时时间
     */
    public static String TIMEOUT_EXPRESS;
    /**
     * 二维码超时时间
     */
    public static String QR_CODE_TIMEOUT_EXPRESS;

    /**
     * 同步返回地址，HTTP/HTTPS开头字符串
     */
    public static String RETURN_URL;

    /**
     * 支付宝服务器主动通知商户服务器地址
     */
    public static String NOTIFY_SERVER_PATH;

    /**
     * 是否记录PC网页支付流水
     */
    public static boolean ENABLED_RECORD_COMPUTER_WEBSITE_PAY_FLOW;

    /**
     * 是否记录二维码支付流水
     */
    public static boolean ENABLED_RECORD_QR_CODE_PAY_FLOW;

    @Value("${alipay.url}")
    public void setURL(String url) {
        URL = url;
    }

    @Value("${alipay.app_id}")
    public void setAppId(String appId) {
        APP_ID = appId;
    }

    @Value("${alipay.app_private_key}")
    public void setAppPrivateKey(String appPrivateKey) {
        APP_PRIVATE_KEY = appPrivateKey;
    }

    @Value("${alipay.format}")
    public void setFORMAT(String format) {
        FORMAT = format;
    }

    @Value("${alipay.charset}")
    public void setCHARSET(String charset) {
        CHARSET = charset;
    }

    @Value("${alipay.alipay_public_key}")
    public void setAlipayPublicKey(String alipayPublicKey) {
        ALIPAY_PUBLIC_KEY = alipayPublicKey;
    }

    @Value("${alipay.sign_type}")
    public void setSignType(String signType) {
        SIGN_TYPE = signType;
    }

    @Value("${alipay.seller_uid}")
    public void setSellerUid(String sellerUid) {
        SELLER_UID = sellerUid;
    }

    @Value("${alipay.time_out}")
    public void setTimeOut(Long timeOut) {
        int minTimeout = 60000;
        if (timeOut < minTimeout) {
            throw new RuntimeException("支付超时时间不能小于60000毫秒");
        }
        TIME_OUT = timeOut;
        String timeoutStr = timeOut / 60000 + "m";
        TIMEOUT_EXPRESS = timeoutStr;
        QR_CODE_TIMEOUT_EXPRESS = timeoutStr;
    }

    @Value("${alipay.trade_query_polling_interval}")
    public void setTradeQueryPollingInterval(Long tradeQueryPollingInterval) {
        TRADE_QUERY_POLLING_INTERVAL = tradeQueryPollingInterval;
    }

    @Value("${alipay.return_url}")
    public void setReturnUrl(String returnUrl) {
        RETURN_URL = returnUrl;
    }

    @Value("${alipay.notify_server_path}")
    public void setNotifyServerPath(String notifyServerPath) {
        NOTIFY_SERVER_PATH = notifyServerPath;
    }

    @Value("${alipay.enabled_record_computer_website_pay_flow}")
    public void setEnabledRecordComputerWebsitePayFlow(boolean enabledRecordComputerWebsitePayFlow) {
        ENABLED_RECORD_COMPUTER_WEBSITE_PAY_FLOW = enabledRecordComputerWebsitePayFlow;
    }

    @Value("${alipay.enabled_record_qr_code_pay_flow}")
    public void setEnabledRecordQrCodePayFlow(boolean enabledRecordQrCodePayFlow) {
        ENABLED_RECORD_QR_CODE_PAY_FLOW = enabledRecordQrCodePayFlow;
    }

}
