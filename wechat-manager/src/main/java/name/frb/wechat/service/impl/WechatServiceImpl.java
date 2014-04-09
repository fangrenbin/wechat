package name.frb.wechat.service.impl;

import name.frb.configuration.xmlconfiguration.XmlConfiguration;
import name.frb.wechat.bean.ReceiveMessageType;
import name.frb.wechat.bean.SendMessageType;
import name.frb.wechat.dao.MsgDao;
import name.frb.wechat.dao.NcenglishDao;
import name.frb.wechat.model.wechat.TextMessage;
import name.frb.wechat.service.TranslationService;
import name.frb.wechat.service.WechatService;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.lang.StringUtils;

import java.io.InputStream;
import java.util.Date;

public class WechatServiceImpl implements WechatService {
    private TranslationService translationService;

    private XmlConfiguration wechatTemplate;
    private MsgDao msgdao;
    private NcenglishDao ncenglishDao;

    private final static String MSG_START = "<![CDATA[";
    private final static String MSG_END = "]]>";
    private final static String TAB = "  ";

    private final static String SUBSCRIBE = "subscribe";
    private final static String UNSUBSCRIBE = "unsubscribe";

    @Override
    public String replyMessage(InputStream inputStream) {
        XMLConfiguration xmlReader = new XMLConfiguration();
        try {
            xmlReader.load(inputStream);
        } catch (ConfigurationException e) {
            System.err.println(e);
        }

        return dealWithOrderMessage(xmlReader);
    }

    /**
     * 处理查看指令，并返回XML模式数据
     *
     * @param xmlReader
     * @return
     */
    private String dealWithOrderMessage(XMLConfiguration xmlReader) {
        String replyContent = "";
        String msgType = xmlReader.getString("MsgType");
        String toUserName = xmlReader.getString("ToUserName");
        String fromUserName = xmlReader.getString("FromUserName");

        // 处理推送事件
        if (StringUtils.equals(msgType, ReceiveMessageType.EVENT.getValue())) {
            //TODO    欢迎信息和再见信息都需要做成可维护的。
            String eventType = xmlReader.getString("Event");
            if (StringUtils.equals(eventType, SUBSCRIBE)) {
                replyContent = wechatTemplate.getString("WelcomeMessage");
            } else {
                //TODO delete user information, not reply goodbye message.
                replyContent = wechatTemplate.getString("GoodByeMessage");
            }
        }
        // 处理文本信息
        else if (StringUtils.equals(msgType, ReceiveMessageType.TEXT.getValue())) {
            //接收TEXT消息
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(xmlReader.getString("ToUserName"));
            textMessage.setFromUserName(xmlReader.getString("FromUserName"));
            textMessage.setCreateTime(xmlReader.getString("CreateTime"));
            textMessage.setMsgType(xmlReader.getString("MsgType"));
            textMessage.setContent(xmlReader.getString("Content"));
            textMessage.setMsgId(xmlReader.getString("MsgId"));

            // 保存消息到数据库
            boolean success = msgdao.addTextMessage(textMessage);
            if (!success) {
                System.err.println("Failed to save text message");
                return "error";
            }

            // 处理查看指令
            String querykey = xmlReader.getString("Content");

            if (doesNeedHelp(querykey)) {
                replyContent = wechatTemplate.getString("HelpMessage").replace(TAB, "");

            } else if (StringUtils.startsWith(querykey, "新概念")) {
                //FIXME  这里应该是替换第一次出现的字符串，不能将字符串中的所有字符为关键的都替换掉。
                String key = querykey.replace("新概念", "");
                replyContent = ncenglishDao.retrieveNcenglishContent(key);

                if (StringUtils.isEmpty(replyContent)) {
                    replyContent = wechatTemplate.getString("NoContent");
                }
            } else if (StringUtils.equals(querykey, "图文信息测试")) {
                return generateNewsMessage(textMessage);

            } else if (StringUtils.startsWith(querykey, "翻译")) {
                String words = querykey.replace("翻译", "");
                replyContent = translationService.translatEnToZh(words);
            } else {
                replyContent = wechatTemplate.getString("UnKnowOrder");
            }
        } else {
            // 处理非文本信息
            replyContent = "你发了一条非文本信息，我有时间会看的哦。";
        }

        // form reply message to xml type
        String replyTextMessage = wechatTemplate.getString("TextMessage")
                .replace("${ToUserName}", MSG_START + fromUserName + MSG_END)
                .replace("${FromUserName}", MSG_START + toUserName + MSG_END)
                .replace("${CreateTime}", String.valueOf(new Date().getTime()))
                .replace("${MsgType}", MSG_START + SendMessageType.TEXT.getValue() + MSG_END)
                .replace("${Content}", MSG_START + replyContent + MSG_END);

        return replyTextMessage;
    }

    /**
     * Generate news type message
     *
     * @param textMessage
     * @return
     */
    private String generateNewsMessage(TextMessage textMessage) {
        //TODO   可以控制图文信息的条数
        String newsMessage = wechatTemplate.getString("NewsMessage")
                .replace("${ToUserName}", MSG_START + textMessage.getFromUserName() + MSG_END)
                .replace("${FromUserName}", MSG_START + textMessage.getToUserName() + MSG_END)
                .replace("${CreateTime}", String.valueOf(new Date().getTime()))
                .replace("${MsgType}", MSG_START + SendMessageType.NEWS.getValue() + MSG_END)
                .replace("${title1}", MSG_START + "arduino ethernet shield通过网页来控制LED" + MSG_END)
                .replace("${description1}", MSG_START + "你好，这是图文消息1，这个是测试信息，请不要当真哦" + MSG_END)
                .replace("${picurl1}", MSG_START + "http://frb.name/wp-content/uploads/2012/12/Untitled-Sketch_bb_thumb.png" + MSG_END)
                .replace("${url1}", MSG_START + "http://frb.name/arduino-ethernet-shield-control-led/" + MSG_END)
                .replace("${title2}", MSG_START + "MongoVUE刪除特定的文檔" + MSG_END)
                .replace("${description2}", MSG_START + "你好，这是图文消息2，这个是测试信息，请不要当真哦" + MSG_END)
                .replace("${picurl2}", MSG_START + "http://frb.name/wp-content/uploads/2011/01/kernels-300x225.jpg" + MSG_END)
                .replace("${url2}", MSG_START + "http://frb.name/ubuntu-start-menus/" + MSG_END);

        return newsMessage;
    }

    /**
     * sb. whether needs help
     *
     * @param queryKey
     * @return true or false
     */
    private boolean doesNeedHelp(String queryKey) {
        String key = queryKey.replace(" ", "");
        if (!key.matches("^[a-zA-Z]+")) {
            return false;
        }

        key = key.toUpperCase();

        return StringUtils.equals(key, "HELP");
    }

    public void setTranslationService(TranslationService translationService) {
        this.translationService = translationService;
    }

    public void setWechatTemplate(XmlConfiguration wechatTemplate) {
        this.wechatTemplate = wechatTemplate;
    }

    public void setMsgdao(MsgDao msgdao) {
        this.msgdao = msgdao;
    }

    public void setNcenglishDao(NcenglishDao ncenglishDao) {
        this.ncenglishDao = ncenglishDao;
    }
}
