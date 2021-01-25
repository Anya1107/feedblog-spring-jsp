package by.feedblog.dao.jdbc;

import by.feedblog.dao.TagDao;
import by.feedblog.dao.jdbc.mapper.TagRowMapper;
import by.feedblog.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcTagDao implements TagDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Tag tag) {
        jdbcTemplate.update("insert into tags values(default , ?)",
                tag.getName());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("delete from tags where id = ?", id);
    }

    @Override
    public void delete(String name) {
        jdbcTemplate.update("delete from tags where name = ?", name);
    }

    @Override
    public List<Tag> getAll() {
        return jdbcTemplate.query("select * from tags", new TagRowMapper());
    }

    @Override
    public Tag getById(int id) {
        return jdbcTemplate.queryForObject("select * from tags where id = ?", new Object[]{id}, new TagRowMapper());
    }

    @Override
    public Tag getByName(String name) {
        return jdbcTemplate.queryForObject("select * from tags where name = ?", new Object[]{name}, new TagRowMapper());
    }

    @Override
    public boolean containsById(int id) {
        try{
            jdbcTemplate.queryForObject("select * from tags where id = ?", new Object[]{id}, new TagRowMapper());
        }catch (EmptyResultDataAccessException e){
            return false;
        }
        return true;
    }

    @Override
    public boolean containsByName(String name) {
        try{
            jdbcTemplate.queryForObject("select * from tags where name = ?", new Object[]{name}, new TagRowMapper());
        } catch (EmptyResultDataAccessException e){
            return false;
        }
        return true;
    }
}
