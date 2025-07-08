package com.example.board.user.service;

import com.example.board.user.domain.User;
import com.example.board.user.dto.UserSignupRequestDto;
import com.example.board.user.repository.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)

public class UserServiceTest {
    @Mock
    UserMapper userMapper;

    @InjectMocks
    UserService userService;

    @Test
    void signupTest() {
        // given
        UserSignupRequestDto dto = new UserSignupRequestDto("hwiung", "hwiung@naver.com", "nana");

        // when
        userService.signup(dto);

        // then
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userMapper, times(1)).insertUser(captor.capture()); // insertUser 1번 호출 확인

        User savedUser = captor.getValue();
        assertEquals("hwiung", savedUser.getUsername());
        assertEquals("hwiung@naver.com", savedUser.getEmail());
        assertEquals("nana", savedUser.getPassword());
    }
}