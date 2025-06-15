package practice.service;

import org.springframework.stereotype.Service;
import practice.entities.User;

import java.util.List;


public interface UserService {
    void createUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    void updateUserByID(Long id, User user);
    void deleteUserById(Long id);
    public User getUserByMostPopularPost();
}
