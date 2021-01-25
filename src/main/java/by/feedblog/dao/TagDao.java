package by.feedblog.dao;

import by.feedblog.entity.Tag;

import java.util.List;

public interface TagDao {
    void save(Tag tag);
    void delete(int id);
    void delete(String name);
    List<Tag> getAll();
    Tag getById(int id);
    Tag getByName(String name);
    boolean containsById(int id);
    boolean containsByName(String name);
}
