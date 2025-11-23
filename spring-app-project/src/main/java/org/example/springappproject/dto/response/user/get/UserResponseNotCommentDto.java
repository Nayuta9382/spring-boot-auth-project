package org.example.springappproject.dto.response.user.get;
// ユーザ情報を取得するリクエストからのレスポンスクラス(コメントナシ)
public class UserResponseNotCommentDto {
    private String message;
    private UserInfoNotCommentDto user;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserInfoNotCommentDto getUser() {
        return user;
    }

    public void setUser(UserInfoNotCommentDto user) {
        this.user = user;
    }
}
