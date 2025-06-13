package com.example.board.user.service;

import com.example.board.user.domain.User;
import com.example.board.user.dto.UserNameUpdateRequestDto;
import com.example.board.user.dto.UserResponseDto;
import com.example.board.user.dto.UserSignupRequestDto;
import com.example.board.user.dto.UserUpdateRequestDto;
import com.example.board.user.repository.UserMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserMapper userMapper;

    // 생성자 주입
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    // 회원가입
    public void signup(UserSignupRequestDto dto) {
        User user = new User(null, dto.getUsername(), dto.getEmail(), dto.getPassword());
        userMapper.insertUser(user);
    }

    // 특정 유저 조회
    public UserResponseDto getUserById(Long id) {
        User user = userMapper.findUserById(id);
        return UserResponseDto.from(user);
    }


    // 전체 유저 조회
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userMapper.findAllUsers();
        List<UserResponseDto> result = new ArrayList<>();
        for (User user : users) {
            result.add(UserResponseDto.from(user));
        }
        return result;
    }

    // 회원 수정
    public UserResponseDto updateUser(UserUpdateRequestDto userUpdateRequestDto, Long id) {
        User user = userMapper.findUserById(id);
        user.updateUser(userUpdateRequestDto.getUsername(), userUpdateRequestDto.getEmail(), userUpdateRequestDto.getPassword());
        userMapper.updateUser(user);
        return UserResponseDto.from(user);
    }

    // 회원 이름 수정
    public UserResponseDto updateUserName(UserNameUpdateRequestDto userNameUpdateRequestDto, Long id) {
        User userName = userMapper.findUserById(id);
        userName.updateUserName(userNameUpdateRequestDto.getUsername());
        userMapper.updateUserName(userName);
        return UserResponseDto.from(userName);

    }

    // 패스워드 수정
    public void updateUserPassword(Long id, String newPassword) {
        userMapper.updateUserPassword(id, newPassword);
    }

    // 회원 탈퇴
    public void deleteUser(Long id) {
        userMapper.deleteUserById(id);
    }
}
