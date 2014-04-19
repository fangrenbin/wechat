package name.frb.wechat.manager.service;

import name.frb.wechat.server.model.NewsMessage;

import java.util.List;

public interface NewsMsgService {
    boolean addNewsMsg(NewsMessage newsMessage);

    boolean updateNewsMsg(NewsMessage newsMessage);

    NewsMessage viewNewsMsg(long id);

    boolean deleteNewsMsg(long id);

    List<NewsMessage> retrieveNewsMassage();
}
