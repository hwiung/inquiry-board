package com.example.board.user.controller;

import com.example.board.dto.PasswordConfirmRequestDto;
import com.example.board.user.domain.User;
import com.example.board.user.dto.*;
import com.example.board.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
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

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto dto, HttpSession session) {
        User user = userService.login(dto);
        session.setAttribute("userId", user.getId()); // 세션에 로그인 저장
        return ResponseEntity.ok("로그인 성공");
    }

    // 로그아웃
    @PostMapping("/logout")
    public void logout(HttpSession session) {
        session.invalidate();
    }

    // 회원가입
    @PostMapping
    public void signup(@RequestBody UserSignupRequestDto requestDto) {

        userService.signup(requestDto);
    }

    // 단일 유저 조회
    @GetMapping("/{id}")
    public UserResponseDto getUser(@PathVariable Long id) {

        return userService.getUserById(id);
    }

    // 전체 유저 조회 -> 연습용이라 일단, 파라미터 없을 땐 전체 조회
    @GetMapping(params = "!page")
    public List<UserResponseDto> getAllUsers() {

        return userService.getAllUsers();
    }

    // 페이징 조회 -> page, size 파라미터 있을 때만 호출
    @GetMapping(params = {"page", "size"})
    public UserPageDto listByPage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        return userService.getUserByPage(page, size);
    }

    // 회원 수정
    @PatchMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        userService.updateUser(userUpdateRequestDto, id);
    }

    // 회원 이름 변경
    @PatchMapping("/{id}/username")
    public void updateUserName(@PathVariable Long id, @RequestBody UserNameUpdateRequestDto userNameUpdateRequestDto) {
        userService.updateUserName(userNameUpdateRequestDto, id);
    }

    // 패스워드 변경
    @PatchMapping("/{id}/password")
    public void updatePassword(@PathVariable Long id, @RequestBody UserPasswordUpdateRequestDto userPasswordUpdateRequestDto) {
        userService.updateUserPassword(userPasswordUpdateRequestDto, id);
    }

    // 회원 탈퇴
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id, @RequestBody PasswordConfirmRequestDto dto, HttpSession session) {
        userService.deleteUser(id, dto.getPassword(), session);
    }

}
