package pub.avalon.pay.beans;

import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;

/**
 * 支付宝支付内容
 *
 * @author 白超
 */
public class AlipayTradePreCreateBizContent {

    /**
     * 商户订单号，64个字符以内、可包含字母、数字、下划线；需保证在商户端不重复
     */
    private String out_trade_no;
    /**
     * 卖家支付宝用户ID。 如果该值为空，则默认为商户签约账号对应的支付宝用户ID
     */
    private String seller_id;
    /**
     * 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
     */
    private String total_amount;
    /**
     * 可打折金额. 参与优惠计算的金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000] 如果该值未传入，但传入了【订单总金额】，【不可打折金额】则该值默认为【订单总金额】-【不可打折金额】
     */
    private String discountable_amount;
    /**
     * 订单标题
     */
    private String subject;

    public AlipayTradePreCreateBizContent() {
    }

    public AlipayTradePreCreateBizContent(String out_trade_no, String total_amount, String subject) {
        this.out_trade_no = out_trade_no;
        this.total_amount = total_amount;
        this.subject = subject;
    }

    /**
     * 订单包含的商品列表信息，Json格式：
     * {&quot;show_url&quot;:&quot;https://或http://打头的商品的展示地址&quot;}
     * 在支付时，可点击商品名称跳转到该地址
     */
    private String goods_detail;
    /**
     * 订单描述
     */
    private String body;
    /**
     * 商户操作员编号
     */
    private String operator_id;
    /**
     * 商户门店编号
     */
    private String store_id;
    /**
     * 禁用渠道，用户不可用指定渠道支付
     * 当有多个渠道时用&ldquo;,&rdquo;分隔
     * 注：与enable_pay_channels互斥
     */
    private String disable_pay_channels;
    /**
     * 可用渠道，用户只能在指定渠道范围内支付
     * 当有多个渠道时用&ldquo;,&rdquo;分隔
     * 注：与disable_pay_channels互斥
     */
    private String enable_pay_channels;
    /**
     * 商户机具终端编号
     */
    private String terminal_id;
    /**
     * 业务扩展参数
     */
    private String extend_params;
    /**
     * 该笔订单允许的最晚付款时间，逾期将关闭交易。
     * 取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。
     * 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
     * 该参数在请求到支付宝时开始计时。
     */
    private String timeout_express;
    /**
     * 商户原始订单号，最大长度限制32位
     */
    private String merchant_order_no;
    /**
     * 商户传入业务信息，具体值要和支付宝约定，应用于安全，营销等参数直传场景，格式为json格式
     */
    private String business_params;
    /**
     * 该笔订单允许的最晚付款时间，逾期将关闭交易，从生成二维码开始计时。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
     */
    private String qr_code_timeout_express;

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getDiscountable_amount() {
        return discountable_amount;
    }

