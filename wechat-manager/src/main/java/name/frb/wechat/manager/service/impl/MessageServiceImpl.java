package name.frb.wechat.manager.service.impl;

import name.frb.wechat.manager.service.MessageService;
import name.frb.wechat.server.dao.MessageDao;
import name.frb.wechat.server.dao.NcenglishDao;
import name.frb.wechat.server.model.TextMessage;

import java.util.List;

public class MessageServiceImpl implements MessageService {
    private MessageDao messageDao;
    private NcenglishDao ncenglishDao;

    @Override
    public List<TextMessage> retrieveMessageList() {
        return messageDao.retrieveTextMessage();
    }

    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public void setNcenglishDao(NcenglishDao ncenglishDao) {

        this.ncenglishDao = ncenglishDao;
    }
}
