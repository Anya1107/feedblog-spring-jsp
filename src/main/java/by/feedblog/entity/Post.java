package by.feedblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post implements Comparable<Post>{
    private int id;
    private String title;
    private String description;
    private Category category;
    private Tag tag;
    private User user;
    private Comment moderComment;
    private boolean isChecked = false;
    private Date date;
    private int count;

    public Post(String title, String description, Category category, Tag tag, User user, Date date) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.tag = tag;
        this.user = user;
        this.date = date;
    }

    public Post(String title, String description, Category category, Tag tag, User user, List<Comment> comments, boolean isChecked) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.tag = tag;
        this.user = user;
        this.isChecked = isChecked;
    }

    public Post(String title, String description, Category category, Tag tag, User user, List<Comment> comments, boolean isChecked, List<Like> likes, List<Dislike> dislikes) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.tag = tag;
        this.user = user;
        this.isChecked = isChecked;
    }

    public Post(int id, String title, String description, Category category, Tag tag, User user, boolean isChecked, Date date, int count) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.tag = tag;
        this.user = user;
        this.isChecked = isChecked;
        this.date = date;
        this.count = count;
    }

    public Post(String title, String description, Category category, Tag tag, User user, boolean isChecked, Date date, int count) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.tag = tag;
        this.user = user;
        this.isChecked = isChecked;
        this.date = date;
        this.count = count;
    }

    @Override
    public int compareTo(Post o) {
        return this.getDate().compareTo(o.getDate());
    }
}
