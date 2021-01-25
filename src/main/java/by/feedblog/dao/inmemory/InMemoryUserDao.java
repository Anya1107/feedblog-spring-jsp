package by.feedblog.dao.inmemory;

import by.feedblog.dao.UserDao;
import by.feedblog.entity.Bookmark;
import by.feedblog.entity.Post;
import by.feedblog.entity.Role;
import by.feedblog.entity.User;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryUserDao implements UserDao {
    private List<User> users = new ArrayList<>();
    private List<Bookmark> bookmarks = new ArrayList<>();
    private List<Post> history = new ArrayList<>();

    {
        users.add(new User("admin", "admin", "Admin", 22, Role.MODER));
        users.add(new User("user", "user", "User", 22, Role.USER));
    }

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public void deleteById(int id) {
        for (User user : users) {
            if(user.getId() == id){
                users.remove(user);
                break;
            }
        }
    }

    @Override
    public void deleteByName(String name) {
        for (User user : users) {
            if(user.getUsername() == name){
                users.remove(user);
                break;
            }
        }
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public User getById(int id) {
        for (User user : users) {
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }

    @Override
    public User getByName(String name) {
        for (User user : users) {
            if(user.getUsername().equals(name)){
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean containsById(int id) {
        for (User user : users) {
            if(user.getId() == id){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsByName(String name) {
        for (User user : users) {
            if(user.getUsername().equals(name)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateName(int id, String name) {
        for (User user : users) {
            if(user == null) break;
            if(user.getId() == id){
                user.setFullName(name);
                break;
            }
        }
    }

    @Override
    public void updatePassword(int id, String password) {
        for (User user : users) {
            if(user == null) break;
            if(user.getId() == id){
                user.setPassword(password);
                break;
            }
        }
    }

    @Override
    public void updateAge(int id, int age) {
        for (User user : users) {
            if(user == null) break;
            if(user.getId() == id){
                user.setAge(age);
                break;
            }
        }
    }

    @Override
    public boolean authorization(String username, String password){
        for (User user : users) {
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Bookmark> getAllBookmarks(User user) {
        return null;
    }

    @Override
    public List<Bookmark> getBookmarks(User user) {
        return null;
    }

    @Override
    public void addPostToHistory(Post post, User user) {
        history.add(post);
    }

    @Override
    public List<Post> getAllHistory(User user) {
        return history;
    }

    @Override
    public void addBookmark(Post post, User user) {
        bookmarks.add(new Bookmark(user,post));
    }

    @Override
    public void follow(User user, User follow) {

    }

    @Override
    public List<User> subscriptions(User user) {
        return null;
    }


    @Override
    public int countOfFollowers(User user) {
        return 0;
    }

    @Override
    public List<User> uncheckedFollowers(User user) {
        return null;
    }

    @Override
    public List<User> checkedFollowers(User user) {
        return null;
    }

    @Override
    public void addFollower(User user, User follow) {

    }

    @Override
    public void deleteFollower(User user, User follow) {

    }

    @Override
    public void deleteSubscription(User user, User follow) {

    }


}
