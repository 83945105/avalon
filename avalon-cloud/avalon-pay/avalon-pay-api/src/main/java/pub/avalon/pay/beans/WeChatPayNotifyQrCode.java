package pub.avalon.pay.beans;

import java.util.Map;

/**
 * 微信支付异步通知-业务
 *
 * @author baichao
 */
public class WeChatPayNotifyQrCode {

    /**
     * 返回状态码
     */
    private String return_code;
    /**
     * 返回信息
     */
    private String return_msg;
    /**
     * 公众账号ID
     */
    private String appid;
    /**
     * 商户号
     */
    private String mch_id;
    /**
     * 设备号
     */
    private String device_info;
    /**
     * 随机字符串
     */
    private String nonce_str;
    /**
     * 签名
     */
    private String sign;
    /**
     * 签名类型
     */
    private String sign_type;
    /**
     * 业务结果
     */
    private String result_code;
    /**
     * 错误代码
     */
    private String err_code;
    /**
     * 错误代码描述
     */
    private String err_code_des;
    /**
     * 用户标识
     */
    private String openid;
    /**
     * 是否关注公众账号
     */
    private String is_subscribe;
    /**
     * 交易类型
     */
    private String trade_type;
    /**
     * 付款银行
     */
    private String bank_type;
    /**
     * 订单金额
     */
    private Long total_fee;
    /**
     * 应结订单金额
     */
    private Long settlement_total_fee;
    /**
     * 货币种类
     */
    private String fee_type;
    /**
     * 现金支付金额
     */
    private Long cash_fee;
    /**
     * 现金支付货币类型
     */
    private String cash_fee_type;
    /**
     * 总代金券金额
     */
    private Long coupon_fee;
    /**
     * 代金券使用数量
     */
    private Long coupon_count;
    /**
     * 代金券类型
     */
    private String coupon_type_$n;
    /**
     * 代金券ID
     */
    private String coupon_id_$n;
    /**
     * 单个代金券支付金额
     */
    private Long coupon_fee_$n;
    /**
     * 微信支付订单号
     */
    private String transaction_id;
    /**
     * 商户订单号
     */
    private String out_trade_no;
    /**
     * 商家数据包
     */
    private String attach;
    /**
     * 支付完成时间
     */
    private String time_end;

    public WeChatPayNotifyQrCode() {
    }

    public WeChatPayNotifyQrCode(Map<String, String> notifyMap) {
        this.setReturn_code(String.valueOf(notifyMap.get("return_code")));
        this.setReturn_msg(String.valueOf(notifyMap.get("return_msg")));
        this.setAppid(String.valueOf(notifyMap.get("appid")));
        this.setMch_id(String.valueOf(notifyMap.get("mch_id")));
        this.setDevice_info(String.valueOf(notifyMap.get("device_info")));
        this.setNonce_str(String.valueOf(notifyMap.get("nonce_str")));
        this.setSign(String.valueOf(notifyMap.get("sign")));
        this.setSign_type(String.valueOf(notifyMap.get("sign_type")));
        this.setResult_code(String.valueOf(notifyMap.get("result_code")));
        this.setErr_code(String.valueOf(notifyMap.get("err_code")));
        this.setErr_code_des(String.valueOf(notifyMap.get("err_code_des")));
        this.setOpenid(String.valueOf(notifyMap.get("openid")));
        this.setIs_subscribe(String.valueOf(notifyMap.get("is_subscribe")));
        this.setTrade_type(String.valueOf(notifyMap.get("trade_type")));
        this.setBank_type(String.valueOf(notifyMap.get("bank_type")));
        if (notifyMap.get("total_fee") != null) {
            this.setTotal_fee(Long.valueOf(notifyMap.get("total_fee")));
        }
        if (notifyMap.get("settlement_total_fee") != null) {
            this.setSettlement_total_fee(Long.valueOf(notifyMap.get("settlement_total_fee")));
        }
        this.setFee_type(String.valueOf(notifyMap.get("fee_type")));
        if (notifyMap.get("cash_fee") != null) {
            this.setCash_fee(Long.valueOf(notifyMap.get("cash_fee")));
        }
        this.setCash_fee_type(String.valueOf(notifyMap.get("cash_fee_type")));
        if (notifyMap.get("coupon_fee") != null) {
            this.setCoupon_fee(Long.valueOf(notifyMap.get("coupon_fee")));
        }
        if (notifyMap.get("coupon_count") != null) {
            this.setCoupon_count(Long.valueOf(notifyMap.get("coupon_count")));
        }
        this.setCoupon_type_$n(String.valueOf(notifyMap.get("coupon_type_$n")));
        this.setCoupon_id_$n(String.valueOf(notifyMap.get("coupon_id_$n")));
        if (notifyMap.get("coupon_fee_$n") != null) {
            this.setCoupon_fee_$n(Long.valueOf(notifyMap.get("coupon_fee_$n")));
        }
        this.setTransaction_id(String.valueOf(notifyMap.get("transaction_id")));
        this.setOut_trade_no(String.valueOf(notifyMap.get("out_trade_no")));
        this.setAttach(String.valueOf(notifyMap.get("attach")));
        this.setTime_end(String.valueOf(notifyMap.get("time_end")));
    }

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getIs_subscribe() {
        return is_subscribe;
    }

    public void setIs_subscribe(String is_subscribe) {
        this.is_subscribe = is_subscribe;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getBank_type() {
        return bank_type;
    }

    public void setBank_type(String bank_type) {
        this.bank_type = bank_type;
    }

    public Long getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Long total_fee) {
        this.total_fee = total_fee;
    }

    public Long getSettlement_total_fee() {
        return settlement_total_fee;
    }

    public void setSettlement_total_fee(Long settlement_total_fee) {
        this.settlement_total_fee = settlement_total_fee;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public Long getCash_fee() {
        return cash_fee;
    }

    public void setCash_fee(Long cash_fee) {
        this.cash_fee = cash_fee;
    }

    public String getCash_fee_type() {
        return cash_fee_type;
    }

    public void setCash_fee_type(String cash_fee_type) {
        this.cash_fee_type = cash_fee_type;
    }

    public Long getCoupon_fee() {
        return coupon_fee;
    }

    public void setCoupon_fee(Long coupon_fee) {
        this.coupon_fee = coupon_fee;
    }

    public Long getCoupon_count() {
        return coupon_count;
    }

    public void setCoupon_count(Long coupon_count) {
        this.coupon_count = coupon_count;
    }

    public String getCoupon_type_$n() {
        return coupon_type_$n;
    }

    public void setCoupon_type_$n(String coupon_type_$n) {
        this.coupon_type_$n = coupon_type_$n;
    }

    public String getCoupon_id_$n() {
        return coupon_id_$n;
    }

    public void setCoupon_id_$n(String coupon_id_$n) {
        this.coupon_id_$n = coupon_id_$n;
    }

    public Long getCoupon_fee_$n() {
        return coupon_fee_$n;
    }

    public void setCoupon_fee_$n(Long coupon_fee_$n) {
        this.coupon_fee_$n = coupon_fee_$n;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }
}
