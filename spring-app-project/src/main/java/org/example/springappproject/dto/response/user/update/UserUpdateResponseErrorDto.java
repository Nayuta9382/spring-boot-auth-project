package org.example.springappproject.dto.response.user.update;

// ユーザ情報に不備があったときのレスポンスDTO
public class UserUpdateResponseErrorDto {
    private String message;
    private String cause;

    public UserUpdateResponseErrorDto(String message, String cause) {
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
