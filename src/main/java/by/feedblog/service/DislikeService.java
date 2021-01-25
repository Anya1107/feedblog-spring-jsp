package by.feedblog.service;

import by.feedblog.dao.DislikeDao;
import by.feedblog.entity.Dislike;
import by.feedblog.entity.Post;
import by.feedblog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DislikeService {

    @Autowired
    private DislikeDao dislikeDao;

    public DislikeService(DislikeDao dislikeDao) {
        this.dislikeDao = dislikeDao;
    }

    public void add(Dislike dislike){
        dislikeDao.add(dislike);
    }

    public List<String> getUsers(){
        return dislikeDao.getUsers();
    }

    public int getCount(Post post){
        return dislikeDao.getCount(post);
    }
}
