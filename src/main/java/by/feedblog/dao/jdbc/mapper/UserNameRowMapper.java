package by.feedblog.dao.jdbc.mapper;

import by.feedblog.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserNameRowMapper implements RowMapper<String> {

    @Override
    public String mapRow(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getString(2);
    }
}
