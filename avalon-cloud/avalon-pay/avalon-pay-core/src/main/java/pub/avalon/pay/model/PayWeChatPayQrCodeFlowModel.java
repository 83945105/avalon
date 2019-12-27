package pub.avalon.pay.model;

import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.norm.Model;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("all")
public final class PayWeChatPayQrCodeFlowModel implements Model<PayWeChatPayQrCodeFlowModel, PayWeChatPayQrCodeFlowModel.Column, PayWeChatPayQrCodeFlowModel.On, PayWeChatPayQrCodeFlowModel.Where, PayWeChatPayQrCodeFlowModel.Sort, PayWeChatPayQrCodeFlowModel.Group> {

    /**
     * 表名
     */
    public static final String tableName = "pay_we_chat_pay_qr_code_flow";
    /**
     * 表别名
     */
    public static final String tableAlias = "PayWeChatPayQrCodeFlow";

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
     * 返回状态码
     */
    public static final String returnCode = "return_code";
    /**
     * 返回状态码
     */
    public static final String returnCode_alias = "returnCode";
    /**
     * 返回信息
     */
    public static final String returnMsg = "return_msg";
    /**
     * 返回信息
     */
    public static final String returnMsg_alias = "returnMsg";
    /**
     * 公众账号ID
     */
    public static final String appid = "appid";
    /**
     * 公众账号ID
     */
    public static final String appid_alias = "appid";
    /**
     * 商户号
     */
    public static final String mchId = "mch_id";
    /**
     * 商户号
     */
    public static final String mchId_alias = "mchId";
    /**
     * 设备号
     */
    public static final String deviceInfo = "device_info";
    /**
     * 设备号
     */
    public static final String deviceInfo_alias = "deviceInfo";
    /**
     * 随机字符串
     */
    public static final String nonceStr = "nonce_str";
    /**
     * 随机字符串
     */
    public static final String nonceStr_alias = "nonceStr";
    /**
     * 签名
     */
    public static final String sign = "sign";
    /**
     * 签名
     */
    public static final String sign_alias = "sign";
    /**
     * 签名类型
     */
    public static final String signType = "sign_type";
    /**
     * 签名类型
     */
    public static final String signType_alias = "signType";
    /**
     * 业务结果
     */
    public static final String resultCode = "result_code";
    /**
     * 业务结果
     */
    public static final String resultCode_alias = "resultCode";
    /**
     * 错误代码
     */
    public static final String errCode = "err_code";
    /**
     * 错误代码
     */
    public static final String errCode_alias = "errCode";
    /**
     * 错误代码描述
     */
    public static final String errCodeDes = "err_code_des";
    /**
     * 错误代码描述
     */
    public static final String errCodeDes_alias = "errCodeDes";
    /**
     * 用户标识
     */
    public static final String openid = "openid";
    /**
     * 用户标识
     */
    public static final String openid_alias = "openid";
    /**
     * 是否关注公众账号
     */
    public static final String isSubscribe = "is_subscribe";
    /**
     * 是否关注公众账号
     */
    public static final String isSubscribe_alias = "isSubscribe";
    /**
     * 交易类型
     */
    public static final String tradeType = "trade_type";
    /**
     * 交易类型
     */
    public static final String tradeType_alias = "tradeType";
    /**
     * 付款银行
     */
    public static final String bankType = "bank_type";
    /**
     * 付款银行
     */
    public static final String bankType_alias = "bankType";
    /**
     * 订单金额
     */
    public static final String totalFee = "total_fee";
    /**
     * 订单金额
     */
    public static final String totalFee_alias = "totalFee";
    /**
     * 应结订单金额
     */
    public static final String settlementTotalFee = "settlement_total_fee";
    /**
     * 应结订单金额
     */
    public static final String settlementTotalFee_alias = "settlementTotalFee";
    /**
     * 货币种类
     */
    public static final String feeType = "fee_type";
    /**
     * 货币种类
     */
    public static final String feeType_alias = "feeType";
    /**
     * 现金支付金额
     */
    public static final String cashFee = "cash_fee";
    /**
     * 现金支付金额
     */
    public static final String cashFee_alias = "cashFee";
    /**
     * 现金支付货币类型
     */
    public static final String cashFeeType = "cash_fee_type";
    /**
     * 现金支付货币类型
     */
    public static final String cashFeeType_alias = "cashFeeType";
    /**
     * 总代金券金额
     */
    public static final String couponFee = "coupon_fee";
    /**
     * 总代金券金额
     */
    public static final String couponFee_alias = "couponFee";
    /**
     * 代金券使用数量
     */
    public static final String couponCount = "coupon_count";
    /**
     * 代金券使用数量
     */
    public static final String couponCount_alias = "couponCount";
    /**
     * 代金券类型
     */
    public static final String couponTypeN = "coupon_type_n";
    /**
     * 代金券类型
     */
    public static final String couponTypeN_alias = "couponTypeN";
    /**
     * 代金券ID
     */
    public static final String couponIdN = "coupon_id_n";
    /**
     * 代金券ID
     */
    public static final String couponIdN_alias = "couponIdN";
    /**
     * 单个代金券支付金额
     */
    public static final String couponFeeN = "coupon_fee_n";
    /**
     * 单个代金券支付金额
     */
    public static final String couponFeeN_alias = "couponFeeN";
    /**
     * 微信支付订单号
     */
    public static final String transactionId = "transaction_id";
    /**
     * 微信支付订单号
     */
    public static final String transactionId_alias = "transactionId";
    /**
     * 商户订单号
     */
    public static final String outTradeNo = "out_trade_no";
    /**
     * 商户订单号
     */
    public static final String outTradeNo_alias = "outTradeNo";
    /**
     * 商家数据包
     */
    public static final String attach = "attach";
    /**
     * 商家数据包
     */
    public static final String attach_alias = "attach";
    /**
     * 支付完成时间
     */
    public static final String timeEnd = "time_end";
    /**
     * 支付完成时间
     */
    public static final String timeEnd_alias = "timeEnd";

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
        COLUMN_ALIAS_MAP.put(returnCode, returnCode_alias);
        ALIAS_COLUMN_MAP.put(returnCode_alias, returnCode);
        COLUMN_ALIAS_MAP.put(returnMsg, returnMsg_alias);
        ALIAS_COLUMN_MAP.put(returnMsg_alias, returnMsg);
        COLUMN_ALIAS_MAP.put(appid, appid_alias);
        ALIAS_COLUMN_MAP.put(appid_alias, appid);
        COLUMN_ALIAS_MAP.put(mchId, mchId_alias);
        ALIAS_COLUMN_MAP.put(mchId_alias, mchId);
        COLUMN_ALIAS_MAP.put(deviceInfo, deviceInfo_alias);
        ALIAS_COLUMN_MAP.put(deviceInfo_alias, deviceInfo);
        COLUMN_ALIAS_MAP.put(nonceStr, nonceStr_alias);
        ALIAS_COLUMN_MAP.put(nonceStr_alias, nonceStr);
        COLUMN_ALIAS_MAP.put(sign, sign_alias);
        ALIAS_COLUMN_MAP.put(sign_alias, sign);
        COLUMN_ALIAS_MAP.put(signType, signType_alias);
        ALIAS_COLUMN_MAP.put(signType_alias, signType);
        COLUMN_ALIAS_MAP.put(resultCode, resultCode_alias);
        ALIAS_COLUMN_MAP.put(resultCode_alias, resultCode);
        COLUMN_ALIAS_MAP.put(errCode, errCode_alias);
        ALIAS_COLUMN_MAP.put(errCode_alias, errCode);
        COLUMN_ALIAS_MAP.put(errCodeDes, errCodeDes_alias);
        ALIAS_COLUMN_MAP.put(errCodeDes_alias, errCodeDes);
        COLUMN_ALIAS_MAP.put(openid, openid_alias);
        ALIAS_COLUMN_MAP.put(openid_alias, openid);
        COLUMN_ALIAS_MAP.put(isSubscribe, isSubscribe_alias);
        ALIAS_COLUMN_MAP.put(isSubscribe_alias, isSubscribe);
        COLUMN_ALIAS_MAP.put(tradeType, tradeType_alias);
        ALIAS_COLUMN_MAP.put(tradeType_alias, tradeType);
        COLUMN_ALIAS_MAP.put(bankType, bankType_alias);
        ALIAS_COLUMN_MAP.put(bankType_alias, bankType);
        COLUMN_ALIAS_MAP.put(totalFee, totalFee_alias);
        ALIAS_COLUMN_MAP.put(totalFee_alias, totalFee);
        COLUMN_ALIAS_MAP.put(settlementTotalFee, settlementTotalFee_alias);
        ALIAS_COLUMN_MAP.put(settlementTotalFee_alias, settlementTotalFee);
        COLUMN_ALIAS_MAP.put(feeType, feeType_alias);
        ALIAS_COLUMN_MAP.put(feeType_alias, feeType);
        COLUMN_ALIAS_MAP.put(cashFee, cashFee_alias);
        ALIAS_COLUMN_MAP.put(cashFee_alias, cashFee);
        COLUMN_ALIAS_MAP.put(cashFeeType, cashFeeType_alias);
        ALIAS_COLUMN_MAP.put(cashFeeType_alias, cashFeeType);
        COLUMN_ALIAS_MAP.put(couponFee, couponFee_alias);
        ALIAS_COLUMN_MAP.put(couponFee_alias, couponFee);
        COLUMN_ALIAS_MAP.put(couponCount, couponCount_alias);
        ALIAS_COLUMN_MAP.put(couponCount_alias, couponCount);
        COLUMN_ALIAS_MAP.put(couponTypeN, couponTypeN_alias);
        ALIAS_COLUMN_MAP.put(couponTypeN_alias, couponTypeN);
        COLUMN_ALIAS_MAP.put(couponIdN, couponIdN_alias);
        ALIAS_COLUMN_MAP.put(couponIdN_alias, couponIdN);
        COLUMN_ALIAS_MAP.put(couponFeeN, couponFeeN_alias);
        ALIAS_COLUMN_MAP.put(couponFeeN_alias, couponFeeN);
        COLUMN_ALIAS_MAP.put(transactionId, transactionId_alias);
        ALIAS_COLUMN_MAP.put(transactionId_alias, transactionId);
        COLUMN_ALIAS_MAP.put(outTradeNo, outTradeNo_alias);
        ALIAS_COLUMN_MAP.put(outTradeNo_alias, outTradeNo);
        COLUMN_ALIAS_MAP.put(attach, attach_alias);
        ALIAS_COLUMN_MAP.put(attach_alias, attach);
        COLUMN_ALIAS_MAP.put(timeEnd, timeEnd_alias);
        ALIAS_COLUMN_MAP.put(timeEnd_alias, timeEnd);
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

