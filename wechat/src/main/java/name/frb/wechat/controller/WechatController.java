package name.frb.wechat.controller;

import name.frb.configuration.xmlconfiguration.XmlConfiguration;
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
import java.util.Date;

@Controller
public class WechatController {
    private static final String TOKEN = "klxenglish";

    @Resource(name = "messageService")
    private MessageService messageService;

    @Resource(name = "wechatTemplate")
    private XmlConfiguration wechatTemplate;

    /**
     * 验证微信公众平台接口
     *
     * @param request
     * @return
     */
//    @RequestMapping(value = "/weixin", method = RequestMethod.GET)
//    @ResponseBody
//    public String initWeixinURL(HttpServletRequest request) {
//        String echostr = request.getParameter("echostr");
//        if (checkSignature(request) && echostr != null) {
//            return echostr;
//        } else {
//            return "error";
//        }
//    }

    @RequestMapping(value = "/weixin", method = RequestMethod.POST)
    @ResponseBody
    public String replyMessage(HttpServletRequest request) throws IOException, ConfigurationException {
        String wechatSignature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");

        if (StringUtils.isEmpty(wechatSignature) || StringUtils.isEmpty(timestamp) || StringUtils.isEmpty(nonce)) {
            return "sth. is empty. wechatSignature="+wechatSignature + "timestamp="+timestamp + "nonce" + nonce;
        }

        String[] stringArray = new String[]{TOKEN, timestamp, nonce};
        java.util.Arrays.sort(stringArray);
        String signature = "";
        for (String string : stringArray) {
            signature = signature + string;
        }

        if (!checkSignature(request)) {
            return "singnatrue error, signature=" + signature + "wechatSignature=" + wechatSignature;
        }

        InputStream is = request.getInputStream();
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

        boolean success = messageService.addMessage(textMessage);
        if (!success) {
            return "failed to save text message";
        }

        String replyContent = "感谢关注快乐学ENGLISH微信号，本号码正在开发当中，希望您耐心等待！";

        String replayMessage = wechatTemplate.getString("TextMessage")
                .replace("${ToUserName}", "<![CDATA[" + textMessage.getFromUserName() + "]]>")
                .replace("${FromUserName}", "<![CDATA[" + textMessage.getToUserName() + "]]>")
                .replace("${CreateTime}", String.valueOf(new Date().getTime()))
                .replace("${MsgType}", "<![CDATA[" + "text" + "]]>")
                .replace("${Content}", "<![CDATA[" + replyContent + "]]>");

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

    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    public void setWechatTemplate(XmlConfiguration wechatTemplate) {
        this.wechatTemplate = wechatTemplate;
    }
}
