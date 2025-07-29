package com.example.board.inquiry.domain;

public class Inquiry {

    private Long id;
    private Long userId;    // FK(유저의 id 참조)
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

    public Inquiry(Long id, String email, String title, String content) {
        this.id = id;
        this.email = email;
        this.title = title;
        this.content = content;
    }

    public Inquiry(Long userId, Long id, String email, String title, String content) {
        this.userId = userId;
        this.id = id;
        this.email = email;
        this.title = title;
        this.content = content;
    }

    public Inquiry(Long id, String username, String email, String title, String content) {
        this.id = id;
        this.username = username;
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

    // 커스텀 변경 메소드 -> 서비스에서 명확하게 "이 메소드만" 사용해서 값 변경. setter 없이도 안전하게, 의도 명확하게 구현 가능.
    public void updateContent(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
