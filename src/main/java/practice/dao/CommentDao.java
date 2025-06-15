package practice.dao;

import org.springframework.stereotype.Repository;
import practice.entities.Comment;

import java.util.List;

@Repository
public interface CommentDao {
    void createComment(Long userId, Long postId, Comment comment);

    Comment getCommentById(Long id);

    void updateCommentById(Long id, Comment comment);

    void deleteCommentById(Long id);

    List<Comment> getCommentsByUserId(Long id);

    List<Comment> getCommentsByPostId(Long id);

    Comment getPopularComment();

    Comment getPopularCommentByPostId(Long id);

    Comment getPopularCommentByUserId(Long id);
}
