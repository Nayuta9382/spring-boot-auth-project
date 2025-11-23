package org.example.springappproject.controller;

import org.example.springappproject.auth.CustomUserDetail;
import org.example.springappproject.dto.request.SignupRequestDto;
import org.example.springappproject.dto.request.UserUpdateRequestDto;
import org.example.springappproject.dto.response.MessageResponseDto;
import org.example.springappproject.dto.response.signup.SignupResponseSuccessDto;
import org.example.springappproject.dto.response.signup.SignupUserInfoDto;
import org.example.springappproject.dto.response.user.update.UserUpdateResponseSuccessDto;
import org.example.springappproject.entity.User;
import org.example.springappproject.exception.UserNotFoundException;
import org.example.springappproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ユーザ情報を取得する
    @GetMapping("/users/{userId}")
    public ResponseEntity<Object> getUser(@PathVariable("userId") String userId) {
        User user = userService.getUserByUserId(userId);

        if(user == null){
            throw new UserNotFoundException();
        }

        Object responseDto = userService.ChengeResponseDto(user);

        return ResponseEntity.ok(responseDto);
    }

    // サインアップ処理
    @PostMapping("/signup")
    public ResponseEntity<SignupResponseSuccessDto> signup(@RequestBody SignupRequestDto signupRequestDto){
        String userId = signupRequestDto.getUserId();
        String password = signupRequestDto.getPassword();
        userService.addUser(userId ,password);

        SignupUserInfoDto signupUserInfoDto = new SignupUserInfoDto(userId, userId);
        SignupResponseSuccessDto signupResponseSuccessDto = new SignupResponseSuccessDto("Account successfully created",signupUserInfoDto);
        return ResponseEntity.ok(signupResponseSuccessDto);
                
    }

    // ユーザ情報を更新する
    @PatchMapping("/users/{userId}")
    public ResponseEntity<UserUpdateResponseSuccessDto> updateUser(@PathVariable("userId") String userId, @RequestBody UserUpdateRequestDto userUpdateRequestDto, @AuthenticationPrincipal CustomUserDetail userDetail) {
        String base64UserId = userDetail.getUserId();

        // ユーザ情報を更新する
        userService.updateUser(userId,userUpdateRequestDto,base64UserId);

        // レスポンス
        UserUpdateResponseSuccessDto userUpdateResponseSuccessDto = userService.getUserUpdateResponseSuccessDto(userId);

        return ResponseEntity.ok(userUpdateResponseSuccessDto);
    }

    // ユーザを削除する
    @PostMapping("/close")
    public ResponseEntity<MessageResponseDto> deleteUser(@AuthenticationPrincipal CustomUserDetail userDetail) {
        String userId = userDetail.getUserId();

        userService.deleteUser(userId);

        return ResponseEntity.ok(new MessageResponseDto("Account and user successfully removed"));
    }


}
