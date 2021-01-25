package by.feedblog.service;

import by.feedblog.dao.LikeDao;
import by.feedblog.entity.Like;
import by.feedblog.entity.Post;
import by.feedblog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {

    @Autowired
    private LikeDao likeDao;

    public LikeService(LikeDao likeDao) {
        this.likeDao = likeDao;
    }

    public void add(Like like){
        likeDao.add(like);
    }

    public List<String> getUsers(){
        return likeDao.getUsers();
    }

    public int getCount(Post post){
        return likeDao.getCount(post);
    }
}
