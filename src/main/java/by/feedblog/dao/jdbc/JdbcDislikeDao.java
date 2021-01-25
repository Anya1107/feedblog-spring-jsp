package by.feedblog.dao.jdbc;

import by.feedblog.dao.DislikeDao;
import by.feedblog.dao.jdbc.mapper.UserNameRowMapper;
import by.feedblog.dao.jdbc.mapper.UserRowMapper;
import by.feedblog.entity.Dislike;
import by.feedblog.entity.Post;
import by.feedblog.entity.User;
import by.feedblog.service.DislikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcDislikeDao implements DislikeDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void add(Dislike dislike){
        jdbcTemplate.update("insert into dislikes values (default , ?, ?)", dislike.getUser().getId(),
                dislike.getPost().getId());
        jdbcTemplate.update("update users set dislike_id = dislikes.id from dislikes where users.id = ? and dislikes.user_id = ?",  dislike.getUser().getId(), dislike.getUser().getId());
        jdbcTemplate.update("update posts set dislike_id = dislikes.id from dislikes where posts.id = ? and dislikes.post_id = ?",  dislike.getPost().getId(), dislike.getPost().getId());
    }

    public List<String> getUsers(){
        return jdbcTemplate.query("select * from users u left join dislikes d on u.dislike_id = d.id",
                new UserNameRowMapper());
    }

    public int getCount(Post post){
        return jdbcTemplate.queryForObject("select count(d.id) from dislikes d join posts p on d.post_id = p.id" +
                " where p.id = ?", new Object[]{post.getId()}, Integer.class);
    }
}
