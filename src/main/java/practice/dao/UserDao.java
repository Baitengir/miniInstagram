package practice.dao;
import org.springframework.stereotype.Repository;
import practice.entities.User;
import java.util.List;

@Repository
public interface UserDao {
    void createUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    void updateUserByID(Long id, User user);
    void deleteUserById(Long id);
    User getUserByMostPopularPost();

}
