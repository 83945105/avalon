package pub.avalonframework.wechat.official.account.core;

/**
 * 用于解析json数据的key
 *
 * @author baichao
 */
public interface JsonKey {

    /**
     * 消息类型先关key
     */
    enum MessageTypeKey implements JsonKey {
        /**
         * 返回消息类型：文本
         */
        RESP_TEXT("text"),
        /**
         * 返回消息类型：音乐
         */
        RESP_MUSIC("music"),
        /**
         * 返回消息类型：图文
         */
        RESP_NEWS("news"),
        /**
         * 请求消息类型：文本
         */
        REQ_TEXT("text"),
        /**
         * 请求消息类型：图片
         */
        REQ_IMAGE("image"),
        /**
         * 请求消息类型：链接
         */
        REQ_LINK("link"),
        /**
         * 请求消息类型：地理位置
         */
        REQ_LOCATION("location"),
        /**
         * 请求消息类型：音频
         */
        REQ_VOICE("voice"),
        /**
         * 请求消息类型：推送
         */
        REQ_EVENT("event"),
        /**
         * 事件类型：subscribe(订阅)
         */
        EVENT_SUBSCRIBE("subscribe"),
        /**
         * 事件类型：unsubscribe(取消订阅)
         */
        EVENT_UNSUBSCRIBE("unsubscribe"),
        /**
         * 事件类型：CLICK(自定义菜单点击事件)
         */
        EVENT_CLICK("CLICK");

        private String value;

        MessageTypeKey(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 过滤器相关key
     */
    interface FilterKey extends JsonKey {

    }

    /**
     * 文本过滤器相关key
     */
    enum TextFilterKey implements FilterKey {
        /**
         * 请求为文本类型时,文本内容key的名称
         */
        REQ_CONTENT("Content");

        private String value;

        TextFilterKey(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 事件过滤器相关key
     */
    enum EventFilterKey implements FilterKey {
        /**
         * 请求为事件时,事件类型
         */
        REQ_TYPE("Event"),
        /**
         * 请求为事件时,事件key的名称
         */
        REQ_KEY("EventKey");

        private String value;

        EventFilterKey(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}