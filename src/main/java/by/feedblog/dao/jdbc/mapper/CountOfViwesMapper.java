package by.feedblog.dao.jdbc.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountOfViwesMapper implements RowMapper<Integer> {
    @Override
    public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getInt(9);
    }
}
