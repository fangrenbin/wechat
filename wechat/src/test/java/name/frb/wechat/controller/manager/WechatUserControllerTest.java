package name.frb.wechat.controller.manager;

import name.frb.wechat.AbstractTestng;
import org.testng.annotations.Test;

import javax.annotation.Resource;

public class WechatUserControllerTest extends AbstractTestng {
    @Resource
    private WechatUserController wechatUserController;

    @Test
    public void userListTest() {
        wechatUserController.userList();
    }
}
