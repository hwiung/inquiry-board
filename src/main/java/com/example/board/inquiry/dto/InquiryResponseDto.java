package com.example.board.inquiry.dto;

import com.example.board.inquiry.domain.Inquiry;

public class InquiryResponseDto {

    private Long id;
    private String username;
    private String title;
    private String content;

    public InquiryResponseDto() {

    }

    public InquiryResponseDto(Long id, String username, String title, String content) {

        this.id = id;
        this.username = username;
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public static InquiryResponseDto from(Inquiry inquiry) {
        return new InquiryResponseDto(
                inquiry.getId(),
                inquiry.getUsername(),
                inquiry.getTitle(),
                inquiry.getContent()
        );
    }

}
