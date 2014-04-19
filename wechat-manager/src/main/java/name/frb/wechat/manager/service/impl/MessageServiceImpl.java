package name.frb.wechat.manager.service.impl;

import name.frb.wechat.server.dao.MessageDao;
import name.frb.wechat.server.model.TextMessage;
import name.frb.wechat.manager.service.MessageService;

import java.util.List;

public class MessageServiceImpl implements MessageService {
    private MessageDao msgDao;

    @Override
    public List<TextMessage> retrieveMessageList() {
        return msgDao.retrieveTextMessage();
    }

    //TODO  这里用来手工处理信息，回复的信息不单限于文本信息
    @Override
    public boolean addMessage(TextMessage textMessage) {
        String msgId = textMessage.getMsgId();
        boolean exist = msgDao.doesTextMessageExist(msgId);
        if (exist) {
            return true;
        }

        return msgDao.addTextMessage(textMessage);
    }

    public void setMsgDao(MessageDao msgDao) {
        this.msgDao = msgDao;
    }
}
