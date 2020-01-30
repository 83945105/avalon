package pub.avalon.pay.beans;

import java.math.BigDecimal;

/**
 * 支付宝异步通知-业务
 *
 * @author baichao
 */
public class AlipayNotifyComputerWebsite extends AlipayNotify {

    /**
     * 支付宝交易号
     */
    private String trade_no;
    /**
     * 开发者的app_id
     */
    private String app_id;
    /**
     * 商户订单号
     */
    private String out_trade_no;
    /**
     * 商户业务号
     */
    private String out_biz_no;
    /**
     * 买家支付宝用户号
     */
    private String buyer_id;
    /**
     * 卖家支付宝用户号
     */
    private String seller_id;
    /**
     * 交易状态
     */
    private String trade_status;
    /**
     * 订单金额
     */
    private BigDecimal total_amount;
    /**
     * 实收金额
     */
    private BigDecimal receipt_amount;
    /**
     * 开票金额
     */
    private BigDecimal invoice_amount;
    /**
     * 付款金额
     */
    private BigDecimal buyer_pay_amount;
    /**
     * 集分宝金额
     */
    private BigDecimal point_amount;
    /**
     * 总退款金额
     */
    private BigDecimal refund_fee;
    /**
     * 订单标题
     */
    private String subject;
    /**
     * 商品描述
     */
    private String body;
    /**
     * 交易创建时间
     */
    private String gmt_create;
    /**
     * 交易付款时间
     */
    private String gmt_payment;
    /**
     * 交易退款时间
     */
    private String gmt_refund;
    /**
     * 交易结束时间
     */
    private String gmt_close;
    /**
     * 支付金额信息
     */
    private String fund_bill_list;
    /**
     * 优惠券信息
     */
    private String voucher_detail_list;
    /**
     * 回传参数
     */
    private String passback_params;

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getOut_biz_no() {
        return out_biz_no;
    }

    public void setOut_biz_no(String out_biz_no) {
        this.out_biz_no = out_biz_no;
    }

    public String getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(String buyer_id) {
        this.buyer_id = buyer_id;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getTrade_status() {
        return trade_status;
    }

    public void setTrade_status(String trade_status) {
        this.trade_status = trade_status;
    }

    public BigDecimal getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(BigDecimal total_amount) {
        this.total_amount = total_amount;
    }

    public BigDecimal getReceipt_amount() {
        return receipt_amount;
    }

    public void setReceipt_amount(BigDecimal receipt_amount) {
        this.receipt_amount = receipt_amount;
    }

    public BigDecimal getInvoice_amount() {
        return invoice_amount;
    }

    public void setInvoice_amount(BigDecimal invoice_amount) {
        this.invoice_amount = invoice_amount;
    }

    public BigDecimal getBuyer_pay_amount() {
        return buyer_pay_amount;
    }

    public void setBuyer_pay_amount(BigDecimal buyer_pay_amount) {
        this.buyer_pay_amount = buyer_pay_amount;
    }

    public BigDecimal getPoint_amount() {
        return point_amount;
    }

    public void setPoint_amount(BigDecimal point_amount) {
        this.point_amount = point_amount;
    }

    public BigDecimal getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(BigDecimal refund_fee) {
        this.refund_fee = refund_fee;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(String gmt_create) {
        this.gmt_create = gmt_create;
    }

    public String getGmt_payment() {
        return gmt_payment;
    }

    public void setGmt_payment(String gmt_payment) {
        this.gmt_payment = gmt_payment;
    }

    public String getGmt_refund() {
        return gmt_refund;
    }

    public void setGmt_refund(String gmt_refund) {
        this.gmt_refund = gmt_refund;
    }

    public String getGmt_close() {
        return gmt_close;
    }

    public void setGmt_close(String gmt_close) {
        this.gmt_close = gmt_close;
    }

    public String getFund_bill_list() {
        return fund_bill_list;
    }

    public void setFund_bill_list(String fund_bill_list) {
        this.fund_bill_list = fund_bill_list;
    }

    public String getVoucher_detail_list() {
        return voucher_detail_list;
    }

    public void setVoucher_detail_list(String voucher_detail_list) {
        this.voucher_detail_list = voucher_detail_list;
    }

    public String getPassback_params() {
        return passback_params;
    }

    public void setPassback_params(String passback_params) {
        this.passback_params = passback_params;
    }
}
