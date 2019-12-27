package pub.avalon.pay.model;

import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.norm.Model;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("all")
public final class PayAlipayComputerWebsiteFlowModel implements Model<PayAlipayComputerWebsiteFlowModel, PayAlipayComputerWebsiteFlowModel.Column, PayAlipayComputerWebsiteFlowModel.On, PayAlipayComputerWebsiteFlowModel.Where, PayAlipayComputerWebsiteFlowModel.Sort, PayAlipayComputerWebsiteFlowModel.Group> {

    /**
     * 表名
     */
    public static final String tableName = "pay_alipay_computer_website_flow";
    /**
     * 表别名
     */
    public static final String tableAlias = "PayAlipayComputerWebsiteFlow";

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
     * 编码格式
     */
    public static final String charset = "charset";
    /**
     * 编码格式
     */
    public static final String charset_alias = "charset";
    /**
     * 接口版本
     */
    public static final String version = "version";
    /**
     * 接口版本
     */
    public static final String version_alias = "version";
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
     * 授权方的app_id
     */
    public static final String authAppId = "auth_app_id";
    /**
     * 授权方的app_id
     */
    public static final String authAppId_alias = "authAppId";
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
     * 卖家支付宝用户号
     */
    public static final String sellerId = "seller_id";
    /**
     * 卖家支付宝用户号
     */
    public static final String sellerId_alias = "sellerId";
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
     * 优惠券信息
     */
    public static final String voucherDetailList = "voucher_detail_list";
    /**
     * 优惠券信息
     */
    public static final String voucherDetailList_alias = "voucherDetailList";
    /**
     * 回传参数
     */
    public static final String passbackParams = "passback_params";
    /**
     * 回传参数
     */
    public static final String passbackParams_alias = "passbackParams";

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
        COLUMN_ALIAS_MAP.put(charset, charset_alias);
        ALIAS_COLUMN_MAP.put(charset_alias, charset);
        COLUMN_ALIAS_MAP.put(version, version_alias);
        ALIAS_COLUMN_MAP.put(version_alias, version);
        COLUMN_ALIAS_MAP.put(signType, signType_alias);
        ALIAS_COLUMN_MAP.put(signType_alias, signType);
        COLUMN_ALIAS_MAP.put(sign, sign_alias);
        ALIAS_COLUMN_MAP.put(sign_alias, sign);
        COLUMN_ALIAS_MAP.put(authAppId, authAppId_alias);
        ALIAS_COLUMN_MAP.put(authAppId_alias, authAppId);
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
        COLUMN_ALIAS_MAP.put(sellerId, sellerId_alias);
        ALIAS_COLUMN_MAP.put(sellerId_alias, sellerId);
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
        COLUMN_ALIAS_MAP.put(voucherDetailList, voucherDetailList_alias);
        ALIAS_COLUMN_MAP.put(voucherDetailList_alias, voucherDetailList);
        COLUMN_ALIAS_MAP.put(passbackParams, passbackParams_alias);
        ALIAS_COLUMN_MAP.put(passbackParams_alias, passbackParams);
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

    public static final class Column extends ColumnModel<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> {

        private Column() {
        }

        /**
         * 主键
         */
        public Column primaryKey() {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.primaryKeyName, PayAlipayComputerWebsiteFlowModel.primaryKeyAlias);
            return this;
        }

