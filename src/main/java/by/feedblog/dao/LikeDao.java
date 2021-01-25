package by.feedblog.dao;

import by.feedblog.entity.Like;
import by.feedblog.entity.Post;
import by.feedblog.entity.User;

import java.util.List;

public interface LikeDao {
    void add(Like like);
    List<String> getUsers();
    int getCount(Post post);
}
