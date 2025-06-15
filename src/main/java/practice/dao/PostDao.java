package practice.dao;
import org.springframework.stereotype.Repository;
import practice.entities.Post;
import java.util.List;

@Repository
public interface PostDao {
    void addPostByUserId(Long id, Post post);

    Post getPostById(Long id);

    List<Post> getAllPostsByUserId(Long id);

    List<Post> getSortedPostsByLike(String ascOrDesc);

    void deletePostById(Long id);

    Post getMostPopularPost();

    Post getMostPopularPostByCommentCount();
}
