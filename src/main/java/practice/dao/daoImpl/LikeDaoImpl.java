package practice.dao.daoImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.HibernateException;
import practice.config.HibernateConfig;
import practice.dao.LikeDao;
import practice.entities.Like;
import practice.entities.Post;
import practice.entities.User;

import java.time.LocalDate;

public class LikeDaoImpl implements LikeDao {
    private EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactory();

    @Override
    public void addLikeForPost(Long postId,Long ownerUserId, Like like) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Post post = entityManager.find(Post.class, postId);
            User user = entityManager.find(User.class, ownerUserId);
            post.getLikes().add(like);
            like.setLikedDate(LocalDate.now());
            like.setPost(post);
            like.setUser(user);
            entityManager.persist(like);
            entityManager.getTransaction().commit();
            System.out.println("like successfully created");
        } catch (HibernateException e){
            if (entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            throw new HibernateException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void addLikeForComment(Long id, Like like) {

    }
}
