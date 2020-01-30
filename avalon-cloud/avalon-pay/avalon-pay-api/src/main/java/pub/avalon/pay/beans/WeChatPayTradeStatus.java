package pub.avalon.pay.beans;

import pub.avalon.beans.EnumMethods;

/**
 * @author baichao
 * @date 2018/12/23
 */
public enum WeChatPayTradeStatus implements EnumMethods {
    /**
     * 支付成功
     */
    SUCCESS,
    /**
     * 转入退款
     */
    REFUND,
    /**
     * 未支付
     */
    NOTPAY,
    /**
     * 已关闭
     */
    CLOSED,
    /**
     * 已撤销（付款码支付）
     */
    REVOKED,
    /**
     * 用户支付中（付款码支付）
     */
    USERPAYING,
    /**
     * 支付失败(其他原因，如银行返回失败)
     */
    PAYERROR;

    @Override
    public Object getValue() {
        return this.name();
    }
}
