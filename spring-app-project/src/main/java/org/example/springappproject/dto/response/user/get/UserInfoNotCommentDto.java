package org.example.springappproject.dto.response.user.get;

public class UserInfoNotCommentDto {
    private String userId;
    private String nickname;

    public UserInfoNotCommentDto(String userId, String nickname) {
        this.userId = userId;
        this.nickname = nickname;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