    public void setDiscountable_amount(String discountable_amount) {
        this.discountable_amount = discountable_amount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGoods_detail() {
        return goods_detail;
    }

    public void setGoods_detail(String goods_detail) {
        this.goods_detail = goods_detail;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOperator_id() {
        return operator_id;
    }

    public void setOperator_id(String operator_id) {
        this.operator_id = operator_id;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getTerminal_id() {
        return terminal_id;
    }

    public void setTerminal_id(String terminal_id) {
        this.terminal_id = terminal_id;
    }

    public String getExtend_params() {
        return extend_params;
    }

    public String getMerchant_order_no() {
        return merchant_order_no;
    }

    public void setMerchant_order_no(String merchant_order_no) {
        this.merchant_order_no = merchant_order_no;
    }

    public String getBusiness_params() {
        return business_params;
    }

    public void setBusiness_params(String business_params) {
        this.business_params = business_params;
    }

    public String getQr_code_timeout_express() {
        return qr_code_timeout_express;
    }

    public void setQr_code_timeout_express(String qr_code_timeout_express) {
        this.qr_code_timeout_express = qr_code_timeout_express;
    }

    public void setExtend_params(String extend_params) {
        this.extend_params = extend_params;
    }

    public void setExtend_params(ExtendParams extend_params) {
        this.extend_params = extend_params.toJsonString();
    }

    public String getTimeout_express() {
        return timeout_express;
    }

    public void setTimeout_express(String timeout_express) {
        this.timeout_express = timeout_express;
    }

    public String getEnable_pay_channels() {
        return enable_pay_channels;
    }

    public void setEnable_pay_channels(String enable_pay_channels) {
        this.enable_pay_channels = enable_pay_channels;
    }

    public void setEnable_pay_channels(PayChannel[] enable_pay_channels) {
        if (enable_pay_channels == null || enable_pay_channels.length == 0) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        Arrays.asList(enable_pay_channels).forEach(payChannel -> sb.append(",").append(payChannel));
        this.enable_pay_channels = sb.substring(1);
    }

    public String getDisable_pay_channels() {
        return disable_pay_channels;
    }

    public void setDisable_pay_channels(String disable_pay_channels) {
        this.disable_pay_channels = disable_pay_channels;
    }

    public void setDisable_pay_channels(PayChannel[] disable_pay_channels) {
        if (disable_pay_channels == null || disable_pay_channels.length == 0) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        Arrays.asList(disable_pay_channels).forEach(payChannel -> sb.append(",").append(payChannel));
        this.disable_pay_channels = sb.substring(1);
    }

    /**
     * 业务扩展参数
     */
    public static class ExtendParams {
        /**
         * 系统商编号，该参数作为系统商返佣数据提取的依据，请填写系统商签约协议的PID
         */
        private String sys_service_provider_id;
        /**
         * 花呗分期数（目前仅支持3、6、12）注：使用该参数需要仔细阅读“花呗分期接入文档”
         */
        private String hb_fq_num;
        /**
         * 卖家承担收费比例，商家承担手续费传入100，用户承担手续费传入0，仅支持传入100、0两种，其他比例暂不支持注：使用该参数需要仔细阅读“花呗分期接入文档”
         */
        private String hb_fq_seller_percent;

        public String getSys_service_provider_id() {
            return sys_service_provider_id;
        }

        public void setSys_service_provider_id(String sys_service_provider_id) {
            this.sys_service_provider_id = sys_service_provider_id;
        }

        public String getHb_fq_num() {
            return hb_fq_num;
        }

        public void setHb_fq_num(String hb_fq_num) {
            this.hb_fq_num = hb_fq_num;
        }

        public String getHb_fq_seller_percent() {
            return hb_fq_seller_percent;
        }

        public void setHb_fq_seller_percent(String hb_fq_seller_percent) {
            this.hb_fq_seller_percent = hb_fq_seller_percent;
        }

        public String toJsonString() {
            String json = JSONObject.toJSONString(this);
            return json.length() > 2 ? json : null;
        }

    }

    /**
     * 渠道
     */
    public enum PayChannel {
        /**
         * 余额
         */
        balance("余额"),
        /**
         * 余额宝
         */
        moneyFund("余额宝"),
        /**
         * 红包
         */
        coupon("红包"),
        /**
         * 花呗
         */
        pcredit("花呗"),
        /**
         * 花呗分期
         */
        pcreditpayInstallment("花呗分期"),
        /**
         * 信用卡
         */
        creditCard("信用卡"),
        /**
         * 信用卡快捷
         */
        creditCardExpress("信用卡快捷"),
        /**
         * 信用卡卡通
         */
        creditCardCartoon("信用卡卡通"),
        /**
         * 信用支付类型（包含信用卡卡通、信用卡快捷、花呗、花呗分期）
         */
        credit_group("信用支付类型（包含信用卡卡通、信用卡快捷、花呗、花呗分期）"),
        /**
         * 借记卡快捷
         */
        debitCardExpress("借记卡快捷"),
        /**
         * 商户预存卡
         */
        mcard("商户预存卡"),
        /**
         * 个人预存卡
         */
        pcard("个人预存卡"),
        /**
         * 优惠（包含实时优惠+商户优惠）
         */
        promotion("优惠（包含实时优惠+商户优惠）"),
        /**
         * 营销券
         */
        voucher("营销券"),
        /**
         * 积分
         */
        point("积分"),
        /**
         * 商户优惠
         */
        mdiscount("商户优惠"),
        /**
         * 网银
         */
        bankPay("网银");

        String label;

        PayChannel(String label) {
            this.label = label;
        }
    }

    public String toJsonString() {
        return JSONObject.toJSONString(this);
    }

}
