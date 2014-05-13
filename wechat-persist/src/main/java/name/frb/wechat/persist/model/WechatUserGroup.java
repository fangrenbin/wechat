package name.frb.wechat.persist.model;

import javax.persistence.*;

/**
 * Created by renbin.fang on 2014/5/13
 *
 * @hibernate.class dynamic-insert="true" dynamic-update="true" table="wechat_user_group" lazy="false"
 */
@Entity
@Table(name = "wechat_user_group")
public class WechatUserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long wechatId;
    private String name;
    private Integer count;

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
     * @hibernate.property type="string"
     */
    public Long getWechatId() {
        return wechatId;
    }

    public void setWechatId(Long wechatId) {
        this.wechatId = wechatId;
    }

    /**
     * @hibernate.property type="string"
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @hibernate.property type="int"
     */
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "WechatUserGroup{" +
                "id=" + id +
                ", wechatId=" + wechatId +
                ", name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}
