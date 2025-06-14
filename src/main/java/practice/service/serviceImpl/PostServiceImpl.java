package practice.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practice.dao.PostDao;
import practice.dao.daoImpl.PostDaoImpl;
import practice.entities.Post;

import java.util.List;

@Service
public class PostServiceImpl implements practice.service.PostService {
    @Autowired
    PostDao postDao = new PostDaoImpl();
    @Override
    public void addPostByUserId(Long id, Post post) {
        postDao.addPostByUserId(id, post);
    }

    @Override
    public Post getPostById(Long id) {
        return postDao.getPostById(id);
    }

    @Override
    public List<Post> getAllPostsByUserId(Long id) {
        return postDao.getAllPostsByUserId(id);
    }

    @Override
    public List<Post> getSortedPostsByLike(String ascOrDesc) {
        return postDao.getSortedPostsByLike(ascOrDesc);
    }

    @Override
    public void deletePostById(Long id) {
        postDao.deletePostById(id);
    }

}
