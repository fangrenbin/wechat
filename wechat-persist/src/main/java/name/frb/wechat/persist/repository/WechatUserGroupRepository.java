package name.frb.wechat.persist.repository;

import name.frb.wechat.persist.model.WechatUserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Wechat user group repository
 * Created by renbin.fang on 2014/5/13.
 */
public interface WechatUserGroupRepository extends JpaRepository<WechatUserGroup, Long> {
}
