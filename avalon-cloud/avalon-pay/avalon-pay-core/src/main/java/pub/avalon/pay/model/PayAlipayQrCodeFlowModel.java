package pub.avalon.pay.model;

import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.norm.Model;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("all")
public final class PayAlipayQrCodeFlowModel implements Model<PayAlipayQrCodeFlowModel, PayAlipayQrCodeFlowModel.Column, PayAlipayQrCodeFlowModel.On, PayAlipayQrCodeFlowModel.Where, PayAlipayQrCodeFlowModel.Sort, PayAlipayQrCodeFlowModel.Group> {

    /**
     * 表名
     */
    public static final String tableName = "pay_alipay_qr_code_flow";
    /**
     * 表别名
     */
    public static final String tableAlias = "PayAlipayQrCodeFlow";

    /**
     * 主键名
     */
    public static final String primaryKeyName = "id";
    /**
     * 主键别名
     */
    public static final String primaryKeyAlias = "id";



    /**
     * 主键ID
     */
    public static final String id = "id";
    /**
     * 主键ID
     */
    public static final String id_alias = "id";
    /**
     * 通知时间
     */
    public static final String notifyTime = "notify_time";
    /**
     * 通知时间
     */
    public static final String notifyTime_alias = "notifyTime";
    /**
     * 通知类型
     */
    public static final String notifyType = "notify_type";
    /**
     * 通知类型
     */
    public static final String notifyType_alias = "notifyType";
    /**
     * 通知校验ID
     */
    public static final String notifyId = "notify_id";
    /**
     * 通知校验ID
     */
    public static final String notifyId_alias = "notifyId";
    /**
     * 签名类型
     */
    public static final String signType = "sign_type";
    /**
     * 签名类型
     */
    public static final String signType_alias = "signType";
    /**
     * 签名
     */
    public static final String sign = "sign";
    /**
     * 签名
     */
    public static final String sign_alias = "sign";
    /**
     * 支付宝交易号
     */
    public static final String tradeNo = "trade_no";
    /**
     * 支付宝交易号
     */
    public static final String tradeNo_alias = "tradeNo";
    /**
     * 开发者的app_id
     */
    public static final String appId = "app_id";
    /**
     * 开发者的app_id
     */
    public static final String appId_alias = "appId";
    /**
     * 商户订单号
     */
    public static final String outTradeNo = "out_trade_no";
    /**
     * 商户订单号
     */
    public static final String outTradeNo_alias = "outTradeNo";
    /**
     * 商户业务号
     */
    public static final String outBizNo = "out_biz_no";
    /**
     * 商户业务号
     */
    public static final String outBizNo_alias = "outBizNo";
    /**
     * 买家支付宝用户号
     */
    public static final String buyerId = "buyer_id";
    /**
     * 买家支付宝用户号
     */
    public static final String buyerId_alias = "buyerId";
    /**
     * 买家支付宝账号
     */
    public static final String buyerLogonId = "buyer_logon_id";
    /**
     * 买家支付宝账号
     */
    public static final String buyerLogonId_alias = "buyerLogonId";
    /**
     * 卖家支付宝用户号
     */
    public static final String sellerId = "seller_id";
    /**
     * 卖家支付宝用户号
     */
    public static final String sellerId_alias = "sellerId";
    /**
     * 卖家支付宝账号
     */
    public static final String sellerEmail = "seller_email";
    /**
     * 卖家支付宝账号
     */
    public static final String sellerEmail_alias = "sellerEmail";
    /**
     * 交易状态
     */
    public static final String tradeStatus = "trade_status";
    /**
     * 交易状态
     */
    public static final String tradeStatus_alias = "tradeStatus";
    /**
     * 订单金额
     */
    public static final String totalAmount = "total_amount";
    /**
     * 订单金额
     */
    public static final String totalAmount_alias = "totalAmount";
    /**
     * 实收金额
     */
    public static final String receiptAmount = "receipt_amount";
    /**
     * 实收金额
     */
    public static final String receiptAmount_alias = "receiptAmount";
    /**
     * 开票金额
     */
    public static final String invoiceAmount = "invoice_amount";
    /**
     * 开票金额
     */
    public static final String invoiceAmount_alias = "invoiceAmount";
    /**
     * 付款金额
     */
    public static final String buyerPayAmount = "buyer_pay_amount";
    /**
     * 付款金额
     */
    public static final String buyerPayAmount_alias = "buyerPayAmount";
    /**
     * 集分宝金额
     */
    public static final String pointAmount = "point_amount";
    /**
     * 集分宝金额
     */
    public static final String pointAmount_alias = "pointAmount";
    /**
     * 总退款金额
     */
    public static final String refundFee = "refund_fee";
    /**
     * 总退款金额
     */
    public static final String refundFee_alias = "refundFee";
    /**
     * 商户实际退款给用户的金额，单位为元，支持两位小数
     */
    public static final String sendBackFee = "send_back_fee";
    /**
     * 商户实际退款给用户的金额，单位为元，支持两位小数
     */
    public static final String sendBackFee_alias = "sendBackFee";
    /**
     * 订单标题
     */
    public static final String subject = "subject";
    /**
     * 订单标题
     */
    public static final String subject_alias = "subject";
    /**
     * 商品描述
     */
    public static final String body = "body";
    /**
     * 商品描述
     */
    public static final String body_alias = "body";
    /**
     * 交易创建时间
     */
    public static final String gmtCreate = "gmt_create";
    /**
     * 交易创建时间
     */
    public static final String gmtCreate_alias = "gmtCreate";
    /**
     * 交易付款时间
     */
    public static final String gmtPayment = "gmt_payment";
    /**
     * 交易付款时间
     */
    public static final String gmtPayment_alias = "gmtPayment";
    /**
     * 交易退款时间
     */
    public static final String gmtRefund = "gmt_refund";
    /**
     * 交易退款时间
     */
    public static final String gmtRefund_alias = "gmtRefund";
    /**
     * 交易结束时间
     */
    public static final String gmtClose = "gmt_close";
    /**
     * 交易结束时间
     */
    public static final String gmtClose_alias = "gmtClose";
    /**
     * 支付金额信息
     */
    public static final String fundBillList = "fund_bill_list";
    /**
     * 支付金额信息
     */
    public static final String fundBillList_alias = "fundBillList";

