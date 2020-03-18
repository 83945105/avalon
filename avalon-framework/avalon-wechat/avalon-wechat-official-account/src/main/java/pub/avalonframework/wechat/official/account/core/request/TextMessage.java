package pub.avalonframework.wechat.official.account.core.request;

/**
 * 文本消息
 *
 * @author baichao
 */
public class TextMessage extends BaseMessage {

    /**
     * 消息内容
     */
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}