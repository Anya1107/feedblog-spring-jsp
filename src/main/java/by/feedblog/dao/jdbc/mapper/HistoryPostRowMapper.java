package by.feedblog.dao.jdbc.mapper;

import by.feedblog.entity.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoryPostRowMapper implements RowMapper<Post> {

    @Override
    public Post mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt(4);
        String title = resultSet.getString(5);
        String description = resultSet.getString(6);
        boolean isChecked = resultSet.getBoolean(10);
        Date date = resultSet.getDate(11);
        int count = resultSet.getInt(12);
        int categoryId = resultSet.getInt(15);
        String categoryTitle = resultSet.getString(16);
        int tagId = resultSet.getInt(17);
        String tagName = resultSet.getString(18);
        int userId = resultSet.getInt(19);
        String username = resultSet.getString(20);
        String password = resultSet.getString(21);
        String fullName = resultSet.getString(22);
        int age = resultSet.getInt(23);
        String role = resultSet.getString(28);
        return new Post(id, title, description, new Category(categoryId, categoryTitle),
                new Tag(tagId, tagName), new User(userId, username, password, fullName, age, Role.valueOf(role)),
                isChecked, date,count);
    }
}
