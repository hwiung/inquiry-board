package com.example.board.inquiry.domain;

public class Inquiry {

    private Long id;
    private String username;
    private String title;
    private String content;

    public Inquiry() {
    }

    public Inquiry(Long id, String username, String title, String content) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.content = content;
    }

    public Inquiry(String title, String content) {
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

}
