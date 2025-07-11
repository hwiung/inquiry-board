package com.example.board.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
public class UserUpdateRequestDto {

    private String username;
    private String email;
    private String password;

    public UserUpdateRequestDto(String username) {
        this.username = username;
    }

    public UserUpdateRequestDto(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public UserUpdateRequestDto(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

}
