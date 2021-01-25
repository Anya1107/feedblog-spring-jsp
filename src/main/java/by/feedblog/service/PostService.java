package by.feedblog.service;

import by.feedblog.dao.PostDao;
import by.feedblog.entity.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private PostDao postDao;

    public PostService(PostDao postDao) {
        this.postDao = postDao;
    }

    public boolean save(Post post){
        if(postDao.containsByTitle(post.getTitle())){
            return false;
        } else {
            postDao.save(post);
        }
        return true;
    }

    public boolean deleteById(int id){
        if(postDao.containsById(id)){
            postDao.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean deleteByTitle(String title){
        if(postDao.containsByTitle(title)){
            postDao.deleteByTitle(title);
            return true;
        }
        return false;
    }

    public List<Post> getAll(){
        return postDao.getAll();
    }

    public Post getById(int id){
        if(postDao.containsById(id)){
            return postDao.getById(id);
        }
        return null;
    }

    public Post getByTitle(String title){
        if(postDao.containsByTitle(title)){
            return postDao.getByTitle(title);
        }
        return null;
    }

    public List<Post> getAllCheckedPosts(){
        return postDao.getAllChecked();
    }

    public List<Post> getAllUncheckedPosts(){
        return postDao.getAllUnchecked();
    }

    public List<Post> getAllByUser(User user){
        return postDao.getAllByUser(user);
    }

    public List<Post> getAllByTag(Tag tag){
        return postDao.getAllByTag(tag);
    }

    public List<Post> getAllByCategory(Category category){
        return postDao.getAllByCategory(category);
    }

    public void addLike(Like like){
        postDao.addLike(like);
    }

    public void addDislike(Dislike dislike, Like like){
        postDao.addDislike(dislike, like);
    }

    public void addDislikeWithoutRemove(Dislike dislike){
        postDao.addDislikeWithoutRemove(dislike);
    }

    public List<Like> getLikes(){
        return postDao.getLikes();
    }

    public List<Dislike> getDislikes(){
        return postDao.getDislikes();
    }

    public void updateDescription(int id, String description){
        postDao.updateDescription(id, description);
    }

    public void updateTag(int id, Tag tag){
        postDao.updateTag(id, tag);
    }

    public void updateCategory(int id, Category category){
        postDao.updateCategory(id, category);
    }

    public Post search(String title){
        return postDao.getByTitle(title);
    }

    public void addCoolReaction( Reaction reaction){
        postDao.addCoolReaction( reaction);
    }

    public void addSadReaction(Reaction reaction){
        postDao.addSadReaction(reaction);
    }

    public List<Reaction> getCoolReactions(){
        return postDao.getCoolReactions();
    }

    public List<Reaction> getSadReactions(){
        return postDao.getSadReactions();
    }

    public void addChecked(Post post){
        postDao.addChecked(post);
    }

    public void setCountOfViews(Post post){
        postDao.setCountOfViews(post);
    }

    public int getCountOfViews(Post post){
        return postDao.getCountOfViews(post);
    }

    public String getCategoryName(Post post){
        return postDao.getCategoryName(post);
    }

    public String getTagName(Post post){
        return postDao.getTagName(post);
    }

    public List<Post> getAllByFollowUsers(User user){
        return postDao.getAllByFollowUsers(user);
    }
}
