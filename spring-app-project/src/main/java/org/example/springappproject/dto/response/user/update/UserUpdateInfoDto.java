package org.example.springappproject.dto.response.user.update;

public class UserUpdateInfoDto {
    private String userId;
    private String nickname;
    private String comment;

    public UserUpdateInfoDto(String userId, String nickname, String comment) {
        this.userId = userId;
        this.nickname = nickname;
        this.comment = comment;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}
