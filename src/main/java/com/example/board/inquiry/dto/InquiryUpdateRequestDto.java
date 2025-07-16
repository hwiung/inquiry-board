package com.example.board.inquiry.dto;

public class InquiryUpdateRequestDto {

    private Long id;
    private String email;
    private String title;
    private String content;

    public InquiryUpdateRequestDto(Long id, String email, String title, String content) {
        this.id = id;
        this.email = email;
        this.title = title;
        this.content = content;
    }

    public Long getId() {

        return id;
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
