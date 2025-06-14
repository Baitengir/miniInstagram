package practice.service.serviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practice.dao.LikeDao;
import practice.dao.daoImpl.LikeDaoImpl;
import practice.entities.Like;
import practice.service.LikeService;
@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    LikeDao likeDao = new LikeDaoImpl();
    @Override
    public void addLikeForPost(Long userId,Long ownerUserId, Like like) {
        likeDao.addLikeForPost(userId, ownerUserId, like);
    }

    @Override
    public void addLikeForComment(Long id, Like like) {

    }
}
