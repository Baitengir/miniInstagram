package practice.service.serviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practice.dao.UserDao;
import practice.dao.daoImpl.UserDaoImpl;
import practice.entities.User;
import practice.service.UserService;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao = new UserDaoImpl();

    @Override
    public void createUser(User user) {
        userDao.createUser(user);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void updateUserByID(Long id, User user) {
        userDao.updateUserByID(id, user);
    }

    @Override
    public void deleteUserById(Long id) {
        userDao.deleteUserById(id);
    }

    @Override
    public User getUserByMostPopularPost() {
        return userDao.getUserByMostPopularPost();
    }
}
