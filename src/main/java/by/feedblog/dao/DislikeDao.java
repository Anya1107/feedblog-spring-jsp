package by.feedblog.dao;

import by.feedblog.entity.Dislike;
import by.feedblog.entity.Post;
import by.feedblog.entity.User;

import java.util.List;

public interface DislikeDao {
    void add(Dislike dislike);
    List<String> getUsers();
    int getCount(Post post);
}
