package org.example.springappproject.dto.response.signup;

// サインアップ成功時に返すレスポンスDTOクラス
public class SignupResponseSuccessDto {
    private String message;
    private SignupUserInfoDto user;

    public SignupResponseSuccessDto(String message, SignupUserInfoDto user) {
        this.message = message;
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SignupUserInfoDto getUser() {
        return user;
    }

    public void setUser(SignupUserInfoDto user) {
        this.user = user;
    }
}
