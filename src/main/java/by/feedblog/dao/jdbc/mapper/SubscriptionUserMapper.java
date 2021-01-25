package by.feedblog.dao.jdbc.mapper;

import by.feedblog.entity.Role;
import by.feedblog.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubscriptionUserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt(5);
        String username = resultSet.getString(6);
        String password = resultSet.getString(7);
        String fullName = resultSet.getString(8);
        int age = resultSet.getInt(9);
        String role = resultSet.getString(14);
        return new User(id, username, password, fullName, age, Role.valueOf(role));
    }
}
