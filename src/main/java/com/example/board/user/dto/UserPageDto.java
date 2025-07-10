package com.example.board.user.dto;

import com.example.board.user.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserPageDto {

    private final List<UserResponseDto> content;    // 이 페이지에 담긴 유저 목록(실제 조회 결과)
    private final int page;     // 현재 페이지 번호
    private final int size;     // 한 페이지에 보여줄 최대 유저 수(page size)

    //전체 user 수(총 레코드 수)를 담는 필드(멤버 변수)
    private final long totalElements;       // 전체 검색 결과(조건 적용x, 전체 유저 수)
    private final int totalPages;       // 전체 페이지 수(totalElements / size)


    public UserPageDto(List<User> users, int page, int size, long totalElements) {
        List<UserResponseDto> list = new ArrayList<>();
        for (User user : users) {
            list.add(UserResponseDto.from(user));
        }
        this.content = list;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = (int) Math.ceil((double) totalElements / size);
    }

    public List<UserResponseDto> getContent() {
        return content;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
