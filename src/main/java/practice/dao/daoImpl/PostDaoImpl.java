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
        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        try{
//            entityManager.createQuery("select p from Post p order by li")
//        }
        return List.of();
    }

    @Override
    public void deletePostById(Long id) {

    }
}
