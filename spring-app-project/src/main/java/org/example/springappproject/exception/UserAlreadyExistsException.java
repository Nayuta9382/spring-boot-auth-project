package org.example.springappproject.exception;

// 既に同じユーザが存在する
public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(){
        super();
    }
}
