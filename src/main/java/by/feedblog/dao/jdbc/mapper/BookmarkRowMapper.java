package by.feedblog.dao.jdbc.mapper;

import by.feedblog.entity.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookmarkRowMapper implements RowMapper<Bookmark> {
    @Override
    public Bookmark mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt(1);
        int userId = resultSet.getInt(4);
        String username = resultSet.getString(5);
        String password = resultSet.getString(6);
        String fullName = resultSet.getString(7);
        int age = resultSet.getInt(8);
        String role = resultSet.getString(13);
        int postId = resultSet.getInt(14);
        String title = resultSet.getString(15);
        String description = resultSet.getString(16);
        boolean isChecked = resultSet.getBoolean(20);
        Date date = resultSet.getDate(21);
        int count = resultSet.getInt(22);
        int categoryId = resultSet.getInt(25);
        String categoryTitle = resultSet.getString(26);
        int tagId = resultSet.getInt(27);
        String name = resultSet.getString(28);
        User user = new User(userId, username, password, fullName, age, Role.valueOf(role));
        return new Bookmark(id, user, new Post(postId, title, description, new Category(categoryId, categoryTitle), new Tag(tagId, name),
                        user, isChecked, date, count));
    }
}
