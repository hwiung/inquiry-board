package com.example.board.user.repository;

import com.example.board.user.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    // 유저 생성
    void insertUser(User user);

    // 특정 유저 조회
    User findUserById(Long id);

    // 전체 유저 조회
    List<User> findAllUsers();

    // 페이징 조회
    List<User> findUserByPage(
            @Param("offset") int offset,
            @Param("limit") int limit
    );

    // 총 레코드 조회
    long countUsers();

    // 유저 수정
    void updateUser(User user);

    // 유저 이름 수정
    void updateUserName(User userName);

    // 패스워드 수정
    void updateUserPassword(@Param("id") Long id, @Param("newPassword") String newPassword);

    // 유저 삭제
    void deleteUserById(Long id);
}