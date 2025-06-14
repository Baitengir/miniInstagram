package practice.service;

import org.springframework.stereotype.Service;
import practice.entities.Like;

public interface LikeService {
    void addLikeForPost(Long postId, Long ownerUserId, Like like);
    void deleteLikeFromPost(Long postId, Long ownerUserId);
    void addLikeForComment(Long commentAuthorId, Long commentId, Like like);
    void deleteLikeFromComment(Long commentId, Long commentAuthorId);
}
