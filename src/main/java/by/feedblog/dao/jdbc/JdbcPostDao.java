package by.feedblog.dao.jdbc;

import by.feedblog.dao.PostDao;
import by.feedblog.dao.jdbc.mapper.CategoryNameRowMapper;
import by.feedblog.dao.jdbc.mapper.CountOfViwesMapper;
import by.feedblog.dao.jdbc.mapper.PostRowMapper;
import by.feedblog.dao.jdbc.mapper.TagNameRowMapper;
import by.feedblog.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcPostDao implements PostDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Post post) {
        jdbcTemplate.update("insert into posts values(default, ?, ?, ?, ?, ?, ?, ?, ?)",
                post.getTitle(),
                post.getDescription(),
                post.getCategory().getId(),
                post.getTag().getId(),
                post.getUser().getId(),
                post.isChecked(),
                post.getDate(),
                post.getCount());
    }

    @Override
    public void deleteById(int id) {
        jdbcTemplate.update("delete from posts where id =?", id);
    }

    @Override
    public void deleteByTitle(String title) {
        jdbcTemplate.update("delete from posts where title = ?", title);
    }

    @Override
    public List<Post> getAll() {
        return jdbcTemplate.query("select * from posts p join categories c on p.category_id = c.id " +
                " join tags t on p.tag_id = t.id join users u on p.user_id = u.id left join likes l on p.like_id = l.id" +
                " left join dislikes d on p.dislike_id = d.id", new PostRowMapper());
    }

    @Override
    public Post getById(int id) {
        return jdbcTemplate.queryForObject("select * from posts p join categories c on p.category_id = c.id " +
                "join tags t on p.tag_id = t.id join users u on p.user_id = u.id left join likes l on p.like_id = l.id" +
                        " left join dislikes d on p.dislike_id = d.id where p.id = ?",
                new Object[] {id}, new PostRowMapper());
    }

    @Override
    public Post getByTitle(String title) {
        return jdbcTemplate.queryForObject("select * from posts p join categories c on p.category_id = c.id " +
                        "join tags t on p.tag_id = t.id join users u on p.user_id = u.id left join likes l on p.like_id = l.id" +
                        " left join dislikes d on p.dislike_id = d.id where p.title = ?",
                         new Object[] {title}, new PostRowMapper());
    }

    @Override
    public List<Post> getAllByUser(User user) {
        return jdbcTemplate.query("select * from posts p join categories c on p.category_id = c.id" +
                " join tags t on p.tag_id = t.id join users u on p.user_id = u.id left join likes l on p.like_id = l.id" +
                " left join dislikes d on p.dislike_id = d.id where u.id = ?", new Object[]{user.getId()}, new PostRowMapper());
    }

    @Override
    public List<Post> getAllByTag(Tag tag) {
        return jdbcTemplate.query("select * from posts p join categories c on p.category_id = c.id" +
                " join tags t on p.tag_id = t.id join users u on p.user_id = u.id left join likes l on p.like_id = l.id " +
                " left join dislikes d on p.dislike_id = d.id where t.id = ?", new Object[]{tag.getId()}, new PostRowMapper());
    }

    @Override
    public List<Post> getAllByCategory(Category category) {
        return jdbcTemplate.query("select * from posts p join categories c on p.category_id = c.id" +
                " join tags t on p.tag_id = t.id join users u on p.user_id = u.id left join likes l on p.like_id = l.id" +
                " left join dislikes d on p.dislike_id = d.id where c.id = ?", new Object[]{category.getId()}, new PostRowMapper());
    }

    @Override
    public List<Post> getAllChecked() {
        return jdbcTemplate.query("select * from posts p join categories c on p.category_id = c.id" +
                " join tags t on p.tag_id = t.id join users u on p.user_id = u.id " +
                " left join likes l on p.like_id = l.id left join dislikes d on p.dislike_id = d.id where p.isChecked = true", new PostRowMapper());
    }

    @Override
    public List<Post> getAllUnchecked() {
        return jdbcTemplate.query("select * from posts p join categories c on p.category_id = c.id" +
                " join tags t on p.tag_id = t.id join users u on p.user_id = u.id " +
                " left join likes l on p.like_id = l.id left join dislikes d on p.dislike_id = d.id where p.isChecked = false", new PostRowMapper());
    }

    @Override
    public boolean containsById(int id) {
        try {
            jdbcTemplate.queryForObject("select * from posts p join categories c on p.category_id = c.id" +
                            " join tags t on p.tag_id = t.id join users u on p.user_id = u.id left join likes l on p.like_id = l.id " +
                            " left join dislikes d on p.dislike_id = d.id where p.id = ?",
                    new Object[] {id}, new PostRowMapper());
        } catch (EmptyResultDataAccessException e){
            return false;
        }
        return true;
    }

    @Override
    public boolean containsByTitle(String title) {
        try{
            jdbcTemplate.queryForObject("select * from posts p join categories c on p.category_id = c.id " +
                            "join tags t on p.tag_id = t.id join users u on p.user_id = u.id left join likes l on p.like_id = l.id " +
                            " left join dislikes d on p.dislike_id = d.id  where p.title = ?",
                    new Object[] {title}, new PostRowMapper());
        } catch (EmptyResultDataAccessException e){
            return false;
        }
        return true;
    }

    @Override
    public void addLike(Like like) {

    }

    @Override
    public void addDislike(Dislike dislike, Like like) {

    }

    @Override
    public void addDislikeWithoutRemove(Dislike dislike) {

    }

    @Override
    public List<Like> getLikes() {
        return null;
    }

    @Override
    public List<Dislike> getDislikes() {
        return null;
    }

    @Override
    public void updateDescription(int id, String description) {
        jdbcTemplate.update("update posts set description = ? where id = ?", description, id);
        jdbcTemplate.update("update posts set isChecked = false where id = ?" ,id);
    }

    @Override
    public void updateTag(int id, Tag tag) {
        jdbcTemplate.update("update posts set tag_id = ? where id = ?", tag.getId(), id);
        jdbcTemplate.update("update posts set isChecked = false where id = ?" ,id);
    }

    @Override
    public void updateCategory(int id, Category category) {
        jdbcTemplate.update("update posts set category_id = ? where id = ?", category.getId(), id);
        jdbcTemplate.update("update posts set isChecked = false where id = ?" ,id);
    }

    @Override
    public void addCoolReaction(Reaction reaction) {

    }

    @Override
    public void addSadReaction(Reaction reaction) {

    }

    @Override
    public List<Reaction> getCoolReactions() {
        return null;
    }

    @Override
    public List<Reaction> getSadReactions() {
        return null;
    }

    @Override
    public void addChecked(Post post) {
        jdbcTemplate.update("update posts p set isChecked = true where p.id = ?", post.getId());
    }

    @Override
    public void setCountOfViews(Post post) {
        jdbcTemplate.update("update posts set count = count+1 where id = ?", post.getId());
    }

    @Override
    public int getCountOfViews(Post post) {
        return jdbcTemplate.queryForObject("select * from posts where id = ?", new Object[]{post.getId()},
                new CountOfViwesMapper());
    }

    @Override
    public String getCategoryName(Post post) {
        return jdbcTemplate.queryForObject("select * from posts p join categories c on p.category_id = c.id " +
                "where p.id = ?", new Object[]{post.getId()}, new CategoryNameRowMapper());
    }

    @Override
    public String getTagName(Post post) {
        return jdbcTemplate.queryForObject("select * from posts p join tags t on p.tag_id = t.id " +
                "where p.id = ?", new Object[]{post.getId()}, new TagNameRowMapper());
    }

    @Override
    public List<Post> getAllByFollowUsers(User user) {
        return jdbcTemplate.query("select * from posts p join categories c on p.category_id = c.id" +
                " join tags t on p.tag_id = t.id join users u on p.user_id = u.id left join likes l on p.like_id = l.id" +
                " left join dislikes d on p.dislike_id = d.id join followers f on u.id = f.follow_id " +
                "where f.user_id = ?", new Object[]{user.getId()}, new PostRowMapper());
    }

}
