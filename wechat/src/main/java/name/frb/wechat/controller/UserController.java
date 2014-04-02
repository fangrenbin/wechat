package name.frb.wechat.controller;

import name.frb.wechat.model.User;
import name.frb.wechat.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Resource
    UserService userService;

    @RequestMapping(value = "listUser", method = RequestMethod.GET)
    public ModelAndView listUser() {
        ModelAndView mv = new ModelAndView();
        List<User> userList = userService.retrieveUserList();
        mv.setViewName("listUser");
        mv.addObject("userList", userList);

        return mv;
    }

    @RequestMapping(value = "addUserPage", method = RequestMethod.GET)
    public ModelAndView addUserPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("addUser");

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


        mv.setViewName("viewUser");
        User user = userService.viewUser(id);
        mv.addObject("user", user);

        return mv;
    }

    @RequestMapping(value = "updateUserPage", method = RequestMethod.GET)
    public ModelAndView updateUserPage(long id) {
        ModelAndView mv = new ModelAndView();
        User user = userService.viewUser(id);

        mv.setViewName("updateUser");
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
