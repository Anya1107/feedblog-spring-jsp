package by.feedblog.dao.jdbc;

import by.feedblog.dao.UserDao;
import by.feedblog.dao.jdbc.mapper.BookmarkRowMapper;
import by.feedblog.dao.jdbc.mapper.SubscriptionUserMapper;
import by.feedblog.dao.jdbc.mapper.HistoryPostRowMapper;
import by.feedblog.dao.jdbc.mapper.UserRowMapper;
import by.feedblog.entity.Bookmark;
import by.feedblog.entity.Post;
import by.feedblog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcUserDao implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(User user) {
        jdbcTemplate.update("insert into users values(default, ?, ?, ?, ?, ?)",
                user.getUsername(),
                user.getPassword(),
                user.getFullName(),
                user.getAge(),
                2);
    }

    @Override
    public void deleteById(int id) {
        jdbcTemplate.update("delete from users u join roles r on u.role_id = r.id where id = ?", id);
    }

    @Override
    public void deleteByName(String name) {
        jdbcTemplate.update("delete from users u join roles r on u.role_id = r.id where username = ?", name);
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query("select * from users u join roles r on u.role_id = r.id left join likes l on u.like_id = l.id " +
                " left join dislikes d on u.dislike_id = d.id", new UserRowMapper());
    }

    @Override
    public User getById(int id) {
        return jdbcTemplate.queryForObject("select * from users u join roles r on u.role_id = r.id left join likes l on u.like_id = l.id " +
                " left join dislikes d on u.dislike_id = d.id where u.id = ?", new Object[]{id}, new UserRowMapper());
    }

    @Override
    public User getByName(String name) {
        return jdbcTemplate.queryForObject("select * from users u join roles r on u.role_id = r.id left join likes l on u.like_id = l.id " +
                " left join dislikes d on u.dislike_id = d.id where username = ?", new Object[]{name}, new UserRowMapper());
    }

    @Override
    public boolean containsById(int id) {
        try{
            jdbcTemplate.queryForObject("select * from users u join roles r on u.role_id = r.id left join likes l on u.like_id = l.id " +
                    " left join dislikes d on u.dislike_id = d.id where u.id = ?", new Object[]{id}, new UserRowMapper());
        } catch (EmptyResultDataAccessException e){
            return false;
        }
        return true;
    }

    @Override
    public boolean containsByName(String name) {
        try{
            jdbcTemplate.queryForObject("select * from users u join roles r on u.role_id = r.id  left join likes l on u.like_id = l.id " +
                    " left join dislikes d on u.dislike_id = d.id where username = ?", new Object[]{name}, new UserRowMapper());
        } catch (EmptyResultDataAccessException e){
            return false;
        }
        return true;
    }

    @Override
    public void updateName(int id, String name) {
        jdbcTemplate.update("update users set fullName = ? where id = ?", name, id);
    }

    @Override
    public void updatePassword(int id, String password) {
        jdbcTemplate.update("update users set password = ? where id =?", password,id);
    }

    @Override
    public void updateAge(int id, int age) {
        jdbcTemplate.update("update users set age = ? where id = ?", age, id);
    }

    @Override
    public boolean authorization(String username, String password) {
        User user = jdbcTemplate.queryForObject("select * from users u join roles r on u.role_id = r.id where username = ? and password = ?",
                new Object[]{username, password},
                new UserRowMapper());
        return user != null;
    }

    @Override
    public List<Bookmark> getAllBookmarks(User user) {
        return jdbcTemplate.query("select * from bookmarks b join users u on b.user_id = u.id  join roles r on u.role_id = r.id\n" +
                "join posts p on b.post_id = p.id join categories c on p.category_id = c.id join tags t on p.tag_id = t.id " +
                        "where b.user_id = ?", new Object[]{user.getId()}, new BookmarkRowMapper());
    }

    @Override
    public List<Bookmark> getBookmarks(User user) {
        return jdbcTemplate.query("select * from bookmarks b join users u on b.user_id = u.id  join roles r on u.role_id = r.id\n" +
                "join posts p on b.post_id = p.id join categories c on p.category_id = c.id join tags t on p.tag_id = t.id " +
                "where b.user_id = ?", new Object[]{user.getId()}, new BookmarkRowMapper());
    }

    @Override
    public void addPostToHistory(Post post, User user) {
        jdbcTemplate.update("insert into history values (default , ?, ?)", post.getId(), user.getId());
    }

    @Override
    public List<Post> getAllHistory(User user) {
        return jdbcTemplate.query("select * from history h join posts p on h.post_id = p.id " +
                        "join categories c on p.category_id = c.id\n" +
                        "join tags t on p.tag_id = t.id join users u on p.user_id = u.id " +
                        "join roles r on u.role_id = r.id where h.user_id = ?",
                new Object[]{user.getId()}, new HistoryPostRowMapper());
    }

    @Override
    public void addBookmark(Post post, User user) {
        jdbcTemplate.update("insert into bookmarks values(default , ?, ?)",
                user.getId(), post.getId());
    }

    @Override
    public void follow(User user, User follow) {
        jdbcTemplate.update("insert into followers values(default ,?, ?, default)",
                user.getId(), follow.getId());
    }

    @Override
    public List<User> subscriptions(User user) {
        return jdbcTemplate.query("select * from followers join users u on followers.follow_id = u.id" +
                        " join roles r on u.role_id = r.id where followers.user_id = ?",
                new Object[]{user.getId()}, new SubscriptionUserMapper());
    }

    @Override
    public int countOfFollowers(User user) {
        return jdbcTemplate.queryForObject("select count(user_id) from followers join users u " +
                        "on followers.user_id = u.id where follow_id = ? and ischecked = true",
                new Object[]{user.getId()}, Integer.class);
    }

    @Override
    public List<User> uncheckedFollowers(User user) {
        return jdbcTemplate.query("select * from followers f join users u on f.user_id = u.id" +
                        " join roles r on u.role_id = r.id where follow_id = ? and f.ischecked = false",
                new Object[]{user.getId()}, new SubscriptionUserMapper());
    }

    @Override
    public List<User> checkedFollowers(User user) {
        return jdbcTemplate.query("select * from followers f join users u on f.user_id = u.id" +
                        " join roles r on u.role_id = r.id where follow_id = ? and f.ischecked = true",
                new Object[]{user.getId()}, new SubscriptionUserMapper());
    }

    @Override
    public void addFollower(User user, User follow) {
        jdbcTemplate.update("update followers set ischecked = true where follow_id = ? " +
                        "and user_id = ?", user.getId(), follow.getId());
    }

    @Override
    public void deleteFollower(User user, User follow) {
        jdbcTemplate.update("delete from followers where follow_id = ? and user_id =?",
                user.getId(), follow.getId());
    }

    @Override
    public void deleteSubscription(User user, User follow) {
        jdbcTemplate.update("delete from followers where user_id = ? and follow_id = ?",
                user.getId(), follow.getId());
    }
}
