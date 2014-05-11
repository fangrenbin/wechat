package name.frb.wechat.server;


import name.frb.wechat.AbstractTestng;
import name.frb.wechat.server.service.WechatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class WechatUserServiceTest extends AbstractTestng {
    @Autowired
    private WechatUserService wechatUserService;

    @Test
    public void syncWechatUserTest() {
//        wechatUserService.syncWechatUser();
    }
}