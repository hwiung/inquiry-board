package com.example.board.user.dto;

import com.example.board.enums.UserRole;
import lombok.Getter;

@Getter
public class UserSignupRequestDto {
    private String username;
    private String email;
    private String password;
    private UserRole role;

    public UserSignupRequestDto(String username, String email, String password, UserRole role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

}
