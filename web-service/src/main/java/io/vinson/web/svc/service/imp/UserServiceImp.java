package io.vinson.web.svc.service.imp;

import io.vinson.web.svc.domain.User;
import io.vinson.web.svc.service.UserService;

import java.util.List;

public class UserServiceImp implements UserService {


    @Override
    public int addUser(User user) {
        return 0;
    }

    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public int updateUser(User user) {
        return 0;
    }

    @Override
    public User getUserByUsernameOrEmail(String account) {
        return null;
    }

    @Override
    public List<User> getUserOfFriends(int userId) {
        return null;
    }

    @Override
    public List<User> getGroupUsers(int groupId) {
        return null;
    }
}
