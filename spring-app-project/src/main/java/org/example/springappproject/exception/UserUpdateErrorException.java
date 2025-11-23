package org.example.springappproject.exception;

// ユーザ情報のアップデートに不備がある
public class UserUpdateErrorException extends RuntimeException{
    public UserUpdateErrorException(String cause){
        super(cause);
    }
}
