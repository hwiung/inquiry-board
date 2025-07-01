package com.example.board.inquiry.domain;

public class Inquiry {

    private Long id;
    private Long userId;    //FK(유저의 id 참조)
    private String email;
    private String title;
    private String content;

    public Inquiry() {
    }

    //생성자 이름은 같고 매개변수 구성(타입, 순서, 개수)은 다르게 -> 오버로딩!(메소드도 오버로딩 가능함)
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

    public Long getId() {
        return id;
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
