package practice.dao.daoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.HibernateException;
import practice.config.HibernateConfig;
import practice.dao.CommentDao;
import practice.entities.Comment;
import practice.entities.Post;
import practice.entities.User;

import java.time.LocalDate;
import java.util.List;

public class CommentDaoImpl implements CommentDao {
    private final EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactory();

    @Override
    public void createComment(Long userId, Long postId, Comment comment) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            User user = entityManager.find(User.class, userId);
            Post post = entityManager.find(Post.class, postId);
            comment.setUser(user);
            comment.setPost(post);
            comment.setCreateDate(LocalDate.now());
            entityManager.persist(comment);
            post.setCommentsCount(post.getCommentsCount() + 1);
            post.getComments().add(comment);
            user.getComments().add(comment);
            entityManager.getTransaction().commit();
            System.out.println("success");
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
    public Comment getCommentById(Long id) {
        return null;
    }

    @Override
    public void updateCommentById(Long id, Comment comment) {

    }

    @Override
    public void deleteCommentById(Long id) {

    }

    @Override
    public List<Comment> getCommentsByUserId(Long id) {
        return List.of();
    }

    @Override
    public List<Comment> getCommentsByPostId(Long id) {
        return List.of();
    }

    @Override
    public Comment getPopularComment() {
        return null;
    }

    @Override
    public List<Comment> getPopularCommentsByPostId(Long id) {
        return List.of();
    }

    @Override
    public Comment getPopularCommentByUserId(Long id) {
        return null;
    }
}
