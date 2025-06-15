package practice.dao.daoImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.HibernateException;
import practice.config.HibernateConfig;
import practice.dao.PostDao;
import practice.entities.Post;
import practice.entities.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public Post getPostById(Long id) {
        try(EntityManager entityManager = entityManagerFactory.createEntityManager()){
             return entityManager.find(Post.class, id);
        } catch (HibernateException e){
            return new Post();
        }
    }

    @Override
    public List<Post> getAllPostsByUserId(Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()){
           return entityManager.createQuery(
                    "select p from Post p join p.user u where u.id = :userId", Post.class)
                    .setParameter("userId", id)
                    .getResultList();
        } catch (HibernateException e){
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
//        // todo with nativeQuery
//        try (EntityManager entityManager = entityManagerFactory.createEntityManager()){
//            return entityManager.createNativeQuery(
//                    "select p.* from posts p join users u on p.user_id = u.id where u.id = ?", Post.class)
//                    .setParameter(1, id)
//                    .getResultList();
//        } catch (HibernateException e){
//            System.out.println(e.getMessage());
//            return new ArrayList<>();
//        }
    }

    @Override
    public List<Post> getSortedPostsByLike(String ascOrDesc) {
        if (ascOrDesc == null || !ascOrDesc.equals("asc") && !ascOrDesc.equals("desc")) {
            System.out.println("invalid parameter, use (asc / desc)! ");
            return new ArrayList<>();
        }

        try (EntityManager entityManager = entityManagerFactory.createEntityManager()){
            String hql = "select p from Post p order by p.likesCount "+ ascOrDesc;
            return entityManager.createQuery(hql, Post.class)
                    .getResultList();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return List.of();
        }
    }

    @Override
    public void deletePostById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Post post = entityManager.find(Post.class, id);
            entityManager.remove(post);
            entityManager.getTransaction().commit();
            System.out.println("successfully deleted post with id " + id);
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
    public Post getMostPopularPost() {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()){
            return entityManager.createQuery(
                    "select p from Post p order by p.likesCount desc",
                    Post.class)
                    .setMaxResults(1)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Post getMostPopularPostByCommentCount() {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()){
            return  entityManager.createQuery(
                    "select p from Post p order by p.commentsCount desc",
                    Post.class)
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
