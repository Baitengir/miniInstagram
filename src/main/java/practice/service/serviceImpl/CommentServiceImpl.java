package practice.service.serviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practice.dao.CommentDao;
import practice.dao.daoImpl.CommentDaoImpl;
import practice.entities.Comment;
import practice.service.CommentService;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDao commentDao = new CommentDaoImpl();
    @Override
    public void createComment(Long userId, Long postId, Comment comment) {
        commentDao.createComment(userId, postId, comment);
    }

    @Override
    public Comment getCommentById(Long id) {
        return commentDao.getCommentById(id);
    }

    @Override
    public void updateCommentById(Long id, Comment comment) {
        commentDao.updateCommentById(id, comment);
    }

    @Override
    public void deleteCommentById(Long id) {
        commentDao.deleteCommentById(id);
    }

    @Override
    public List<Comment> getCommentsByUserId(Long id) {
        return commentDao.getCommentsByUserId(id);
    }

    @Override
    public List<Comment> getCommentsByPostId(Long id) {
        return commentDao.getCommentsByPostId(id);
    }

    @Override
    public Comment getPopularComment() {
        return commentDao.getPopularComment();
    }

    @Override
    public Comment getPopularCommentByPostId(Long id) {
        return commentDao.getPopularCommentByPostId(id);
    }

    @Override
    public Comment getPopularCommentByUserId(Long id) {
        return commentDao.getPopularCommentByUserId(id);
    }
}
