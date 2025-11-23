package org.example.springappproject.entity;

import java.time.LocalDateTime;

public class User {
    private String userId;
    private String password;
    private String nickname;
    private String comment;
    private LocalDateTime createdAt;

    public User(String userId, String password, String nickname, String comment, LocalDateTime createdAt) {
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
