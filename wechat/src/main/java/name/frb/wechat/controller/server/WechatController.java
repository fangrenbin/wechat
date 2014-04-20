package name.frb.wechat.controller.server;

import name.frb.wechat.server.service.WechatService;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * 与微信服务器通讯
 */
@Controller
public class WechatController {
    /*  Wechat Token */
    private static final String TOKEN = "klxenglish";

    private WechatService wechatService;

    /**
     * 验证微信公众平台接口
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/weixin", method = RequestMethod.GET)
    @ResponseBody
    public String initWeixinURL(HttpServletRequest request) {
        String echostr = request.getParameter("echostr");
        if (checkSignature(request) && echostr != null) {
            return echostr;
        } else {
            return "error";
        }
    }

    @RequestMapping(value = "/weixin", method = RequestMethod.POST)
    @ResponseBody
    public String replyMessage(HttpServletRequest request) throws IOException, ConfigurationException {
        if (!checkSignature(request)) {
            return "error";
        }

        String replayMessage = wechatService.replyMessage(request.getInputStream());
        System.out.println("replayMessage" + replayMessage);

        return replayMessage;
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

    public void setWechatService(WechatService wechatService) {
        this.wechatService = wechatService;
    }
}
