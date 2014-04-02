package name.frb.wechat.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;

@Controller
@RequestMapping(value = "/wechat")
public class WechatController {
    private static final String TOKEN = "klxenglish";

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String test(HttpServletRequest request) {
        if (checkSignature(request)) {
            return "true";
        }

        return "false";
    }

    /**
     * 验证消息的真实性
     *
     * @param request
     * @return true or false
     */
    private boolean checkSignature(HttpServletRequest request) {
        String wechatSignature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");

        if (StringUtils.isEmpty(wechatSignature) || StringUtils.isEmpty(timestamp) || StringUtils.isEmpty(nonce)) {
            return false;
        }

        String[] stringArray = new String[]{TOKEN, timestamp, nonce};
        java.util.Arrays.sort(stringArray);
        String signature = "";
        for (String string : stringArray) {
            signature = signature + string;
        }

        return StringUtils.equals(wechatSignature, sha1(signature));
    }

    /**
     * sha1
     *
     * @param key
     * @return string
     */
    private String sha1(String key) {
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
