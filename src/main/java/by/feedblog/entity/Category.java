package by.feedblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private int id;
    private String title;

    public Category(String title) {
        this.title = title;
    }
}
