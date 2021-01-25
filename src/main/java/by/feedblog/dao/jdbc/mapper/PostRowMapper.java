package by.feedblog.dao.jdbc.mapper;

import by.feedblog.entity.Category;
import by.feedblog.entity.Post;
import by.feedblog.entity.Tag;
import by.feedblog.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostRowMapper implements RowMapper<Post> {

    @Override
    public Post mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt(1);
        String title = resultSet.getString(2);
        String description = resultSet.getString(3);
        int categoryId = resultSet.getInt(4);
        int tagId = resultSet.getInt(5);
        int userId = resultSet.getInt(6);
        boolean isChecked = resultSet.getBoolean(7);
        Date date = resultSet.getDate(8);
        int count = resultSet.getInt(9);
        String categoryName = resultSet.getString(14);
        String tagName = resultSet.getString(16);
        String username = resultSet.getString(17);
        String password = resultSet.getString(18);
        String fullName = resultSet.getString(19);
        int userAge = resultSet.getInt(20);
        return new Post(id, title, description, new Category(categoryId, categoryName), new Tag(tagId, tagName),
                new User(userId, username,password, fullName, userAge), isChecked, date,count);
    }
}
