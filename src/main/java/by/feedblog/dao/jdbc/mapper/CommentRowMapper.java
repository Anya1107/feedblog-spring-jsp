package by.feedblog.dao.jdbc.mapper;

import by.feedblog.entity.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentRowMapper implements RowMapper<Comment> {
    @Override
    public Comment mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt(1);
        String comment = resultSet.getString(2);
        int userId = resultSet.getInt(5);
        String username = resultSet.getString(6);
        String password = resultSet.getString(7);
        String fullname = resultSet.getString(8);
        int age = resultSet.getInt(9);
        String role = resultSet.getString(14);
        int postId = resultSet.getInt(15);
        String title = resultSet.getString(16);
        String description = resultSet.getString(17);
        boolean isChecked = resultSet.getBoolean(21);
        Date date = resultSet.getDate(22);
        int count = resultSet.getInt(23);
        int catgeoryId = resultSet.getInt(26);
        String categoryTitle = resultSet.getString(27);
        int tagId = resultSet.getInt(28);
        String name = resultSet.getString(29);
        User user = new User(id, username, password, fullname, age, Role.valueOf(role));
        return new Comment(id, comment, user, new Post(postId, title, description, new Category(catgeoryId,categoryTitle),
                         new Tag(tagId, name), user, isChecked, date, count));
    }
}
