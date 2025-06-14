package practice.dao;
import org.springframework.stereotype.Repository;
import practice.entities.Like;
@Repository
public interface LikeDao {
    void addLikeForPost(Long postId, Long ownerUserId, Like like);
    void addLikeForComment(Long id, Like like);
}
