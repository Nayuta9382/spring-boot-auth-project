package org.example.springappproject.exception;

import org.example.springappproject.dto.response.MessageResponseDto;
import org.example.springappproject.dto.response.signup.SignupResponseErrorDto;
import org.example.springappproject.dto.response.user.update.UserUpdateResponseErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 例外をキャッチしてレスポンスを返すクラス
@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException{


    // Userが存在しない
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<MessageResponseDto> handleUserRegistrationException(UserNotFoundException e){
        return ResponseEntity.badRequest().body(new MessageResponseDto(e.getMessage()));
    }

    // 既に同じユーザが存在する
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<SignupResponseErrorDto> handleUserAlreadyExistsException(UserAlreadyExistsException e){
        SignupResponseErrorDto signupResponseErrorDto = new SignupResponseErrorDto("Account creation failed","Already same user_id is used");

        return ResponseEntity.badRequest().body(signupResponseErrorDto);
    }

    // サインアップ情報に不備がある
    @ExceptionHandler(SignupValidationException.class)
    public ResponseEntity<SignupResponseErrorDto> SignupValidationException(SignupValidationException e){
        SignupResponseErrorDto signupResponseErrorDto = new SignupResponseErrorDto("Account creation failed",e.getMessage());

        return ResponseEntity.badRequest().body(signupResponseErrorDto);
    }

    // ユーザ情報のアップデートに不備がある
    @ExceptionHandler(UserUpdateErrorException.class)
    public ResponseEntity<UserUpdateResponseErrorDto> UserUpdateErrorException(UserUpdateErrorException e){
        UserUpdateResponseErrorDto userUpdateResponseErrorDto = new UserUpdateResponseErrorDto("User updation failed",e.getMessage());

        return ResponseEntity.badRequest().body(userUpdateResponseErrorDto);
    }

    // トークンとパスパラメータのUserIdか異なった時の例外
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<MessageResponseDto> AccessDeniedException(AccessDeniedException e){
        return ResponseEntity.status(403).body(new MessageResponseDto("No permission for update"));
    }




}
