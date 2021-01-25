package by.feedblog.dao.jdbc.mapper;

import by.feedblog.entity.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRowMapper implements RowMapper<Role> {
    @Override
    public Role mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt(1);
        String role = resultSet.getString(2);
        return Role.valueOf(role);
    }
}
