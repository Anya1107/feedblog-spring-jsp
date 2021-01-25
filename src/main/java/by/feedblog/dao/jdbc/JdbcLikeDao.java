package by.feedblog.dao.jdbc;

import by.feedblog.dao.LikeDao;
import by.feedblog.dao.jdbc.mapper.UserNameRowMapper;
import by.feedblog.entity.Like;
import by.feedblog.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcLikeDao implements LikeDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void add(Like like){
        if (jdbcTemplate.queryForObject("select like_id from users where id = ?", new Object[]{like.getUser().getId()},
                Integer.class) == null) {
            jdbcTemplate.update("insert into likes values (default , ?, ?)", like.getUser().getId(),
                    like.getPost().getId());
            jdbcTemplate.update("update users set like_id = likes.id from likes where users.id = ? " +
                    "and likes.user_id = ?", like.getUser().getId(), like.getUser().getId());
            jdbcTemplate.update("update posts set like_id = likes.id from likes where posts.id = ? " +
                    "and likes.user_id = ?", like.getPost().getId(), like.getPost().getId());
        }
    }

    public List<String> getUsers(){
        return jdbcTemplate.query("select * from users u left join likes l on u.like_id = l.id",
                new UserNameRowMapper());
    }

    public int getCount(Post post){
        return jdbcTemplate.queryForObject("select count(l.id) from likes l join posts p on l.post_id = p.id " +
                "where p.id = ?", new Object[]{post.getId()}, Integer.class);
    }
}
