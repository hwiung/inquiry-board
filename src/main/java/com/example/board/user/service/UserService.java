package com.example.board.user.service;

import com.example.board.user.domain.User;
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

    // 회원 탈퇴
    public void deleteUser(Long id) {
        userMapper.deleteUserById(id);
    }
}
