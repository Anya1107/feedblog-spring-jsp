package by.feedblog.dao.jdbc;

import by.feedblog.dao.CategoryDao;
import by.feedblog.dao.jdbc.mapper.CategoryRowMapper;
import by.feedblog.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcCategoryDao implements CategoryDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Category category) {
        jdbcTemplate.update("insert into categories values(default , ?)",
                category.getTitle());
    }

    @Override
    public void deleteById(int id) {
        jdbcTemplate.update("delete from categories where id = ?", id);
    }

    @Override
    public void deleteByTitle(String title) {
        jdbcTemplate.update("delete from categories where title = ?", title);
    }

    @Override
    public List<Category> getAll() {
        return jdbcTemplate.query("select * from categories", new CategoryRowMapper());
    }

    @Override
    public Category getById(int id) {
        return jdbcTemplate.queryForObject("select * from categories where id = ?", new Object[]{id}, new CategoryRowMapper());
    }

    @Override
    public Category getByTitle(String title) {
        return jdbcTemplate.queryForObject("select * from categories where title = ?", new Object[]{title}, new CategoryRowMapper());
    }

    @Override
    public boolean containsById(int id) {
        try {
            jdbcTemplate.queryForObject("select * from categories where id = ?", new Object[]{id}, new CategoryRowMapper());
        } catch (EmptyResultDataAccessException e){
            return false;
        }
        return true;
    }

    @Override
    public boolean containsByTitle(String title) {
        try{
            jdbcTemplate.queryForObject("select * from categories where title = ?", new Object[]{title}, new CategoryRowMapper());
        } catch (EmptyResultDataAccessException e){
            return false;
        }
        return true;
    }
}
