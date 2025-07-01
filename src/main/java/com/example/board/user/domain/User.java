package com.example.board.user.domain;

import lombok.Getter;

@Getter
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void updateUser(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void updateUserName(String username) {
        this.username = username;
    }

}
