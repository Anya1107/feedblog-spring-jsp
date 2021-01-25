package by.feedblog.dao.jdbc.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryNameRowMapper implements RowMapper<String> {
    @Override
    public String mapRow(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getString(13);
    }
}
