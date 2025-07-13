package com.example.board.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class UserNameUpdateRequestDto {

    private String username;

    public UserNameUpdateRequestDto(String username) {
        this.username = username;
    }
}
