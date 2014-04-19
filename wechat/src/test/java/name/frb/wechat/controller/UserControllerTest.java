package name.frb.wechat.controller;

import name.frb.wechat.AbstractTestng;
import name.frb.wechat.server.dao.MessageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class UserControllerTest extends AbstractTestng {
    @Autowired
    private MessageDao msgDao;

    @Test
    public void readWechatTeplateTest() {

    }
}
