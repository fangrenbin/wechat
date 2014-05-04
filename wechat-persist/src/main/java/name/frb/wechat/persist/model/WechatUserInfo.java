package name.frb.wechat.persist.model;

/**
 * @hibernate.class dynamic-insert="true" dynamic-update="true" table="wechat_user_info" lazy="false"
 */
public class WechatUserInfo {
    private Long id;
    private Integer subscribe;
    private String openId;
    private String nickname;
    private Integer sex;
    private String city;
    private String country;
    private String province;
    private String language;
    private String headImgUrl;
    private String subscribeTime;

    /**
     * @return
     * @hibernate.id generator-class="native" type="long" unsaved-value="0"
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return
     * @hibernate.property type="int"
     */
    public Integer getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Integer subscribe) {
        this.subscribe = subscribe;
    }

    /**
     * @return
     * @hibernate.property type="string"
     */
    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * @return
     * @hibernate.property type="string"
     */
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return
     * @hibernate.property type="int"
     */
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }


    /**
     * @return
     * @hibernate.property type="string"
     */
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return
     * @hibernate.property type="string"
     */
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return
     * @hibernate.property type="string"
     */
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * @return
     * @hibernate.property type="string"
     */
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @return
     * @hibernate.property type="string"
     */
    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    /**
     * @return
     * @hibernate.property type="string"
     */
    public String getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(String subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    @Override
    public String toString() {
        return "WechatUserInfo{" +
                "id=" + id +
                ", subscribe=" + subscribe +
                ", openId='" + openId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex=" + sex +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", language='" + language + '\'' +
                ", headImgUrl='" + headImgUrl + '\'' +
                ", subscribeTime='" + subscribeTime + '\'' +
                '}';
    }
}
