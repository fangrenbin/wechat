package name.frb.wechat.service;

import name.frb.configuration.xmlconfiguration.XmlConfiguration;
import name.frb.wechat.AbstractTestng;
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
        System.out.println(helpMessage);
        System.out.println(helpMessage);
    }
}
