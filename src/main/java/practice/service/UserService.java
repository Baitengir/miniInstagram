package practice.service;

import org.springframework.stereotype.Service;
import practice.entities.User;


public interface UserService {
    void createUser(User user);
}
