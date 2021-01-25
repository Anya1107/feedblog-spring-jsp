package by.feedblog.dao;

import by.feedblog.entity.Bookmark;
import by.feedblog.entity.Post;
import by.feedblog.entity.User;

import java.awt.print.Book;
import java.util.List;

public interface UserDao {
    void save(User user);
    void deleteById(int id);
    void deleteByName(String name);
    List<User> getAll();
    User getById(int id);
    User getByName(String name);
    boolean containsById(int id);
    boolean containsByName(String name);
    void updateName(int id, String name);
    void updatePassword(int id, String password);
    void updateAge(int id, int age);
    boolean authorization(String username, String password);
    List<Bookmark> getAllBookmarks(User user);
    List<Bookmark> getBookmarks(User user);
    void addPostToHistory(Post post, User user);
    List<Post> getAllHistory(User user);
    void addBookmark(Post post, User user);
    void follow(User user, User follow);
    List<User> subscriptions(User user);
    int countOfFollowers(User user);
    List<User> uncheckedFollowers(User user);
    List<User> checkedFollowers(User user);
    void addFollower(User user, User follow);
    void deleteFollower(User user, User follow);
    void deleteSubscription(User user, User follow);
}
