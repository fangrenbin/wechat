package name.frb.wechat.controller.manager;

import name.frb.wechat.manager.model.User;
import name.frb.wechat.manager.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统用户管理
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Resource(name = "userService")
    UserService userService;

    @RequestMapping(value = "listUser", method = RequestMethod.GET)
    public ModelAndView listUser() {
        ModelAndView mv = new ModelAndView();
        List<User> userList = userService.retrieveUserList();
        mv.setViewName("user/listUser");
        mv.addObject("userList", userList);

        return mv;
    }

    @RequestMapping(value = "addUserPage", method = RequestMethod.GET)
    public ModelAndView addUserPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/addUser");

        return mv;
    }

    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public ModelAndView addUser(User user) {
        ModelAndView mv = new ModelAndView();
        long id = userService.addUser(user);

        mv.setViewName("redirect:viewUser");
        mv.addObject("id", id);
        user = userService.viewUser(id);
        mv.addObject("user", user);

        return mv;
    }

    @RequestMapping(value = "viewUser", method = RequestMethod.GET)
    public ModelAndView viewUser(long id) {
        ModelAndView mv = new ModelAndView();


        mv.setViewName("user/viewUser");
        User user = userService.viewUser(id);
        mv.addObject("user", user);

        return mv;
    }

    @RequestMapping(value = "updateUserPage", method = RequestMethod.GET)
    public ModelAndView updateUserPage(long id) {
        ModelAndView mv = new ModelAndView();
        User user = userService.viewUser(id);

        mv.setViewName("user/updateUser");
        mv.addObject("user", user);

        return mv;
    }

    @RequestMapping(value = "updateUser", method = RequestMethod.POST)
    public ModelAndView updateUser(User user) {
        ModelAndView mv = new ModelAndView();

        userService.updateUser(user);
        mv.setViewName("redirect:listUser");

        return mv;
    }

    @RequestMapping(value = "deleteUser", method = RequestMethod.GET)
    public ModelAndView deleteUser(long id) {
        ModelAndView mv = new ModelAndView();

        userService.deleteUser(id);
        mv.setViewName("redirect:listUser");

        return mv;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
