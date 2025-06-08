package com.example.board.user.controller;

import com.example.board.user.domain.User;
import com.example.board.user.dto.UserNameUpdateRequest;
import com.example.board.user.dto.UserUpdateRequest;
import com.example.board.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    // 생성자 주입
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입
    @PostMapping
    public void signup(@RequestBody User user) {
        userService.signup(user);
    }

    // 단일 유저 조회
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // 전체 유저 조회
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // 회원 수정
    @PatchMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest userUpdateRequest) {
        userService.updateUser(userUpdateRequest, id);
    }

    // 회원 이름 변경
    @PatchMapping("/user-name/{id}")
    public void updateUserName(@PathVariable Long id, @RequestBody UserNameUpdateRequest userNameUpdateRequest) {
        userService.updateUserName(userNameUpdateRequest, id);
    }

    // 회원 탈퇴
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}
