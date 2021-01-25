package by.feedblog.dao.jdbc.mapper;

import by.feedblog.entity.Role;
import by.feedblog.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt(1);
        String username = resultSet.getString(2);
        String password = resultSet.getString(3);
        String fullName = resultSet.getString(4);
        int age = resultSet.getInt(5);
        String role = resultSet.getString(10);
        return new User(id, username, password, fullName,age, Role.valueOf(role));
    }
}
