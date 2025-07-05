package com.example.board.user.service;

import com.example.board.user.domain.User;
import com.example.board.user.dto.*;
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
        User user = new User(dto.getUsername(), dto.getEmail(), dto.getPassword());
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

    // 페이징 조회
    public UserPageDto getUserByPage(int page, int size) {
        // 1) offset 계산
        int offset = page * size;
        // 2) 페이징된 사용자 목록 조회
        List<User> users = userMapper.findUserByPage(offset, size);
        // 3) 전체 사용자 수 조회(페이지 계산용)
        long totalCount = userMapper.countUsers();
        // 4) dto 생성 및 반환
        return new UserPageDto(users, page, size, totalCount);
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
