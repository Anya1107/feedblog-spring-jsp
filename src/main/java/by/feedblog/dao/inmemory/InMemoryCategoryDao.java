package by.feedblog.dao.inmemory;

import by.feedblog.dao.CategoryDao;
import by.feedblog.entity.Category;
import by.feedblog.entity.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryCategoryDao implements CategoryDao {
    private List<Category> categories = new ArrayList<>();


    @Override
    public void save(Category category) {
        categories.add(category);
    }

    @Override
    public void deleteById(int id) {
        for (Category category : categories) {
            if(category.getId()==id){
                categories.remove(category);
                break;
            }
        }
    }

    @Override
    public void deleteByTitle(String title) {
        for (Category category : categories) {
            if(category.getTitle().equals(title)){
                categories.remove(category);
                break;
            }
        }
    }

    @Override
    public List<Category> getAll() {
        return categories;
    }

    @Override
    public Category getById(int id) {
        for (Category category : categories) {
            if(category.getId()==id){
                return category;
            }
        }
        return null;
    }

    @Override
    public Category getByTitle(String title) {
        for (Category category : categories) {
            if(category.getTitle().equals(title)){
                return category;
            }
        }
        return null;
    }

    @Override
    public boolean containsById(int id) {
        for (Category category : categories) {
            if(category.getId()==id){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsByTitle(String title) {
        for (Category category : categories) {
            if(category.getTitle().equals(title)){
                return true;
            }
        }
        return false;
    }
}
