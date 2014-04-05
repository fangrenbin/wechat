package name.frb.wechat.service.impl;

import name.frb.wechat.dao.MsgDao;
import name.frb.wechat.model.wechat.TextMessage;
import name.frb.wechat.service.MessageService;

import java.util.List;

public class MessageServiceImpl implements MessageService {
    private MsgDao msgDao;

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

    public void setMsgDao(MsgDao msgDao) {
        this.msgDao = msgDao;
    }
}
