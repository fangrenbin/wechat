package name.frb.wechat.manager.service;


import name.frb.wechat.manager.model.User;

import java.util.List;

public interface UserService {
    long addUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(long id);

    List<User> retrieveUserList();

    User viewUser(long id);
}
