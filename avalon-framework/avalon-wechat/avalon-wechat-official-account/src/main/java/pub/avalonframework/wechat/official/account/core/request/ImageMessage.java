package pub.avalonframework.wechat.official.account.core.request;

/**
 * 图片消息
 *
 * @author baichao
 */
public class ImageMessage extends BaseMessage {

    /**
     * 图片链接
      */
    private String PicUrl;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }
}