package pub.avalonframework.wechat.official.account.core.request;

/**
 * 音频消息
 *
 * @author baichao
 */
public class VoiceMessage extends BaseMessage {

    /**
     * 媒体ID
     */
    private String MediaId;

    /**
     * 语音格式
     */
    private String Format;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }
}