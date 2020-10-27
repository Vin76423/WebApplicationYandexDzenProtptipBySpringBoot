package com.example.webapplicationboot.service.user;

import com.example.webapplicationboot.entity.User;
import com.example.webapplicationboot.repository.UserRepository;
import com.example.webapplicationboot.service.user.exception.DuplicateUserException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Argument 'user' is null!");
        }
        if (userRepository.existsUserByLogin(user.getLogin())) {
            throw new DuplicateUserException();
        }
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(long id) {
        if (id < 1) {
            throw new IllegalArgumentException("Argument 'id' is negative or zero!");
        }
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        if (login == null) {
            throw new IllegalArgumentException("Argument 'login' is null!");
        }
        return userRepository.findByLogin(login);
    }

//    @Override
//    public void updateUserById(User user, String field, String value) {
//        if (field == null || value == null) {
//            throw new IllegalArgumentException("Arguments is null!");
//        }
//        if (!userDao.containUserById(user.getId())) {
//            throw new NotFoundUserByIdException();
//        }
//        switch (field) {
//            case "name":
//                user.setName(value);
//                break;
//            case "login":
//                user.setLogin(value);
//                break;
//            case "password":
//                user.setPassword(value);
//                break;
//            case "age":
//                user.setAge(Integer.parseInt(value));
//                break;
//        }
//        userRepository.updateUserById(user);
//    }

    @Override
    public void deleteUserById(long id) {
        if (id < 1) {
            throw new IllegalArgumentException("Argument 'id' is negative or zero!");
        }
        userRepository.deleteById(id);
    }
}
