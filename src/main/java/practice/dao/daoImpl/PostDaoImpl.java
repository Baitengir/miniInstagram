package practice.dao.daoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.HibernateException;
import practice.config.HibernateConfig;
import practice.dao.PostDao;
import practice.entities.Post;
import practice.entities.User;
import java.time.LocalDate;

public class PostDaoImpl implements PostDao {
    private final EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactory();
    @Override
    public void addPostByUserId(Long id, Post post) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            User user = entityManager.find(User.class,id);
            post.setUser(user);
            post.setCreateDate(LocalDate.now());
            entityManager.persist(post);
            user.getPosts().add(post);
            entityManager.getTransaction().commit();
            System.out.println("success");
        } catch (HibernateException e){
            if (entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            throw new HibernateException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }
}
