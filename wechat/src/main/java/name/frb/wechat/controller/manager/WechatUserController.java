package name.frb.wechat.controller.manager;

import name.frb.wechat.manager.service.WechatUserManager;
import name.frb.wechat.persist.model.WechatUserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Wehcat user manager controller
 */
@Controller
@RequestMapping(value = "/wechatUser")
public class WechatUserController {
    @Resource(name = "wechatUserManager")
    private WechatUserManager wechatUserManager;

    /**
     * Retrieve user list
     *
     * @return User list
     */
    @RequestMapping(value = "userList", method = RequestMethod.GET)
    public ModelAndView userList() {
        List<WechatUserInfo> userList = wechatUserManager.userList();
        return new ModelAndView("wechatUser/userList").addObject("userList", userList);
    }

    /**
     * View user info
     *
     * @param id user id
     * @return User info
     */
    @RequestMapping(value = "viewUser", method = RequestMethod.GET)
    public ModelAndView viewUser(Long id) {
        WechatUserInfo userInfo = wechatUserManager.viewUser(id);

        return new ModelAndView("wechatUser/viewUser").addObject("userInfo", userInfo);
    }

    /**
     * Setter
     *
     * @param wechatUserManager
     */
    public void setWechatUserManager(WechatUserManager wechatUserManager) {
        this.wechatUserManager = wechatUserManager;
    }
}
