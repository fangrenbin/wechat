package name.frb.wechat.controller;

import name.frb.wechat.AbstractTestng;
import name.frb.wechat.dao.MsgDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class UserControllerTest extends AbstractTestng {
    @Autowired
    private MsgDao msgDao;

    @Test
    public void readWechatTeplateTest() {

    }
}
