package com.example.board.user.repository;

import com.example.board.user.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    // 유저 생성
    void insertUser(User user);

    // 특정 유저 조회
    User findUserById(Long id);

    // 전체 유저 조회
    List<User> findAllUsers();

    // 유저 삭제
    void deleteUserById(Long id);
}