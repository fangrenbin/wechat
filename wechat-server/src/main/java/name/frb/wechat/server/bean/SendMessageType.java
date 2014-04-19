package name.frb.wechat.server.bean;

/**
 * Send message type
 */
public enum SendMessageType {
    /**
     * 文本消息
     */
    TEXT("text"),
    /**
     * 图片消息
     */
    IMAGE("image"),
    /**
     * 语音消息
     */
    VOICE("voice"),
    /**
     * 视频消息
     */
    VIDEO("video"),
    /**
     * 音乐
     */
    MUSIC("music"),
    /**
     * 图文
     */
    NEWS("news");

    /**
     * value
     */
    private String value;

    /**
     * get value
     *
     * @return value
     */
    public String getValue() {
        return value;
    }

    /**
     * Constructor
     *
     * @param value
     */
    private SendMessageType(String value) {
        this.value = value;
    }
}
