package com.example.board.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserUpdateRequestDto {

    private String username;
    private String email;
    private String password;
}
