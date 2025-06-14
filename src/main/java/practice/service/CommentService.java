package practice.service;

import org.springframework.stereotype.Service;
import practice.entities.Comment;

public interface CommentService {
    void createComment(Long userId, Long postId, Comment comment);

}
