package by.feedblog.dao;

import by.feedblog.entity.*;

import java.util.List;
public interface PostDao {
    void save(Post post);
    void deleteById(int id);
    void deleteByTitle(String title);
    List<Post> getAll();
    Post getById(int id);
    Post getByTitle(String title);
    List<Post> getAllByUser(User user);
    List<Post> getAllByTag(Tag tag);
    List<Post> getAllByCategory(Category category);
    List<Post> getAllChecked();
    List<Post> getAllUnchecked();
    boolean containsById(int id);
    boolean containsByTitle(String title);
    void addLike(Like like);
    void addDislike(Dislike dislike,Like like);
    void addDislikeWithoutRemove( Dislike dislike);
    List<Like> getLikes();
    List<Dislike> getDislikes();
    void updateDescription(int id, String description);
    void updateTag(int id, Tag tag);
    void updateCategory(int id, Category category);
    void addCoolReaction(Reaction reaction);
    void addSadReaction(Reaction reaction);
    List<Reaction> getCoolReactions();
    List<Reaction> getSadReactions();
    void addChecked(Post post);
    void setCountOfViews(Post post);
    int getCountOfViews(Post post);
    String getCategoryName(Post post);
    String getTagName(Post post);
    List<Post> getAllByFollowUsers(User user);
}
