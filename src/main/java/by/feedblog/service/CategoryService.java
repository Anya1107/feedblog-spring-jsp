package by.feedblog.service;

import by.feedblog.dao.CategoryDao;
import by.feedblog.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private CategoryDao categoryDao;

    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public boolean save(Category category){
        if(categoryDao.containsByTitle(category.getTitle())){
            return false;
        } else {
            categoryDao.save(category);
        }
        return true;
    }

    public boolean deleteById(int id){
        if(categoryDao.containsById(id)){
            categoryDao.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean deleteByTitle(String title){
        if(categoryDao.containsByTitle(title)){
            categoryDao.deleteByTitle(title);
            return true;
        }
        return false;
    }

    public List<Category> getAll(){
        return categoryDao.getAll();
    }

    public Category getById(int id){
        if(categoryDao.containsById(id)){
            return categoryDao.getById(id);
        }
        return null;
    }

    public Category getByTitle(String title){
        if(categoryDao.containsByTitle(title)){
            return categoryDao.getByTitle(title);
        }
        return null;
    }
}
