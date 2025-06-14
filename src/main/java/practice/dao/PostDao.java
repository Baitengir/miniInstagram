package practice.dao;

import org.springframework.stereotype.Repository;
import practice.entities.Post;
@Repository
public interface PostDao {
    void addPostByUserId(Long id, Post post);
    Post
}
