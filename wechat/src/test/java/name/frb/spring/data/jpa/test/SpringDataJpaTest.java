package name.frb.spring.data.jpa.test;

import name.frb.wechat.AbstractTestng;
import name.frb.wechat.controller.manager.PersonController;
import name.frb.wechat.manager.dto.PersonDTO;
import org.testng.annotations.Test;

import javax.annotation.Resource;

/**
 * Created by 斌 on 2014/4/29.
 */
public class SpringDataJpaTest extends AbstractTestng {
    @Resource
    private PersonController personController;

    @Test
    public void testCreate() {
        PersonDTO created = new PersonDTO("Fang", "Renbin");
        personController.createPerson(created);
    }
}
