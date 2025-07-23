package com.example.board.user.service;

import com.example.board.exception.NotFoundException;
import com.example.board.exception.UnauthorizedException;
import com.example.board.user.domain.User;
import com.example.board.user.dto.*;
import com.example.board.user.repository.UserMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    // 생성자 주입
    public UserService(UserMapper userMapper, PasswordEncoder passwordEncoder) {

        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    // 회원가입
    public void signup(UserSignupRequestDto dto) {
        // 암호화
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        User user = new User(dto.getUsername(), dto.getEmail(), encodedPassword, dto.getRole());
        userMapper.insertUser(user);
    }

    // 로그인
    public User login(LoginRequestDto dto) {
        User user = userMapper.findUserByEmail(dto.getEmail());
        if (user == null) throw new IllegalArgumentException("이메일 없음");
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호 불일치");
        }
        return user;
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
    public void updateUserPassword(UserPasswordUpdateRequestDto userPasswordUpdateRequestDto, Long id) {
        userMapper.updateUserPassword(id, userPasswordUpdateRequestDto.getNewPassword());
    }

    // 회원 탈퇴
    public void deleteUser(Long id, String password, HttpSession session) {

        // 1. 세션 인증(본인 확인)
        Long loginId = (Long) session.getAttribute("userId");   // 우변 -> 강제 형변환
        if (loginId == null || !loginId.equals(id)) {
            throw new UnauthorizedException("본인만 탈퇴 가능");
        }

        // 2. DB에서 User 객체 조회
        User user = userMapper.findUserById(id);
        if (user == null) {
            throw new NotFoundException("해당 회원 없음");
        }

        // 3. 비밀번호 일치 확인
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호 불일치");
        }

        // 4. 실제 회원 탈퇴 처리
        userMapper.deleteUserById(id);
    }
}
