package com.example.board.user.service;

import com.example.board.user.domain.User;
import com.example.board.user.dto.UserPageDto;
import com.example.board.user.dto.UserResponseDto;
import com.example.board.user.dto.UserSignupRequestDto;
import com.example.board.user.repository.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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
    @Test
    void getUserTest() {
        // given: 테스트에 쓸 가짜 유저 객체 생성(생성자에서 id까지)
        User user = new User(1L, "hwiung", "hwiung@naver.com", "nana123!@#");
        when(userMapper.findUserById(1L)).thenReturn(user);

        // when: 서비스 메소드 호출
        UserResponseDto result = userService.getUserById(1L);

        // then: 결과 검증
        assertEquals("hwiung", result.getUsername());
        assertEquals("hwiung@naver.com", result.getEmail());
    }
    @Test
    void getAllUsersTest() {
        // given
        User user1 = new User ("hwiung", "hwiung1@naver.com", "nana123!@#");
        User user2 = new User ("hwiung2", "hwiung2@naver.com", "nana123!@#");
        List<User> userList = List.of(user1, user2);
        when(userMapper.findAllUsers()).thenReturn(userList);

        // when
        List<UserResponseDto> result = userService.getAllUsers();

        // then
        assertEquals(2, result.size());
        assertEquals("hwiung", result.get(0).getUsername());
        assertEquals("hwiung2", result.get(1).getUsername());

    }

    @Test
    void listByPageTest() {
        // given
        int page = 1;
        int size = 2;
        int offset = page * size;

        User user1 = new User(3L, "hwiung3", "hwiung3@naver.com", "nana123!@#");
        User user2 = new User(4L, "hwiung4", "hwiung4@naver.com", "nana123!@#");
        List<User> pagedList = List.of(user1, user2);

        // userMapper가 반환해야 하는 값들 미리 지정
        when(userMapper.findUserByPage(offset, size)).thenReturn(pagedList);
        when(userMapper.countUsers()).thenReturn(10L);

        // when
        UserPageDto pageDto = userService.getUserByPage(page, size);

        // then
        assertEquals(page, pageDto.getPage());
        assertEquals(size, pageDto.getSize());
        assertEquals(10L, pageDto.getTotalElements());
        assertEquals(2, pageDto.getContent().size());
        assertEquals("hwiung3", pageDto.getContent().get(0).getUsername());
        assertEquals("hwiung4", pageDto.getContent().get(1).getUsername());

    }
}