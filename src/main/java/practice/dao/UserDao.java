package practice.dao;
import org.springframework.stereotype.Repository;
import practice.entities.User;

@Repository
public interface UserDao {
    void createUser(User user);

}
