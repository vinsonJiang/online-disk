package io.vinson.web.svc.service;

import io.vinson.web.svc.domain.User;

import java.util.List;

public interface UserService {

    public int addUser(User user);

    public User getUserById(int id);

    public int updateUser(User user);

    public User getUserByUsernameOrEmail(String account);

    public List<User> getUserOfFriends(int userId);

    public List<User> getGroupUsers(int groupId);

}
