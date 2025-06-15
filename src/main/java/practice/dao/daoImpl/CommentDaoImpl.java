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
import java.util.ArrayList;
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
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            Comment comment = entityManager.find(Comment.class, id);
            if (comment == null) {
                System.out.println("comment not found");
                return null;
            }
            return comment;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void updateCommentById(Long id, Comment comment) {
        if (comment == null || comment.getCommentText() == null) {
            System.out.println("comment is invalid, try again!");
            return;
        }

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Comment commentInDB = entityManager.find(Comment.class, id);
            if (commentInDB == null) {
                System.out.println("comment not found with id " + id);
            } else {
                commentInDB.setCommentText(comment.getCommentText());
                commentInDB.setChangedDate(LocalDate.now());
                entityManager.getTransaction().commit();
                System.out.println("comment successfully updated");
            }

        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }

    }

    @Override
    public void deleteCommentById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Comment comment = entityManager.find(Comment.class, id);
            if (comment == null) {
                System.out.println("comment not found with id " + id);
                return;
            }
            entityManager.remove(comment);
            entityManager.getTransaction().commit();
            System.out.println("comment successfully deleted with id " + id);
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Comment> getCommentsByUserId(Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            User user = entityManager.find(User.class, id);
            if (user == null) {
                System.out.println("user not found with id " + id);
                return new ArrayList<>();
            }
            return user.getComments();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Comment> getCommentsByPostId(Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            Post post = entityManager.find(Post.class, id);
            if (post == null) {
                System.out.println("post not found with id " + id);
                return new ArrayList<>();
            }
            return post.getComments();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Comment getPopularComment() {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            Comment comment = entityManager.createQuery("select c from Comment c order by likesCount", Comment.class)
                    .getSingleResult();
            if (comment == null) {
                System.out.println("Database for comment is null!");
                return null;
            }
            return comment;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Comment getPopularCommentByPostId(Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {

            List<Comment> comments = entityManager.createQuery(
                            "select c from Comment c where c.post.id = :id order by c.likesCount desc",
                            Comment.class)
                    .setParameter("id", id)
                    .setMaxResults(1)
                    .getResultList();

            if (comments.isEmpty()) {
                System.out.println("comments not found from post with id " + id);
                return null;
            }
            return comments.get(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Comment getPopularCommentByUserId(Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            return entityManager.createQuery(
                            "select c from Comment c where c.user.id = :id order by c.likesCount desc", Comment.class
                    ).setParameter("id", id)
                    .setMaxResults(1)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
