package pub.avalon.pay.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pub.avalon.holygrail.response.utils.ExceptionUtil;
import pub.avalon.pay.entity.PayAlipayComputerWebsiteFlow;
import pub.avalon.pay.entity.PayAlipayQrCodeFlow;
import pub.avalon.pay.entity.PayWeChatPayQrCodeFlow;
import pub.avalon.pay.model.PayAlipayComputerWebsiteFlowModel;
import pub.avalon.pay.model.PayAlipayQrCodeFlowModel;
import pub.avalon.pay.model.PayWeChatPayQrCodeFlowModel;
import pub.avalon.pay.service.PayService;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.spring.core.SpringJdbcEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 白超
 */
@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private SpringJdbcEngine jdbcEngine;

    @Override
    public void postAlipayComputerWebsitePayFlow(PayAlipayComputerWebsiteFlow record, HttpServletRequest request, HttpServletResponse response) throws Exception {
        int count = this.jdbcEngine.insertJavaBeanSelective(record, MySqlDynamicEngine.insert(PayAlipayComputerWebsiteFlowModel.class));
        if (count != 1) {
            ExceptionUtil.throwErrorException("记录支付宝电脑网页支付流水失败");
        }
    }

    @Override
    public void postAlipayQrCodePayFlow(PayAlipayQrCodeFlow record, HttpServletRequest request, HttpServletResponse response) throws Exception {
        int count = this.jdbcEngine.insertJavaBeanSelective(record, MySqlDynamicEngine.insert(PayAlipayQrCodeFlowModel.class));
        if (count != 1) {
            ExceptionUtil.throwErrorException("记录支付宝二维码支付流水失败");
        }
    }

    @Override
    public void postWeChatPayQrCodePayFlow(PayWeChatPayQrCodeFlow record, HttpServletRequest request, HttpServletResponse response) throws Exception {
        int count = this.jdbcEngine.insertJavaBeanSelective(record, MySqlDynamicEngine.insert(PayWeChatPayQrCodeFlowModel.class));
        if (count != 1) {
            ExceptionUtil.throwErrorException("记录微信二维码支付流水失败");
        }
    }
}
