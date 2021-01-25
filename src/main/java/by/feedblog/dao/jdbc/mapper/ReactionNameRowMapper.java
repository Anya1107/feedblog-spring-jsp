package by.feedblog.dao.jdbc.mapper;

import by.feedblog.entity.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReactionNameRowMapper implements RowMapper<Reaction> {
    @Override
    public Reaction mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt(1);
        String reaction = resultSet.getString(2);
        return new Reaction(id, reaction);
    }
}
