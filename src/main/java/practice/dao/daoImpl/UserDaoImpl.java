package practice.dao.daoImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.HibernateException;
import practice.config.HibernateConfig;
import practice.dao.UserDao;
import practice.entities.User;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private final EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactory();

    @Override
    public void createUser(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            System.out.println("user saved");
        } catch (HibernateException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new HibernateException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public User getUserById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            User user = entityManager.find(User.class, id);
            entityManager.getTransaction().commit();
            return user;
        } catch (HibernateException e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            throw new HibernateException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            return entityManager.createQuery("select u from User u", User.class)
                    .getResultList();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void updateUserByID(Long id, User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            User userInDb = entityManager.find(User.class, id);
            if (userInDb != null) {
                userInDb.setFirstName(user.getFirstName());
                userInDb.setLastName(user.getLastName());
                userInDb.setEmail(user.getEmail());
                userInDb.setPhoneNumber(user.getPhoneNumber());
                userInDb.setDateOfBirth(user.getDateOfBirth());
                entityManager.getTransaction().commit();
                System.out.println("user successfully updated");
            } else {
                entityManager.getTransaction().rollback();
                System.out.println("user with id- " + id + " not found!");
            }
        } catch (HibernateException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new HibernateException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void deleteUserById(Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            User user = entityManager.find(User.class, id);
            if (user != null) {
                entityManager.remove(user);
                entityManager.getTransaction().commit();
                System.out.println("user successfully deleted with id- " + id);
            } else {
                entityManager.getTransaction().rollback();
                System.out.println("user not found with id - " + id);
            }
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }
}
