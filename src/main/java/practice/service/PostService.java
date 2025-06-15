package practice.service;

import practice.entities.Post;

import java.util.List;

public interface PostService {
    void addPostByUserId(Long id, Post post);
    Post getPostById (Long id);
    List<Post> getAllPostsByUserId (Long id);
    List<Post> getSortedPostsByLike (String ascOrDesc);
    void deletePostById(Long id);
    Post getMostPopularPostByCommentCount();
}
