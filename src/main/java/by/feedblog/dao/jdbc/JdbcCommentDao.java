package by.feedblog.dao.jdbc;

import by.feedblog.dao.CommentDao;
import by.feedblog.dao.jdbc.mapper.CommentRowMapper;
import by.feedblog.dao.jdbc.mapper.PostRowMapper;
import by.feedblog.entity.Comment;
import by.feedblog.entity.Post;
import by.feedblog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcCommentDao implements CommentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Comment comment) {
        jdbcTemplate.update("insert into comments values(default, ?, ?, ?)", comment.getComment(),
                comment.getUser().getId(), comment.getPost().getId());
    }

    @Override
    public void deleteById(int id) {
        jdbcTemplate.update("delete from comments where id = ?", id);
    }

    @Override
    public Comment getById(int id) {
        return jdbcTemplate.queryForObject("select * from comments c join users u on c.user_id = u.id join roles r on u.role_id = r.id\n" +
                        "    join posts p on c.post_id = p.id join categories ca on p.category_id = ca.id join tags t on p.tag_id = t.id" +
                        " where c.id = ?", new Object[]{id},
                new CommentRowMapper());
    }

    @Override
    public List<Comment> getAllByPost(Post post) {
        return jdbcTemplate.query("select * from comments c join users u on c.user_id = u.id join roles r on u.role_id = r.id\n" +
                        "    join posts p on c.post_id = p.id join categories ca on p.category_id = ca.id join tags t on p.tag_id = t.id" +
                        " where p.id = ?", new Object[]{post.getId()}, new CommentRowMapper());
    }

    @Override
    public List<Comment> getAllByUser(User user) {
        return jdbcTemplate.query("select * from comments c join users u on c.user_id = u.id join roles r on u.role_id = r.id\n" +
                "    join posts p on c.post_id = p.id join categories ca on p.category_id = ca.id join tags t on p.tag_id = t.id" +
                " where u.id = ?", new Object[]{user.getId()}, new CommentRowMapper());
    }

    @Override
    public List<Comment> getAll() {
        return jdbcTemplate.query("select * from comments c join users u on c.user_id = u.id join roles r on u.role_id = r.id\n" +
                "    join posts p on c.post_id = p.id join categories ca on p.category_id = ca.id join tags t on p.tag_id = t.id" +
                "", new CommentRowMapper());
    }

    @Override
    public boolean containsById(int id) {
        try {
            jdbcTemplate.queryForObject("select * from comments c join users u on c.user_id = u.id join roles r on u.role_id = r.id\n" +
                    "    join posts p on c.post_id = p.id join categories ca on p.category_id = ca.id join tags t on p.tag_id = t.id" +
                    " where c.id = ?", new Object[]{id}, new CommentRowMapper());
        } catch (EmptyResultDataAccessException e){
            return false;
        }
        return true;
    }
}
