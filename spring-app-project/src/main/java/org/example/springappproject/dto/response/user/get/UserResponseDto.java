package org.example.springappproject.dto.response.user.get;

// ユーザ情報を取得するリクエストからのレスポンスクラス
public class UserResponseDto {
    private String message;
    private UserInfoDto user;



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserInfoDto getUser() {
        return user;
    }

    public void setUser(UserInfoDto user) {
        this.user = user;
    }
}
