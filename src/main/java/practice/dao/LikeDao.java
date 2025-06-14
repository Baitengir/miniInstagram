package practice.dao;
import org.springframework.stereotype.Repository;
import practice.entities.Like;
@Repository
public interface LikeDao {
    void addLikeForPost(Long postId, Long ownerUserId, Like like);
    void deleteLikeFromPost(Long postId, Long ownerUserId);
    void addLikeForComment(Long commentAuthorId, Long commentId, Like like);
    void deleteLikeFromComment(Long commentId, Long commentAuthorId);
}
