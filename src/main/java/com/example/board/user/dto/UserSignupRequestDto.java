package com.example.board.user.dto;

import lombok.Getter;

@Getter
public class UserSignupRequestDto {
    private String username;
    private String email;
    private String password;
}
