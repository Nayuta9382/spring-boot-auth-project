package org.example.springappproject.exception;

// サインアップ情報に不備がある
public class SignupValidationException extends RuntimeException{
    public SignupValidationException(String cause){
        super(cause);
    }
}
