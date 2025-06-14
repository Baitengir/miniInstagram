package practice.service;

import practice.entities.Post;

public interface PostService {
    void addPostByUserId(Long id, Post post);
}
