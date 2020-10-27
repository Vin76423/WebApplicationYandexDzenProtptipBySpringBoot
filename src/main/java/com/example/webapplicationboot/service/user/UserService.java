package com.example.webapplicationboot.service.user;

import com.example.webapplicationboot.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    void createUser(User user);
    List<User> getAllUsers();
    Optional<User> getUserById(long id);
    Optional<User> getUserByLogin(String login);
//    void updateUserById(User user, String field, String value);
    void deleteUserById(long id);
}
