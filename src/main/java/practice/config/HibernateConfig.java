package practice.config;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import practice.entities.Comment;
import practice.entities.Like;
import practice.entities.Post;
import practice.entities.User;
import java.util.Properties;

public class HibernateConfig {
    public static EntityManagerFactory getEntityManagerFactory() {
        try {
            Properties properties = new Properties();
            properties.put(Environment.JAKARTA_JDBC_URL, "jdbc:postgresql://localhost:5432/postgres");
            properties.put(Environment.JAKARTA_JDBC_USER, "postgres");
            properties.put(Environment.JAKARTA_JDBC_PASSWORD, "busubaha");

            properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
            properties.put(Environment.JAKARTA_JDBC_DRIVER, "org.postgresql.Driver");
            properties.put(Environment.HBM2DDL_AUTO, "update");
            properties.put(Environment.SHOW_SQL, "true");

            Configuration configuration = new Configuration();
            configuration.setProperties(properties);
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Post.class);
            configuration.addAnnotatedClass(Comment.class);
//            configuration.addAnnotatedClass(Like.class);
            return configuration.buildSessionFactory().unwrap(EntityManagerFactory.class);
        } catch (HibernateException e) {
            throw new HibernateException(e.getMessage());
        }
    }
}
