package name.frb.wechat.persist.repository;

import name.frb.wechat.persist.model.WechatUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Wechat user repository
 */
public interface UserRepository extends JpaRepository<WechatUserInfo, Long> {
}
