package name.frb.wechat.persist.model;

/**
 * @hibernate.class dynamic-insert="true" dynamic-update="true" table="text_message" lazy="false"
 */
public class TextMessage {
    private long id;
    private String toUserName;
    private String fromUserName;
    private String createTime;
    private String msgType;
    private String content;
    private String msgId;

    /**
     * @return
     * @hibernate.id generator-class="native" type="long" unsaved-value="0"
     */
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return
     * @hibernate.property type="string"
     */
    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    /**
     * @return
     * @hibernate.property type="string"
     */
    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    /**
     * @return
     * @hibernate.property type="string"
     */
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * @return
     * @hibernate.property type="string"
     */
    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    /**
     * @return
     * @hibernate.property type="string" length="500"
     */
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return
     * @hibernate.property type="string"
     */
    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    @Override
    public String toString() {
        return "TextMessage{" +
                "id=" + id +
                ", toUserName='" + toUserName + '\'' +
                ", fromUserName='" + fromUserName + '\'' +
                ", createTime='" + createTime + '\'' +
                ", msgType='" + msgType + '\'' +
                ", content='" + content + '\'' +
                ", msgId='" + msgId + '\'' +
                '}';
    }
}
