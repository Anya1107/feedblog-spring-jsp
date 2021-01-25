package by.feedblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reaction {
    private int id;
    private String reaction;
    private User user;
    private Post post;

    public Reaction(int id, String reaction) {
        this.id = id;
        this.reaction = reaction;
    }

    public Reaction(String reaction) {
        this.reaction = reaction;
    }
}