    /**
     * 字段-别名 集合
     */
    public static final Map<String, String> COLUMN_ALIAS_MAP = new LinkedHashMap<>();
    /**
     * 别名-字段 集合
     */
    public static final Map<String, String> ALIAS_COLUMN_MAP = new LinkedHashMap<>();

    static {
        COLUMN_ALIAS_MAP.put(id, id_alias);
        ALIAS_COLUMN_MAP.put(id_alias, id);
        COLUMN_ALIAS_MAP.put(notifyTime, notifyTime_alias);
        ALIAS_COLUMN_MAP.put(notifyTime_alias, notifyTime);
        COLUMN_ALIAS_MAP.put(notifyType, notifyType_alias);
        ALIAS_COLUMN_MAP.put(notifyType_alias, notifyType);
        COLUMN_ALIAS_MAP.put(notifyId, notifyId_alias);
        ALIAS_COLUMN_MAP.put(notifyId_alias, notifyId);
        COLUMN_ALIAS_MAP.put(signType, signType_alias);
        ALIAS_COLUMN_MAP.put(signType_alias, signType);
        COLUMN_ALIAS_MAP.put(sign, sign_alias);
        ALIAS_COLUMN_MAP.put(sign_alias, sign);
        COLUMN_ALIAS_MAP.put(tradeNo, tradeNo_alias);
        ALIAS_COLUMN_MAP.put(tradeNo_alias, tradeNo);
        COLUMN_ALIAS_MAP.put(appId, appId_alias);
        ALIAS_COLUMN_MAP.put(appId_alias, appId);
        COLUMN_ALIAS_MAP.put(outTradeNo, outTradeNo_alias);
        ALIAS_COLUMN_MAP.put(outTradeNo_alias, outTradeNo);
        COLUMN_ALIAS_MAP.put(outBizNo, outBizNo_alias);
        ALIAS_COLUMN_MAP.put(outBizNo_alias, outBizNo);
        COLUMN_ALIAS_MAP.put(buyerId, buyerId_alias);
        ALIAS_COLUMN_MAP.put(buyerId_alias, buyerId);
        COLUMN_ALIAS_MAP.put(buyerLogonId, buyerLogonId_alias);
        ALIAS_COLUMN_MAP.put(buyerLogonId_alias, buyerLogonId);
        COLUMN_ALIAS_MAP.put(sellerId, sellerId_alias);
        ALIAS_COLUMN_MAP.put(sellerId_alias, sellerId);
        COLUMN_ALIAS_MAP.put(sellerEmail, sellerEmail_alias);
        ALIAS_COLUMN_MAP.put(sellerEmail_alias, sellerEmail);
        COLUMN_ALIAS_MAP.put(tradeStatus, tradeStatus_alias);
        ALIAS_COLUMN_MAP.put(tradeStatus_alias, tradeStatus);
        COLUMN_ALIAS_MAP.put(totalAmount, totalAmount_alias);
        ALIAS_COLUMN_MAP.put(totalAmount_alias, totalAmount);
        COLUMN_ALIAS_MAP.put(receiptAmount, receiptAmount_alias);
        ALIAS_COLUMN_MAP.put(receiptAmount_alias, receiptAmount);
        COLUMN_ALIAS_MAP.put(invoiceAmount, invoiceAmount_alias);
        ALIAS_COLUMN_MAP.put(invoiceAmount_alias, invoiceAmount);
        COLUMN_ALIAS_MAP.put(buyerPayAmount, buyerPayAmount_alias);
        ALIAS_COLUMN_MAP.put(buyerPayAmount_alias, buyerPayAmount);
        COLUMN_ALIAS_MAP.put(pointAmount, pointAmount_alias);
        ALIAS_COLUMN_MAP.put(pointAmount_alias, pointAmount);
        COLUMN_ALIAS_MAP.put(refundFee, refundFee_alias);
        ALIAS_COLUMN_MAP.put(refundFee_alias, refundFee);
        COLUMN_ALIAS_MAP.put(sendBackFee, sendBackFee_alias);
        ALIAS_COLUMN_MAP.put(sendBackFee_alias, sendBackFee);
        COLUMN_ALIAS_MAP.put(subject, subject_alias);
        ALIAS_COLUMN_MAP.put(subject_alias, subject);
        COLUMN_ALIAS_MAP.put(body, body_alias);
        ALIAS_COLUMN_MAP.put(body_alias, body);
        COLUMN_ALIAS_MAP.put(gmtCreate, gmtCreate_alias);
        ALIAS_COLUMN_MAP.put(gmtCreate_alias, gmtCreate);
        COLUMN_ALIAS_MAP.put(gmtPayment, gmtPayment_alias);
        ALIAS_COLUMN_MAP.put(gmtPayment_alias, gmtPayment);
        COLUMN_ALIAS_MAP.put(gmtRefund, gmtRefund_alias);
        ALIAS_COLUMN_MAP.put(gmtRefund_alias, gmtRefund);
        COLUMN_ALIAS_MAP.put(gmtClose, gmtClose_alias);
        ALIAS_COLUMN_MAP.put(gmtClose_alias, gmtClose);
        COLUMN_ALIAS_MAP.put(fundBillList, fundBillList_alias);
        ALIAS_COLUMN_MAP.put(fundBillList_alias, fundBillList);
    }

