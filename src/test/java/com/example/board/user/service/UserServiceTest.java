package com.example.board.user.service;

import com.example.board.user.domain.User;
import com.example.board.user.dto.*;
import com.example.board.user.repository.UserMapper;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static com.example.board.enums.UserRole.USER;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class UserServiceTest {
    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void signupTest() {
        // given
        UserSignupRequestDto dto = new UserSignupRequestDto("hwiung", "hwiung@naver.com", "nana", USER);
        when(passwordEncoder.encode("nana")).thenReturn("nana");
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
        // given: 테스트에 쓸 가짜 유저 객체 생성(생성자에 id 파라미터도)
        User user = new User(1L, "hwiung", "hwiung@naver.com", "nana123!@#");
        when(userMapper.findUserById(1L)).thenReturn(user);

        // when: 서비스 메소드 호출
        UserResponseDto result = userService.getUserById(1L);

        // then: 결과 검증
        assertEquals(1L, result.getId());
        assertEquals("hwiung", result.getUsername());
        assertEquals("hwiung@naver.com", result.getEmail());
    }

    @Test
    void getAllUsersTest() {
        // given
        User user1 = new User(1L, "hwiung", "hwiung1@naver.com", "nana123!@#");
        User user2 = new User(2L, "hwiung2", "hwiung2@naver.com", "nana123!@#");
        List<User> userList = List.of(user1, user2);
        when(userMapper.findAllUsers()).thenReturn(userList);

        // when
        List<UserResponseDto> result = userService.getAllUsers();

        // then
        assertEquals(2, result.size());

        assertEquals(1L, result.get(0).getId());
        assertEquals("hwiung", result.get(0).getUsername());
        assertEquals("hwiung1@naver.com", result.get(0).getEmail());

        assertEquals(2L, result.get(1).getId());
        assertEquals("hwiung2", result.get(1).getUsername());
        assertEquals("hwiung2@naver.com", result.get(1).getEmail());


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

        assertEquals(3L, pageDto.getContent().get(0).getId());
        assertEquals("hwiung3", pageDto.getContent().get(0).getUsername());
        assertEquals("hwiung3@naver.com", pageDto.getContent().get(0).getEmail());

        assertEquals(4L, pageDto.getContent().get(1).getId());
        assertEquals("hwiung4", pageDto.getContent().get(1).getUsername());
        assertEquals("hwiung4@naver.com", pageDto.getContent().get(1).getEmail());

    }

    @Test
    void updateUserTest() {
        // given
        User user = new User(1L, "hwiung", "hwiung@naver.com", "aa123!@");
        UserUpdateRequestDto dto = new UserUpdateRequestDto("hwiung2", "hwiung123@naver.com");
        when(userMapper.findUserById(1L)).thenReturn(user);
        doNothing().when(userMapper).updateUser(any(User.class));

        // when
        userService.updateUser(dto, 1L);

        // then
        verify(userMapper, times(1)).updateUser(any(User.class));   // 호출 검증

        assertAll(  // 변경 검증
                () -> assertEquals("hwiung2", user.getUsername()),
                () -> assertEquals("hwiung123@naver.com", user.getEmail())
        );
    }

    @Test
    void updateUserNameTest() {
        // given
        User user = new User(1L, "hwiung", "hwiung@naver.com", "qwer123!@#");
        UserNameUpdateRequestDto dto = new UserNameUpdateRequestDto("hwiung3");
        when(userMapper.findUserById(1L)).thenReturn(user);
        doNothing().when(userMapper).updateUserName(any(User.class));

        // when
        userService.updateUserName(dto, 1L);

        // then
        verify(userMapper, times(1)).updateUserName(any(User.class));
        assertEquals("hwiung3", user.getUsername());

    }

    @Test
    void updateUserPasswordTest() {
        // given
        Long userId = 1L;
        String newPassword = "asdf123!@#";
        UserPasswordUpdateRequestDto dto = new UserPasswordUpdateRequestDto(newPassword);
        doNothing().when(userMapper).updateUserPassword(any(Long.class), any(String.class));

        // when
        userService.updateUserPassword(dto, userId);

        // then
        verify(userMapper, times(1)).updateUserPassword(any(Long.class), any(String.class));

    }

    @Test
    void deleteUserTest() {
        // given
        Long userId = 1L;
        String password = "qwer!@#";
        HttpSession session = new MockHttpSession();
        session.setAttribute("userId", userId);

        User mockUser = new User(userId, "hwiung", "hwiung@naver.com", password);
        //userMapper가 호출되면 mockUser를 반환하도록 설정
        when(userMapper.findUserById(userId)).thenReturn(mockUser);
        when(passwordEncoder.matches(password, mockUser.getPassword())).thenReturn(true);

        // when
        userService.deleteUser(userId, password, session);

        // then
        verify(userMapper, times(1)).deleteUserById(userId);

    }
}