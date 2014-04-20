package name.frb.wechat.manager.service.impl;

import name.frb.wechat.manager.dao.NewsMsgDao;
import name.frb.wechat.manager.service.NewsMsgService;
import name.frb.wechat.server.model.NewsMessage;

import java.util.List;

public class NewsMsgServiceImpl implements NewsMsgService {
    private NewsMsgDao newsMsgDao;

    @Override
    public boolean addNewsMsg(NewsMessage newsMessage) {
        return newsMsgDao.addNewsMsg(newsMessage);
    }

    @Override
    public boolean updateNewsMsg(NewsMessage newsMessage) {
        return newsMsgDao.updateNewsMsg(newsMessage);
    }

    @Override
    public NewsMessage viewNewsMsg(long id) {
        return newsMsgDao.viewNewsMsg(id);
    }

    @Override
    public boolean deleteNewsMsg(long id) {
        return newsMsgDao.deleteNewsMsg(id);
    }

    @Override
    public List<NewsMessage> retrieveNewsMassage() {
        return newsMsgDao.retrieveNewsMsgList();
    }

    public void setNewsMsgDao(NewsMsgDao newsMsgDao) {
        this.newsMsgDao = newsMsgDao;
    }
}
