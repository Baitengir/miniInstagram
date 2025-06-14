package practice.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practice.dao.PostDao;
import practice.dao.daoImpl.PostDaoImpl;
import practice.entities.Post;

@Service
public class PostServiceImpl implements practice.service.PostService {
    @Autowired
    PostDao postDao = new PostDaoImpl();
    @Override
    public void addPostByUserId(Long id, Post post) {
        postDao.addPostByUserId(id, post);
    }


}