    public static final class Column extends ColumnModel<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> {

        private Column() {
        }

        /**
         * 主键
         */
        public Column primaryKey() {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.primaryKeyName, PayWeChatPayQrCodeFlowModel.primaryKeyAlias);
            return this;
        }

        /**
         * 主键
         * @param alias 别名
         */
        public Column primaryKey(String alias) {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.primaryKeyName, alias);
            return this;
        }
    
        /**
         * 主键ID
         */
        public Column id() {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.id, PayWeChatPayQrCodeFlowModel.id_alias);
            return this;
        }

        /**
         * 主键ID
         * @param alias 别名
         */
        public Column id(String alias) {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.id, alias);
            return this;
        }

        /**
         * 返回状态码
         */
        public Column returnCode() {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.returnCode, PayWeChatPayQrCodeFlowModel.returnCode_alias);
            return this;
        }

        /**
         * 返回状态码
         * @param alias 别名
         */
        public Column returnCode(String alias) {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.returnCode, alias);
            return this;
        }

        /**
         * 返回信息
         */
        public Column returnMsg() {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.returnMsg, PayWeChatPayQrCodeFlowModel.returnMsg_alias);
            return this;
        }

        /**
         * 返回信息
         * @param alias 别名
         */
        public Column returnMsg(String alias) {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.returnMsg, alias);
            return this;
        }

        /**
         * 公众账号ID
         */
        public Column appid() {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.appid, PayWeChatPayQrCodeFlowModel.appid_alias);
            return this;
        }

        /**
         * 公众账号ID
         * @param alias 别名
         */
        public Column appid(String alias) {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.appid, alias);
            return this;
        }

        /**
         * 商户号
         */
        public Column mchId() {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.mchId, PayWeChatPayQrCodeFlowModel.mchId_alias);
            return this;
        }

        /**
         * 商户号
         * @param alias 别名
         */
        public Column mchId(String alias) {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.mchId, alias);
            return this;
        }

        /**
         * 设备号
         */
        public Column deviceInfo() {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.deviceInfo, PayWeChatPayQrCodeFlowModel.deviceInfo_alias);
            return this;
        }

        /**
         * 设备号
         * @param alias 别名
         */
        public Column deviceInfo(String alias) {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.deviceInfo, alias);
            return this;
        }

        /**
         * 随机字符串
         */
        public Column nonceStr() {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.nonceStr, PayWeChatPayQrCodeFlowModel.nonceStr_alias);
            return this;
        }

        /**
         * 随机字符串
         * @param alias 别名
         */
        public Column nonceStr(String alias) {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.nonceStr, alias);
            return this;
        }

        /**
         * 签名
         */
        public Column sign() {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.sign, PayWeChatPayQrCodeFlowModel.sign_alias);
            return this;
        }

        /**
         * 签名
         * @param alias 别名
         */
        public Column sign(String alias) {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.sign, alias);
            return this;
        }

        /**
         * 签名类型
         */
        public Column signType() {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.signType, PayWeChatPayQrCodeFlowModel.signType_alias);
            return this;
        }

        /**
         * 签名类型
         * @param alias 别名
         */
        public Column signType(String alias) {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.signType, alias);
            return this;
        }

        /**
         * 业务结果
         */
        public Column resultCode() {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.resultCode, PayWeChatPayQrCodeFlowModel.resultCode_alias);
            return this;
        }

        /**
         * 业务结果
         * @param alias 别名
         */
        public Column resultCode(String alias) {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.resultCode, alias);
            return this;
        }

        /**
         * 错误代码
         */
        public Column errCode() {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.errCode, PayWeChatPayQrCodeFlowModel.errCode_alias);
            return this;
        }

        /**
         * 错误代码
         * @param alias 别名
         */
        public Column errCode(String alias) {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.errCode, alias);
            return this;
        }

        /**
         * 错误代码描述
         */
        public Column errCodeDes() {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.errCodeDes, PayWeChatPayQrCodeFlowModel.errCodeDes_alias);
            return this;
        }

        /**
         * 错误代码描述
         * @param alias 别名
         */
        public Column errCodeDes(String alias) {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.errCodeDes, alias);
            return this;
        }

        /**
         * 用户标识
         */
        public Column openid() {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.openid, PayWeChatPayQrCodeFlowModel.openid_alias);
            return this;
        }

        /**
         * 用户标识
         * @param alias 别名
         */
        public Column openid(String alias) {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.openid, alias);
            return this;
        }

        /**
         * 是否关注公众账号
         */
        public Column isSubscribe() {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.isSubscribe, PayWeChatPayQrCodeFlowModel.isSubscribe_alias);
            return this;
        }

        /**
         * 是否关注公众账号
         * @param alias 别名
         */
        public Column isSubscribe(String alias) {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.isSubscribe, alias);
            return this;
        }

        /**
         * 交易类型
         */
        public Column tradeType() {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.tradeType, PayWeChatPayQrCodeFlowModel.tradeType_alias);
            return this;
        }

        /**
         * 交易类型
         * @param alias 别名
         */
        public Column tradeType(String alias) {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.tradeType, alias);
            return this;
        }

        /**
         * 付款银行
         */
        public Column bankType() {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.bankType, PayWeChatPayQrCodeFlowModel.bankType_alias);
            return this;
        }

        /**
         * 付款银行
         * @param alias 别名
         */
        public Column bankType(String alias) {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.bankType, alias);
            return this;
        }

        /**
         * 订单金额
         */
        public Column totalFee() {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.totalFee, PayWeChatPayQrCodeFlowModel.totalFee_alias);
            return this;
        }

        /**
         * 订单金额
         * @param alias 别名
         */
        public Column totalFee(String alias) {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.totalFee, alias);
            return this;
        }

        /**
         * 应结订单金额
         */
        public Column settlementTotalFee() {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.settlementTotalFee, PayWeChatPayQrCodeFlowModel.settlementTotalFee_alias);
            return this;
        }

        /**
         * 应结订单金额
         * @param alias 别名
         */
        public Column settlementTotalFee(String alias) {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.settlementTotalFee, alias);
            return this;
        }

        /**
         * 货币种类
         */
        public Column feeType() {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.feeType, PayWeChatPayQrCodeFlowModel.feeType_alias);
            return this;
        }

        /**
         * 货币种类
         * @param alias 别名
         */
        public Column feeType(String alias) {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.feeType, alias);
            return this;
        }

        /**
         * 现金支付金额
         */
        public Column cashFee() {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.cashFee, PayWeChatPayQrCodeFlowModel.cashFee_alias);
            return this;
        }

        /**
         * 现金支付金额
         * @param alias 别名
         */
        public Column cashFee(String alias) {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.cashFee, alias);
            return this;
        }

        /**
         * 现金支付货币类型
         */
        public Column cashFeeType() {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.cashFeeType, PayWeChatPayQrCodeFlowModel.cashFeeType_alias);
            return this;
        }

        /**
         * 现金支付货币类型
         * @param alias 别名
         */
        public Column cashFeeType(String alias) {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.cashFeeType, alias);
            return this;
        }

        /**
         * 总代金券金额
         */
        public Column couponFee() {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.couponFee, PayWeChatPayQrCodeFlowModel.couponFee_alias);
            return this;
        }

        /**
         * 总代金券金额
         * @param alias 别名
         */
        public Column couponFee(String alias) {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.couponFee, alias);
            return this;
        }

        /**
         * 代金券使用数量
         */
        public Column couponCount() {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.couponCount, PayWeChatPayQrCodeFlowModel.couponCount_alias);
            return this;
        }

        /**
         * 代金券使用数量
         * @param alias 别名
         */
        public Column couponCount(String alias) {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.couponCount, alias);
            return this;
        }

        /**
         * 代金券类型
         */
        public Column couponTypeN() {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.couponTypeN, PayWeChatPayQrCodeFlowModel.couponTypeN_alias);
            return this;
        }

        /**
         * 代金券类型
         * @param alias 别名
         */
        public Column couponTypeN(String alias) {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.couponTypeN, alias);
            return this;
        }

        /**
         * 代金券ID
         */
        public Column couponIdN() {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.couponIdN, PayWeChatPayQrCodeFlowModel.couponIdN_alias);
            return this;
        }

        /**
         * 代金券ID
         * @param alias 别名
         */
        public Column couponIdN(String alias) {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.couponIdN, alias);
            return this;
        }

        /**
         * 单个代金券支付金额
         */
        public Column couponFeeN() {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.couponFeeN, PayWeChatPayQrCodeFlowModel.couponFeeN_alias);
            return this;
        }

        /**
         * 单个代金券支付金额
         * @param alias 别名
         */
        public Column couponFeeN(String alias) {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.couponFeeN, alias);
            return this;
        }

        /**
         * 微信支付订单号
         */
        public Column transactionId() {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.transactionId, PayWeChatPayQrCodeFlowModel.transactionId_alias);
            return this;
        }

        /**
         * 微信支付订单号
         * @param alias 别名
         */
        public Column transactionId(String alias) {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.transactionId, alias);
            return this;
        }

        /**
         * 商户订单号
         */
        public Column outTradeNo() {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.outTradeNo, PayWeChatPayQrCodeFlowModel.outTradeNo_alias);
            return this;
        }

        /**
         * 商户订单号
         * @param alias 别名
         */
        public Column outTradeNo(String alias) {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.outTradeNo, alias);
            return this;
        }

        /**
         * 商家数据包
         */
        public Column attach() {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.attach, PayWeChatPayQrCodeFlowModel.attach_alias);
            return this;
        }

        /**
         * 商家数据包
         * @param alias 别名
         */
        public Column attach(String alias) {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.attach, alias);
            return this;
        }

        /**
         * 支付完成时间
         */
        public Column timeEnd() {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.timeEnd, PayWeChatPayQrCodeFlowModel.timeEnd_alias);
            return this;
        }

        /**
         * 支付完成时间
         * @param alias 别名
         */
        public Column timeEnd(String alias) {
            this.addColumnAlias(PayWeChatPayQrCodeFlowModel.timeEnd, alias);
            return this;
        }

    }

    @Override
    public On getOnModel() {
        return new On();
    }

    public static final class On extends OnModel<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> {

        private On() {
        }

        /**
         * 主键
         */
        public OnBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.onBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.primaryKeyName);
        }
    
        /**
         * 主键ID
         */
        public OnBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> id() {
            return this.onBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.id);
        }

        /**
         * 返回状态码
         */
        public OnBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> returnCode() {
            return this.onBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.returnCode);
        }

        /**
         * 返回信息
         */
        public OnBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> returnMsg() {
            return this.onBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.returnMsg);
        }

        /**
         * 公众账号ID
         */
        public OnBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> appid() {
            return this.onBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.appid);
        }

        /**
         * 商户号
         */
        public OnBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> mchId() {
            return this.onBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.mchId);
        }

        /**
         * 设备号
         */
        public OnBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> deviceInfo() {
            return this.onBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.deviceInfo);
        }

        /**
         * 随机字符串
         */
        public OnBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> nonceStr() {
            return this.onBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.nonceStr);
        }

        /**
         * 签名
         */
        public OnBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> sign() {
            return this.onBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.sign);
        }

        /**
         * 签名类型
         */
        public OnBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> signType() {
            return this.onBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.signType);
        }

        /**
         * 业务结果
         */
        public OnBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> resultCode() {
            return this.onBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.resultCode);
        }

        /**
         * 错误代码
         */
        public OnBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> errCode() {
            return this.onBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.errCode);
        }

        /**
         * 错误代码描述
         */
        public OnBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> errCodeDes() {
            return this.onBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.errCodeDes);
        }

        /**
         * 用户标识
         */
        public OnBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> openid() {
            return this.onBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.openid);
        }

        /**
         * 是否关注公众账号
         */
        public OnBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> isSubscribe() {
            return this.onBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.isSubscribe);
        }

        /**
         * 交易类型
         */
        public OnBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> tradeType() {
            return this.onBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.tradeType);
        }

        /**
         * 付款银行
         */
        public OnBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> bankType() {
            return this.onBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.bankType);
        }

        /**
         * 订单金额
         */
        public OnBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> totalFee() {
            return this.onBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.totalFee);
        }

        /**
         * 应结订单金额
         */
        public OnBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> settlementTotalFee() {
            return this.onBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.settlementTotalFee);
        }

        /**
         * 货币种类
         */
        public OnBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> feeType() {
            return this.onBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.feeType);
        }

        /**
         * 现金支付金额
         */
        public OnBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> cashFee() {
            return this.onBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.cashFee);
        }

        /**
         * 现金支付货币类型
         */
        public OnBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> cashFeeType() {
            return this.onBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.cashFeeType);
        }

        /**
         * 总代金券金额
         */
        public OnBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> couponFee() {
            return this.onBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.couponFee);
        }

        /**
         * 代金券使用数量
         */
        public OnBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> couponCount() {
            return this.onBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.couponCount);
        }

        /**
         * 代金券类型
         */
        public OnBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> couponTypeN() {
            return this.onBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.couponTypeN);
        }

        /**
         * 代金券ID
         */
        public OnBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> couponIdN() {
            return this.onBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.couponIdN);
        }

        /**
         * 单个代金券支付金额
         */
        public OnBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> couponFeeN() {
            return this.onBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.couponFeeN);
        }

        /**
         * 微信支付订单号
         */
        public OnBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> transactionId() {
            return this.onBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.transactionId);
        }

        /**
         * 商户订单号
         */
        public OnBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> outTradeNo() {
            return this.onBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.outTradeNo);
        }

        /**
         * 商家数据包
         */
        public OnBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> attach() {
            return this.onBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.attach);
        }

        /**
         * 支付完成时间
         */
        public OnBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> timeEnd() {
            return this.onBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.timeEnd);
        }

    }

    @Override
    public Where getWhereModel() {
        return new Where();
    }

    public static final class Where extends WhereModel<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> {

        private Where() {
        }

        /**
         * 主键
         */
        public WhereBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.whereBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.primaryKeyName);
        }
    
        /**
         * 主键ID
         */
        public WhereBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> id() {
            return this.whereBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.id);
        }

        /**
         * 返回状态码
         */
        public WhereBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> returnCode() {
            return this.whereBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.returnCode);
        }

        /**
         * 返回信息
         */
        public WhereBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> returnMsg() {
            return this.whereBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.returnMsg);
        }

        /**
         * 公众账号ID
         */
        public WhereBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> appid() {
            return this.whereBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.appid);
        }

        /**
         * 商户号
         */
        public WhereBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> mchId() {
            return this.whereBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.mchId);
        }

        /**
         * 设备号
         */
        public WhereBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> deviceInfo() {
            return this.whereBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.deviceInfo);
        }

        /**
         * 随机字符串
         */
        public WhereBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> nonceStr() {
            return this.whereBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.nonceStr);
        }

        /**
         * 签名
         */
        public WhereBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> sign() {
            return this.whereBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.sign);
        }

        /**
         * 签名类型
         */
        public WhereBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> signType() {
            return this.whereBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.signType);
        }

        /**
         * 业务结果
         */
        public WhereBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> resultCode() {
            return this.whereBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.resultCode);
        }

        /**
         * 错误代码
         */
        public WhereBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> errCode() {
            return this.whereBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.errCode);
        }

        /**
         * 错误代码描述
         */
        public WhereBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> errCodeDes() {
            return this.whereBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.errCodeDes);
        }

        /**
         * 用户标识
         */
        public WhereBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> openid() {
            return this.whereBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.openid);
        }

        /**
         * 是否关注公众账号
         */
        public WhereBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> isSubscribe() {
            return this.whereBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.isSubscribe);
        }

        /**
         * 交易类型
         */
        public WhereBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> tradeType() {
            return this.whereBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.tradeType);
        }

        /**
         * 付款银行
         */
        public WhereBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> bankType() {
            return this.whereBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.bankType);
        }

        /**
         * 订单金额
         */
        public WhereBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> totalFee() {
            return this.whereBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.totalFee);
        }

        /**
         * 应结订单金额
         */
        public WhereBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> settlementTotalFee() {
            return this.whereBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.settlementTotalFee);
        }

        /**
         * 货币种类
         */
        public WhereBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> feeType() {
            return this.whereBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.feeType);
        }

        /**
         * 现金支付金额
         */
        public WhereBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> cashFee() {
            return this.whereBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.cashFee);
        }

        /**
         * 现金支付货币类型
         */
        public WhereBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> cashFeeType() {
            return this.whereBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.cashFeeType);
        }

        /**
         * 总代金券金额
         */
        public WhereBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> couponFee() {
            return this.whereBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.couponFee);
        }

        /**
         * 代金券使用数量
         */
        public WhereBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> couponCount() {
            return this.whereBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.couponCount);
        }

        /**
         * 代金券类型
         */
        public WhereBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> couponTypeN() {
            return this.whereBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.couponTypeN);
        }

        /**
         * 代金券ID
         */
        public WhereBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> couponIdN() {
            return this.whereBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.couponIdN);
        }

        /**
         * 单个代金券支付金额
         */
        public WhereBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> couponFeeN() {
            return this.whereBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.couponFeeN);
        }

        /**
         * 微信支付订单号
         */
        public WhereBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> transactionId() {
            return this.whereBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.transactionId);
        }

        /**
         * 商户订单号
         */
        public WhereBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> outTradeNo() {
            return this.whereBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.outTradeNo);
        }

        /**
         * 商家数据包
         */
        public WhereBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> attach() {
            return this.whereBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.attach);
        }

        /**
         * 支付完成时间
         */
        public WhereBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> timeEnd() {
            return this.whereBuilder.handler(PayWeChatPayQrCodeFlowModel.tableName, PayWeChatPayQrCodeFlowModel.tableAlias, PayWeChatPayQrCodeFlowModel.timeEnd);
        }

    }

    @Override
    public Group getGroupModel() {
        return new Group();
    }

    public static final class Group extends GroupModel<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> {

        private Group() {
        }

        /**
         * 主键
         */
        public Group primaryKey() {
            this.addColumn(PayWeChatPayQrCodeFlowModel.primaryKeyName);
            return this;
        }
    
        /**
         * 主键ID
         */
        public Group id() {
            this.addColumn(PayWeChatPayQrCodeFlowModel.id);
            return this;
        }

        /**
         * 返回状态码
         */
        public Group returnCode() {
            this.addColumn(PayWeChatPayQrCodeFlowModel.returnCode);
            return this;
        }

        /**
         * 返回信息
         */
        public Group returnMsg() {
            this.addColumn(PayWeChatPayQrCodeFlowModel.returnMsg);
            return this;
        }

        /**
         * 公众账号ID
         */
        public Group appid() {
            this.addColumn(PayWeChatPayQrCodeFlowModel.appid);
            return this;
        }

        /**
         * 商户号
         */
        public Group mchId() {
            this.addColumn(PayWeChatPayQrCodeFlowModel.mchId);
            return this;
        }

        /**
         * 设备号
         */
        public Group deviceInfo() {
            this.addColumn(PayWeChatPayQrCodeFlowModel.deviceInfo);
            return this;
        }

        /**
         * 随机字符串
         */
        public Group nonceStr() {
            this.addColumn(PayWeChatPayQrCodeFlowModel.nonceStr);
            return this;
        }

        /**
         * 签名
         */
        public Group sign() {
            this.addColumn(PayWeChatPayQrCodeFlowModel.sign);
            return this;
        }

        /**
         * 签名类型
         */
        public Group signType() {
            this.addColumn(PayWeChatPayQrCodeFlowModel.signType);
            return this;
        }

        /**
         * 业务结果
         */
        public Group resultCode() {
            this.addColumn(PayWeChatPayQrCodeFlowModel.resultCode);
            return this;
        }

        /**
         * 错误代码
         */
        public Group errCode() {
            this.addColumn(PayWeChatPayQrCodeFlowModel.errCode);
            return this;
        }

        /**
         * 错误代码描述
         */
        public Group errCodeDes() {
            this.addColumn(PayWeChatPayQrCodeFlowModel.errCodeDes);
            return this;
        }

        /**
         * 用户标识
         */
        public Group openid() {
            this.addColumn(PayWeChatPayQrCodeFlowModel.openid);
            return this;
        }

        /**
         * 是否关注公众账号
         */
        public Group isSubscribe() {
            this.addColumn(PayWeChatPayQrCodeFlowModel.isSubscribe);
            return this;
        }

        /**
         * 交易类型
         */
        public Group tradeType() {
            this.addColumn(PayWeChatPayQrCodeFlowModel.tradeType);
            return this;
        }

        /**
         * 付款银行
         */
        public Group bankType() {
            this.addColumn(PayWeChatPayQrCodeFlowModel.bankType);
            return this;
        }

        /**
         * 订单金额
         */
        public Group totalFee() {
            this.addColumn(PayWeChatPayQrCodeFlowModel.totalFee);
            return this;
        }

        /**
         * 应结订单金额
         */
        public Group settlementTotalFee() {
            this.addColumn(PayWeChatPayQrCodeFlowModel.settlementTotalFee);
            return this;
        }

        /**
         * 货币种类
         */
        public Group feeType() {
            this.addColumn(PayWeChatPayQrCodeFlowModel.feeType);
            return this;
        }

        /**
         * 现金支付金额
         */
        public Group cashFee() {
            this.addColumn(PayWeChatPayQrCodeFlowModel.cashFee);
            return this;
        }

        /**
         * 现金支付货币类型
         */
        public Group cashFeeType() {
            this.addColumn(PayWeChatPayQrCodeFlowModel.cashFeeType);
            return this;
        }

        /**
         * 总代金券金额
         */
        public Group couponFee() {
            this.addColumn(PayWeChatPayQrCodeFlowModel.couponFee);
            return this;
        }

        /**
         * 代金券使用数量
         */
        public Group couponCount() {
            this.addColumn(PayWeChatPayQrCodeFlowModel.couponCount);
            return this;
        }

        /**
         * 代金券类型
         */
        public Group couponTypeN() {
            this.addColumn(PayWeChatPayQrCodeFlowModel.couponTypeN);
            return this;
        }

        /**
         * 代金券ID
         */
        public Group couponIdN() {
            this.addColumn(PayWeChatPayQrCodeFlowModel.couponIdN);
            return this;
        }

        /**
         * 单个代金券支付金额
         */
        public Group couponFeeN() {
            this.addColumn(PayWeChatPayQrCodeFlowModel.couponFeeN);
            return this;
        }

        /**
         * 微信支付订单号
         */
        public Group transactionId() {
            this.addColumn(PayWeChatPayQrCodeFlowModel.transactionId);
            return this;
        }

        /**
         * 商户订单号
         */
        public Group outTradeNo() {
            this.addColumn(PayWeChatPayQrCodeFlowModel.outTradeNo);
            return this;
        }

        /**
         * 商家数据包
         */
        public Group attach() {
            this.addColumn(PayWeChatPayQrCodeFlowModel.attach);
            return this;
        }

        /**
         * 支付完成时间
         */
        public Group timeEnd() {
            this.addColumn(PayWeChatPayQrCodeFlowModel.timeEnd);
            return this;
        }

    }

    @Override
    public Sort getSortModel() {
        return new Sort();
    }

    public static final class Sort extends SortModel<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> {

        private Sort() {
        }

        /**
         * 主键
         */
        public SortBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.sortBuilder.handler(PayWeChatPayQrCodeFlowModel.primaryKeyName);
        }
    
        /**
         * 主键ID
         */
        public SortBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> id() {
            return this.sortBuilder.handler(PayWeChatPayQrCodeFlowModel.id);
        }

        /**
         * 返回状态码
         */
        public SortBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> returnCode() {
            return this.sortBuilder.handler(PayWeChatPayQrCodeFlowModel.returnCode);
        }

        /**
         * 返回信息
         */
        public SortBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> returnMsg() {
            return this.sortBuilder.handler(PayWeChatPayQrCodeFlowModel.returnMsg);
        }

        /**
         * 公众账号ID
         */
        public SortBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> appid() {
            return this.sortBuilder.handler(PayWeChatPayQrCodeFlowModel.appid);
        }

        /**
         * 商户号
         */
        public SortBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> mchId() {
            return this.sortBuilder.handler(PayWeChatPayQrCodeFlowModel.mchId);
        }

        /**
         * 设备号
         */
        public SortBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> deviceInfo() {
            return this.sortBuilder.handler(PayWeChatPayQrCodeFlowModel.deviceInfo);
        }

        /**
         * 随机字符串
         */
        public SortBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> nonceStr() {
            return this.sortBuilder.handler(PayWeChatPayQrCodeFlowModel.nonceStr);
        }

        /**
         * 签名
         */
        public SortBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> sign() {
            return this.sortBuilder.handler(PayWeChatPayQrCodeFlowModel.sign);
        }

        /**
         * 签名类型
         */
        public SortBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> signType() {
            return this.sortBuilder.handler(PayWeChatPayQrCodeFlowModel.signType);
        }

        /**
         * 业务结果
         */
        public SortBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> resultCode() {
            return this.sortBuilder.handler(PayWeChatPayQrCodeFlowModel.resultCode);
        }

        /**
         * 错误代码
         */
        public SortBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> errCode() {
            return this.sortBuilder.handler(PayWeChatPayQrCodeFlowModel.errCode);
        }

        /**
         * 错误代码描述
         */
        public SortBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> errCodeDes() {
            return this.sortBuilder.handler(PayWeChatPayQrCodeFlowModel.errCodeDes);
        }

        /**
         * 用户标识
         */
        public SortBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> openid() {
            return this.sortBuilder.handler(PayWeChatPayQrCodeFlowModel.openid);
        }

        /**
         * 是否关注公众账号
         */
        public SortBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> isSubscribe() {
            return this.sortBuilder.handler(PayWeChatPayQrCodeFlowModel.isSubscribe);
        }

        /**
         * 交易类型
         */
        public SortBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> tradeType() {
            return this.sortBuilder.handler(PayWeChatPayQrCodeFlowModel.tradeType);
        }

        /**
         * 付款银行
         */
        public SortBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> bankType() {
            return this.sortBuilder.handler(PayWeChatPayQrCodeFlowModel.bankType);
        }

        /**
         * 订单金额
         */
        public SortBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> totalFee() {
            return this.sortBuilder.handler(PayWeChatPayQrCodeFlowModel.totalFee);
        }

        /**
         * 应结订单金额
         */
        public SortBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> settlementTotalFee() {
            return this.sortBuilder.handler(PayWeChatPayQrCodeFlowModel.settlementTotalFee);
        }

        /**
         * 货币种类
         */
        public SortBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> feeType() {
            return this.sortBuilder.handler(PayWeChatPayQrCodeFlowModel.feeType);
        }

        /**
         * 现金支付金额
         */
        public SortBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> cashFee() {
            return this.sortBuilder.handler(PayWeChatPayQrCodeFlowModel.cashFee);
        }

        /**
         * 现金支付货币类型
         */
        public SortBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> cashFeeType() {
            return this.sortBuilder.handler(PayWeChatPayQrCodeFlowModel.cashFeeType);
        }

        /**
         * 总代金券金额
         */
        public SortBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> couponFee() {
            return this.sortBuilder.handler(PayWeChatPayQrCodeFlowModel.couponFee);
        }

        /**
         * 代金券使用数量
         */
        public SortBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> couponCount() {
            return this.sortBuilder.handler(PayWeChatPayQrCodeFlowModel.couponCount);
        }

        /**
         * 代金券类型
         */
        public SortBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> couponTypeN() {
            return this.sortBuilder.handler(PayWeChatPayQrCodeFlowModel.couponTypeN);
        }

        /**
         * 代金券ID
         */
        public SortBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> couponIdN() {
            return this.sortBuilder.handler(PayWeChatPayQrCodeFlowModel.couponIdN);
        }

        /**
         * 单个代金券支付金额
         */
        public SortBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> couponFeeN() {
            return this.sortBuilder.handler(PayWeChatPayQrCodeFlowModel.couponFeeN);
        }

        /**
         * 微信支付订单号
         */
        public SortBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> transactionId() {
            return this.sortBuilder.handler(PayWeChatPayQrCodeFlowModel.transactionId);
        }

        /**
         * 商户订单号
         */
        public SortBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> outTradeNo() {
            return this.sortBuilder.handler(PayWeChatPayQrCodeFlowModel.outTradeNo);
        }

        /**
         * 商家数据包
         */
        public SortBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> attach() {
            return this.sortBuilder.handler(PayWeChatPayQrCodeFlowModel.attach);
        }

        /**
         * 支付完成时间
         */
        public SortBuilder<PayWeChatPayQrCodeFlowModel, Column, On, Where, Sort, Group> timeEnd() {
            return this.sortBuilder.handler(PayWeChatPayQrCodeFlowModel.timeEnd);
        }

    }

}