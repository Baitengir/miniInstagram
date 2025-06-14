package practice.dao;

import org.springframework.stereotype.Repository;
import practice.entities.Comment;
@Repository
public interface CommentDao {
    void createComment(Long userId, Long postId, Comment comment);
}
