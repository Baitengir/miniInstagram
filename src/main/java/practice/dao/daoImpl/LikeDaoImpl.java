package practice.dao.daoImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import practice.config.HibernateConfig;
import practice.dao.LikeDao;
import practice.entities.Comment;
import practice.entities.Like;
import practice.entities.Post;
import practice.entities.User;
import java.time.LocalDate;

public class LikeDaoImpl implements LikeDao {
    private static final Log log = LogFactory.getLog(LikeDaoImpl.class);
    private EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactory();

    @Override
    public void addLikeForPost(Long postId, Long ownerUserId, Like like) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Post post = entityManager.find(Post.class, postId);
            User user = entityManager.find(User.class, ownerUserId);

            boolean alreadyLiked = post.getLikes().stream()
                    .anyMatch(l -> l.getUser().getId().equals(ownerUserId));

            if (!alreadyLiked) {
                like.setLikedDate(LocalDate.now());
                like.setPost(post);
                like.setUser(user);

                post.getLikes().add(like);
                post.setLikesCount(post.getLikesCount() + 1);

                entityManager.persist(like);
                entityManager.merge(post);
                System.out.println("like successfully added");
            } else {
                deleteLikeFromPost(postId, ownerUserId);
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
    public void deleteLikeFromPost(Long postId, Long ownerUserId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Post post = entityManager.find(Post.class, postId);
            User user = entityManager.find(User.class, ownerUserId);

            Like likeToRemove = post.getLikes().stream()
                    .filter(like -> like.getUser().getId().equals(user.getId()))
                    .findFirst()
                    .orElse(null);

            if (likeToRemove != null) {
                post.getLikes().remove(likeToRemove);
                entityManager.remove(likeToRemove);
            } 
            entityManager.getTransaction().commit();
            System.out.println("like successfully removed");
        } catch (HibernateException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void addLikeForComment(Long commentAuthorId, Long commentId, Like like) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            User commentAuthor = entityManager.find(User.class, commentAuthorId);
            Comment comment = entityManager.find(Comment.class, commentId);
            
            Like likeToRemove = comment.getLikes().stream()
                    .filter(l -> l.getUser().getId().equals(commentAuthorId))
                    .findFirst()
                    .orElse(null);
            
            if (likeToRemove == null){
                like.setComment(comment);
                like.setUser(commentAuthor);
                like.setLikedDate(LocalDate.now());
                
                comment.getLikes().add(like);
                comment.setLikesCount(comment.getLikesCount()+1);
                
                entityManager.persist(like);
                entityManager.merge(comment);
                entityManager.getTransaction().commit();
            } else {
                deleteLikeFromComment(commentId, commentAuthorId);
            }
            
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
    public void deleteLikeFromComment(Long commentId, Long commentAuthorId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            Comment comment = entityManager.find(Comment.class, commentId);
            User commentAuthor = entityManager.find(User.class, commentAuthorId);
            
            Like likeToRemove = comment.getLikes().stream()
                    .filter(l-> l.getUser().getId().equals(commentAuthor.getId()))
                    .findFirst()
                    .orElse(null);
            
            if (likeToRemove != null){
                comment.getLikes().remove(likeToRemove);
                entityManager.remove(likeToRemove);
            }
            entityManager.getTransaction().commit();
            System.out.println("like successfully removed");
            
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
