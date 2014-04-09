package name.frb.wechat.service;

import name.frb.wechat.model.wechat.NewsMessage;

import java.util.List;

public interface NewsMsgService {
    boolean addNewsMsg(NewsMessage newsMessage);

    boolean updateNewsMsg(NewsMessage newsMessage);

    NewsMessage viewNewsMsg(long id);

    boolean deleteNewsMsg(long id);

    List<NewsMessage> retrieveNewsMassage();
}
