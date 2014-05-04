package name.frb.wechat.manager.service;

import name.frb.configuration.xmlconfiguration.XmlConfiguration;
import name.frb.wechat.AbstractTestng;
import name.frb.wechat.server.service.WechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class WechatServiceTest extends AbstractTestng {
    @Autowired
    private XmlConfiguration wechatTemplate;

    @Autowired
    private WechatService wechatService;

    @Test
    public void generateNewsMessageTest() {
        String helpMessage = wechatTemplate.getString("HelpMessage").replace("  ", "");
    }
}
