package name.frb.wechat.controller.manager;

import name.frb.wechat.persist.model.NewsMessage;
import name.frb.wechat.manager.service.NewsMsgService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * 管理新闻类消息
 */
@Controller
@RequestMapping(value = "/newsMsg")
public class NewsMsgController {
//    @Resource(name = "newsMsgService")
    private NewsMsgService newsMsgService;

    @RequestMapping(value = "/newsMsgList", method = RequestMethod.GET)
    public ModelAndView newsMsgList() {
        List<NewsMessage> newsMessageList = newsMsgService.retrieveNewsMassage();

        return new ModelAndView("newsMsg/newsMsgList").addObject("newsMessageList", newsMessageList);
    }

    @RequestMapping(value = "/addNewsMsgPage", method = RequestMethod.GET)
    public ModelAndView addNewsMsgPage() {
        return new ModelAndView("newsMsg/addNewsMsg");
    }

    @RequestMapping(value = "addNewsMsg", method = RequestMethod.POST)
    public ModelAndView addNewsMsg(NewsMessage newsMessage) {
        boolean success = newsMsgService.addNewsMsg(newsMessage);

        return new ModelAndView("redirect:newsMsgList");
    }

    @RequestMapping(value = "updateNewsMsgPage", method = RequestMethod.GET)
    public ModelAndView updateNewsMsgPage(long id) {
        NewsMessage newsMessage = newsMsgService.viewNewsMsg(id);

        return new ModelAndView("newsMsg/updateNewsMsg").addObject("newsMessage", newsMessage);
    }

    @RequestMapping(value = "updateNewsMsg", method = RequestMethod.POST)
    public ModelAndView updateNewsMsg(NewsMessage newsMessage) {
        boolean success = newsMsgService.updateNewsMsg(newsMessage);

        return new ModelAndView("redirect:newsMsgList");
    }

    @RequestMapping(value = "deleteNewsMsg", method = RequestMethod.GET)
    public ModelAndView deleteNewsMsg(long id) {
        boolean success = newsMsgService.deleteNewsMsg(id);

        return new ModelAndView("redirect:newsMsgList");
    }

    @RequestMapping(value = "viewNewsMsg", method = RequestMethod.GET)
    public ModelAndView viewNewsMsg(long id) {
        NewsMessage newsMessage = newsMsgService.viewNewsMsg(id);

        return new ModelAndView("newsMsg/viewNewsMsg").addObject("newsMessage", newsMessage);
    }

    public void setNewsMsgService(NewsMsgService newsMsgService) {
        this.newsMsgService = newsMsgService;
    }
}