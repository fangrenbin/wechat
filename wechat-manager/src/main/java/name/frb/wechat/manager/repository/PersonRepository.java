package name.frb.wechat.manager.repository;


import name.frb.wechat.manager.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by renbin.fang on 2014/4/29.
 */
public interface PersonRepository extends JpaRepository<Person, Long> {
}
