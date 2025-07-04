package com.example.board.inquiry.domain;

public class Inquiry {

    private Long id;
    private Long userId;    //FK(유저의 id 참조)
    private String username;
    private String email;
    private String title;
    private String content;

    public Inquiry() {
    }

    public Inquiry(String email, String title, String content) {
        this.email = email;
        this.title = title;
        this.content = content;
    }

    public Inquiry(Long userId, String email, String title, String content) {
        this.userId = userId;
        this.email = email;
        this.title = title;
        this.content = content;
    }

    public Inquiry(Long id, Long userId, String title, String content) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
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
