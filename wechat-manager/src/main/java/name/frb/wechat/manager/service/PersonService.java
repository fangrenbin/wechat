package name.frb.wechat.manager.service;

import name.frb.wechat.manager.dto.PersonDTO;
import name.frb.wechat.manager.model.Person;

import java.util.List;

/**
 * Created by 斌 on 2014/4/29.
 */
public interface PersonService {
    Person create(PersonDTO created);

    Person delete(Long personId);

    List<Person> findAll();

    Person findById(Long id);

    Person update(PersonDTO updated);
}
