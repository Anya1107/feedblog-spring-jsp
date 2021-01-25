package by.feedblog.dao.inmemory;

import by.feedblog.dao.CommentDao;
import by.feedblog.entity.Comment;
import by.feedblog.entity.Post;
import by.feedblog.entity.User;

import java.util.ArrayList;
import java.util.List;


public class InMemoryCommentDao implements CommentDao {
    private List<Comment> comments = new ArrayList<>();

    @Override
    public void save(Comment comment) {
        comments.add(comment);
    }

    @Override
    public void deleteById(int id) {
        for (Comment comment : comments) {
            if(comment.getId() == id){
                comments.remove(comment);
                break;
            }
        }
    }

    @Override
    public Comment getById(int id) {
        for (Comment comment : comments) {
            if(comment.getId() == id){
                return comment;
            }
        }
        return null;
    }

    @Override
    public List<Comment> getAllByPost(Post post) {
        List<Comment> commentList = new ArrayList<>();
        for (Comment comment : comments) {
            if(comment.getPost().equals(post)){
                commentList.add(comment);
            }
        }
        return commentList;
    }

    @Override
    public List<Comment> getAllByUser(User user) {
        List<Comment> commentList = new ArrayList<>();
        for (Comment comment : comments) {
            if(comment.getUser().equals(user)){
                commentList.add(comment);
            }
        }
        return commentList;
    }

    @Override
    public List<Comment> getAll() {
        return comments;
    }

    public boolean containsById(int id){
        for (Comment comment : comments) {
            if(comment.getId() == id){
                return true;
            }
        }
        return false;
    }
}
