package name.frb.wechat.manager.service.impl;

import name.frb.wechat.manager.service.WechatUserManager;
import name.frb.wechat.persist.model.WechatUserInfo;
import name.frb.wechat.persist.repository.WechatUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WechatUserManagerImpl implements WechatUserManager {
    @Autowired
    private WechatUserRepository wechatUserRepository;

    @Transactional(readOnly = true)
    @Override
    public List<WechatUserInfo> userList() {
        return wechatUserRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public WechatUserInfo viewUser(Long id) {
        return wechatUserRepository.findOne(id);
    }

    public void setWechatUserRepository(WechatUserRepository wechatUserRepository) {
        this.wechatUserRepository = wechatUserRepository;
    }
}