    /**
     * 表名
     */
    @Override
    public String getTableName() {
        return tableName;
    }

    /**
     * 表别名
     */
    @Override
    public String getTableAlias() {
        return tableAlias;
    }

    /**
     * 主键名
     */
    @Override
    public String getPrimaryKeyName() {
        return primaryKeyName;
    }

    /**
     * 主键别名
     */
    @Override
    public String getPrimaryKeyAlias() {
        return primaryKeyAlias;
    }

    /**
     * 列名-别名 集合
     */
    @Override
    public Map<String, String> getColumnAliasMap() {
        return COLUMN_ALIAS_MAP;
    }

    /**
     * 别名-列名 集合
     */
    @Override
    public Map<String, String> getAliasColumnMap() {
        return ALIAS_COLUMN_MAP;
    }

    @Override
    public Column getColumnModel() {
        return new Column();
    }

    public static final class Column extends ColumnModel<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> {

        private Column() {
        }

        /**
         * 主键
         */
        public Column primaryKey() {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.primaryKeyName, PayAlipayQrCodeFlowModel.primaryKeyAlias);
            return this;
        }

        /**
         * 主键
         * @param alias 别名
         */
        public Column primaryKey(String alias) {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.primaryKeyName, alias);
            return this;
        }
    
        /**
         * 主键ID
         */
        public Column id() {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.id, PayAlipayQrCodeFlowModel.id_alias);
            return this;
        }

        /**
         * 主键ID
         * @param alias 别名
         */
        public Column id(String alias) {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.id, alias);
            return this;
        }

        /**
         * 通知时间
         */
        public Column notifyTime() {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.notifyTime, PayAlipayQrCodeFlowModel.notifyTime_alias);
            return this;
        }

        /**
         * 通知时间
         * @param alias 别名
         */
        public Column notifyTime(String alias) {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.notifyTime, alias);
            return this;
        }

        /**
         * 通知类型
         */
        public Column notifyType() {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.notifyType, PayAlipayQrCodeFlowModel.notifyType_alias);
            return this;
        }

        /**
         * 通知类型
         * @param alias 别名
         */
        public Column notifyType(String alias) {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.notifyType, alias);
            return this;
        }

        /**
         * 通知校验ID
         */
        public Column notifyId() {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.notifyId, PayAlipayQrCodeFlowModel.notifyId_alias);
            return this;
        }

        /**
         * 通知校验ID
         * @param alias 别名
         */
        public Column notifyId(String alias) {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.notifyId, alias);
            return this;
        }

        /**
         * 签名类型
         */
        public Column signType() {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.signType, PayAlipayQrCodeFlowModel.signType_alias);
            return this;
        }

        /**
         * 签名类型
         * @param alias 别名
         */
        public Column signType(String alias) {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.signType, alias);
            return this;
        }

        /**
         * 签名
         */
        public Column sign() {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.sign, PayAlipayQrCodeFlowModel.sign_alias);
            return this;
        }

        /**
         * 签名
         * @param alias 别名
         */
        public Column sign(String alias) {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.sign, alias);
            return this;
        }

        /**
         * 支付宝交易号
         */
        public Column tradeNo() {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.tradeNo, PayAlipayQrCodeFlowModel.tradeNo_alias);
            return this;
        }

        /**
         * 支付宝交易号
         * @param alias 别名
         */
        public Column tradeNo(String alias) {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.tradeNo, alias);
            return this;
        }

        /**
         * 开发者的app_id
         */
        public Column appId() {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.appId, PayAlipayQrCodeFlowModel.appId_alias);
            return this;
        }

        /**
         * 开发者的app_id
         * @param alias 别名
         */
        public Column appId(String alias) {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.appId, alias);
            return this;
        }

        /**
         * 商户订单号
         */
        public Column outTradeNo() {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.outTradeNo, PayAlipayQrCodeFlowModel.outTradeNo_alias);
            return this;
        }

        /**
         * 商户订单号
         * @param alias 别名
         */
        public Column outTradeNo(String alias) {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.outTradeNo, alias);
            return this;
        }

        /**
         * 商户业务号
         */
        public Column outBizNo() {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.outBizNo, PayAlipayQrCodeFlowModel.outBizNo_alias);
            return this;
        }

        /**
         * 商户业务号
         * @param alias 别名
         */
        public Column outBizNo(String alias) {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.outBizNo, alias);
            return this;
        }

        /**
         * 买家支付宝用户号
         */
        public Column buyerId() {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.buyerId, PayAlipayQrCodeFlowModel.buyerId_alias);
            return this;
        }

        /**
         * 买家支付宝用户号
         * @param alias 别名
         */
        public Column buyerId(String alias) {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.buyerId, alias);
            return this;
        }

        /**
         * 买家支付宝账号
         */
        public Column buyerLogonId() {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.buyerLogonId, PayAlipayQrCodeFlowModel.buyerLogonId_alias);
            return this;
        }

        /**
         * 买家支付宝账号
         * @param alias 别名
         */
        public Column buyerLogonId(String alias) {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.buyerLogonId, alias);
            return this;
        }

        /**
         * 卖家支付宝用户号
         */
        public Column sellerId() {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.sellerId, PayAlipayQrCodeFlowModel.sellerId_alias);
            return this;
        }

        /**
         * 卖家支付宝用户号
         * @param alias 别名
         */
        public Column sellerId(String alias) {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.sellerId, alias);
            return this;
        }

        /**
         * 卖家支付宝账号
         */
        public Column sellerEmail() {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.sellerEmail, PayAlipayQrCodeFlowModel.sellerEmail_alias);
            return this;
        }

        /**
         * 卖家支付宝账号
         * @param alias 别名
         */
        public Column sellerEmail(String alias) {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.sellerEmail, alias);
            return this;
        }

        /**
         * 交易状态
         */
        public Column tradeStatus() {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.tradeStatus, PayAlipayQrCodeFlowModel.tradeStatus_alias);
            return this;
        }

        /**
         * 交易状态
         * @param alias 别名
         */
        public Column tradeStatus(String alias) {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.tradeStatus, alias);
            return this;
        }

        /**
         * 订单金额
         */
        public Column totalAmount() {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.totalAmount, PayAlipayQrCodeFlowModel.totalAmount_alias);
            return this;
        }

        /**
         * 订单金额
         * @param alias 别名
         */
        public Column totalAmount(String alias) {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.totalAmount, alias);
            return this;
        }

        /**
         * 实收金额
         */
        public Column receiptAmount() {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.receiptAmount, PayAlipayQrCodeFlowModel.receiptAmount_alias);
            return this;
        }

        /**
         * 实收金额
         * @param alias 别名
         */
        public Column receiptAmount(String alias) {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.receiptAmount, alias);
            return this;
        }

        /**
         * 开票金额
         */
        public Column invoiceAmount() {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.invoiceAmount, PayAlipayQrCodeFlowModel.invoiceAmount_alias);
            return this;
        }

        /**
         * 开票金额
         * @param alias 别名
         */
        public Column invoiceAmount(String alias) {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.invoiceAmount, alias);
            return this;
        }

        /**
         * 付款金额
         */
        public Column buyerPayAmount() {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.buyerPayAmount, PayAlipayQrCodeFlowModel.buyerPayAmount_alias);
            return this;
        }

        /**
         * 付款金额
         * @param alias 别名
         */
        public Column buyerPayAmount(String alias) {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.buyerPayAmount, alias);
            return this;
        }

        /**
         * 集分宝金额
         */
        public Column pointAmount() {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.pointAmount, PayAlipayQrCodeFlowModel.pointAmount_alias);
            return this;
        }

        /**
         * 集分宝金额
         * @param alias 别名
         */
        public Column pointAmount(String alias) {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.pointAmount, alias);
            return this;
        }

        /**
         * 总退款金额
         */
        public Column refundFee() {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.refundFee, PayAlipayQrCodeFlowModel.refundFee_alias);
            return this;
        }

        /**
         * 总退款金额
         * @param alias 别名
         */
        public Column refundFee(String alias) {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.refundFee, alias);
            return this;
        }

        /**
         * 商户实际退款给用户的金额，单位为元，支持两位小数
         */
        public Column sendBackFee() {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.sendBackFee, PayAlipayQrCodeFlowModel.sendBackFee_alias);
            return this;
        }

        /**
         * 商户实际退款给用户的金额，单位为元，支持两位小数
         * @param alias 别名
         */
        public Column sendBackFee(String alias) {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.sendBackFee, alias);
            return this;
        }

        /**
         * 订单标题
         */
        public Column subject() {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.subject, PayAlipayQrCodeFlowModel.subject_alias);
            return this;
        }

        /**
         * 订单标题
         * @param alias 别名
         */
        public Column subject(String alias) {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.subject, alias);
            return this;
        }

        /**
         * 商品描述
         */
        public Column body() {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.body, PayAlipayQrCodeFlowModel.body_alias);
            return this;
        }

        /**
         * 商品描述
         * @param alias 别名
         */
        public Column body(String alias) {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.body, alias);
            return this;
        }

        /**
         * 交易创建时间
         */
        public Column gmtCreate() {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.gmtCreate, PayAlipayQrCodeFlowModel.gmtCreate_alias);
            return this;
        }

        /**
         * 交易创建时间
         * @param alias 别名
         */
        public Column gmtCreate(String alias) {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.gmtCreate, alias);
            return this;
        }

        /**
         * 交易付款时间
         */
        public Column gmtPayment() {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.gmtPayment, PayAlipayQrCodeFlowModel.gmtPayment_alias);
            return this;
        }

        /**
         * 交易付款时间
         * @param alias 别名
         */
        public Column gmtPayment(String alias) {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.gmtPayment, alias);
            return this;
        }

        /**
         * 交易退款时间
         */
        public Column gmtRefund() {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.gmtRefund, PayAlipayQrCodeFlowModel.gmtRefund_alias);
            return this;
        }

        /**
         * 交易退款时间
         * @param alias 别名
         */
        public Column gmtRefund(String alias) {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.gmtRefund, alias);
            return this;
        }

        /**
         * 交易结束时间
         */
        public Column gmtClose() {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.gmtClose, PayAlipayQrCodeFlowModel.gmtClose_alias);
            return this;
        }

        /**
         * 交易结束时间
         * @param alias 别名
         */
        public Column gmtClose(String alias) {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.gmtClose, alias);
            return this;
        }

        /**
         * 支付金额信息
         */
        public Column fundBillList() {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.fundBillList, PayAlipayQrCodeFlowModel.fundBillList_alias);
            return this;
        }

        /**
         * 支付金额信息
         * @param alias 别名
         */
        public Column fundBillList(String alias) {
            this.addColumnAlias(PayAlipayQrCodeFlowModel.fundBillList, alias);
            return this;
        }

    }

    @Override
    public On getOnModel() {
        return new On();
    }

    public static final class On extends OnModel<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> {

        private On() {
        }

        /**
         * 主键
         */
        public OnBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.onBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.primaryKeyName);
        }
    
        /**
         * 主键ID
         */
        public OnBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> id() {
            return this.onBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.id);
        }

        /**
         * 通知时间
         */
        public OnBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> notifyTime() {
            return this.onBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.notifyTime);
        }

        /**
         * 通知类型
         */
        public OnBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> notifyType() {
            return this.onBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.notifyType);
        }

        /**
         * 通知校验ID
         */
        public OnBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> notifyId() {
            return this.onBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.notifyId);
        }

        /**
         * 签名类型
         */
        public OnBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> signType() {
            return this.onBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.signType);
        }

        /**
         * 签名
         */
        public OnBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> sign() {
            return this.onBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.sign);
        }

        /**
         * 支付宝交易号
         */
        public OnBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> tradeNo() {
            return this.onBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.tradeNo);
        }

        /**
         * 开发者的app_id
         */
        public OnBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> appId() {
            return this.onBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.appId);
        }

        /**
         * 商户订单号
         */
        public OnBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> outTradeNo() {
            return this.onBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.outTradeNo);
        }

        /**
         * 商户业务号
         */
        public OnBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> outBizNo() {
            return this.onBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.outBizNo);
        }

        /**
         * 买家支付宝用户号
         */
        public OnBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> buyerId() {
            return this.onBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.buyerId);
        }

        /**
         * 买家支付宝账号
         */
        public OnBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> buyerLogonId() {
            return this.onBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.buyerLogonId);
        }

        /**
         * 卖家支付宝用户号
         */
        public OnBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> sellerId() {
            return this.onBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.sellerId);
        }

        /**
         * 卖家支付宝账号
         */
        public OnBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> sellerEmail() {
            return this.onBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.sellerEmail);
        }

        /**
         * 交易状态
         */
        public OnBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> tradeStatus() {
            return this.onBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.tradeStatus);
        }

        /**
         * 订单金额
         */
        public OnBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> totalAmount() {
            return this.onBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.totalAmount);
        }

        /**
         * 实收金额
         */
        public OnBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> receiptAmount() {
            return this.onBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.receiptAmount);
        }

        /**
         * 开票金额
         */
        public OnBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> invoiceAmount() {
            return this.onBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.invoiceAmount);
        }

        /**
         * 付款金额
         */
        public OnBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> buyerPayAmount() {
            return this.onBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.buyerPayAmount);
        }

        /**
         * 集分宝金额
         */
        public OnBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> pointAmount() {
            return this.onBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.pointAmount);
        }

        /**
         * 总退款金额
         */
        public OnBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> refundFee() {
            return this.onBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.refundFee);
        }

        /**
         * 商户实际退款给用户的金额，单位为元，支持两位小数
         */
        public OnBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> sendBackFee() {
            return this.onBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.sendBackFee);
        }

        /**
         * 订单标题
         */
        public OnBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> subject() {
            return this.onBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.subject);
        }

        /**
         * 商品描述
         */
        public OnBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> body() {
            return this.onBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.body);
        }

        /**
         * 交易创建时间
         */
        public OnBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> gmtCreate() {
            return this.onBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.gmtCreate);
        }

        /**
         * 交易付款时间
         */
        public OnBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> gmtPayment() {
            return this.onBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.gmtPayment);
        }

        /**
         * 交易退款时间
         */
        public OnBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> gmtRefund() {
            return this.onBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.gmtRefund);
        }

        /**
         * 交易结束时间
         */
        public OnBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> gmtClose() {
            return this.onBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.gmtClose);
        }

        /**
         * 支付金额信息
         */
        public OnBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> fundBillList() {
            return this.onBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.fundBillList);
        }

    }

    @Override
    public Where getWhereModel() {
        return new Where();
    }

    public static final class Where extends WhereModel<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> {

        private Where() {
        }

        /**
         * 主键
         */
        public WhereBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.whereBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.primaryKeyName);
        }
    
        /**
         * 主键ID
         */
        public WhereBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> id() {
            return this.whereBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.id);
        }

        /**
         * 通知时间
         */
        public WhereBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> notifyTime() {
            return this.whereBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.notifyTime);
        }

        /**
         * 通知类型
         */
        public WhereBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> notifyType() {
            return this.whereBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.notifyType);
        }

        /**
         * 通知校验ID
         */
        public WhereBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> notifyId() {
            return this.whereBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.notifyId);
        }

        /**
         * 签名类型
         */
        public WhereBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> signType() {
            return this.whereBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.signType);
        }

        /**
         * 签名
         */
        public WhereBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> sign() {
            return this.whereBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.sign);
        }

        /**
         * 支付宝交易号
         */
        public WhereBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> tradeNo() {
            return this.whereBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.tradeNo);
        }

        /**
         * 开发者的app_id
         */
        public WhereBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> appId() {
            return this.whereBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.appId);
        }

        /**
         * 商户订单号
         */
        public WhereBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> outTradeNo() {
            return this.whereBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.outTradeNo);
        }

        /**
         * 商户业务号
         */
        public WhereBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> outBizNo() {
            return this.whereBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.outBizNo);
        }

        /**
         * 买家支付宝用户号
         */
        public WhereBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> buyerId() {
            return this.whereBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.buyerId);
        }

        /**
         * 买家支付宝账号
         */
        public WhereBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> buyerLogonId() {
            return this.whereBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.buyerLogonId);
        }

        /**
         * 卖家支付宝用户号
         */
        public WhereBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> sellerId() {
            return this.whereBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.sellerId);
        }

        /**
         * 卖家支付宝账号
         */
        public WhereBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> sellerEmail() {
            return this.whereBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.sellerEmail);
        }

        /**
         * 交易状态
         */
        public WhereBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> tradeStatus() {
            return this.whereBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.tradeStatus);
        }

        /**
         * 订单金额
         */
        public WhereBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> totalAmount() {
            return this.whereBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.totalAmount);
        }

        /**
         * 实收金额
         */
        public WhereBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> receiptAmount() {
            return this.whereBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.receiptAmount);
        }

        /**
         * 开票金额
         */
        public WhereBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> invoiceAmount() {
            return this.whereBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.invoiceAmount);
        }

        /**
         * 付款金额
         */
        public WhereBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> buyerPayAmount() {
            return this.whereBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.buyerPayAmount);
        }

        /**
         * 集分宝金额
         */
        public WhereBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> pointAmount() {
            return this.whereBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.pointAmount);
        }

        /**
         * 总退款金额
         */
        public WhereBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> refundFee() {
            return this.whereBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.refundFee);
        }

        /**
         * 商户实际退款给用户的金额，单位为元，支持两位小数
         */
        public WhereBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> sendBackFee() {
            return this.whereBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.sendBackFee);
        }

        /**
         * 订单标题
         */
        public WhereBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> subject() {
            return this.whereBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.subject);
        }

        /**
         * 商品描述
         */
        public WhereBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> body() {
            return this.whereBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.body);
        }

        /**
         * 交易创建时间
         */
        public WhereBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> gmtCreate() {
            return this.whereBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.gmtCreate);
        }

        /**
         * 交易付款时间
         */
        public WhereBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> gmtPayment() {
            return this.whereBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.gmtPayment);
        }

        /**
         * 交易退款时间
         */
        public WhereBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> gmtRefund() {
            return this.whereBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.gmtRefund);
        }

        /**
         * 交易结束时间
         */
        public WhereBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> gmtClose() {
            return this.whereBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.gmtClose);
        }

        /**
         * 支付金额信息
         */
        public WhereBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> fundBillList() {
            return this.whereBuilder.handler(PayAlipayQrCodeFlowModel.tableName, PayAlipayQrCodeFlowModel.tableAlias, PayAlipayQrCodeFlowModel.fundBillList);
        }

    }

    @Override
    public Group getGroupModel() {
        return new Group();
    }

    public static final class Group extends GroupModel<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> {

        private Group() {
        }

        /**
         * 主键
         */
        public Group primaryKey() {
            this.addColumn(PayAlipayQrCodeFlowModel.primaryKeyName);
            return this;
        }
    
        /**
         * 主键ID
         */
        public Group id() {
            this.addColumn(PayAlipayQrCodeFlowModel.id);
            return this;
        }

        /**
         * 通知时间
         */
        public Group notifyTime() {
            this.addColumn(PayAlipayQrCodeFlowModel.notifyTime);
            return this;
        }

        /**
         * 通知类型
         */
        public Group notifyType() {
            this.addColumn(PayAlipayQrCodeFlowModel.notifyType);
            return this;
        }

        /**
         * 通知校验ID
         */
        public Group notifyId() {
            this.addColumn(PayAlipayQrCodeFlowModel.notifyId);
            return this;
        }

        /**
         * 签名类型
         */
        public Group signType() {
            this.addColumn(PayAlipayQrCodeFlowModel.signType);
            return this;
        }

        /**
         * 签名
         */
        public Group sign() {
            this.addColumn(PayAlipayQrCodeFlowModel.sign);
            return this;
        }

        /**
         * 支付宝交易号
         */
        public Group tradeNo() {
            this.addColumn(PayAlipayQrCodeFlowModel.tradeNo);
            return this;
        }

        /**
         * 开发者的app_id
         */
        public Group appId() {
            this.addColumn(PayAlipayQrCodeFlowModel.appId);
            return this;
        }

        /**
         * 商户订单号
         */
        public Group outTradeNo() {
            this.addColumn(PayAlipayQrCodeFlowModel.outTradeNo);
            return this;
        }

        /**
         * 商户业务号
         */
        public Group outBizNo() {
            this.addColumn(PayAlipayQrCodeFlowModel.outBizNo);
            return this;
        }

        /**
         * 买家支付宝用户号
         */
        public Group buyerId() {
            this.addColumn(PayAlipayQrCodeFlowModel.buyerId);
            return this;
        }

        /**
         * 买家支付宝账号
         */
        public Group buyerLogonId() {
            this.addColumn(PayAlipayQrCodeFlowModel.buyerLogonId);
            return this;
        }

        /**
         * 卖家支付宝用户号
         */
        public Group sellerId() {
            this.addColumn(PayAlipayQrCodeFlowModel.sellerId);
            return this;
        }

        /**
         * 卖家支付宝账号
         */
        public Group sellerEmail() {
            this.addColumn(PayAlipayQrCodeFlowModel.sellerEmail);
            return this;
        }

        /**
         * 交易状态
         */
        public Group tradeStatus() {
            this.addColumn(PayAlipayQrCodeFlowModel.tradeStatus);
            return this;
        }

        /**
         * 订单金额
         */
        public Group totalAmount() {
            this.addColumn(PayAlipayQrCodeFlowModel.totalAmount);
            return this;
        }

        /**
         * 实收金额
         */
        public Group receiptAmount() {
            this.addColumn(PayAlipayQrCodeFlowModel.receiptAmount);
            return this;
        }

        /**
         * 开票金额
         */
        public Group invoiceAmount() {
            this.addColumn(PayAlipayQrCodeFlowModel.invoiceAmount);
            return this;
        }

        /**
         * 付款金额
         */
        public Group buyerPayAmount() {
            this.addColumn(PayAlipayQrCodeFlowModel.buyerPayAmount);
            return this;
        }

        /**
         * 集分宝金额
         */
        public Group pointAmount() {
            this.addColumn(PayAlipayQrCodeFlowModel.pointAmount);
            return this;
        }

        /**
         * 总退款金额
         */
        public Group refundFee() {
            this.addColumn(PayAlipayQrCodeFlowModel.refundFee);
            return this;
        }

        /**
         * 商户实际退款给用户的金额，单位为元，支持两位小数
         */
        public Group sendBackFee() {
            this.addColumn(PayAlipayQrCodeFlowModel.sendBackFee);
            return this;
        }

        /**
         * 订单标题
         */
        public Group subject() {
            this.addColumn(PayAlipayQrCodeFlowModel.subject);
            return this;
        }

        /**
         * 商品描述
         */
        public Group body() {
            this.addColumn(PayAlipayQrCodeFlowModel.body);
            return this;
        }

        /**
         * 交易创建时间
         */
        public Group gmtCreate() {
            this.addColumn(PayAlipayQrCodeFlowModel.gmtCreate);
            return this;
        }

        /**
         * 交易付款时间
         */
        public Group gmtPayment() {
            this.addColumn(PayAlipayQrCodeFlowModel.gmtPayment);
            return this;
        }

        /**
         * 交易退款时间
         */
        public Group gmtRefund() {
            this.addColumn(PayAlipayQrCodeFlowModel.gmtRefund);
            return this;
        }

        /**
         * 交易结束时间
         */
        public Group gmtClose() {
            this.addColumn(PayAlipayQrCodeFlowModel.gmtClose);
            return this;
        }

        /**
         * 支付金额信息
         */
        public Group fundBillList() {
            this.addColumn(PayAlipayQrCodeFlowModel.fundBillList);
            return this;
        }

    }

    @Override
    public Sort getSortModel() {
        return new Sort();
    }

    public static final class Sort extends SortModel<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> {

        private Sort() {
        }

        /**
         * 主键
         */
        public SortBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.sortBuilder.handler(PayAlipayQrCodeFlowModel.primaryKeyName);
        }
    
        /**
         * 主键ID
         */
        public SortBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> id() {
            return this.sortBuilder.handler(PayAlipayQrCodeFlowModel.id);
        }

        /**
         * 通知时间
         */
        public SortBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> notifyTime() {
            return this.sortBuilder.handler(PayAlipayQrCodeFlowModel.notifyTime);
        }

        /**
         * 通知类型
         */
        public SortBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> notifyType() {
            return this.sortBuilder.handler(PayAlipayQrCodeFlowModel.notifyType);
        }

        /**
         * 通知校验ID
         */
        public SortBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> notifyId() {
            return this.sortBuilder.handler(PayAlipayQrCodeFlowModel.notifyId);
        }

        /**
         * 签名类型
         */
        public SortBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> signType() {
            return this.sortBuilder.handler(PayAlipayQrCodeFlowModel.signType);
        }

        /**
         * 签名
         */
        public SortBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> sign() {
            return this.sortBuilder.handler(PayAlipayQrCodeFlowModel.sign);
        }

        /**
         * 支付宝交易号
         */
        public SortBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> tradeNo() {
            return this.sortBuilder.handler(PayAlipayQrCodeFlowModel.tradeNo);
        }

        /**
         * 开发者的app_id
         */
        public SortBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> appId() {
            return this.sortBuilder.handler(PayAlipayQrCodeFlowModel.appId);
        }

        /**
         * 商户订单号
         */
        public SortBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> outTradeNo() {
            return this.sortBuilder.handler(PayAlipayQrCodeFlowModel.outTradeNo);
        }

        /**
         * 商户业务号
         */
        public SortBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> outBizNo() {
            return this.sortBuilder.handler(PayAlipayQrCodeFlowModel.outBizNo);
        }

        /**
         * 买家支付宝用户号
         */
        public SortBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> buyerId() {
            return this.sortBuilder.handler(PayAlipayQrCodeFlowModel.buyerId);
        }

        /**
         * 买家支付宝账号
         */
        public SortBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> buyerLogonId() {
            return this.sortBuilder.handler(PayAlipayQrCodeFlowModel.buyerLogonId);
        }

        /**
         * 卖家支付宝用户号
         */
        public SortBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> sellerId() {
            return this.sortBuilder.handler(PayAlipayQrCodeFlowModel.sellerId);
        }

        /**
         * 卖家支付宝账号
         */
        public SortBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> sellerEmail() {
            return this.sortBuilder.handler(PayAlipayQrCodeFlowModel.sellerEmail);
        }

        /**
         * 交易状态
         */
        public SortBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> tradeStatus() {
            return this.sortBuilder.handler(PayAlipayQrCodeFlowModel.tradeStatus);
        }

        /**
         * 订单金额
         */
        public SortBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> totalAmount() {
            return this.sortBuilder.handler(PayAlipayQrCodeFlowModel.totalAmount);
        }

        /**
         * 实收金额
         */
        public SortBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> receiptAmount() {
            return this.sortBuilder.handler(PayAlipayQrCodeFlowModel.receiptAmount);
        }

        /**
         * 开票金额
         */
        public SortBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> invoiceAmount() {
            return this.sortBuilder.handler(PayAlipayQrCodeFlowModel.invoiceAmount);
        }

        /**
         * 付款金额
         */
        public SortBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> buyerPayAmount() {
            return this.sortBuilder.handler(PayAlipayQrCodeFlowModel.buyerPayAmount);
        }

        /**
         * 集分宝金额
         */
        public SortBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> pointAmount() {
            return this.sortBuilder.handler(PayAlipayQrCodeFlowModel.pointAmount);
        }

        /**
         * 总退款金额
         */
        public SortBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> refundFee() {
            return this.sortBuilder.handler(PayAlipayQrCodeFlowModel.refundFee);
        }

        /**
         * 商户实际退款给用户的金额，单位为元，支持两位小数
         */
        public SortBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> sendBackFee() {
            return this.sortBuilder.handler(PayAlipayQrCodeFlowModel.sendBackFee);
        }

        /**
         * 订单标题
         */
        public SortBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> subject() {
            return this.sortBuilder.handler(PayAlipayQrCodeFlowModel.subject);
        }

        /**
         * 商品描述
         */
        public SortBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> body() {
            return this.sortBuilder.handler(PayAlipayQrCodeFlowModel.body);
        }

        /**
         * 交易创建时间
         */
        public SortBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> gmtCreate() {
            return this.sortBuilder.handler(PayAlipayQrCodeFlowModel.gmtCreate);
        }

        /**
         * 交易付款时间
         */
        public SortBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> gmtPayment() {
            return this.sortBuilder.handler(PayAlipayQrCodeFlowModel.gmtPayment);
        }

        /**
         * 交易退款时间
         */
        public SortBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> gmtRefund() {
            return this.sortBuilder.handler(PayAlipayQrCodeFlowModel.gmtRefund);
        }

        /**
         * 交易结束时间
         */
        public SortBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> gmtClose() {
            return this.sortBuilder.handler(PayAlipayQrCodeFlowModel.gmtClose);
        }

        /**
         * 支付金额信息
         */
        public SortBuilder<PayAlipayQrCodeFlowModel, Column, On, Where, Sort, Group> fundBillList() {
            return this.sortBuilder.handler(PayAlipayQrCodeFlowModel.fundBillList);
        }

    }

}