package pub.avalon.pay.beans;

import com.alibaba.fastjson.JSONObject;

/**
 * 交易撤销
 *
 * @author 白超
 */
public class AlipayTradeCancel {

    private String out_trade_no;

    private String trade_no;

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public String toJsonString() {
        return JSONObject.toJSONString(this);
    }
}
