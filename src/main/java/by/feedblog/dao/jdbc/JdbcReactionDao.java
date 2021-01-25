package by.feedblog.dao.jdbc;

import by.feedblog.dao.ReactionDao;
import by.feedblog.dao.jdbc.mapper.ReactionNameRowMapper;
import by.feedblog.dao.jdbc.mapper.ReactionRowMapper;
import by.feedblog.entity.Post;
import by.feedblog.entity.Reaction;
import by.feedblog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcReactionDao implements ReactionDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void add(Reaction reaction) {
        jdbcTemplate.update("insert into reactions values (default , ?)", reaction.getReaction());
    }

    @Override
    public void deleteById(int id) {
        jdbcTemplate.update("delete from reactions where id = ?", id);
    }

    @Override
    public void reaction(Reaction reaction, Post post, User user) {
        jdbcTemplate.update("insert into post_reaction values (?, ?, ?)", reaction.getId(), post.getId(), user.getId());
    }

    @Override
    public Reaction getById(int id) {
        return jdbcTemplate.queryForObject("select * from  reactions where id = ?",
                new Object[]{id}, new ReactionNameRowMapper());
    }

    @Override
    public List<Reaction> getAllByPost(Post post) {
        return jdbcTemplate.query("select * from reactions r join post_reaction re on r.id = re.reaction_id" +
                " join posts p on re.post_id = p.id join categories c on p.category_id = c.id\n" +
                "join tags t on p.tag_id = t.id join users u on re.user_id = u.id join roles r2 on u.role_id = r2.id" +
                " where re.post_id = ?", new Object[]{post.getId()}, new ReactionRowMapper());
    }

    @Override
    public List<Reaction> getAll() {
        return jdbcTemplate.query("select * from reactions", new ReactionNameRowMapper());
    }

    @Override
    public List<Reaction> getAllForModer() {
        return jdbcTemplate.query("select * from reactions r join post_reaction re on r.id = re.reaction_id" +
                " join posts p on re.post_id = p.id join categories c on p.category_id = c.id\n" +
                "join tags t on p.tag_id = t.id join users u on re.user_id = u.id join roles r2 on u.role_id = r2.id", new ReactionRowMapper());
    }

    @Override
    public int getCount(Post post) {
        return jdbcTemplate.queryForObject("select count(post_reaction.reaction_id) from post_reaction where  " +
                "post_reaction.post_id = ?", new Object[]{ post.getId()}, Integer.class);
    }
}
