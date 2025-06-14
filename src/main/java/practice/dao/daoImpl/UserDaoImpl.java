package practice.dao.daoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.HibernateException;
import practice.config.HibernateConfig;
import practice.dao.UserDao;
import practice.entities.User;

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
        } catch (HibernateException e){
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new HibernateException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }
}
