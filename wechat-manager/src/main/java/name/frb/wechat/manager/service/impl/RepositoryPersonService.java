package name.frb.wechat.manager.service.impl;

import name.frb.wechat.manager.dto.PersonDTO;
import name.frb.wechat.manager.repository.PersonRepository;
import name.frb.wechat.manager.service.PersonService;
import name.frb.wechat.manager.model.Person;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 斌 on 2014/4/29.
 */
@Service
public class RepositoryPersonService implements PersonService {
    @Resource
    private PersonRepository personRepository;

    @Override
    public Person create(PersonDTO created) {
        Person person = Person.getBuilder(created.getFirstName(), created.getLastName()).build();
        return personRepository.save(person);
    }

    @Override
    public Person delete(Long personId) {
        Person deleted = personRepository.findOne(personId);

        if (deleted == null) {
            System.out.println("person not found. personId=" + personId);

            return deleted;
        }

        personRepository.delete(deleted);

        return deleted;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Person findById(Long id) {
        return personRepository.findOne(id);
    }

    @Override
    public Person update(PersonDTO updated) {
        Person person = personRepository.findOne(updated.getId());

        if (person == null) {
            System.out.println("Person not found id=" + updated.getId());
            return person;
        }

        person.update(updated.getFirstName(), updated.getLastName());

        return person;
    }

    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
}
