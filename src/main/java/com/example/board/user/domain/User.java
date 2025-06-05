package com.example.board.user.domain;

import lombok.Getter;

@Getter
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;

    // 전체 필드 생성자
    public User(Long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
