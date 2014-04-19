package name.frb.wechat.manager.service;

import name.frb.wechat.server.model.TextMessage;

import java.util.List;

public interface MessageService {
    List<TextMessage> retrieveMessageList();

    boolean addMessage(TextMessage textMessage);
}
