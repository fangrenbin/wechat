package name.frb.wechat.controller;

import name.frb.configuration.xmlconfiguration.XmlConfiguration;
import name.frb.wechat.AbstractTestng;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;

public class UserControllerTest extends AbstractTestng {
//    @Autowired
    private XmlConfiguration wechatTemplate;

//    @Test
    public void readWechatTeplateTest() {

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
