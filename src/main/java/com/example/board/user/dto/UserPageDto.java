package com.example.board.user.dto;

import com.example.board.user.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserPageDto {

    private final List<UserResponseDto> content;
    private final int page;
    private final int size;
    //전체 user 수(총 레코드 수)를 담는 필드(멤버 변수)
    private final long totalElements;
    private final int totalPages;


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
