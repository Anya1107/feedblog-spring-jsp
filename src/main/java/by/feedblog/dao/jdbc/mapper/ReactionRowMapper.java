package by.feedblog.dao.jdbc.mapper;

import by.feedblog.entity.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ReactionRowMapper implements RowMapper<Reaction> {
    @Override
    public Reaction mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt(1);
        String reaction = resultSet.getString(2);
        int postId = resultSet.getInt(6);
        String title = resultSet.getString(7);
        String description = resultSet.getString(8);
        boolean isChecked = resultSet.getBoolean(12);
        Date date = resultSet.getDate(13);
        int count = resultSet.getInt(14);
        int categoryId = resultSet.getInt(15);
        String categoryTitle = resultSet.getString(16);
        int tagId = resultSet.getInt(19);
        String tagName = resultSet.getString(20);
        int userId = resultSet.getInt(21);
        String username = resultSet.getString(22);
        String password = resultSet.getString(23);
        String fullName = resultSet.getString(24);
        int age = resultSet.getInt(25);
        String role = resultSet.getString(30);
        User user = new User(userId, username, password, fullName, age, Role.valueOf(role));
        return new Reaction(id, reaction, user, new Post(postId, title, description, new Category(categoryId, categoryTitle),
                        new Tag(tagId, tagName), user, isChecked, date, count));
    }
}
