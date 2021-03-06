package name.frb.wechat.controller;

import name.frb.configuration.xmlconfiguration.XmlConfiguration;
import name.frb.wechat.AbstractTestng;
import name.frb.wechat.controller.server.WechatController;
import name.frb.wechat.server.bean.ReceiveMessageType;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * WechatControllerTest
 * Created by renbin.fang on 2014/4/22.
 */
public class WechatControllerTest extends AbstractTestng {
    @Autowired
    private WechatController wechatController;

    //获取模拟微信服务器发送信息的模板
    @Autowired
    private XmlConfiguration wechatTemplate;

    //接收微信服务消息地址
    private final String WECHAT_URL = "http://aqueous-taiga-4186.herokuapp.com/weixin";

    //生成签名信息
    private final String TOKEN = "klxenglish";
    private final String TIMESTAMP = "1398151318213";
    private final String NONCE = "123123";

    //生成微信消息
    private final String TO_USER_NAME = "gh_583174c5755e";
    private final String FROME_USER_NAME = "o8vWct1kjxAViVf2u_BZHuR4bbeU";
    private final String CREATE_TIME = "1396504041";
    private final String CONTENT = "this is a test";
    private final String MSG_ID = "5997939185026900371";

    private MockHttpServletRequest request;

    @BeforeMethod
    public void setUp() {
        //request中加入消息的InputStream
        request = new MockHttpServletRequest();
        request.setContent(generateMessageInputStream(ReceiveMessageType.TEXT.getValue()));

        String signature = generateSignature();
        request.addParameter("signature", signature);
        request.addParameter("timestamp", TIMESTAMP);
        request.addParameter("nonce", NONCE);
    }


//    @Test
    public void replyMessageTest() throws IOException, ConfigurationException {
        String actual = getMessageContext(wechatController.replyMessage(request));
        String expected = wechatTemplate.getString("UnKnowOrder");

        Assert.assertEquals(actual.toString(), expected.toString());
    }

    //TODO 测试真实的地址
//    @Test
    public void wechatUrlTest() throws IOException {
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod("http://klxenglish.herokuapp.com/weixin/");
        String signature = generateSignature();
        NameValuePair[] data = {new NameValuePair("signature", signature),
                new NameValuePair("timestamp", TIMESTAMP),
                new NameValuePair("nonce", NONCE)};
        postMethod.setRequestBody(data);

        int statusCode = httpClient.executeMethod(postMethod);
        //读取内容
        byte[] responseBody = postMethod.getResponseBody();
        //处理内容
        System.out.println(new String(responseBody));
    }

    /**
     * 生成签名
     *
     * @return
     */
    private String generateSignature() {
        String signature = "";
        String[] stringArray = new String[]{TOKEN, TIMESTAMP, NONCE};
        java.util.Arrays.sort(stringArray);
        for (String string : stringArray) {
            signature = signature + string;
        }
        return sha1(signature);
    }

    /**
     * 获取XML格式消息的内容
     *
     * @param replyMessage
     * @return
     */
    private String getMessageContext(String replyMessage) {
        XMLConfiguration xmlReader = new XMLConfiguration();
        try {
            xmlReader.load(new ByteArrayInputStream(replyMessage.getBytes()));
        } catch (ConfigurationException e) {
            System.err.println(e);
        }

        String content = xmlReader.getString("Content");
        return content;
    }

    /**
     * 生成发送消息的InputStream
     *
     * @param messageType 消息类型
     * @return 发送消息的InputStream
     */
    private byte[] generateMessageInputStream(String messageType) {
        String messageTemplate = null;
        if (StringUtils.equals(messageType, ReceiveMessageType.TEXT.getValue())) {
            messageTemplate = wechatTemplate.getString("Wechat.TextMessage");
            messageTemplate = replacePlaceHolder(messageTemplate, ReceiveMessageType.TEXT.getValue());
        }

        return messageTemplate.getBytes();
    }

    /**
     * 生成微信发送的消息
     *
     * @param messageTemplate 消息模板
     * @param messageType     消息类型
     * @return 微信发送的消息
     */
    private String replacePlaceHolder(String messageTemplate, String messageType) {
        return messageTemplate.replace("${ToUserName}", TO_USER_NAME)
                .replace("${FromUserName}", FROME_USER_NAME)
                .replace("${CreateTime}", CREATE_TIME)
                .replace("${MsgType}", messageType)
                .replace("${Content}", CONTENT)
                .replace("${MsgId}}", MSG_ID);
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
