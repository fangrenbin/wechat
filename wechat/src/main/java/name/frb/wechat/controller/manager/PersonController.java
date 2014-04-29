package name.frb.wechat.controller.manager;

import name.frb.wechat.manager.dto.PersonDTO;
import name.frb.wechat.manager.service.impl.RepositoryPersonService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by renbin.fang on 2014/4/29.
 */
@Controller
@RequestMapping(value = "/person")
public class PersonController {
    @Resource(name = "personService")
    private RepositoryPersonService personService;

    @RequestMapping(value = "createPerson", method = RequestMethod.POST)
    public ModelAndView createPerson(PersonDTO created) {
        personService.create(created);

        return new ModelAndView("createPerson");
    }

    public void setPersonService(RepositoryPersonService personService) {
        this.personService = personService;
    }
}
