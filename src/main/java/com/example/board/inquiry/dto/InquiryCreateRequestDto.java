package com.example.board.inquiry.dto;

public class InquiryCreateRequestDto {

    //todo 리팩토링 시 requestDto에선 userId 필드 제거. 로그인(인증) 기능 미구현이라 userId 값을 입력하는 것임.
    private Long userId;
    private String email;
    private String title;
    private String content;

    public InquiryCreateRequestDto(String email, String title, String content) {
        this.email = email;
        this.title = title;
        this.content = content;
    }

    public Long getUserId() {

        return userId;
    }

    public String getEmail() {

        return email;
    }

    public String getTitle() {

        return title;
    }

    public String getContent() {

        return content;
    }
}
