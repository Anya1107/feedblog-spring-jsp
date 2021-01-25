package by.feedblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String username;
    private String password;
    private String fullName;
    private int age;
    private Role role;

    public User(String username, String password, String fullName, int age) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.age = age;
    }

    public User(String username, String password, String fullName, int age, Role role) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.age = age;
        this.role = role;
    }

    public User(int id, String username, String password, String fullName, int age) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.age = age;
    }
}
