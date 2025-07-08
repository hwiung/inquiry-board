package com.example.board.user.dto;

import lombok.Getter;

@Getter
public class UserSignupRequestDto {
    private String username;
    private String email;
    private String password;

    public UserSignupRequestDto(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
