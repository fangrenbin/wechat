package name.frb.wechat.controller;

import name.frb.wechat.model.wechat.TextMessage;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

public class UserControllerTest {
    public static void main(String[] args) throws FileNotFoundException, ConfigurationException {
        InputStream is = new FileInputStream(new File("/Users/renbinfang/Desktop/weixinTextMsg.xml"));

        XMLConfiguration xmlreader = new XMLConfiguration();
        xmlreader.load(is);

        //接收TEXT消息
        TextMessage textMessage = new TextMessage();
        textMessage.setToUserName(xmlreader.getString("ToUserName"));
        textMessage.setFromUserName(xmlreader.getString("FromUserName"));
        textMessage.setCreateTime(xmlreader.getString("CreateTime"));
        textMessage.setMsgType(xmlreader.getString("MsgType"));
        textMessage.setContent(xmlreader.getString("Content"));
        textMessage.setMsgId(xmlreader.getString("MsgId"));

        System.out.println(textMessage.toString());
    }

//    public static void main(String[] args) {
//        UserControllerTest test = new UserControllerTest();
//        System.out.println(sha1("abc"));
//    }

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
