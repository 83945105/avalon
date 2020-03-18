package pub.avalonframework.wechat.official.account.core.response;

/**
 * 音乐消息
 *
 * @author baichao
 */
public class MusicMessage extends BaseMessage {
    /**
     * 音乐
     */
    private Music Music;

    public Music getMusic() {
        return Music;
    }

    public void setMusic(Music music) {
        Music = music;
    }
}