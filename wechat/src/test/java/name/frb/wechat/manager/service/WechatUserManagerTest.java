package name.frb.wechat.manager.service;

import name.frb.wechat.AbstractTestng;
import name.frb.wechat.persist.model.WechatUserInfo;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import java.util.List;

public class WechatUserManagerTest extends AbstractTestng {
    @Resource
    private WechatUserManager wechatUserManager;

    @Test
    public void listUserTest() {
        List<WechatUserInfo> userInfoList = wechatUserManager.userList();
        for (WechatUserInfo userInfo : userInfoList) {
            System.out.println(userInfo.toString());
        }
    }

}
