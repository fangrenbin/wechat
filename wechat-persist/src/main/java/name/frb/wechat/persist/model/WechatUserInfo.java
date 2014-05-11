package name.frb.wechat.persist.model;

import javax.persistence.*;

/**
 * @hibernate.class dynamic-insert="true" dynamic-update="true" table="wechat_user_info" lazy="false"
 */
@Entity
@Table(name = "wechat_user_info")
public class WechatUserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "subscribe", nullable = false)
    private Integer subscribe;
    @Column(name = "openId", nullable = false)
    private String openId;
    @Column(name = "nickname", nullable = false)
    private String nickname;
    @Column(name = "sex", nullable = false)
    private Integer sex;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "country", nullable = false)
    private String country;
    @Column(name = "province", nullable = false)
    private String province;
    @Column(name = "language", nullable = false)
    private String language;
    @Column(name = "headImgUrl", nullable = false)
    private String headImgUrl;
    @Column(name = "subscribeTime", nullable = false)
    private Long subscribeTime;

    /**
     * @hibernate.id generator-class="native" type="long" unsaved-value="0"
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @hibernate.property type="int"
     */
    public Integer getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Integer subscribe) {
        this.subscribe = subscribe;
    }

    /**
     * @hibernate.property type="string"
     */
    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * @hibernate.property type="string"
     */
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @hibernate.property type="int"
     */
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }


    /**
     * @hibernate.property type="string"
     */
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @hibernate.property type="string"
     */
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @hibernate.property type="string"
     */
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * @hibernate.property type="string"
     */
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @hibernate.property type="string"
     */
    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    /**
     * @hibernate.property type="string"
     */
    public Long getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(Long subscribeTime) {
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
