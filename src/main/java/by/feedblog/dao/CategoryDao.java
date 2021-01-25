package by.feedblog.dao;

import by.feedblog.entity.Category;
import by.feedblog.entity.Post;

import java.util.List;

public interface CategoryDao {
    void save(Category category);
    void deleteById(int id);
    void deleteByTitle(String title);
    List<Category> getAll();
    Category getById(int id);
    Category getByTitle(String title);
    boolean containsById(int id);
    boolean containsByTitle(String title);
}
