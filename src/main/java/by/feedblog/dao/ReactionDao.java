package by.feedblog.dao;

import by.feedblog.entity.Post;
import by.feedblog.entity.Reaction;
import by.feedblog.entity.User;

import java.util.List;

public interface ReactionDao {
    void add(Reaction reaction);
    void deleteById(int id);
    void reaction(Reaction reaction, Post post, User user);
    Reaction getById(int id);
    List<Reaction> getAllByPost(Post post);
    List<Reaction> getAll();
    List<Reaction> getAllForModer();
    int getCount( Post post);
}
