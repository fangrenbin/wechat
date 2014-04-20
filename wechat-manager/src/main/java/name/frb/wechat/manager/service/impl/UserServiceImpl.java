package name.frb.wechat.manager.service.impl;


import name.frb.wechat.manager.dao.UserDao;
import name.frb.wechat.manager.model.User;
import name.frb.wechat.manager.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    @Override
    public long addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public boolean updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public boolean deleteUser(long id) {
        return userDao.deleteUser(id);
    }

    @Override
    public List<User> retrieveUserList() {
        return userDao.retrieveUserList();
    }

    @Override
    public User viewUser(long id) {
        return userDao.viewUser(id);
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
