package name.frb.wechat.bean;

public enum MessageTypeEnum {
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
     * 地理位置消息
     */
    LOCATION("location"),
    /**
     * 链接消息
     */
    LINK("link");

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
    private MessageTypeEnum(String value) {
        this.value = value;
    }
}
