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
            return dealWithTextMessage(xmlReader);
        }

        return "";
    }

    /**
     * 处理查看指令，并返回XML模式数据
     *
     * @param xmlReader
     * @return
     */
    private String dealWithTextMessage(XMLConfiguration xmlReader) {
        String replyMessage = "";
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

        } else {
            replyContent = wechatTemplate.getString("UnKnowOrder");
        }

        // 3nd. form reply message to xml type
        replyMessage = wechatTemplate.getString("TextMessage")
                .replace("${ToUserName}", "<![CDATA[" + textMessage.getFromUserName() + "]]>")
                .replace("${FromUserName}", "<![CDATA[" + textMessage.getToUserName() + "]]>")
                .replace("${CreateTime}", String.valueOf(new Date().getTime()))
                .replace("${MsgType}", "<![CDATA[" + MessageType.TEXT.getValue() + "]]>")
                .replace("${Content}", "<![CDATA[" + replyContent + "]]>");

        return replyMessage;
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
