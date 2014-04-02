package name.frb.wechat.controller;

import java.math.BigInteger;
import java.security.MessageDigest;

public class UserControllerTest {
    public static void main(String[] args) {
        UserControllerTest test = new UserControllerTest();
        System.out.println(sha1("abc"));
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
