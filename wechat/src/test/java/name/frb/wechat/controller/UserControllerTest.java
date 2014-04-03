package name.frb.wechat.controller;

import name.frb.configuration.xmlconfiguration.XmlConfiguration;
import name.frb.wechat.AbstractTestng;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;

public class UserControllerTest extends AbstractTestng {
    @Autowired
    private XmlConfiguration wechatTemplate;

    @Test
    public void readWechatTeplateTest() {
        String replayMessage = wechatTemplate.getString("TextMessage")
                .replace("${ToUserName}", "o8vWct1kjxAViVf2u_BZHuR4bbeU")
                .replace("${FromUserName}", "gh_583174c5755e")
                .replace("${CreateTime}", String.valueOf(new Date().getTime()))
                .replace("${Content}", "感谢关注“快乐学ENGLISH”微信号，本号码正在开发当中，希望您耐心等待！");

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
