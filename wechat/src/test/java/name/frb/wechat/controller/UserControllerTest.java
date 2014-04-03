package name.frb.wechat.controller;

import name.frb.configuration.resourceloader.RescourceLoader;
import name.frb.configuration.xmlconfiguration.XmlConfiguration;
import name.frb.wechat.AbstractTestng;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;

public class UserControllerTest extends AbstractTestng {
//    @Autowired
//    private XmlConfiguration wechatTemplate;

//    @Test
//    public void readWechatTeplateTest() {
//
//    }

    public static void main(String[] args) {
        RescourceLoader rescourceLoader = new RescourceLoader();
        XmlConfiguration wechatTemplate = new XmlConfiguration("/Users/renbinfang/Programming/github.com/wechat-klxenglish/wechat/src/main/resources/wechat/wechat-template.xml");
        wechatTemplate.setRescourceLoader(rescourceLoader);
        wechatTemplate.init();

        String replyContent = "感谢关注快乐学ENGLISH微信号，本号码正在开发当中，希望您耐心等待！";

        String replayMessage = wechatTemplate.getString("TextMessage")
                .replace("${ToUserName}", "<![CDATA[" + "o8vWct1kjxAViVf2u_BZHuR4bbeU" + "]]>")
                .replace("${FromUserName}", "<![CDATA[" + "gh_583174c5755e" + "]]>")
                .replace("${CreateTime}", String.valueOf(new Date().getTime()))
                .replace("${MsgType}", "<![CDATA[" + "text" + "]]>")
                .replace("${Content}", "<![CDATA[" + replyContent + "]]>");

        System.out.println(replayMessage);
    }

    public static String sha1(String key) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            md.update(key.getBytes());
            String pwd = new BigInteger(1, md.digest()).toString(16);
            return pwd;
        } catch (Exception e) {
            e.printStackTrace();
            return key;
        }
    }
}
