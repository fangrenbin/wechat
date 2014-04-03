package name.frb.wechat.service;

import name.frb.wechat.model.wechat.TextMessage;

import java.util.List;

public interface MessageService {
    List<TextMessage> retrieveMessageList();

    boolean addMessage(TextMessage textMessage);
}
