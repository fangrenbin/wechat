package name.frb.wechat.controller.manager;

import name.frb.wechat.server.model.NewsMessage;
import name.frb.wechat.manager.service.NewsMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 管理新闻类消息
 */
@Controller
@RequestMapping(value = "/newsMsg")
public class NewsMsgController {
    private NewsMsgService newsMsgService;

    @RequestMapping(value = "/newsMsgList", method = RequestMethod.GET)
    public ModelAndView newsMsgList() {
        ModelAndView mv = new ModelAndView();

        List<NewsMessage> newsMessageList = newsMsgService.retrieveNewsMassage();
        mv.addObject("newsMessageList", newsMessageList);

        mv.setViewName("newsMsg/newsMsgList");

        return mv;
    }

    @RequestMapping(value = "/addNewsMsgPage", method = RequestMethod.GET)
    public ModelAndView addNewsMsgPage() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("newsMsg/addNewsMsg");

        return mv;
    }

    @RequestMapping(value = "addNewsMsg", method = RequestMethod.POST)
    public ModelAndView addNewsMsg(NewsMessage newsMessage) {
        ModelAndView mv = new ModelAndView();

        boolean success = newsMsgService.addNewsMsg(newsMessage);
        mv.setViewName("redirect:newsMsgList");

        return mv;
    }

    @RequestMapping(value = "updateNewsMsgPage", method = RequestMethod.GET)
    public ModelAndView updateNewsMsgPage(long id) {
        ModelAndView mv = new ModelAndView();

        NewsMessage newsMessage = newsMsgService.viewNewsMsg(id);

        mv.addObject("newsMessage", newsMessage);
        mv.setViewName("newsMsg/updateNewsMsg");

        return mv;
    }

    @RequestMapping(value = "updateNewsMsg", method = RequestMethod.POST)
    public ModelAndView updateNewsMsg(NewsMessage newsMessage) {
        ModelAndView mv = new ModelAndView();

        boolean success = newsMsgService.updateNewsMsg(newsMessage);
        mv.setViewName("redirect:newsMsgList");

        return mv;
    }

    @RequestMapping(value = "deleteNewsMsg", method = RequestMethod.GET)
    public ModelAndView deleteNewsMsg(long id) {
        ModelAndView mv = new ModelAndView();

        boolean success = newsMsgService.deleteNewsMsg(id);
        mv.setViewName("redirect:newsMsgList");

        return mv;
    }

    @RequestMapping(value = "viewNewsMsg", method = RequestMethod.GET)
    public ModelAndView viewNewsMsg(long id) {
        ModelAndView mv = new ModelAndView();

        NewsMessage newsMessage = newsMsgService.viewNewsMsg(id);
        mv.addObject("newsMessage", newsMessage);
        mv.setViewName("newsMsg/viewNewsMsg");

        return mv;
    }

    public void setNewsMsgService(NewsMsgService newsMsgService) {
        this.newsMsgService = newsMsgService;
    }
}
