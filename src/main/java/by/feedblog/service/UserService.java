package by.feedblog.service;

import by.feedblog.dao.UserDao;
import by.feedblog.entity.Bookmark;
import by.feedblog.entity.Post;
import by.feedblog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;

@Service
public class UserService {

    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean save(User user){
        if(userDao.containsByName(user.getFullName())){
            return false;
        } else {
            userDao.save(user);
        }
        return true;
    }

    public boolean deleteById(int id){
        if(userDao.containsById(id)){
            userDao.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean deleteByName(String name){
        if(userDao.containsByName(name)){
            userDao.deleteByName(name);
            return true;
        }
        return false;
    }

    public List<User> getAll(){
        return userDao.getAll();
    }

    public User getById(int id){
        if(userDao.containsById(id)){
            return userDao.getById(id);
        }
        return null;
    }

    public User getByName(String name){
        if(userDao.containsByName(name)){
            return userDao.getByName(name);
        }
        return null;
    }

    public void updateName(int id, String name){
            userDao.updateName(id, name);
    }

    public void updatePassword(int id, String password){
            userDao.updatePassword(id, password);
    }

    public void updateAge(int id, int age){
            userDao.updateAge(id, age);
    }

    public boolean registration(User user){
        if(userDao.containsByName(user.getUsername())){
            return false;
        } else {
            userDao.save(user);
        }
        return true;
    }

    public boolean authorization(String username, String password){
        return userDao.authorization(username, password);
    }

    public List<Bookmark> getAllBookmarks(User user){
        return userDao.getAllBookmarks(user);
    }

    public List<Bookmark> getBookmark(User user){
        return userDao.getBookmarks(user);
    }

    public void addPostToHistory(Post post, User user){
        userDao.addPostToHistory(post, user);
    }

    public List<Post> getAllHistory(User user){
        return userDao.getAllHistory(user);
    }

    public void addBookmark(Post post, User user){
        userDao.addBookmark(post, user);
    }

    public void follow(User user, User follow){
        userDao.follow(user, follow);
    }

    public List<User> subscriptions(User user){
        return userDao.subscriptions(user);
    }


    public int countOfFollowers(User user){
        return userDao.countOfFollowers(user);
    }

    public List<User> uncheckedFollowers(User user){ return userDao.uncheckedFollowers(user);}

    public List<User> checkedFollowers(User user){return  userDao.checkedFollowers(user);}

    public void addFollower(User user, User follow){ userDao.addFollower(user, follow);}

    public void deleteFollower(User user, User follow){ userDao.deleteFollower(user, follow);}

    public void deleteSubscription(User user, User follow){ userDao.deleteSubscription(user, follow);}
}
