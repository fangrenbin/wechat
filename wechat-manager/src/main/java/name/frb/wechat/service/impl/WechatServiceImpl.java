package name.frb.wechat.service.impl;

import name.frb.configuration.xmlconfiguration.XmlConfiguration;
import name.frb.wechat.bean.MessageType;
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

    @Override
    public String replyMessage(InputStream inputStream) {
        XMLConfiguration xmlReader = new XMLConfiguration();
        try {
            xmlReader.load(inputStream);
        } catch (ConfigurationException e) {
            System.err.println(e);
        }

        String msgType = xmlReader.getString("MsgType");
        if (StringUtils.equals(msgType, MessageType.TEXT.getValue())) {
            //接收TEXT消息
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(xmlReader.getString("ToUserName"));
            textMessage.setFromUserName(xmlReader.getString("FromUserName"));
            textMessage.setCreateTime(xmlReader.getString("CreateTime"));
            textMessage.setMsgType(xmlReader.getString("MsgType"));
            textMessage.setContent(xmlReader.getString("Content"));
            textMessage.setMsgId(xmlReader.getString("MsgId"));

            //1st. save text message into database
            boolean success = msgdao.addTextMessage(textMessage);
            if (!success) {
                System.err.println("failed to save text message");
                return "error";
            }

            // 2nd. retrieve content of reply message
            String querykey = xmlReader.getString("Content");

            String replyContent = ncenglishDao.retrieveNcenglishContent(querykey);
            if (StringUtils.isEmpty(replyContent)) {
                replyContent = wechatTemplate.getString("UnknowMessage");
            }

            // 3nd. form reply message to xml type
            String replayMessage = wechatTemplate.getString("TextMessage")
                    .replace("${ToUserName}", "<![CDATA[" + textMessage.getFromUserName() + "]]>")
                    .replace("${FromUserName}", "<![CDATA[" + textMessage.getToUserName() + "]]>")
                    .replace("${CreateTime}", String.valueOf(new Date().getTime()))
                    .replace("${MsgType}", "<![CDATA[" + MessageType.TEXT.getValue() + "]]>")
                    .replace("${Content}", "<![CDATA[" + replyContent + "]]>");

            //4th. return reply message
            return replayMessage;
        }

        return "";
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
