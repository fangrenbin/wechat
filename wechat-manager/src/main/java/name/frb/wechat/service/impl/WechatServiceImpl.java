package name.frb.wechat.service.impl;

import name.frb.configuration.xmlconfiguration.XmlConfiguration;
import name.frb.wechat.bean.ReceiveMessageType;
import name.frb.wechat.bean.SendMessageType;
import name.frb.wechat.dao.MsgDao;
import name.frb.wechat.dao.NcenglishDao;
import name.frb.wechat.model.wechat.TextMessage;
import name.frb.wechat.service.WechatService;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.lang.StringUtils;

import java.io.InputStream;
import java.util.Date;

public class WechatServiceImpl implements WechatService {
    private XmlConfiguration wechatTemplate;
    private MsgDao msgdao;
    private NcenglishDao ncenglishDao;

    private final static String MSG_START = "<![CDATA[";
    private final static String MSG_END = "]]>";

    @Override
    public String replyMessage(InputStream inputStream) {
        XMLConfiguration xmlReader = new XMLConfiguration();
        try {
            xmlReader.load(inputStream);
        } catch (ConfigurationException e) {
            System.err.println(e);
        }

        String msgType = xmlReader.getString("MsgType");
        if (!StringUtils.equals(msgType, ReceiveMessageType.TEXT.getValue())) {
            return "你发了一条非文本信息，我有时间会看的哦。";
        }

        return dealWithTextMessage(xmlReader);
    }

    /**
     * 处理查看指令，并返回XML模式数据
     *
     * @param xmlReader
     * @return
     */
    private String dealWithTextMessage(XMLConfiguration xmlReader) {
        String replyTextMessage = "";
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

        // 处理查看新概念请求
        String querykey = xmlReader.getString("Content");
        String replyContent = "";

        if (doesNeedHelp(querykey)) {
            replyContent = wechatTemplate.getString("HelpMessage");

        } else if (StringUtils.startsWith(querykey, "新概念")) {
            String key = querykey.replace("新概念", "");
            replyContent = ncenglishDao.retrieveNcenglishContent(key);

            if (StringUtils.isEmpty(replyContent)) {
                replyContent = wechatTemplate.getString("NoContent");
            }
        } else if (StringUtils.equals(querykey, "图文信息测试")) {
            return generateNewsMessage(textMessage);

        } else {
            replyContent = wechatTemplate.getString("UnKnowOrder");
        }

        // 3nd. form reply message to xml type
        replyTextMessage = wechatTemplate.getString("TextMessage")
                .replace("${ToUserName}", MSG_START + textMessage.getFromUserName() + MSG_END)
                .replace("${FromUserName}", MSG_START + textMessage.getToUserName() + MSG_END)
                .replace("${CreateTime}", String.valueOf(new Date().getTime()))
                .replace("${MsgType}", MSG_START + SendMessageType.TEXT.getValue() + MSG_END)
                .replace("${Content}", MSG_START + replyContent + MSG_END);

        return replyTextMessage;
    }

    private String generateNewsMessage(TextMessage textMessage) {
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
     * whether needs help
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
