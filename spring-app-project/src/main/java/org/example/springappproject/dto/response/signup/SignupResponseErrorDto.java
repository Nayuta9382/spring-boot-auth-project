package org.example.springappproject.dto.response.signup;

// サインアップ時の失敗情報をレスポンスするクラス
public class SignupResponseErrorDto {
    private String message;
    private String cause;

    public SignupResponseErrorDto(String message, String cause) {
        this.message = message;
        this.cause = cause;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
