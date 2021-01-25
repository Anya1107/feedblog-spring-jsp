package by.feedblog.dao.jdbc.mapper;

import by.feedblog.entity.Tag;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TagRowMapper implements RowMapper<Tag> {
    @Override
    public Tag mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt(1);
        String name = resultSet.getString(2);
        return new Tag(id, name);
    }
}
