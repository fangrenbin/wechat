package name.frb.wechat.manager.service;

import name.frb.wechat.persist.model.WechatUserInfo;

import java.util.List;

public interface WechatUserManager {
    List<WechatUserInfo> userList();

    WechatUserInfo viewUser(Long id);
}
