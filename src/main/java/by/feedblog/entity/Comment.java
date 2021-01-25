package by.feedblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private int id;
    private String comment;
    private User user;
    private Post post;

    public Comment(String comment, User user, Post post) {
        this.comment = comment;
        this.user = user;
        this.post = post;
    }
}