        /**
         * 主键
         * @param alias 别名
         */
        public Column primaryKey(String alias) {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.primaryKeyName, alias);
            return this;
        }
    
        /**
         * 主键ID
         */
        public Column id() {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.id, PayAlipayComputerWebsiteFlowModel.id_alias);
            return this;
        }

        /**
         * 主键ID
         * @param alias 别名
         */
        public Column id(String alias) {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.id, alias);
            return this;
        }

        /**
         * 通知时间
         */
        public Column notifyTime() {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.notifyTime, PayAlipayComputerWebsiteFlowModel.notifyTime_alias);
            return this;
        }

        /**
         * 通知时间
         * @param alias 别名
         */
        public Column notifyTime(String alias) {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.notifyTime, alias);
            return this;
        }

        /**
         * 通知类型
         */
        public Column notifyType() {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.notifyType, PayAlipayComputerWebsiteFlowModel.notifyType_alias);
            return this;
        }

        /**
         * 通知类型
         * @param alias 别名
         */
        public Column notifyType(String alias) {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.notifyType, alias);
            return this;
        }

        /**
         * 通知校验ID
         */
        public Column notifyId() {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.notifyId, PayAlipayComputerWebsiteFlowModel.notifyId_alias);
            return this;
        }

        /**
         * 通知校验ID
         * @param alias 别名
         */
        public Column notifyId(String alias) {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.notifyId, alias);
            return this;
        }

        /**
         * 编码格式
         */
        public Column charset() {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.charset, PayAlipayComputerWebsiteFlowModel.charset_alias);
            return this;
        }

        /**
         * 编码格式
         * @param alias 别名
         */
        public Column charset(String alias) {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.charset, alias);
            return this;
        }

        /**
         * 接口版本
         */
        public Column version() {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.version, PayAlipayComputerWebsiteFlowModel.version_alias);
            return this;
        }

        /**
         * 接口版本
         * @param alias 别名
         */
        public Column version(String alias) {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.version, alias);
            return this;
        }

        /**
         * 签名类型
         */
        public Column signType() {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.signType, PayAlipayComputerWebsiteFlowModel.signType_alias);
            return this;
        }

        /**
         * 签名类型
         * @param alias 别名
         */
        public Column signType(String alias) {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.signType, alias);
            return this;
        }

        /**
         * 签名
         */
        public Column sign() {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.sign, PayAlipayComputerWebsiteFlowModel.sign_alias);
            return this;
        }

        /**
         * 签名
         * @param alias 别名
         */
        public Column sign(String alias) {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.sign, alias);
            return this;
        }

        /**
         * 授权方的app_id
         */
        public Column authAppId() {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.authAppId, PayAlipayComputerWebsiteFlowModel.authAppId_alias);
            return this;
        }

        /**
         * 授权方的app_id
         * @param alias 别名
         */
        public Column authAppId(String alias) {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.authAppId, alias);
            return this;
        }

        /**
         * 支付宝交易号
         */
        public Column tradeNo() {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.tradeNo, PayAlipayComputerWebsiteFlowModel.tradeNo_alias);
            return this;
        }

        /**
         * 支付宝交易号
         * @param alias 别名
         */
        public Column tradeNo(String alias) {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.tradeNo, alias);
            return this;
        }

        /**
         * 开发者的app_id
         */
        public Column appId() {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.appId, PayAlipayComputerWebsiteFlowModel.appId_alias);
            return this;
        }

        /**
         * 开发者的app_id
         * @param alias 别名
         */
        public Column appId(String alias) {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.appId, alias);
            return this;
        }

        /**
         * 商户订单号
         */
        public Column outTradeNo() {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.outTradeNo, PayAlipayComputerWebsiteFlowModel.outTradeNo_alias);
            return this;
        }

        /**
         * 商户订单号
         * @param alias 别名
         */
        public Column outTradeNo(String alias) {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.outTradeNo, alias);
            return this;
        }

        /**
         * 商户业务号
         */
        public Column outBizNo() {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.outBizNo, PayAlipayComputerWebsiteFlowModel.outBizNo_alias);
            return this;
        }

        /**
         * 商户业务号
         * @param alias 别名
         */
        public Column outBizNo(String alias) {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.outBizNo, alias);
            return this;
        }

        /**
         * 买家支付宝用户号
         */
        public Column buyerId() {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.buyerId, PayAlipayComputerWebsiteFlowModel.buyerId_alias);
            return this;
        }

        /**
         * 买家支付宝用户号
         * @param alias 别名
         */
        public Column buyerId(String alias) {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.buyerId, alias);
            return this;
        }

        /**
         * 卖家支付宝用户号
         */
        public Column sellerId() {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.sellerId, PayAlipayComputerWebsiteFlowModel.sellerId_alias);
            return this;
        }

        /**
         * 卖家支付宝用户号
         * @param alias 别名
         */
        public Column sellerId(String alias) {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.sellerId, alias);
            return this;
        }

        /**
         * 交易状态
         */
        public Column tradeStatus() {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.tradeStatus, PayAlipayComputerWebsiteFlowModel.tradeStatus_alias);
            return this;
        }

        /**
         * 交易状态
         * @param alias 别名
         */
        public Column tradeStatus(String alias) {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.tradeStatus, alias);
            return this;
        }

        /**
         * 订单金额
         */
        public Column totalAmount() {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.totalAmount, PayAlipayComputerWebsiteFlowModel.totalAmount_alias);
            return this;
        }

        /**
         * 订单金额
         * @param alias 别名
         */
        public Column totalAmount(String alias) {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.totalAmount, alias);
            return this;
        }

        /**
         * 实收金额
         */
        public Column receiptAmount() {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.receiptAmount, PayAlipayComputerWebsiteFlowModel.receiptAmount_alias);
            return this;
        }

        /**
         * 实收金额
         * @param alias 别名
         */
        public Column receiptAmount(String alias) {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.receiptAmount, alias);
            return this;
        }

        /**
         * 开票金额
         */
        public Column invoiceAmount() {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.invoiceAmount, PayAlipayComputerWebsiteFlowModel.invoiceAmount_alias);
            return this;
        }

        /**
         * 开票金额
         * @param alias 别名
         */
        public Column invoiceAmount(String alias) {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.invoiceAmount, alias);
            return this;
        }

        /**
         * 付款金额
         */
        public Column buyerPayAmount() {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.buyerPayAmount, PayAlipayComputerWebsiteFlowModel.buyerPayAmount_alias);
            return this;
        }

        /**
         * 付款金额
         * @param alias 别名
         */
        public Column buyerPayAmount(String alias) {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.buyerPayAmount, alias);
            return this;
        }

        /**
         * 集分宝金额
         */
        public Column pointAmount() {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.pointAmount, PayAlipayComputerWebsiteFlowModel.pointAmount_alias);
            return this;
        }

        /**
         * 集分宝金额
         * @param alias 别名
         */
        public Column pointAmount(String alias) {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.pointAmount, alias);
            return this;
        }

        /**
         * 总退款金额
         */
        public Column refundFee() {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.refundFee, PayAlipayComputerWebsiteFlowModel.refundFee_alias);
            return this;
        }

        /**
         * 总退款金额
         * @param alias 别名
         */
        public Column refundFee(String alias) {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.refundFee, alias);
            return this;
        }

        /**
         * 订单标题
         */
        public Column subject() {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.subject, PayAlipayComputerWebsiteFlowModel.subject_alias);
            return this;
        }

        /**
         * 订单标题
         * @param alias 别名
         */
        public Column subject(String alias) {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.subject, alias);
            return this;
        }

        /**
         * 商品描述
         */
        public Column body() {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.body, PayAlipayComputerWebsiteFlowModel.body_alias);
            return this;
        }

        /**
         * 商品描述
         * @param alias 别名
         */
        public Column body(String alias) {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.body, alias);
            return this;
        }

        /**
         * 交易创建时间
         */
        public Column gmtCreate() {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.gmtCreate, PayAlipayComputerWebsiteFlowModel.gmtCreate_alias);
            return this;
        }

        /**
         * 交易创建时间
         * @param alias 别名
         */
        public Column gmtCreate(String alias) {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.gmtCreate, alias);
            return this;
        }

        /**
         * 交易付款时间
         */
        public Column gmtPayment() {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.gmtPayment, PayAlipayComputerWebsiteFlowModel.gmtPayment_alias);
            return this;
        }

        /**
         * 交易付款时间
         * @param alias 别名
         */
        public Column gmtPayment(String alias) {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.gmtPayment, alias);
            return this;
        }

        /**
         * 交易退款时间
         */
        public Column gmtRefund() {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.gmtRefund, PayAlipayComputerWebsiteFlowModel.gmtRefund_alias);
            return this;
        }

        /**
         * 交易退款时间
         * @param alias 别名
         */
        public Column gmtRefund(String alias) {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.gmtRefund, alias);
            return this;
        }

        /**
         * 交易结束时间
         */
        public Column gmtClose() {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.gmtClose, PayAlipayComputerWebsiteFlowModel.gmtClose_alias);
            return this;
        }

        /**
         * 交易结束时间
         * @param alias 别名
         */
        public Column gmtClose(String alias) {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.gmtClose, alias);
            return this;
        }

        /**
         * 支付金额信息
         */
        public Column fundBillList() {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.fundBillList, PayAlipayComputerWebsiteFlowModel.fundBillList_alias);
            return this;
        }

        /**
         * 支付金额信息
         * @param alias 别名
         */
        public Column fundBillList(String alias) {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.fundBillList, alias);
            return this;
        }

        /**
         * 优惠券信息
         */
        public Column voucherDetailList() {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.voucherDetailList, PayAlipayComputerWebsiteFlowModel.voucherDetailList_alias);
            return this;
        }

        /**
         * 优惠券信息
         * @param alias 别名
         */
        public Column voucherDetailList(String alias) {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.voucherDetailList, alias);
            return this;
        }

        /**
         * 回传参数
         */
        public Column passbackParams() {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.passbackParams, PayAlipayComputerWebsiteFlowModel.passbackParams_alias);
            return this;
        }

        /**
         * 回传参数
         * @param alias 别名
         */
        public Column passbackParams(String alias) {
            this.addColumnAlias(PayAlipayComputerWebsiteFlowModel.passbackParams, alias);
            return this;
        }

    }

    @Override
    public On getOnModel() {
        return new On();
    }

    public static final class On extends OnModel<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> {

        private On() {
        }

        /**
         * 主键
         */
        public OnBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.onBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.primaryKeyName);
        }
    
        /**
         * 主键ID
         */
        public OnBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> id() {
            return this.onBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.id);
        }

        /**
         * 通知时间
         */
        public OnBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> notifyTime() {
            return this.onBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.notifyTime);
        }

        /**
         * 通知类型
         */
        public OnBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> notifyType() {
            return this.onBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.notifyType);
        }

        /**
         * 通知校验ID
         */
        public OnBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> notifyId() {
            return this.onBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.notifyId);
        }

        /**
         * 编码格式
         */
        public OnBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> charset() {
            return this.onBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.charset);
        }

        /**
         * 接口版本
         */
        public OnBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> version() {
            return this.onBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.version);
        }

        /**
         * 签名类型
         */
        public OnBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> signType() {
            return this.onBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.signType);
        }

        /**
         * 签名
         */
        public OnBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> sign() {
            return this.onBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.sign);
        }

        /**
         * 授权方的app_id
         */
        public OnBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> authAppId() {
            return this.onBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.authAppId);
        }

        /**
         * 支付宝交易号
         */
        public OnBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> tradeNo() {
            return this.onBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.tradeNo);
        }

        /**
         * 开发者的app_id
         */
        public OnBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> appId() {
            return this.onBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.appId);
        }

        /**
         * 商户订单号
         */
        public OnBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> outTradeNo() {
            return this.onBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.outTradeNo);
        }

        /**
         * 商户业务号
         */
        public OnBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> outBizNo() {
            return this.onBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.outBizNo);
        }

        /**
         * 买家支付宝用户号
         */
        public OnBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> buyerId() {
            return this.onBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.buyerId);
        }

        /**
         * 卖家支付宝用户号
         */
        public OnBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> sellerId() {
            return this.onBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.sellerId);
        }

        /**
         * 交易状态
         */
        public OnBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> tradeStatus() {
            return this.onBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.tradeStatus);
        }

        /**
         * 订单金额
         */
        public OnBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> totalAmount() {
            return this.onBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.totalAmount);
        }

        /**
         * 实收金额
         */
        public OnBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> receiptAmount() {
            return this.onBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.receiptAmount);
        }

        /**
         * 开票金额
         */
        public OnBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> invoiceAmount() {
            return this.onBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.invoiceAmount);
        }

        /**
         * 付款金额
         */
        public OnBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> buyerPayAmount() {
            return this.onBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.buyerPayAmount);
        }

        /**
         * 集分宝金额
         */
        public OnBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> pointAmount() {
            return this.onBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.pointAmount);
        }

        /**
         * 总退款金额
         */
        public OnBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> refundFee() {
            return this.onBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.refundFee);
        }

        /**
         * 订单标题
         */
        public OnBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> subject() {
            return this.onBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.subject);
        }

        /**
         * 商品描述
         */
        public OnBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> body() {
            return this.onBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.body);
        }

        /**
         * 交易创建时间
         */
        public OnBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> gmtCreate() {
            return this.onBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.gmtCreate);
        }

        /**
         * 交易付款时间
         */
        public OnBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> gmtPayment() {
            return this.onBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.gmtPayment);
        }

        /**
         * 交易退款时间
         */
        public OnBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> gmtRefund() {
            return this.onBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.gmtRefund);
        }

        /**
         * 交易结束时间
         */
        public OnBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> gmtClose() {
            return this.onBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.gmtClose);
        }

        /**
         * 支付金额信息
         */
        public OnBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> fundBillList() {
            return this.onBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.fundBillList);
        }

        /**
         * 优惠券信息
         */
        public OnBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> voucherDetailList() {
            return this.onBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.voucherDetailList);
        }

        /**
         * 回传参数
         */
        public OnBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> passbackParams() {
            return this.onBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.passbackParams);
        }

    }

    @Override
    public Where getWhereModel() {
        return new Where();
    }

    public static final class Where extends WhereModel<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> {

        private Where() {
        }

        /**
         * 主键
         */
        public WhereBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.whereBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.primaryKeyName);
        }
    
        /**
         * 主键ID
         */
        public WhereBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> id() {
            return this.whereBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.id);
        }

        /**
         * 通知时间
         */
        public WhereBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> notifyTime() {
            return this.whereBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.notifyTime);
        }

        /**
         * 通知类型
         */
        public WhereBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> notifyType() {
            return this.whereBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.notifyType);
        }

        /**
         * 通知校验ID
         */
        public WhereBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> notifyId() {
            return this.whereBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.notifyId);
        }

        /**
         * 编码格式
         */
        public WhereBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> charset() {
            return this.whereBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.charset);
        }

        /**
         * 接口版本
         */
        public WhereBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> version() {
            return this.whereBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.version);
        }

        /**
         * 签名类型
         */
        public WhereBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> signType() {
            return this.whereBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.signType);
        }

        /**
         * 签名
         */
        public WhereBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> sign() {
            return this.whereBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.sign);
        }

        /**
         * 授权方的app_id
         */
        public WhereBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> authAppId() {
            return this.whereBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.authAppId);
        }

        /**
         * 支付宝交易号
         */
        public WhereBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> tradeNo() {
            return this.whereBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.tradeNo);
        }

        /**
         * 开发者的app_id
         */
        public WhereBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> appId() {
            return this.whereBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.appId);
        }

        /**
         * 商户订单号
         */
        public WhereBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> outTradeNo() {
            return this.whereBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.outTradeNo);
        }

        /**
         * 商户业务号
         */
        public WhereBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> outBizNo() {
            return this.whereBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.outBizNo);
        }

        /**
         * 买家支付宝用户号
         */
        public WhereBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> buyerId() {
            return this.whereBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.buyerId);
        }

        /**
         * 卖家支付宝用户号
         */
        public WhereBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> sellerId() {
            return this.whereBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.sellerId);
        }

        /**
         * 交易状态
         */
        public WhereBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> tradeStatus() {
            return this.whereBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.tradeStatus);
        }

        /**
         * 订单金额
         */
        public WhereBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> totalAmount() {
            return this.whereBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.totalAmount);
        }

        /**
         * 实收金额
         */
        public WhereBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> receiptAmount() {
            return this.whereBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.receiptAmount);
        }

        /**
         * 开票金额
         */
        public WhereBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> invoiceAmount() {
            return this.whereBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.invoiceAmount);
        }

        /**
         * 付款金额
         */
        public WhereBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> buyerPayAmount() {
            return this.whereBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.buyerPayAmount);
        }

        /**
         * 集分宝金额
         */
        public WhereBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> pointAmount() {
            return this.whereBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.pointAmount);
        }

        /**
         * 总退款金额
         */
        public WhereBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> refundFee() {
            return this.whereBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.refundFee);
        }

        /**
         * 订单标题
         */
        public WhereBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> subject() {
            return this.whereBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.subject);
        }

        /**
         * 商品描述
         */
        public WhereBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> body() {
            return this.whereBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.body);
        }

        /**
         * 交易创建时间
         */
        public WhereBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> gmtCreate() {
            return this.whereBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.gmtCreate);
        }

        /**
         * 交易付款时间
         */
        public WhereBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> gmtPayment() {
            return this.whereBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.gmtPayment);
        }

        /**
         * 交易退款时间
         */
        public WhereBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> gmtRefund() {
            return this.whereBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.gmtRefund);
        }

        /**
         * 交易结束时间
         */
        public WhereBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> gmtClose() {
            return this.whereBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.gmtClose);
        }

        /**
         * 支付金额信息
         */
        public WhereBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> fundBillList() {
            return this.whereBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.fundBillList);
        }

        /**
         * 优惠券信息
         */
        public WhereBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> voucherDetailList() {
            return this.whereBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.voucherDetailList);
        }

        /**
         * 回传参数
         */
        public WhereBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> passbackParams() {
            return this.whereBuilder.handler(PayAlipayComputerWebsiteFlowModel.tableName, PayAlipayComputerWebsiteFlowModel.tableAlias, PayAlipayComputerWebsiteFlowModel.passbackParams);
        }

    }

    @Override
    public Group getGroupModel() {
        return new Group();
    }

    public static final class Group extends GroupModel<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> {

        private Group() {
        }

        /**
         * 主键
         */
        public Group primaryKey() {
            this.addColumn(PayAlipayComputerWebsiteFlowModel.primaryKeyName);
            return this;
        }
    
        /**
         * 主键ID
         */
        public Group id() {
            this.addColumn(PayAlipayComputerWebsiteFlowModel.id);
            return this;
        }

        /**
         * 通知时间
         */
        public Group notifyTime() {
            this.addColumn(PayAlipayComputerWebsiteFlowModel.notifyTime);
            return this;
        }

        /**
         * 通知类型
         */
        public Group notifyType() {
            this.addColumn(PayAlipayComputerWebsiteFlowModel.notifyType);
            return this;
        }

        /**
         * 通知校验ID
         */
        public Group notifyId() {
            this.addColumn(PayAlipayComputerWebsiteFlowModel.notifyId);
            return this;
        }

        /**
         * 编码格式
         */
        public Group charset() {
            this.addColumn(PayAlipayComputerWebsiteFlowModel.charset);
            return this;
        }

        /**
         * 接口版本
         */
        public Group version() {
            this.addColumn(PayAlipayComputerWebsiteFlowModel.version);
            return this;
        }

        /**
         * 签名类型
         */
        public Group signType() {
            this.addColumn(PayAlipayComputerWebsiteFlowModel.signType);
            return this;
        }

        /**
         * 签名
         */
        public Group sign() {
            this.addColumn(PayAlipayComputerWebsiteFlowModel.sign);
            return this;
        }

        /**
         * 授权方的app_id
         */
        public Group authAppId() {
            this.addColumn(PayAlipayComputerWebsiteFlowModel.authAppId);
            return this;
        }

        /**
         * 支付宝交易号
         */
        public Group tradeNo() {
            this.addColumn(PayAlipayComputerWebsiteFlowModel.tradeNo);
            return this;
        }

        /**
         * 开发者的app_id
         */
        public Group appId() {
            this.addColumn(PayAlipayComputerWebsiteFlowModel.appId);
            return this;
        }

        /**
         * 商户订单号
         */
        public Group outTradeNo() {
            this.addColumn(PayAlipayComputerWebsiteFlowModel.outTradeNo);
            return this;
        }

        /**
         * 商户业务号
         */
        public Group outBizNo() {
            this.addColumn(PayAlipayComputerWebsiteFlowModel.outBizNo);
            return this;
        }

        /**
         * 买家支付宝用户号
         */
        public Group buyerId() {
            this.addColumn(PayAlipayComputerWebsiteFlowModel.buyerId);
            return this;
        }

        /**
         * 卖家支付宝用户号
         */
        public Group sellerId() {
            this.addColumn(PayAlipayComputerWebsiteFlowModel.sellerId);
            return this;
        }

        /**
         * 交易状态
         */
        public Group tradeStatus() {
            this.addColumn(PayAlipayComputerWebsiteFlowModel.tradeStatus);
            return this;
        }

        /**
         * 订单金额
         */
        public Group totalAmount() {
            this.addColumn(PayAlipayComputerWebsiteFlowModel.totalAmount);
            return this;
        }

        /**
         * 实收金额
         */
        public Group receiptAmount() {
            this.addColumn(PayAlipayComputerWebsiteFlowModel.receiptAmount);
            return this;
        }

        /**
         * 开票金额
         */
        public Group invoiceAmount() {
            this.addColumn(PayAlipayComputerWebsiteFlowModel.invoiceAmount);
            return this;
        }

        /**
         * 付款金额
         */
        public Group buyerPayAmount() {
            this.addColumn(PayAlipayComputerWebsiteFlowModel.buyerPayAmount);
            return this;
        }

        /**
         * 集分宝金额
         */
        public Group pointAmount() {
            this.addColumn(PayAlipayComputerWebsiteFlowModel.pointAmount);
            return this;
        }

        /**
         * 总退款金额
         */
        public Group refundFee() {
            this.addColumn(PayAlipayComputerWebsiteFlowModel.refundFee);
            return this;
        }

        /**
         * 订单标题
         */
        public Group subject() {
            this.addColumn(PayAlipayComputerWebsiteFlowModel.subject);
            return this;
        }

        /**
         * 商品描述
         */
        public Group body() {
            this.addColumn(PayAlipayComputerWebsiteFlowModel.body);
            return this;
        }

        /**
         * 交易创建时间
         */
        public Group gmtCreate() {
            this.addColumn(PayAlipayComputerWebsiteFlowModel.gmtCreate);
            return this;
        }

        /**
         * 交易付款时间
         */
        public Group gmtPayment() {
            this.addColumn(PayAlipayComputerWebsiteFlowModel.gmtPayment);
            return this;
        }

        /**
         * 交易退款时间
         */
        public Group gmtRefund() {
            this.addColumn(PayAlipayComputerWebsiteFlowModel.gmtRefund);
            return this;
        }

        /**
         * 交易结束时间
         */
        public Group gmtClose() {
            this.addColumn(PayAlipayComputerWebsiteFlowModel.gmtClose);
            return this;
        }

        /**
         * 支付金额信息
         */
        public Group fundBillList() {
            this.addColumn(PayAlipayComputerWebsiteFlowModel.fundBillList);
            return this;
        }

        /**
         * 优惠券信息
         */
        public Group voucherDetailList() {
            this.addColumn(PayAlipayComputerWebsiteFlowModel.voucherDetailList);
            return this;
        }

        /**
         * 回传参数
         */
        public Group passbackParams() {
            this.addColumn(PayAlipayComputerWebsiteFlowModel.passbackParams);
            return this;
        }

    }

    @Override
    public Sort getSortModel() {
        return new Sort();
    }

    public static final class Sort extends SortModel<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> {

        private Sort() {
        }

        /**
         * 主键
         */
        public SortBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.sortBuilder.handler(PayAlipayComputerWebsiteFlowModel.primaryKeyName);
        }
    
        /**
         * 主键ID
         */
        public SortBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> id() {
            return this.sortBuilder.handler(PayAlipayComputerWebsiteFlowModel.id);
        }

        /**
         * 通知时间
         */
        public SortBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> notifyTime() {
            return this.sortBuilder.handler(PayAlipayComputerWebsiteFlowModel.notifyTime);
        }

        /**
         * 通知类型
         */
        public SortBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> notifyType() {
            return this.sortBuilder.handler(PayAlipayComputerWebsiteFlowModel.notifyType);
        }

        /**
         * 通知校验ID
         */
        public SortBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> notifyId() {
            return this.sortBuilder.handler(PayAlipayComputerWebsiteFlowModel.notifyId);
        }

        /**
         * 编码格式
         */
        public SortBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> charset() {
            return this.sortBuilder.handler(PayAlipayComputerWebsiteFlowModel.charset);
        }

        /**
         * 接口版本
         */
        public SortBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> version() {
            return this.sortBuilder.handler(PayAlipayComputerWebsiteFlowModel.version);
        }

        /**
         * 签名类型
         */
        public SortBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> signType() {
            return this.sortBuilder.handler(PayAlipayComputerWebsiteFlowModel.signType);
        }

        /**
         * 签名
         */
        public SortBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> sign() {
            return this.sortBuilder.handler(PayAlipayComputerWebsiteFlowModel.sign);
        }

        /**
         * 授权方的app_id
         */
        public SortBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> authAppId() {
            return this.sortBuilder.handler(PayAlipayComputerWebsiteFlowModel.authAppId);
        }

        /**
         * 支付宝交易号
         */
        public SortBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> tradeNo() {
            return this.sortBuilder.handler(PayAlipayComputerWebsiteFlowModel.tradeNo);
        }

        /**
         * 开发者的app_id
         */
        public SortBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> appId() {
            return this.sortBuilder.handler(PayAlipayComputerWebsiteFlowModel.appId);
        }

        /**
         * 商户订单号
         */
        public SortBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> outTradeNo() {
            return this.sortBuilder.handler(PayAlipayComputerWebsiteFlowModel.outTradeNo);
        }

        /**
         * 商户业务号
         */
        public SortBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> outBizNo() {
            return this.sortBuilder.handler(PayAlipayComputerWebsiteFlowModel.outBizNo);
        }

        /**
         * 买家支付宝用户号
         */
        public SortBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> buyerId() {
            return this.sortBuilder.handler(PayAlipayComputerWebsiteFlowModel.buyerId);
        }

        /**
         * 卖家支付宝用户号
         */
        public SortBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> sellerId() {
            return this.sortBuilder.handler(PayAlipayComputerWebsiteFlowModel.sellerId);
        }

        /**
         * 交易状态
         */
        public SortBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> tradeStatus() {
            return this.sortBuilder.handler(PayAlipayComputerWebsiteFlowModel.tradeStatus);
        }

        /**
         * 订单金额
         */
        public SortBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> totalAmount() {
            return this.sortBuilder.handler(PayAlipayComputerWebsiteFlowModel.totalAmount);
        }

        /**
         * 实收金额
         */
        public SortBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> receiptAmount() {
            return this.sortBuilder.handler(PayAlipayComputerWebsiteFlowModel.receiptAmount);
        }

        /**
         * 开票金额
         */
        public SortBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> invoiceAmount() {
            return this.sortBuilder.handler(PayAlipayComputerWebsiteFlowModel.invoiceAmount);
        }

        /**
         * 付款金额
         */
        public SortBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> buyerPayAmount() {
            return this.sortBuilder.handler(PayAlipayComputerWebsiteFlowModel.buyerPayAmount);
        }

        /**
         * 集分宝金额
         */
        public SortBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> pointAmount() {
            return this.sortBuilder.handler(PayAlipayComputerWebsiteFlowModel.pointAmount);
        }

        /**
         * 总退款金额
         */
        public SortBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> refundFee() {
            return this.sortBuilder.handler(PayAlipayComputerWebsiteFlowModel.refundFee);
        }

        /**
         * 订单标题
         */
        public SortBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> subject() {
            return this.sortBuilder.handler(PayAlipayComputerWebsiteFlowModel.subject);
        }

        /**
         * 商品描述
         */
        public SortBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> body() {
            return this.sortBuilder.handler(PayAlipayComputerWebsiteFlowModel.body);
        }

        /**
         * 交易创建时间
         */
        public SortBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> gmtCreate() {
            return this.sortBuilder.handler(PayAlipayComputerWebsiteFlowModel.gmtCreate);
        }

        /**
         * 交易付款时间
         */
        public SortBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> gmtPayment() {
            return this.sortBuilder.handler(PayAlipayComputerWebsiteFlowModel.gmtPayment);
        }

        /**
         * 交易退款时间
         */
        public SortBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> gmtRefund() {
            return this.sortBuilder.handler(PayAlipayComputerWebsiteFlowModel.gmtRefund);
        }

        /**
         * 交易结束时间
         */
        public SortBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> gmtClose() {
            return this.sortBuilder.handler(PayAlipayComputerWebsiteFlowModel.gmtClose);
        }

        /**
         * 支付金额信息
         */
        public SortBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> fundBillList() {
            return this.sortBuilder.handler(PayAlipayComputerWebsiteFlowModel.fundBillList);
        }

        /**
         * 优惠券信息
         */
        public SortBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> voucherDetailList() {
            return this.sortBuilder.handler(PayAlipayComputerWebsiteFlowModel.voucherDetailList);
        }

        /**
         * 回传参数
         */
        public SortBuilder<PayAlipayComputerWebsiteFlowModel, Column, On, Where, Sort, Group> passbackParams() {
            return this.sortBuilder.handler(PayAlipayComputerWebsiteFlowModel.passbackParams);
        }

    }

}