package org.example.springappproject.exception;

// Userが存在しない
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){
        super("No user found");
    }
}
