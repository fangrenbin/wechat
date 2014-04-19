package name.frb.wechat.controller.manager;

import name.frb.wechat.server.model.TextMessage;
import name.frb.wechat.manager.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/message")
public class UserMsgController {
    @Resource
    private MessageService messageService;

    /**
     * 微信用户信息列表
     *
     * @return
     */
    @RequestMapping(value = "/messageList", method = RequestMethod.GET)
    public ModelAndView messageList() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("message/messageList");
        List<TextMessage> textMsgList = messageService.retrieveMessageList();
        mv.addObject("textMsgList", textMsgList);

        System.out.print(textMsgList.size());

        return mv;
    }

    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }
}
