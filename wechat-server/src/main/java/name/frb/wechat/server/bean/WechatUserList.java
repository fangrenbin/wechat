package name.frb.wechat.server.bean;

import java.util.List;

/**
 * 微信用户列表OPENID
 * Created by renbin.fang on 2014/5/9.
 */
public class WechatUserList {
    private Long total;
    private Long count;
    private List<String> openIdList;
    private String nextOpenId;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<String> getOpenIdList() {
        return openIdList;
    }

    public void setOpenIdList(List<String> openIdList) {
        this.openIdList = openIdList;
    }

    public String getNextOpenId() {
        return nextOpenId;
    }

    public void setNextOpenId(String nextOpenId) {
        this.nextOpenId = nextOpenId;
    }

    @Override
    public String toString() {
        return "WechatUserList{" +
                "total=" + total +
                ", count=" + count +
                ", openIdList=" + openIdList +
                ", nextOpenId='" + nextOpenId + '\'' +
                '}';
    }
}
