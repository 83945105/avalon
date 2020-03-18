package pub.avalonframework.wechat.official.account.spring.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pub.avalonframework.wechat.official.account.core.api.config.WechatOfficialAccountConfiguration;
import pub.avalonframework.wechat.official.account.core.request.RequestMessageDistributor;
import pub.avalonframework.wechat.official.account.core.response.BaseMessage;
import pub.avalonframework.wechat.official.account.core.utils.RequestMessageUtils;
import pub.avalonframework.wechat.official.account.core.utils.SignUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author baichao
 */
@RequestMapping("${spring.avalon.wechat.official-account.entrance-root-path}")
@RestController
public class EntranceController {

    @Autowired
    private WechatOfficialAccountConfiguration wechatOfficialAccountConfiguration;

    private void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        PrintWriter out = response.getWriter();
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (SignUtils.checkSignature(wechatOfficialAccountConfiguration.getToken(), signature, timestamp, nonce)) {
            out.print(echostr);
        }
        out.close();
    }

    @RequestMapping(value = "${spring.avalon.wechat.official-account.entrance-sub-path}")
    public void index(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (HttpMethod.GET.name().equalsIgnoreCase(request.getMethod())) {
            doGet(request, response);
            return;
        }

        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 调用核心业务类接收消息、处理消息
        String respMessage = processRequest(request);

        // 响应消息
        PrintWriter out = response.getWriter();
        out.print(respMessage);
        out.close();
    }

    /**
     * 分发器
     */
    private RequestMessageDistributor requestMessageDistributor = new RequestMessageDistributor();

    /**
     * 处理微信发来的请求
     *
     * @param request
     */
    public String processRequest(HttpServletRequest request) {
        String respMessage = null;
        try {
            // xml请求解析
            Map<String, String> requestMap = RequestMessageUtils.parseXml(request);

            // 发送方帐号（open_id）
            String fromUserName = requestMap.get("FromUserName");
            // 公众帐号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");

            BaseMessage baseMessage = requestMessageDistributor.doMessage(requestMap, fromUserName, toUserName, msgType);
            respMessage = RequestMessageUtils.baseMessageToXml(baseMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respMessage;
    }
}