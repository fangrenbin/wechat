package name.frb.wechat.controller;

import name.frb.wechat.model.wechat.TextMessage;
import name.frb.wechat.service.MessageService;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

@Controller()
public class WechatController {
    private static final String TOKEN = "klxenglish";

    @Resource(name = "messageService")
    private MessageService messageService;

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
    public String replyMessage(HttpServletRequest request) throws IOException, ConfigurationException {
        if (!checkSignature(request)) {
            return "error";
        }

        InputStream is = request.getInputStream();
        XMLConfiguration xmlreader = new XMLConfiguration();
        xmlreader.load(is);

        //接收TEXT消息
        TextMessage textMessage = new TextMessage();
        textMessage.setToUserName(xmlreader.getString("xml.ToUserName"));
        textMessage.setFromUserName(xmlreader.getString("xml.FromUserName"));
        textMessage.setCreateTime(xmlreader.getString("xml.CreateTime"));
        textMessage.setMsgType(xmlreader.getString("xml.MsgType"));
        textMessage.setContent(xmlreader.getString("xml.Content"));
        textMessage.setMsgId(xmlreader.getString("xml.MsgId"));

        boolean success = messageService.addMessage(textMessage);
        if (!success) {
            return "error";
        }

        return "thank you";
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

    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }
}
