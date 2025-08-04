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

    public Inquiry(Long id, String title, String content) {
        this.id = id;
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

    // 엔터티에 전용 변경 메소드(커스텀 변경 메소드) 사용. Setter 직접 호출을 최소화하여 객체 상태 변경을 한 곳에서 관리하기 위함.
    // Setter 호출 대신에 전용 메소드를 사용하여 객체 자신이 스스로 자신의 상태(속성값)를 적절하고 일관성 있게 관리함.
    public void updateInquiry(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
