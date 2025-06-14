package practice.service.serviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practice.dao.UserDao;
import practice.dao.daoImpl.UserDaoImpl;
import practice.entities.User;
import practice.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao = new UserDaoImpl();

    @Override
    public void createUser(User user) {
        userDao.createUser(user);
    }
}
