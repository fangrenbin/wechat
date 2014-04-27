package name.frb.wechat.manager.service;

import name.frb.wechat.manager.vo.UserMessageListVo;

import java.util.List;

public interface MessageService {
    List<UserMessageListVo> retrieveMessageList();
}
