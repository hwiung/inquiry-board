package com.example.board.inquiry.domain;

public class Inquiry {

    private Long id;
    private String username;
    private String title;
    private String content;

    public Inquiry() {
    }

    //생성자 이름은 같고 매개변수 구성은 다르게 -> 오버로딩!(메소드도 오버로딩 가능함)
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
