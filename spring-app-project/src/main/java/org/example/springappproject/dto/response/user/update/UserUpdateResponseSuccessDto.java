package org.example.springappproject.dto.response.user.update;


public class UserUpdateResponseSuccessDto {
    private String message;
    private UserUpdateInfoDto user;



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserUpdateInfoDto getUser() {
        return user;
    }

    public void setUser(UserUpdateInfoDto user) {
        this.user = user;
    }
}
