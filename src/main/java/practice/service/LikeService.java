package practice.service;

import org.springframework.stereotype.Service;
import practice.entities.Like;

public interface LikeService {
    void addLikeForPost(Long postId, Long ownerUserId, Like like);
    void addLikeForComment(Long id, Like like);
}
