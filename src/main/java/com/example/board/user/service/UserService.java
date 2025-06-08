package com.example.board.user.service;

import com.example.board.user.controller.UserController;
import com.example.board.user.domain.User;
import com.example.board.user.dto.UserNameUpdateRequest;
import com.example.board.user.dto.UserUpdateRequest;
import com.example.board.user.repository.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserMapper userMapper;

    // 생성자 주입
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    // 회원가입
    public void signup(User user) {
        userMapper.insertUser(user);
    }

    // 특정 유저 조회
    public User getUserById(Long id) {
        return userMapper.findUserById(id);
    }

    // 전체 유저 조회
    public List<User> getAllUsers() {
        return userMapper.findAllUsers();
    }

    // 회원 수정
    public void updateUser(UserUpdateRequest userUpdateRequest, Long id) {
        User user = userMapper.findUserById(id);
        user.updateUser(userUpdateRequest.getUsername(), userUpdateRequest.getEmail(), userUpdateRequest.getPassword());
        userMapper.updateUser(user);
    }

    // 회원 이름 수정
    public void updateUserName(UserNameUpdateRequest userNameUpdateRequest, Long id) {
        User userName = userMapper.findUserById(id);
        userName.updateUserName(userNameUpdateRequest.getUsername());
        userMapper.updateUserName(userName);

    }

    // 회원 탈퇴
    public void deleteUser(Long id) {
        userMapper.deleteUserById(id);
    }
}
