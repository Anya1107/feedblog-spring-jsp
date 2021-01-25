package by.feedblog.service;

import by.feedblog.dao.CommentDao;
import by.feedblog.entity.Comment;
import by.feedblog.entity.Post;
import by.feedblog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private CommentDao commentDao;

    public CommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public void save(Comment comment){
        commentDao.save(comment);
    }

    public boolean deleteById(int id){
        if(commentDao.containsById(id)){
            commentDao.deleteById(id);
            return true;
        }
        return false;
    }

    public Comment getById(int id){
        return commentDao.getById(id);
    }

    public List<Comment> getAll(){
        return commentDao.getAll();
    }

    public List<Comment> getAllByPost(Post post){
        return commentDao.getAllByPost(post);
    }

    public List<Comment> getAllByUser(User user){
        return commentDao.getAllByUser(user);
    }
}
