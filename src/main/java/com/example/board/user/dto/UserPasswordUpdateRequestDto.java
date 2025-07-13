package com.example.board.user.dto;

import lombok.Getter;

@Getter
public class UserPasswordUpdateRequestDto {

    private String newPassword;

    public UserPasswordUpdateRequestDto(String newPassword) {
        this.newPassword = newPassword;
    }
}
