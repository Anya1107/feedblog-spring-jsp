package by.feedblog.dao.jdbc.mapper;

import by.feedblog.entity.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class CategoryRowMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt(1);
        String title = resultSet.getString(2);
        return new Category(id, title);
    }
}
