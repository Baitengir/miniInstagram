package practice.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practice.dao.CommentDao;
import practice.dao.UserDao;
import practice.dao.daoImpl.CommentDaoImpl;
import practice.dao.daoImpl.UserDaoImpl;
import practice.entities.Comment;
import practice.service.CommentService;
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDao commentDao = new CommentDaoImpl();
    @Override
    public void createComment(Long userId, Long postId, Comment comment) {
        commentDao.createComment(userId, postId, comment);
    }
}
