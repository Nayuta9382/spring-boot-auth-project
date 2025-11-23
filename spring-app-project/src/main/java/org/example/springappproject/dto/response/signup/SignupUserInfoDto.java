package org.example.springappproject.dto.response.signup;

//サインアップ時のユーザ情報を返すレスポンスDTOクラス
public class SignupUserInfoDto {
    private String userId;
    private String nickname;

    public SignupUserInfoDto(String userId, String nickname) {
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
