package by.feedblog.service;

import by.feedblog.dao.ReactionDao;
import by.feedblog.entity.Post;
import by.feedblog.entity.Reaction;
import by.feedblog.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReactionService {
    private ReactionDao reactionDao;

    public ReactionService(ReactionDao reactionDao) {
        this.reactionDao = reactionDao;
    }

    public void add(Reaction reaction){
        reactionDao.add(reaction);
    }

    public void deleteById(int id){
        reactionDao.deleteById(id);
    }

    public void reaction(Reaction reaction, Post post, User user){
        reactionDao.reaction(reaction, post, user);
    }

    public Reaction getById(int id){
        return reactionDao.getById(id);
    }

    public List<Reaction> getAllByPost(Post post){
        return reactionDao.getAllByPost(post);
    }

    public List<Reaction> getALl(){
        return reactionDao.getAll();
    }

    public List<Reaction> getAllForModer(){
        return reactionDao.getAllForModer();
    }

    public int getCount( Post post){
        return reactionDao.getCount(post);
    }
}
