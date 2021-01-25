package by.feedblog.dao.inmemory;

import by.feedblog.dao.PostDao;
import by.feedblog.entity.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InMemoryPostDao implements PostDao {
    private List<Post> posts = new ArrayList<>();
    private List<Like> likes = new ArrayList<>();
    private List<Dislike> dislikes = new ArrayList<>();
    private List<Reaction> coolReaction = new ArrayList<>();
    private List<Reaction> sadReaction = new ArrayList<>();

    @Override
    public void save(Post post) {
        posts.add(post);
    }

    @Override
    public void deleteById(int id) {
        for (Post post : posts) {
            if(post.getId()==id){
                posts.remove(post);
                break;
            }
        }
    }

    @Override
    public void deleteByTitle(String title) {
        for (Post post : posts) {
            if(post.getTitle().equals(title)){
                posts.remove(post);
                break;
            }
        }
    }

    @Override
    public List<Post> getAll() {
        return posts;
    }

    @Override
    public Post getById(int id) {
        for (Post post : posts) {
            if(post.getId()==id){
                return post;
            }
        }
        return null;
    }

    @Override
    public Post getByTitle(String title) {
        for (Post post : posts) {
            if(post.getTitle().equals(title)){
                return post;
            }
        }
        return null;
    }

    @Override
    public List<Post> getAllByUser(User user) {
        List<Post> postList = new ArrayList<>();
        for (Post post : posts) {
            if(post.getUser().equals(user)){
                postList.add(post);
            }
        }
        return postList;
    }

    @Override
    public List<Post> getAllByTag(Tag tag) {
        List<Post> postList = new ArrayList<>();
        for (Post post : posts) {
            if(post.getTag().equals(tag)){
                postList.add(post);
            }
        }
        return postList;
    }

    @Override
    public List<Post> getAllByCategory(Category category) {
        List<Post> postList = new ArrayList<>();
        for (Post post : posts) {
            if(post.getCategory().equals(category)){
                postList.add(post);
            }
        }
        return postList;
    }

    @Override
    public List<Post> getAllChecked() {
        List<Post> postList = new ArrayList<>();
        for (Post post : posts) {
            if(post.isChecked()){
                postList.add(post);
                postList.sort(Collections.reverseOrder());
            }
        }
        return postList;
    }

    @Override
    public List<Post> getAllUnchecked() {
        List<Post> postList = new ArrayList<>();
        for (Post post : posts) {
            if(!post.isChecked()){
                postList.add(post);
            }
        }
        return postList;
    }

    @Override
    public boolean containsById(int id) {
        for (Post post : posts) {
            if(post.getId()==id){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsByTitle(String title) {
        for (Post post : posts) {
            if(post.getTitle().equals(title)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void addLike(Like like) {
        if(likes.size() == 0){
            likes.add(like);
        } else {
            if(likes.get(likes.size() - 1).getUser() != like.getUser()){
                likes.add(like);
            }
        }
    }

    @Override
    public void addDislike(Dislike dislike, Like like) {
        if(dislikes.size() == 0){
            dislikes.add(dislike);
            likes.remove(like);
        } else {
           if(dislikes.get(dislikes.size() - 1).getUser() != dislike.getUser()){
               dislikes.add(dislike);
               likes.remove(like);
           }
        }
    }

    @Override
    public void addDislikeWithoutRemove(Dislike dislike) {
        if(dislikes.get(dislikes.size() - 1).getUser() != dislike.getUser()){
            dislikes.add(dislike);
        }
    }

    @Override
    public List<Like> getLikes() {
        return likes;
    }

    @Override
    public List<Dislike> getDislikes() {
        return dislikes;
    }

    @Override
    public void updateDescription(int id, String description) {
        for (Post post : posts) {
            if(post.getId() == id){
                post.setDescription(description);
                post.setChecked(false);
            }
        }
    }

    @Override
    public void updateTag(int id, Tag tag) {
        for (Post post : posts) {
            if(post.getId() == id){
                post.setTag(tag);
                post.setChecked(false);
            }
        }
    }

    @Override
    public void updateCategory(int id, Category category) {
        for (Post post : posts) {
            if(post.getId() == id){
                post.setCategory(category);
                post.setChecked(false);
            }
        }
    }

    @Override
    public void addCoolReaction(Reaction reaction) {
        if(coolReaction.size() == 0){
            coolReaction.add(reaction);
        } else {
            if(coolReaction.get(coolReaction.size() - 1).getUser() != reaction.getUser()){
                coolReaction.add(reaction);
            }
        }
    }

    @Override
    public void addSadReaction(Reaction reaction) {
        if(sadReaction.size() == 0){
            sadReaction.add(reaction);
        } else {
            if(sadReaction.get(sadReaction.size() - 1).getUser() != reaction.getUser()){
                sadReaction.add(reaction);
            }
        }
    }

    @Override
    public List<Reaction> getCoolReactions() {
        return coolReaction;
    }

    @Override
    public List<Reaction> getSadReactions() {
        return sadReaction;
    }

    @Override
    public void addChecked(Post post) {
        post.setChecked(true);
    }

    @Override
    public void setCountOfViews(Post post) {

    }

    @Override
    public int getCountOfViews(Post post) {
        return 0;
    }

    @Override
    public String getCategoryName(Post post) {
        return null;
    }

    @Override
    public String getTagName(Post post) {
        return null;
    }

    @Override
    public List<Post> getAllByFollowUsers(User user) {
        return null;
    }

}
